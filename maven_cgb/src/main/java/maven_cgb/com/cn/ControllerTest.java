package maven_cgb.com.cn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerTest {

	
	@GetMapping(value="/abc")
	public String index(){
		
		return "hello";
	}
	
}
