package com.liu.springbootdemo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootdemoApplicationTests {

	@Autowired
	private TestRestTemplate template;



	private URL base;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + 8080 + "/");
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void userTestGet(){
		ResponseEntity<String> response = template.getForEntity(base.toString()+"/user/getitem/1",
				String.class);
		System.out.println(response.getBody());
	}

	@Test
	public void userTestPost(){

		// 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
		paramMap.add("name", "项羽");
		paramMap.add("age", "39");
		paramMap.add("address", "江南楚江边");
		paramMap.add("personinfo", "这是一个王者级别的人呢");

		// 1、使用postForObject请求接口
		String result = template.postForObject(base.toString()+"/user/add", paramMap, String.class);
		System.out.println("result1==================" + result);

		// 2、使用postForEntity请求接口--与postforobject区别是可以带header
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap,headers);
		ResponseEntity<String> response2 = template.postForEntity(base.toString()+"/user/add", httpEntity, String.class);
		System.out.println("result2====================" + response2.getBody());

		// 3、使用exchange请求接口
		ResponseEntity<String> response3 = template.exchange(base.toString()+"/user/add", HttpMethod.POST, httpEntity, String.class);
		System.out.println("result3====================" + response3.getBody());


	}


}
