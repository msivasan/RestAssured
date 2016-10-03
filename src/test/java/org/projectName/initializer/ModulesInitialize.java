package org.projectName.initializer;

import org.framework.controller.MainController;
import org.projectName.operations.UserOperations;

public class ModulesInitialize extends MainController{
	
	
	public UserOperations user()
	{
		return new UserOperations();
	}

}
