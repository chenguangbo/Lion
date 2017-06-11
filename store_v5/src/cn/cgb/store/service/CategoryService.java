package cn.cgb.store.service;

import java.util.List;

import cn.cgb.store.domain.Category;

public interface CategoryService {

	List<Category> findAllCats()throws Exception;

	void addCat(Category category)throws Exception;

}
