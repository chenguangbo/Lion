package cn.cgb.store.service;

import java.util.List;

import cn.cgb.store.domain.Order;
import cn.cgb.store.domain.PageBean;
import cn.cgb.store.domain.User;

public interface OrderService {

	void saveOrder(Order order)throws Exception ;

	PageBean findOrderByUid(User user, int curNum)throws Exception ;

	Order findOrderByOid(String oid)throws Exception ;

	void updateOrder(Order order)throws Exception;

	List<Order> findAllOrders()throws Exception;

	List<Order> findAllOrdersByState(String state)throws Exception;

}
