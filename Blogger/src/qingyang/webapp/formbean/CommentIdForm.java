package qingyang.webapp.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("commentid")
public class CommentIdForm extends FormBean {
	private String commentid;
	
	public CommentIdForm() {
		super();
	}
	
	public CommentIdForm(HttpServletRequest request) {
    	super(request);
    }

	public int getIdAsInt() {
        return Integer.parseInt(commentid);
    }

	public String getCommentid() {
		return commentid;
	}

	@InputType("hidden")
	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	public void validate() {
		
        super.validate();

        if (hasValidationErrors()) {
            return;
        }

        try {
            Integer.parseInt(commentid);
        } catch (NumberFormatException e) {
            this.addFormError("Comment Id is not an integer");
        }
    }
	
}
