package tv.controller.site;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tv.dao.OrderDAO;
import tv.models.Order;
import tv.models.User;
import tv.utils.AuthUtil;
import java.sql.Date;

/**
 * Servlet implementation class CheckoutController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/checkout" })
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = AuthUtil.checkLogin(request, response);
		if (user == null) {
			response.sendRedirect("login");
			return;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/site/checkout.jsp");
		request.setAttribute("user", user);
		request.setAttribute("title", "Checkout");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String paymentMethod = request.getParameter("paymentMethod");
		String ccName = request.getParameter("cc-name");
		String ccExpiration = request.getParameter("cc-expiration");
		String ccNumber = request.getParameter("cc-number");
		String ccCVV = request.getParameter("cc-cvv");
		double total = Double.parseDouble(request.getParameter("total"));
		
		System.out.println(total);
		
		String shippingInfo = "address: " + address + ",country : " + country + ",state: " + state + ",zip: " + zip;
		String orderStatus = "Delivering";
		
		Date orderDate = new Date(System.currentTimeMillis());

		Order order = new Order(email, orderDate, shippingInfo, orderStatus, paymentMethod, ccName, ccExpiration, ccNumber, ccCVV, total);
		
		OrderDAO.CreateOrder(order);
		
		response.sendRedirect("success");
	}

}
