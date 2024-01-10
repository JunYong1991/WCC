package com.bezkoder.spring.datajpa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "postcodelatlng")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "postcode")
	private String postCode;
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "longitude")
	private String longitude;
	
	

	public Post() {

	}

	public Post( String postCode, String latitude, String longitude) {
		this.postCode = postCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public long getId() {
		return id;
	}
	
	

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", postCode=" + postCode + ", longitude=" + longitude  + "]";
	}

}
