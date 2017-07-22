package a.cn.com.baidu.createIndex;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.tomcat.jni.Directory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.baidu.dao.BookDao;
import cn.baidu.dao.BookDaoImpl;
import co.baidu.po.Book;

public class CreateIndexDemo {

	@Test
	public void createIndex() throws Exception{
		
		//1.获取数据
		BookDao bookDao = new BookDaoImpl();
		List<Book> listBook = bookDao.queryListBook();
		List<Document> docs = new ArrayList<Document>();
		for (Book book : listBook) {
		//2.创建document对象      
			Document doc = new Document();
			doc.add(new TextField("id",book.getId()+"", Store.YES));
			doc.add(new TextField("name",book.getName(),Store.YES));
			doc.add(new TextField("price", book.getPrice(),Store.YES));
			doc.add(new TextField("pic",book.getPic(),Store.YES));
			doc.add(new TextField("description",book.getDescription(),Store.YES));
			docs.add(doc);
		}
		//3.创建分词器
		Analyzer an = new IKAnalyzer();
		//4.创建索引配置文件加载分词器
		IndexWriterConfig indexConf = new IndexWriterConfig(an);
		
		//5.打开硬盘存储路径
		Path path = Paths.get("d:/lucene");
		FSDirectory dir = FSDirectory.open(path);
		
		//6.创建IndexWriter对象      夹杂分词器对象和硬盘存储路径
		IndexWriter iw = new IndexWriter(dir, indexConf);
		
		//7.将Document对象写入磁盘（索引库对象）
		iw.addDocuments(docs);
		//8.提交写入流
		iw.commit();
		iw.close();
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(d) + "创建索引库完成");
		
	}
	
	
	
	
}
