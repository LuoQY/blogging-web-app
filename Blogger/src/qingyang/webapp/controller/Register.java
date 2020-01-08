package qingyang.webapp.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import qingyang.webapp.databean.UserBean;
import qingyang.webapp.formbean.RegisterForm;
import qingyang.webapp.model.Model;
import qingyang.webapp.model.UserDAO;

public class Register extends Action {
    private UserDAO userDAO;

    public Register(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "Register.do";
    }

    public String performGet(HttpServletRequest request) {
        // If user is ALREADY logged in, redirect to todolist.do
        if (request.getSession().getAttribute("user") != null) {
            return "Homepage.do";
        }

        // Otherwise, just display the register page.
        request.setAttribute("form", new RegisterForm());
        return "Register.jsp";
    }

    public String performPost(HttpServletRequest request) {
        // If user is ALREADY logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "Homepage.do";
        }

    	RegisterForm form = new RegisterForm(request);
        request.setAttribute("form", form);

        // Any validation errors?
        if (form.hasValidationErrors()) {
            return "Register.jsp";
        }

        try {
            UserBean newUser = new UserBean();
            newUser.setFirstName(form.getFirstName());
            newUser.setLastName(form.getLastName());
            newUser.setEmail(form.getEmail());
            newUser.setPassword(form.getPassword());
            userDAO.create(newUser);

            session.setAttribute("allusers", userDAO.getUsers());
            session.setAttribute("user", newUser);
            return ("Homepage.do");
        } catch (DuplicateKeyException e) {
            form.addFieldError("email", "A user with this email already exists");
            return "Register.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "Error.jsp";
        }
    }
}
