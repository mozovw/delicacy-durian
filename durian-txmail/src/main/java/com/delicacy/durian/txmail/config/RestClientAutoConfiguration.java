package com.delicacy.durian.txmail.config;

import com.delicacy.durian.txmail.util.RestClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;



/**
 * 工具类引导装配类
 * @author yangzhilong
 *
 */
@Configuration
public class RestClientAutoConfiguration {
	@Value("${rest.config.connectTimeout:10000}")
	private int connectTimeout;
	@Value("${rest.config.readTimeout:30000}")
	private int readTimeout;
	
	/**
	 * 使用Bootstrap来装配RestClient中的RestTemplate属性，
	 * 避免直接装配RestTemplate来污染了正常的spring Cloud的调用
	 * @return
	 */
	@Bean
    public RestClientBootstrap restClientBootstrap(){
        //HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(connectTimeout);
        httpRequestFactory.setReadTimeout(readTimeout);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        RestClientUtil.setRestTemplate(restTemplate);
        return new RestClientBootstrap();
    }
	
	/**
	 * 空的引导类
	 * @author yangzhilong
	 *
	 */
	static class RestClientBootstrap {
		
	}
}
