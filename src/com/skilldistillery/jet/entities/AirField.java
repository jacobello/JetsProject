package com.skilldistillery.jet.entities;

import java.util.ArrayList;
import java.util.List;

public class AirField {
	private List<Jet> jets;
	
	public AirField() {
		jets = new ArrayList<>();
	}

	public List<Jet> getJets() {
		return jets;
	}

	public void setJets(List<Jet> jets) {
		this.jets = jets;
	}

	public void setJet(Jet jet) {
		jets.add(jet);
	}

	@Override
	public String toString() {
		return "AirField jets: " + jets;
	}

}

