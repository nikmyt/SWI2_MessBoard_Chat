package cz.osu.java.messboardapp.Configs;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SQLiteConfig {

	@Bean(name = "sqliteDataSource")
	@ConfigurationProperties(prefix = "spring.sqlite.datasource")
	public DataSource sqliteDataSource() {
		return new org.sqlite.SQLiteDataSource();
	}

	// Other configurations for SQLite
}
