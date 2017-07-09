package maven_cgb.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import maven_cgb.pojo.BoyProperties;

@RestController
public class DemoMaven_pojo_Test {

	@Autowired
	private BoyProperties boyProperties;

	@GetMapping(value = "/boy")
	public Object boy() {
		System.err.println(boyProperties.toString());
		return boyProperties;
	}

}
