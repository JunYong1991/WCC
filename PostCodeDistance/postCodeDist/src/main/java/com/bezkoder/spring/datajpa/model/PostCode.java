package com.bezkoder.spring.datajpa.model;

import jakarta.persistence.*;


public class PostCode {



	private String postCode;
	
	private String postCode2;
	
	private String distance;

	public PostCode() {

	}

	public PostCode( String postCode, String postCode2, String distance) {
		this.postCode = postCode;
		this.postCode2 = postCode2;
		this.distance = distance;
	}
	
	

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostCode2() {
		return postCode2;
	}

	public void setPostCode2(String postCode2) {
		this.postCode2 = postCode2;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "PostCode [postCode=" + postCode + ",postCode2=" + postCode2 + ", distance=" + distance  + "]";
	}

}
