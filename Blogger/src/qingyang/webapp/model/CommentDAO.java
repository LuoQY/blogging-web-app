package qingyang.webapp.model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import qingyang.webapp.databean.CommentBean;

public class CommentDAO extends GenericDAO<CommentBean>{
	public CommentDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CommentBean.class, tableName, cp);
	}
	public void addToBottom(CommentBean item) throws RollbackException {
		try {
			Transaction.begin();

			// Get item at bottom of list
			CommentBean[] a = match(MatchArg.max("position"));

			CommentBean bottomBean;
			if (a.length == 0) {
				bottomBean = null;
			} else {
				bottomBean = a[0];
			}

			int newPos;
			if (bottomBean == null) {
				// List is empty...just add it with position = 1
				newPos = 1;
			} else {
				// New item's position is one less than the top bean's position
				newPos = bottomBean.getPosition() + 1;
			}

			item.setPosition(newPos);

			// Create a new ItemBean in the database with the next id number
			// Note that GenericDAO.create() will use auto-increment when
			// the primary key field is an int or a long.
			create(item);

			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
	
	public CommentBean[] getItems() throws RollbackException {

		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans
		CommentBean[] items = match();
		
		Arrays.sort(items, (CommentBean i1, CommentBean i2) -> i1.getPosition() - i2.getPosition());

		return items;
	}
	
	public CommentBean[] getItems(int postid) throws RollbackException {

		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans
		CommentBean[] items = match(MatchArg.equals("postId", postid));
		
		Arrays.sort(items, (CommentBean i1, CommentBean i2) -> i1.getPosition() - i2.getPosition());

		return items;
	}
	
	public CommentBean[] getItemsById(int commentid) throws RollbackException {

		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans
		CommentBean[] items = match(MatchArg.equals("id", commentid));
		
		Arrays.sort(items, (CommentBean i1, CommentBean i2) -> i1.getPosition() - i2.getPosition());

		return items;
	}
}
