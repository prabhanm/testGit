package org.axa.framework;

import com.microsoft.playwright.*;

import com.microsoft.playwright.Page.WaitForSelectorOptions;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.io.File;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.axa.portal.page.utility;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class CommonFunctions {

	public static String getCurrentDate(String datePattern) {
		SimpleDateFormat formate=new SimpleDateFormat(datePattern);
		return formate.format(new Date());
	}

	public void clickAction(Page page,String elementPath) {
		page.click(elementPath);
	}
	public void clickAction(Locator locator) {
		locator.click();
	}

	public void enterText(Page page,String elementPath,String textValue) {
		if((getAttribute(page, elementPath, "value")!=null && !getAttribute(page, elementPath, "value").equals(""))) {
			page.locator(elementPath).clear();
		} 
		page.fill(elementPath, textValue);
	}
	public void enterText(Locator locator,String textValue) {
		locator.fill(textValue);
	}

	public void KeyBoardTyping(Locator locator,String textValue) {
		locator.type(textValue);
	}
	public void KeyBoardTyping(Page page,String elementPath,String textValue) {
		page.type(elementPath, textValue);
	}

	public void selectDropdownByText(Page page,String elementPath,String textValue) {
		page.locator(elementPath).selectOption(new SelectOption().setLabel(textValue));
	}
	public void selectDropdownByText(Locator locator,String textValue) {
		locator.selectOption(new SelectOption().setLabel(textValue));
	}
	public void selectDropdownByValue(Page page,String elementPath,String value) {
		page.locator(elementPath).selectOption(new SelectOption().setValue(value));
	}
	public void selectDropdownByValue(Locator locator,String value) {
		locator.selectOption(new SelectOption().setValue(value));
	}
	public void scrollPageTillElementLocation(Page page,String elementPath){
		page.locator(elementPath).scrollIntoViewIfNeeded();
	}
	public void scrollPageTillElementLocation(Locator locator){
		locator.scrollIntoViewIfNeeded();
	}

	public String getText(Page page,String elementPath){
		return page.textContent(elementPath);
	}
	public String getText(Locator locator){
		return locator.textContent();
	}

	public String getTitle(Page page){
		return page.title();
	}

	public String getAttribute(Page page,String elementPath,String attributeName){
		return page.getAttribute(elementPath, attributeName);
	}
	public String getAttribute(Locator locator,String attributeName){
		return locator.getAttribute(attributeName);
	}

	public void textContentValidation(Page page,String testCaseId,String elementPath,String expectedText,String captureName){
		try {
			Assertion.assertBytext(page, elementPath, expectedText);
			passStatusWithScreenshots(page, testCaseId,"Extecped text:'" +expectedText+"' displayed successfully", captureName, false);
		}catch(AssertionError error) {
			failStatusWithScreenshots(page, testCaseId,"Extecped text:'" +expectedText+"' is not present in the page", "Failed_"+captureName, false);
		}catch(Exception e){
			failStatusWithScreenshots(page, testCaseId,"Extecped text:'" +expectedText+"' is not present in the page", "Failed_"+captureName, false);
		}
	}

	public Page switchToAnotherWindowOrTab(Page page,String elementPath){
		Page popup = page.waitForPopup(() -> {
			clickAction(page,elementPath );
		});
		return popup;
	}

	public boolean elementVisibility(Page page,String elementPath){
		//page.locator(elementPath).isEnabled();
		return page.locator(elementPath).isVisible(new Locator.IsVisibleOptions().setTimeout(5000));
	}
	public boolean elementVisibility(Locator locator){
		//locator.isEnabled();
		return locator.isVisible(new Locator.IsVisibleOptions().setTimeout(15000));
	}

	public void takescreenShots(Page page,String testCaseId,String captureName,boolean FullPageCapture) {

		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(Report.FULL_PATH+"\\"+testCaseId+"\\"+captureName+".png")).setFullPage(FullPageCapture));
	}

	public void passStatusWithScreenshots(Page page,String testCaseId,String stepDescription, String captureName,boolean FullPageCapture) {

		//page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(".\\"+getCurrentDate("dd-MM-yyyy")+"\\INS"+utility.property.getProperty("Environment")+"_"+FOLDER_NAM+"\\"+testCaseId+"\\"+captureName+".png")).setFullPage(FullPageCapture));
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(Report.FULL_PATH+"\\"+testCaseId+"\\"+captureName+".png")).setFullPage(FullPageCapture));
		//Report.logger.log(Status.PASS, stepDescription, MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\P.Mishra\\eclipse-workspace\\SME_ADJ_Portal\\"+getCurrentDate("dd-MM-yyyy")+"\\INS"+utility.property.getProperty("Environment")+"_"+FOLDER_NAME+"\\"+testCaseId+"\\"+captureName+".png").build());
		Report.logger.log(Status.PASS, stepDescription, MediaEntityBuilder.createScreenCaptureFromPath(Report.FULL_PATH+"\\"+testCaseId+"\\"+captureName+".png").build());


	}

	public void takeScreenShotsOfComponent(Page page,String elementPath,String testCaseId,String stepDescription, String captureName) {
		//page.locator(elementPath).highlight();
		page.locator(elementPath).screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(Report.FULL_PATH+"\\"+testCaseId+"\\"+captureName+".png")));
		page.locator(elementPath).screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(Report.FULL_PATH+"\\"+testCaseId+"\\"+captureName+".png","")));
		Report.logger.log(Status.PASS, stepDescription, MediaEntityBuilder.createScreenCaptureFromPath(Report.FULL_PATH+"\\"+testCaseId+"\\"+captureName+".png").build());

	}
	public void failStatusWithScreenshots(Page page,String testCaseId,String stepDescription, String captureName,boolean FullPageCapture) {

		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(Report.FULL_PATH+"\\"+testCaseId+"\\"+captureName+".png")).setFullPage(FullPageCapture));
		Report.logger.log(Status.FAIL, stepDescription, MediaEntityBuilder.createScreenCaptureFromPath(Report.FULL_PATH+"\\"+testCaseId+"\\"+captureName+".png").build());

	}

	public void failStatusWithExceptions(Page page,String testCaseId,String stepDescription, String captureName,Exception e,boolean FullPageCapture) {

		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(Report.FULL_PATH+"\\"+testCaseId+"\\"+captureName+".png")).setFullPage(FullPageCapture));
		Report.logger.log(Status.FAIL, stepDescription, MediaEntityBuilder.createScreenCaptureFromPath(Report.FULL_PATH+"\\"+testCaseId+"\\"+captureName+".png").build());
	}

	public String toHalfWidth(String Fullwidthstr) {

		if (null ==Fullwidthstr || Fullwidthstr.length () <= 0) { 
			return ""; 
		}

		char[] Chararray = Fullwidthstr.toCharArray (); 
		//Char array traversal of full-width character conversions 
		for (int i = 0; i < Chararray.length; ++i) { 
			int charintvalue = (int) Chararray[i]; 
			// If the conversion relationship is satisfied, the offset between the corresponding subscript is reduced by 65248; if it's a space, just do the conversion. 
			if (charintvalue >= 65281 && charintvalue <= 65374) { 
				Chararray[i] = (char) (charintvalue-65248); 
			}else if (charintvalue==12288) { 
				Chararray[i] = (char) 32; 
			} 
		}
		return new String(Chararray); 

	}

	public String toHalfWidthWithLoop(String halfWidth) {

		StringBuilder builder = new StringBuilder();
		for (char c : halfWidth.toCharArray()) {
			builder.append((char) (c - 65248));
		}
		return builder.toString();
	}

	public String convertToHalfWidth(String Fullwidthstr) {
		char[] charArray=Fullwidthstr.toCharArray();

		for(int i=0;i<charArray.length;i++) {
			char c=charArray[i];

			if(c>=0x30A1 && c<=0x30F6){
				charArray[i]=(char)(c-0x60);
			}
		}

		/*
		 * for(char c :Fullwidthstr.toCharArray()) { if(c>=0x30A1 && c<=0x30F6) {
		 * result.append((char) (c-0xfee0)); }else if(c==0x3000) { result.append(' '); }
		 * else { result.append(c); } }
		 */

		return new String(charArray);

	}

	public void stopTracing(BrowserContext context,String testCaseId) {
		context.tracing().stop(new Tracing.StopOptions() .setPath(Paths.get(Report.FULL_PATH+"\\"+testCaseId+"\\trace.zip")));
	}

	public void waitForElementTobeAttached(Page page,String element) {

		page.locator(element).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED));
	}
	public void waitForSelector(Page page,String element) {

		page.waitForSelector(element);
	}

	public void waitForSelectorWithCustomizedTime(Page page,String element,int timeInSecond) {
		timeInSecond=timeInSecond*1000;
		page.waitForSelector(element, new WaitForSelectorOptions().setTimeout(timeInSecond));
	}

	public boolean isElementPresent(Page page,String element) {
		try {
			//Assertion.assertIsEnabled(page, element);
			page.locator(element).isEnabled(new Locator.IsEnabledOptions().setTimeout(5000));
			return true;
		}catch(AssertionError e) {
			return false;
		}catch(Exception e) {
			return false;
		}
	}

	public static String createFolder(String folderName) {
		File theDir = new File(".\\"+getCurrentDate("dd-MM-yyyy")+"\\INS"+utility.property.getProperty("Environment")+"_"+folderName);
		if (!theDir.exists()){
			theDir.mkdirs();
			return getCurrentDate("HH:mm");
		}else {
			return null;
		}
	}


	public int compareTwoDates(String date1,String date2) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		Date firstDate = sdf.parse(date1);
		Date secondDate = sdf.parse(date2); 

		int c = firstDate.compareTo(secondDate); 
		System.out.println(c); 
		return c;

	}

	public void inputFieldLimitValidation(Page page,String elementPath,String value,int expectedLength) {
		enterText(page, elementPath, value);
		//String attribute=(getAttribute(page, elementPath, "value")).trim();
		Assertion.assertHasAttributePattern(page, elementPath, "value", value.substring(0, expectedLength));
	}

	public String getElementProperty(Page page,String elementPath,String propertyName) {
		return (String) page.locator(elementPath).evaluate("element => getComputedStyle(element)['"+propertyName+"']");

	}
	
	public void acceptCookies(Page page,String elementPath) {
		if(elementVisibility(page, elementPath)) {
			clickAction(page, elementPath);
		}
	}
	
	public void uploadFile(Page page,String elementPath,String fileLocation) {
		try {
		FileChooser fileChooser = page.waitForFileChooser(() -> clickAction(page, elementPath));
		fileChooser.setFiles(Paths.get(fileLocation));
		//fileChooser.setFiles(Paths.get("C://Users//B496GZ//OneDrive - AXA//Prabhanshu//Automation Activities//Automation_Data//DriverLicence.pdf"));
		}catch(Exception e) {
			System.out.println(e);
			
		}
	}
}
