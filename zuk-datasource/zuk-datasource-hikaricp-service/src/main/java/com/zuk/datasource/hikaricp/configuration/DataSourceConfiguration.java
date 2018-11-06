package com.zuk.datasource.hikaricp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// 实体的扫描路径
@EntityScan("com.zuk")
// dao的扫描路径
@EnableJpaRepositories(basePackages = "com.zuk")
@Configuration
public class DataSourceConfiguration {

	@Value("${spring.datasource.url}")
	private String dataSourceUrl;

	@Value("${spring.datasource.username}")
	private String user;

	@Value("${spring.datasource.password}")
	private String password;

	@ConfigurationProperties(prefix = "hikari.primary")
	@Bean
	public HikariDataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(dataSourceUrl); // 数据源
		hikariConfig.setUsername(user); // 用户名
		hikariConfig.setPassword(password); // 密码
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true"); // 是否自定义配置，为true时下面两个参数才生效
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250"); // 连接池大小默认25，官方推荐250-500
		hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); // 单条语句最大长度默认256，官方推荐2048
		hikariConfig.addDataSourceProperty("useServerPrepStmts", "true"); // 新版本MySQL支持服务器端准备，开启能够得到显著性能提升
		hikariConfig.addDataSourceProperty("useLocalSessionState", "true");
		hikariConfig.addDataSourceProperty("useLocalTransactionState", "true");
		hikariConfig.addDataSourceProperty("rewriteBatchedStatements", "true");
		hikariConfig.addDataSourceProperty("cacheResultSetMetadata", "true");
		hikariConfig.addDataSourceProperty("cacheServerConfiguration", "true");
		hikariConfig.addDataSourceProperty("elideSetAutoCommits", "true");
		hikariConfig.addDataSourceProperty("maintainTimeStats", "false");

		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		return hikariDataSource;
	}

}
