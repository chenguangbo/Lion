package cn.cgb.store.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//订单
public class Order {

	private String oid;   //订单id,流水号
	private Date ordertime; //下单时间 
	private double total;  //总计
	private int state;  //订单状态  4个状态订单状态约定: 1:下单未付款2:付款未发货3:发货未收货4:收货订单结束
	private String address; //收货人地址
	private String name;//收货人姓名
	private String telephone; //收货人电话
	
	//1_user可以携带更多的数据
	//2_面向对象角度设计,订单对应用户对象,不是订单对应用户手机号,用户的手机不能购物
	private User user;
	
	//集合  当前订单下有多少订单项
	private List<OrderItem> list=new ArrayList<OrderItem>();

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getList() {
		return list;
	}

	public void setList(List<OrderItem> list) {
		this.list = list;
	}
	
	
}
