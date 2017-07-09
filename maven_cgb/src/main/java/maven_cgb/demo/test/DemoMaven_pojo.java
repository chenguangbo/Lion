package maven_cgb.demo.test;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component//将本类注入到springboot容器中
@ConfigurationProperties(prefix = "boy")//加载配置文件到本类中
public class DemoMaven_pojo {

	private String run;
	private Integer age;
	
	
}
