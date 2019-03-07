package com.example.spring.jta.jdbc;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

@Component
public class StartDB {

	public StartDB(@Qualifier("jdbcTemplateStock") JdbcTemplate templateStock,
				   @Qualifier("jdbcTemplateInvoice") JdbcTemplate templateInvoice) throws ScriptException, SQLException {

		Resource resource = new DefaultResourceLoader().getResource("classpath:invoice.sql");
		ScriptUtils.executeSqlScript(templateInvoice.getDataSource().getConnection(), resource);
		
		resource = new DefaultResourceLoader().getResource("classpath:stock.sql");
		ScriptUtils.executeSqlScript(templateStock.getDataSource().getConnection(), resource);
	}

}
