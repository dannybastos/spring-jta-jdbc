package com.example.spring.jta.jdbc;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosXADataSourceWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class Config {

	@Bean("jdbcTemplateInvoice")
	public JdbcTemplate getJDBCTemplateAccount(@Qualifier("dataSourceInvoice") DataSource dataSource) throws Exception {
		return new JdbcTemplate(dataSource);
	}
	@Bean("dataSourceInvoice")
	public DataSource createDataSourceAccount() throws Exception {
		return this.createDataSource("jdbc:h2:mem:InvoiceDB");
	}

	@Bean("jdbcTemplateStock")
	public JdbcTemplate getJDBCTemplateAudit(@Qualifier("dataSourceStock") DataSource dataSource) throws Exception {
		return new JdbcTemplate(dataSource);
	}
	@Bean("dataSourceStock")
	public DataSource createDataSourceAudit() throws Exception {
		return this.createDataSource("jdbc:h2:mem:StockDB");
	}

	public DataSource createDataSource(String url) throws Exception {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(url);
		dataSource.setUser("sa");
        AtomikosXADataSourceWrapper wrapper = new AtomikosXADataSourceWrapper();
        return wrapper.wrapDataSource(dataSource);
	}

}
