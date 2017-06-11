package cn.cgb.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cgb.store.domain.Cart;
import cn.cgb.store.domain.CartItem;
import cn.cgb.store.domain.Product;
import cn.cgb.store.service.ProductService;
import cn.cgb.store.service.serviceImp.ProductServiceImp;
import cn.cgb.store.web.baseservlet.BaseServlet;

public class CartServlet extends BaseServlet {

	
	public String addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取pid
		String pid = request.getParameter("pid");
		//获取数量
		int num= Integer.parseInt(request.getParameter("num"));
		//根据pid查询product
		ProductService ProductService=new ProductServiceImp();
		Product product = ProductService.findProductByPid(pid);
		//创建购物项
		CartItem ct=new CartItem();
		ct.setNum(num);
		ct.setProduct(product);
		
		//获取购物车(从session中获取,获取到直接用,获取不到创建并在session绑定一份)
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		if(null==cart){
			cart=new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		//调用购物车上的添加商品到购物车功能
		cart.addToCart(ct);
		//重新定向到jsp/cart.jsp
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		
		//在jsp/cart.jsp获取购物车中的信息
		
		return null;
	}
	//clearCart
	public String clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		///获取到session中的购物车
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		//调用购物车上的清空购物车方法
		cart.clearCart();
		//重新定向到jsp/cart.jsp
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}
	//removeCartItem
	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取pid
		String pid=request.getParameter("pid");
		//获取购物车
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		//调用购物车上的移除本类商品功能
		cart.removeCartItem(pid);
		//重新定向到/jsp/cart.jsp
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}

}