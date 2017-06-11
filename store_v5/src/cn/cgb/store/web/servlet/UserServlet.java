package cn.cgb.store.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cgb.store.domain.User;
import cn.cgb.store.service.UserService;
import cn.cgb.store.service.serviceImp.UserServiceImp;
import cn.cgb.store.utils.MyBeanUtils;
import cn.cgb.store.utils.UUIDUtils;
import cn.cgb.store.web.baseservlet.BaseServlet;

public class UserServlet extends BaseServlet {

	private static final String String = null;
	private UserService UserService = new UserServiceImp();

	// registUI
	public String registUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return "/jsp/register.jsp";
	}

	// userRegist
	public String userRegist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 接收验证码
		String yzm = request.getParameter("yzm");
		String rand = (String) request.getSession().getAttribute("rand");
		if (!rand.equals(yzm)) {
			request.setAttribute("msg", "验证码不正确，请重新输入！");
			return "/jsp/register.jsp";
		}

		// 接受用户表单数据,封装为javaBean对象
		User user = MyBeanUtils.populate(User.class, request.getParameterMap());
		// 为用户的其他属性赋值 uid,state,code
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getId());
		System.out.println(user);
		// 调用业务层保存用户功能
		UserService.saveUser(user);
		// 向request放入提示信息:用户注册成功,请激活
		request.setAttribute("msg", "用户注册成功,请激活");
		// 转发到提示页面
		return "/jsp/info.jsp";
	}

	// active
	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 接受激活码
			String code = request.getParameter("code");
			// 调用业务层方法进行激活
			UserService.active(code);
			// 激活成功到/jsp/login.jsp进行提示
			request.setAttribute("msg", "用户激活成功,请登录!");
			return "/jsp/login.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			// throw new RuntimeException("用户激活过程失败,程序错误");
			// 激活失败到/jsp/info.jsp进行提示
			request.setAttribute("msg", e.getMessage());
			return "/jsp/info.jsp";
		}
	}

	// loginUI
	public String loginUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsp/login.jsp";
	}

	// userLogin
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 接收验证码
		String yzm = request.getParameter("yzm");
		String rand = (String) request.getSession().getAttribute("rand");
		if (!rand.equals(yzm)) {
			request.setAttribute("msg", "验证码不正确，请重新输入！");
			return "/jsp/login.jsp";
		}
		// 接受用户名
		User user = MyBeanUtils.populate(User.class, request.getParameterMap());
		// 调用业务层登录功能
		User uu = UserService.userLogin(user);
		if (null != uu) {
			// 登录成功,向session放入用户信息,重新定向到首页
			request.getSession().setAttribute("loginUser", uu);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return null;
		} else {
			// 登录失败,向request放入提示信息,转发到登录页面
			request.setAttribute("msg", "账户和密码不匹配");
			return "/jsp/login.jsp";
		}

	}

	// loginOut
	public String loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 清空session
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return null;

	}
}