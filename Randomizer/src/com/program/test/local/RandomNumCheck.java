package com.program.test.local;

import java.io.Serializable;

public class RandomNumCheck implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer randomNum;
	private Boolean primeChk;
	
	public RandomNumCheck(Integer randomNum) {
		this.randomNum = randomNum;
		this.primeChk = null;
	}
	public Integer getRandomNum() {
		return randomNum;
	}
	public void setRandomNum(Integer randomNum) {
		this.randomNum = randomNum;
	}
	public Boolean getPrimeChk() {
		return primeChk;
	}
	public void setPrimeChk(Boolean primeChk) {
		this.primeChk = primeChk;
	}
}
