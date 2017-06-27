package trs.com.cn;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClient_Demo {

	public static void main(String[] args) throws ClientProtocolException, IOException {

		CloseableHttpClient createDefault = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.tuicool.com/");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36 QIHU 360EE");
		CloseableHttpResponse response = createDefault.execute(httpGet);

		HttpEntity entity = response.getEntity();
		Header contentType = entity.getContentType();
//		InputStream in = entity.getContent();
//		byte[] b = new byte[1024];
//		int i = 0 ;
//		while ((i=in.read(b))!=-1) {
//			System.out.println(new String(b,0,i));
//		}
//		Header contentEncoding = entity.getContentEncoding();  //空
//		System.out.println("编码方式"+contentEncoding.getName()+"value:  "+contentEncoding.getValue());
		long contentLength = entity.getContentLength();
		System.out.println(contentLength);
		System.out.println(contentType);
		
		String ent = EntityUtils.toString(entity, "utf-8");
	//	System.out.println(ent);

	}

}
