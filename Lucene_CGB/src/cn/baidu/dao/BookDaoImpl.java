package cn.baidu.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.baidu.Utils.JDBCUtils;
import co.baidu.po.Book;

public class BookDaoImpl implements BookDao {

	@Override
	public List<Book> queryListBook() throws SQLException {
		DataSource dataSource = JDBCUtils.getDataSource();
		QueryRunner qr = new QueryRunner(dataSource);
		String SQL = "select * from book";
		
		List<Book> list = qr.query(SQL, new BeanListHandler<Book>(Book.class));
		return list;
	}

	public static void main(String[] args) throws SQLException {
		List<Book> list = new BookDaoImpl().queryListBook();
		for (Book book : list) {
			System.out.println(book.toString());
		}
		
	}
	
	
}
