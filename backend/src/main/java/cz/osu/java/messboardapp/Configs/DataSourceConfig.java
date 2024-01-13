package cz.osu.java.messboardapp.Configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

	@Bean(name = "mariadbDataSource")
	public DataSource mariadbDataSource() {
		// Configure MariaDB DataSource directly, replace with your actual configurations
		return DataSourceBuilder.create()
				.driverClassName("org.mariadb.jdbc.Driver")
				.url("jdbc:mariadb://localhost:3306/messBoard")
				.username("root")
				.password("")
				.build();
	}

	@Bean
	public DynamicRoutingDataSource dynamicRoutingDataSource(@Qualifier("mariadbDataSource") DataSource mariadbDataSource,
															 @Qualifier("sqliteDataSource") DataSource sqliteDataSource) {
		DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
		dynamicRoutingDataSource.setDefaultTargetDataSource(mariadbDataSource);

		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put("mariadb", mariadbDataSource);
		targetDataSources.put("sqlite", sqliteDataSource);

		dynamicRoutingDataSource.setTargetDataSources(targetDataSources);

		return dynamicRoutingDataSource;
	}

	// Define your sqliteDataSource() method here as before
	private DataSource sqliteDataSource() {
		// Implement your SQLite DataSource creation logic
		return new org.sqlite.SQLiteDataSource();
	}
}