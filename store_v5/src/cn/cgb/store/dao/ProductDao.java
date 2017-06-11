package cn.cgb.store.dao;

import java.util.List;

import cn.cgb.store.domain.Product;

public interface ProductDao {

	List<Product> findNewsProducts() throws Exception ;

	List<Product> findHotsProducts() throws Exception ;

	Product findProductByPid(String pid)throws Exception ;

	int findTotalNumByCid(String cid)throws Exception ;

	List<Product> findProductsByCidWithPage(String cid, int startIndex, int pageSize)throws Exception ;

	void saveProduct(Product product)throws Exception ;

}
