package qingyang.webapp.controller;

import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import qingyang.webapp.model.PostDAO;
import qingyang.webapp.model.Model;
import qingyang.webapp.model.CommentDAO;
import qingyang.webapp.formbean.PostForm;



public class Homepage extends Action {
	private PostDAO postDAO;
	private CommentDAO commentDAO;

    public Homepage(Model model) {
    	postDAO = model.getPostDAO();
    	commentDAO = model.getCommentDAO();
    }

    public String getName() {
        return "Homepage.do";
    }

    public String performGet(HttpServletRequest request) {
        // If user is NOT logged in, redirect to login.do
        if (request.getSession().getAttribute("user") == null) {
            return "Login.do";
        }

        try {
            request.setAttribute("posts", postDAO.getPosts());
            request.setAttribute("comments", commentDAO.getItems());
            request.setAttribute("form",  new PostForm());

            return ("HomePage.jsp");
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "Error.jsp";
        }
    }
}
