package com.rltsistemas.raffle.model;

public class Reward {

	private int value;
	private String coinage;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getCoinage() {
		return coinage;
	}

	public void setCoinage(String coinage) {
		this.coinage = coinage;
	}

	@Override
	public String toString() {
		return "Reward [value=" + value + ", coinage=" + coinage + "]";
	}

}
