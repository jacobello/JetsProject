package com.skilldistillery.jet.entities;

public class FighterJet extends Jet implements CombatReady  {
	
	public FighterJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fight() {
		System.out.println(this.toString() + "FIRE IN THE HOLE \n");

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
