package net.codejava.SpringBootWebApp;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class AppController {

	@RequestMapping("/")
	public String listContact(Model model) {
		// Get data from csv
		CtrlRecord business = new CtrlRecord();
		List<Record> record = business.getRecord();
		
		// Upload the list
		model.addAttribute("recordValues", record);

		return "courses";
	}

	@PostMapping("/")
	public String calculate(@ModelAttribute("value1") String value1, @ModelAttribute("currency1") String currency1,
			@ModelAttribute("currency2") String currency2, Model model) {
		
		// Get data from csv
		CtrlRecord business = new CtrlRecord();
		List<Record> record = business.getRecord();
		
		// Initialize variables
		double result;
		double cashIn;
		
		double course1 = 0;
		double course2 = 0;
		
		// Replace coma with dots
		if (value1.contains(",")) {
			value1.replace(",", ".");
		}
			
		// Try to map the string to double
		try {
			cashIn = Double.parseDouble(value1);
		}
		catch(NumberFormatException e) {
			System.out.println("Failed to receive number in a numeric format!");
			cashIn = 0.0;
		}

		// Find the appropriate factor
		for (int i = 0; i < record.size(); i++) {

			if (record.get(i).getName().equals(currency1)) {

				course1 = Double.parseDouble(record.get(i).getValue());
			}

			if (record.get(i).getName().equals(currency2)) {

				course2 = Double.parseDouble(record.get(i).getValue());
			}
		}

		// Evaluate expression
		result = (cashIn * course1) / course2;
		result = Math.round(result * 100.0) / 100.0;
		
		// Put the attributes
		model.addAttribute("result", result);
		model.addAttribute("value", cashIn);
		model.addAttribute("course1", currency1);
		model.addAttribute("course2", currency2);

		return "calc";
	}

}
