package a.cn.com.baidu.search;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.baidu.search.IndexSearch;



public class SearchIndex {

	@Test
	public void search() throws Exception {

		Path path = Paths.get("d:/lucene");
		// 1.获取路径
		Directory directory = FSDirectory.open(path);

		// 2.创建索引读取对象
		IndexReader ir = DirectoryReader.open(directory);
		
		// 3.创建索引读取对象
		IndexSearcher indexSearcher = new IndexSearcher(ir);
		
		// 4.创建分词器
		Analyzer an = new IKAnalyzer();
		
		// 5.指定默认搜索的域 filed域                         域名   分词器
		QueryParser queryParser = new QueryParser("name", an);
		
		// 6.创建查询条件  
		Query query = queryParser.parse("description:java");
		
		// 7.执行查询                          查询条件   查询多少条
		TopDocs topDocs = indexSearcher.search(query, 100);
		
		// 8.查询一共有多少条数据
		System.out.println("一共有" + " <font color='red'>" + topDocs.totalHits +"</font>"+ "条数据.");
		
		// 9.获取查询结果
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		
		for (ScoreDoc scoreDoc : scoreDocs) {
			int docID = scoreDoc.doc;//获取当前这条数据的id
			Document document = indexSearcher.doc(docID);//获取当前这条数据的document对象
			System.out.println(document.get("id"));
			System.out.println(document.get("name"));
			System.out.println(document.get("pic"));
			System.out.println(document.get("price"));
			System.out.println(document.get("description"));
			System.err.println("======================================================");
		}
		
		
		
		
	}

}
