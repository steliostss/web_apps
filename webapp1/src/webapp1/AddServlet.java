package webapp1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		res.setContentType("text/html");

		PrintWriter out = res.getWriter();
		
		String Fname = req.getParameter("Fname");
		String Lname = req.getParameter("Lname");
		
		Cookie cookie_1 = new Cookie("Fname", Fname);
		Cookie cookie_2 = new Cookie("Lname", Lname);
		
		res.addCookie(cookie_1); 
		res.addCookie(cookie_2);
		
		//second hmtl page
		out.println("Hello, " + Fname + " " + Lname);
		out.print("<form action='choose' method='post'>");
		out.print("Would you like to order takeaway or delivery?<br>");
		out.print("<input type='radio' name='choice' value='takeaway'>I want to order takeaway<br>");
		out.print("<input type='radio' name='choice' value='delivery'>I want to order delivery<br>");
		out.print("<input type='submit' name='Go'>");
		out.close();
		
	}

}
