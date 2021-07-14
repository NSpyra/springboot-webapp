package net.codejava.SpringBootWebApp;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class AppController {
	
	@RequestMapping("/test")
	public String test() {
		System.out.println("AppController -> test() 2");
		
		return "test";
	}
	
	@RequestMapping("/list_contact")
	public String listContact(Model model) {
		
		CtrlRecord business = new CtrlRecord();
		List<Record> record = business.getRecord();
		
		model.addAttribute("recordValues", record);
		
		return "courses";
	}

}
