package qingyang.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import qingyang.webapp.databean.CommentBean;
import qingyang.webapp.databean.UserBean;
import qingyang.webapp.databean.PostBean;
import qingyang.webapp.formbean.CommentForm;
import qingyang.webapp.model.CommentDAO;
import qingyang.webapp.model.Model;
import qingyang.webapp.model.PostDAO;

public class Newcomment extends Action {
	private PostDAO postDAO;
	private CommentDAO commentDAO;
	
	public Newcomment(Model model) {
		postDAO = model.getPostDAO();
		commentDAO = model.getCommentDAO();
    }

	public String getName() {
        return "Newcomment.do";
    }
	
	public String performPost(HttpServletRequest request) {
        // If user is NOT logged in, redirect to login.do
        if (request.getSession().getAttribute("user") == null) {
            return "Login.do";
        }

        try {
        	CommentForm form = new CommentForm(request);
            if (form.hasValidationErrors()) {
                request.setAttribute("form", form);
                request.setAttribute("comments", commentDAO.getItems());
                request.setAttribute("posts", postDAO.getPosts());
                request.setAttribute("error", "Comment cannot be empty");
                return "LoggedInVisitorPage.jsp";
            }
            
            PostBean[] post = postDAO.getPosts(form.getIdAsInt());
            if(post.length == 0) {
            	request.setAttribute("error", "This post does not exit");
            	request.setAttribute("form", new CommentForm());
                request.setAttribute("comments", commentDAO.getItems());
                request.setAttribute("posts", postDAO.getPosts());
                return "LoggedInVisitorPage.jsp";
            }

            CommentBean bean = new CommentBean();
            bean.setContent(form.getComment());
            bean.setPostId(form.getIdAsInt());
            bean.setTime(new java.util.Date());
            bean.setFirstName(((UserBean) request.getSession().getAttribute("user")).getFirstName());
            bean.setLastName(((UserBean) request.getSession().getAttribute("user")).getLastName());
            bean.setEmail(((UserBean) request.getSession().getAttribute("user")).getEmail());
            commentDAO.addToBottom(bean);


            request.setAttribute("form", new CommentForm());
            request.setAttribute("comments", commentDAO.getItems());
            request.setAttribute("posts", postDAO.getPosts());

            return "LoggedInVisitorPage.jsp";

        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "Error.jsp";
        }
    }
}
