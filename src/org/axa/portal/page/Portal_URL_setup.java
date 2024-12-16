package org.axa.portal.page;

import java.io.IOException;

import org.axa.framework.Assertion;
import org.axa.framework.CommonFunctions;
import org.axa.framework.Portal_ObjectRepository;
import org.axa.framework.Report;

import com.microsoft.playwright.Page;

public class Portal_URL_setup extends CommonFunctions {

	public void launchURL(Page page,String testCaseId,String insuranceFlowType) throws IOException {
		try {
			if(insuranceFlowType.equals("Corporate-->Individual")) {
				//if(utility.homePageMap.get(testCaseId).getInsurnaceFlowType().equals("Corporate")) {
				//if(insuranceFlowType.equals("Corporate")) {
				
				//page.navigate(utility.property.getProperty("MainURL")+utility.property.getProperty("Environment")+utility.property.getProperty("SMEURL"));
				//Report.logger.info("Launched URL: "+utility.property.getProperty("MainURL")+utility.property.getProperty("Environment")+utility.property.getProperty("SMEURL"));
				page.navigate(utility.property.getProperty("SMEURL"));
			}else {
				//page.navigate(utility.property.getProperty("MainURL")+utility.property.getProperty("Environment")+utility.property.getProperty("IndividualURL"));
				//Report.logger.info("Launched URL: "+utility.property.getProperty("MainURL")+utility.property.getProperty("Environment")+utility.property.getProperty("IndividualURL"));
				page.navigate(utility.property.getProperty("IndividualURL"));
			}


			try {
				waitForSelectorWithCustomizedTime(page, Portal_ObjectRepository.CarInsuranceOption,30);
			}catch(Exception e){
				page.reload();
				waitForSelector(page, Portal_ObjectRepository.CarInsuranceOption);
			} 
			passStatusWithScreenshots(page,testCaseId, "User successfully navigated to Top page", "topPageNavigation",true);

			//clickAction(page, Portal_ObjectRepository.closeBanner);
			acceptCookies(page, Portal_ObjectRepository.closeBanner);
			ADJ_portal_homePage.CORPORATE_URL=Assertion.assertURLContains(page, "sme");

		}catch(Exception e){
			System.out.println(e);
			ADJ_portal_homePage.CORPORATE_URL=Assertion.assertURLContains(page, "sme");
			//clickAction(page, Portal_ObjectRepository.closeBanner);
			acceptCookies(page, Portal_ObjectRepository.closeBanner);
			//failStatusWithScreenshots(page,testCaseId, "There is problem in Top page", "Failed_TopPageNavigation",true);
		}
	}
	
	
	public void launchAMPURL(Page page,String testCaseId,String insuranceFlowType) throws IOException {
		try {
			//page.navigate("https://www-inssuite"+utility.property.getProperty("Environment")+utility.property.getProperty("AMP_URL"));
			page.navigate(utility.property.getProperty("AMP_URL"));
			waitForSelector(page, Portal_ObjectRepository.emmaLoginID);
			passStatusWithScreenshots(page,testCaseId, "User successfully navigated to Emma login page", "LoginPageNavigation",true);
			
			enterText(page, Portal_ObjectRepository.emmaLoginID, utility.emmaLoginMap.get(testCaseId).getLoginID());
			enterText(page, Portal_ObjectRepository.emmaLoginPassword, utility.emmaLoginMap.get(testCaseId).getPassword());
			
			/*
			 * if(!utility.homePageMap.get(testCaseId).getInsurnaceFlowType().equals(
			 * "Individual")) { enterText(page, Portal_ObjectRepository.emmaLoginID,
			 * utility.property.getProperty("CorporateEmmaLoginId")); enterText(page,
			 * Portal_ObjectRepository.emmaLoginPassword,
			 * utility.property.getProperty("CorporateEmmaPassword")); //CORPORATE_URL=true;
			 * }else { enterText(page, Portal_ObjectRepository.emmaLoginID,
			 * utility.property.getProperty("PersonalEmmaLoginId")); enterText(page,
			 * Portal_ObjectRepository.emmaLoginPassword,
			 * utility.property.getProperty("PersonalEmmaPassword")); //CORPORATE_URL=false;
			 * }
			 */
			passStatusWithScreenshots(page,testCaseId, "User entered login credentilas successfully ","loginPage",false);
			clickAction(page, Portal_ObjectRepository.emmaLoginButton);
			Thread.sleep(5000);
			passStatusWithScreenshots(page,testCaseId,"OTP Authentication screen displayed successfully ","otpAuthenticationScreen",true);
			//page.reload();
			Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
			waitForSelector(page, Portal_ObjectRepository.emmaNewProcedureTab);
			passStatusWithScreenshots(page,testCaseId,"AMP Home screen displayed successfully ","ampHomeScreenNavigationValidation",true);
			ADJ_portal_homePage.CORPORATE_URL=Assertion.assertURLContains(page, "sme");
			//clickAction(page, Portal_ObjectRepository.closeBanner);
			acceptCookies(page, Portal_ObjectRepository.closeBanner);

		}catch(Exception e){
			System.out.println(e);
			ADJ_portal_homePage.CORPORATE_URL=Assertion.assertURLContains(page, "sme");
			//clickAction(page, Portal_ObjectRepository.closeBanner);
			acceptCookies(page, Portal_ObjectRepository.closeBanner);
			//failStatusWithScreenshots(page,testCaseId, "There is problem in Top page", "Failed_TopPageNavigation",true);
		}
	}


}
