package com.example.spring.jta.jdbc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

@Service
public class StockService {
	
	private JdbcTemplate template;
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public StockService(@Qualifier("jdbcTemplateStock") JdbcTemplate template) {
		this.template = template;
	}
	
	public void update(Long productId, Long qty) {
		template.update("update stock set stock_qty=stock_qty-? where product_id=?", qty, productId);
		log.info(String.format("updated stock product_id : %d => qty : %d", productId, qty));		
	}
	
	public Long stockOf(Long productId) {
		String sql = "select stock_qty from stock where product_id=?";
		Object[] args = {productId};
		return template.query(sql, args, (ResultSetExtractor<Long>)(rs) -> {
			rs.next();
			return rs.getLong(1);
		});
	}
}
