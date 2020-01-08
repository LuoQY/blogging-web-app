package qingyang.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import qingyang.webapp.databean.PostBean;
import qingyang.webapp.databean.UserBean;
import qingyang.webapp.formbean.PostForm;
import qingyang.webapp.model.PostDAO;
import qingyang.webapp.model.CommentDAO;
import qingyang.webapp.model.Model;


public class Newpost extends Action {
	private PostDAO postDAO;
	private CommentDAO commentDAO;
	
	public Newpost(Model model) {
		postDAO = model.getPostDAO();
		commentDAO = model.getCommentDAO();
    }

	public String getName() {
        return "Newpost.do";
    }
	
	public String performPost(HttpServletRequest request) {
        // If user is NOT logged in, redirect to login.do
        if (request.getSession().getAttribute("user") == null) {
            return "Login.do";
        }

        try {
        	PostForm form = new PostForm(request);
            if (form.hasValidationErrors()) {
                request.setAttribute("form", form);
                request.setAttribute("posts", postDAO.getPosts());
                request.setAttribute("comments", commentDAO.getItems());
                return "HomePage.jsp";
            }

            PostBean bean = new PostBean();
            bean.setContent(form.getPost());
            bean.setTime(new java.util.Date());
            bean.setEmail(((UserBean) request.getSession().getAttribute("user")).getEmail());
            postDAO.addToTop(bean);


            request.setAttribute("form", new PostForm());
            request.setAttribute("posts", postDAO.getPosts());
            request.setAttribute("comments", commentDAO.getItems());

            return "HomePage.jsp";

        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "Error.jsp";
        }
    }
}
