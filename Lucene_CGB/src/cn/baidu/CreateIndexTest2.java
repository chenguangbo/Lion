package cn.baidu;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.NIOFSDirectory;

import cn.baidu.dao.BookDao;
import cn.baidu.dao.BookDaoImpl;
import co.baidu.po.Book;

public class CreateIndexTest2 {

	


	/**
	 * @throws SQLException
	 * @throws IOException 
	 */
	public static void main(String[] args) throws SQLException, IOException {
		createIndex();
	}

	public static void createIndex() throws SQLException, IOException {
		// 1.采集数据
		BookDao bookDao = new BookDaoImpl();
		List<Book> list = bookDao.queryListBook();
		// 2.创建Document文档对象
		List<Document> documents = new ArrayList<Document>();
		Document doc = new Document();
		for (Book book : list) {
			doc.add(new TextField("id", "" + book.getId(), Store.YES));
			doc.add(new TextField("name", "" + book.getName(), Store.YES));
			doc.add(new TextField("price", "" + book.getPrice(), Store.YES));
			doc.add(new TextField("pic", "" + book.getPic(), Store.YES));
			doc.add(new TextField("pic", "" + book.getDescription(), Store.YES));
			documents.add(doc);
		}

		// 3.创建分析器（分词器）
		Analyzer a = new StandardAnalyzer();// 创建标准的分词解析器
	
		// 创建文件夹对象
		// 声明索引库位置
		//Directory d = FSDirectory.open(new File(""));
		// FSDirectory:硬盘
		// RAMDirectory:内存
//		File file = new File("D:/lucene");
//		Directory directory = FSDirectory.open(file); //声明索引库位置以及索引库的存储方式
		// 4.创建IndexWriterConfig配置信息类
		IndexWriterConfig conf = new IndexWriterConfig(a);
		// 5.创建Directory对象，声明索引库存储位置
		
		Directory directory = NIOFSDirectory.open(FileSystems.getDefault().getPath("d:/lucene"));
		
		// 6.创建IndexWriter写入对象
		
		IndexWriter iw = new IndexWriter(directory, conf);
		// 7.把Document写入到索引库中
		for (Document docs : documents) {
			iw.addDocument(docs);
		}
		// 8.释放资源
		iw.close();
		Date d = new Date();
		long time = d.getTime();
		System.out.println(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = dateFormat.format(d);
		System.out.println("创建索引完成!"+format);

	}

}
