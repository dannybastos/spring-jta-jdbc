package com.example.spring.jta.jdbc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

	private JdbcTemplate template;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public InvoiceService(@Qualifier("jdbcTemplateInvoice") JdbcTemplate template) {
		this.template = template;
	}
	
	public void generateInvoice(Long productId, Long qty) {
		template.update("insert into invoice (product_id, qty) values (?,?)", productId, qty);
		log.info(String.format("invoice created. product: %s, qty:%d", productId, qty));
	}
	
}
