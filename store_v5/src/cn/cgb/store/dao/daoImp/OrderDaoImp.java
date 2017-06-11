package cn.cgb.store.dao.daoImp;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.cgb.store.dao.OrderDao;
import cn.cgb.store.domain.Order;
import cn.cgb.store.domain.OrderItem;
import cn.cgb.store.domain.Product;
import cn.cgb.store.domain.User;
import cn.cgb.store.utils.JDBCUtils;

public class OrderDaoImp implements OrderDao {

	@Override
	public List<Order> findAllOrersByState(String state) throws Exception {
		String sql="select * from orders where state=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Order>(Order.class),state);
		
	}

	@Override
	public List<Order> findAllOrders() throws Exception {
		String sql="select * from orders";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Order>(Order.class));
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		String sql="update orders set ordertime=? ,total= ?, state=?, address=?, name=?, telephone= ? where oid=? ";
		Object[] params={order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid()};
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,params);
	}

	//INSERT INTO orders VALUES(?,?,?,?,?,?,?,?);
	//INSERT INTO orderitem VALUES(?,?,?,?,?);
	@Override
	public void saveOrder(Order order) throws Exception {
		String sql="INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner();
		Object[] params={order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid()};
		qr.update(JDBCUtils.getConnection(),sql,params);
	}

	@Override
	public int findTotalNumByUid(User user) throws Exception {
		String sql="select count(*) from orders where uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),user.getUid());
		return num.intValue();
	}

	@Override
	public List<Order> findOrderByUid(User user, int startIndex, int pageSize) throws Exception {
		String sql="SELECT * FROM orders WHERE uid=? LIMIT ?,?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list=qr.query(sql, new BeanListHandler<Order>(Order.class),user.getUid(),startIndex,pageSize);
		
		
		//以下语句是当前功能的注意之初:之前我们只需要查询所有的订单即可,现在我们需要将所有的订单上相关联的订单项以及订单项关联的商品信息查询出来,否则无法
		//在页面上无法获取到订单上关联的订单项和商品信息 
		if(null!=list&&list.size()>0){
			for(Order order:list){
				//获取到订单oid
				sql="SELECT * FROM orderitem o, product p WHERE o.pid=p.pid AND oid=?";
				List<Map<String, Object>> list01 = qr.query(sql, new MapListHandler(),order.getOid());
				for(Map<String, Object> map:list01){
					System.out.println(map);
					Product product=new Product();
					OrderItem orderItem=new OrderItem();
					//BeanUtils工具将map中属于product对象上的数据自动填充到Product对象上
					//BeanUtils工具将map中属于orderItem对象上的数据自动填充到Product对象上
					
					
					//BeanUtils:通过product product.getClass()可以获取到Product.class字节码对象
					//结合MAP中名称中判断Product.class字节码对象上是否有setPid,setShop_price,如果找到,调用product对应的方法,将当前的值传递到方法中
					BeanUtils.populate(product, map);
					BeanUtils.populate(orderItem, map);
					
					orderItem.setProduct(product);
					
					order.getList().add(orderItem);
				}
			}
		}
		
		
		return list;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		String sql="select * from orders where oid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Order order=qr.query(sql, new BeanHandler<Order>(Order.class),oid);
		
		//获取到订单oid
		sql="SELECT * FROM orderitem o, product p WHERE o.pid=p.pid AND oid=?";
		List<Map<String, Object>> list01 = qr.query(sql, new MapListHandler(),order.getOid());
		for(Map<String, Object> map:list01){
			System.out.println(map);
			Product product=new Product();
			OrderItem orderItem=new OrderItem();
			//BeanUtils工具将map中属于product对象上的数据自动填充到Product对象上
			//BeanUtils工具将map中属于orderItem对象上的数据自动填充到Product对象上
			
			
			//BeanUtils:通过product product.getClass()可以获取到Product.class字节码对象
			//结合MAP中名称中判断Product.class字节码对象上是否有setPid,setShop_price,如果找到,调用product对应的方法,将当前的值传递到方法中
			BeanUtils.populate(product, map);
			BeanUtils.populate(orderItem, map);
			
			orderItem.setProduct(product);
			
			order.getList().add(orderItem);
		}
		return order;
	}

	@Override
	public void saveOrderItem(OrderItem item) throws Exception {
		String sql="INSERT INTO orderitem VALUES(?,?,?,?,?)";
		QueryRunner qr=new QueryRunner();
		Object[] params={item.getItemid(),item.getQuantity(),item.getTotal(),item.getProduct().getPid(),item.getOrder().getOid()};
		qr.update(JDBCUtils.getConnection(),sql,params);
	}

	
	public static void main(String[] args) {
		try {
			User user = new User();
			user.setUid("684C14B266784DBA849DA97F9C41D3F7");
			new OrderDaoImp().findOrderByUid(user, 0, 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
