package qingyang.webapp.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

public class UserIdForm extends FormBean {
	
	private String email;
    
    public UserIdForm() {
    	super();
    }
    
    public UserIdForm(HttpServletRequest request) {
    	super(request);
    }

    public String getEmail() { return email;    }
    
    @InputType("hidden")
    public void setEmail(String id) { this.email = id; }

    public void validate() {
        super.validate();

        if (hasValidationErrors()) {
            return;
        }
    }

}
