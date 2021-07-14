package net.codejava.SpringBootWebApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebScraper {
	public static void main(String[] args) {

		String filepath = reinitializeFile();
		Document doc = readWebsite("https://mybank.pl/kursy-walut/");
		parseContent(doc, filepath);
		LocalDate currDate = LocalDate.now();
		System.out.print(currDate);

	}

	public static Document readWebsite(String website) {

		Document doc = null;

		try {
			doc = Jsoup.connect(website).get();
		} catch (IOException e) {

			JOptionPane.showMessageDialog(null, "Failed to read data from the website. Following error turns up: " + e);
			e.printStackTrace();
		}

		return doc;

	}

	public static void parseContent(Document siteContent, String path) {

		boolean dataSaveInProgress = false;
		boolean getNextValue = false;

		String mnemonic = "";
		String value = "";

		String filepath = reinitializeFile();

		for (Element row : siteContent.select("table.tab_kursy tr")) {

			for (Element td : row.select("td")) {

				if (getNextValue) {
					value = td.text().replace(',', '.');
				}

				if (td.text().contains("1 ") && !td.text().contains("1 zł")) {
					dataSaveInProgress = true;

					mnemonic = td.text().replace("1 ", "");

					getNextValue = true;
					continue;
				}
				

				if (getNextValue && dataSaveInProgress) {
					dataSaveInProgress = false;
					getNextValue = false;
					saveRecord(mnemonic, value, filepath);

				}

			}

		}

	}

	public static void saveRecord(String mnemonic, String value, String filepath) {

		try {
			FileWriter fw = new FileWriter(filepath, true); // append data
			BufferedWriter bw = new BufferedWriter(fw); //
			PrintWriter pw = new PrintWriter(bw);

			pw.println(mnemonic + ',' + value);

			pw.flush();

			pw.close();

		} catch (Exception e) {
			System.out.println("Failed to open the file");
			JOptionPane.showMessageDialog(null, "Failed to open the file. Following error turns up: " + e);
			e.printStackTrace();
		}

	}

	public static String reinitializeFile() {

		String constPrefix = "/csv_file";

		File directory = new File(System.getProperty("user.dir") + constPrefix);

		if (!directory.exists()) {
			directory.mkdir();
		}

		File csvFile = new File(directory.getAbsolutePath() + "/file.csv");

		if (csvFile.exists()) {
			csvFile.delete();
		}

		try {
			csvFile.createNewFile();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to create file. Following error turns up: " + e);
			e.printStackTrace();
		}

		return csvFile.getAbsolutePath();
	}

}
