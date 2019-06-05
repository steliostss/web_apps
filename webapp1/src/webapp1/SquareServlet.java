package webapp1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SquareServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		res.setContentType("text/html");

		PrintWriter out = res.getWriter();
		
		Cookie cookies[] = req.getCookies();
		String choice = req.getParameter("choice");
		Cookie cookie_3 = new Cookie("choice", choice);
		
		res.addCookie(cookie_3);
		
		//second html page (delivery/takeaway)
		out.print("<form action='dreat' method='post'>");

			out.println("What do you want to eat?...<br>");
				out.print("<input type='radio' name='choice2' value='burger'>Big Mac<br>");
				out.print("<input type='radio' name='choice2' value='pizza'>Pizza<br>");
				out.print("<input type='radio' name='choice2' value='spagghetti'>Bolognese<br>");
				out.print("<input type='submit' value='Go!'>");   

				out.println("Type your address: <input type='text' name='Address'>");
				out.println("The address of the shop is **Vassilisis Sofias 15** ");

				Cookie cookie_4 = new Cookie("Choice2", choice2);
				Cookie cookie_5 = new Cookie("Address", Address);

				res.addCookie(cookie_4);
				res.addCookie(cookie_5);

		out.print("</form>"); 

		out.close();	
	
	}

}
