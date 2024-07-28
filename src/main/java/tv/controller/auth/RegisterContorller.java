package tv.controller.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import tv.dao.UserDAO;
import tv.models.User;

/**
 * Servlet implementation class LoginContorller
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
public class RegisterContorller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterContorller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/site/register.jsp");
		request.setAttribute("title", "Register");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt());
		String username = request.getParameter("username");
		String role = request.getParameter("role");
        User user = new User(firstName, lastName, email, username, password, role);
        
        if (UserDAO.addUser(user) != 0) {
        	response.sendRedirect("home");
        }
	}

}
