package com.example.spring.jta.jdbc.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.jta.jdbc.exceptions.OutOfStockException;

@Service
public class SellService {

	private StockService stockService;
	private InvoiceService invoiceService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public SellService(StockService stockService, InvoiceService invoiceService) {
		this.stockService = stockService;
		this.invoiceService = invoiceService;
	}
	
	
	@Transactional
	public void sell(Long productId, Long qty) {
		stockService.update(productId, qty);
		log.info("stock ok");
		invoiceService.generateInvoice(productId, qty);
		log.info("invoice ok");
		if (stockService.stockOf(productId).compareTo(0l) < 0 ) {
			throw new OutOfStockException("Out of stock");
		}
	}
}
