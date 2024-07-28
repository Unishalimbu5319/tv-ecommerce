package tv.controller.site;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tv.dao.ContactDAO;
import tv.models.ContactMessage;

/**
 * Servlet implementation class AboutController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/about" })
public class AboutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AboutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/site/about.jsp");
		request.setAttribute("title", "About Us");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		
		ContactMessage contact = new ContactMessage(email, message);
		if (ContactDAO.addContact(contact) != 0) {
			response.sendRedirect("about");
			return;
		}
		
		response.sendRedirect("about");
	}

}
