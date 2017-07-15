package cn.baidu;

import java.io.File;
import java.util.ArrayList;
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
import org.apache.lucene.util.Version;
import org.junit.Test;

import cn.baidu.dao.BookDao;
import cn.baidu.dao.BookDaoImpl;
import co.baidu.po.Book;

public class CreateIndex {
	@Test
	public void testCreateIndex() throws Exception {
		// 1. 采集数据
		BookDao bookDao = new BookDaoImpl();
		List<Book> list = bookDao.queryListBook();

		// 2. 创建Document文档对象
		List<Document> documents = new ArrayList<>();
		for (Book book : list) {
			Document document = new Document();

			// Document文档中添加Field域
			// 图书Id
			// Store.YES:表示存储到文档域中
			document.add(new TextField("id", book.getId()+"", Store.YES));
			// 图书名称
			document.add(new TextField("name", book.getName().toString(), Store.YES));
			// 图书价格
			document.add(new TextField("price", book.getPrice().toString(), Store.YES));
			// 图书图片地址
			document.add(new TextField("pic", book.getPic().toString(), Store.YES));
			// 图书描述
			document.add(new TextField("desc", book.getDescription().toString(), Store.YES));

			// 把Document放到list中
			documents.add(document);
		}

		// 3. 创建Analyzer分词器,分析文档，对文档进行分词
		Analyzer analyzer = new StandardAnalyzer();

		// 4. 创建Directory对象,声明索引库的位置
//		Directory directory = FSDirectory.open(new File("C:/itcast/lucene/index"));

		// 5. 创建IndexWriteConfig对象，写入索引需要的配置
//		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_6_6_0, analyzer);

		// 6.创建IndexWriter写入对象
//		IndexWriter indexWriter = new IndexWriter(directory, config);

		// 7.写入到索引库，通过IndexWriter添加文档对象document
//		for (Document doc : documents) {
//			indexWriter.addDocument(doc);
//		}
//
//		// 8.释放资源
//		indexWriter.close();
	}
}