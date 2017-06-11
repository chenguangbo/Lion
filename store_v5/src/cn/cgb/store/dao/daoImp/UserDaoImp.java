package cn.cgb.store.dao.daoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.cgb.store.dao.UserDao;
import cn.cgb.store.domain.User;
import cn.cgb.store.utils.JDBCUtils;

public class UserDaoImp implements UserDao {

	@Override
	public void saveUser(User user) throws Exception {
		String sql="INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
		Object[] params={user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,params);
	}

	@Override
	public User userLogin(User user) throws Exception {
		String sql="select * from user where username=? and password=? and state=1";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
	}

	@Override
	public User findUserByCode(String code) throws Exception {
		String sql="select * from user where code=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<User>(User.class),code);
		
	}

	@Override
	public void updateUser(User user) throws Exception {
		String sql="UPDATE USER SET username=? , PASSWORD=? ,NAME=? ,email=?, telephone=?, birthday=?,sex=?,state=? ,CODE=? WHERE uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode(),user.getUid()};
		qr.update(sql,params);
	}
	
	

}
