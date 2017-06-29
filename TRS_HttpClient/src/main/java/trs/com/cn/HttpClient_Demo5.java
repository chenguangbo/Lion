package trs.com.cn;

import java.io.File;
import java.io.InputStream;
import java.net.URI;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClient_Demo5 {

	public static void main(String[] args) throws Exception {

		CloseableHttpClient httpClient = HttpClients.createDefault();//创建httpClient对象
		
		URI uri = new URI("http://www.tuicool.com/");
		HttpGet httpGet = new HttpGet(uri); //创建httpget对象 
		//设置用户代理
		httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0");
		CloseableHttpResponse response = httpClient.execute(httpGet);//执行爬取
		//获取网页的实体
		HttpEntity entity = response.getEntity();
		InputStream in = entity.getContent();
		//获取服务器端返回的状态码
		int code = response.getStatusLine().getStatusCode();
		System.out.println("请求状态是:  " + code );
		FileUtils.copyToFile(in, new File("c://123.txt"));
		
		
		httpClient.close();
		response.close();
		
		
//		boolean domainMatches = HttpCookie.domainMatches("", InetAddress.getLocalHost().getHostAddress());
		
	}

}
