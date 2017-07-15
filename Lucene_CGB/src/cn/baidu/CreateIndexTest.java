package cn.baidu;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import cn.baidu.dao.BookDao;
import cn.baidu.dao.BookDaoImpl;
import co.baidu.po.Book;

public class CreateIndexTest {

	/**
	 * @throws SQLException
	 * @throws IOException 
	 */

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
		Path path = new Path() {
			
			@Override
			public URI toUri() {
				
				try {
					return new URI("D:/lucene");
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
			@Override
			public Path toRealPath(LinkOption... options) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public File toFile() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Path toAbsolutePath() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Path subpath(int beginIndex, int endIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean startsWith(String other) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean startsWith(Path other) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Path resolveSibling(String other) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Path resolveSibling(Path other) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Path resolve(String other) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Path resolve(Path other) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Path relativize(Path other) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public WatchKey register(WatchService watcher, Kind<?>[] events, Modifier... modifiers) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public WatchKey register(WatchService watcher, Kind<?>... events) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Path normalize() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Iterator<Path> iterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isAbsolute() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Path getRoot() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Path getParent() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getNameCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Path getName(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public FileSystem getFileSystem() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Path getFileName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean endsWith(String other) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean endsWith(Path other) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public int compareTo(Path other) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		Directory dir = FSDirectory.open(path);
		
		// 4.创建IndexWriterConfig配置信息类
		IndexWriterConfig indexConfig = new IndexWriterConfig(a);
		// 5.创建Directory对象，声明索引库存储位置
		// 6.创建IndexWriter写入对象
		// 7.把Document写入到索引库中
		// 8.释放资源

	}

}
