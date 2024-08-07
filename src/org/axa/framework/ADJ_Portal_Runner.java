package org.axa.framework;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

//import org.axa.BC.BC_utility;
import org.axa.portal.page.utility;

public class ADJ_Portal_Runner {
	

	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		System.out.println(CommonFunctions.getCurrentDate("HH:mm"));
		
		//BC_utility util=new BC_utility();
		//util.BC_methodToInokeFunction();
		
		utility util=new utility(); 
		util.methodToInokeFunction();
		 

}

}
