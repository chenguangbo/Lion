package cn.cgb.store.dao;

import java.util.List;

import cn.cgb.store.domain.Order;
import cn.cgb.store.domain.OrderItem;
import cn.cgb.store.domain.User;

public interface OrderDao {

	void saveOrder(Order order)throws Exception;

	void saveOrderItem(OrderItem item)throws Exception;

	int findTotalNumByUid(User user)throws Exception;

	List<Order> findOrderByUid(User user, int startIndex, int pageSize)throws Exception;

	Order findOrderByOid(String oid)throws Exception;

	void updateOrder(Order order)throws Exception;

	List<Order> findAllOrersByState(String state)throws Exception;

	List<Order> findAllOrders()throws Exception;

}
