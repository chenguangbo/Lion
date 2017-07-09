package maven_cgb.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component//将本类注入到springboot容器中
@ConfigurationProperties(prefix = "boy")//加载配置文件到本类中
public class BoyProperties {
	/* 必须要给get/set方法否则获取不到值 */
	private String run;
	private Integer age;
	private String hobby; 
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public void setRun(String run) {
		this.run = run;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getRun() {
		return run;
	}
	public Integer getAge() {
		return age;
	}
	@Override
	public String toString() {
		return "BoyProperties [run=" + run + ", age=" + age + ", hobby=" + hobby + "]";
	}
	
	
	
	
}
