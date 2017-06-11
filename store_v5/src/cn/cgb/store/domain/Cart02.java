package cn.cgb.store.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart02 {

	//集合<购物项>
	private List<CartItem> list=new ArrayList<CartItem>();
	//总计
	private double total;
	
	//总计时经过计算获得
	public double getTotal(){
		total=0;
		for(CartItem item:list){
			total=total+item.getSubTotal();
		}
		return total;
	}
	
	
	//1_向购物车中添加购物项
	public void addToCart(CartItem ct){
		
		//获取到当前待添加到购物车中的商品id
		String pid=ct.getProduct().getPid();
		
		for(CartItem item:list){
			if(item.getProduct().getPid().equals(pid)){
				//先获取到原先的数量 和现在数量相加
				item.setNum(item.getNum()+ct.getNum());
				
			}else{
				list.add(ct);
			}
		}
	}
	//2_移除购物车中某个类别商品信息
	public void removeCartItem(String pid){
		for(CartItem ct:list){
			if(ct.getProduct().getPid().equals(pid)){
				list.remove(ct);
			}
		}
	}
	
	//3_清除购物车
	public void clearCart(){
		list.clear();
	}
	
}
