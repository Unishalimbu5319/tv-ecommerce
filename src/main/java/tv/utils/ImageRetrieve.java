package tv.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageRetrieve
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ImageRetrieve" })
public class ImageRetrieve extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageRetrieve() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter("fileName");
		if (fileName != null) {
			String filePath = getServletContext().getRealPath("/WEB-INF/uploads/" + fileName);
			File file = new File(filePath);
			if (file.exists()) {

				response.setContentType("application/octet-stream");
				response.setContentLength((int) file.length());

				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = fis.read(buffer)) != -1) {
					response.getOutputStream().write(buffer, 0, bytesRead);
				}
				fis.close();
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File name parameter is missing");
		}
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