package qingyang.webapp.formbean;



import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.FieldOrder;

@FieldOrder("email,password")
public class LoginForm extends FormBean {
    private String email;
    private String password;
    
    public LoginForm() {
    	super();
    }
    
    public LoginForm(HttpServletRequest request) {
    	super(request);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setEmail(String s)  { email = s.trim(); }
    
    @InputType("password")
    public void setPassword(String s)  { password = s.trim(); }
    
    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
        
        // No validation errors from super class, so all required fields are present
        
        if (email.matches(".*[<>\"].*")) {
            this.addFieldError("email", "May not contain angle brackets or quotes");
        }
        
        if (!email.matches(".*[@].*"))
        	this.addFieldError("email", "Wrong format");
        
    }

}

