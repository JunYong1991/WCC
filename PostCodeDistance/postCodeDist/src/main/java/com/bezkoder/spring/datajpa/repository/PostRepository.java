package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.Post;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
	Post findByPostCode(String postCode);
}
