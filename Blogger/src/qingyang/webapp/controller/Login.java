
package qingyang.webapp.controller;


import org.genericdao.RollbackException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import qingyang.webapp.databean.UserBean;
import qingyang.webapp.formbean.LoginForm;
import qingyang.webapp.model.Model;
import qingyang.webapp.model.UserDAO;

public class Login extends Action{
       
	private UserDAO userDAO;

	public Login(Model model) {
        userDAO = model.getUserDAO();
    }
	
	public String getName() {
        return "Login.do";
    }
	
	public String performGet(HttpServletRequest request) {
        // If user is ALREADY logged in, redirect to todolist.do
        if (request.getSession().getAttribute("user") != null) {
            return "Homepage.do";
        }

        // Otherwise, just display the login page.
        try {
        request.setAttribute("form", new LoginForm());
        HttpSession session = request.getSession();
        session.setAttribute("allusers", userDAO.getUsers());
        } catch(RollbackException e) {
        	request.setAttribute("error", e.getMessage());
        }
        return "Login.jsp";
    }

    public String performPost(HttpServletRequest request) {
        // If user is ALREADY logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "Homepage.do";
        }
        try {
            LoginForm form = new LoginForm(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "Login.jsp";
            }

            // Look up the user
            UserBean user = userDAO.read(form.getEmail());
            if (user == null) {
                form.addFieldError("email", "Email not found");
                return "Login.jsp";
            }

            // Check the password
            if (!user.getPassword().equals(form.getPassword())) {
                form.addFieldError("password", "Incorrect password");
                return "Login.jsp";
            }

            session.setAttribute("user", user);
            session.setAttribute("blogger", user);

            return "Homepage.do";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "Error.jsp";
        }
    }

}
