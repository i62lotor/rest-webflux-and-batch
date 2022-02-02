package com.rltsistemas.rxlotteryexample.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa una recompensa.
 * @author rltorres
 */
public class Reward {

	private int value;
	private String coinage;


	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Reward(@JsonProperty("value") @NotNull int value,
			@JsonProperty("coinage") @NotNull String coinage) {
		this.value = value;
		this.coinage = coinage;
	}

	public int getValue() {
		return value;
	}
	
	public String getCoinage() {
		return coinage;
	}

	@Override
	public String toString() {
		return "Reward [value=" + value + ", coinage=" + coinage + "]";
	}

}
