package com.skilldistillery.jet.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.jet.entities.AirField;
import com.skilldistillery.jet.entities.CargoCarrier;
import com.skilldistillery.jet.entities.CargoPlane;
import com.skilldistillery.jet.entities.CombatReady;
import com.skilldistillery.jet.entities.FighterJet;
import com.skilldistillery.jet.entities.Jet;
import com.skilldistillery.jet.entities.PassengerPlane;

public class JetsApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JetsApplication app = new JetsApplication();
		app.menu();

	}

	public List<Jet> getJetsInput(String file) {
		String fileName = file;
		List<Jet> fleetArr = new ArrayList<>();

		try {
			BufferedReader buffread = new BufferedReader(new FileReader(fileName));
			String line = null;
			while ((line = buffread.readLine()) != null) {
				String[] jetParams = line.split(",");
				Double param2 = Double.valueOf(jetParams[2]);
				int param3 = Integer.parseInt(jetParams[3]);
				long param4 = Integer.parseInt(jetParams[4]);
				if (jetParams[0].equalsIgnoreCase("Cargo")) {
					CargoPlane cp = new CargoPlane(jetParams[1], param2, param3, param4);
					fleetArr.add(cp);
				} else if (jetParams[0].equalsIgnoreCase("fighter")) {
					FighterJet fj = new FighterJet(jetParams[1], param2, param3, param4);
					fleetArr.add(fj);
				} else {
					PassengerPlane gj = new PassengerPlane(jetParams[1], param2, param3, param4);
					fleetArr.add(gj);
				}
			}
			buffread.close();
		} catch (FileNotFoundException e) {
			System.err.println(fileName + " File not Found");
			;
		} catch (IOException e) {
			System.err.println("IO Exception");
		}

		return fleetArr;

	}

	public void menu() {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		AirField airfield = new AirField();
		List<Jet> fleetArr = getJetsInput("Jets.txt");
		airfield.setJets(fleetArr);
		while (true) {
			try {
				System.out.println("Please select following options:\n\n");
				System.out.println("**************************************\n");
				System.out.println("1: List fleet");
				System.out.println("2: Fly all jets");
				System.out.println("3: View fastest jet");
				System.out.println("4: View jet with longest range");
				System.out.println("5: Load all the Cargo Jets!");
				System.out.println("6: Dogfight!");
				System.out.println("7: Add a jet to Fleet");
				System.out.println("8: Remove a jet from Fleet");
				System.out.println("9: Quit.");
				System.out.println("**************************************\n");

				choice = input.nextInt();
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("\n\n\t\tPlease enter a valid number");
				Menu();
				continue;
			}
			switch (choice) {
			case 1:
				System.out.println(airfield.getJets());
				Menu();
				continue;
			case 2:
				jetFlight(fleetArr);
				Menu();
				continue;
			case 3:
				fastestJet(fleetArr);
				Menu();
				continue;
			case 4:
				jetRange(fleetArr);
				Menu();
				continue;
			case 5:
				jetCargo(fleetArr);
				Menu();
				continue;
			case 6:
				jetFight(fleetArr);
				Menu();
				continue;
			case 7:
				addJet(fleetArr, input);
				continue;
			case 8:
				removeJet(fleetArr, input);
				continue;
			case 9:
				System.out.println("\nGoodbye!");
				break;
			default:
				System.out.println("Invalid entry. Reselect! ");
				Menu();
				continue;
			}
			input.close();
			break;

		}

	}

	public void addJet(List<Jet> fleetArr, Scanner input) {
		PassengerPlane jetget;
		input.nextLine();
		while (true) {
			try {
				System.out.println("With your level of secret clearance, you are only authorized to add passenger plane. \nEnter jet model name");
				String name = input.nextLine();
				System.out.println("Enter jet maximum speed (MPH)");
				double speed = input.nextDouble();
				System.out.println("Enter maximum range in miles");
				int range = input.nextInt();
				System.out.println("Enter price!");
				long price = input.nextLong();
				jetget = new PassengerPlane(name, speed, range, price);
				break;
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("Invalid entry!");
				continue;
			}
		}

		fleetArr.add(jetget);

	}

	public void fastestJet(List<Jet> fleetArr) {
		Jet fastJet = fleetArr.get(0);
		for (Jet jet : fleetArr) {
			if (jet.getSpeed() > fastJet.getSpeed()) {
				fastJet = jet;

			}

		}
		System.out.println(fastJet.toString());
	}

	public void Menu() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Error!");
			;
		}

	}

	public void jetRange(List<Jet> fleetArr) {
		Jet jetRange = fleetArr.get(0);
		for (Jet jet : fleetArr) {
			if (jet.getRange() > jetRange.getRange()) {
				jetRange = jet;

			}

		}
		System.out.println(jetRange.toString());
	}

	public void jetFlight(List<Jet> fleetArr) {
		for (Jet jet : fleetArr) {
			jet.fly();

		}

	}

	public void jetCargo(List<Jet> fleetArr) {
		int count = 0;
		for (Jet jet : fleetArr) {
			if (jet instanceof CargoCarrier) {
				((CargoCarrier) jet).loadCargo();
				count++;
			}

		}
		if (count == 0) {
			System.out.println("There are no cargo planes in the airfield");
		}

	}

	public void jetFight(List<Jet> fleetArr) {
		int count = 0;
		for (Jet jet : fleetArr) {
			if (jet instanceof CombatReady) {
				((CombatReady) jet).fight();
				count++;
			}
		}
		if (count == 0) {
			System.out.println("No fighter jet!");
		}
	}

	public void removeJet(List<Jet> fleetArr, Scanner input) {
		while (true) {
			try {
				if (fleetArr.size() <= 0) {
					System.out.println("Emptied airfield!");
					Menu();
					break;
				}
				System.out.println("Select hangar number from 1 to " + fleetArr.size() +  ". Enter 0 to cancel removal operation");
				int jetDelete = input.nextInt() - 1;
				if (jetDelete > fleetArr.size() - 1 || jetDelete < -1) {
					System.out.println("Invalid hangar number selected!");
					input.nextLine();
					continue;
				} else if (jetDelete == -1) {
					System.out.println("Main menu...");
					Menu();
					break;
				}
				fleetArr.remove(jetDelete);
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("Enter a whole number!");
				continue;
			}
			break;
		}

	}

}
