package net.codejava.SpringBootWebApp;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import net.codejava.SpringBootWebApp.WebScraper;

@Controller
public class AppController {

	@RequestMapping("/")
	public String listContact(Model model) {
		// Get data from csv
		WebScraper.main(null);
		CtrlRecord business = new CtrlRecord();
		List<Record> record = business.getRecord();
		
		
		// Upload the list
		model.addAttribute("recordValues", record);

		return "courses";
	}

	@PostMapping("/")
	public String calculate(@ModelAttribute("value1") String value1, @ModelAttribute("currency1") String currency1,
			@ModelAttribute("currency2") String currency2, @ModelAttribute("info1") String info1, @ModelAttribute("info2") String info2, Model model) {
		
		// Get data from csv
		CtrlRecord business = new CtrlRecord();
		List<Record> record = business.getRecord();
		
		// Info is empty String first
		info1 = "";
		info2 = "";
		
		// Initialize variables
		double result;
		double cashIn;
		
		double course1 = 0;
		double course2 = 0;
		
		// Replace coma with dots
		if (value1.contains(",")) {
			value1 = value1.replace(",", ".");
		}
		System.out.print(value1);
			
		// Try to map the string to double
		try {
			cashIn = Double.parseDouble(value1);
		}
		catch(NumberFormatException e) {
			System.out.println("Failed to receive number in a numeric format!");
			cashIn = 0.0;
			info1 = "Wartosc podana w oknie 'Kwota' nie jest liczba!";
		}

		// Find the appropriate factor
		for (int i = 0; i < record.size(); i++) {

			if (record.get(i).getName().equals(currency1)) {
				
				course1 = Double.parseDouble(record.get(i).getValueBuy().replace(',', '.'));
				
			}

			if (record.get(i).getName().equals(currency2)) {


				course2 = Double.parseDouble(record.get(i).getValueSell().replace(',', '.'));

			}
		}
		
		// Determine info
		if (currency1.equals(currency2)) {
			info2 = "Obie podane waluty sa takie same!";
		}

		// Evaluate expression
		result = (cashIn * course1) / course2;
		result = Math.round(result * 100.0) / 100.0;
		
		String resultStr = String.valueOf(result);
		String inStr = String.valueOf(cashIn);
		
		if (resultStr.contains(".")) {
			resultStr = resultStr.replace(".", ",");
		}
		
		if (inStr.contains(".")) {
			inStr = inStr.replace(".", ",");
		}
		
		// Put the attributes
		model.addAttribute("result", resultStr);
		model.addAttribute("value", inStr);
		model.addAttribute("course1", currency1);
		model.addAttribute("course2", currency2);
		model.addAttribute("info1", info1);
		model.addAttribute("info2", info2);

		return "calc";
	}

}
