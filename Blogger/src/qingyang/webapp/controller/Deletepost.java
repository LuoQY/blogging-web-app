package qingyang.webapp.controller;
import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import qingyang.webapp.formbean.PostIdForm;
import qingyang.webapp.formbean.PostForm;
import qingyang.webapp.databean.CommentBean;
import qingyang.webapp.databean.PostBean;
import qingyang.webapp.databean.UserBean;
import qingyang.webapp.model.PostDAO;
import qingyang.webapp.model.CommentDAO;
import qingyang.webapp.model.Model;

public class Deletepost extends Action {
    private PostDAO postDAO;
    private CommentDAO commentDAO;

    public Deletepost(Model model) {
    	postDAO = model.getPostDAO();
    	commentDAO = model.getCommentDAO();
    }

    public String getName() {
        return "Deletepost.do";
    }

    public String performPost(HttpServletRequest request) {
        // If user is NOT logged in, redirect to login.do
        if (request.getSession().getAttribute("user") == null) {
            return "Login.do";
        }

        try {
        	
            PostIdForm form = new PostIdForm(request);

            if (form.hasValidationErrors()) {
            	request.setAttribute("form", form);
                return "Error.jsp";
            }
            
            PostBean[] post = postDAO.getPosts(form.getIdAsInt());
            if(post.length<1)
            {
            	request.setAttribute("error", "the post doesn't exist");
            	return "Error.jsp";
            }
            if(!post[0].getEmail().equals(((UserBean)request.getSession().getAttribute("user")).getEmail()))
            {
            	request.setAttribute("error", "You aren't allowed to delete this post");
            	return "Error.jsp";
            }
        	Transaction.begin();
            postDAO.delete(form.getIdAsInt());
            CommentBean[] comments = commentDAO.getItems(form.getIdAsInt());
            for(CommentBean c: comments)
            		commentDAO.delete(c.getId());
            Transaction.commit();

            request.setAttribute("posts", postDAO.getPosts());
            request.setAttribute("comments", commentDAO.getItems());
            request.setAttribute("form", new PostForm());
            return "HomePage.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "Error.jsp";
        }finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
    }
}