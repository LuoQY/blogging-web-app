package qingyang.webapp.controller;

import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import qingyang.webapp.model.PostDAO;
import qingyang.webapp.model.Model;
import qingyang.webapp.model.CommentDAO;
import qingyang.webapp.databean.UserBean;
import qingyang.webapp.formbean.CommentForm;
import qingyang.webapp.formbean.UserIdForm;
import qingyang.webapp.model.UserDAO;


public class Loggedin extends Action {
	private PostDAO postDAO;
	private CommentDAO commentDAO;
	private UserDAO userDAO;
	
	public Loggedin(Model model) {
    	postDAO = model.getPostDAO();
    	commentDAO = model.getCommentDAO();
    	userDAO = model.getUserDAO();
    }
	
	public String getName() {
        return "Loggedin.do";
    }
	
	public String performPost(HttpServletRequest request) {
        // If user is NOT logged in, redirect to login.do
        if (request.getSession().getAttribute("user") == null) {
            return "Notloggin.do";
        }

        try {
        	UserIdForm form = new UserIdForm(request);
        	UserBean blogger = userDAO.read(form.getEmail());
        	if (blogger == null) {
                System.out.println("Sorry, the user does not exist");
                return "HomePage.jsp";
            }
        	
        	HttpSession session = request.getSession();
        	session.setAttribute("blogger", blogger);

            request.setAttribute("posts", postDAO.getPosts());
            request.setAttribute("comments", commentDAO.getItems());
            request.setAttribute("form",  new CommentForm());

            return ("LoggedInVisitorPage.jsp");
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "Error.jsp";
        }
    }

}
