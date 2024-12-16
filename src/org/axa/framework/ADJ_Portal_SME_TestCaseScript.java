package org.axa.framework;

	import java.io.IOException;

import org.axa.portal.page.ADJ_portal_ContractConfirmationScreen;
import org.axa.portal.page.ADJ_portal_aboutMainDriverAndPolicyPlanScreen;
	import org.axa.portal.page.ADJ_portal_currentInsuranceScreen;
	import org.axa.portal.page.ADJ_portal_homePage;
	import org.axa.portal.page.ADJ_portal_paymentInformationScreen;
	import org.axa.portal.page.ADJ_portal_policyHolderInformationScreen;
	import org.axa.portal.page.ADJ_portal_quotationScreen;
	import org.axa.portal.page.ADJ_portal_suspensionCertificateScreen;
	import org.axa.portal.page.ADJ_portal_vehicleInformationScreen;
	import org.axa.portal.page.ParameterOfHomeAndQuotationPage;

	   public class ADJ_Portal_SME_TestCaseScript {
		InitializeBrowser executor=new InitializeBrowser();
		ADJ_portal_currentInsuranceScreen currentInsurnace=new ADJ_portal_currentInsuranceScreen();
		ADJ_portal_suspensionCertificateScreen suspension=new ADJ_portal_suspensionCertificateScreen();
		ADJ_portal_homePage home=new ADJ_portal_homePage();
		ADJ_portal_quotationScreen quotationPage=new ADJ_portal_quotationScreen();
		ADJ_portal_aboutMainDriverAndPolicyPlanScreen mainDriver=new ADJ_portal_aboutMainDriverAndPolicyPlanScreen();
		ADJ_portal_vehicleInformationScreen vehicleInfo=new ADJ_portal_vehicleInformationScreen();
		ADJ_portal_policyHolderInformationScreen policyHolder=new ADJ_portal_policyHolderInformationScreen();
		ADJ_portal_paymentInformationScreen payment=new ADJ_portal_paymentInformationScreen();
		ADJ_portal_ContractConfirmationScreen contractConfirmation=new ADJ_portal_ContractConfirmationScreen();
		CommonFunctions common=new CommonFunctions();
		
		
		public void End2End(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
			executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
			//System.out.println(executor.page.u);
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
			currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			policyHolder.e2eFlowOfPolicyHolderInformation(executor.page, data.getTestCaseID());
			policyHolder.termAndConditionsConfirmationPage(executor.page, data.getTestCaseID());
			contractConfirmation.contractDetailsConfirmationPage(executor.page, data.getTestCaseID());
			payment.e2eFlowOfPaymentPage(executor.page, data.getTestCaseID());
			common.stopTracing(executor.context, data.getTestCaseID());
			executor.page.close();
			}catch(AssertionError e) {
				common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
				common.stopTracing(executor.context, data.getTestCaseID());
				executor.page.close();
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
				common.stopTracing(executor.context, data.getTestCaseID());
				executor.page.close();
			}
		}
		
		 public void BAU_17873(ParameterOfHomeAndQuotationPage data) throws IOException {
			
			executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
			//System.out.println(executor.page.getCurrentUrl());
			home.loginToEmmaApplication(executor.page, data.getTestCaseID());
			home.startNewQuoteFromHomePage(executor.page, data.getTestCaseID());
			home.validateFlowText(executor.page, data.getTestCaseID());
			executor.page.close();
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
		
	public void BAU_17874(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			executor.page.close();
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}

	public void BAU_17965(ParameterOfHomeAndQuotationPage data) throws IOException {
		
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		try {
		//System.out.println(executor.page.getCurrentUrl());
		     home.loginToEmmaApplication(executor.page, data.getTestCaseID());
		     home.startNewQuoteFromHomePage(executor.page, data.getTestCaseID());
		     home.validateFlowText(executor.page, data.getTestCaseID());
		     executor.page.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page, data.getTestCaseID(),"There is problem in the flow", "FailedStep", e,true);
			executor.page.close();
		}
	}

	public void BAU_17966(ParameterOfHomeAndQuotationPage data) throws IOException {
		
	    executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		try {
		    home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
		    executor.page.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
			executor.page.close();
		}
	}

	   public void BAU_17994(ParameterOfHomeAndQuotationPage data) throws IOException {
		
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		try {
			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),"Individual");
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
			currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.selectPolicyPlan(executor.page, data.getTestCaseID());
			mainDriver.infoCorrectionDialog(executor.page, data.getTestCaseID());
			mainDriver.perviousPageReturnConfirmationDialog(executor.page, data.getTestCaseID());
		    common.textContentValidation(executor.page, data.getTestCaseID(),Portal_ObjectRepository.tentativErrorContent, "次の項目が仮となっています。チェックボックスを外し、修正をお願いします。", "homePageError");
		    home.insurancePurchaseFlow(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		    executor.page.close();
			
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
			executor.page.close();
		}
	}
	   
	   public void BAU_17998(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		try {
			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
			currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.selectPolicyPlan(executor.page, data.getTestCaseID());
			mainDriver.infoCorrectionDialog(executor.page, data.getTestCaseID());
			mainDriver.perviousPageReturnConfirmationDialog(executor.page, data.getTestCaseID());
		    common.textContentValidation(executor.page, data.getTestCaseID(),Portal_ObjectRepository.tentativErrorContent, "次の項目が仮となっています。チェックボックスを外し、修正をお願いします。", "homePageError");
		    home.insurancePurchaseFlow(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		    executor.page.close();
			
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
			executor.page.close();
		}
	}
	   
	   public void BAU_17875(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
			//home.navigateToCorporateFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			//home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),"Individul");
			executor.page.close();
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_17877(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
				home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
				if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
					suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				}
				quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
				mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
				mainDriver.selectPolicyPlan(executor.page, data.getTestCaseID());
				mainDriver.infoCorrectionDialog(executor.page, data.getTestCaseID());
				mainDriver.perviousPageReturnConfirmationDialog(executor.page, data.getTestCaseID());
			    common.textContentValidation(executor.page, data.getTestCaseID(),Portal_ObjectRepository.tentativErrorContent, "次の項目が仮となっています。チェックボックスを外し、修正をお願いします。", "homePageError");
			    home.insurancePurchaseFlow(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			    executor.page.close();
				
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_17970(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			//home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),"Individul");
			executor.page.close();
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_17971(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
				home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
				if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
					suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				}
				quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
				mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
				mainDriver.selectPolicyPlan(executor.page, data.getTestCaseID());
				mainDriver.infoCorrectionDialog(executor.page, data.getTestCaseID());
				mainDriver.perviousPageReturnConfirmationDialog(executor.page, data.getTestCaseID());
			    common.textContentValidation(executor.page, data.getTestCaseID(),Portal_ObjectRepository.tentativErrorContent, "次の項目が仮となっています。チェックボックスを外し、修正をお願いします。", "homePageError");
			    home.insurancePurchaseFlow(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			    executor.page.close();	
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_17972(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			//home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),"Individul");
			executor.page.close();
			
			//need to implement assertion to validate UI content.
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_17956(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			home.rejectSwithFlowPopup(executor.page, data.getTestCaseID());
			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			executor.page.close();
			
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_17975(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			home.rejectSwithFlowPopup(executor.page, data.getTestCaseID());
			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			executor.page.close();
			
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_17976(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			home.rejectSwithFlowPopup(executor.page, data.getTestCaseID());
			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			
			//Validate UI content
			executor.page.close();
			
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_18261(ParameterOfHomeAndQuotationPage data) throws IOException {
			
			executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
				home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),"Individual");
				if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
					suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				}
				quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
				mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
				mainDriver.selectPolicyPlan(executor.page, data.getTestCaseID());
				mainDriver.infoCorrectionDialog(executor.page, data.getTestCaseID());
				mainDriver.perviousPageReturnConfirmationDialog(executor.page, data.getTestCaseID());
			    common.textContentValidation(executor.page, data.getTestCaseID(),Portal_ObjectRepository.tentativErrorContent, "次の項目が仮となっています。チェックボックスを外し、修正をお願いします。", "homePageError");
				home.validateFlowText(executor.page, data.getTestCaseID());
				home.switchToAnotherFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
				home.rejectSwithFlowPopup(executor.page, data.getTestCaseID());
				home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
				executor.page.close();
				
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_17984(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
			//home.navigateToCorporateFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			home.rejectSwithFlowPopup(executor.page, data.getTestCaseID());
			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			executor.page.close();
			
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_18015(ParameterOfHomeAndQuotationPage data) throws IOException {
			
			executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
				home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
				if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
					suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				}
				quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
				mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
				mainDriver.selectPolicyPlan(executor.page, data.getTestCaseID());
				mainDriver.infoCorrectionDialog(executor.page, data.getTestCaseID());
				mainDriver.perviousPageReturnConfirmationDialog(executor.page, data.getTestCaseID());
			    common.textContentValidation(executor.page,data.getTestCaseID(), Portal_ObjectRepository.tentativErrorContent, "次の項目が仮となっています。チェックボックスを外し、修正をお願いします。", "homePageError");
				home.validateFlowText(executor.page, data.getTestCaseID());
				home.switchToAnotherFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
				home.rejectSwithFlowPopup(executor.page, data.getTestCaseID());
				home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
				executor.page.close();
				
				
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_18016(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
			//home.navigateToCorporateFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			home.rejectSwithFlowPopup(executor.page, data.getTestCaseID());
			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			executor.page.close();
			
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_18017(ParameterOfHomeAndQuotationPage data) throws IOException {
			
			executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
				home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
				if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
					suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				}
				quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
				mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
				mainDriver.selectPolicyPlan(executor.page, data.getTestCaseID());
				mainDriver.infoCorrectionDialog(executor.page, data.getTestCaseID());
				mainDriver.perviousPageReturnConfirmationDialog(executor.page, data.getTestCaseID());
			    common.textContentValidation(executor.page,data.getTestCaseID(), Portal_ObjectRepository.tentativErrorContent, "次の項目が仮となっています。チェックボックスを外し、修正をお願いします。", "homePageError");
				home.validateFlowText(executor.page, data.getTestCaseID());
				home.switchToAnotherFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
				home.rejectSwithFlowPopup(executor.page, data.getTestCaseID());
				home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
				executor.page.close();
				
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_18020(ParameterOfHomeAndQuotationPage data) throws IOException {
			
			executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
				home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
				if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
					suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				}
				quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
				mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
				mainDriver.selectPolicyPlan(executor.page, data.getTestCaseID());
				mainDriver.infoCorrectionDialog(executor.page, data.getTestCaseID());
				mainDriver.perviousPageReturnConfirmationDialog(executor.page, data.getTestCaseID());
			    common.textContentValidation(executor.page, data.getTestCaseID(),Portal_ObjectRepository.tentativErrorContent, "次の項目が仮となっています。チェックボックスを外し、修正をお願いします。", "homePageError");
			    home.insurancePurchaseFlow(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			    executor.page.close();
				//Validation of UI elements
				
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_18041(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
				home.validationOfBikeButtonEnability(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
				executor.page.close();
			
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_18042(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
				home.validationOfBikeButtonEnability(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			executor.page.close();
			
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_18100(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
				home.validationOfBikeButtonEnability(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
				executor.page.close();
			
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_18102(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
			try {
				home.validationOfBikeButtonEnability(executor.page, data.getTestCaseID(),data.getInsurnaceFlowType());
			executor.page.close();
			
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   @SuppressWarnings("unused")
	public void BAU_18152(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	  		try {
	  			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	  			String PolicyNumber=home.getQuotaionNumber(executor.page,data.getTestCaseID(),data.getBrowserView());
	  			if(PolicyNumber.isEmpty() || PolicyNumber==null || PolicyNumber.equals("-") ) {
	  			 common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "Policy Number is not generated", "PolicynumberNot generated",false);
	  			}else {
	  			common.clickAction(executor.page, Portal_ObjectRepository.logoImage);
	  			common.waitForSelector(executor.page, Portal_ObjectRepository.saveDataDialogEmailInput);
	  			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"pop-up to save quote is displayed successfully", "saveQuotePop-up",false);
	  			}
	  			executor.page.close();
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_18156(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	  		try {
	  	
	  			String PolicyNumber=home.getQuotaionNumber(executor.page,data.getTestCaseID(),data.getBrowserView());
	  			if(PolicyNumber.isEmpty() || !PolicyNumber.equals("-")) {
	  				common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "Policy Number:"+PolicyNumber+" is generated in home page", "Failed_Policynumber",false);
	  			}else {
	  			common.clickAction(executor.page, Portal_ObjectRepository.logoImage);
	  			common.waitForSelector(executor.page, Portal_ObjectRepository.roughtEstimationDialogButton);
	  			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "pop-up for quote recommendation is displayed successfully", "QuoteRecommendationPop-up",false);
	  			}
	  		    executor.page.close();
			
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_18158(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	 		try {
	 	
	 			String PolicyNumber=home.getQuotaionNumber(executor.page,data.getTestCaseID(),data.getBrowserView());
	 			if(PolicyNumber.isEmpty() || !PolicyNumber.equals("-")) {
	 				common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "Policy Number:"+PolicyNumber+" is generated in home page", "Failed_Policynumber",false);
	 			}else {
	 			common.clickAction(executor.page, Portal_ObjectRepository.logoImage);
	 			
				common.waitForSelector(executor.page,"//span[text()='お見積り']");
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "User successfully navigated to Grand Top page","GrandTopPage",false);
	 			}
			    executor.page.close();
			
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_18159(ParameterOfHomeAndQuotationPage data) throws IOException {
			
		   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	  		try {
	  	
	  			String PolicyNumber=home.getQuotaionNumber(executor.page,data.getTestCaseID(),data.getBrowserView());
	  			if(PolicyNumber.isEmpty() || !PolicyNumber.equals("-")) {
	  				common.failStatusWithScreenshots(executor.page, data.getTestCaseID(),"Policy Number:"+PolicyNumber+" is generated in home page", "Failed_Policynumber",false);
	  			}else {
	  			common.clickAction(executor.page, Portal_ObjectRepository.logoImage);
	  			
				common.waitForSelector(executor.page,"//span[text()='お見積り']");
				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"User successfully navigated to Grand Top page","GrandTopPage",true);
	  			}
			    executor.page.close();
			
			}catch(Exception e) {
				common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
				executor.page.close();
			}
		}
	   
	   public void BAU_18161(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	   	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	   		try {
	   			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	   			String PolicyNumber=home.getQuotaionNumber(executor.page,data.getTestCaseID(),data.getBrowserView());
	   			if(PolicyNumber.isEmpty() || PolicyNumber==null || PolicyNumber.equals("-") ) {
	   			 common.failStatusWithScreenshots(executor.page, data.getTestCaseID(),"Policy Number is not generated", "PolicynumberNot generated",false);
	   			}else {
	   			common.clickAction(executor.page, Portal_ObjectRepository.logoImage);
	   			common.waitForSelector(executor.page, Portal_ObjectRepository.saveDataDialogEmailInput);
	   			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "pop-up to save quote is displayed successfully", "saveQuotePop-up",false);
	   			}
	   		    executor.page.close();
	   		
	   		}catch(Exception e) {
	   			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	   			executor.page.close();
	   		}
	   	}
	      
	      public void BAU_18160(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	   	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	   		try {
	   	
	   			String PolicyNumber=home.getQuotaionNumber(executor.page,data.getTestCaseID(),data.getBrowserView());
	   			if(PolicyNumber.isEmpty() || !PolicyNumber.equals("-")) {
	   				common.failStatusWithScreenshots(executor.page, data.getTestCaseID(),"Policy Number:"+PolicyNumber+" is generated in home page", "Failed_Policynumber",false);
	   			}else {
	   			common.clickAction(executor.page, Portal_ObjectRepository.logoImage
	   					);
	   			common.waitForSelector(executor.page, Portal_ObjectRepository.roughtEstimationDialogButton);
	   			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"pop-up for quote recommendation is displayed successfully", "QuoteRecommendationPop-up",false);
	   			}
	   		    executor.page.close();
	   		
	   		}catch(Exception e) {
	   			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	   			executor.page.close();
	   		}
	   	}
	      
	      public void BAU_18178(ParameterOfHomeAndQuotationPage data) throws IOException {
	     		
	      	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	      		try {
	      	
	      			common.scrollPageTillElementLocation(executor.page, Portal_ObjectRepository.internetOptionImage);
	      			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"'Regarding Internet quotes' text displayed successfully", "internetQuotesText",true);
	      		    executor.page.close();
	      		
	      		}catch(Exception e) {
	      			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	      			executor.page.close();
	      		}
	      	}
	      
	      public void BAU_18179(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	     	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	     		try {
	     	
	     			common.scrollPageTillElementLocation(executor.page, Portal_ObjectRepository.internetOptionImage);
	     			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"'Regarding Internet quotes' text displayed successfully", "internetQuotesText",false);
	     		    executor.page.close();
	     		
	     		}catch(Exception e) {
	     			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	     			executor.page.close();
	     		}
	     	}
	      
	      public void BAU_18182(ParameterOfHomeAndQuotationPage data) throws IOException {
	     		
	    	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	    		try {
	    			
	    			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	    			currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType()); 
	    			String validationText="選択いただいたケースの場合は、インターネットでお申込みいただけません。カスタマーサービスセンターまでお電話下さい。";
	       			common.textContentValidation(executor.page, data.getTestCaseID(), Portal_ObjectRepository.contactCenterDialogBox, validationText, "contactCenterDialog");
	       		    executor.page.close();
	    		
	    		}catch(Exception e) {
	    			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	    			executor.page.close();
	    		}
	    	}
	      
	      public void BAU_18183(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	   	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	   		try {
	   			
	   			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	   			currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType()); 
	   			String validationText="選択いただいたケースの場合は、インターネットでお申込みいただけません。カスタマーサービスセンターまでお電話下さい。";
	   			common.textContentValidation(executor.page, data.getTestCaseID(), Portal_ObjectRepository.contactCenterDialogBox, validationText, "contactCenterDialog");
	   		    executor.page.close();
	   		
	   		}catch(Exception e) {
	   			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	   			executor.page.close();
	   		}
	   	}
	      
	      public void BAU_18184(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	   	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	   		try {
	   			
	   			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	   			currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType()); 
	   			String validationText="選択いただいたケースの場合は、インターネットでお申込みいただけません。カスタマーサービスセンターまでお電話下さい。";
	   			common.textContentValidation(executor.page, data.getTestCaseID(), Portal_ObjectRepository.contactCenterDialogBox, validationText, "contactCenterDialog");
	   		    executor.page.close();
	   		
	   		}catch(Exception e) {
	   			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	   			executor.page.close();
	   		}
	   	}
	      
	      public void BAU_18185(ParameterOfHomeAndQuotationPage data) throws IOException {
	     		
	      	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	      		try {
	      			
	      			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	      			currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType()); 
	      			String validationText="確認事項";
	      			common.textContentValidation(executor.page, data.getTestCaseID(), Portal_ObjectRepository.oldContactCenterDialogBox, validationText, "contactCenterDialog");
	      		    executor.page.close();
	      		
	      		}catch(Exception e) {
	      			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	      			executor.page.close();
	      		}
	      	}
	      
	      public void BAU_18186(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	     	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	     		try {
	     			
	     			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	     			currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType()); 
	     			common.elementVisibility(executor.page, Portal_ObjectRepository.carRegistrationYear);
	     			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "User successfully Navigated to quotation page", "quotationPageNavigation",true);
	     		    executor.page.close();
	     		
	     		}catch(Exception e) {
	     			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	     			executor.page.close();
	     		}
	     	}
	      
	      public void BAU_18187(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	   	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	   		try {
	   			
	   			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	   			currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType()); 
	   			String validationText="選択いただいたケースの場合は、インターネットでお申込みいただけません。カスタマーサービスセンターまでお電話下さい。";
	   			
	      		common.textContentValidation(executor.page, data.getTestCaseID(), Portal_ObjectRepository.contactCenterDialogBox, validationText, "contactCenterDialog");
	      		    executor.page.close();
	   		}catch(Exception e) {
	   			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	   			executor.page.close();
	   		}
	   	}
	      
	      public void BAU_18539(ParameterOfHomeAndQuotationPage data) throws IOException {
	     		
	      	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	      		try {
	      			
	      			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	      			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "suspension infirmation dialog displayed successfully", "suspensionDialog",false);
	      			common.scrollPageTillElementLocation(executor.page, Portal_ObjectRepository.suspentionCertificateDialogPoint5);
	      			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Point number 5 is present inside the dialog information", "Dialogpoint5Info",false);
	      			common.scrollPageTillElementLocation(executor.page, Portal_ObjectRepository.suspentionCertificateDialogPoint6);
	      			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Point number 6 is present inside the dialog information", "Dialogpoint5Info",false);
	      		    executor.page.close();
	      		}catch(Exception e) {
	      			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	      			executor.page.close();
	      		}
	      	}
	      
	      public void BAU_18541(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	     	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	     		try {
	     			
	     			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	     			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "suspension infirmation dialog displayed successfully", "suspensionDialog",false);
	     			common.scrollPageTillElementLocation(executor.page, Portal_ObjectRepository.suspentionCertificateDialogPoint5);
	     			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Point number 5 is present inside the dialog information", "Dialogpoint5Info",false);
	     			common.scrollPageTillElementLocation(executor.page, Portal_ObjectRepository.suspentionCertificateDialogPoint6);
	     			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Point number 6 is present inside the dialog information", "Dialogpoint5Info",false);
	     		    executor.page.close();
	     		}catch(Exception e) {
	     			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	     			executor.page.close();
	     		}
	     	}
	      
	      public void BAU_18556(ParameterOfHomeAndQuotationPage data) throws IOException {
	     		
	    	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	    		try {
	    			
	    			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	    			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "suspension infirmation dialog displayed successfully", "suspensionDialog",false);
	    			common.scrollPageTillElementLocation(executor.page, Portal_ObjectRepository.suspentionCertificateDialogPoint6);
	    			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Point number 6 is present inside the dialog information", "Dialogpoint5Info",false);
	    		    executor.page.close();
	    		}catch(Exception e) {
	    			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	    			executor.page.close();
	    		}
	    	}
	      
	      public void BAU_18592(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	   	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	   		try {
	   			
	   			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	   			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "suspension infirmation dialog displayed successfully", "suspensionDialog",false);
	   			common.scrollPageTillElementLocation(executor.page, Portal_ObjectRepository.suspentionCertificateDialogPoint6);
	   			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Point number 6 is present inside the dialog information", "Dialogpoint5Info",false);
	   		    executor.page.close();
	   		}catch(Exception e) {
	   			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	   			executor.page.close();
	   		}
	   	}
	      
	      public void BAU_18604(ParameterOfHomeAndQuotationPage data) throws IOException {
	     		
	      	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	      		try {
	      			
	      			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	      			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "suspension infirmation dialog displayed successfully", "suspensionDialog",false);
	      			common.scrollPageTillElementLocation(executor.page, Portal_ObjectRepository.suspentionCertificateDialogPoint6);
	      			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Point number 6 is present inside the dialog information", "Dialogpoint5Info",false);
	      		    executor.page.close();
	      		}catch(Exception e) {
	      			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	      			executor.page.close();
	      		}
	      	}
	      
	      public void BAU_18605(ParameterOfHomeAndQuotationPage data) throws IOException {
	     		
	      	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	      		try {
	      			
	      			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	      			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "suspension infirmation dialog displayed successfully", "suspensionDialog",false);
	      			common.scrollPageTillElementLocation(executor.page, Portal_ObjectRepository.suspentionCertificateDialogPoint6);
	      			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Point number 6 is present inside the dialog information", "Dialogpoint5Info",false);
	      		    executor.page.close();
	      		}catch(Exception e) {
	      			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	      			executor.page.close();
	      		}
	      	}
	      public void BAU_18625(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	     	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	     		try {
	     			
	     			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	     			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	     			suspension.selectSuspensionReason(executor.page, data.getTestCaseID());
	     		    executor.page.close();
	     		}catch(Exception e) {
	     			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	     			executor.page.close();
	     		}
	     	}
	      public void BAU_18654(ParameterOfHomeAndQuotationPage data) throws IOException {
	     		
	    	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	    		try {
	    			
	    			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	    			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	    			suspension.selectSuspensionReason(executor.page, data.getTestCaseID());
	    		    executor.page.close();
	    		}catch(Exception e) {
	    			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	    			executor.page.close();
	    		}
	    	}
	      
	      public void BAU_18655(ParameterOfHomeAndQuotationPage data) throws IOException {
	     		
	    	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	    		try {
	    			
	    			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	    			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	    			suspension.selectSuspensionReason(executor.page, data.getTestCaseID());
	    		    executor.page.close();
	    		}catch(Exception e) {
	    			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	    			executor.page.close();
	    		}
	    	}
	      
	      public void BAU_18656(ParameterOfHomeAndQuotationPage data) throws IOException {
	     		
	    	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	    		try {
	    			
	    			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	    			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	    			suspension.selectSuspensionReason(executor.page, data.getTestCaseID());
	    		    executor.page.close();
	    		}catch(Exception e) {
	    			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	    			executor.page.close();
	    		}
	    	}
	      
	      public void BAU_18659(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	   	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	   		try {
	   			
	   			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	   			suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
	   			
	   		    executor.page.close();
	   		}catch(Exception e) {
	   			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	   			executor.page.close();
	   		}
	   	}

	      public void BAU_18668(ParameterOfHomeAndQuotationPage data) throws IOException {
	     		
	      	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	      		try {
	      			
	      			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	      			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	      			suspension.suspensionCertificateStartDate(executor.page, data.getTestCaseID());
	      			suspension.overseaseTravelPopupForSIC(executor.page, data.getTestCaseID());
	      		    executor.page.close();
	      		}catch(Exception e) {
	      			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	      			executor.page.close();
	      		}
	      	}
	      
	      public void BAU_18675(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	     	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	     		try {
	     			
	     			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	     			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	     			suspension.suspensionCertificateStartDate(executor.page, data.getTestCaseID());
	     			suspension.overseaseTravelPopupForSIC(executor.page, data.getTestCaseID());
	     			executor.page.close();
	     		}catch(Exception e) {
	     			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	     			executor.page.close();
	     		}
	     	}
	      
	      public void BAU_18676(ParameterOfHomeAndQuotationPage data) throws IOException {
	     		
	    	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	    		try {
	    			
	    			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	    			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	    			suspension.suspensionCertificateStartDate(executor.page, data.getTestCaseID());
	    			if(common.isElementPresent(executor.page, "//*[text()='閉じる']")==false) {
	    				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Contact center pop-up is not displayed after entering start date ","contactCenterPop-upNotDisplayed",false);
	    			}else {
	     				common.failStatusWithScreenshots(executor.page, data.getTestCaseID(), "Contact center pop-up is displayed after entering start date ","Failed_contactCenterPop-upNotDisplayed",false);
	     			}
	    			
	    		    executor.page.close();
	    		}catch(Exception e) {
	    			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	    			executor.page.close();
	    		}
	    	}
	      
	      public void BAU_18677(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	     	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	     		try {
	     			
	     			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	     			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	     			suspension.suspensionCertificateStartDate(executor.page, data.getTestCaseID());
	     			suspension.overseaseTravelPopupForSIC(executor.page, data.getTestCaseID());
	     		    executor.page.close();
	     		}catch(Exception e) {
	     			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	     			executor.page.close();
	     		}
	     	}
	     
	     public void BAU_18679(ParameterOfHomeAndQuotationPage data) throws IOException {
	  		
	    	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	    		try {
	    			
	    			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	    			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	    			suspension.suspensionCertificateStartDate(executor.page, data.getTestCaseID());
	    			suspension.overseaseTravelPopupForSIC(executor.page, data.getTestCaseID());
	    			executor.page.close();
	    		}catch(Exception e) {
	    			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	    			executor.page.close();
	    		}
	    	}
	     
	     public void BAU_18681(ParameterOfHomeAndQuotationPage data) throws IOException {
	    		
	   	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	   		try {
	   			
	   			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	   			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	   			suspension.suspensionCertificateStartDate(executor.page, data.getTestCaseID());
	   			if(common.isElementPresent(executor.page, "//*[text()='閉じる']")==false) {
	   				Thread.sleep(2000);
	   				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Contact center pop-up is not displayed after entering start date ","contactCenterPop-upNotDisplayed",false);
	   			}else {
	 				common.failStatusWithScreenshots(executor.page, data.getTestCaseID(), "Contact center pop-up is displayed after entering start date ","Failed_contactCenterPop-upNotDisplayed",true);
	 			}
	   			
	   		    executor.page.close();
	   		}catch(Exception e) {
	   			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	   			executor.page.close();
	   		}
	   	}
	     
	     public void BAU_18723(ParameterOfHomeAndQuotationPage data) throws IOException {
	    		
	   	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	   		try {
	   			
	   			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	   			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	   			suspension.suspensionCertificateRegistrationDate(executor.page, data.getTestCaseID());
	   			suspension.overseaseTravelPopupForSIC(executor.page, data.getTestCaseID());
	   		    executor.page.close();
	   		}catch(Exception e) {
	   			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	   			executor.page.close();
	   		}
	   	}
	     
	     public void BAU_18724(ParameterOfHomeAndQuotationPage data) throws IOException {
	 		
	     	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	     		try {
	     			
	     			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	     			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	     			suspension.suspensionCertificateRegistrationDate(executor.page, data.getTestCaseID());
	     			suspension.overseaseTravelPopupForSIC(executor.page, data.getTestCaseID());
	     		    executor.page.close();
	     		}catch(Exception e) {
	     			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	     			executor.page.close();
	     		}
	     	}
	     public void BAU_18728(ParameterOfHomeAndQuotationPage data) throws IOException {
	 		
	     	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	     		try {
	     			
	     			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	     			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	     			suspension.suspensionCertificateRegistrationDate(executor.page, data.getTestCaseID());
	     			suspension.overseaseTravelPopupForSIC(executor.page, data.getTestCaseID());
	     		    executor.page.close();
	     		}catch(Exception e) {
	     			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	     			executor.page.close();
	     		}
	     	}
	     public void BAU_18729(ParameterOfHomeAndQuotationPage data) throws IOException {
	 		
	     	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	     		try {
	     			
	     			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	     			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	     			suspension.suspensionCertificateRegistrationDate(executor.page, data.getTestCaseID());
	     			suspension.overseaseTravelPopupForSIC(executor.page, data.getTestCaseID());
	     		    executor.page.close();
	     		}catch(Exception e) {
	     			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	     			executor.page.close();
	     		}
	     	}
	     
	     public void BAU_18731(ParameterOfHomeAndQuotationPage data) throws IOException {
	 		
	     	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	     		try {
	     			
	     			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	     			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	     			suspension.suspensionCertificateRegistrationDate(executor.page, data.getTestCaseID());
	     			if(common.isElementPresent(executor.page, "//*[text()='閉じる']")==false) {
	     				Thread.sleep(2000);
	       				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Contact center pop-up is not displayed when aquisation date < Inception date ","contactCenterPop-upNotDisplayed",false);
	     			}else {
	     				common.failStatusWithScreenshots(executor.page, data.getTestCaseID(), "Contact center pop-up is displayed when aquisation date < Inception date ","Failed_contactCenterPop-upNotDisplayed",true);
	     			}
	     		    executor.page.close();
	     		}catch(Exception e) {
	     			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	     			executor.page.close();
	     		}
	     	}
	     
	     public void BAU_18732(ParameterOfHomeAndQuotationPage data) throws IOException {
	  		
	   	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	   		try {
	   			
	   			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	   			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
	   			suspension.suspensionCertificateRegistrationDate(executor.page, data.getTestCaseID());
	   			if(common.isElementPresent(executor.page, "//*[text()='閉じる']")==false) {
	   				Thread.sleep(2000);
	     				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Contact center pop-up is not displayed when aquisation date < Inception date ","contactCenterPop-upNotDisplayed",false);
	     			}else {
	     				common.failStatusWithScreenshots(executor.page, data.getTestCaseID(), "Contact center pop-up is displayed when aquisation date < Inception date ","Failed_contactCenterPop-upNotDisplayed",true);
	     			}
	   		    executor.page.close();
	   		}catch(Exception e) {
	   			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	   			executor.page.close();
	   		}
	   	}
	     
	     public void BAU_18495(ParameterOfHomeAndQuotationPage data) throws IOException {
	  		
	   	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	   		try {
	   			
	   			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	   			common.waitForSelector(executor.page,Portal_ObjectRepository.carRegistrationYear);
	   			if(common.isElementPresent(executor.page,Portal_ObjectRepository.carUsingWithChildConfirmation)==false) {
	   				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Question field for child care discount is not displayed for corporate flow ","childCarediscountQuestionaries",true);
	   			}else {
	   				common.failStatusWithScreenshots(executor.page, data.getTestCaseID(), "Question field for child care discount is displayed for corporate flow ","Faild_childCarediscountQuestionaries",true);
	   			}
	   			executor.page.close();
	   				
	   		}catch(Exception e) {
	   			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	   			executor.page.close();
	   		}
	   	}
	     
	     public void BAU_18497(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	     	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	     		try {
	     			
	     			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	     			common.waitForSelector(executor.page,Portal_ObjectRepository.carRegistrationYear);
	     			if(common.isElementPresent(executor.page,Portal_ObjectRepository.carUsingWithChildConfirmation)==true) {
	     				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Question field for child care discount is displayed for personal flow ","childCarediscountQuestionaries",true);
	     			}else {
	     				common.failStatusWithScreenshots(executor.page, data.getTestCaseID(), "Question field for child care discount is not displayed for personal flow ","Faild_childCarediscountQuestionaries",true);
	     			}
	     			executor.page.close();
	     				
	     		}catch(Exception e) {
	     			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	     			executor.page.close();
	     		}
	     	}
	     
	     public void BAU_18622(ParameterOfHomeAndQuotationPage data) throws IOException {
	    		
	   	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	   		try {
	   			
	   			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	   			common.waitForSelector(executor.page,Portal_ObjectRepository.carRegistrationYear);
	   			if(common.isElementPresent(executor.page,Portal_ObjectRepository.carUsingWithChildConfirmation)==true) {
	   				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Question field for child care discount is displayed for personal flow ","childCarediscountQuestionaries",true);
	   			}else {
	   				common.failStatusWithScreenshots(executor.page, data.getTestCaseID(), "Question field for child care discount is not displayed for personal flow ","Faild_childCarediscountQuestionaries",true);
	   			}
	   			executor.page.close();
	   				
	   		}catch(Exception e) {
	   			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	   			executor.page.close();
	   		}
	   	}
	     
	     public void BAU_18617(ParameterOfHomeAndQuotationPage data) throws IOException {
	  		
	   	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	   		try {
	   			
	   			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	   			common.waitForSelector(executor.page,Portal_ObjectRepository.carRegistrationYear);
	   			if(common.isElementPresent(executor.page,Portal_ObjectRepository.multipleOwnershipDiscount)==false) {
	   				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Multiple Ownership discount is not displayed for corporate flow ","multiOwnerDiscount",true);
	   			}else {
	   				common.failStatusWithScreenshots(executor.page, data.getTestCaseID(), "Multi discount text is displayed for personal flow ","Failed_multiDiscount",true);
	   			}
	   			executor.page.close();
	   				
	   		}catch(Exception e) {
	   			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	   			executor.page.close();
	   		}
	   	}
	     
	     public void BAU_18619(ParameterOfHomeAndQuotationPage data) throws IOException {
	   		
	     	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	     		try {
	     			
	     			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	     			common.waitForSelector(executor.page,Portal_ObjectRepository.carRegistrationYear);
	     			if(common.isElementPresent(executor.page,Portal_ObjectRepository.multipleOwnershipDiscount)==false) {
	     				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Multiple Ownership discount is not displayed for corporate flow ","multiOwnerDiscount",true);
	     			}else {
	     				common.failStatusWithScreenshots(executor.page, data.getTestCaseID(), "Multi discount text is displayed for personal flow ","Failed_multiDiscount",true);
	     			}
	     			executor.page.close();
	     				
	     		}catch(Exception e) {
	     			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	     			executor.page.close();
	     		}
	     	}
	     
	     public void BAU_18621(ParameterOfHomeAndQuotationPage data) throws IOException {
	 		
	     	   executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
	     		try {
	     			
	     			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
	     			common.waitForSelector(executor.page,Portal_ObjectRepository.carRegistrationYear);
	     			if(common.isElementPresent(executor.page,Portal_ObjectRepository.multipleOwnershipDiscount)==true) {
	     				common.textContentValidation(executor.page, data.getTestCaseID(), Portal_ObjectRepository.multipleOwnershipDiscount, "複数所有新規割引", "multiDiscountText");
	     			}else {
	     				common.failStatusWithScreenshots(executor.page, data.getTestCaseID(), "Multi discount text is not displayed for personal flow ","Failed_multiDiscount",true);
	     			}
	     			executor.page.close();
	     				
	     		}catch(Exception e) {
	     			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
	     			executor.page.close();
	     		}
	     	}
	}
