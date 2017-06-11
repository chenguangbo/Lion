package cn.cgb.store.service;

import cn.cgb.store.domain.User;

public interface UserService {

	void saveUser(User user)throws Exception ;

	void active(String code)throws Exception ;

	User userLogin(User user)throws Exception ;

}
