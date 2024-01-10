package com.bezkoder.spring.datajpa;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bezkoder.spring.datajpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import com.bezkoder.spring.datajpa.model.Post;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class testJunit {
	
	@Autowired
	PostRepository postRepository;
	
    @Test
	public void TestJunit(){
    	double EARTH_RADIUS = 6371; // radius in kilometer
		String distance ="";
		 
		Post post2 = postRepository.findByPostCode("AB10 1XG");
		Post post3 = postRepository.findByPostCode("AB10 6RN");
		
		double lon1Radians = Math.toRadians(Double.parseDouble(post2.getLongitude()));
		double lon2Radians = Math.toRadians(Double.parseDouble(post3.getLongitude()));
		double lat1Radians = Math.toRadians(Double.parseDouble(post2.getLatitude()));
		double lat2Radians = Math.toRadians(Double.parseDouble(post3.getLatitude()));
		double a = haversine(lat1Radians, lat2Radians)
				+ Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		
		BigDecimal bd = new BigDecimal(EARTH_RADIUS * c).setScale(2, RoundingMode.HALF_UP);
		double newNum = bd.doubleValue();
		distance = String.valueOf(newNum);
		System.out.println("Distance : "+distance+"km");
	}
    
    private double square(double x) {
		return x * x;
	}

	private double haversine(double deg1, double deg2) {
		return square(Math.sin((deg1 - deg2) / 2.0));
	}

}
