package no.tusla.orderservice;

import lombok.Data;

@Data
public class Mobile {
	    private Integer id;
	 	private String company;
		private String model;
		private int price;
		private int ramInGb;
		private int batteryCapacityInMilliAmp;
		private String operatingSystem;
		private int stocks;

}
