package cz.osu.java.messboardapp.Configs;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource implements DynamicDataSource {

	private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<>();

	@Override
	protected Object determineCurrentLookupKey() {
		return dataSourceKey.get();
	}

	@Override
	public void setDataSource(String key) {
		dataSourceKey.set(key);
	}

	public static void clearDataSource() {
		dataSourceKey.remove();
	}
}