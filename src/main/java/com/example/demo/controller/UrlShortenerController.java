package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.UrlShortenerRepo;
import com.example.demo.service.UrlShortenerService;
import com.example.demo.vo.UrlShortenerVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UrlShortenerController {
	
	@Autowired
	UrlShortenerRepo repo;
	
	private final UrlValidator urlValidator;
	private final UrlShortenerService urlShortenerService;
	private static final String HOST = "http://localhost:8080/";
	
	private static final Logger logger = LoggerFactory.getLogger(UrlShortenerController.class);
	
	@Autowired
    public UrlShortenerController(@Qualifier("serviceImpl") UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
        this.urlValidator = new UrlValidator();
    }
	
	@RequestMapping(value="/urlShortener/main")
	public String main() throws Exception{
	    return "main";
	}
	
    @RequestMapping(value="/urlShortener/create")
    public String create(@RequestParam("targetUrl") String url, Map<String, String> model) {
    	
    	System.out.println("URL 유효성 체크 테스트(1)" + urlValidator.isValid("https://foo.bar.com/")); // TRUE
    	System.out.println("URL 유효성 체크 테스트(2)" + urlValidator.isValid("http://localhost/") ); // FALSE
    	System.out.println("URL 유효성 체크 테스트(3)" + urlValidator.isValid("ldap://foo.bar.com/")); // FALSE
    	
    	UrlShortenerVO shortener = new UrlShortenerVO();
    	//1. 입력한 URL 유효성 체크한다.
		/*if (!urlValidator.isValid(url)) {
			logger.info("msg: {}, targetUrl : {}", "유효하지 않은 URL 요청", url);
			model.put("isValidUrl", "잘못된 URL입니다. 다시 입력해주세요");
			return "main";
		}*/
    	
		//2. 입력한 URL이 TRUE라면 h2 File에서 url 정보가 있는지 체크한다.
    	UrlShortenerVO oldOrgUrl = urlShortenerService.getShortenedOrgUrlKey(url);
    	UrlShortenerVO oldDestUrl = urlShortenerService.getShortenedDestUrlKey(url);
    	
    	//3. URL 입력 시, 기존 등록된 정보가 있다면 Redirect한다.
    	if(oldOrgUrl != null){
    		logger.info("msg: {}", "::::::::::: EXIST oldOrgUrl VALUE :::::::::::");
    		model.put("returnUrl", HOST + oldOrgUrl.getDestUrl());
    		return "main";
    	}else if(oldDestUrl != null){
    		logger.info("msg: {}", "::::::::::: EXIST oldDestUrl VALUE :::::::::::");
    		model.put("returnUrl", oldDestUrl.getOrgUrl());
    		return "main";
    	}else{
    		//4. 8자리 문자열을 임의로 추출한 후 값을 DB의 저장한다.
        	String ranCharUrl = urlShortenerService.doShorteningUrl(url);
        	shortener.setDestUrl(ranCharUrl);
        	shortener.setOrgUrl(url);
        	
        	urlShortenerService.insertShorteningUrl(shortener); //DB저장
        	
        	logger.info("msg: {}", shortener);
        	model.put("returnUrl", HOST + shortener.getDestUrl());
            return "main";
    	}
    }

	@RequestMapping("/urlShortener/info")
    public @ResponseBody List<UrlShortenerVO> get() throws Exception{
    	return repo.findAll();
    }
	
}
