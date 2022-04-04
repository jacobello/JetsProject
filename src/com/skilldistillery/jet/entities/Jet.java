package com.skilldistillery.jet.entities;

public abstract class Jet {
	private String model;
	private double speed;
	private int range;
	private long price;
	private static double mach = 767.269;

	public Jet(String model, double speed, int range, long price) {
		super();
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
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

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public double machSpeed() {
		return (this.getSpeed() / getMach());

	}

	public double flightTime() {
		return (this.getRange() / this.getSpeed());

	}

	public static double getMach() {
		return mach;
	}

	public abstract void fly();

	public void loadCargo() {
		// TODO Auto-generated method stub
		
	}

}
