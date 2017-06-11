package cn.cgb.store.dao.daoImp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.cgb.store.dao.ProductDao;
import cn.cgb.store.domain.Product;
import cn.cgb.store.utils.JDBCUtils;

public class ProductDaoImp implements ProductDao {

	@Override
	public void saveProduct(Product product) throws Exception {
		String sql="insert into product values (?,?,?,?,?,?,?,?,?,?)";
		Object[] params={product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid()};
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,params);
	}

	@Override
	public int findTotalNumByCid(String cid) throws Exception {
		String sql="select count(*) from product where cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),cid);
		return num.intValue();
	}

	@Override
	public List<Product> findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {
		String sql="SELECT * FROM product WHERE cid=? LIMIT ?,?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
	}

	@Override
	public List<Product> findNewsProducts() throws Exception {
		String sql="SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT 0,9";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
		
	}

	@Override
	public List<Product> findHotsProducts() throws Exception {
		String sql="SELECT * FROM product WHERE is_hot = 1 AND pflag=0 ORDER BY pdate DESC LIMIT 0,9";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		String sql="select * from product where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
		
	}

	
	
}
