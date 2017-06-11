package cn.cgb.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cgb.store.domain.Category;
import cn.cgb.store.service.CategoryService;
import cn.cgb.store.service.serviceImp.CategoryServiceImp;
import cn.cgb.store.web.baseservlet.BaseServlet;

public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String addProUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//查询所有分类
		CategoryService CategoryService=new CategoryServiceImp();
		List<Category> list = CategoryService.findAllCats();
		//将分类放入requst
		request.setAttribute("allCats", list);
		return "/admin/product/add.jsp";
	}


}