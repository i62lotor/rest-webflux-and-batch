package com.rltsistemas.rxlotteryexample.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.rltsistemas.rxlotteryexample.model.Award.Builder;

/**
 * Representa un premio
 * @author rltorres
 */
@JsonDeserialize(builder = Builder.class)
public class Award {

	private int number;
	private Reward reward;
	private LocalDateTime date;

	
	@JsonPOJOBuilder	
	public static class Builder {
	
		private int number;		
		private Reward reward;
		private LocalDateTime date;
		
		
		public Builder withNumber(int number) {
	        this.number = number;
	        return this;
	    }
	    
	    public Builder withReward(Reward reward) {
	        this.reward = reward;
	        return this;
	    }
	    
	    public Builder withDate(LocalDateTime date) {
	    	this.date = date;
	    	return this;
	    }
	    
	    public Award build() {
	        return new Award(this.number,
	        		this.reward,
	        		this.date == null ? LocalDateTime.now(): this.date);
	        
	    } 
	}
	
	private Award(@NotNull int number, @NotNull Reward reward,
			@NotNull LocalDateTime date ) {
		this.number = number;
		this.reward = reward;
		this.date = date;
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
