package org.projectName.operations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.framework.controller.MainController;
import org.framework.utils.ConvertToString;
import org.framework.utils.RandomGenerator;
import org.framework.utils.RestCustomMethods;

import com.jayway.restassured.response.Response;

import ru.yandex.qatools.allure.annotations.Step;

public class UserOperations extends MainController{

	
	ConvertToString toString = new ConvertToString();
	RestCustomMethods customMethods = new RestCustomMethods();
	
	@Step("verify whether {1} is {2}")
	public void iVerifyWhetherTheStatusIsActive(Response response, String parameterToCheck, String expectedValueOfTheParameter) {
        assertThat(response.path(parameterToCheck).toString(),is(equalTo(expectedValueOfTheParameter)));
}

	@Step("create user with localeId:{0} ")
	public String getRequestBodyForCreatingUser(String localeId,String fileName) throws IOException {
		
		return toString.createUser(localeId,fileName);
		
	}

	@Step("post create user with a valid req.body")
	public Response postCreateUser(String requestBody) {
		return customMethods.postwithoutaccesstoken(getBaseURL(),requestBody);	
	}
}
