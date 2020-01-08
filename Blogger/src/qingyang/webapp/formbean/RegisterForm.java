package qingyang.webapp.formbean;


import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.Label;

@FieldOrder("firstName, lastName, email, password, confirmPassword")
public class RegisterForm extends FormBean{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;

    public RegisterForm() {
    	super();
    }
    
    public RegisterForm(HttpServletRequest request) {
    	super(request);
    }
   
    @InputType("firstname")
    public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}

    @InputType("lastname")
	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
	}

	@InputType("inputEmail")
	public void setEmail(String email) {
		this.email = email.trim();
	}

	@InputType("inputPassword")
	public void setPassword(String password) {
		this.password = password.trim();
	}

	@InputType("password")
    @Label("Confirm Password:")
	public void setConfirmPassword(String confirmpassword) {
		this.confirmPassword = confirmpassword.trim();
	}

	public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getConfirmPassword() {
    	return confirmPassword;
    }

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
        
        if (!password.equals(confirmPassword)) {
            this.addFieldError("password", "Passwords do not match");
            this.addFieldError("confirmPassword",  "Passwords do not match");
        }
    }
}

