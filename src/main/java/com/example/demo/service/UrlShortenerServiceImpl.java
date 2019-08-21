package com.example.demo.service;

import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UrlShortenerRepo;
import com.example.demo.vo.UrlShortenerVO;

@Service
@Qualifier("serviceImpl")
public class UrlShortenerServiceImpl implements UrlShortenerService{
	
	private final UrlShortenerRepo urlShortenerRepo;
	private String lastString = ""; //값 초기화
	
	@Autowired
    public UrlShortenerServiceImpl(UrlShortenerRepo urlShortenerRepo) {
        this.urlShortenerRepo = urlShortenerRepo;
    }
	
	@Override
	public UrlShortenerVO getShortenedOrgUrlKey(String url) {
		
		//1. 입력한 URL의 정보가 있는지 확인한다.(ORG URL)
		UrlShortenerVO shortener = urlShortenerRepo.findByOrgUrl(url);
		return shortener;
		
	}
	
	@Override
	public UrlShortenerVO getShortenedDestUrlKey(String url) {
		
		int indexNumber = url.lastIndexOf('/');
		lastString = url.substring(indexNumber + 1);
		
		UrlShortenerVO tempVO = new UrlShortenerVO();
		tempVO.setDestUrl(lastString);
		
		//1. 입력한 URL의 정보가 있는지 확인한다.(DEST URL)
		UrlShortenerVO shortener = urlShortenerRepo.findByDestUrl(tempVO.getDestUrl());
		return shortener;
	}
	
	@Override
	public String doShorteningUrl(String url) {

		// 1.'/'의 마지막 문자를 출력한다. ex)http://www.kakao.com/url
		int indexNumber = url.lastIndexOf('/');
		lastString = url.substring(indexNumber + 1);// url

		// 2. 랜덤으로 8글자로 변경한다.
		String tempPw = "";
		for (int i = 0; i < 8; i++) {
			int rndVal = (int) (Math.random() * 62);
			if (rndVal < 10) {
				tempPw += rndVal;
			} else if (rndVal > 35) {
				tempPw += (char) (rndVal + 61);
			} else {
				tempPw += (char) (rndVal + 55);
			}
		}
		lastString = tempPw;

		return lastString;
	}
	
	@Override
	public void insertShorteningUrl(UrlShortenerVO vo){
		urlShortenerRepo.save(vo);
	}
	
}
