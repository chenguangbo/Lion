package cn.cgb.store.domain;

//购物项
public class CartItem {
	private Product product; //商品对象
	private int num;  //商品数量
	private double subTotal; //小计
	
	//小计是经过计算获取的
	public double getSubTotal() {
		return product.getShop_price()*num;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public CartItem() {
		// TODO Auto-generated constructor stub
	}
	public CartItem(Product product, int num, double subTotal) {
		super();
		this.product = product;
		this.num = num;
		this.subTotal = subTotal;
	}
	@Override
	public String toString() {
		return "CartItem [product=" + product + ", num=" + num + ", subTotal=" + subTotal + "]";
	}
	
}
