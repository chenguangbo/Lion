package cn.cgb.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cgb.store.domain.Category;
import cn.cgb.store.service.CategoryService;
import cn.cgb.store.service.serviceImp.CategoryServiceImp;
import cn.cgb.store.utils.JedisUtils;
import cn.cgb.store.web.baseservlet.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

public class CategoryServlet extends BaseServlet {

	CategoryService CategoryService=new CategoryServiceImp();
	
	/*
        //获取所有分类版本2的实现代码
 		//设置本次响应内容类型和编码
		response.setContentType("application/json;charset=utf-8");
		//调用业务层,查询所有分类返回集合
		CategoryService CategoryService=new CategoryServiceImp();
		List<Category> list = CategoryService.findAllCats();
		// 将集合转换为JSON格式的数据
		String jsonStr = JSONArray.fromObject(list).toString();
		// 响应到客户端
		response.getWriter().print(jsonStr);*/
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//设置本次响应内容类型和编码
		response.setContentType("application/json;charset=utf-8");
		Jedis jedis = JedisUtils.getJedis();
		//*_缓存中获取JSON格式的字符串
		String jsonStr = jedis.get("allCats");
		if(null==jsonStr||"".equals(jsonStr)){
			System.out.println("缓存中没有数据");
			//*_获取不到,调用业务层,查询所有分类返回集合将集合转换为JSON格式的数据
			List<Category> list = CategoryService.findAllCats();
			// 将集合转换为JSON格式的数据
			jsonStr = JSONArray.fromObject(list).toString();
			//将JSON格式的数据保存在缓存中
			jedis.set("allCats", jsonStr);
		}else{
			System.out.println("缓存中有数据");
		}
		JedisUtils.closeJedis(jedis);
		//*_可以获取到,直接响应即可
		response.getWriter().print(jsonStr);
		
		return null;
	}

}