package cn.baidu.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {

	// 创建连接池
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	// 创建线程级别的连接
	private static ThreadLocal<Connection> tl = new ThreadLocal<>();

	/**
	 * 获取链接
	 * createtime 2017年7月18日21:54:10
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		Connection conn = tl.get();// 从线程级别的类中获取链接
		if (conn == null) {
			conn = ds.getConnection();
			tl.set(conn);
		}
		return conn;
	}

	/**
	 * 获取连接池
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return ds;
	}

	// 释放资源

	public static void closeResource(Statement st, ResultSet rs) {
		closeResultSet(rs);
		closeStatement(st);

		// closeResource(st, rs);
	}

	public static void closeResource(Connection conn, Statement st, ResultSet rs) {
		closeConn(conn);
		closeStatement(st);
		closeResultSet(rs);
	}

	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("关闭链接时产生了异常!");
				e.printStackTrace();
			}
		}
	}

	// 释放结果集
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				// 释放statement时产生了一场
				System.out.println("释放statement时产生了一场");
			}
			st = null;
		}
	}

	// 开启事物
	public static void startTransaction() throws SQLException {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("开启事物失败");
		}

	}

	// 事物提交释放链接
	public static void transactionCommet() {
		Connection conn = null;
		try {
			conn = getConnection();// 获取链接
			conn.commit();// 提交事物
			conn.close();// 关闭资源
			tl.remove();// 删除当前线程车中的 链接信息
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 事物回滚 释放资源
	public static void transactionRollBack() {

		Connection conn = null;
		try {
			conn = getConnection();// 从当前线程中获取链接
			conn.rollback();
			conn.close();

			tl.remove();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws SQLException {
		System.err.println(getConnection());
	}

}
