package cn.baidu;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexCommit;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.Sort;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.LockFactory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.store.RAMDirectory;

import cn.baidu.dao.BookDao;
import cn.baidu.dao.BookDaoImpl;
import co.baidu.po.Book;

public class CreateIndexTest4 {

	/**
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws SQLException, IOException {
		createIndex();
	}

	@SuppressWarnings("resource")
	public static void createIndex() throws SQLException, IOException {
		// 1.采集数据
		BookDao bookDao = new BookDaoImpl();
		List<Book> list = bookDao.queryListBook();
		// 2.创建Document文档对象
		List<Document> documents = new ArrayList<Document>();
		for (Book book : list) {
			Document doc = new Document();// document要创建在循环里面 创建在外面相同的域名不覆盖
			doc.add(new TextField("id", "" + book.getId(), Store.YES));
			doc.add(new TextField("name", "" + book.getName(), Store.YES));
			doc.add(new TextField("price", "" + book.getPrice(), Store.YES));
			doc.add(new TextField("pic", "" + book.getPic(), Store.YES));
			doc.add(new TextField("description", "" + book.getDescription(), Store.YES));
			documents.add(doc);
		}

		// 3.创建分析器（分词器）
		Analyzer a = new StandardAnalyzer();// 创建标准的分词解析器

		// 4.创建IndexWriterConfig配置信息类
		IndexWriterConfig conf = new IndexWriterConfig(a);
		// 5.创建Directory对象，声明索引库存储位置

		Directory directory = NIOFSDirectory.open(FileSystems.getDefault().getPath("e:/lucene"));
		// 存储到内存中
		
		Directory ramDir = new RAMDirectory();
		Path path = Paths.get("d:/lucene");
		// 存储到硬盘中
		FSDirectory fsDir = FSDirectory.open(path);
		
		
		// 6.创建IndexWriter写入对象
		IndexWriter indexWriter = new IndexWriter(ramDir, conf);
		
//		 7.把Document写入到索引库中
		indexWriter.addDocuments(documents);
		indexWriter.commit();
		indexWriter.close();
		
		// 8.释放资源
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = dateFormat.format(d);
		System.out.println("创建索引完成!" + format);

	}

}
