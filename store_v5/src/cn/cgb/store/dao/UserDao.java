package cn.cgb.store.dao;

import cn.cgb.store.domain.User;

public interface UserDao {

	void saveUser(User user)throws Exception ;
	User findUserByCode(String code)throws Exception ;
	void updateUser(User user)throws Exception ;
	User userLogin(User user)throws Exception ;
}
