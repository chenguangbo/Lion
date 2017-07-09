package maven_cgb.demo.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoMaven_Constant {

	// 从配置文件读取数据
	@Value("${cubSize}")
	private String cubSize;

	@GetMapping(value = "/contant/{str}")
	public String contant(@PathVariable String str) {
		System.out.println("url输入的:" + str);
		return cubSize;
	}

}
