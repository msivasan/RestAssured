package org.framework.controller;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by hemanthsridhar on 9/22/16.
 */

public class MainController {

	private String baseURL = System.getProperty("baseURL");
	
	private byte[] getSampleFile(String fileName) throws Exception {
    	File file = new File(fileName);
    	return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
       
    }
    
	public String getBaseURL(){
		return baseURL;
	}
    public void attachFile(String file) throws Exception
    {
        if(file.contains("xlsx"))
        {
            saveXlsxAttachment(file);
        }
        else if(file.contains("txt"))
        {
            saveTextFileAttachment(file);
        }
        else if(file.contains("xml"))
        {
            saveXMLAttachment(file);
        }
        else if(file.contains("csv"))
        {
            saveCsvAttachment(file);
        }
        else if (file.contains("json"))
        {
            saveJSONFileAttachment(file);
        }
    }


    public void attachFile(String fileName,String attachmentName,String format) throws Exception
    {
        if(format.equals("xlsx"))
        {
            attachXLSXFile(attachmentName,fileName);
        }
        else if(format.equals("xml"))
        {
            attachXMLFile(attachmentName,fileName);
        }
        else if(format.equals("txt"))
        {
            attachTextFile(attachmentName,fileName);
        }
        else if(format.equals("csv"))
        {
            attachCSVFile(attachmentName,fileName);
        }
        else if (format.equals("json"))
        {
            attachJSONFile(attachmentName,fileName);
        }
    }


    @Attachment(value = "json {0} attachment", type = "text/json")
    private byte[] attachJSONFile(String attachmentName,String file) {
        return file.getBytes();
    }

    @Attachment(value = "xlsx {0} attachment")
    private byte[] attachXLSXFile(String attachmentName,String file) {
        return file.getBytes();
    }

    @Attachment(value = "xml {0} attachment", type = "text/xml")
    private byte[] attachXMLFile(String attachmentName,String file) {
        return file.getBytes();
    }

    @Attachment(value = "text {0} attachment", type = "text/plain")
    private byte[] attachTextFile(String attachmentName,String file) {
        return file.getBytes();
    }


    @Attachment(value = "csv {0} attachment", type = "text/csv")
    private byte[] attachCSVFile(String attachmentName, String file) {
        return file.getBytes();
    }


    @Attachment(value = "csv attachment", type = "text/csv")
    public byte[] saveCsvAttachment(String filePath) throws Exception {
        return getSampleFile(filePath);
    }

    @Attachment(value = "xml attachment", type = "text/xml")
    public byte[] saveXMLAttachment(String filePath) throws Exception {
        return getSampleFile(filePath);
    }


    @Attachment(value = "xlsx attachment")
    public byte[] saveXlsxAttachment(String filePath) throws Exception {
        return getSampleFile(filePath);
    }
    
    @Attachment(value = "text attachment", type ="text/plain")
    public byte[] saveTextFileAttachment(String filePath) throws Exception {
        return getSampleFile(filePath);
    }
    
    @Attachment(value="JSON attachment", type="text/json")
    public byte[] saveJSONFileAttachment(String filePath) throws Exception
    {
    	return getSampleFile(filePath);
    }
}


