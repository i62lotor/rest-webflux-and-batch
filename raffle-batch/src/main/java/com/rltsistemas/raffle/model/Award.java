package com.rltsistemas.raffle.model;

import java.time.LocalDateTime;

public class Award {

	private int number;
	private Reward reward;	
	private LocalDateTime date;

	public Award(int number, Reward reward) {
		super();
		this.number = number;
		this.reward = reward;
	}

	public int getNumber() {
		return number;
	}

	public Reward getReward() {
		return reward;
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Award other = (Award) obj;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Award [number=" + number + ", reward=" + reward + ", date=" + date + "]";
	}

}
