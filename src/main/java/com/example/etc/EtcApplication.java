package com.example.etc;

import com.example.etc.helper.PersistenceConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
@Import({PersistenceConfig.class})
public class EtcApplication {

	public static void main(String[] args) {
		System.setProperty("liquibase.databaseChangeLogTableName", "DATABASECHANGELOG");
		System.setProperty("liquibase.databaseChangeLogLockTableName", "DATABASECHANGELOGLOCK");
		SpringApplication.run(EtcApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() throws Exception {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		requestFactory.setConnectTimeout(20000); // 20 seconds
		requestFactory.setReadTimeout(20000); // 20 seconds
		return new RestTemplate(requestFactory);
	}

	@Bean
	public VelocityEngine velocityEngine() throws VelocityException, IOException {
		VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
		Properties props = new Properties();
//		props.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
//		props.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		props.setProperty(RuntimeConstants.RESOURCE_LOADER, "class,file");
		props.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
		props.setProperty("runtime.log.logsystem.log4j.logger", "VELLOGGER");
		props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		props.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
		factory.setVelocityProperties(props);
		return factory.createVelocityEngine();
	}

}
