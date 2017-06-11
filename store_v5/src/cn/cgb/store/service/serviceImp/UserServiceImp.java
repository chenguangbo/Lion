package cn.cgb.store.service.serviceImp;

import cn.cgb.store.dao.UserDao;
import cn.cgb.store.dao.daoImp.UserDaoImp;
import cn.cgb.store.domain.User;
import cn.cgb.store.service.UserService;
import cn.cgb.store.utils.BeanFactory;
import cn.cgb.store.utils.MailUtils;

public class UserServiceImp implements UserService {

	private UserDao UserDao=new UserDaoImp();
	//private UserDao UserDao=(UserDao)BeanFactory.createBean("UserDao");
	
	@Override
	public void saveUser(User user) throws Exception {
		//调用DAO层保存用户
		UserDao.saveUser(user);	
		//发送邮件
		MailUtils.sendMail(user.getEmail(), user.getCode());
	}

	@Override
	public void active(String code) throws Exception {
		//根据用户激活码查询用户
		User user=UserDao.findUserByCode(code);
		if(null!=user){
			//设置用户状态为1,将激活码进行清除
			user.setState(1);
			user.setCode("");
			//更新用户状态信息
			UserDao.updateUser(user);
		}else{
			throw new RuntimeException("用户激活失败");
		}
	}

	@Override
	public User userLogin(User user) throws Exception {
		return UserDao.userLogin(user);
	}
	

}
