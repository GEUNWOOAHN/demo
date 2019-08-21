package com.example.demo.service;

import com.example.demo.vo.UrlShortenerVO;

public interface UrlShortenerService {
	
	public UrlShortenerVO getShortenedOrgUrlKey(String url);
	public String doShorteningUrl(String url);
	public void insertShorteningUrl(UrlShortenerVO vo);
	UrlShortenerVO getShortenedDestUrlKey(String url);
	
}
