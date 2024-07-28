package tv.controller.site;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tv.dao.UserDAO;
import tv.models.User;

/**
 * Servlet implementation class Profile
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
        User user = null;
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
            	if (cookie.getName().equals("email")) {
                    String email = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    System.out.println(email);
                    user = UserDAO.getUserByEmail(email);
                    break;
                }
            }
        }
        
        if (user == null) {
        	response.sendRedirect("login");
        	return;
        }
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/site/profile.jsp");
		request.setAttribute("title", "Profile");
		request.setAttribute("user", user);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		User user = UserDAO.getUserByEmail(email);
		user.setUsername(username);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		
		UserDAO.updateUser(user);
		
		response.sendRedirect("profile");
	}

}
