package chen.trs.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class ItemController {
	
	@GetMapping(value="/personList")
	public String personList(){
		
		return "";
	}
	
	
}
