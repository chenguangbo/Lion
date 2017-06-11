package cn.cgb.store.service.serviceImp;

import java.util.List;

import cn.cgb.store.dao.ProductDao;
import cn.cgb.store.dao.daoImp.ProductDaoImp;
import cn.cgb.store.domain.PageBean;
import cn.cgb.store.domain.Product;
import cn.cgb.store.service.ProductService;
import cn.cgb.store.utils.BeanFactory;

public class ProductServiceImp implements ProductService {

	@Override
	public void saveProduct(Product product) throws Exception {
		ProductDao.saveProduct(product);
	}

	ProductDao ProductDao=new ProductDaoImp();
	//ProductDao ProductDao=(ProductDao)BeanFactory.createBean("ProductDao");
	
	@Override
	public List<Product> findNewsProducts() throws Exception {
		return ProductDao.findNewsProducts();
	}

	@Override
	public List<Product> findHotsProducts() throws Exception {
		return ProductDao.findHotsProducts();
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		return ProductDao.findProductByPid(pid);
	}

	@Override
	public PageBean findProductsByCidWithPage(String cid, int pageNumber) throws Exception {
		
		//*_ 创建PageBean对象  目的:携带分页参数
		//总数据条数:通过查询DB获取
		int totalRecord=ProductDao.findTotalNumByCid(cid);
		PageBean pageBean=new PageBean<Product>(pageNumber, totalRecord, 12);
		
		//*_查询当前页中的数据,调用Dao功能:
		List<Product> list=ProductDao.findProductsByCidWithPage(cid,pageBean.getStartIndex(),pageBean.getPageSize());
		//SELECT * FROM product WHERE cid=? LIMIT ?,?
		//获取到集合,将集合设置在pageBean对象上
		pageBean.setData(list);
		
		//为pageBean设置url
		pageBean.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		//pageBean.setUrl("/store_v5/ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		
		//*_返回pageBean对象
		return pageBean;
	}
	
	
}
