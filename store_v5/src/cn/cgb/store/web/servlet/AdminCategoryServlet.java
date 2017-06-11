package cn.cgb.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cgb.store.domain.Category;
import cn.cgb.store.service.CategoryService;
import cn.cgb.store.service.serviceImp.CategoryServiceImp;
import cn.cgb.store.utils.JedisUtils;
import cn.cgb.store.utils.UUIDUtils;
import cn.cgb.store.web.baseservlet.BaseServlet;
import redis.clients.jedis.Jedis;

public class AdminCategoryServlet extends BaseServlet {

	CategoryService CategoryService=new CategoryServiceImp();
	
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取所有分类信息
		List<Category> allCats = CategoryService.findAllCats();
		//将类别放入request域对象
		request.setAttribute("allCats", allCats);
		return "/admin/category/list.jsp";
	}
	//addCatUI
	public String addCatUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/admin/category/add.jsp";
	}
	
	//addCat
	public String addCat(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cname=request.getParameter("cname");
		//创建分类对象
		Category Category=new Category();
		Category.setCid(UUIDUtils.getId());
		Category.setCname(cname);
		//调用业务层添加分类功能
		CategoryService.addCat(Category);
		//更新redis缓存数据
		Jedis j=JedisUtils.getJedis();
		j.del("allCats");
		JedisUtils.closeJedis(j);
		//重新定向到查询全部分类页面
		response.sendRedirect(request.getContextPath()+"/AdminCategoryServlet?method=findAllCats");
		return null;
	}

}