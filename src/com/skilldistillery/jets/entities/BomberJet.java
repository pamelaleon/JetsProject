package com.skilldistillery.jets.entities;

public class BomberJet extends Jet implements CombatReady{

	private String model; 
	private double speed; 
	private int range; 
	private double price; 
	
	@Override
	public String toString() {
		return "BomberJet [model=" + model + ", speed=" + speed + ", range=" + range + ", price=" + price + "]";
	}

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

	public void fight() {
		System.out.println("Engaging in a dogfight!");
	}
	
	public void fly() {
		System.out.println("I am flying!");
	}
}
