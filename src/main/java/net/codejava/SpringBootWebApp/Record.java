package net.codejava.SpringBootWebApp;

public class Record {
	// Definitions
	private String name;
	private String valueBuy;
	private String valueSell;
	
	// Setters and getters, these were added manually because I have forgotten the shortcut
	public Record(String name, String valueBuy, String valueSell) {
		super();
		this.name = name;
		this.valueBuy = valueBuy.replace('.', ',');
		this.valueSell = valueSell.replace('.', ',');
	}
	
	public String getValueBuy() {
		return valueBuy;
	}

	public void setValueBuy(String valueBuy) {
		this.valueBuy = valueBuy;
	}

	public String getValueSell() {
		return valueSell;
	}

	public void setValueSell(String valueSell) {
		this.valueSell = valueSell;
	}

	public String getName() {
		return name;
	}

}
