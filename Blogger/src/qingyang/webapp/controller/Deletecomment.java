package qingyang.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import qingyang.webapp.formbean.CommentIdForm;
import qingyang.webapp.formbean.PostForm;
import qingyang.webapp.formbean.CommentForm;
import qingyang.webapp.databean.CommentBean;
import qingyang.webapp.databean.UserBean;
import qingyang.webapp.model.PostDAO;
import qingyang.webapp.model.CommentDAO;
import qingyang.webapp.model.Model;

public class Deletecomment extends Action {

    private CommentDAO commentDAO;
    private PostDAO postDAO;

    public Deletecomment(Model model) {
    	commentDAO = model.getCommentDAO();
    	postDAO = model.getPostDAO();
    }

    public String getName() {
        return "Deletecomment.do";
    }

    public String performPost(HttpServletRequest request) {
        // If user is NOT logged in, redirect to login.do
        if (request.getSession().getAttribute("user") == null) {
            return "Login.do";
        }

        try {
            CommentIdForm form = new CommentIdForm(request);

            if (form.hasValidationErrors()) {
            	request.setAttribute("form", form);
                return "Error.jsp";
            }

            CommentBean[] comment = commentDAO.getItemsById(form.getIdAsInt());
            if(comment.length<1)
            {
            	request.setAttribute("error", "the comment doesn't exist");
            	return "Error.jsp";
            }
            else if(comment[0].getEmail().equals(((UserBean)request.getSession().getAttribute("user")).getEmail()))
        	{
        		commentDAO.delete(form.getIdAsInt());
        		request.setAttribute("posts", postDAO.getPosts());
                request.setAttribute("comments", commentDAO.getItems());
        		request.setAttribute("form", new CommentForm());
        		return "LoggedInVisitorPage.jsp";
        	}
            else if((postDAO.getPosts(comment[0].getPostId()))[0].getEmail().equals(((UserBean)request.getSession().getAttribute("user")).getEmail()))
            {
            	commentDAO.delete(form.getIdAsInt());
            	request.setAttribute("posts", postDAO.getPosts());
                request.setAttribute("comments", commentDAO.getItems());
            	request.setAttribute("form", new PostForm());
            	return "HomePage.jsp";
            }
            else {
        		request.setAttribute("error", "Sorry, you aren't allowed to delete the comment");
        		return "Error.jsp";
            }
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "Error.jsp";
        }
    }
}
