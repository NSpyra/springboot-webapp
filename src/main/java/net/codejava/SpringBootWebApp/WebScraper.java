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
	// This sequence results in csv generation
	public static void main(String[] args) {
		
		String filepath = reinitializeFile();
		Document doc = readWebsite("https://mybank.pl/kursy-walut/kupno-sprzedaz/");
		parseContent(doc, filepath);
		LocalDate currDate = LocalDate.now();
		System.out.print(currDate);

	}
	
	// That is a web scraper
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

	// this part of code walks through the content of the html and extracts the necessary data
	public static void parseContent(Document siteContent, String path) {

		boolean dataSaveInProgress = false;
		boolean getBuyValue = false;
		boolean getSellValue = false;
		boolean buyReady = false;
		boolean sellReady = false;

		String mnemonic = "";
		String valueBuy = "";
		String valueSell = "";
		
		String filepath = reinitializeFile();

		for (Element row : siteContent.select("table.tab_kursy tr")) {

			for (Element td : row.select("td")) {

				if (getBuyValue) {
					getBuyValue = false;
					valueBuy = td.text().replace(',', '.');
					buyReady = true;
					continue;

				}
				if (getSellValue) {
					getSellValue = false;
					valueSell = td.text().replace(',', '.');
					sellReady = true;
					continue;
				}

				if (td.text().contains("1 ") || td.text().contains("100 ")) {
					dataSaveInProgress = true;

					mnemonic = td.text().replace("1 ", "");
					getBuyValue = true;
					getSellValue = true;
					continue;
				}
				

				if (buyReady && sellReady && dataSaveInProgress) {
					dataSaveInProgress = false;
					buyReady = false;
					sellReady = false;
					saveRecord(mnemonic, valueBuy, valueSell, filepath);

				}

			}

		}

	}
	
	// This portion of code adds (appends) pair of values (mnemonic - value) to the file
	public static void saveRecord(String mnemonic, String valueBuy, String valueSell, String filepath) {

		try {
			FileWriter fw = new FileWriter(filepath, true); // append data
			BufferedWriter bw = new BufferedWriter(fw); //
			PrintWriter pw = new PrintWriter(bw);

			pw.println(mnemonic + ',' + valueBuy + "," + valueSell);

			pw.flush();

			pw.close();

		} catch (Exception e) {
			System.out.println("Failed to open the file");
			JOptionPane.showMessageDialog(null, "Failed to open the file. Following error turns up: " + e);
			e.printStackTrace();
		}

	}

	// This method finds/creates the directory and reinitializes (creates a new) csv file
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
