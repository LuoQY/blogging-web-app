package qingyang.webapp.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("postid")
public class PostIdForm extends FormBean {
	private String postid;
    
    public PostIdForm() {
    	super();
    }
    
    public PostIdForm(HttpServletRequest request) {
    	super(request);
    }
    
    public int getIdAsInt() {
        return Integer.parseInt(postid);
    }

    public String getPostid() {
		return postid;
	}
    @InputType("hidden")
	public void setPostid(String postid) {
		this.postid = postid;
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
