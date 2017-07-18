package cn.baidu.search;

import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopDocsCollector;
import org.apache.lucene.search.TopFieldDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockFactory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;
import org.junit.Test;

import co.baidu.po.Book;

public class IndexSearch3 {

	public void searchIndex() throws Exception {
		// 1.创建读取路径
		Path path = Paths.get("d:/lucene");
		
		// 2.创建读取文件夹
		Directory dir = FSDirectory.open(path);
		//内存中读取
		RAMDirectory ramDirectory = new RAMDirectory();
		
		
		
		// 3.读取文件夹里面的索引数据
		IndexReader ir = DirectoryReader.open(ramDirectory);
		
		DirectoryReader r = DirectoryReader.open(ramDirectory);
		
		// 4.创建索引搜索对象
	//	IndexSearcher is = new IndexSearcher(ir);
		IndexSearcher is = new IndexSearcher(r);

		// CharArraySet stopWords = new CharArraySet(0, true); //从第一个开始不区分大小写
		// Analyzer a = new StandardAnalyzer(stopWords);
		// 5.创建分词器
		Analyzer a = new StandardAnalyzer(); // 创建普通的分词器
		// 6.指定默认搜索的域 filed域                      域名       分词器
		QueryParser qp = new QueryParser("name", a);
		// 7.创建查询条件
		Query query = qp.parse("description:java");
		// TermQuery tq = new TermQuery (new Term ("fieldName","term"));
		// Collector results = (Collector) new ArrayList<>();
		TopDocs top = is.search(query, 10);

		System.out.println("符合查询条件的总条数:   " + top.totalHits);
		ScoreDoc[] docs = top.scoreDocs;

		for (ScoreDoc scoreDoc : docs) {
			int docID = scoreDoc.doc;
			Document doc = is.doc(docID);
			String string = doc.get("name");
			System.out.println(string);
			System.out.println( doc.get("description"));
		
		}

		
//		String string = doc.get("id");
//		System.out.println(string);
		
		
	}

	public static void main(String[] args) throws Exception {
		new IndexSearch3().searchIndex();
	}

}
