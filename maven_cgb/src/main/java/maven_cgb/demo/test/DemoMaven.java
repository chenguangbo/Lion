package maven_cgb.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoMaven {
//	@RequestMapping(value = "/")
//	public String index() {
//
//		return "hello";
//	}
	@GetMapping(value="/hello")
	public String hello() {
		return "hello123";
	}
	@GetMapping(value="/")
	public String index(){
		return "index";
	}
	

}
