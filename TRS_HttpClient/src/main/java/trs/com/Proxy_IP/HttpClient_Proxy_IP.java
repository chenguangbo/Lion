package trs.com.Proxy_IP;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClient_Proxy_IP {

	public static void main(String[] args) throws Exception {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://zhidao.baidu.com/question/2202919366028771468.html");
		//创建代理服务器host
		HttpHost proxy = new HttpHost("123.118.169.48",8118);
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
		httpGet.setHeader("accept-language", "zh-CN,zh;q=0.8");
		//请求设置代理
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		httpGet.setConfig(config);//设置新添加的代理服务器
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		response.addHeader("accept-language", "zh-CN,zh;q=0.8");
		FileUtils.copyToFile(entity.getContent(), new File("c://321.txt"));
		String ent = EntityUtils.toString(entity);
		System.out.println(ent);
		int code = response.getStatusLine().getStatusCode();
		
		System.out.println(code);
		
		
		
	}
	
	
}
