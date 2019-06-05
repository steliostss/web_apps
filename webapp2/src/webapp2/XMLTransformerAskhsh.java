package webapp2;

import java.io.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.*;
// for servlet:
import javax.servlet.*;
import javax.servlet.http.*;

import org.w3c.dom.*;  // for DOM (Java DOM)
import javax.xml.parsers.*;  

import javax.xml.transform.dom.*;// for transformations

public class XMLTransformerAskhsh extends HttpServlet {
	ServletContext ctx;
	String absPath;          //absolute path to our files - see below
	SAXSource xsltDoc; TransformerFactory tF;
	Transformer myTransformer;// will be built at init, will be used by doGet
	Document doc;

	public void init(ServletConfig config) throws UnavailableException {
		System.out.println("Init start");
		try {
			ctx = config.getServletContext();   // we will use the 'contex' below
			absPath = ctx.getRealPath("/WEB-INF/CarPresentor2.xsl");
			xsltDoc = new SAXSource(new InputSource(absPath));
			tF = TransformerFactory.newInstance();
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			// absolutely important, to understand the meaning of prefix 'xslt' !!!!
			fact.setNamespaceAware(true);
			DocumentBuilder builder = fact.newDocumentBuilder();
			doc = builder.parse(absPath);
			System.out.println("Name of document element is " +  doc.getDocumentElement().getNodeName()); 
		      } catch (Exception e) {	e.printStackTrace(); }
		System.out.println("Init end");
	}
	private void changeDomByColor(Document doc, String color) {
		NodeList nl = doc.getElementsByTagName("h1");
		Attr a = doc.createAttribute("style");
		a.setValue("background-color: "+color);
		nl.item(0).getAttributes().setNamedItem(a);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Item = request.getParameter("item");
		String Color = request.getParameter("color");
		
		changeDomByColor(doc, Color);
		
		PrintWriter pwr = response.getWriter();
		DOMSource ds = new DOMSource(doc);
		System.out.println( ((Document)ds.getNode()).getDocumentElement().getNodeName() +" "+((Document)ds.getNode()).getDocumentElement().getNodeValue());
		try {
			myTransformer = tF.newTransformer(ds);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StreamSource xmlSource;
		xmlSource = null;
		if(Item.equals("cars")) xmlSource = new StreamSource(ctx.getResourceAsStream("/WEB-INF/Cars.xml"));
		else if(Item.equals("boats")) xmlSource = new StreamSource(ctx.getResourceAsStream("/WEB-INF/Boats.xml"));
		else if(Item.equals("mobiles")) xmlSource = new StreamSource(ctx.getResourceAsStream("/WEB-INF/Mobiles.xml"));
		System.out.println("Sending xml transformed into html");
		response.setContentType("text/html");
		try {
			myTransformer.transform(xmlSource, new StreamResult(pwr));
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pwr.println("The response sent back as a page!");
		pwr.close();
	}
	
}
