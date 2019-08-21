package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.UrlShortenerRepo;
import com.example.demo.vo.UrlShortenerVO;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UrlShortenerTest {
	@Autowired
	private UrlShortenerRepo repo;

	@Test
	public void test() {
		
		//1. 임의 값을 세팅한다.
		UrlShortenerVO kakaoPay = new UrlShortenerVO();
		kakaoPay.setOrgUrl("https://www.kakaopay.com/");
		kakaoPay.setDestUrl("AqqwDoOa");
		repo.save(kakaoPay);
		
		UrlShortenerVO kakao = new UrlShortenerVO();
		kakao.setOrgUrl("https://www.kakao.com/");
		kakao.setDestUrl("apoiJlas");
		repo.save(kakao);
		
		//2. repo의 저장된 데이터를 조회한다.
		List<UrlShortenerVO> vos = repo.findAll();
		
		//Unit Test Final
		assertEquals(2, vos.size());
	}
}