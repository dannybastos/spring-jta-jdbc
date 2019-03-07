package com.example.spring.jta.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.jta.jdbc.exceptions.OutOfStockException;
import com.example.spring.jta.jdbc.service.SellService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SellServiceTest {

	@Autowired
	private SellService sellService;
	
	@Test
	public void sell_with_stock_ok() {
		sellService.sell(1010l, 10l);
	}

	@Test(expected=OutOfStockException.class)
	public void sell_with_out_of_stock() {
		sellService.sell(1010l, 10000l);
	}

}
