package cn.cgb.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cgb.store.domain.Order;
import cn.cgb.store.domain.OrderItem;
import cn.cgb.store.service.OrderService;
import cn.cgb.store.service.serviceImp.OrderServiceImp;
import cn.cgb.store.web.baseservlet.BaseServlet;
import net.sf.json.JSONArray;

public class AdminOrderServlet extends BaseServlet {

	OrderService OrderService=new OrderServiceImp();

	public String findAllOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//获取订单状态
		String state=request.getParameter("state");
		//声明一个集合,存放全部订单或者不同状态订单
		List<Order> list=null;
		if(null==state||"".equals(state)){
			//如果没有获取到state,查询全部订单,返回集合
			list=OrderService.findAllOrders();
		}else{
			//如果获取到state,查询不同状态订单,返回集合
			list=OrderService.findAllOrdersByState(state);
		}
		//将集合放入request
		request.setAttribute("orders", list);
		//转发到/admin/order/list.jsp
		return "/admin/order/list.jsp";
	}
	//findOrderByOid
	public String findOrderByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//设置本次响应内容类型为JSON格式
		response.setContentType("application/json;charset=utf-8");
		//服务端获取oid
		String oid=request.getParameter("oid");
		  //调用业务层功能:通过oid查询订单下所有的订单项以及订单项对应的商品信息,返回集合
		Order order = OrderService.findOrderByOid(oid);
		  //将订单下对应的订单项以及商品转换为JSON格式的字符串
		List<OrderItem> list = order.getList();
		String jsonStr=JSONArray.fromObject(list).toString();
		System.out.println(jsonStr);
		  //响应回客户端
		response.getWriter().print(jsonStr);
		return null;
	}
	//sendGood
	public String sendGood(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//服务端获取到oid,
		String oid = request.getParameter("oid");
		//根据订单oid更新订单的状态为3   update order set state=3 where oid=?
		//PS:此处我们应该先查询订单,设置订单状态,发送更新语句: 为了利用已经实现过的功能代码
		Order order=OrderService.findOrderByOid(oid);
		order.setState(3);
		OrderService.updateOrder(order);
		//重新定向到查询所有已经发货的订单
		response.sendRedirect(request.getContextPath()+"/AdminOrderServlet?method=findAllOrders&state=3");
		
		return null;
	}
}





