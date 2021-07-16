package net.codejava.SpringBootWebApp;

public class Record {
	// Definitions
	private String name;
	private String value;
	
	// Setters and getters, these were added manually because I have forgotten the shortcut
	public Record(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
