package tv.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tv.dao.ProductDAO;
import tv.models.User;
import tv.utils.AuthUtil;

/**
 * Servlet implementation class DeleteProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/delete" })
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = AuthUtil.checkLogin(request, response);

		if (user != null && user.getRole().equals("Staff")) {
			request.setAttribute("user", user.getUsername());
		} else {
			response.sendRedirect("login");
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));
		ProductDAO.deleteProduct(id);
		response.sendRedirect("products");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
