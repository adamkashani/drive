package core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class FilterGuard implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		Cookie cookieToken = getCookieByName(request, "token");
		// get string directions url httpServletRequest
		String pathRequest = request.getRequestURI().toString();
		// if client not yet logged in and the path ends with /login or /registration
		// and if the string and with /coupon/getall does not matter the cookie id value
		// always access
		if (cookieToken == null && pathRequest.endsWith("/login") || pathRequest.endsWith("/registration")) {
			chain.doFilter(request, response);
			// return when the request back to this filter after layer api
			return;
		}
		if (cookieToken != null && !(pathRequest.endsWith("/login")) && !(pathRequest.endsWith("/registration"))) {
			chain.doFilter(req, res);
			// checking permission (in the next upgrade)
			// return when the request back to this filter after layer api
			return;
		} else if (cookieToken != null && pathRequest.endsWith("/login") || pathRequest.endsWith("/registration")) {
			response.setStatus(420);
			response.setContentType("text/plain");
			response.getWriter().println("you already login in the system");
			// return when the request back to this filter after layer api
			return;
		}
		System.out.println("from FilterGuard the user not login try go to path : "+pathRequest);
		response.setStatus(420);
		response.setContentType("text/plain");
		response.getWriter().println("you must first login ,You are not registered in the system");

	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}

	private Cookie getCookieByName(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

}
