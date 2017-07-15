package cn.baidu.dao;

import java.sql.SQLException;
import java.util.List;

import co.baidu.po.Book;

public interface BookDao {

	List<Book> queryListBook() throws SQLException;

}
