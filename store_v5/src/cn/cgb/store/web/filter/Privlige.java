package cn.cgb.store.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.cgb.store.domain.User;

public class Privlige implements Filter {

    public Privlige() {
    }

	public void destroy() {
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		User user=(User)req.getSession().getAttribute("loginUser");
		if(null==user){
			
			request.setAttribute("msg", "没有权限访问,请登录后在访问");
			request.getRequestDispatcher("/jsp/info.jsp").forward(req, response);
			return;
		}
		chain.doFilter(request, response);
	}

	

}
