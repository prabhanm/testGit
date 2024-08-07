package org.axa.framework;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.axa.portal.page.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Report {
	
	public static SimpleDateFormat dateFormate=new SimpleDateFormat("yyyy-MM-dd");
	public static String date=dateFormate.format(new Date());
	
	 public static String FOLDER_NAME;
     public static String CAPTURE_PATH;
     public static String EVIDENCE_LOCATION;
     public static String FULL_PATH;
	
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExtentSparkReporter sparkReport;
	
	public static void reportInitilization(String applicationName,String environment) {
		
		FOLDER_NAME=CommonFunctions.getCurrentDate("HH-mm");
		CAPTURE_PATH="\\"+applicationName+"\\"+CommonFunctions.getCurrentDate("dd-MM-yyyy")+"\\INS"+environment+"_"+FOLDER_NAME;
		//FULL_PATH=utility.property.getProperty("EvidenceLocation")+"\\"+CAPTURE_PATH;
		FULL_PATH=System.getProperty("user.dir")+CAPTURE_PATH;
		
		report=new ExtentReports();
		//sparkReport=new ExtentSparkReporter(new File(System.getProperty("user.dir")+"\\Reports\\"+date+"\\ADJ_Portal_ExecutionReport"+System.currentTimeMillis()+".html"));
		sparkReport=new ExtentSparkReporter(new File(System.getProperty("user.dir")+CAPTURE_PATH+"\\Report"+System.currentTimeMillis()+".html"));
		report.attachReporter(sparkReport);
		 
		report.setSystemInfo("Project","SME");
		report.setSystemInfo("Application", applicationName);
		report.setSystemInfo("Envrionment", "INS"+environment);
		report.setSystemInfo("Testing Type", "Regression");
		report.setSystemInfo("Tester", "Automation Team");
		
		sparkReport.config().enableOfflineMode(true);
		sparkReport.config().setDocumentTitle(applicationName+" Automation Report");
		sparkReport.config().setReportName(applicationName+" Regression Testing");
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		
		
	}
	public static void startTest(String testCaseID) {
		logger=report.createTest(testCaseID);
	}
	
	public static void endTest() {
		report.removeTest(logger);
	}
	public static void endReport() {
		report.flush();
	}


}
