package qingyang.webapp.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private PostDAO postDAO;
	private CommentDAO commentDAO;
	private UserDAO userDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
			
			userDAO  = new UserDAO(pool, "user");
			postDAO = new PostDAO(pool, "post");
			commentDAO = new CommentDAO(pool, "comment");
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public PostDAO getPostDAO()  { return postDAO; }
	public CommentDAO getCommentDAO() { return commentDAO; }
	public UserDAO getUserDAO()  { return userDAO; }
}
