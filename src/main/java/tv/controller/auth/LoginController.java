package tv.controller.auth;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import tv.dao.UserDAO;
import tv.models.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/site/login.jsp");
		request.setAttribute("title", "Login");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = UserDAO.getUserByEmail(email);
    	if (user == null) {
    		request.setAttribute("error", "Account does not exist. Please create a new account.");
    		request.getRequestDispatcher("/WEB-INF/views/site/login.jsp").forward(request, response); 
    	}
    	
    	String hashedPassword = user.getPassword();
    	if (BCrypt.checkpw(password, hashedPassword)) {
    		HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setMaxInactiveInterval(30 * 60);

            Cookie cookie = new Cookie("email", URLEncoder.encode(email, "UTF-8"));
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(30 * 60);
            response.addCookie(cookie);
            
    		if (user.getRole().equals("Staff")) {
    			session.setAttribute("isLoggedIn", true);
//    			request.getRequestDispatcher("/WEB-INF/views/admin/listProducts.jsp").forward(request, response); 
    			response.sendRedirect("products");
    		}else if (user.getRole().equals("Regular")) {
    			session.setAttribute("isLoggedIn", true);
    			response.sendRedirect("home");
    		}
    	}else {
    		request.setAttribute("error", "Incorrect email or password. Please Try Again!!.");
    		request.getRequestDispatcher("/WEB-INF/views/site/login.jsp").forward(request, response);    		
    	}
	}

}
