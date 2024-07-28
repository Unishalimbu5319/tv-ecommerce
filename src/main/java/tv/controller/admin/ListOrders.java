package tv.controller.admin;

import java.io.IOException;
import java.util.List;

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

/**
 * Servlet implementation class ListOrders
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/orders" })
public class ListOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = AuthUtil.checkLogin(request, response);
        
        if (user != null && user.getRole().equals("Staff")) {
        	request.setAttribute("user", user.getUsername());        	
        }else {
        	response.sendRedirect("login");
        	return;
        }
		List<Order> orders = OrderDAO.getAllOrders();
		request.setAttribute("pageTitle", "List Orders");
		request.setAttribute("orders", orders);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/listOrders.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
