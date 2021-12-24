package backend.testingonline.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import backend.testingonline.model.Staff;

public class LoginFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		if (staff == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		} else {
			System.out.println("thanh cong!");
		}
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
