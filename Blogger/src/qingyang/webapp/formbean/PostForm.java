package qingyang.webapp.formbean;


import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.Label;

@FieldOrder("post")
public class PostForm extends FormBean {
    private String post;
    
    public PostForm() {
    	super();
    }
    
    public PostForm(HttpServletRequest request) {
    	super(request);
    }
  
    
    public String getPost() {
        return post;
    }

    @Label("New post:")
    public void setPost(String item) {
        this.post = item;
    }

	public void validate() {
        super.validate();
        
        if (hasValidationErrors()) {
            return;
        }
    }
}