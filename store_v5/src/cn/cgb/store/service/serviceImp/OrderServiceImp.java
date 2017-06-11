package cn.cgb.store.service.serviceImp;

import java.util.List;

import cn.cgb.store.dao.OrderDao;
import cn.cgb.store.dao.daoImp.OrderDaoImp;
import cn.cgb.store.domain.Order;
import cn.cgb.store.domain.OrderItem;
import cn.cgb.store.domain.PageBean;
import cn.cgb.store.domain.User;
import cn.cgb.store.service.OrderService;
import cn.cgb.store.utils.JDBCUtils;

public class OrderServiceImp implements OrderService {

	@Override
	public List<Order> findAllOrders() throws Exception {
		return OrderDao.findAllOrders();
	}

	@Override
	public List<Order> findAllOrdersByState(String state) throws Exception {
		return OrderDao.findAllOrersByState(state);
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		OrderDao.updateOrder(order);
	}

	OrderDao OrderDao=new OrderDaoImp();
	
	@Override
	public PageBean findOrderByUid(User user, int curNum) throws Exception {
		//创建PageBean对象
		int totalRecord=OrderDao.findTotalNumByUid(user);
		PageBean pageBean=new PageBean(curNum,totalRecord,3);
		//为PageBean对象关联当前页中订单的数据
		List<Order> list=OrderDao.findOrderByUid(user,pageBean.getStartIndex(),pageBean.getPageSize());
		pageBean.setData(list);
		//为PageBean对象关联公共的url路径
		pageBean.setUrl("OrderServlet?method=findOrderByUid");
		// 返回PageBean对象
		return pageBean;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		return OrderDao.findOrderByOid(oid);
	}

	@Override
	public void saveOrder(Order order) throws Exception {
		try {
			JDBCUtils.startTransaction();
			OrderDao.saveOrder(order);
			for(OrderItem item:order.getList()){
				OrderDao.saveOrderItem(item);
			}
			JDBCUtils.commitAndClose();
		} catch (Exception e) {
			JDBCUtils.rollbackAndClose();
			e.printStackTrace();
		}
	}

}
