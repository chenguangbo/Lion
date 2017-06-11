package cn.cgb.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	// 集合<购物项> pid<===>CartItem
	private Map<String, CartItem> map = new HashMap<String, CartItem>();
	// 总计
	private double total;
	
	//返回map中所有的值
	public Collection<CartItem> getCartItems(){
		System.out.println("DDDDDDD");
		return map.values();
	}
	
	//获取总计
	public double getTotal(){
		total=0;
		Collection<CartItem> values = map.values();
		for(CartItem ct:values){
			total=ct.getSubTotal()+total;
		}
		return total;
	}
	
	
	// 1_向购物车中添加购物项
	public void addToCart(CartItem ct) {
		//向购物车中添加商品pid
		String pid=ct.getProduct().getPid();
		if(map.containsKey(pid)){
			//获取到购物车中原先的购物项
			CartItem oldItem=map.get(pid);
			oldItem.setNum(oldItem.getNum()+ct.getNum());
		}else{
			map.put(pid, ct);
		}
		
		
	}
	//2_移除购物车中某个类别商品信息
	public void removeCartItem(String pid){
		map.remove(pid);
	}
	
	//3_清除购物车
	public void clearCart(){
		map.clear();
	}


	public Map<String, CartItem> getMap() {
		return map;
	}


	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}


	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
}
