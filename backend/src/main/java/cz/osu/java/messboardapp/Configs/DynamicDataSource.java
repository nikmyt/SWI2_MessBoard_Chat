package cz.osu.java.messboardapp.Configs;

import javax.sql.DataSource;

public interface DynamicDataSource extends DataSource {
	void setDataSource(String dataSourceKey);
}