package cn.cgb.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cgb.store.domain.Product;
import cn.cgb.store.service.ProductService;
import cn.cgb.store.service.serviceImp.ProductServiceImp;
import cn.cgb.store.web.baseservlet.BaseServlet;

public class IndexServlet extends BaseServlet {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		//查询首页中的所有分类,首页中所有的商品信息
		//调用业务层,查询所有分类返回集合
		//CategoryService CategoryService=new CategoryServiceImp();
		//List<Category> allCats=CategoryService.findAllCats();
		//将集合放入 request
		//request.setAttribute("allCats", allCats);
		//转发/jsp/index.jsp
		

		//调用Service,查询2种商品
		ProductService ProductService=new ProductServiceImp();
		List<Product> news=ProductService.findNewsProducts();
		List<Product> hots=ProductService.findHotsProducts();
		//放入request
		request.setAttribute("news", news);
		request.setAttribute("hots", hots);
		//转发到/jsp/index.jsp
		return "/jsp/index.jsp";
	}
}