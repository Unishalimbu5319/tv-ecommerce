package tv.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tv.dao.UserDAO;
import tv.models.User;

public class AuthUtil {
    public static User checkLogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
    	Cookie[] cookies = request.getCookies();
        User user = null;
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
            	if (cookie.getName().equals("email")) {
                    String email = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    user = UserDAO.getUserByEmail(email);
                    break;
                }
            }
        }
        
        if (user == null) {
        	return null;
        }
        return user;
    }
}
