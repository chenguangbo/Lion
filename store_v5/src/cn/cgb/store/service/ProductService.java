package cn.cgb.store.service;

import java.util.List;

import cn.cgb.store.domain.PageBean;
import cn.cgb.store.domain.Product;

public interface ProductService {

	List<Product> findNewsProducts()throws Exception;

	List<Product> findHotsProducts()throws Exception;

	Product findProductByPid(String pid)throws Exception;

	PageBean findProductsByCidWithPage(String cid, int pageNumber)throws Exception;

	void saveProduct(Product product)throws Exception;

}
