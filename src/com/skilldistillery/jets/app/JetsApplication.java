package com.skilldistillery.jets.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.skilldistillery.jets.entities.AirField;
import com.skilldistillery.jets.entities.BatwingJet;
import com.skilldistillery.jets.entities.BomberJet;
import com.skilldistillery.jets.entities.CargoJet;
import com.skilldistillery.jets.entities.IronJet;
import com.skilldistillery.jets.entities.Jet;
import com.skilldistillery.jets.entities.ThorJet;

public class JetsApplication {

	public static void main(String[] args) {

		AirField airfield = new AirField();
		JetsApplication run = new JetsApplication();

		airfield.setFleet(run.populateAirfield());

		boolean jetMenu = true;

		Scanner input = new Scanner(System.in);
		while (jetMenu != false) {
			System.out.println("----------------------------");
			System.out.println("Welcome to the Jets Menu!!");
			System.out.println("1. List Fleet");
			System.out.println("2. Fly all jets");
			System.out.println("3. View fastest jet");
			System.out.println("4. View jet with longest range");
			System.out.println("5. Load all cargo jets");
			System.out.println("6. Dogfight!");
			System.out.println("7. Add a jet to the fleet");
			System.out.println("8. Remove a jet from the fleet");
			System.out.println("9. Quit");
			int userInput = input.nextInt();

			switch (userInput) {
			case 1:
				System.out.println(airfield.getFleet());
				break;
			case 2:
				for (Jet jet : airfield.getFleet()) {
					jet.fly();
				}
				break;
			case 3:
				System.out.println(run.getFastestJet(airfield.getFleet()));
				break;
			case 4:
				System.out.println(run.getLongestRange(airfield.getFleet()));
				break;
			case 5:
				for (Jet jet : airfield.getFleet()) {
					if (jet instanceof CargoJet) {
						CargoJet newJet = (CargoJet) jet;
						newJet.loadCargo();
					}
				}
				break;
			case 6:
				for (Jet jet : airfield.getFleet()) {
					if (jet instanceof BomberJet) {
						BomberJet newJet = (BomberJet) jet;
						newJet.fight();
					}
				}
				break;
			case 7:
				System.out.println("What type of jet should this jet be? ");
				System.out.println("Write out the following Jet name you would like to add!");
				System.out.println("BatwingJet " + " \t " + "IronJet" + "\t" + "BomberJet" + "\t" + "ThorJet");
				String type = input.next();
				Jet jet = run.chooseJet(type);
				System.out.println("What model should this jet be? ");
				String model = input.next();
				System.out.println("What speed should this jet be? ");
				double speed = input.nextDouble();
				System.out.println("What range should this jet be? ");
				int range = input.nextInt();
				System.out.println("How much should this jet be? ");
				double price = input.nextDouble();
				jet.setModel(model);
				jet.setSpeed(speed);
				jet.setRange(range);
				jet.setPrice(price);

				ArrayList<Jet> jets = airfield.getFleet();
				jets.add(jet);
				airfield.setFleet(jets);
				break;

			case 8:
				ArrayList<Jet> jets1 = airfield.getFleet();
				System.out.println("Which jet would you like to delete?");
				
				for (int i = 0; i < jets1.size(); i++) {
					System.out.println(i + ": " + jets1.get(i));
				}
				int choice = input.nextInt();
				if (choice >= jets1.size() || choice < 0 ) {
					System.out.println("Invalid option");
				}
				else {
					jets1.remove(jets1.get(choice));
					airfield.setFleet(jets1);
				}
				break;
			case 9:
				System.out.println("Thanks for using the Jet App!");
				jetMenu = false;
				break;

			default:
				System.out.println("Please input valid entry!");
				break;
			}
		}
		input.close();
	}

	public Jet getFastestJet(ArrayList<Jet> jets) {
		Jet fastestJet = jets.get(0);
		for (Jet jet : jets) {
			if (jet != null) {
				double speed = jet.getSpeed();
				if (speed > fastestJet.getSpeed()) {
					fastestJet = jet;
				}
			}
		}
		return fastestJet;
	}

	public Jet getLongestRange(ArrayList<Jet> jets) {
		Jet longestR = jets.get(3);
		for (Jet jet : jets) {
			if (jet != null) {
				double range = jet.getRange();
				if (range > longestR.getRange()) {
					longestR = jet;
				}
			}
		}
		return longestR;
	}

	public ArrayList<Jet> populateAirfield() {

		ArrayList<Jet> listOfJets = new ArrayList<>();
		try (BufferedReader bufIn = new BufferedReader(new FileReader("jets.txt"))) {
			String line;
			JetsApplication run = new JetsApplication();
			while ((line = bufIn.readLine()) != null) {
				String[] jetSplit = line.split(",");
				Jet jet = run.chooseJet(jetSplit[0]);
				jet.setModel(jetSplit[1]);
				jet.setSpeed(Double.parseDouble(jetSplit[2]));
				jet.setRange(Integer.parseInt(jetSplit[3]));
				jet.setPrice(Double.parseDouble(jetSplit[4]));

				listOfJets.add(jet);

			}
			bufIn.close();
		} catch (IOException e) {
			System.err.println(e);
		}
		return listOfJets;

	}
	
	public Jet chooseJet(String jetType) {
		if (jetType.equals("BatwingJet")) {
			return new BatwingJet();
		} else if (jetType.equals("BomberJet")) {
			return new BomberJet();
		} else if (jetType.equals("CargoJet")) {
			return new CargoJet();
		} else if (jetType.equals("IronJet")) {
			return new IronJet();
		} else if (jetType.equals("ThorJet")) {
			return new ThorJet();
		}
		return null;
	}

}
// casting, instanceof