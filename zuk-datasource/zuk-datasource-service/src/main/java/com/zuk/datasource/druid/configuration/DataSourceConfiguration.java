package com.zuk.datasource.druid.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.alibaba.druid.filter.logging.LogFilter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;

@Configuration
// dao的扫描路径
@EnableJpaRepositories(basePackages = "com.zuk")
// 实体的扫描路径
@EntityScan("com.zuk")
// 允许使用缓存
@EnableCaching
public class DataSourceConfiguration {

	@ConditionalOnClass(DruidDataSource.class)
	@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
	protected static class DruidDataSourceConfiguration {

		@Value("${datasource.druid.logger:false}")
		private Boolean enableLogger;

		@ConfigurationProperties(prefix = "dataSource.druid")
		@Bean(name = "dataSource")
		public DruidDataSource dataSource(DataSourceProperties properties) throws Exception {
			DruidDataSource dataSource = (DruidDataSource) properties.initializeDataSourceBuilder()
					.type(DruidDataSource.class).build();
			DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
			String validationQuery = databaseDriver.getValidationQuery();
			if (validationQuery != null) {
				dataSource.setValidationQuery(validationQuery);
			}
			LogFilter logFilter = logFilter();
			if (logFilter != null) {
				dataSource.setProxyFilters(Arrays.asList(logFilter));
			}
			return dataSource;
		}

		private Slf4jLogFilter logFilter() {
			if (enableLogger) {
				Slf4jLogFilter slf4jLogFilter = new Slf4jLogFilter();
				slf4jLogFilter.setResultSetLogEnabled(false);
				slf4jLogFilter.setConnectionLogEnabled(false);
				slf4jLogFilter.setDataSourceLogEnabled(false);
				slf4jLogFilter.setStatementCreateAfterLogEnabled(false);
				slf4jLogFilter.setStatementParameterSetLogEnabled(false);
				slf4jLogFilter.setStatementExecuteQueryAfterLogEnabled(false);
				slf4jLogFilter.setStatementPrepareCallAfterLogEnabled(false);
				slf4jLogFilter.setStatementParameterClearLogEnable(false);
				slf4jLogFilter.setStatementPrepareAfterLogEnabled(false);
				slf4jLogFilter.setStatementExecutableSqlLogEnable(true);
				return slf4jLogFilter;
			}
			return null;
		}
	}

}
