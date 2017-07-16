package cn.baidu.search;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
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
import org.junit.Test;

import co.baidu.po.Book;

public class IndexSearch {

	public void searchIndex() throws Exception {
		// 1.创建读取路径
		Path path = Paths.get("d:/lucene");
		// 2.创建读取文件夹
		Directory dir = FSDirectory.open(path);
		// 3.读取文件夹里面的索引数据
		IndexReader ir = DirectoryReader.open(dir);
		// 4.创建索引搜索对象
		IndexSearcher is = new IndexSearcher(ir);

		// CharArraySet stopWords = new CharArraySet(0, true); //从第一个开始不区分大小写
		// Analyzer a = new StandardAnalyzer(stopWords);
		// 5.创建分词器
		Analyzer a = new StandardAnalyzer(); // 创建普通的分词器
		// 6.指定默认搜索的域 filed域
		QueryParser qp = new QueryParser("name", a);
		// 7.创建查询条件
		Query query = qp.parse("description:java");
		// TermQuery tq = new TermQuery (new Term ("fieldName","term"));
		// Collector results = (Collector) new ArrayList<>();

		
		TopDocs top = is.search(query, 10);
		
	//	SortField fields = new SortField("name", (Type) SortField.STRING_FIRST, false);
		Sort sort = new Sort( (SortField) SortField.FIELD_SCORE);
		TopFieldDocs fieldDocs = is.search(query, 10, sort );

	//	fieldDocs.
		//查询所有文档
		ScoreDoc[] scoreDocs = fieldDocs.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			int doc = scoreDoc.doc;
			System.out.println(doc);
		}
	}

	public static void main(String[] args) throws Exception {
		new IndexSearch().searchIndex();
	}
	
	
}
