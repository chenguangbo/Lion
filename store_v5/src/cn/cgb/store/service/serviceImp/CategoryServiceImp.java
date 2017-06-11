package cn.cgb.store.service.serviceImp;

import java.util.List;

import cn.cgb.store.dao.CategoryDao;
import cn.cgb.store.dao.daoImp.CategoryDaoImp;
import cn.cgb.store.domain.Category;
import cn.cgb.store.service.CategoryService;
import cn.cgb.store.utils.BeanFactory;

public class CategoryServiceImp implements CategoryService {

	CategoryDao CategoryDao=new CategoryDaoImp();
	//CategoryDao CategoryDao=(CategoryDao)BeanFactory.createBean("CategoryDao");
	@Override
	public List<Category> findAllCats() throws Exception {
		
		return CategoryDao.findAllCats();
	}
	@Override
	public void addCat(Category category) throws Exception {
		CategoryDao.addCat(category);
	}

	
	
}
