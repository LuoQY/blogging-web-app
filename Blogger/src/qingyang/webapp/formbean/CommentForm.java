package qingyang.webapp.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.Label;


@FieldOrder("comment, postid")
public class CommentForm extends FormBean{
	private String comment;
	private String postid;
    
    public CommentForm() {
    	super();
    }
    
    public CommentForm(HttpServletRequest request) {
    	super(request);
    }
  
    
    public String getComment() {
        return comment;
    }

    public void setComment(String item) {
        this.comment = item;
    }

	public String getPostid() {
		return postid;
	}
	
	@InputType("hidden")
	public void setPostid(String postid) {
		this.postid = postid;
	}
	
	 public int getIdAsInt() {
        return Integer.parseInt(postid);
    }

	public void validate() {
        super.validate();
        
        if (hasValidationErrors()) {
            return;
        }
        
        try {
            Integer.parseInt(postid);
        } catch (NumberFormatException e) {
            this.addFormError("Post Id is not an integer");
        }
    }
}
