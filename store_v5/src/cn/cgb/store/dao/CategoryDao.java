package cn.cgb.store.dao;

import java.util.List;

import cn.cgb.store.domain.Category;

public interface CategoryDao {

	List<Category> findAllCats()throws Exception;

	void addCat(Category category)throws Exception;

}
