package maven_cgb.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoMaven_RestFul {
	//@RestController相当于@Controller+@ResponseBody
	@GetMapping(value = "/rest/{str}")
	// 加上@ResponseBody输入什么返回什么 不加跳转的是对应的jsp页面
	// public @ResponseBody String restFul(@PathVariable String str){
	public String restFul(@PathVariable String str) {
		System.out.println("restful 的测试");
		return str;
	}

}
