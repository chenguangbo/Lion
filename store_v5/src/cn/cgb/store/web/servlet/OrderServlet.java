package cn.cgb.store.web.servlet;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cgb.store.domain.Cart;
import cn.cgb.store.domain.CartItem;
import cn.cgb.store.domain.Order;
import cn.cgb.store.domain.OrderItem;
import cn.cgb.store.domain.PageBean;
import cn.cgb.store.domain.User;
import cn.cgb.store.service.OrderService;
import cn.cgb.store.service.serviceImp.OrderServiceImp;
import cn.cgb.store.utils.PaymentUtil;
import cn.cgb.store.utils.UUIDUtils;
import cn.cgb.store.web.baseservlet.BaseServlet;

public class OrderServlet extends  BaseServlet {
	private static final long serialVersionUID = 1L;

	OrderService OrderService=new OrderServiceImp();
	
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//保证用户登录状态,如果用户是登录状态,获取用户
		//保证用户登录状态,如果用户不是登录状态,转到提示页面:登录后在提交表单
		User user=(User)request.getSession().getAttribute("loginUser");
		if(null==user){
			request.setAttribute("msg", "请登录后提交");
			return "/jsp/info.jsp";
		}

		//创建订单对象
		Order order=new Order();
		//为订单对象的部分属性赋值 oid ordertime state
		order.setOid(UUIDUtils.getCode());
		order.setOrdertime(new Date());
		order.setState(1);
		//为订单的用户属性设置一个用户  user
		order.setUser(user);
		//从购物车上获取到总计赋值给订单对象下的总计属性 total
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		order.setTotal(cart.getTotal());
		

		// 遍历购物项的同时创建订单项
		for(CartItem ct:cart.getCartItems()){
			//为订单项的属性赋值:  
			//  订单项itemid属性通过程序赋值,
			//订单项的total从购物项获取到的
			//订单项的quantity从购物项的num获取到的
			//订单项的product从购物项的product获取到的
			//订单项的order就是上述创建过的订单
			OrderItem orderItem=new OrderItem();
			orderItem.setItemid(UUIDUtils.getCode());
			orderItem.setTotal(ct.getSubTotal());
			orderItem.setQuantity(ct.getNum());
			orderItem.setProduct(ct.getProduct());
			
			//此处的订单项为什么要关联订单?因为在保存订单项的同时,需要保存oid
			orderItem.setOrder(order);
			//将所有的订单项加入到订单中
			order.getList().add(orderItem);
		}
		
		//将各个订单项添加到订单下的集合中
		//调用service保存订单方法,将订单传输到service,事务的方式
		OrderService.saveOrder(order);
		
		//清空购物车
		cart.clearCart();
		//将订单放入到request
		request.setAttribute("order", order);
		//转发到订单详情页面
		return "/jsp/order_info.jsp";
	}

	//findOrderByUid
	public String findOrderByUid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取当前页,获取用户
		int curNum=Integer.parseInt(request.getParameter("pageNumber"));
		User user=(User)request.getSession().getAttribute("loginUser");
		//调用service返回PageBean对象(1_分页参数2_当前页中的数据3_共同的url)
		PageBean pageBean=OrderService.findOrderByUid(user,curNum);
		//将PageBean对象放入request
		request.setAttribute("pageBean", pageBean);
		//转发到order_list.jsp
		return "/jsp/order_list.jsp";
	}
	
	//findOrderByOid
	public String findOrderByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取到订单id,根据订单id查询订单,
		String oid=request.getParameter("oid");
		//查询当前订单下所有订单项以及订单项关联的所有商品信息
		Order order=OrderService.findOrderByOid(oid);
		//将订单放到request
		request.setAttribute("order", order);
		//转发到订单详情页面
		return "/jsp/order_info.jsp";
	}
	//
	public String payOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//服务端接受收货人姓名,电话,地址,银行信息,订单oid
		String name=request.getParameter("name");
		String telephone=request.getParameter("telephone");
		String address=request.getParameter("address");
		String pd_FrpId=request.getParameter("pd_FrpId");
		String oid=request.getParameter("oid");
		//根据oid查询订单对象,更新订单的收货人的信息
		Order order=OrderService.findOrderByOid(oid);
		order.setAddress(address);
		order.setName(name);
		order.setTelephone(telephone);
		OrderService.updateOrder(order);

		//拼凑易宝支付所需参数
		// 把付款所需要的参数准备好:
		String p0_Cmd = "Buy";
		//商户编号
		String p1_MerId = "10001126856";
		//订单编号
		String p2_Order = oid;
		//金额
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		//接受响应参数的Servlet
		String p8_Url = "http://localhost:8080/store_v5/OrderServlet?method=callBack";
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		//公司的秘钥
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
			
		//调用易宝的加密算法,对所有数据进行加密,返回电子签名
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
				
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		System.out.println(sb.toString());
		//https://www.yeepay.com/app-merchant-proxy/node?p0_Cmd=Buy&p1_MerId=10001126856&p2_Order=123191616515616515616&p3_Amt=0.01&p4_Cur=CNY&p5_Pid=&p6_Pcat=&p7_Pdesc=&p8_Url=http://localhost:8080/TestPay/CallBackServlet?method=callBack&p9_SAF=&pa_MP=&pd_FrpId=CMBCHINA-NET&pr_NeedResponse=1&hmac=39d1d77760925c726d4f81923d91f588
		// 使用重定向：重定向到易宝支付网关接口	
		response.sendRedirect(sb.toString());
		
		//	
		return null;
	}
	
	///callBack
	public String callBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 验证请求来源和数据有效性
				// 阅读支付结果参数说明
				// System.out.println("==============================================");
				String p1_MerId = request.getParameter("p1_MerId");
				String r0_Cmd = request.getParameter("r0_Cmd");
				String r1_Code = request.getParameter("r1_Code");
				String r2_TrxId = request.getParameter("r2_TrxId");
				String r3_Amt = request.getParameter("r3_Amt");
				String r4_Cur = request.getParameter("r4_Cur");
				String r5_Pid = request.getParameter("r5_Pid");
				String r6_Order = request.getParameter("r6_Order");
				String r7_Uid = request.getParameter("r7_Uid");
				String r8_MP = request.getParameter("r8_MP");
				String r9_BType = request.getParameter("r9_BType");
				String rb_BankId = request.getParameter("rb_BankId");
				String ro_BankOrderId = request.getParameter("ro_BankOrderId");
				String rp_PayDate = request.getParameter("rp_PayDate");
				String rq_CardNo = request.getParameter("rq_CardNo");
				String ru_Trxtime = request.getParameter("ru_Trxtime");

				// hmac
				String hmac = request.getParameter("hmac");
				// 利用本地密钥和加密算法 加密数据
				String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
				boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
						r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
						r8_MP, r9_BType, keyValue);
				if (isValid) {
					// 有效
					if (r9_BType.equals("1")) {
						// 浏览器重定向
						//response.setContentType("text/html;charset=utf-8");
						//response.getWriter().println("支付成功！订单号：" + r6_Order + "金额：" + r3_Amt);
						//根据易宝支付返回的订单号查询订单
						Order order=OrderService.findOrderByOid(r6_Order);
						//设置订单状态为2
						order.setState(2);
						//更新订单
						OrderService.updateOrder(order);
						//提示信息放入requst
						request.setAttribute("msg", "支付成功！订单号：" + r6_Order + "金额：" + r3_Amt);
						return "/jsp/info.jsp";
						
						
					} else if (r9_BType.equals("2")) {
						// 修改订单状态:
						// 服务器点对点，来自于易宝的通知
						System.out.println("收到易宝通知，修改订单状态！");//
						// 回复给易宝success，如果不回复，易宝会一直通知
						response.getWriter().print("success");
					}

				} else {
					throw new RuntimeException("数据被篡改！");
				}
				return null;
	}

}