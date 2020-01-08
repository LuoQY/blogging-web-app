package qingyang.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import qingyang.webapp.model.Model;
import qingyang.webapp.formbean.LoginForm;


public class Logout extends Action {
	
	public Logout(Model model) {
    }
    
    public String getName() {
        return "Logout.do";
    }

    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);

        request.setAttribute("form", new LoginForm());
        return "Login.jsp";
    }
	
}