package trs.com.cn;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClient_Demo2 {

	public static void main(String[] args) throws ClientProtocolException, IOException {

		CloseableHttpClient createDefault = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.tuicool.com/aasa");
		// 设置头信息
		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36 QIHU 360EE");
		CloseableHttpResponse response = createDefault.execute(httpGet);
		// 执行httpGet请求
		HttpEntity entity = response.getEntity();// 获取实体
		Header contentType = entity.getContentType();// 获取头信息
		StatusLine statusLine = response.getStatusLine();
		int code = statusLine.getStatusCode();//获取干净的状态码
		System.out.println(code);
		Locale locale = response.getLocale();  //获取地点
//		response.get
		System.out.println(locale);
		System.out.println("状态码:  "+statusLine);
		
		System.out.println(contentType);
		String ent = EntityUtils.toString(entity, "utf-8"); // 把抓取到的页面转换成string形式以UTF-8的形式

//		System.out.println(ent);

	}

}
