package qingyang.webapp.controller;

import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import qingyang.webapp.model.PostDAO;
import qingyang.webapp.model.Model;
import qingyang.webapp.model.CommentDAO;
import qingyang.webapp.databean.UserBean;
import qingyang.webapp.formbean.UserIdForm;
import qingyang.webapp.model.UserDAO;

public class Notloggedin extends Action {
	
	private PostDAO postDAO;
	private CommentDAO commentDAO;
	private UserDAO userDAO;
	
	public Notloggedin(Model model) {
    	postDAO = model.getPostDAO();
    	commentDAO = model.getCommentDAO();
    	userDAO = model.getUserDAO();
    }
	
	public String getName() {
        return "Notloggedin.do";
    }
	
	public String performGet(HttpServletRequest request) {
		return performPost(request);
	}
	
	public String performPost(HttpServletRequest request) {
        // If user is logged in, redirect to loggedin.do
        if (request.getSession().getAttribute("user") != null) {
            return "Loggedin.do";
        }

        try {
        	UserIdForm form = new UserIdForm(request);
        	UserBean blogger = userDAO.read(form.getEmail());
        	if (blogger == null) {
                System.out.println("Sorry, the user does not exist");
                return "Login.jsp";
            }
        	
        	HttpSession session = request.getSession();
        	session.setAttribute("blogger", blogger);

            request.setAttribute("posts", postDAO.getPosts());
            request.setAttribute("comments", commentDAO.getItems());

            return ("NotLoggedInVisitorPage.jsp");
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "Error.jsp";
        }
    }

}
