package org.axa.framework;

import java.io.IOException;
import java.nio.file.Path;

//import org.axa.BC.BC_utility;
import org.axa.portal.page.Portal_URL_setup;
import org.axa.portal.page.utility;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class InitializeBrowser {
	CommonFunctions common=new CommonFunctions();
	Portal_URL_setup url=new Portal_URL_setup();

	public Playwright playwright;
	public Browser browser;
	public BrowserContext context;
	public Page page;

	public void launchBrowser(String BrowserView,String testCaseId) throws IOException {

		playwright= Playwright.create();
		Path path=Path.of("");

		switch(utility.property.getProperty("BrowserName").toLowerCase()) {
		case "chrome":
			if(utility.property.getProperty("BrowserType").equals("Incognito")) {
				browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
				context=browser.newContext();
			}else {
				context = playwright.chromium().launchPersistentContext(path,new BrowserType.LaunchPersistentContextOptions().setHeadless(false).setChannel("chrome"));
			}

			break;
		case "edge":
			if(utility.property.getProperty("BrowserType").equals("Incognito")) {
				browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
				context=browser.newContext();
			}else {
				context = playwright.chromium().launchPersistentContext(path,new BrowserType.LaunchPersistentContextOptions().setHeadless(false).setChannel("msedge"));
			}
			break;
		case "safari":
			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			if(utility.property.getProperty("BrowserType").equals("Incognito")) {
				browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
				context=browser.newContext();
			}else {
				context = playwright.webkit().launchPersistentContext(path,new BrowserType.LaunchPersistentContextOptions().setHeadless(false).setChannel("webkit"));
			}
			break;
		case "firefox":
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			if(utility.property.getProperty("BrowserType").equals("Incognito")) {
				browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
				context=browser.newContext();
			}else {
				context = playwright.firefox().launchPersistentContext(path,new BrowserType.LaunchPersistentContextOptions().setHeadless(false).setChannel("firefox"));
			}
			break;
		default:
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			context=browser.newContext();

			break;
		}

		//if(BrowserView.equals("Mobile") && utility.property.getProperty("BrowserName").equalsIgnoreCase("chrome")) {
		/*
		 * if(BrowserView.equals("Mobile")) { context= browser.newContext(new
		 * Browser.NewContextOptions().setViewportSize(390, 844));
		 * 
		 * }else { context= browser.newContext(new
		 * Browser.NewContextOptions().setViewportSize(1920, 1080)); }
		 */
		//context = browser.newContext(new Browser.NewContextOptions().setIsMobile(false));
		//context=  browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080).setRecordVideoDir(Paths.get("Videos/")));
		//.setRecordVideoSize(640, 480));

		context.tracing().start(new Tracing.StartOptions() .setScreenshots(true)
				.setSnapshots(true) .setSources(true));
		context.clearCookies();

		page = context.newPage();

		if(BrowserView.equals("Mobile")) {
			page.setViewportSize(390, 844);

		}else {
			page.setViewportSize(1920, 1080);
		}

	}

	public void closeBrowser(Browser browser,BrowserContext context) {
		context.close();
		if(!(browser==null)) {
			browser.close();
		}

	}




	public void launchURL(Page page,String testCaseId,String insuranceFlowType) throws IOException {
		try {
			if(insuranceFlowType.equals("Corporate-->Individual")) {
				//page.navigate(utility.property.getProperty("MainURL")+utility.property.getProperty("Environment")+utility.property.getProperty("SMEURL"));
				page.navigate(utility.property.getProperty("MainURL"));
			}else {
				//page.navigate(utility.property.getProperty("MainURL")+utility.property.getProperty("Environment")+utility.property.getProperty("IndividualURL"));
				page.navigate(utility.property.getProperty("MainURL"));
			}
			try {
				common.waitForSelectorWithCustomizedTime(page, Portal_ObjectRepository.CarInsuranceOption,15);
			}catch(Exception e){
				page.reload();
				common.waitForSelector(page, Portal_ObjectRepository.CarInsuranceOption);
			} 
			common.passStatusWithScreenshots(page,testCaseId, "User successfully navigated to Top page", "topPageNavigation",true);
			common.clickAction(page, Portal_ObjectRepository.closeBanner);

		}catch(Exception e){
			System.out.println(e);
		}
	}


}
