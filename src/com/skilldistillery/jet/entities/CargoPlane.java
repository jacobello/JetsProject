package com.skilldistillery.jet.entities;

public class CargoPlane extends Jet implements CargoCarrier {

	public CargoPlane(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void loadCargo() {
		System.out.println(this.toString() + "Loading!");
	}

	@Override
	public void fly() {
		System.out.printf(this.getClass().getSimpleName() + " Model: " + getModel() + ", Speed: " + getSpeed()
				+ "mph, Max range: " + getRange() + ", Price: $" + getPrice()
				+ " Flight Time: %.2f hours, Mach= %.2f  \n", flightTime(), machSpeed());
	
	}

	public String toString() {
		return "\n" + getModel() + ", Speed: " + getSpeed() + "mph, Max range: " + getRange() + ", Price: $"
				+ getPrice() + ", Class: " + getClass().getSimpleName() + "\n";
	}
}
