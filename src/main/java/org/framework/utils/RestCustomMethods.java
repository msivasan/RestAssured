package org.framework.utils;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import ru.yandex.qatools.allure.annotations.Step;

import java.io.PrintWriter;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestCustomMethods {

	
	public   String getRequestTestURLs(String [] url,int status){
		String s = "";
	
		for(int i=0;i<url.length;i++)
		{
			int tempStatus  = getStatusCode(url[i]);
			if( tempStatus != status){
				s += url[i] + " failed  with status " +  tempStatus + "\n"; 
			}
		}
		return s;
	}
	
	public   int getStatusCode(String url){
		 Response res = RestAssured.when().get(url);
		 return res.statusCode();
	}
	
	public   Response postwithoutaccesstoken(String URL, String requestbody) {
		Response response = 
				RestAssured.given()
	                .body(requestbody)
	                .header("Accept", "application/hal+json")
	                .header("Content-Type", "application/hal+json")
	                .post(URL);
	        return response;
	}


	public void storeResponseLocally(Response response,String fileName)  throws Exception{
	
		PrintWriter writer = new PrintWriter("src/test/resources/responses/"+fileName , "UTF-8");
		writer.println(response.asString());
		writer.close();
	}

	@Step("verify status code to be {1}")
	public void verifyStatusCode(int actualStatusCode, int expectedStatusCode) {
		assertThat(actualStatusCode,equalTo(expectedStatusCode));
		
	}
}
