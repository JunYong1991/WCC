package com.bezkoder.spring.datajpa.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bezkoder.spring.datajpa.model.Post;
import com.bezkoder.spring.datajpa.model.PostCode;
import com.bezkoder.spring.datajpa.repository.PostRepository;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostRepository postRepository;

	@GetMapping("/post")
	public ResponseEntity<PostCode> getAllBook(@RequestBody PostCode post) {
		try {
			double EARTH_RADIUS = 6371; // radius in kilometer
			String distance ="";
			Logger logger = LoggerFactory.getLogger(PostController.class);
			
			//List<Post> post3
			Post post2 = postRepository.findByPostCode(post.getPostCode());
			Post post3 = postRepository.findByPostCode(post.getPostCode2());
			
			logger.info("Post Code 1 : "+post.getPostCode());
			logger.info("Post Code 2 : "+post.getPostCode2());
			
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
			post.setDistance(distance+"km");
			
			return new ResponseEntity<>(post, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/post/{postCode}")
	public ResponseEntity<Post> updateBook(@PathVariable("postCode") String postCode, @RequestBody Post post) {
		
		Post post2 = postRepository.findByPostCode(postCode);
		Optional<Post> postData = postRepository.findById(post2.getId());

		if (postData.isPresent()) {
			Post _post = postData.get();
			_post.setLatitude(post.getLatitude());
			_post.setLongitude(post.getLongitude());
			
			return new ResponseEntity<>(postRepository.save(_post), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	private double square(double x) {
		return x * x;
	}

	private double haversine(double deg1, double deg2) {
		return square(Math.sin((deg1 - deg2) / 2.0));
	}

}
