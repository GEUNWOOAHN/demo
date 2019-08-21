package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.UrlShortenerVO;

@Repository
public interface UrlShortenerRepo extends JpaRepository<UrlShortenerVO, Long> {

	UrlShortenerVO findByOrgUrl(String url);
	UrlShortenerVO findByDestUrl(String url);
	
}
