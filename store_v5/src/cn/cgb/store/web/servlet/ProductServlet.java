package cn.cgb.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cgb.store.domain.PageBean;
import cn.cgb.store.domain.Product;
import cn.cgb.store.service.ProductService;
import cn.cgb.store.service.serviceImp.ProductServiceImp;
import cn.cgb.store.web.baseservlet.BaseServlet;

public class ProductServlet extends BaseServlet {


	private static final long serialVersionUID = 1L;

	ProductService ProductService=new ProductServiceImp();
	
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接受商品id
		String pid=request.getParameter("pid");
		//调用业务层根据pid查询商品功能
		Product product=ProductService.findProductByPid(pid);
		//将返回的商品放入request对象
		request.setAttribute("pro", product);
		//转发到商品详情页面
		return "jsp/product_info.jsp";
	}
	//findProductsByCidWithPage
	public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接受cid
		String cid=request.getParameter("cid");
		// 接受当前页pageNumber
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		//调用业务层功能获取当前页信息以及分页参数返回PageBean对象
		PageBean pageBean=ProductService.findProductsByCidWithPage(cid,pageNumber);
		//将pageBean对象放入request
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		
		//转发到/jsp/product_list.jsp
		return "/jsp/product_list.jsp";
	}

}