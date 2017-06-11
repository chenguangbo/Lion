package cn.cgb.store.dao.daoImp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.cgb.store.dao.CategoryDao;
import cn.cgb.store.domain.Category;
import cn.cgb.store.utils.JDBCUtils;

public class CategoryDaoImp implements CategoryDao {

	@Override
	public List<Category> findAllCats() throws Exception {
		String sql="select * from category";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
		
	}

	@Override
	public void addCat(Category category) throws Exception {
		String sql="insert into category values(?,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,category.getCid(),category.getCname());
	}

}
