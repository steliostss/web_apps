pacookiesage webapp1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FinalServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		Cookie cookies[] = req.getCookies();
		
		out.print("Perfect, " + cookies[0].getValue() + " " + cookies[1].getValue() + ".");
		out.print("<br><br>");
		out.print("You ordered " + cookies[2].getValue() + ".");
		out.print("<br><br>");
		out.print("Your " + cookies[3].getValue() + " will be arriving immediately at " +cookies[1].getValue());
		if (cookies[2].getValue().equals("delivery")) {
			out.print("Your food will be delivered to: " + cookies[4].getValue() + ".");
			out.print("<br><br>");
		}
		else { 
			out.print("Your have to take your food from: Vassilisis Sofias 15 at 30'.");
			out.print("<br><br>");
		}
		out.close();
	}

}
