package com.skilldistillery.jets.entities;

public abstract class Jet {

	private String model; 
	private double speed; 
	private int range; 
	private double price; 
	
	
	
	public abstract void fly();
	
//	public void fly() {
//		System.out.println("Flying!!!");
//	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}




}
