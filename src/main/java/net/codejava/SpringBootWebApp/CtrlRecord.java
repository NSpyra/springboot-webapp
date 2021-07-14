package net.codejava.SpringBootWebApp;

import java.util.List;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CtrlRecord {

	public List<Record> getRecord() {
		List<Record> listRecord = new ArrayList<>();
		String path = findFile();
		listRecord = readFileAndAppendList(path, listRecord);

		return listRecord;
	}

	public String findFile() {

		File csvFile = new File(System.getProperty("user.dir") + "/csv_file/file.csv");

		if (!csvFile.exists()) {
			JOptionPane.showMessageDialog(null, "Directory " + csvFile + " not found!");
		}

		return csvFile.getAbsolutePath();
	}

	public List<Record> readFileAndAppendList(String directory, List<Record> list) {
		String file = directory;
		BufferedReader reader = null;
		String line = "";

		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				list.add(new Record(row[0], row[1]));

			}

		} catch (Exception e) {
			System.out.println("Failed to read the file");
			JOptionPane.showMessageDialog(null, "Failed to read the file. Following error turns up: " + e);
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.out.println("Failed to close the file");
				JOptionPane.showMessageDialog(null, "Failed to close the file. Following error turns up: " + e);
				e.printStackTrace();
			}
		}
		return list;
	}
}
