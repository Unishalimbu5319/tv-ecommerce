package tv.controller.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tv.dao.ProductDAO;
import tv.models.Product;

/**
 * Servlet implementation class FilterController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/filter" })
public class FilterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		double minPrice = Double.parseDouble(request.getParameter("minPrice"));
		double maxPrice = Double.parseDouble(request.getParameter("maxPrice"));
		
		List<Product> productList = ProductDAO.getAllProducts();
		
		if (name.equals("") && minPrice == 0 && maxPrice==0) {
			sendFilteredProductsResponse(productList, response);			
		}

		List<Product> filteredProducts = filterProducts(productList, name, minPrice, maxPrice);
		sendFilteredProductsResponse(filteredProducts, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private List<Product> filterProducts(List<Product> productList, String name, double minPrice, double maxPrice) {
        List<Product> filteredProducts = new ArrayList<>();
        
        if (minPrice == 0 && maxPrice == 0 && !name.isEmpty()) {
        	for (Product product : productList) {
                if ((name == null || name.isEmpty() || product.getName().contains(name))) {
                    filteredProducts.add(product);
                }
            }
        	return filteredProducts;
        }
        
        for (Product product : productList) {
            if ((name == null || name.isEmpty() || product.getName().contains(name))
                    && product.getPrice() >= minPrice
                    && product.getPrice() <= maxPrice) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
	
	private void sendFilteredProductsResponse(List<Product> filteredProducts, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        for (Product product : filteredProducts) {
            out.println("<div class=\"col mb-5\">");
            out.println("<div class=\"card h-100\">");
            out.println("<div class=\"badge bg-dark text-white position-absolute\" style=\"top: 0.5rem; right: 0.5rem\">Sale</div>");
            out.println("<img class=\"card-img-top\" src=\"ImageRetrieve?fileName=" + product.getImage() + "\" alt=\"" + product.getName() + "\">");
            out.println("<div class=\"card-body p-4\">");
            out.println("<div class=\"text-center\">");
            out.println("<h5 class=\"fw-bolder\">" + product.getName() + "</h5>");
            out.println("<div class=\"d-flex justify-content-center small text-warning mb-2\">");
            out.println("<div class=\"bi-star-fill\"></div>");
            out.println("<div class=\"bi-star-fill\"></div>");
            out.println("<div class=\"bi-star-fill\"></div>");
            out.println("<div class=\"bi-star-fill\"></div>");
            out.println("<div class=\"bi-star-fill\"></div>");
            out.println("</div>");
            out.println("<span class=\"\">Rs. " + product.getPrice() + "</span>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent\">");
            out.println("<div class=\"text-center\">");
            out.println("<a class=\"btn btn-outline-dark mt-auto\" href=\"#\">Add to cart</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
        }
    }

}
