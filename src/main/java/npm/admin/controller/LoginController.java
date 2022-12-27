package npm.admin.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import npm.dashboard.service.NodeDetailsService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	NodeDetailsService service;

	@RequestMapping(value = { "/loginuser" }, method = RequestMethod.POST)
	public void checkCredentials(HttpServletRequest req, HttpServletResponse res) {
		String controller_response = "";
		PrintWriter out = null;
		try {

			out = res.getWriter();
		
//			out.print("error");
	
		System.out.println("Controller Called loginuser");

//		String email=req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//System.out.println("login password = "+password);
//	
//		
//		service.getDBVerification(username, password);
		String dao_output = service.getDBVerification(username, password);
		//System.out.println("From Service_IMPL_output response====" + dao_output);
		//System.out.println("password" + password);
		// String dao_output = login_dao.getDBVerification(username, password);
		//System.out.println("From Service_IMPL_output response " + dao_output);
		String[] arrOfStr = dao_output.split("~");
		//System.out.println("Processed Password: " + arrOfStr[0]);
		//System.out.println("Username: " + arrOfStr[1]);
		
		if (username.equals(arrOfStr[1])) {
			System.out.println("Username matched");
			if (password.equals(arrOfStr[0])) {
				System.out.println("Credentials Matched !!!!!!");
				controller_response = "success";
			} 
			else {
				System.out.println("Password Not Matchedd");
				controller_response = "error";
			}

		} 
		else {
			System.out.println("Username Not matched");
			controller_response = "userntmtch";
		}
		System.out.println("login controller final response:"+controller_response);
		out.print(controller_response);
		} catch (Exception e) {
			System.out.println("Exceptionn:"+e);
		} finally {
			
			out.close();
		}
		System.out.println("#############################");
		
	//	return "landingPage";
	//	return null;
	}

	@RequestMapping(value = { "/Home" }, method = RequestMethod.GET)
	public String viewDLP(ModelMap model) {

	//	return "landingPage";
		return "summary";
	}

}
