package com.liu.springbootdemo;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;


@SpringBootApplication()
public class SpringbootdemoApplication {

	@Value("${multipart.maxFileSize}")
	private String multipart_maxFileSize;
	@Value("${multipart.maxRequestSize}")
	private String multipart_maxRequestSize;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

	/**
	 * 用来设置单个上传文件和总上传文件的大小，默认是1M
	 * 会导致传的文件过大就会报错误
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 单个数据大小
		factory.setMaxFileSize(multipart_maxFileSize); // KB,MB
		/// 总上传数据大小
		factory.setMaxRequestSize(multipart_maxRequestSize);
		return factory.createMultipartConfig();
	}
}
