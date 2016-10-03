package org.projectName.persistentAPI;

import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import org.framework.utils.ConvertToString;
import org.framework.utils.PermittedCharacters;
import org.framework.utils.RandomGenerator;
import org.framework.utils.RestCustomMethods;
import org.projectName.dataprovider.DatadrivenTestingFromExcel;
import org.projectName.initializer.ModulesInitialize;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;

public class APITest extends ModulesInitialize{
	
	ConvertToString toString = new ConvertToString();
	RestCustomMethods customMethods = new RestCustomMethods();
	

	@Test(dataProvider="singleExcelMultipleSheets",dataProviderClass=DatadrivenTestingFromExcel.class)
	@Features("attachment test")
	@Description("Simple attachment tests for different file formats")
	public void attachmentsTest1(String format, String filePath) throws Exception{
		switch(format)
		{
		case "json":saveJSONFileAttachment(filePath);
					break;
		case "xlsx":saveXlsxAttachment(filePath);
					break;
		case "xml":saveXMLAttachment(filePath);
					break;
		case "txt":saveTextFileAttachment(filePath);
					break;
		case "csv":saveCsvAttachment(filePath);
					break;
		default: throw new Exception("invalid file extension");	
		}
	}
	
	@Test(dataProvider="singleExcelMultipleSheets",dataProviderClass=DatadrivenTestingFromExcel.class)
	@Features("attachment test")
	@Description("Demonstration of changing of values in the request body dynamically.")
	public void replaceAndAttach(String format,String id,String password,String fileName) throws Exception
	{
		String requestBody = new String();
		switch(format)
		{
		case "xml":
				requestBody = toString.loginRequestBodyTxt(id, password, fileName);
				attachFile(requestBody,"request","xml");
				break;
		case "json":
				requestBody = toString.loginRequestBodyJSON(id, password, fileName);
				attachFile(requestBody,"request","json");
				break;			
		default: throw new Exception("invalid file extension");
		}
	}

	@Test(dataProvider="singleExcelMultipleSheets",dataProviderClass=DatadrivenTestingFromExcel.class)
	@Features("store response test")
	@Description("Demonstration of creating a new user")
	public void createUser(String fileName,String parameterToCheck,String expectedValueOfTheParameter) throws Exception{
		RestAssured.useRelaxedHTTPSValidation();		
		
		RestAssured.given().when().get(getBaseURL());
		
		String localeId = RandomGenerator.random(6, PermittedCharacters.ALPHANUMERIC);
		
		String requestBody =user().getRequestBodyForCreatingUser(localeId,fileName);
		
		attachFile(requestBody,"request","json");
		
		Response response = user().postCreateUser(requestBody);
		
		attachFile(response.asString(),"response","json");	
		
		customMethods.verifyStatusCode(response.getStatusCode(),200);
		
		user().iVerifyWhetherTheStatusIsActive(response,parameterToCheck,expectedValueOfTheParameter);
		
		customMethods.storeResponseLocally(response,"createUserResponse.json");

	}
}
