package org.framework.utils;

import java.io.File;
import java.io.IOException;

public class ConvertToString {
	
	public String loginRequestBodyTxt(String emailId,String password,String fileName) throws Exception {
	File file = new File("src/test/resources/requests/"+fileName);
	FileRead read = new FileRead();
    String body= read.readFile(file.getAbsolutePath());
    body=body.replace("email", emailId);
    body=body.replace("password", password);
    return body;
    }

    public String loginRequestBodyJSON(String firstName,String password,String fileName) throws Exception {
        File file = new File("src/test/resources/TestData/"+fileName);
        FileRead read = new FileRead();
        String body= read.readFile(file.getAbsolutePath());
        body=body.replace("$firstName", firstName);
        body=body.replace("$password", password);
        return body;
    }

    public String createUser(String uniqueName, String fileName) throws IOException {
        File file = new File("src/test/resources/requests/"+fileName);
        FileRead read = new FileRead();
        String body= read.readFile(file.getAbsolutePath());
        body=body.replace("$localeId", uniqueName);
        return body;
    }
}