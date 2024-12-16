package org.axa.framework;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.axa.portal.claim.ADJ_portal_AMP_claimPortal;
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
import org.axa.portal.page.Portal_URL_setup;
import org.axa.portal.page.utility;
import org.axa.portal.validation.ADJ_portal_CommonValidation;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ADJ_portal_testCaseScript {
	InitializeBrowser executor=new InitializeBrowser();
	ADJ_portal_currentInsuranceScreen currentInsurnace=new ADJ_portal_currentInsuranceScreen();
	ADJ_portal_suspensionCertificateScreen suspension=new ADJ_portal_suspensionCertificateScreen();
	ADJ_portal_homePage home=new ADJ_portal_homePage();
	ADJ_portal_quotationScreen quotationPage=new ADJ_portal_quotationScreen();
	ADJ_portal_aboutMainDriverAndPolicyPlanScreen mainDriver=new ADJ_portal_aboutMainDriverAndPolicyPlanScreen();
	ADJ_portal_vehicleInformationScreen vehicleInfo=new ADJ_portal_vehicleInformationScreen();
	ADJ_portal_policyHolderInformationScreen policyHolder=new ADJ_portal_policyHolderInformationScreen();
	ADJ_portal_paymentInformationScreen payment=new ADJ_portal_paymentInformationScreen();
	CommonFunctions common=new CommonFunctions();
	ADJ_portal_CommonValidation validation=new ADJ_portal_CommonValidation();
	ADJ_portal_AMP_claimPortal claims=new ADJ_portal_AMP_claimPortal();
	ADJ_portal_ContractConfirmationScreen contractConfirmation=new ADJ_portal_ContractConfirmationScreen();
	Portal_URL_setup url=new Portal_URL_setup();

	public void End2End1(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException, ParseException {
		common.compareTwoDates("19-12-2023", "22-12-2023");
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());

		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			String quotationNumber=validation.getQuotationNumber(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Pass",quotationNumber);
		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}


	public void End2End(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		
		try {

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
			String quotationNumber=validation.getQuotationNumber(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);
			//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Pass",quotationNumber);
		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}



	public void allPageErrorValidation(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.ErrorMessageValidationOfHomePage(executor.page, data.getTestCaseID(),"これは必須フィールドです");
			home.enterInceptionDate(executor.page, data.getTestCaseID(), data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate());
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.errorMessageValidationOfCurrentInsurancePage(executor.page,data.getTestCaseID(), "これは必須フィールドです");
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.ErrorMessageValidationOfQuotationPage(executor.page, data.getTestCaseID(), "これは必須フィールドです");
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.ErrorMessageValidationOfMainDriverPage(executor.page, data.getTestCaseID(), "これは必須フィールドです");
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.ErrorMessageValidationOfVehicleInformationPage(executor.page, data.getTestCaseID(), "これは必須フィールドです");
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			policyHolder.ErrorMessageValidationOfPolicyHolderInformationPage(executor.page, data.getTestCaseID(), "これは必須フィールドです");
			policyHolder.e2eFlowOfPolicyHolderInformation(executor.page, data.getTestCaseID());
			policyHolder.errorMessageAndDefaultCheckboxSelectionValidationOfTandCPage(executor.page, data.getTestCaseID());
			policyHolder.termAndConditionsConfirmationPage(executor.page, data.getTestCaseID());
			contractConfirmation.errorMessageAndDefaultCheckboxSelectionValidationOfContractConfirmationPage(executor.page, data.getTestCaseID(), "次へ進む前に、契約内容をご確認の上、チェックボックスを入力ください");
			contractConfirmation.contractDetailsConfirmationPage(executor.page, data.getTestCaseID());
			payment.defaultPaymentOptionselectionValidation(executor.page, data.getTestCaseID());
			payment.e2eFlowOfPaymentPage(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void Individual_RT_01(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			home.functionalValidationOfHomePage(executor.page, data.getTestCaseID());
			home.purchaseTypeValidationForCar(executor.page);
			//home.purchaseTypeValidationForBike(executor.page, data.getTestCaseID());
			//common.clickAction(executor.page, Portal_ObjectRepository.CarInsuranceOption);
			home.ErrorMessageValidationOfHomePage(executor.page, data.getTestCaseID(),"これは必須フィールドです");
			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			validation.BackToPreviousScreen(executor.page, data.getTestCaseID());
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully Navigated to Vehicle screen from ContractType screen where filled data is already present", "ContractToVehiclePageNavigation",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	public void SME_RT_01(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_01(data);
	}

	public void Individual_RT_02(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.functionalValidationOfHomePage(executor.page, data.getTestCaseID());
			home.purchaseTypeValidationForBike(executor.page, data.getTestCaseID());
			home.ErrorMessageValidationOfHomePage(executor.page, data.getTestCaseID(),"これは必須フィールドです");
			common.clickAction(executor.page, Portal_ObjectRepository.CarInsuranceOption);
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			validation.BackToPreviousScreen(executor.page, data.getTestCaseID());
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully Navigated to Vehicle screen from ContractType screen where filled data is already present", "ContractToVehiclePageNavigation",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void Individual_RT_03(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		//executor.launchBrowser(data.getBrowserView(),data.getTestCaseID(),data.getInsurnaceFlowType());
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			currentInsurnace.functionalValidationOfCI(executor.page, data.getTestCaseID());
			validation.navigateToBeforeQuotationPageFromThreeMenuList(executor.page, data.getTestCaseID());
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			currentInsurnace.errorMessageValidationOfCurrentInsurancePage(executor.page, data.getTestCaseID(), "これは必須フィールドです");
			currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			validation.BackToPreviousScreen(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void SME_RT_02(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_03(data);
	}

	public void SME_RT_03(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void SME_RT_04(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		SME_RT_03(data);
	}

	public void Individual_RT_04(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			suspension.functionalValidationOfSuspendedPolicyPage(executor.page, data.getTestCaseID());
			validation.navigateToBeforeQuotationPageFromThreeMenuList(executor.page, data.getTestCaseID());
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
			suspension.errorMessageValidationOfSuspendedPolicyPage(executor.page, data.getTestCaseID(), "これは必須フィールドです");
			suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			validation.BackToPreviousScreen(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	public void SME_RT_05(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_04(data);
	}

	public void SME_RT_06(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			suspension.selectInsuranceSuspendedCompany(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void SME_RT_07(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			suspension.suspensionCertificateStartDate(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void SME_RT_08(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			suspension.suspensionCertificateRegistrationDate(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void Individual_RT_05(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			quotationPage.functionalValidationOfVehiclePage(executor.page, data.getTestCaseID());
			validation.navigateToBeforeQuotationPageFromThreeMenuList(executor.page, data.getTestCaseID());
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			Assertion.assertURLContains(executor.page, "Vehicle");
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Successfully navigated to Vehicle page from お見積りの前に page" , "RenavigationToVehiclePage", false);
			quotationPage.ErrorMessageValidationOfQuotationPage(executor.page, data.getTestCaseID(), "これは必須フィールドです");
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void SME_RT_09(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_05(data);
	}


	public void Individual_RT_06(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.ErrorMessageValidationOfMainDriverPage(executor.page, data.getTestCaseID(), "これは必須フィールドです");
			mainDriver.functionalValidationOfMainDriverPage(executor.page, data.getTestCaseID());
			validation.navigateToAboutVehiclePageFromThreeMenuList(executor.page, data.getTestCaseID());
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			Assertion.assertURLContains(executor.page, "Driver");
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Successfully navigated to Driver page from お車/バイクについて page" , "RenavigationToDriverPage", true);
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void SME_RT_10(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_06(data);
	}

	public void SME_RT_11(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.policyHolderIsMainDriverQuestionaries(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void Individual_RT_07(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.functionalValidationOfPolicyPlanPage(executor.page, data.getTestCaseID());
			validation.navigateToBeforeQuotationPageFromThreeMenuList(executor.page, data.getTestCaseID());
			validation.pageNavigation(executor.page, data.getTestCaseID(), "page6");
			/*
			 * while(true) { System.out.println(executor.page.url());
			 * if(Assertion.assertURLContains(executor.page, "page6")) {
			 * common.passStatusWithScreenshots(executor.page, data.getTestCaseID()
			 * ,"Successfully navigated to Quoatation page from お車/バイクについて page"
			 * ,"RenavigationToQuoatationPage", true); break; } else {
			 * common.clickAction(executor.page, Portal_ObjectRepository.proceedNext); } }
			 */
			mainDriver.e2eFlowOfPolicyPlan(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void SME_RT_12(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_07(data);
	}

	public void Individual_RT_08(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.functionalValidationOfVehicleInformationPage(executor.page, data.getTestCaseID());
			validation.navigateToQuotationPageFromThreeMenuList(executor.page, data.getTestCaseID());
			mainDriver.e2eFlowOfPolicyPlan(executor.page, data.getTestCaseID());
			//common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Successfully navigated to Before Application page from Quotation page" , "RenavigationToBeforeApplicationPage", true);
			vehicleInfo.ErrorMessageValidationOfVehicleInformationPage(executor.page, data.getTestCaseID(), "これは必須フィールドです");
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void SME_RT_13(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_08(data);
	}
	public void Individual_RT_09(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			policyHolder.functionalValidationOfPolicyHolderInformationPage(executor.page, data.getTestCaseID());
			validation.navigateToQuotationPageFromThreeMenuList(executor.page, data.getTestCaseID());
			mainDriver.e2eFlowOfPolicyPlan(executor.page, data.getTestCaseID());
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			policyHolder.ErrorMessageValidationOfPolicyHolderInformationPage(executor.page, data.getTestCaseID(), "これは必須フィールドです");
			validation.BackToPreviousScreen(executor.page, data.getTestCaseID());
			mainDriver.selectedPolicyPlanDialogConfirmation(executor.page, data.getTestCaseID());
			mainDriver.selectedEarthquackDialogConfirmation(executor.page, data.getTestCaseID());
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			policyHolder.legalEntityValueValidation(executor.page, data.getTestCaseID());
			policyHolder.inputFieldValidationOfContractorPage(executor.page, data.getTestCaseID());
			policyHolder.e2eFlowOfPolicyHolderInformation(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void SME_RT_14(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_09(data);
	}

	public void Individual_RT_10(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			policyHolder.e2eFlowOfPolicyHolderInformation(executor.page, data.getTestCaseID());
			policyHolder.functionalValidationOfTandCpage(executor.page, data.getTestCaseID());
			validation.navigateToBeforeApplicationFromThreeMenuList(executor.page, data.getTestCaseID());
			validation.pageNavigation(executor.page, data.getTestCaseID(),"page9");
			//common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			//common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			policyHolder.errorMessageAndDefaultCheckboxSelectionValidationOfTandCPage(executor.page, data.getTestCaseID());
			policyHolder.termAndConditionsConfirmationPage(executor.page, data.getTestCaseID());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void SME_RT_15(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_10(data);
	}

	public void Individual_RT_11(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			contractConfirmation.functionalValidationOfContractConfirmationPage(executor.page, data.getTestCaseID());
			contractConfirmation.contractConfirmationPageDataValidation(executor.page, data.getTestCaseID());
			contractConfirmation.errorMessageAndDefaultCheckboxSelectionValidationOfContractConfirmationPage(executor.page, data.getTestCaseID(), "次へ進む前に、契約内容をご確認の上、チェックボックスを入力ください");
			contractConfirmation.modifyDataLinkValidation(executor.page, data.getTestCaseID());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void SME_RT_16(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_11(data);
	}

	public void Individual_RT_12(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			payment.defaultPaymentOptionselectionValidation(executor.page, data.getTestCaseID());
			payment.selectPaymentMethod(executor.page, data.getTestCaseID());
			payment.CreditCardInformationFieldValidation(executor.page, data.getTestCaseID());
			payment.enterCreditCardInformation(executor.page, data.getTestCaseID());
			payment.finalPaymentConfirmation(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void SME_RT_17(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_12(data);
	}

	public void Individual_RT_13(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			payment.selectPaymentMethod(executor.page, data.getTestCaseID());
			payment.CreditCardInformationFieldValidation(executor.page, data.getTestCaseID());
			payment.enterCreditCardInformation(executor.page, data.getTestCaseID());
			payment.finalPaymentConfirmation(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void Individual_RT_14(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_13(data);
	}
	public void Individual_RT_15(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_13(data);
	}
	public void Individual_RT_16(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_13(data);
	}
	public void Individual_RT_17(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_13(data);
	}

	public void SME_RT_18(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_13(data);
	}
	public void SME_RT_19(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_13(data);
	}
	public void SME_RT_20(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_13(data);
	}
	public void SME_RT_21(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_13(data);
	}
	public void SME_RT_22(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_13(data);
	}

	public void Individual_RT_18(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			payment.functionalValidationOfPaymentPage(executor.page, data.getTestCaseID());
			payment.defaultPaymentOptionselectionValidation(executor.page, data.getTestCaseID());
			payment.selectPaymentMethod(executor.page, data.getTestCaseID());
			payment.konbiniOptionsValidation(executor.page, data.getTestCaseID());
			payment.SelectKonbiniStore(executor.page, data.getTestCaseID());
			payment.finalPaymentConfirmation(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			System.out.println(e);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void SME_RT_23(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_18(data);
	}

	public void Individual_RT_19(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.productSelection(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			home.insurancePurchaseType(executor.page, data.getTestCaseID(), data.getInsurancePurchaseType());
			if(utility.property.get("Environment").equals("26")) {
				home.enterInceptionDate(executor.page, data.getTestCaseID(), data.getInsurancePurchaseType(),"01-04-2024");	
			}else {
				home.enterInceptionDate(executor.page, data.getTestCaseID(), data.getInsurancePurchaseType(),CommonFunctions.getCurrentDate("dd-MM-yyyy"));
			}
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			Assertion.assertBytext(executor.page, "//div[contains(@data-aem,'DateDropdown_Error')]", "明日以降の日付をお選びください。");
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Error message is displayed when IDE is Pervious Day", "PreviousDayIDEValidation", true);
			home.enterInceptionDate(executor.page, data.getTestCaseID(), data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate());
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Enter IDE more than the 60 days and Procced t next button", "MoerThan60DayIDEValidation", true);
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);

			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.acceptSuspensionPageNavigationDialog(executor.page, data.getTestCaseID());
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.selectPolicyPlan(executor.page, data.getTestCaseID());
			//common.waitForSelector(executor.page, Portal_ObjectRepository.IDEMoreThan60DaysPopupConfirmation);
			Assertion.assertBytextContains(executor.page, Portal_ObjectRepository.IDEMoreThan60DaysPopup, "お申込みは保険始期日の60日前から始期日の前日までとなります。");
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Pop-up to save data when IDE is more than 60 days is displayed", "MoreThan60DaysIDEPOPup", false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void Individual_RT_20(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_19(data);
	}
	public void SME_RT_24(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_19(data);
	}
	public void SME_RT_25(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		Individual_RT_19(data);
	}

	public void SME_RT_26(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.selectPolicyPlan(executor.page,data.getTestCaseID());
			mainDriver.infoCorrectionDialog(executor.page,data.getTestCaseID());
			Assertion.assertBytext(executor.page, Portal_ObjectRepository.tentativErrorContent, "次の項目が仮となっています。チェックボックスを外し、修正をお願いします。");
			Assertion.assertBytext(executor.page, Portal_ObjectRepository.tentativErrorBox+"//li", "補償を開始したい日");
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Tentative error in case of 'I dont know functions' id displaying", "tentativeErrorValidation", false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_17073(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			if(data.getMemberType().equals("Login Member")) {
				home.loginToEmmaApplication(executor.page, data.getTestCaseID());
			}
			home.validateFlowText(executor.page, data.getTestCaseID());
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Login contract page", "loginContractPageNavigation",true);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_17873(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17073(data);
	}
	public void BAU_17874(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17073(data);
	}
	public void BAU_17965(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17073(data);
	}

	public void BAU_17966(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17073(data);
	}
	public void BAU_17875(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17073(data);
	}

	public void BAU_17970(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17073(data);
	}

	public void BAU_17073_1(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.normalFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.selectPolicyPlan(executor.page,data.getTestCaseID());
			mainDriver.infoCorrectionDialog(executor.page,data.getTestCaseID());
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Tentative error in case of 'I dont know functions' id displaying", "tentativeErrorValidation", false);
			home.validateFlowText(executor.page, data.getTestCaseID());
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Login contract page", "loginContractPageNavigation",true);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_17993(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17073_1(data);
	}
	public void BAU_17994(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17073_1(data);
	}
	public void BAU_17997(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17073_1(data);
	}

	public void BAU_17998(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17073_1(data);
	}
	public void BAU_17877(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17073_1(data);
	}

	public void BAU_17971(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17073_1(data);
	}
	public void BAU_17075(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			if(data.getMemberType().equals("Login Member")) {
				home.loginToEmmaApplication(executor.page, data.getTestCaseID());
			}

			home.validateFlowText(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			home.rejectSwithFlowPopup(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			home.acceptSwithFlowPopup(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	public void BAU_17955(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17075(data);
	}

	public void BAU_17956(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17075(data);
	}
	public void BAU_17974(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17075(data);
	}

	public void BAU_17975(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17075(data);
	}
	public void BAU_17984(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17075(data);
	}

	public void BAU_18016(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17075(data);
	}

	public void BAU_17075_1(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.selectPolicyPlan(executor.page,data.getTestCaseID());
			mainDriver.infoCorrectionDialog(executor.page,data.getTestCaseID());
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Tentative error in case of 'I dont know functions' id displaying", "tentativeErrorValidation", false);

			home.validateFlowText(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			home.rejectSwithFlowPopup(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			home.acceptSwithFlowPopup(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	public void BAU_18260(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17075_1(data);
	}

	public void BAU_18261(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17075_1(data);
	}
	public void BAU_18015(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17075_1(data);
	}

	public void BAU_18017(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17075_1(data);
	}
	public void BAU_17077(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			home.validationOfBikeButtonEnability(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18041(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17077(data);
	}

	public void BAU_18042(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17077(data);
	}
	public void BAU_18100(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17077(data);
	}

	public void BAU_18102(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17077(data);
	}

	public void BAU_17078(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			if(data.getMemberType().equals("Login Member")) {
				home.loginToEmmaApplication(executor.page, data.getTestCaseID());
			}
			validation.PageLogoValidation(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18152(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			validation.BackToPreviousScreen(executor.page, data.getTestCaseID());
			validation.PageLogoValidation(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	public void BAU_18156(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17078(data);
	}

	public void BAU_18158(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17078(data);
	}
	public void BAU_18161(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18152(data);
	}

	public void BAU_18159(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17078(data);
	}
	public void BAU_18160(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17078(data);
	}

	public void BAU_17079(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.internetEstimationContentValidation(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18178(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17079(data);
	}
	public void BAU_18179(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17079(data);
	}

	public void BAU_17240(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());

			currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18182(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17240(data);
	}
	public void BAU_18183(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17240(data);
	}
	public void BAU_18184(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17240(data);
	}
	public void BAU_18185(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17240(data);
	}
	public void BAU_18186(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17240(data);
	}
	public void BAU_18187(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17240(data);
	}

	public void BAU_17312(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			suspension.selectSuspensionReason(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18625(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17312(data);
	}
	public void BAU_18654(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17312(data);
	}
	public void BAU_18655(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17312(data);
	}
	public void BAU_18656(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17312(data);
	}
	public void BAU_18659(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17312(data);
	}

	public void BAU_17313(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			suspension.suspensionCertificateStartDate(executor.page, data.getTestCaseID());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18668(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17313(data);
	}
	public void BAU_18675(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17313(data);
	}
	public void BAU_18676(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17313(data);
	}
	public void BAU_18677(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17313(data);
	}
	public void BAU_18679(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17313(data);
	}
	public void BAU_18681(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17313(data);
	}

	public void BAU_17314(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			suspension.suspensionCertificateRegistrationDate(executor.page, data.getTestCaseID());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18723(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17314(data);
	}
	public void BAU_18724(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17314(data);
	}
	public void BAU_18728(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17314(data);
	}
	public void BAU_18729(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17314(data);
	}
	public void BAU_18731(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17314(data);
	}
	public void BAU_18732(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17314(data);
	}
	public void BAU_19042(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17314(data);
	}

	public void BAU_17315(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			quotationPage.QuestionariesvalidationOfSME(executor.page, data.getTestCaseID());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18495(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17315(data);
	}
	public void BAU_18496(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17315(data);
	}
	public void BAU_18497(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17315(data);
	}
	public void BAU_18622(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17315(data);
	}
	public void BAU_18617(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17315(data);
	}
	public void BAU_18620(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17315(data);
	}
	public void BAU_18621(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17315(data);
	}
	public void BAU_18619(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_17315(data);
	}

	public void BAU_18192(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.IsMainDriverPolicyHolderContentValidation(executor.page, data.getTestCaseID());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19114(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18192(data);
	}
	public void BAU_19115(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18192(data);
	}
	public void BAU_19116(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18192(data);
	}
	public void BAU_19117(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18192(data);
	}

	public void BAU_18193(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.policyHolderIsMainDriverQuestionaries(executor.page, data.getTestCaseID());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19139(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18193(data);
	}
	public void BAU_19141(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18193(data);
	}
	public void BAU_19154(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18193(data);
	}
	public void BAU_19157(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18193(data);
	}

	public void BAU_19158(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18193(data);
	}
	public void BAU_19159(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18193(data);
	}
	public void BAU_19160(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18193(data);
	}
	public void BAU_19161(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18193(data);
	}

	public void BAU_18194(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());

			if(mainDriver.CORPORATE_URL) {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.driverPrefectureSectionTitle, "今回お見積りのお車の保管場所となる都道府県は？");
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.driverPrefectureSection, data.getTestCaseID(), "Title as '今回お見積りのお車の保管場所となる都道府県は？' for prefection section is displayed for SME", "SMEprefectureSectionTitleValidation");
			}else {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.driverPrefectureSectionTitle, "主な運転者のお住まいの都道府県は？");
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.driverPrefectureSection, data.getTestCaseID(), "Title as '主な運転者のお住まいの都道府県は？' for prefection section is displayed for Personal", "PersonalprefectureSectionTitleValidation");
			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19311(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18194(data);
	}
	public void BAU_19312(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18194(data);
	}
	public void BAU_19321(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18194(data);
	}
	public void BAU_19324(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18194(data);
	}

	public void BAU_18195(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());

			if(mainDriver.CORPORATE_URL) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.driverBirthDateSection);
				Report.logger.pass("DOB selection field is hidden for corporate flow");
			}else if(data.getMemberType().equals("Non Login Member")) {
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.driverBirthDateSection);
				Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.driverBirthYear);
				Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.driverBirthMonth);
				Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.driverBirthDay);
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.driverBirthDateSection, data.getTestCaseID(), "DOB selection field is enabled for personal flow", "personalDOBselectionValidation");
			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19335(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18195(data);
	}
	public void BAU_19337(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18195(data);
	}
	public void BAU_19339(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18195(data);
	}
	public void BAU_19341(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18195(data);
	}

	public void BAU_18196(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());

			if(mainDriver.CORPORATE_URL) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.driverLicenceSection);
				Report.logger.pass("Licence selection field is hidden for corporate flow");
			}else {
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.driverLicenceSection);
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.driverLicenceSection, data.getTestCaseID(), "Licence selection field is enabled for personal flow", "personalLicenceselectionValidation");
			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19370(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18196(data);
	}
	public void BAU_19372(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18196(data);
	}
	public void BAU_19385(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18196(data);
	}
	public void BAU_19391(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18196(data);
	}

	public void BAU_18197(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());

			mainDriver.driverAgeLimitModelContentValidation(executor.page, data.getTestCaseID());
			mainDriver.driverAgeRangeOptionValidation(executor.page, data.getTestCaseID());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19242(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18197(data);
	}
	public void BAU_19247(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18197(data);
	}
	public void BAU_19254(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18197(data);
	}
	public void BAU_19256(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18197(data);
	}

	public void BAU_18856(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.genderSectionValidation(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19849(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18856(data);
	}
	public void BAU_19879(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18856(data);
	}
	public void BAU_19891(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18856(data);
	}
	public void BAU_19892(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18856(data);
	}

	public void BAU_18865(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_20561(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18865(data);
	}
	public void BAU_20562(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18865(data);
	}
	public void BAU_20563(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18865(data);
	}
	public void BAU_20564(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18865(data);
	}
	public void BAU_20566(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18865(data);
	}
	public void BAU_20567(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18865(data);
	}
	public void BAU_20568(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18865(data);
	}
	public void BAU_20569(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18865(data);
	}

	public void BAU_20570(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18865(data);
	}
	public void BAU_20571(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18865(data);
	}
	public void BAU_20572(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18865(data);
	}
	public void BAU_20573(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18865(data);
	}

	public void BAU_18845(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.contractor_statusPosition);
			Assertion.assertIsNotChecked(executor.page, Portal_ObjectRepository.contractor_companyInfoSection+"//span[text()='法人名の後']");
			Assertion.assertIsNotChecked(executor.page, Portal_ObjectRepository.contractor_companyInfoSection+"//span[text()='法人名の前']");
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.contractor_companyInfoSection, data.getTestCaseID(), "Corporate status Position field isndisplayed and by default non of the options are selected", "corporateStatusPositionFieldValidation");
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	
	public void BAU_20550(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18845(data);
	}
	public void BAU_20586(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18845(data);
	}
	public void BAU_20588(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18845(data);
	}
	public void BAU_20597(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18845(data);
	}
	public void BAU_20598(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18845(data);
	}
	public void BAU_20600(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18845(data);
	}
	public void BAU_20616(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18845(data);
	}
	public void BAU_20621(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18845(data);
	}
	public void BAU_20623(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18845(data);
	}
	public void BAU_20628(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18845(data);
	}

	public void BAU_18873(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());

			common.enterText(executor.page,  Portal_ObjectRepository.postalCode, "1243467");
			common.clickAction(executor.page, Portal_ObjectRepository.searchAddress);
			Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.unableToFindPostalCode);
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "pop-up regarding unable to find postal address is displayed", "unableToFindPostalAddress", false);
			common.clickAction(executor.page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_21228(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18873(data);
	}
	public void BAU_21229(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18873(data);
	}
	public void BAU_21230(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18873(data);
	}
	public void BAU_21231(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18873(data);
	}

	public void BAU_18906(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());

			common.selectDropdownByText(executor.page, Portal_ObjectRepository.contractor_legalEntityType, "その他");
			Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.contractor_CorporateOther);
			Assertion.assertHasAttribute(executor.page, Portal_ObjectRepository.contractor_CorporateOther, "placeholder","全角9文字以内");
			Assertion.assertBytext(executor.page, Portal_ObjectRepository.contractor_CorporateOther+"/../..//span", "法人格をご入力ください。");
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.contractor_companyInfoSection, data.getTestCaseID(), "Behaviour of Legal entity type その他 validation", "その他LegalEntityFieldValidation");
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_20636(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18906(data);
	}
	public void BAU_20645(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18906(data);
	}

	public void BAU_18907(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.contractor_statusPosition);
			Assertion.assertIsNotChecked(executor.page, Portal_ObjectRepository.contractor_companyInfoSection+"//span[text()='法人名の後']");
			Assertion.assertIsNotChecked(executor.page, Portal_ObjectRepository.contractor_companyInfoSection+"//span[text()='法人名の前']");
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.contractor_companyInfoSection, data.getTestCaseID(), "Corporate status Position field isndisplayed and by default non of the options are selected", "corporateStatusPositionFieldValidation");
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_20667(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18907(data);
	}
	public void BAU_20669(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18907(data);
	}

	public void BAU_18908(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.contractor_delegateInfoSection);
			common.clickAction(executor.page, Portal_ObjectRepository.contractor_delegateInfoSection+"//span[text()='（委任を受けている方＊）']");
			common.enterText(executor.page, Portal_ObjectRepository.contractor_delegateLastNameKanji, "川村");
			common.enterText(executor.page, Portal_ObjectRepository.contractor_delegateFirstNameKanji, "佐藤");
			common.enterText(executor.page, Portal_ObjectRepository.contractor_delegateLastNameKana, "カワムラ");
			common.enterText(executor.page, Portal_ObjectRepository.contractor_delegateFirstNameKana, "サト");
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.contractor_companyInfoSection, data.getTestCaseID(), "Representative filed validation after clicking on いいえ button ", "いいえRepresentativeFieldValidation");
			common.clickAction(executor.page, Portal_ObjectRepository.contractor_delegateInfoSection+"//span[text()='（代表者本人）']");
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.contractor_companyInfoSection, data.getTestCaseID(), "Representative filed validation after clicking on はい button ", "はいRepresentativeFieldValidation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_20684(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18908(data);
	}
	public void BAU_20685(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18908(data);
	}

	public void BAU_18910(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_21416(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18910(data);
	}
	public void BAU_21421(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18910(data);
	}
	public void BAU_21422(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18910(data);
	}
	public void BAU_21424(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18910(data);
	}

	public void BAU_18911(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			policyHolder.ErrorMessageValidationOfPolicyHolderInformationPage(executor.page, data.getTestCaseID(), "これは必須フィールドです");
			policyHolder.inputFieldValidationOfContractorPage(executor.page, data.getTestCaseID());
			//common.selectDropdownByText(executor.page, Portal_ObjectRepository.contractor_legalEntityType, "-");
			//policyHolder.ErrorMessageValidationOfPolicyHolderInformationPage(executor.page, data.getTestCaseID(), "これは必須フィールドです");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_21561(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18911(data);
	}
	public void BAU_21562(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18911(data);
	}
	public void BAU_21564(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18911(data);
	}
	public void BAU_22397(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18911(data);
	}

	public void BAU_18200(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageAgeRangeSectionValidation(executor.page, data.getTestCaseID());
			if(mainDriver.CORPORATE_URL) {
				mainDriver.tarrifPageSectionInfoIconClick(executor.page, data.getTestCaseID(), "運転者年齢条件特約", 2);
				Assertion.assertIsNotAttached(executor.page, "(//div[contains(text(),'運転者限定特約')]/../..//select[@class='form-select'])");
				Report.logger.info("運転者限定特約 section details and dropdown is not present");
				mainDriver.tarrifPageDropdownDefaultContentValidation(executor.page, data.getTestCaseID(), "運転者年齢条件特約");
			}else {
				mainDriver.tarrifPageSectionInfoIconClick(executor.page, data.getTestCaseID(), "運転者限定特約", 2);
				mainDriver.tarrifPageDropdownDefaultContentValidation(executor.page, data.getTestCaseID(), "運転者限定特約");
				mainDriver.tarrifPageDropdownDefaultContentValidation(executor.page, data.getTestCaseID(), "運転者年齢条件特約");
			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19294(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18200(data);
	}
	public void BAU_19303(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18200(data);
	}

	public void BAU_18200_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageAgeRangeSectionValidation(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18201(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			if(mainDriver.CORPORATE_URL) {
				Assertion.assertBytext(executor.page,"(//div[text()='人身傷害補償特約']/../..//li)[4]", "搭乗中のみ補償特約   あり");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='人身傷害補償特約']/../..",  data.getTestCaseID(), "搭乗中のみ補償特約(Only on board coverage)” is always on for plan2 of 人身傷害補償特約 section for SME flow", "搭乗中のみ補償特約OnValidation");
				mainDriver.tarrifPageSectionInfoIconClick(executor.page, data.getTestCaseID(), "人身傷害補償特約", 2);
				mainDriver.tarrifPageDropdownDefaultContentValidation(executor.page, data.getTestCaseID(), "人身傷害補償特約");
				Assertion.assertHasCount(executor.page, "(//div[text()='人身傷害補償特約']/../..//select[@class='form-select'])/..//following-sibling::div", 1);
				Assertion.assertBytext(executor.page, "(//div[text()='人身傷害補償特約']/../..//select[@class='form-select'])/..//following-sibling::div", "あり");
				common.takeScreenShotsOfComponent(executor.page, "(//div[text()='人身傷害補償特約']/../..//select[@class='form-select'])/../..",  data.getTestCaseID(), "搭乗中のみ補償特約(Only on board coverage)” is always on for plan2 of 人身傷害補償特約 section dropdown for SME flow", "搭乗中のみ補償特約OnValidationInDropdown");

			}else {
				Assertion.assertBytext(executor.page,"(//div[text()='人身傷害補償特約']/../..//li)[4]", "搭乗中のみ補償特約   なし");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='人身傷害補償特約']/../..",  data.getTestCaseID(), "搭乗中のみ補償特約(Only on board coverage)” is always off for plan2 of 人身傷害補償特約 section for personal flow", "搭乗中のみ補償特約OFFValidation");
				mainDriver.tarrifPageSectionInfoIconClick(executor.page, data.getTestCaseID(), "人身傷害補償特約", 2);
				mainDriver.tarrifPageDropdownDefaultContentValidation(executor.page, data.getTestCaseID(), "人身傷害補償特約");
				Assertion.assertHasCount(executor.page, "(//div[text()='人身傷害補償特約']/../..//select[@class='form-select'])/..//following-sibling::div/div", 2);
				Assertion.assertBytext(executor.page, "(//div[text()='人身傷害補償特約']/../..//select[@class='form-select'])/..//following-sibling::div/div[2]", "なし");
				common.takeScreenShotsOfComponent(executor.page, "(//div[text()='人身傷害補償特約']/../..//select[@class='form-select'])/../..",  data.getTestCaseID(), "搭乗中のみ補償特約(Only on board coverage)” is always selected off button for plan2 of 人身傷害補償特約 section dropdown for SME flow", "搭乗中のみ補償特約OFFValidationInDropdown");
			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19362(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18201(data);
	}
	public void BAU_19376(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18201(data);
	}

	public void BAU_18201_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			if(utility.homePageMap.get(data.getTestCaseID()).getBrowserView().equals("Mobile") ) {
				//mainDriver.selectPolicyPlan(executor.page, data.getTestCaseID());
				if(mainDriver.CORPORATE_URL) {

					Assertion.assertIsAttached(executor.page,"(//p[text()='搭乗中のみ補償特約']/../..)[1]");
					Assertion.assertBytext(executor.page,"(//p[text()='搭乗中のみ補償特約']/../..)[1]//p[2]", "あり");
					executor.page.locator("(//p[text()='搭乗中のみ補償特約']/../..)[1]//p[2]").highlight();
					common.takeScreenShotsOfComponent(executor.page, "//p[text()='搭乗中のみ補償特約']/../../..",  data.getTestCaseID(), "搭乗中のみ補償特約 has value あり for SME flow", "搭乗中のみ補償特約ValueValidation");

				}else {
					Assertion.assertIsAttached(executor.page,"(//p[text()='搭乗中のみ補償特約']/../..)[1]");
					if(common.getText(executor.page, "(//p[text()='人身傷害補償特約']/../..)[1]//p[2]").equals("無制限")) {
						Assertion.assertBytext(executor.page,"(//p[text()='搭乗中のみ補償特約']/../..)[1]//p[2]", "-");
						executor.page.locator("(//p[text()='搭乗中のみ補償特約']/../..)[1]//p[2]").highlight();
						common.takeScreenShotsOfComponent(executor.page, "//p[text()='搭乗中のみ補償特約']/../../..",  data.getTestCaseID(), "搭乗中のみ補償特約 has value - for Individual flow", "搭乗中のみ補償特約ValueValidation");
					}else {
						Assertion.assertBytext(executor.page,"(//p[text()='搭乗中のみ補償特約']/../..)[1]//p[2]", "あり");
						executor.page.locator("(//p[text()='搭乗中のみ補償特約']/../..)[1]//p[2]").highlight();
						common.takeScreenShotsOfComponent(executor.page, "//p[text()='搭乗中のみ補償特約']/../../..",  data.getTestCaseID(), "搭乗中のみ補償特約 has value あり for Individual flow", "搭乗中のみ補償特約ValueValidation");
					}
				}
			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19365(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18201_SP(data);
	}
	public void BAU_19380(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18201_SP(data);
	}

	public void BAU_18202(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			if(mainDriver.CORPORATE_URL) {
				Assertion.assertBytext(executor.page,"(//div[text()='無保険車傷害保険']/../..//li)[4]", "搭乗中のみ補償特約   あり");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='無保険車傷害保険']/../..",  data.getTestCaseID(), "搭乗中のみ補償特約(Only on board coverage)” is always on for plan2 of 無保険車傷害保険 section for Personal flow", "搭乗中のみ補償特約OnValidation");
				mainDriver.tarrifPageSectionInfoIconClick(executor.page, data.getTestCaseID(), "搭乗者傷害保険", 2);
				Assertion.assertBytext(executor.page, "//div[text()='無保険車傷害保険']/../following::div[1]/div[2]//span[text()='搭乗中のみ補償特約']/../following::div[1]", "あり");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='無保険車傷害保険']/../following::div[1]/div[2]/../..",  data.getTestCaseID(), "搭乗中のみ補償特約(Only on board coverage)” is always on for plan2 of 無保険車傷害保険 section dropdown for Personal flow", "搭乗中のみ補償特約OnValidationInDropdown");

			}else {
				Assertion.assertIsNotAttached(executor.page,"(//div[text()='無保険車傷害保険']/../..//li)[4]");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='無保険車傷害保険']/../..",  data.getTestCaseID(), "搭乗中のみ補償特約(Only on board coverage)” is not present for plan2 of 無保険車傷害保険 section for Personal flow", "搭乗中のみ補償特約OFFValidation");
				mainDriver.tarrifPageSectionInfoIconClick(executor.page, data.getTestCaseID(), "搭乗者傷害保険", 2);
				Assertion.assertIsNotAttached(executor.page, "//div[text()='無保険車傷害保険']/../following::div[1]/div[2]//span[text()='搭乗中のみ補償特約']/../following::div[1]");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='無保険車傷害保険']/../following::div[1]/div[2]/../..",  data.getTestCaseID(), "搭乗中のみ補償特約(Only on board coverage)” is not attached for plan2 of 無保険車傷害保険 section dropdown for Personal flow", "搭乗中のみ補償特約OFFValidationInDropdown");
			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19411(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18202(data);
	}
	public void BAU_19430(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18202(data);
	}

	public void BAU_18202_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			if(utility.homePageMap.get(data.getTestCaseID()).getBrowserView().equals("Mobile") ) {
				mainDriver.selectPolicyPlan(executor.page, data.getTestCaseID());
				if(mainDriver.CORPORATE_URL) {

					Assertion.assertIsAttached(executor.page,"(//p[text()='搭乗中のみ補償特約']/../..)[2]");
					Assertion.assertBytext(executor.page,"(//p[text()='搭乗中のみ補償特約']/../..)[2]//p[2]", "あり");
					executor.page.locator("(//p[text()='搭乗中のみ補償特約']/../..)[2]").highlight();
					common.takeScreenShotsOfComponent(executor.page, "//p[text()='搭乗中のみ補償特約']/../../..",  data.getTestCaseID(), "搭乗中のみ補償特約 has value あり for SME flow", "搭乗中のみ補償特約ValueValidation");

				}else {
					Assertion.assertIsNotAttached(executor.page,"(//p[text()='搭乗中のみ補償特約']/../..)[2]");
					common.takeScreenShotsOfComponent(executor.page, "//p[text()='搭乗中のみ補償特約']/../../..",  data.getTestCaseID(), "搭乗中のみ補償特約 is not atached under 無保険車傷害保険 of Individual flow", "搭乗中のみ補償特約NotAttachedValidation");
				}
			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19429(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18202_SP(data);
	}
	public void BAU_19431(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18202_SP(data);
	}

	public void BAU_18203(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			if(mainDriver.CORPORATE_URL) {
				Assertion.assertIsAttached(executor.page, "//div[text()='弁護士費用等補償特約（自動車事故）']/../..");
				Assertion.assertIsNotAttached(executor.page, "//div[text()='日常生活賠償責任保険特約（示談交渉付）']/../..");
				Report.logger.info("日常生活賠償責任保険特約（示談交渉付） Item is not present for SME flow of その他の追加補償 Section ");
				Assertion.assertIsNotAttached(executor.page, "//div[text()='アクサ安心プラス']/../..");
				Report.logger.info("アクサ安心プラス Item is not present for SME flow of その他の追加補償 Section ");
				Assertion.assertIsAttached(executor.page, "//div[text()='EV充電設備補償特約']/../..");
				Assertion.assertIsAttached(executor.page, "//div[text()='臨時代替自動車補償特約（自動セット）']/../..");
				Assertion.assertIsNotAttached(executor.page, "//div[text()='他車運転危険補償特約（自動セット）']/../..");
				Report.logger.info("他車運転危険補償特約（自動セット）'] Item is not present for SME flow of その他の追加補償 Section ");
				Assertion.assertIsNotAttached(executor.page, "//div[text()='弁護士費用等補償特約']/../..");
				Report.logger.info("弁護士費用等補償特約 Item is not present for SME flow of その他の追加補償 Section ");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='弁護士費用等補償特約（自動車事故）']/../../..",  data.getTestCaseID(),"Items(sub sections) validation of その他の追加補償 section of SME flow", "その他の追加補償SectionItemsValidation");

				mainDriver.tarrifPageSectionInfoIconClick(executor.page, data.getTestCaseID(), "弁護士費用等補償特約（自動車事故）", 2);
				Assertion.assertIsChecked(executor.page, "//div[text()='弁護士費用等補償特約（自動車事故）']/../..//input");
				Report.logger.info("By default check-box for あり for 弁護士費用等補償特約（自動車事故） is checked");
				Assertion.assertBytext(executor.page, "(//div[text()='EV充電設備補償特約']/../..//li)[2]", "ご入力のお車にはEV充電設備補償特約をお付けいただけません。");
				Assertion.assertBytext(executor.page, "(//div[text()='臨時代替自動車補償特約（自動セット）']/../..//p)[2]", "あり");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='臨時代替自動車補償特約（自動セット）']/../../..",  data.getTestCaseID(),"content validation for 弁護士費用等補償特約(自動車事故), EV充電設備補償特約 and 臨時代替自動車補償特約(自動セット) of  その他の追加補償 section of SME flow after clicking on Info icon", "その他の追加補償SubsectionInfoValidation");


			}else {

				Assertion.assertIsNotAttached(executor.page, "//div[text()='弁護士費用等補償特約（自動車事故）']/../..");
				Report.logger.info("弁護士費用等補償特約（自動車事故） Item is not present for Perosnal flow of その他の追加補償 Section ");
				Assertion.assertIsAttached(executor.page, "//div[text()='弁護士費用等補償特約']/../..");
				Assertion.assertIsAttached(executor.page, "//div[text()='日常生活賠償責任保険特約（示談交渉付）']/../..");
				Assertion.assertIsAttached(executor.page, "//div[text()='アクサ安心プラス']/../..");
				Assertion.assertIsAttached(executor.page, "//div[text()='EV充電設備補償特約']/../..");
				Assertion.assertIsAttached(executor.page, "//div[text()='ファミリーバイク特約']/../..");
				Assertion.assertIsAttached(executor.page, "//div[text()='他車運転危険補償特約（自動セット）']/../..");
				Assertion.assertIsNotAttached(executor.page, "//div[text()='臨時代替自動車補償特約（自動セット）']/../..");
				Report.logger.info("臨時代替自動車補償特約（自動セット） Item is not present for Perosnal flow of その他の追加補償 Section ");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='弁護士費用等補償特約']/../../..",  data.getTestCaseID(),"Items(sub sections) validation of その他の追加補償 section of Personal flow", "その他の追加補償SectionItemsValidation");

				mainDriver.tarrifPageSectionInfoIconClick(executor.page, data.getTestCaseID(), "弁護士費用等補償特約", 2);
				Assertion.assertIsChecked(executor.page, "//div[text()='弁護士費用等補償特約']/../..//input");
				Report.logger.info("By default check-box for あり for 弁護士費用等補償特約 is checked");
				Assertion.assertIsChecked(executor.page, "//div[text()='日常生活賠償責任保険特約（示談交渉付）']/../..//input");
				Report.logger.info("By default check-box for あり for 日常生活賠償責任保険特約（示談交渉付）is checked");

				Assertion.assertIsNotChecked(executor.page, "(//p[text()='ファミリープラス']/../..//input)[1]");
				Report.logger.info("By default check-box for あり for ファミリープラスis not checked of アクサ安心プラス section");
				Assertion.assertIsNotChecked(executor.page, "(//p[text()='レディースプラス']/../..//input)[1]");
				Report.logger.info("By default check-box for あり for レディースプラス is not checked of アクサ安心プラス section");
				Assertion.assertIsNotChecked(executor.page, "(//p[text()='ペットプラス']/../..//input)[1]");
				Report.logger.info("By default check-box for あり for ペットプラス is not checked of アクサ安心プラス section");
				Assertion.assertBytext(executor.page, "(//div[text()='EV充電設備補償特約']/../..//li)[2]", "ご入力のお車にはEV充電設備補償特約をお付けいただけません。");
				Assertion.assertIsNotChecked(executor.page, "//div[text()='ファミリーバイク特約']/../..//input");
				Report.logger.info("By default check-box for あり for ファミリーバイク特約 is not checked");
				Assertion.assertBytext(executor.page, "(//div[text()='他車運転危険補償特約（自動セット）']/../..//p)[2]", "あり");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='弁護士費用等補償特約']/../../..",  data.getTestCaseID(),"content validation for subcontent of  その他の追加補償 section of Personal flow after clicking on Info icon", "その他の追加補償SubsectionInfoValidation");

			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19471(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18203(data);
	}
	public void BAU_19474(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18203(data);
	}

	public void BAU_18203_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			if(utility.homePageMap.get(data.getTestCaseID()).getBrowserView().equals("Mobile") ) {
				//mainDriver.selectPolicyPlan(executor.page, data.getTestCaseID());
				Assertion.assertIsAttached(executor.page, "(//p[text()='その他の追加補償']/../..)");
				//List<Locator>listElement=executor.page.locator("//p[text()='その他の追加補償']/../following-sibling::div//li").all();

				if(mainDriver.CORPORATE_URL) {
					Assertion.assertHasCount(executor.page, "//p[text()='その他の追加補償']/../following-sibling::div//li", 3);
					Assertion.assertBytext(executor.page, "//p[text()='その他の追加補償']/../following-sibling::div//li[1]//p[1]", "弁護士費用等補償特約（自動車事故）");
					Assertion.assertBytext(executor.page, "//p[text()='その他の追加補償']/../following-sibling::div//li[2]//p[1]", "EV充電設備補償特約");
					Assertion.assertBytext(executor.page, "//p[text()='その他の追加補償']/../following-sibling::div//li[3]//p[1]", "臨時代替自動車補償特約（自動セット）");
					common.takeScreenShotsOfComponent(executor.page, "(//p[text()='その他の追加補償']/../..)",  data.getTestCaseID(), "Subsections items validation of その他の追加補償 section", "その他の追加補償SubsectionsItemsValidation");

				}else {
					Assertion.assertHasCount(executor.page, "//p[text()='その他の追加補償']/../following-sibling::div//li", 6);
					Assertion.assertBytext(executor.page, "//p[text()='その他の追加補償']/../following-sibling::div//li[1]//p[1]", "弁護士費用等補償特約");
					Assertion.assertBytext(executor.page, "//p[text()='その他の追加補償']/../following-sibling::div//li[2]//p[1]", "日常生活賠償責任保険特約（示談交渉付）");
					Assertion.assertBytext(executor.page, "//p[text()='その他の追加補償']/../following-sibling::div//li[3]//p[1]", "アクサ安心プラス");
					Assertion.assertBytext(executor.page, "//p[text()='その他の追加補償']/../following-sibling::div//li[4]//p[1]", "EV充電設備補償特約");
					Assertion.assertBytext(executor.page, "//p[text()='その他の追加補償']/../following-sibling::div//li[5]//p[1]", "ファミリーバイク特約");
					Assertion.assertBytext(executor.page, "//p[text()='その他の追加補償']/../following-sibling::div//li[6]//p[1]", "他車運転危険補償特約（自動セット）");
					common.takeScreenShotsOfComponent(executor.page, "(//p[text()='その他の追加補償']/../..)",  data.getTestCaseID(), "Subsections items validation of その他の追加補償 section", "その他の追加補償SubsectionsItemsValidation");
				}
			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19473(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18203_SP(data);
	}
	public void BAU_19475(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18203_SP(data);
	}

	public void BAU_18198(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());


			Assertion.assertIsAttached(executor.page, "//div[text()='運転される方の範囲']/../..");
			common.takeScreenShotsOfComponent(executor.page, "//div[text()='運転される方の範囲']/../..",  data.getTestCaseID(), "image of 運転される方の範囲 is displayed successfully", "運転される方の範囲ImageValidation");
			common.clickAction(executor.page, "//div[text()='運転される方の範囲']/..//p[text()='詳細']");
			Assertion.assertBytext(executor.page, "//p[text()='運転者限定特約']/../..//div[2]/p[1]","「運転者限定特約」とは、運転者の範囲を記名被保険者との続柄で限定する特約です。\r\n"
					+ "お車を運転する方を主な運転者（記名被保険者）本人とその配偶者にのみ限定することで保険料を節約することができます。\r\n"
					+ "※この特約はバイク保険および法人契約には適用することができません。");
			common.takeScreenShotsOfComponent(executor.page, "//p[text()='運転者限定特約']/../..",  data.getTestCaseID(), "'この特約はバイク保険および法人契約には適用することができません。' text validation for 運転者限定特約 of 運転される方の範囲 section", "運転される方の範囲textValidation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19912(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18198(data);
	}
	public void BAU_19933(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18198(data);
	}

	public void BAU_18198_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());

			if(utility.homePageMap.get(data.getTestCaseID()).getBrowserView().equals("Mobile")) {
				Assertion.assertIsAttached(executor.page, "//p[text()='運転される方の範囲']/../..");
				common.takeScreenShotsOfComponent(executor.page, "//p[text()='運転される方の範囲']/../..",  data.getTestCaseID(), "image of 運転される方の範囲 is displayed successfully", "運転される方の範囲ImageValidation");
				common.clickAction(executor.page, "//p[text()='運転される方の範囲']/../../div[1]//img");
				if(mainDriver.CORPORATE_URL) {
					Assertion.assertIsNotAttached(executor.page, "//div[text()='運転者限定特約']/../../..");
					Report.logger.info("運転者限定特約 section is not present inside 運転される方の範囲 model pop-up for Corporate");
				}else {
					if(data.getInsurnaceProductType().equals("")) {
						Assertion.assertIsAttached(executor.page, "//div[text()='運転者年齢条件特約']/../../..");
						Report.logger.info("'“※この特約はバイク保険には適用することができません。' text is not present under 運転者限定特約 section of personal flow");
						common.takeScreenShotsOfComponent(executor.page, "//div[text()='運転者年齢条件特約']/../../..",  data.getTestCaseID(), "運転者年齢条件特約 section is present present inside 運転される方の範囲 model pop-up for Personal Flow", "運転者年齢条件特約textValidation");
						Assertion.assertIsNotAttached(executor.page, "//div[text()='運転者限定特約']/../../..");
						Report.logger.info("運転者限定特約 is not present under 運転者限定特約 section of BIKE personal flow");
					}else {
						Assertion.assertIsAttached(executor.page, "//div[text()='運転者限定特約']/../../..");
						Assertion.assertBytextNotContains(executor.page, "(//div[text()='運転者限定特約']/../../..//p)[3]", "この特約はバイク保険には適用することができません。");
						Report.logger.info("'“※この特約はバイク保険には適用することができません。' text is not present under 運転者限定特約 section of personal flow");
						common.takeScreenShotsOfComponent(executor.page, "//div[text()='運転者限定特約']/../../..",  data.getTestCaseID(), "運転者限定特約 section is not present inside 運転される方の範囲 model pop-up for Personal Flow", "運転者限定特約textValidation");
					}
				}


			}
			common.takeScreenShotsOfComponent(executor.page, "//div[@data-aem='MobilePage_ConfirmObjectModal_AdjTariffPcModalBox']",  data.getTestCaseID(), "運転される方の範囲 model pop-up content validation", "運転される方の範囲contentValidation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19934(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18198_SP(data);
	}

	public void BAU_18199(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());

			if(utility.homePageMap.get(data.getTestCaseID()).getBrowserView().equals("Mobile")) {	
				Assertion.assertIsAttached(executor.page, "//p[text()='ご自身や同乗者への補償']/../..");
				common.takeScreenShotsOfComponent(executor.page, "//p[text()='ご自身や同乗者への補償']/../..",  data.getTestCaseID(), "image of ご自身や同乗者への補償 is displayed successfully", "ご自身や同乗者への補償ImageValidation");
				common.clickAction(executor.page, "//p[text()='ご自身や同乗者への補償']/../../div[1]//img");
				Assertion.assertBytextContains(executor.page, "//div[text()='人身傷害補償特約']/../../../p[1]","※法人契約の場合「人身傷害補償特約（搭乗中のみ補償）」のみとなります。");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='人身傷害補償特約']/../../../p[1]",  data.getTestCaseID(), "'※法人契約の場合「人身傷害補償特約（搭乗中のみ補償）」のみとなります。' text validation for 人身傷害補償特約of ご自身や同乗者への補償 section", "人身傷害補償特約textValidation");		

			}else {
				Assertion.assertIsAttached(executor.page, "//div[text()='ご自身や同乗者への補償']/../..");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='ご自身や同乗者への補償']/../..",  data.getTestCaseID(), "image of ご自身や同乗者への補償 is displayed successfully", "ご自身や同乗者への補償ImageValidation");
				common.clickAction(executor.page, "//div[text()='ご自身や同乗者への補償']/..//p[text()='詳細']");
				Assertion.assertBytext(executor.page, "//p[text()='人身傷害補償特約（選べる基本補償）']/../..//div[2]","運転者や同乗者が事故により死傷された場合に、その治療費用などの実費に対して保険金をお支払いします。過失割合にかかわらず、示談解決を待たずにスピーディーに保険金を受け取れます。\r\n"
						+ "人身傷害補償には、歩行中の車との事故なども補償する「人身傷害補償特約」と、ご契約のお車に搭乗中の事故に補償を限定する「人身傷害補償特約（搭乗中のみ補償）」の2つのタイプがあります。\r\n"
						+ "\r\n"
						+ "※法人契約の場合「人身傷害補償特約（搭乗中のみ補償）」のみとなります。\r\n"
						+ "※主な運転者や、そのご家族の方が人身傷害補償特約をセットした保険契約に加入されている場合には、補償内容が重複することがありますので、ご注意ください。");
				common.takeScreenShotsOfComponent(executor.page, "//p[text()='人身傷害補償特約（選べる基本補償）']/../..",  data.getTestCaseID(), "'※法人契約の場合「人身傷害補償特約（搭乗中のみ補償）」のみとなります。' text validation for 人身傷害補償特約（選べる基本補償） of ご自身や同乗者への補償 section", "人身傷害補償特約（選べる基本補償）textValidation");
			}

			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Successfully navigated to model block of ご自身や同乗者への補償", "ご自身や同乗者への補償ModelBlockNavigation", true);			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19940(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18199(data);
	}
	public void BAU_19950(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18199(data);
	}
	public void BAU_19952(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18199(data);
	}
	public void BAU_18199_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18199(data);
	}

	public void BAU_18839(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());

			if(utility.homePageMap.get(data.getTestCaseID()).getBrowserView().equals("Mobile")) {
				Assertion.assertIsAttached(executor.page, "//p[text()='ご自身や同乗者への補償']/../..");
				common.takeScreenShotsOfComponent(executor.page, "//p[text()='ご自身や同乗者への補償']/../..",  data.getTestCaseID(), "image of ご自身や同乗者への補償 is displayed successfully", "ご自身や同乗者への補償ImageValidation");
				common.clickAction(executor.page, "//p[text()='ご自身や同乗者への補償']/../../div[1]//img");
				Assertion.assertIsAttached(executor.page, "//div[text()='無保険車傷害保険']/../../../..");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='無保険車傷害保険']/../../../..",  data.getTestCaseID(), "無保険車傷害保険 section is attached inside ご自身や同乗者への補償 model pop-up", "無保険車傷害保険SectionValidation");	
				if(mainDriver.CORPORATE_URL) {
					Assertion.assertIsChecked(executor.page, "//div[text()='無保険車傷害保険']/../../..//span[text()='搭乗中のみ補償特約']/..");
					common.takeScreenShotsOfComponent(executor.page, "//div[text()='無保険車傷害保険']/../../..//span[text()='搭乗中のみ補償特約']/..",  data.getTestCaseID(), "搭乗中のみ補償特約 text is added and by default checked is checked for 無保険車傷害保険", "搭乗中のみ補償特約TextValidation");	
				}else {
					Assertion.assertIsNotAttached(executor.page, "//div[text()='無保険車傷害保険']/../../..//span[text()='搭乗中のみ補償特約']/..");
					Report.logger.info("搭乗中のみ補償特約 text is not present under 無保険車傷害保険 for Personal flow ");
				}
				Assertion.assertBytextContains(executor.page, "//div[text()='無保険車傷害保険']/../../../p[1]","※法人契約の場合、ご契約のお車に搭乗中の事故に補償を限定する「無保険車傷害保険（搭乗中のみ補償）」のみとなります。");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='無保険車傷害保険']/../../../p[1]",  data.getTestCaseID(), "'※法人契約の場合「人身傷害補償特約（搭乗中のみ補償）」のみとなります。' text validation for 無保険車傷害保険 of ご自身や同乗者への補償 section", "無保険車傷害保険textValidation");		

			}else {
				Assertion.assertIsAttached(executor.page, "//div[text()='無保険車傷害保険']/../..");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='無保険車傷害保険']/../..",  data.getTestCaseID(), "image of ご自身や同乗者への補償 is displayed successfully", "ご自身や同乗者への補償ImageValidation");
				common.clickAction(executor.page, "//div[text()='無保険車傷害保険']/..//div[@data-testid='OpenModalButtonContainer']");
				Assertion.assertBytext(executor.page, "//p[text()='自損事故保険（基本補償/自動セット）']/../..//div[2]","ご契約のお車を運転中の事故（電柱衝突や転落事故など）で、運転者や同乗者が死傷され、かつ自賠責保険などにより補償が受けられない場合に、保険金をお支払いします。\r\n"
						+ "※人身傷害補償特約をセットしている場合は、人身傷害補償特約で補償されます。");
				common.takeScreenShotsOfComponent(executor.page, "//p[text()='自損事故保険（基本補償/自動セット）']/../..",  data.getTestCaseID(), "'※人身傷害補償特約をセットしている場合は、人身傷害補償特約で補償されます。' text validation for 自損事故保険（基本補償/自動セット） of 無保険車傷害保険 section", "自損事故保険（基本補償/自動セット）textValidation");
			}
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Successfully navigated to model block of 無保険車傷害保険", "無保険車傷害保険ModelBlockNavigation", true);			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18844(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18839(data);
	}

	public void BAU_18840(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());

			if(utility.homePageMap.get(data.getTestCaseID()).getBrowserView().equals("Mobile")) {
				mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "運転される方の範囲");;

			}else {
				Assertion.assertIsAttached(executor.page, "//div[contains(text(),'弁護士費用等補償特約')]/../..");
				common.takeScreenShotsOfComponent(executor.page, "//div[contains(text(),'弁護士費用等補償特約')]/../..",  data.getTestCaseID(), "Section of 弁護士費用等補償特約 is displayed successfully", "弁護士費用等補償特約SectionValidation");
				common.clickAction(executor.page, "//div[contains(text(),'弁護士費用等補償特約')]/..//div[@data-testid='OpenModalButtonContainer']");
				Assertion.assertBytext(executor.page, "(//p[text()='弁護士費用等補償特約（選べる追加補償）']/../../div)[2]/div[2]/p[2]","この特約は法人契約にはセットすることができません。法人契約の場合、ご契約のお車の事故による被害に限定した「弁護士費用等補償特約（自動車事故）」をセットすることができます。");
				common.takeScreenShotsOfComponent(executor.page, "//p[text()='弁護士費用等補償特約（選べる追加補償）']/../..",  data.getTestCaseID(), "'この特約は法人契約にはセットすることができません。法人契約の場合、ご契約のお車の事故による被害に限定した「弁護士費用等補償特約（自動車事故）」をセットすることができます。' text validation for 弁護士費用等補償特約（選べる追加補償） of 弁護士費用等補償特約 section", "弁護士費用等補償特約（選べる追加補償）textValidation");

				Assertion.assertIsAttached(executor.page, "//p[text()='弁護士費用等補償特約（自動車事故）（選べる追加補償）']/../..");
				common.takeScreenShotsOfComponent(executor.page, "//p[text()='弁護士費用等補償特約（自動車事故）（選べる追加補償）']/../..",  data.getTestCaseID(), "弁護士費用等補償特約（自動車事故）（選べる追加補償） section is present inside 弁護士費用等補償特約 model pop-up", "弁護士費用等補償特約（自動車事故）（選べる追加補償）SectionValidation");
				Assertion.assertBytext(executor.page, "//p[text()='弁護士費用等補償特約（自動車事故）（選べる追加補償）']/../../div[2]","ご契約のお車のもらい事故などにより被害にあわれた場合に、相手への損害賠償請求を弁護士などへ委任した際に生じる弁護士費用や法律相談費用をお支払いする特約です。※この特約は個人契約にはセットすることができません。個人契約の場合、ご契約のお車の事故による被害に限定しない「弁護士費用等補償特約」をセットすることができます。");
				common.takeScreenShotsOfComponent(executor.page, "//p[text()='弁護士費用等補償特約（自動車事故）（選べる追加補償）']/../../div[2]",  data.getTestCaseID(), "※この特約は個人契約にはセットすることができません。個人契約の場合、ご契約のお車の事故による被害に限定しない「弁護士費用等補償特約」をセットすることができます。", "弁護士費用等補償特約（自動車事故）（選べる追加補償）SectionTextValidation");
				Assertion.assertIsAttached(executor.page, "//p[text()='弁護士費用等補償特約（自動車事故）（選べる追加補償）']/../..//button[text()='もっと詳しく']");
				common.takeScreenShotsOfComponent(executor.page, "//p[text()='弁護士費用等補償特約（自動車事故）（選べる追加補償）']/../..//button[text()='もっと詳しく']",  data.getTestCaseID(), "もっと詳しく button is present for 弁護士費用等補償特約（自動車事故）（選べる追加補償） section", "もっと詳しくButtonValidation");
				common.clickAction(executor.page, "//p[text()='弁護士費用等補償特約（自動車事故）（選べる追加補償）']/../..//button[text()='もっと詳しく']");
				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Model pop-up is open while clicking on もっと詳しく button of 弁護士費用等補償特約（自動車事故）（選べる追加補償） section", "もっと詳しくModelBlockNavigation", false);			

			}			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18840_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18840(data);
	}

	public void BAU_18842(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());

			Assertion.assertIsAttached(executor.page, "//div[text()='その他の追加補償']/../..");
			common.takeScreenShotsOfComponent(executor.page, "//div[text()='その他の追加補償']/../..",  data.getTestCaseID(), "image of 'その他の追加補償 is displayed successfully", "'その他の追加補償ImageValidation");
			common.clickAction(executor.page, "//div[text()='その他の追加補償']/..//p[text()='詳細']");
			Assertion.assertBytext(executor.page, "(//p[text()='日常生活賠償責任保険特約（示談交渉付）（選べる追加補償）']/../../div)[2]/div[2]/p[4]","この特約はバイク保険および法人契約にはセットすることができません。");
			common.takeScreenShotsOfComponent(executor.page, "//p[text()='日常生活賠償責任保険特約（示談交渉付）（選べる追加補償）']/../..",  data.getTestCaseID(), "'この特約はバイク保険および法人契約にはセットすることができません。' text validation for 日常生活賠償責任保険特約（示談交渉付）（選べる追加補償） of ご自身や同乗者への補償 section", "日常生活賠償責任保険特約（示談交渉付）（選べる追加補償）textValidation");

			Assertion.assertBytext(executor.page, "(//p[text()='ファミリープラス（アクサ安心プラス）（選べる追加補償）']/../../div)[2]/div[2]/p[2]","このパッケージは搭乗者傷害保険のご契約がない場合やバイク保険および法人契約にはセットすることができません。");
			common.takeScreenShotsOfComponent(executor.page, "//p[text()='ファミリープラス（アクサ安心プラス）（選べる追加補償）']/../..",  data.getTestCaseID(), "'このパッケージは搭乗者傷害保険のご契約がない場合やバイク保険および法人契約にはセットすることができません。' text validation for ファミリープラス（アクサ安心プラス）（選べる追加補償） of ご自身や同乗者への補償 section", "ファミリープラス（アクサ安心プラス）（選べる追加補償）textValidation");

			Assertion.assertBytext(executor.page, "(//p[text()='ファミリーバイク特約（選べる追加補償）']/../../div)[2]/div[2]/p[6]","この特約はバイク保険および法人契約にはセットすることができません。​");
			common.takeScreenShotsOfComponent(executor.page, "//p[text()='ファミリーバイク特約（選べる追加補償）']/../..",  data.getTestCaseID(), "'この特約はバイク保険および法人契約にはセットすることができません。​' text validation for ファミリーバイク特約（選べる追加補償） of ご自身や同乗者への補償 section", "ファミリーバイク特約（選べる追加補償）textValidation");

			Assertion.assertBytext(executor.page, "(//p[text()='他車運転危険補償特約（自動セット）']/../../div)[2]/div[2]/p[2]","この特約はバイク保険および法人契約にはセットされません。なお、法人契約の場合は「臨時代替自動車補償特約」が自動セットされます。");
			common.takeScreenShotsOfComponent(executor.page, "//p[text()='他車運転危険補償特約（自動セット）']/../..",  data.getTestCaseID(), "'この特約はバイク保険および法人契約にはセットされません。なお、法人契約の場合は「臨時代替自動車補償特約」が自動セットされます。' text validation for 他車運転危険補償特約（自動セット） of ご自身や同乗者への補償 section", "他車運転危険補償特約（自動セット）textValidation");

			Assertion.assertBytext(executor.page, "(//p[text()='レディースプラス（アクサ安心プラス）（選べる追加補償）']/../../div)[2]/div[2]/p[2]","このパッケージは搭乗者傷害保険のご契約がない場合やバイク保険および法人契約にはセットすることができません。");
			common.takeScreenShotsOfComponent(executor.page, "//p[text()='レディースプラス（アクサ安心プラス）（選べる追加補償）']/../..",  data.getTestCaseID(), "'このパッケージは搭乗者傷害保険のご契約がない場合やバイク保険および法人契約にはセットすることができません。' text validation for レディースプラス（アクサ安心プラス）（選べる追加補償） of ご自身や同乗者への補償 section", "レディースプラス（アクサ安心プラス）（選べる追加補償）textValidation");

			Assertion.assertBytext(executor.page, "(//p[text()='ペットプラス（アクサ安心プラス）（選べる追加補償）']/../../div)[2]/div[2]/p[2]","このパッケージは搭乗者傷害保険のご契約がない場合やバイク保険および法人契約にはセットすることができません。​");
			common.takeScreenShotsOfComponent(executor.page, "//p[text()='ペットプラス（アクサ安心プラス）（選べる追加補償）']/../..",  data.getTestCaseID(), "'このパッケージは搭乗者傷害保険のご契約がない場合やバイク保険および法人契約にはセットすることができません。​' text validation for ペットプラス（アクサ安心プラス）（選べる追加補償） of ご自身や同乗者への補償 section", "ペットプラス（アクサ安心プラス）（選べる追加補償）textValidation");

			Assertion.assertBytext(executor.page, "(//p[text()='臨時代替自動車補償特約（自動セット）']/../../div)[2]","ご契約のお車が整備、修理、点検等のために整備工場等にあって使用できない間に、その代わりとして臨時で借りた代車（＊）を使用中に起こした対人事故・対物事故・人身傷害事故・自損傷害事故・無保険車傷害事故・搭乗者傷害事故・車両事故について、ご契約条件に従い補償します。\r\n"
					+ "＊自家用8車種（自家用普通乗用車、自家用小型乗用車、自家用軽四輪乗用車、自家用普通貨物車（最大積載量0.5トン超2トン以下）、自家用普通貨物車（最大積載量0.5トン以下）、自家用小型貨物車、自家用軽四輪貨物車および特種用途自動車（キャンピング車））の場合に限ります。※この特約は個人契約にはセットされません。なお、個人契約（自動車保険のみ）の場合は「他車運転危険補償特約」が自動セットされます。");
			common.takeScreenShotsOfComponent(executor.page, "//p[text()='臨時代替自動車補償特約（自動セット）']/../..",  data.getTestCaseID(), "Content of new sections '臨時代替自動車補償特約（自動セット）' has been added under ご自身や同乗者への補償 Model pop-up", "臨時代替自動車補償特約（自動セット）SectionValidation");
			common.scrollPageTillElementLocation(executor.page, "//div[contains(@data-aem,'ConfirmObjectModal_AdjTariffPcModalBox')]");
			//common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Successfully navigated to model block of ご自身や同乗者への補償", "ご自身や同乗者への補償ModelBlockNavigation", true);
			//common.takeScreenShotsOfComponent(executor.page, "//div[contains(@data-aem,'ConfirmObjectModal_AdjTariffPcModalBox')]",  data.getTestCaseID(), "ご自身や同乗者への補償 model pop-up content validation", "ご自身や同乗者への補償contentValidation");
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	
	public void BAU_19976(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18842(data);
	}
	public void BAU_20020(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18842(data);
	}
	public void BAU_20022(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18842(data);
	}
	public void BAU_20024(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18842(data);
	}
	public void BAU_20028(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18842(data);
	}
	public void BAU_20029(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18842(data);
	}
	public void BAU_20030(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18842(data);
	}
	public void BAU_20031(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18842(data);
	}

	public void BAU_18843(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());

			if(utility.homePageMap.get(data.getTestCaseID()).getBrowserView().equals("Mobile")) {
				Assertion.assertIsAttached(executor.page, "//p[text()='その他の追加補償']/../..");
				common.takeScreenShotsOfComponent(executor.page, "//p[text()='その他の追加補償']/../..",  data.getTestCaseID(), "image of その他の追加補償 is displayed successfully", "その他の追加補償ImageValidation");
				common.clickAction(executor.page, "//p[text()='その他の追加補償']/../../div[1]//img");

				if(mainDriver.CORPORATE_URL) {
					Assertion.assertIsNotAttached(executor.page, "//div[text()='日常生活賠償責任保険特約（示談交渉付）']/../../..");
					Report.logger.info("日常生活賠償責任保険特約（示談交渉付） section details is not present inside その他の追加補償 model pop-up");
					Assertion.assertIsNotAttached(executor.page, "//div[text()='ファミリープラス']/../../..");
					Report.logger.info("ファミリープラス section details is not present inside その他の追加補償 model pop-up");
					Assertion.assertIsNotAttached(executor.page, "//div[text()='ファミリーバイク特約']/../../..");
					Report.logger.info("ファミリーバイク特約 section details is not present inside その他の追加補償 model pop-up");
					Assertion.assertIsNotAttached(executor.page, "//div[text()='レディースプラス']/../../..");
					Report.logger.info("レディースプラス section details is not present inside その他の追加補償 model pop-up");
					Assertion.assertIsNotAttached(executor.page, "//div[text()='ペットプラス']/../../..");
					Report.logger.info("ペットプラス section details is not present inside その他の追加補償 model pop-up");
					Assertion.assertIsAttached(executor.page, "//div[text()='臨時代替自動車補償特約（自動セット）']/../../..");
					common.takeScreenShotsOfComponent(executor.page, "//div[text()='臨時代替自動車補償特約（自動セット）']/../../..",  data.getTestCaseID(), "臨時代替自動車補償特約（自動セット） section is present inside その他の追加補償 model pop-up", "SectionValidation");
					Assertion.assertBytextNotContains(executor.page, "(//div[text()='臨時代替自動車補償特約（自動セット）']/../../..//p)[2]", "この特約はバイク保険には適用することができません。");
					Report.logger.info("'この特約はバイク保険には適用することができません。' text is not present inside 他車運転危険補償特約（自動セット） section");

				}else {
					if(data.getInsurnaceProductType().equals("バイク保険（二輪）")) {
						Assertion.assertIsNotAttached(executor.page, "//div[text()='日常生活賠償責任保険特約（示談交渉付）']/../../..");
						Report.logger.info("日常生活賠償責任保険特約（示談交渉付） section details is not present inside その他の追加補償 model pop-up");
						Assertion.assertIsNotAttached(executor.page, "//div[text()='ファミリープラス']/../../..");
						Report.logger.info("ファミリープラス section details is not present inside その他の追加補償 model pop-up");
						Assertion.assertIsNotAttached(executor.page, "//div[text()='ファミリーバイク特約']/../../..");
						Report.logger.info("ファミリーバイク特約 section details is not present inside その他の追加補償 model pop-up");
						Assertion.assertIsNotAttached(executor.page, "//div[text()='レディースプラス']/../../..");
						Report.logger.info("レディースプラス section details is not present inside その他の追加補償 model pop-up");
						Assertion.assertIsNotAttached(executor.page, "//div[text()='ペットプラス']/../../..");
						Report.logger.info("ペットプラス section details is not present inside その他の追加補償 model pop-up");
						Assertion.assertIsNotAttached(executor.page, "//div[text()='他車運転危険補償特約（自動セット）']/../../..");
						Report.logger.info("他車運転危険補償特約（自動セットス section details is not present inside その他の追加補償 model pop-up");

					}else {
						Assertion.assertIsAttached(executor.page, "//div[text()='日常生活賠償責任保険特約（示談交渉付）']/../../..");
						common.takeScreenShotsOfComponent(executor.page, "//div[text()='日常生活賠償責任保険特約（示談交渉付）']/../../..",  data.getTestCaseID(), "日常生活賠償責任保険特約（示談交渉付） section is present inside その他の追加補償 model pop-up", "日常生活賠償責任保険特約（示談交渉付）SectionValidation");
						Assertion.assertIsAttached(executor.page, "//div[text()='ファミリープラス']/../../..");
						common.takeScreenShotsOfComponent(executor.page, "//div[text()='ファミリープラス']/../../..",  data.getTestCaseID(), "ファミリープラス section is present inside ファミリープラス model pop-up", "ファミリープラスSectionValidation");
						Assertion.assertIsAttached(executor.page, "//div[text()='ファミリーバイク特約']/../../..");
						common.takeScreenShotsOfComponent(executor.page, "//div[text()='ファミリーバイク特約']/../../..",  data.getTestCaseID(), "ファミリーバイク特約 section is present inside その他の追加補償 model pop-up", "ファミリーバイク特約SectionValidation");
						Assertion.assertIsAttached(executor.page, "//div[text()='レディースプラス']/../../..");
						common.takeScreenShotsOfComponent(executor.page, "//div[text()='レディースプラス']/../../..",  data.getTestCaseID(), "レディースプラス section is present inside その他の追加補償 model pop-up", "レディースプラスSectionValidation");
						Assertion.assertIsAttached(executor.page, "//div[text()='ペットプラス']/../../..");
						common.takeScreenShotsOfComponent(executor.page, "//div[text()='他車運転危険補償特約（自動セット）']/../../..",  data.getTestCaseID(), "他車運転危険補償特約（自動セット） section is present inside その他の追加補償 model pop-up", "他車運転危険補償特約（自動セット）SectionValidation");
						Assertion.assertIsAttached(executor.page, "//div[text()='他車運転危険補償特約（自動セット）']/../../..");
						common.takeScreenShotsOfComponent(executor.page, "//div[text()='他車運転危険補償特約（自動セット）']/../../..",  data.getTestCaseID(), "他車運転危険補償特約（自動セット） section is present inside その他の追加補償 model pop-up", "他車運転危険補償特約（自動セット）SectionValidation");
						Assertion.assertBytextNotContains(executor.page, "(//div[text()='他車運転危険補償特約（自動セット）']/../../..//p)[2]", "この特約はバイク保険には適用することができません。");
						Report.logger.info("'この特約はバイク保険には適用することができません。' text is not present inside 他車運転危険補償特約（自動セット） section");
					}
				}

			}
			common.takeScreenShotsOfComponent(executor.page, "//div[contains(@data-aem,'ConfirmObjectModal_AdjTariffPcModalBox')]",  data.getTestCaseID(), "その他の追加補償 model pop-up content validation", "その他の追加補償contentValidation");
			//common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Successfully navigated to model block of ご自身や同乗者への補償", "ご自身や同乗者への補償ModelBlockNavigation", true);			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	
	public void BAU_22038(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18843(data);
	}
	public void BAU_22039(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18843(data);
	}
	public void BAU_22040(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18843(data);
	}
	public void BAU_22041(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18843(data);
	}
	public void BAU_22043(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18843(data);
	}
	public void BAU_22044(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18843(data);
	}
	public void BAU_22046(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18843(data);
	}
	public void BAU_22047(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18843(data);
	}

	public void BAU_17913(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18198_SP(data);
	}

	public void BAU_18841(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());

			if(utility.homePageMap.get(data.getTestCaseID()).getBrowserView().equals("Mobile")) {
				Assertion.assertIsAttached(executor.page, "//p[text()='ご自身や同乗者への補償']/../..");
				common.takeScreenShotsOfComponent(executor.page, "//p[text()='ご自身や同乗者への補償']/../..",  data.getTestCaseID(), "image of ご自身や同乗者への補償 is displayed successfully", "ご自身や同乗者への補償ImageValidation");
				common.clickAction(executor.page, "//p[text()='ご自身や同乗者への補償']/../../div[1]//img");
				Assertion.assertIsAttached(executor.page, "//div[text()='人身傷害補償特約']/../../..");
				Report.logger.info("人身傷害補償特約 section details is present inside その他の追加補償 model pop-up");
				if(mainDriver.CORPORATE_URL) {
					Assertion.assertIsChecked(executor.page, "//div[text()='人身傷害補償特約']/../../..//span[text()='搭乗中のみ補償特約']/../input");
					common.takeScreenShotsOfComponent(executor.page, "//div[text()='人身傷害補償特約']/../../..//span[text()='搭乗中のみ補償特約']",  data.getTestCaseID(), "搭乗中のみ補償特約 option is always checked for 身傷害補償特約 section details", "搭乗中のみ補償特約DefaultCheckValidation");		

				}else {
					Assertion.assertIsChecked(executor.page, "//div[text()='人身傷害補償特約']/../../..//div[text()='搭乗中のみ補償特約']/../..");
					common.takeScreenShotsOfComponent(executor.page, "//div[text()='人身傷害補償特約']/../../..//div[text()='搭乗中のみ補償特約']/../../..",  data.getTestCaseID(), "搭乗中のみ補償特約 check-box option is editable for 身傷害補償特約 section details", "搭乗中のみ補償特約Check-boxValidation");	
				}
				Assertion.assertBytextContains(executor.page, "//div[text()='人身傷害補償特約']/../../../p[1]","※法人契約の場合「人身傷害補償特約（搭乗中のみ補償）」のみとなります。");
				common.takeScreenShotsOfComponent(executor.page, "//div[text()='人身傷害補償特約']/../../../p[1]",  data.getTestCaseID(), "'※法人契約の場合「人身傷害補償特約（搭乗中のみ補償）」のみとなります。' text validation for 人身傷害補償特約of ご自身や同乗者への補償 section", "人身傷害補償特約textValidation");		

			}
			common.takeScreenShotsOfComponent(executor.page, "//div[contains(@data-aem,'ConfirmObjectModal_AdjTariffPcModalBox')]",  data.getTestCaseID(), "ご自身や同乗者への補償 model pop-up content validation", "ご自身や同乗者への補償contentValidation");
			//common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Successfully navigated to model block of ご自身や同乗者への補償", "ご自身や同乗者への補償ModelBlockNavigation", true);			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18871(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18840(data);
	}

	public void BAU_18847(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "運転される方の範囲");
			mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "運転者年齢条件特約");
			Assertion.assertBytext(executor.page, "(//li[@data-aem='_summary_ref_list_ReferenceList_ListItem'])[2]", "法人契約の場合は、ご契約のお車を運転するすべての方に運転者年齢条件が適用され ます。お車を運転される方で最も若い方を基準に設定してください。 ");
			common.takeScreenShotsOfComponent(executor.page, "(//li[@data-aem='_summary_ref_list_ReferenceList_ListItem'])[2]/../../../../..",  data.getTestCaseID(), "'法人契約の場合は、ご契約のお車を運転するすべての方に運転者年齢条件が適用され ます。お車を運転される方で最も若い方を基準に設定してください。 ' text is present", "運転者年齢条件特約TextValidation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	
	public void BAU_22560(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18847(data);
	}
	public void BAU_22561(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18847(data);
	}
	public void BAU_22562(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18847(data);
	}
	public void BAU_22563(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18847(data);
	}
	
	

	public void BAU_18848(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "ご自身や同乗者への補償");
			mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "人身傷害補償特約（選べる基本補償）");
			Assertion.assertBytext(executor.page, "//div[@data-aem='_AdjTariffPcDetail']/div[1]//div[3]", "人身傷害補償には、歩行中の車との事故なども補償する「人身傷害補償特約」と、ご契約のお車に搭乗中の事故に補償を限定する「人身傷害補償特約（搭乗中のみ補償）」の2つのタイプがあります（＊）。アクサダイレクトでは、まずは「人身傷害補償特約」をお選びいただき、その上で追加の補償として「搭乗者傷害保険」をご検討いただくことをおすすめしています。");
			Assertion.assertBytext(executor.page, "//div[@data-aem='_AdjTariffPcDetail']/div[1]//div[4]", "法人契約の場合「人身傷害補償特約（搭乗中のみ補償）」のみとなります。");
			common.takeScreenShotsOfComponent(executor.page, "//div[@data-aem='_AdjTariffPcDetail']/div[1]",  data.getTestCaseID(), "'Newly added/changed text' is present under 人身傷害補償特約 section", "人身傷害補償特約TextValidation");

			Assertion.assertBytext(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[1]/div[1]/div[4]//li", "法人契約の場合「人身傷害補償特約（搭乗中のみ補償）」のみとなります。");
			common.takeScreenShotsOfComponent(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[1]/div[1]",  data.getTestCaseID(), "'法人契約の場合「人身傷害補償特約（搭乗中のみ補償）」のみとなります。' text present under ", "人身傷害補償特約Text2Validation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	
	public void BAU_22567(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18848(data);
	}
	public void BAU_22571(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18848(data);
	}
	public void BAU_22572(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18848(data);
	}
	public void BAU_22573(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18848(data);
	}

	public void BAU_18849(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "ご自身や同乗者への補償");
			mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "無保険車傷害保険（基本補償/自動セット）");
			System.out.println(common.getText(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[1]/div[1]"));
			Assertion.assertBytext(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[1]/div[1]", "個人契約の場合ア.ご契約のお車を主に運転される方（記名被保険者）イ.ご契約のお車を主に運転される方（記名被保険者）の配偶者ウ.ご契約のお車を主に運転される方（記名被保険者）またはその配偶者の同居の親族エ.ご契約のお車を主に運転される方（記名被保険者）またはその配偶者の別居の未婚の子オ.ア～エ以外の方で、ご契約のお車の正規の乗車装置またはその装置のある室内（＊）に搭乗中の者ア～エの方については、他のお車に搭乗中の場合や歩行中の無保険自動車による事故についても、補償の対象となります。法人契約の場合ご契約のお車の正規の乗車装置またはその装置のある室内（＊）に搭乗中の方隔壁等により通行できないように仕切られている場所を除きます。");
			common.takeScreenShotsOfComponent(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[1]/div[1]",  data.getTestCaseID(), "changed text is present under 個人契約の場合 section", "個人契約の場合ChangedTextValidation");

			Assertion.assertBytext(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[2]/div[1]/div[5]", "法人契約の場合「無保険車傷害に関する被保険自動車搭乗中のみ補償特約」が セットされ、ご契約のお車に搭乗中の事故に限り、保険金をお支払いします。");
			common.takeScreenShotsOfComponent(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[2]/div[1]/div[5]/..",  data.getTestCaseID(), "'法人契約の場合「無保険車傷害に関する被保険自動車搭乗中のみ補償特約」が セットされ、ご契約のお車に搭乗中の事故に限り、保険金をお支払いします。' text present under ", "無保険車傷害保険金TextValidation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	
	public void BAU_22586(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18849(data);
	}
	public void BAU_22588(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18849(data);
	}
	public void BAU_22589(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18849(data);
	}
	public void BAU_22590(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18849(data);
	}

	public void BAU_18850(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "その他の追加補償");
			mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "弁護士費用等補償特約（選べる追加補償）");
			Assertion.assertBytext(executor.page, "(//div[@data-aem='_AdjTariffPcDetail'])/div[1]//div[3]", "この特約は個人契約の場合にセットできます。");
			common.takeScreenShotsOfComponent(executor.page, "(//div[@data-aem='_AdjTariffPcDetail'])/div[1]",  data.getTestCaseID(), "'この特約は個人契約の場合にセットできます。' text is present under 弁護士費用等補償特約 section", "弁護士費用等補償特約AddedTextValidation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	
	public void BAU_22606(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18850(data);
	}
	public void BAU_22607(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18850(data);
	}
	public void BAU_22608(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18850(data);
	}
	public void BAU_22609(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18850(data);
	}

	public void BAU_18854(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "その他の追加補償");
			mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "弁護士費用等補償特約（自動車事故）（選べる追加補償）");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	
	public void BAU_22532(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18854(data);
	}
	public void BAU_22534(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18854_SP(data);
	}

	public void BAU_18855(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "その他の追加補償");
			mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "臨時代替自動車補償特約（自動セット）");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	
	public void BAU_22555(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18855(data);
	}
	public void BAU_22556(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18855_SP(data);
	}

	public void BAU_22245(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "AXAプレミアムロードサービス");

			if(utility.homePageMap.get(data.getTestCaseID()).getBrowserView().equals("Mobile")) {
				Assertion.assertIsAttached(executor.page, "(//div[text()='ロードサイドサービス'])[4]/../..");
				common.takeScreenShotsOfComponent(executor.page, "(//div[text()='ロードサイドサービス'])[4]/../..",data.getTestCaseID(), "Model footer section as ロードサイドサービス is displayed successfully", "ロードサイドサービスDisplayValidation");
				common.clickAction(executor.page, "(//div[text()='ロードサイドサービス'])[4]/../..//button[text()='もっと詳しく']");
			}else {
				Assertion.assertIsAttached(executor.page, "(//p[text()='ロードサイドサービス'])[4]/../..");
				common.takeScreenShotsOfComponent(executor.page,"(//p[text()='ロードサイドサービス'])[4]/../..",  data.getTestCaseID(), "Model footer section as ロードサイドサービス is displayed successfully", "ロードサイドサービスDisplayValidation");
				common.clickAction(executor.page, "(//p[text()='ロードサイドサービス'])[4]/../..//button[text()='もっと詳しく']");
			}

			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"Model pop-up is displayed successfully after clicking on もっと詳しく button of ロードサイドサービス section","ロードサイドサービスもっと詳しくPop-up",false);
			//mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "ロードサイドサービス");
			Assertion.assertBytext(executor.page, "//span[text()='玄関カギ開けサービス（保険期間中1回のみ）']/../../div[3]", "法人契約は対象外です。");
			common.takeScreenShotsOfComponent(executor.page, "//span[text()='玄関カギ開けサービス（保険期間中1回のみ）']/../..", data.getTestCaseID(), "'法人契約は対象外です。' newly added text is present under 玄関カギ開けサービス（保険期間中1回のみ）", "玄関カギ開けサービス（保険期間中1回のみ）TextValidation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	
	public void BAU_22616(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_22245(data);
	}
	public void BAU_22619(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_22245_SP(data);
	}
	public void BAU_22620(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_22245(data);
	}
	public void BAU_22621(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_22245_SP(data);
	}

	public void BAU_22246(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "その他の追加補償");
			mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "他車運転危険補償特約（自動セット）");
			Assertion.assertBytext(executor.page, "(//div[@data-aem='_AdjTariffPcDetail'])/div[1]//div[3]", "この特約は、ご契約のお車が自家用8車種の場合の個人契約に自動セットされます。二輪・原付、法人契約にはセットすることはできません。");
			common.takeScreenShotsOfComponent(executor.page, "(//div[@data-aem='_AdjTariffPcDetail'])/div[1]", data.getTestCaseID(), "'この特約は、ご契約のお車が自家用8車種の場合の個人契約に自動セットされます。二輪・原付、法人契約にはセットすることはできません。' newly added text is present under 玄関カギ開けサービス（保険期間中1回のみ）", "他車運転危険補償特約（自動セット）TextValidation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	
	public void BAU_22627(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_22246(data);
	}
	public void BAU_22628(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_22246_SP(data);
	}
	public void BAU_22629(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_22246(data);
	}
	public void BAU_22630(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_22246_SP(data);
	}

	public void BAU_18847_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18847(data);
	}
	public void BAU_18848_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "ご自身や同乗者への補償");
			mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "人身傷害補償特約");
			Assertion.assertBytext(executor.page, "//div[@data-aem='_AdjTariffPcDetail']/div[1]//div[3]", "人身傷害補償には、歩行中の車との事故なども補償する「人身傷害補償特約」と、ご契約のお車に搭乗中の事故に補償を限定する「人身傷害補償特約（搭乗中のみ補償）」の2つのタイプがあります（＊）。アクサダイレクトでは、まずは「人身傷害補償特約」をお選びいただき、その上で追加の補償として「搭乗者傷害保険」をご検討いただくことをおすすめしています。");
			Assertion.assertBytext(executor.page, "//div[@data-aem='_AdjTariffPcDetail']/div[1]//div[4]", "法人契約の場合「人身傷害補償特約（搭乗中のみ補償）」のみとなります。");
			common.takeScreenShotsOfComponent(executor.page, "//div[@data-aem='_AdjTariffPcDetail']/div[1]",  data.getTestCaseID(), "'Newly added/changed text' is present under 人身傷害補償特約 section", "人身傷害補償特約TextValidation");

			Assertion.assertBytext(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[1]/div[1]/div[4]//li", "法人契約の場合「人身傷害補償特約（搭乗中のみ補償）」のみとなります。");
			common.takeScreenShotsOfComponent(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[1]/div[1]",  data.getTestCaseID(), "'法人契約の場合「人身傷害補償特約（搭乗中のみ補償）」のみとなります。' text present under ", "人身傷害補償特約Text2Validation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	public void BAU_18849_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "ご自身や同乗者への補償");
			mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "無保険車傷害保険");
			System.out.println(common.getText(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[1]/div[1]"));
			Assertion.assertBytext(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[1]/div[1]", "個人契約の場合ア．ご契約のお車を主に運転される方（記名被保険者）イ．ご契約のお車を主に運転される方（記名被保険者）の配偶者ウ．ご契約のお車を主に運転される方（記名被保険者）またはその配偶者の同居の親族エ．ご契約のお車を主に運転される方（記名被保険者）またはその配偶者の別居の未婚の子オ．ア～エ以外の方で、ご契約のお車の正規の乗車装置またはその装置のある室内（＊）に搭乗中の者ア～エの方については、他のお車に搭乗中の場合や歩行中の無保険自動車による事故についても、補償の対象となります。法人契約の場合ご契約のお車の正規の乗車装置またはその装置のある室内（＊）に搭乗中の方隔壁等により通行できないように仕切られている場所を除きます。");
			common.takeScreenShotsOfComponent(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[1]/div[1]",  data.getTestCaseID(), "changed text is present under 個人契約の場合 section", "個人契約の場合ChangedTextValidation");

			Assertion.assertBytext(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[2]/div[1]/div[5]", "法人契約の場合「無保険車傷害に関する被保険自動車搭乗中のみ補償特約」が セットされ、ご契約のお車に搭乗中の事故に限り、保険金をお支払いします。");
			common.takeScreenShotsOfComponent(executor.page, "(//div[@data-aem='_AdjTariffPcDetailBlockContents'])[2]/div[1]/div[5]/..",  data.getTestCaseID(), "'法人契約の場合「無保険車傷害に関する被保険自動車搭乗中のみ補償特約」が セットされ、ご契約のお車に搭乗中の事故に限り、保険金をお支払いします。' text present under ", "無保険車傷害保険金TextValidation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18850_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "その他の追加補償");
			if(data.getInsurnaceFlowType().equals("Corporate")) {
				mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "弁護士費用等補償特約（自動車事故）");
				Assertion.assertBytext(executor.page, "(//div[@data-aem='_AdjTariffPcDetail'])/div[1]//div[3]", "この特約は法人契約の場合にセットできます。");
			}else {
				mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "弁護士費用等補償特約");
				Assertion.assertBytext(executor.page, "(//div[@data-aem='_AdjTariffPcDetail'])/div[1]//div[3]", "この特約は個人契約の場合にセットできます。");
			}
			common.takeScreenShotsOfComponent(executor.page, "(//div[@data-aem='_AdjTariffPcDetail'])/div[1]",  data.getTestCaseID(), "'この特約は個人契約の場合にセットできます。' text is present under 弁護士費用等補償特約 section", "弁護士費用等補償特約AddedTextValidation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	public void BAU_18854_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "その他の追加補償");

			if(data.getInsurnaceFlowType().equals("Corporate")) {
				mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "弁護士費用等補償特約（自動車事故）");
			}else {
				mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "弁護士費用等補償特約");
			}
			//mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "弁護士費用等補償特約");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	public void BAU_18855_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "その他の追加補償");
			mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "臨時代替自動車補償特約（自動セット）");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	public void BAU_22245_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "AXAプレミアムロードサービス");

			if(utility.homePageMap.get(data.getTestCaseID()).getBrowserView().equals("Mobile")) {
				Assertion.assertIsAttached(executor.page, "(//div[text()='ロードサイドサービス'])/../../../..");
				common.takeScreenShotsOfComponent(executor.page, "(//div[text()='ロードサイドサービス'])/../../../..",data.getTestCaseID(), "Model footer section as ロードサイドサービス is displayed successfully", "ロードサイドサービスDisplayValidation");
				common.clickAction(executor.page, "(//div[text()='ロードサイドサービス'])/../../../..//button[text()='もっと詳しく']");
			}else {
				Assertion.assertIsAttached(executor.page, "(//p[text()='ロードサイドサービス'])[4]/../..");
				common.takeScreenShotsOfComponent(executor.page,"(//p[text()='ロードサイドサービス'])[4]/../..",  data.getTestCaseID(), "Model footer section as ロードサイドサービス is displayed successfully", "ロードサイドサービスDisplayValidation");
				common.clickAction(executor.page, "(//p[text()='ロードサイドサービス'])[4]/../..//button[text()='もっと詳しく']");
			}

			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"Model pop-up is displayed successfully after clicking on もっと詳しく button of ロードサイドサービス section","ロードサイドサービスもっと詳しくPop-up",true);
			//mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "ロードサイドサービス");
			Assertion.assertBytext(executor.page, "//span[text()='玄関カギ開けサービス（保険期間中1回のみ）']/../../div[3]", "法人契約は対象外です。");
			common.takeScreenShotsOfComponent(executor.page, "//span[text()='玄関カギ開けサービス（保険期間中1回のみ）']/../..", data.getTestCaseID(), "'法人契約は対象外です。' newly added text is present under 玄関カギ開けサービス（保険期間中1回のみ）", "玄関カギ開けサービス（保険期間中1回のみ）TextValidation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	public void BAU_22246_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "その他の追加補償");
			if(mainDriver.CORPORATE_URL) {
				Assertion.assertIsNotAttached(executor.page, "//div[text()='他車運転危険補償特約（自動セット）']/../../..");
				Report.logger.pass("'他車運転危険補償特約（自動セット） section is not attached for その他の追加補償");
			}else {
				mainDriver.tarrifPageModelBlockMoreInfoPopupNavigation(executor.page, data.getTestCaseID(), "他車運転危険補償特約（自動セット）");
				Assertion.assertBytext(executor.page, "(//div[@data-aem='_AdjTariffPcDetail'])/div[1]//div[3]", "この特約は、ご契約のお車が自家用8車種の場合の個人契約に自動セットされます。二輪・原付、法人契約にはセットすることはできません。");
				common.takeScreenShotsOfComponent(executor.page, "(//div[@data-aem='_AdjTariffPcDetail'])/div[1]", data.getTestCaseID(), "'この特約は、ご契約のお車が自家用8車種の場合の個人契約に自動セットされます。二輪・原付、法人契約にはセットすることはできません。' newly added text is present under 玄関カギ開けサービス（保険期間中1回のみ）", "他車運転危険補償特約（自動セット）TextValidation");
			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18857(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.currentAndSuspendedInsuranceInformationSectionValidation(executor.page, data.getTestCaseID());
			executor.page.locator(Portal_ObjectRepository.vehicleInfo_CurrentInsurance_BranchNumber).clear();
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			if(common.getElementProperty(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentInsurance_BranchNumber,"border").contains("rgb(204, 204, 204)")) {
				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Branch number field is not displaying any error message", "BranchNumberErrorValidation", true);
			}else {
				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Branch number field is displaying error message", "Failed_BranchNumberErrorValidation", true);
			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18857_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18857(data);
	}
	public void BAU_20021(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18857(data);
	}
	public void BAU_20044(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18857(data);
	}
	public void BAU_20045(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18857(data);
	}
	public void BAU_20046(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18857(data);
	}

	public void BAU_18857_1(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.currentAndSuspendedInsuranceInformationSectionValidation(executor.page, data.getTestCaseID());
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			if(common.getElementProperty(executor.page, Portal_ObjectRepository.vehicleInfo_suspendedInsurance_BranchNumber,"border").contains("rgb(204, 204, 204)")) {
				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Branch number field is not displaying any error message", "BranchNumberErrorValidation", true);
			}else {
				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Branch number field is displaying any error message", "Failed_BranchNumberErrorValidation", true);
			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void BAU_18857_1_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18857_1(data);
	}
	public void BAU_20050(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18857_1(data);
	}
	public void BAU_20051(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18857_1(data);
	}
	public void BAU_20052(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18857_1(data);
	}
	public void BAU_20053(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18857_1(data);
	}

	public void BAU_18858(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());


			if(vehicleInfo.CORPORATE_URL) {
				System.out.println(common.getText(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentContractDriverIdentityText));
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentContractDriverIdentityText, "今回のご契約の記名被保険者と「現在のご契約の記名被保険者」は同一名称の法人ですか？");
				Assertion.assertHasAttributePattern(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentContractDriverIdentityText+"/a[1]", "href", "https://www.axa-direct.co.jp/auto/glossary/ka/08.html");
				Assertion.assertHasAttributePattern(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentContractDriverIdentityText+"/a[2]", "href", "https://www.axa-direct.co.jp/auto/glossary/ka/08.html");
				Assertion.assertIsChecked(executor.page, Portal_ObjectRepository.vehicleCurrentInsurnaceQuestionariesConfirm);
				Report.logger.info("Button to confirm 'Whether the name of Contractor and Insured person is same' is by default selected");
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentInsuranceInfo_Section,data.getTestCaseID(), "Current Insurance information section validation is completed", "CurrentInsuranceInfoSectionValidation");
				common.clickAction(executor.page, Portal_ObjectRepository.vehicleCurrentInsurnaceQuestionariesDeny);
				vehicleInfo.InsuredCurrentAndInterruptionContractPopupForSME(executor.page, data.getTestCaseID(),data.getInsurancePurchaseType());
				common.clickAction(executor.page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);

			}else {
				System.out.println(common.getText(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentContractDriverIdentityText));
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentContractDriverIdentityText, "今回のご契約の主な運転者と「現在のご契約の記名被保険者」は同じ方ですか？");
				Assertion.assertHasAttributePattern(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentContractDriverIdentityText+"/a[1]", "href", "https://www.axa-direct.co.jp/auto/glossary/ka/08.html");
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentContractDriverIdentityText+"/a[2]");
				Assertion.assertIsNotChecked(executor.page, Portal_ObjectRepository.vehicleCurrentInsurnaceQuestionariesConfirm);
				Report.logger.info("Button to confirm 'Whether the name of Contractor and Insured person is same' is Not by default selected");
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentInsuranceInfo_Section, data.getTestCaseID(),"Current Insurance information section validation is completed", "CurrentInsuranceInfoSectionValidation");
				common.clickAction(executor.page, Portal_ObjectRepository.vehicleCurrentInsurnaceQuestionariesDeny);
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentInsuranceInsuredRelation);
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentInsuranceInsuredRelation, data.getTestCaseID(),"Current Insurance Insured Relation section is displayed after clicking on いいえ button", "CurrentInsuranceInsuredRekationSectionValidation");
			}


			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18858_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18858(data);
	}
	public void BAU_19935(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18858(data);
	}
	public void BAU_19951(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18858(data);
	}
	public void BAU_19965(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18858(data);
	}
	public void BAU_19969(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18858(data);
	}

	public void BAU_18859(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			if(vehicleInfo.CORPORATE_URL) {
				System.out.println(common.getText(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceDriverIdentityText));
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceDriverIdentityText, "今回のご契約の記名被保険者と「中断証明書記載の記名被保険者」は同一名称の法人ですか？");
				Assertion.assertHasAttributePattern(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceDriverIdentityText+"/a[1]", "href", "https://www.axa-direct.co.jp/auto/glossary/ka/08.html");
				Assertion.assertHasAttributePattern(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceDriverIdentityText+"/a[2]", "href", "https://www.axa-direct.co.jp/auto/glossary/ka/08.html");
				Assertion.assertIsChecked(executor.page, Portal_ObjectRepository.vehicleSuspendedInsurnaceQuestionariesConfirm);
				Report.logger.info("Button to confirm 'Whether the name of Contractor and Insured person is same' is by default selected");
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceInfo_Section,data.getTestCaseID(), "Suspended Insurance information section validation is completed", "SuspendedInsuranceInfoSectionValidation");
				common.clickAction(executor.page, Portal_ObjectRepository.vehicleSuspendedInsurnaceQuestionariesDeny);
				vehicleInfo.InsuredCurrentAndInterruptionContractPopupForSME(executor.page, data.getTestCaseID(),data.getInsurancePurchaseType());
				common.clickAction(executor.page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);

			}else {
				System.out.println(common.getText(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceDriverIdentityText));
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceDriverIdentityText, "今回のご契約の主な運転者と「中断証明書記載の記名被保険者」は同じ方ですか？");
				Assertion.assertHasAttributePattern(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceDriverIdentityText+"/a[1]", "href", "https://www.axa-direct.co.jp/auto/glossary/ka/08.html");
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceDriverIdentityText+"/a[2]");
				Assertion.assertIsNotChecked(executor.page, Portal_ObjectRepository.vehicleSuspendedInsurnaceQuestionariesConfirm);
				Report.logger.info("Button to confirm 'Whether the name of Contractor and Insured person is same' is Not by default selected");
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceInfo_Section, data.getTestCaseID(),"Suspended Insurance information section validation is completed", "SuspendedInsuranceInfoSectionValidation");
				common.clickAction(executor.page, Portal_ObjectRepository.vehicleSuspendedInsurnaceQuestionariesDeny);
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceInsuredRelation);
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceInsuredRelation, data.getTestCaseID(),"Current Insurance Insured Relation section is displayed after clicking on いいえ button", "CurrentInsuranceInsuredRekationSectionValidation");
			}


			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18859_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18859(data);
	}
	public void BAU_19982(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18859(data);
	}
	public void BAU_19984(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18859(data);
	}
	public void BAU_19987(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18859(data);
	}
	public void BAU_19989(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18859(data);
	}

	public void BAU_18860(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.businessVehicleModificationQuestionariesValidation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			vehicleInfo.frieghtVehicleModificationQuestionariesValidation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18860_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18860(data);
	}
	public void BAU_20486(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.businessVehicleModificationQuestionariesValidation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	public void BAU_20489(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_20486(data);
	}
	public void BAU_20496(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_20486(data);
	}
	public void BAU_20497(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_20486(data);
	}
	public void BAU_20508(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.frieghtVehicleModificationQuestionariesValidation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	public void BAU_20510(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_20508(data);
	}
	public void BAU_20511(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_20508(data);
	}
	public void BAU_20512(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_20508(data);
	}

	public void BAU_18861(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			Assertion.assertIsChecked(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityConfirm);
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityConfirm+"/../..", data.getTestCaseID(), "By default 'はい' button of 'owner On Vehicle Inspection Certificate And Policyholder' field is selected", "ownerOnVehicleInspectionCertificateAndPolicyholderDefaultSelection");
			common.clickAction(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityDeny);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection);
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection+"/../..", data.getTestCaseID(), "Owner On Vehicle Inspection Certificate Relation Image section displayed successfully", "VehicleRelationImageSectionValidation");

			if(vehicleInfo.CORPORATE_URL) {
				Assertion.assertHasCount(executor.page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection+"/label", 4);
				Report.logger.pass("4 images of relationship with owner is displayed for corporate flow");
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateNameInputField);
				Report.logger.pass("Name Input field for Owner relationship is not displayed for corporate flow");
			}else {
				Assertion.assertHasCount(executor.page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection+"/label", 6);
				Report.logger.pass("6 images of relationship with owner is displayed for Personal flow");
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateNameInputField);
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateNameInputField, data.getTestCaseID(), "Name Input field for Owner relationship is displayed for Personal flow", "RelationshipOwnerInputFieldValidation");
			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18861_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18861(data);
	}
	public void BAU_20535(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18861(data);
	}
	public void BAU_20536(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18861(data);
	}
	public void BAU_20538(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18861(data);
	}
	public void BAU_20539(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18861(data);
	}

	public void BAU_18864(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			Assertion.assertIsChecked(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityConfirm);
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityConfirm+"/../..", data.getTestCaseID(), "By default 'はい' button of 'owner On Vehicle Inspection Certificate And Policyholder' field is selected", "ownerOnVehicleInspectionCertificateAndPolicyholderDefaultSelection");
			common.clickAction(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityDeny);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection);
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection+"/../..", data.getTestCaseID(), "Owner On Vehicle Inspection Certificate Relation Image section displayed successfully", "VehicleRelationImageSectionValidation");

			common.clickAction(executor.page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection+"//span[contains(text(),'ディーラー/ローン業者/リース会社')]");
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Additional Questionaries is displayed ","HolderRelationModelContentPopupValidation",true);

			if(vehicleInfo.CORPORATE_URL) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.vehicleInfo_UserOnVehicleInspectionCertificateRelationImage);
				Report.logger.pass("images of users for 'User on Vehicle' options is displayed for Corporate flow after clicking on dealer option");
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.vehicleInfo_UserOnVehicleInspectionDealer);
				Report.logger.pass("User Vehicle inspection questionaries is displayed after clicking on Dealer button");
				Assertion.assertIsChecked(executor.page, Portal_ObjectRepository.vehicleInfo_UserOnVehicleInspectionDealer+"//span[text()='はい']");
				Report.logger.pass("By default 'はい' button is selected for User Vehicle inspection questionaries");
				common.clickAction(executor.page, Portal_ObjectRepository.vehicleInfo_UserOnVehicleInspectionDealer+"//span[text()='いいえ']");
				Assertion.assertIsAttached(executor.page,Portal_ObjectRepository.vehicleInfo_HolderRelationModelContentPopup);
				System.out.println(common.getText(executor.page,Portal_ObjectRepository.vehicleInfo_HolderRelationModelContentPopup));
				Assertion.assertBytext(executor.page,Portal_ObjectRepository.vehicleInfo_HolderRelationModelContentPopup, "車検証上の使用者がご契約者と同一の法人以外の場合は、お申込みができません。ご不明な点がありましたら、カスタマーサービスセンターまでお電話ください。お取扱い範囲について");
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Holder relation model content pop-up is displayed after clicking on 'いいえ' button","HolderRelationModelContentPopupValidation",false);

			}else {
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.vehicleInfo_UserOnVehicleInspectionCertificateRelationImage);
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_UserOnVehicleInspectionCertificateRelationImage, data.getTestCaseID(), "Users image is displayed for 'User on Vehicle for Personal flow", "usersOnVehiclesListImageDisplayValidation");
				Assertion.assertHasCount(executor.page, Portal_ObjectRepository.vehicleInfo_UserOnVehicleInspectionCertificateRelationImage+"//label", 4);
				Report.logger.pass("4 images of users for 'User on Vehicle' options is displayed for Personal flow");

			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18864_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18864(data);
	}
	public void BAU_20546(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18864(data);
	}
	public void BAU_20548(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18864(data);
	}
	public void BAU_20549(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18864(data);
	}
	public void BAU_20553(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18864(data);
	}

	public void BAU_18867(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			Assertion.assertIsChecked(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityConfirm);
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityConfirm+"/../..", data.getTestCaseID(), "By default 'はい' button of 'owner On Vehicle Inspection Certificate And Policyholder' field is selected", "ownerOnVehicleInspectionCertificateAndPolicyholderDefaultSelection");
			common.clickAction(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityDeny);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection);
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection+"/../..", data.getTestCaseID(), "Owner On Vehicle Inspection Certificate Relation Image section displayed successfully", "VehicleRelationImageSectionValidation");

			vehicleInfo.vehicleInfoOwnerIntruptionOtherModelContentPopup(executor.page,data.getTestCaseID(), "その他法人");
			vehicleInfo.vehicleInfoOwnerIntruptionOtherModelContentPopup(executor.page,data.getTestCaseID(), "その他個人");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18867_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18867(data);
	}
	public void BAU_20582(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18867(data);
	}
	public void BAU_20584(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18867(data);
	}
	public void BAU_20585(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18867(data);
	}
	public void BAU_20589(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18867(data);
	}

	public void BAU_18868(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			Assertion.assertIsChecked(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityConfirm);
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityConfirm+"/../..", data.getTestCaseID(), "By default 'はい' button of 'owner On Vehicle Inspection Certificate And Policyholder' field is selected", "ownerOnVehicleInspectionCertificateAndPolicyholderDefaultSelection");
			common.clickAction(executor.page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityDeny);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection);
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection+"/../..", data.getTestCaseID(), "Owner On Vehicle Inspection Certificate Relation Image section displayed successfully", "VehicleRelationImageSectionValidation");

			vehicleInfo.vehicleInfoOwnerInterruptionIndiModelContentPopupValidation(executor.page,data.getTestCaseID(), "個人ディーラー");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18868_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18868(data);
	}
	public void BAU_20619(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18868(data);
	}
	public void BAU_20622(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18868(data);
	}
	public void BAU_20624(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18868(data);
	}
	public void BAU_20625(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18868(data);
	}

	public void BAU_18874(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);

			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.vehicleInfoCurrentInsuranceInputFieldError, "これは必須フィールドです");
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentInsuranceInfo_Section, data.getTestCaseID(), "'これは必須フィールドです' error message displayed when proceding next witout inputing data", "CIPolicyFieldErrorValidation");
				common.enterText(executor.page, Portal_ObjectRepository.vehicleCurrentInsurancePolicyNumber, "ああああ");
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.vehicleInfoCurrentInsuranceInputFieldError, "漢字や記号（「－」ハイフンを含む）を省略して半角英数字でご入力ください");
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_CurrentInsuranceInfo_Section, data.getTestCaseID(), "'漢字や記号（「－」ハイフンを含む）を省略して半角英数字でご入力ください' error message displayed when we input incorrect keyword", "IncorrectKeywordCIPolicyFieldErrorValidation");
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.vehicleInfoSuspendedInsuranceInputFieldError, "これは必須フィールドです");
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceInfo_Section, data.getTestCaseID(), "'これは必須フィールドです' error message displayed when proceding next witout inputing data", "CIPolicyFieldErrorValidation");
				common.enterText(executor.page, Portal_ObjectRepository.vehicleSuspendedInsurancePolicyNumber, "ああああ");
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.vehicleInfoSuspendedInsuranceInputFieldError, "漢字や記号（「－」ハイフンを含む）を省略して半角英数字でご入力ください");
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceInfo_Section, data.getTestCaseID(), "'漢字や記号（「－」ハイフンを含む）を省略して半角英数字でご入力ください' error message displayed when we input incorrect keyword", "IncorrectKeywordCIPolicyFieldErrorValidation");
			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18874_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18874(data);
	}


	public void BAU_18909(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			validation.BackToPreviousScreen(executor.page, data.getTestCaseID());
			if(policyHolder.CORPORATE_URL) {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.contractor_frontPageError, "法人名称、郵便番号および住所の変更が必要な方はカスタマーサービスセンターで承ります。");
				Report.logger.pass("'法人名称、郵便番号および住所の変更が必要な方はカスタマーサービスセンターで承ります。' error is displayed after comming back from Terms & conditions page");

				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.contractor_legalEntityType);
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.contractor_corporateName);
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.contractor_corporateNameKana);
				Report.logger.pass("'法人名についてお伺いします' section is not editable");


				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.contractor_representativeTitle);
				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.contractor_representativeTitleKana);
				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.contractor_lastNameKanji);
				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.contractor_FirstNameKanji);
				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.contractor_lastNameFurigana);
				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.contractor_FirstNameFurigana);
				Report.logger.pass("'法人の代表者についてお伺いします' section is editable");

				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.postalCode);
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.homeNumber);
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.buildingName);
				Report.logger.pass("Postal and address field of '連絡先についてお伺いします' section is not editable");

				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.contractor_RepresentativeMobileNumber);
				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.contractor_CorporateMobileNumber);
				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.emailaddress);
				Report.logger.pass("Phone and email field of '連絡先についてお伺いします' section is editable");
				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "'ご契約者について教えてください' page filed validated successfully after comming back from Terms & conditions page", "ご契約者について教えてくださいPageValidationWithPageBackNavigation", true);
			}else {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.lastNameKanji);
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.firstNameKanji);
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.lastNameFurigana);
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.firstNameFurigana);
				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.mobileNumber);
				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.postalCode);
				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.emailaddress);
				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.homeNumber);
				Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.buildingName);
				Report.logger.pass("All the fields of Contractor page is editable after coming back from next page");
			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18909_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18909(data);
	}
	public void BAU_21236(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18909(data);
	}
	public void BAU_21238(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18909(data);
	}
	public void BAU_21239(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18909(data);
	}
	public void BAU_21240(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18909(data);
	}

	public void BAU_18870(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());

			if(vehicleInfo.CORPORATE_URL) {
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.vehicleInfo_SMEAEBJikenkyoUnknownAnswerPopup);
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.vehicleInfo_SMEAEBJikenkyoUnknownAnswerPopup+"//a/span", "0120-945-072");
				Report.logger.pass("Phone number '0120-945-072' present inside AEB unknown Answer pop-up");
			}else {
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.vehicleInfo_PersonalAEBJikenkyoUnknownAnswerPopup);
				Assertion.assertBytextContains(executor.page, Portal_ObjectRepository.vehicleInfo_PersonalAEBJikenkyoUnknownAnswerPopup+"//p[5]", "0120-889-563");
				Report.logger.pass("Phone number '0120-889-563' present inside AEB unknown Answer pop-up");
			}
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"Phone number validation is completed inside AEB unknown Answer pop-up","AEBpopupValidation",true);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18870_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18870(data);
	}

	public void BAU_18916(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			payment.selectPaymentMethod(executor.page, data.getTestCaseID());
			if(payment.CORPORATE_URL){
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.payment_cardNote+"/span", "クレジットカードは、契約者となる法人名義のほか、代表者または契約手続きを行う方（委任を受けている方）名義のものをご利用ください。");
				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"'クレジットカードは、契約者となる法人名義のほか、代表者または契約手続きを行う方（委任を受けている方）名義のものをご利用ください。' Card note text is present for Lumpsum payment ","cardNoteValidationForLumpsumPayment",true);
			}else {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.payment_cardNote, "ご契約者さまご本人名義のみご利用いただけます。ご同居のご家族のカードのご利用をご希望の方はカスタマーサービスセンターまでお電話ください。");
				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"'ご契約者さまご本人名義のみご利用いただけます。ご同居のご家族のカードのご利用をご希望の方はカスタマーサービスセンターまでお電話ください。' Card note text is present for Lumpsum payment ","cardNoteValidationForLumpsumPayment",true);	
			}
			/*
			 * common.clickAction(executor.page, "//span[text()='戻る']");
			 * common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			 * Assertion.assertIsHidden(executor.page,
			 * Portal_ObjectRepository.loadingPageDialog); common.clickAction(executor.page,
			 * Portal_ObjectRepository.proceedPayment);
			 * common.passStatusWithScreenshots(executor.page,
			 * data.getTestCaseID(),"Install-ment payment option selected"
			 * ,"InstallmentPaymentOptionSelection",false); if(payment.CORPORATE_URL){
			 * Assertion.assertBytext(executor.page,
			 * Portal_ObjectRepository.payment_cardNote+"/span",
			 * "クレジットカードは、契約者となる法人名義のほか、代表者または契約手続きを行う方（委任を受けている方）名義のものをご利用ください。");
			 * common.passStatusWithScreenshots(executor.page, data.getTestCaseID()
			 * ,"'クレジットカードは、契約者となる法人名義のほか、代表者または契約手続きを行う方（委任を受けている方）名義のものをご利用ください。' Card note text is present for Lumpsum payment "
			 * ,"cardNoteValidationForLumpsumPayment",true); }else {
			 * Assertion.assertBytext(executor.page,
			 * Portal_ObjectRepository.payment_cardNote,
			 * "ご契約者さまご本人名義のみご利用いただけます。ご同居のご家族のカードのご利用をご希望の方はカスタマーサービスセンターまでお電話ください。");
			 * common.passStatusWithScreenshots(executor.page, data.getTestCaseID()
			 * ,"'ご契約者さまご本人名義のみご利用いただけます。ご同居のご家族のカードのご利用をご希望の方はカスタマーサービスセンターまでお電話ください。' Card note text is present for Lumpsum payment "
			 * ,"cardNoteValidationForLumpsumPayment",true); }
			 */

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	public void BAU_18916_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18916(data);
	}
	public void BAU_21438(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18916(data);
	}
	public void BAU_21440(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18916(data);
	}
	public void BAU_21443(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18916(data);
	}
	public void BAU_21444(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18916(data);
	}
	public void BAU_21446(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18916(data);
	}
	public void BAU_21447(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18916(data);
	}

	public void BAU_18917(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			
            if(utility.paymentInfoMap.get(data.getTestCaseID()).getPaymentMode().equals("CC_Installment Payment")) {
			common.clickAction(executor.page, Portal_ObjectRepository.CreditCarPayment+"//following::button[@data-testid='svgIconButton'][1]");
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"Pop up for Installment payment options displayed successfully","installmentPaymentInfoPopup",true);
			//System.out.println(common.getText(executor.page, Portal_ObjectRepository.payment_installmentPaymentPopupInfo));
			Assertion.assertBytext(executor.page, Portal_ObjectRepository.payment_installmentPaymentPopupInfo, "個人契約の場合 ご契約者さまご本人名義のみご利用いただけます。法人契約の場合 ご契約者となる法人名義のほか、代表者または契約手続きを行う方（委任を受けている方）名義のものがご利用いただけます。");
			common.clickAction(executor.page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
            }
            else if (utility.paymentInfoMap.get(data.getTestCaseID()).getPaymentMode().equals("CC_Full Payment")) {
			common.clickAction(executor.page, Portal_ObjectRepository.fullPaymentOption);
			common.clickAction(executor.page, Portal_ObjectRepository.CreditCarPayment+"//following::button[@data-testid='svgIconButton'][1]");
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"Pop up for Lumpsum payment options displayed successfully","lumpsumPaymentInfoPopup",true);
			//System.out.println(common.getText(executor.page, Portal_ObjectRepository.payment_lumpsumPaymentPopupInfo));
			Assertion.assertBytext(executor.page, Portal_ObjectRepository.payment_lumpsumPaymentPopupInfo, "個人契約の場合 ご契約者さまご本人名義のみご利用いただけます。法人契約の場合 ご契約者となる法人名義のほか、代表者または契約手続きを行う方（委任を受けている方）名義のものがご利用いただけます。");
			common.clickAction(executor.page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
            }


			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18917_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18917(data);
	}
	public void BAU_21459(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18917(data);
	}
	public void BAU_21460(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18917(data);
	}
	public void BAU_21462(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18917(data);
	}
	public void BAU_21463(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18917(data);
	}
	public void BAU_21464(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18917(data);
	}
	public void BAU_21465(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18917(data);
	}
	public void BAU_21466(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18917(data);
	}
	public void BAU_21467(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18917(data);
	}

	public void BAU_18918(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			payment.selectPaymentMethod(executor.page, data.getTestCaseID());
			payment.enterCreditCardInformation(executor.page, data.getTestCaseID());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_18918_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18918(data);
	}
	public void BAU_21986(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18918(data);
	}
	public void BAU_21991(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18918(data);
	}
	public void BAU_21993(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18918(data);
	}
	public void BAU_21996(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18918(data);
	}
	public void BAU_21997(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18918(data);
	}
	public void BAU_21998(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18918(data);
	}
	public void BAU_21999(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18918(data);
	}
	public void BAU_22000(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18918(data);
	}

	public void BAU_18919(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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

			if(payment.CORPORATE_URL) {
				Report.logger.info("****This requirement is revert back  during SME2 from BAU-18919****");
				//Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.closing_RegisterAccountImage);
				//Report.logger.pass("Register Account image is not displayed for Corporate flow");
				//Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.closing_CreateAccountSection);
				//Report.logger.pass("Create Account section is not displayed for Corporate flow");
				//Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaButtonOnFinalPage);
				//Report.logger.pass("'Emma by アクサにアカウント登録する' button is not displayed for corporate flow");

				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.closing_RegisterAccountImage);
				Report.logger.pass("Register Account image is displayed for Corporate flow");
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.closing_CreateAccountSection);
				Report.logger.pass("Create Account section is displayed for Corporate flow");
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaButtonOnFinalPage);
				Report.logger.pass("'Emma by アクサにアカウント登録する' button is displayed for corporate flow");


			}else {
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.closing_RegisterAccountImage);
				Report.logger.pass("Register Account image is displayed for Personal flow");
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.closing_CreateAccountSection);
				Report.logger.pass("Create Account section is displayed for Personal flow");
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaButtonOnFinalPage);
				Report.logger.pass("'Emma by アクサにアカウント登録する' button is displayed for Personal flow");

			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	public void BAU_21488(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21514(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21515(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21517(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21518(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21519(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21520(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21522(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21525(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21526(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21528(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21529(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21530(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21531(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21533(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}
	public void BAU_21534(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18919(data);
	}

	public void BAU_18920(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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

			if(payment.CORPORATE_URL) {
				Report.logger.info("This requiremnt is revert back as BAU-25414");
				//Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.closing_ContractDiscountImage);
				//Report.logger.pass("Contract discount image is not displayed for Corporate flow");

				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.closing_ContractDiscountImage);
				Report.logger.pass("Contract discount image is displayed for Corporate flow");

			}else {
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.closing_ContractDiscountImage);
				Report.logger.pass("Contract discount image is displayed for Personal flow");

			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	public void BAU_18920_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_18920(data);

	}

	public void BAU_21218(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			validation.BrowserBackNavigation(executor.page, data.getTestCaseID());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
				validation.BrowserBackNavigation(executor.page, data.getTestCaseID());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			validation.BrowserBackNavigation(executor.page, data.getTestCaseID());
			//common.clickAction(null, Portal_ObjectRepository.proceedNext);
			//validation.BrowserBackNavigation(executor.page, data.getTestCaseID());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfPolicyPlan(executor.page, data.getTestCaseID());
			validation.BrowserBackNavigation(executor.page, data.getTestCaseID());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			validation.BrowserBackNavigation(executor.page, data.getTestCaseID());
			policyHolder.e2eFlowOfPolicyHolderInformation(executor.page, data.getTestCaseID());
			validation.BrowserBackNavigation(executor.page, data.getTestCaseID());
			//validation.BrowserBackNavigation(executor.page, data.getTestCaseID());
			policyHolder.termAndConditionsConfirmationPage(executor.page, data.getTestCaseID());
			validation.BrowserBackNavigation(executor.page, data.getTestCaseID());
			contractConfirmation.contractDetailsConfirmationPage(executor.page, data.getTestCaseID());
			validation.BrowserBackNavigation(executor.page, data.getTestCaseID());
			payment.e2eFlowOfPaymentPage(executor.page, data.getTestCaseID());
			validation.BrowserBackNavigation(executor.page, data.getTestCaseID());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	public void BAU_21218_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_21218(data);

	}
	public void BAU_22300(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_21218(data);

	}
	public void BAU_22301(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_21218(data);

	}
	public void BAU_22302(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_21218(data);

	}
	public void BAU_22303(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_21218(data);

	}

	public void BAU_19290(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());

		try {

			Report.logger.info("***BAU-19664*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "3888000");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-19668*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "36019");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-19669*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "35504");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-19670*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "44002");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-19671*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "77777");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-19672*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "req010A1");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-19673*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "85123");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-19674*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "7700");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_19290_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_19290(data);

	}

	public void BAU_26118(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "ご自身や同乗者への補償");
			if(mainDriver.CORPORATE_URL) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.tarrifPage_vedioLink);
				Report.logger.pass("Video link is not present for corporate flow of ご自身や同乗者への補償");
			}else {
				Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.tarrifPage_vedioLink);
				Report.logger.pass("Video link is present for Personal flow for ご自身や同乗者への補償");
			}
			common.clickAction(executor.page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "ご自身のお車などへの補償");
			Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.tarrifPage_vedioLink);
			Report.logger.pass("Video link is present for ご自身のお車などへの補償");
			common.clickAction(executor.page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);

			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "事故の相手方への補償");
			Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.tarrifPage_vedioLink);
			Report.logger.pass("Video link is present for 事故の相手方への補償");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	public void BAU_26118_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "ご自身や同乗者への補償");
			if(mainDriver.CORPORATE_URL) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.tarrifPage_vedioLink);
				Report.logger.pass("Video link is not present for corporate flow of ご自身や同乗者への補償");
			}else {
				Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.tarrifPage_vedioLink);
				Report.logger.pass("Video link is present for Personal flow for ご自身や同乗者への補償");
			}
			common.clickAction(executor.page, "//label[@for='この内容で見積りをするId']");
			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "ご自身のお車などへの補償");
			Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.tarrifPage_vedioLink);
			Report.logger.pass("Video link is present for ご自身のお車などへの補償");
			common.clickAction(executor.page, "//label[@for='この内容で見積りをするId']");

			mainDriver.tarrifPageHeaderInfoModelPopupNavigation(executor.page, data.getTestCaseID(), "事故の相手方への補償");
			Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.tarrifPage_vedioLink);
			Report.logger.pass("Video link is present for 事故の相手方への補償");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	
	public void BAU_26129(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_26118(data);
	}
	public void BAU_26130(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_26118_SP(data);
	}

	public void BAU_24314(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(), data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.vehicleInfoVehicleWithNumberEightModal);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Model pop-up id getting displayed when licence plate number is start with '8'", "NumberEightModel",false);
			if(vehicleInfo.CORPORATE_URL){
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.vehicleInfoVehicleWithNumberEightModal+"//a/span", "0120-945-072");
				Report.logger.pass("Contact number is displayed as '0120-945-072' for corporate flow");
			}else {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.vehicleInfoVehicleWithNumberEightModal+"//a/span", "0120-000-194");
				Report.logger.pass("Contact number is displayed as '0120-000-194' for personal flow");
			}
			common.clickAction(executor.page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);

			Report.logger.info("Enter licence plate number as 80");
			common.enterText(executor.page, Portal_ObjectRepository.carlicensePlateClassCode, "80");
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Licence plate number has been entered as 80", "reEnteredLicencePlateNumber", true);
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);

			Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.vehicleInfoVehicleWithNumberEightModal);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Model pop-up id getting displayed when licence plate number is start with '80'", "NumberEightModel80",false);
			if(vehicleInfo.CORPORATE_URL){
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.vehicleInfoVehicleWithNumberEightModal+"//a/span", "0120-945-072");
				Report.logger.pass("Contact number is displayed as '0120-945-072' for corporate flow");
			}else {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.vehicleInfoVehicleWithNumberEightModal+"//a/span", "0120-000-194");
				Report.logger.pass("Contact number is displayed as '0120-000-194' for personal flow");
			}
			common.clickAction(executor.page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}
	public void BAU_24314_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24314(data);
	}
	public void BAU_26366(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24314(data);
	}
	public void BAU_26367(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24314(data);
	}
	public void BAU_26368(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24314(data);
	}
	public void BAU_26369(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24314(data);
	}

	public void BAU_22508(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			Assertion.assertBytext(executor.page, "//p[contains(@data-aem-id,'PriceChoice_EditOrSaveTextSecond0')]", "これまでの内容を保存する");
			common.scrollPageTillElementLocation(executor.page, "//span[text()='前(運転者情報)へ']");
			Report.logger.pass("Page has been scrolled till down");
			Assertion.assertBytext(executor.page, "//p[contains(@data-aem-id,'PriceChoice_EditOrSaveTextSecond0')]", "保存する");

			mainDriver.e2eFlowOfPolicyPlan(executor.page, data.getTestCaseID());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(), data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			policyHolder.e2eFlowOfPolicyHolderInformation(executor.page, data.getTestCaseID());
			validation.navigateToQuotationPageFromThreeMenuList(executor.page, data.getTestCaseID());
			if(mainDriver.CORPORATE_URL) {
				Assertion.assertIsNotAttached(executor.page, "//p[contains(@data-aem-id,'PriceChoice_EditOrSaveTextSecond0')]");
				Report.logger.pass("'これまでの内容を保存する' text is not displayed");
			}else {
				Assertion.assertIsAttached(executor.page, "//p[contains(@data-aem-id,'PriceChoice_EditOrSaveTextSecond0')]");
				Assertion.assertBytext(executor.page, "//p[contains(@data-aem-id,'PriceChoice_EditOrSaveTextSecond0')]", "これまでの内容を保存する");
				common.scrollPageTillElementLocation(executor.page, "//span[text()='前(運転者情報)へ']");
				Report.logger.pass("Page has been scrolled till down");
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Tarrif page has been scrolled till down", "ScrolledTariffPageTillDown",false);
				Assertion.assertBytext(executor.page, "//p[contains(@data-aem-id,'PriceChoice_EditOrSaveTextSecond0')]", "保存する");
			}


			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_22508_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_22508(data);
	}

	public void BAU_23090(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(), data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.contractor_delegateInfoSection);
			Assertion.assertBytext(executor.page, Portal_ObjectRepository.contractor_delegateInfoSection+"//span[text()='いいえ']/following-sibling::span", "（委任を受けている方＊）");
			Assertion.assertBytext(executor.page, Portal_ObjectRepository.contractor_delegateInfoSection+"//li", "自動車保険契約の締結委任を受けている方をいいます。");
			common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.contractor_delegateInfoSection, data.getTestCaseID(), "Text validation of Delegate info section is completed", "delegateInfoSectionValidation");
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_23090_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_23090(data);
	}

	public void BAU_24406(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(), data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			policyHolder.e2eFlowOfPolicyHolderInformation(executor.page, data.getTestCaseID());
			policyHolder.termAndConditionsConfirmationPage(executor.page, data.getTestCaseID());
			contractConfirmation.dataValidationOfVehicleInfoPage(executor.page, data.getTestCaseID());
			if(!data.getInsurancePurchaseType().equals("初めて自動車保険/バイク保険に加入する")) {
				validation.navigateToBeforeApplicationFromThreeMenuList(executor.page, data.getTestCaseID());

				if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
					executor.page.locator(Portal_ObjectRepository.vehicleInfo_CurrentInsurance_BranchNumber).clear();
				}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
					executor.page.locator(Portal_ObjectRepository.vehicleInfo_suspendedInsurance_BranchNumber).clear();
				}
				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Branch number field get empty and proceed to next button", "emptyBranchNumberVehicleInformationPage", true);
				common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				if(Assertion.assertURLContains(executor.page, "page8")) {
					System.out.println("Inside condition");
					common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
					Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				}
				System.out.println("Before TC page click");
				common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				System.out.println("After TC page click");
				Assertion.assertURLContains(executor.page, "page10");
				Assertion.assertIsNotAttached(executor.page, "//section[@data-aem-id='ContractConfirm_ContractForm_PageSection_05']//div[text()='枝番★']//following-sibling::div//div");
				Report.logger.pass("'枝番★' section is not present inside Contract confirmation page when branch number is not inputted in 'Vehicle Information page'");
				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Branch number field is not displayed in Contract confirmation page ", "emptyBranchNumberContractConfirmationPage", true);
				//contractConfirmation.dataValidationOfVehicleInfoPage(executor.page, data.getTestCaseID());
			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",true);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,true);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_24604(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24605(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24606(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24611(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24612(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24613(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24598(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24599(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24601(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24602(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24603(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24607(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24608(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24609(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24610(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}
	public void BAU_24614(ParameterOfHomeAndQuotationPage data) throws IOException {
		BAU_24406(data);
	}

	//******SME2 Requirements*******

	public void BAU_27401(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());

		try {

			Report.logger.info("***BAU-28491*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "req10032A1");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-28981*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "36019");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-28983*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "40105");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-28985*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "36070");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-28987*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "37008");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-28991*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "7010");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-28996*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "38800");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-29000*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "49509");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	//********SME-2*********BAUs**************************************************************************************************************************************************************************

	public void BAU_27401_SP(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());

		try {

			Report.logger.info("***BAU-28494*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "req10032A1");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-28982*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "36019");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-28984*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "40105");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-28986*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "36070");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-28988*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "37008");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-28994*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "7010");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-28999*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "38800");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			Report.logger.info("***BAU-29001*** validation");
			home.homePageCampainCodeValidation(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType(), "49509");
			url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_27045(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			if(payment.CORPORATE_URL) {
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.closing_EmmaAccordionSection);
				common.clickAction(executor.page, Portal_ObjectRepository.closing_EmmaAccordionSection);
				System.out.println(common.getText(executor.page, Portal_ObjectRepository.closing_EmmaAccordionSectionText));
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.closing_EmmaAccordionSectionText, "ご契約内容の確認電話番号、メールアドレスの変更ご契約の継続（更新）2台目のお車のお見積りなど");
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.closing_EmmaAccordionSectionText+"/..", data.getTestCaseID(), "Following text 'ご契約内容の確認電話番号、メールアドレスの変更ご契約の継続（更新）2台目のお車のお見積りなど' inside 'Emma by アクサでできること' is validated successfully", "emmaAccordianTextValidation");

			}else {

			}

			//String quotationNumber=validation.getQuotationNumber(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Pass",quotationNumber);
		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}

	public void BAU_27616(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27617(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27618(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27622(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27623(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27624(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27625(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27627(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}

	public void BAU_27629(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27638(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27639(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27640(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27641(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27642(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27643(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}
	public void BAU_27644(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27045(data);
	}

	public void BAU_26766(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.loginTextboxValidation(executor.page, data.getTestCaseID());
			home.loginToEmmaApplication(executor.page, data.getTestCaseID());
			Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaLoginBox);
			Report.logger.pass("Login box is not present after login from personal");
			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(), "Corporate");
			home.loginTextboxValidation(executor.page, data.getTestCaseID());
			home.loginToEmmaApplication(executor.page, data.getTestCaseID());
			Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaLoginBox);
			Report.logger.pass("Login box is not present after login from Corporate");


			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}

	public void BAU_26792(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26766(data);
	}
	public void BAU_26791(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriver(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.selectPolicyPlan(executor.page,data.getTestCaseID());
			mainDriver.infoCorrectionDialog(executor.page,data.getTestCaseID());
			Assertion.assertBytext(executor.page, Portal_ObjectRepository.tentativErrorContent, "次の項目が仮となっています。チェックボックスを外し、修正をお願いします。");
			Assertion.assertBytext(executor.page, Portal_ObjectRepository.tentativErrorBox+"//li", "補償を開始したい日");
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Tentative error in case of 'I dont know functions' id displaying", "tentativeErrorValidation", false);

			home.loginTextboxValidation(executor.page, data.getTestCaseID());
			home.loginToEmmaApplication(executor.page, data.getTestCaseID());
			Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaLoginBox);
			Report.logger.pass("Login box is not present after login from Corporate");


			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	public void BAU_26794(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {


			home.loginTextboxValidation(executor.page, data.getTestCaseID());
			home.loginToEmmaApplication(executor.page, data.getTestCaseID());
			Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaLoginBox);
			Report.logger.pass("Login box is not present after login from Corporate");
			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			home.loginTextboxValidation(executor.page, data.getTestCaseID());
			home.loginToEmmaApplication(executor.page, data.getTestCaseID());
			Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaLoginBox);
			Report.logger.pass("Login box is not present after login from Personal");


			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	public void BAU_27020(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26794(data);
	}
	public void BAU_24904(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.insurancePurchaseFlow(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
			home.loginToEmmaApplication(executor.page, data.getTestCaseID());
			Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaLoginBox);
			Report.logger.pass("Login box is not present after login from Corporate");
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(),"Individual");
			home.acceptSwithFlowPopup(executor.page, data.getTestCaseID(), "Individual");
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}

	public void BAU_26558(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_24904(data);
	}

	public void BAU_26562(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_24904(data);
	}

	public void BAU_25418(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.imageOfDataSaveIcon);
			validation.threeLineMenuOptionNavigation(executor.page, data.getTestCaseID());
			Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.menuSaveContent);
			Report.logger.pass("'これまでの内容を保存する' icon is not present inside hamburger menu of T&C page");
			common.clickAction(executor.page, Portal_ObjectRepository.threeLineMenuButton);
			policyHolder.termAndConditionsConfirmationPage(executor.page, data.getTestCaseID());
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.imageOfDataSaveIcon);
			validation.threeLineMenuOptionNavigation(executor.page, data.getTestCaseID());
			Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.menuSaveContent);
			Report.logger.pass("'これまでの内容を保存する' icon is not present inside hamburger menu of Summary page");
			common.clickAction(executor.page, Portal_ObjectRepository.threeLineMenuButton);
			contractConfirmation.contractDetailsConfirmationPage(executor.page, data.getTestCaseID());
			Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.imageOfDataSaveIcon);
			Report.logger.pass("save data icon is not present in Payment page");
			payment.e2eFlowOfPaymentPage(executor.page, data.getTestCaseID());
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}

	public void BAU_26623(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25418(data);
	}
	public void BAU_26625(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25418(data);
	}
	public void BAU_26614(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.imageOfDataSaveIcon);
			validation.threeLineMenuOptionNavigation(executor.page, data.getTestCaseID());
			Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.menuSaveContent);
			Report.logger.pass("'これまでの内容を保存する' icon is not present inside hamburger menu of Policyholder page page");
			common.clickAction(executor.page, Portal_ObjectRepository.threeLineMenuButton);
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);

			policyHolder.e2eFlowOfPolicyHolderInformation(executor.page, data.getTestCaseID());
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.imageOfDataSaveIcon);
			validation.threeLineMenuOptionNavigation(executor.page, data.getTestCaseID());
			Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.menuSaveContent);
			Report.logger.pass("'これまでの内容を保存する' icon is not present inside hamburger menu of T&C page");
			common.clickAction(executor.page, Portal_ObjectRepository.threeLineMenuButton);
			common.clickAction(executor.page, Portal_ObjectRepository.proceedNext);

			/*
			 * policyHolder.termAndConditionsConfirmationPage(executor.page,
			 * data.getTestCaseID()); Assertion.assertIsAttached(executor.page,
			 * Portal_ObjectRepository.imageOfDataSaveIcon);
			 * validation.threeLineMenuOptionNavigation(executor.page,
			 * Portal_ObjectRepository.threeLineMenuButton);
			 * Assertion.assertIsNotAttached(executor.page,
			 * Portal_ObjectRepository.menuSaveContent); Report.logger.
			 * pass("'これまでの内容を保存する' icon is not present inside hamburger menu of Summary page"
			 * ); common.clickAction(executor.page,
			 * Portal_ObjectRepository.threeLineMenuButton);
			 */
			validation.BackToPreviousScreen(executor.page, data.getTestCaseID());
			validation.pageURLValidation(executor.page, data.getTestCaseID());

			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.imageOfDataSaveIcon);
			validation.threeLineMenuOptionNavigation(executor.page, data.getTestCaseID());
			Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.menuSaveContent);
			Report.logger.pass("'これまでの内容を保存する' icon is not present inside hamburger menu of Policyholder page page");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			//utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	public void BAU_26621(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26614(data);
	}

	public void BAU_25419(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			policyHolder.certificateSelection(executor.page, data.getTestCaseID());

			Report.logger.info("BAU-25419 validation"); 
			Assertion.assertIsAttached(executor.page,Portal_ObjectRepository.TnC_ContractConfirmationDetailsSection);
			common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.TnC_ContractConfirmationDetailsSection, data.getTestCaseID(), "Contract confirmation details section details are available for T&C page", "contractConfirmationValidation");

			if(policyHolder.CORPORATE_URL) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.TnC_EInsuranceCertificatesection);
				Report.logger.pass("E Insurance certificate section is not displayed for Corporate flow");
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.pdfCertificateCheckbox);
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.insuranceCertificateCheckbox);
			}else {
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.TnC_EInsuranceCertificatesection);
				Report.logger.pass("E Insurance certificate section is displayed for Personal flow");
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.pdfCertificateCheckbox);
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.insuranceCertificateCheckbox);
				common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.TnC_EInsuranceCertificatesection, data.getTestCaseID(), "E Insurance certificate section is displayed for Personal flow for T&C page", "EinsuranceCertificateValidation");
			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}

	public void BAU_26690(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25419(data);
	}
	public void BAU_26691(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25419(data);
	}
	public void BAU_26692(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25419(data);
	}
	public void BAU_26693(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25419(data);
	}

	public void BAU_25414(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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

			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.closing_ContractDiscountImage);
			common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.closing_ContractDiscountImage, data.getTestCaseID(), "Discount Image is present under closing page", "discountImageValidation");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_26606(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26608(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26611(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26613(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26615(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26616(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26617(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26618(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26629(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26633(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26635(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26638(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26640(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26642(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26644(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}
	public void BAU_26646(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25414(data);
	}

	public void BAU_25415(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.closing_HeaderText);

			if(data.getInsurancePurchaseType().equals("現在他社で加入している") && utility.paymentInfoMap.get(data.getTestCaseID()).getPaymentMode().startsWith("CC")) {
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.closing_NoPDConditionText);
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.closing_NoPDConditionText, "ご契約内容についてはEmma（エマ）byアクサ（旧名称：マイ・アクサファイル）にてご確認いただけます。");
				common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.closing_NoPDConditionText+"/../..", data.getTestCaseID(), "Text 'ご契約内容についてはEmma（エマ）byアクサ（旧名称：マイ・アクサファイル）にてご確認いただけます。' is present under closing page", "noPDTextValidation");

			}else {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.closing_NoPDConditionText);
				//Assertion.assertBytextNotContains(executor.page, Portal_ObjectRepository.closing_NoPDConditionText+"/../..", "ご契約内容についてはEmma（エマ）byアクサ（旧名称：マイ・アクサファイル）にてご確認いただけます。");
				Report.logger.info("Text 'ご契約内容についてはEmma（エマ）byアクサ（旧名称：マイ・アクサファイル）にてご確認いただけます。' is not present");

			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_26517(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25415(data);
	}
	public void BAU_26519(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25415(data);
	}
	public void BAU_26520(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25415(data);
	}
	public void BAU_26521(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25415(data);
	}


	public void BAU_25416(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.closing_HeaderText);

			if(data.getMemberType().equals("Login Member")) {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.closing_HeaderText, "お申込みありがとうございます。続いて残りのお手続きをお願いいたします。");
				common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.closing_HeaderText+"/..", data.getTestCaseID(), "Text 'お申込みありがとうございます。続いて残りのお手続きをお願いいたします。' is present under closing page", "HeaderTextValidation");
			}else if(data.getMemberType().equals("Non Login Member")) {
				if(data.getInsurancePurchaseType().equals("現在他社で加入している") && utility.paymentInfoMap.get(data.getTestCaseID()).getPaymentMode().startsWith("CC")) {
					Assertion.assertBytext(executor.page, Portal_ObjectRepository.closing_HeaderText, "お申込みありがとうございます。続いてアカウント登録をお願いします。");
					common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.closing_HeaderText+"/..", data.getTestCaseID(), "Text 'お申込みありがとうございます。続いてアカウント登録をお願いします。' is present under closing page", "HeaderTextValidation");

				}else {
					Assertion.assertBytext(executor.page, Portal_ObjectRepository.closing_HeaderText, "お申込みありがとうございます。まずはアカウント登録をお願いします。続いて残りのお手続きをお願いいたします。");
					common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.closing_HeaderText+"/..", data.getTestCaseID(), "Text 'お申込みありがとうございます。まずはアカウント登録をお願いします。続いて残りのお手続きをお願いいたします。' is present under closing page", "HeaderTextValidation");

				}
			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);
			//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);
			//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);
			//executor.browser.close();
		}

	}

	public void BAU_26448(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26455(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26457(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26476(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26477(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26478(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26480(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26481(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26484(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26485(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26486(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26488(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26489(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26490(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26491(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_26492(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}

	public void BAU_27487(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27547(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27550(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27551(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27552(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27553(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27554(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27555(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27556(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27557(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27559(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27560(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27562(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27564(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27565(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}
	public void BAU_27566(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25416(data);
	}


	public void BAU_25026(ParameterOfHomeAndQuotationPage data) throws IOException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			validation.PageLogoValidation(executor.page, data.getTestCaseID());
			policyHolder.e2eFlowOfPolicyHolderInformation(executor.page, data.getTestCaseID());
			validation.PageLogoValidation(executor.page, data.getTestCaseID());
			policyHolder.termAndConditionsConfirmationPage(executor.page, data.getTestCaseID());
			common.clickAction(executor.page, Portal_ObjectRepository.logoImage);
			Assertion.assertBytext(executor.page, Portal_ObjectRepository.roughtEstimationDialogTitle, "このページから離れますか？");
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Previous Informaion confirmation dialogbox is displaying", "SummaryPagelogoNavigation", false);
			common.clickAction(executor.page, "//button[text()='はい']");
			Thread.sleep(5000);
			executor.page.reload();
			Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.emmaLoginID);
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Emma Login Page Displayed successfully", "emmaLoginPageDisplay", true);

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}

	}

	public void BAU_26729(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25026(data);
	}
	public void BAU_26742(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25026(data);
	}
	public void BAU_26745(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			validation.PageLogoValidation(executor.page, data.getTestCaseID());
			contractConfirmation.dataValidationOfContractTypePage(executor.page, data.getTestCaseID());
			common.clickAction(executor.page, Portal_ObjectRepository.logoImage);
			Assertion.assertBytext(executor.page, Portal_ObjectRepository.roughtEstimationDialogTitle, "このページから離れますか？");
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Previous Informaion confirmation dialogbox is displaying", "SummaryPagelogoNavigation", false);
			common.clickAction(executor.page, "//button[text()='はい']");
			Thread.sleep(5000);
			executor.page.reload();
			Assertion.assertIsEditable(executor.page, Portal_ObjectRepository.emmaLoginID);
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(), "Emma Login Page Displayed successfully after clicking on 'はい' button of Logo dialog", "emmaLoginPageDisplay", true);

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void BAU_26748(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26745(data);
	}

	public void BAU_25410(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			payment.registerAccountInEmma(executor.page, data.getTestCaseID(), "Prem@2020", "Prem@2020");
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.RegisterAccountMailSendConfirmation);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Send email confirmation text is displayed successfully", "endEmailConfirmationPageValidation",true);
			/*
			 * common.clickAction(executor.page, "//div[@class='l-header-logo']");
			 * common.enterText(executor.page, Portal_ObjectRepository.emmaLoginID,
			 * policyHolder.contractorInfo(data.getTestCaseID()).getEmailAddress());
			 * common.enterText(executor.page, Portal_ObjectRepository.emmaLoginID,
			 * "Prem@2020"); common.clickAction(executor.page,
			 * Portal_ObjectRepository.emmaLoginButton);
			 */
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void BAU_26273(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26274(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26275(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26276(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26277(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26278(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26305(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26307(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26316(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26317(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26318(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26319(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26320(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26321(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26322(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26323(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25410(data);
	}
	public void BAU_26495(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

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
			payment.registerAccountInEmma(executor.page, data.getTestCaseID(), "Prem@2020", "Prem@2020");
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	public void BAU_26497(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26495(data);
	}

	public void BAU_25523(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.loginToEmmaApplication(executor.page, data.getTestCaseID());
			if(data.getInsurnaceFlowType().equals("Individual") && (!Assertion.assertURLContains(executor.page, "sme"))) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaLoginBox);
				Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.bikeInsuranceOption);
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Personal page after entring personal emma credentials", "PersonalLoginPageValidation",true);

			}else if((!data.getInsurnaceFlowType().equals("Individual")) && Assertion.assertURLContains(executor.page, "sme")) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaLoginBox);
				Assertion.assertIsDisabled(executor.page, Portal_ObjectRepository.bikeInsuranceOption);
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Personal page after entring Corporate emma credentials", "corporateloginPageValidation",true);
			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	public void BAU_26261(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25523(data);
	}
	public void BAU_26262(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25523(data);
	}
	public void BAU_26264(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25523(data);
	}
	public void BAU_26265(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25523(data);
	}

	public void BAU_25402(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.emmaIDRecovery(executor.page, data.getTestCaseID());

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void BAU_26347(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25402(data);
	}
	public void BAU_26349(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25402(data);
	}
	public void BAU_26331(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25402(data);
	}
	public void BAU_26345(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25402(data);
	}

	public void BAU_26570(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(),"Individual");
		try {

			home.loginToEmmaApplication(executor.page, data.getTestCaseID());
			if(data.getInsurnaceFlowType().equals("Individual") && (!Assertion.assertURLContains(executor.page, "sme"))) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaLoginBox);
				Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.bikeInsuranceOption);
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Personal page after entring personal emma credentials", "PersonalLoginPageValidation",true);

			}else if((!data.getInsurnaceFlowType().equals("Individual")) && Assertion.assertURLContains(executor.page, "sme")) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaLoginBox);
				Assertion.assertIsDisabled(executor.page, Portal_ObjectRepository.bikeInsuranceOption);
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Personal page after entring Corporate emma credentials", "corporateloginPageValidation",true);
			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void BAU_26575(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26570(data);
	}
	public void BAU_26576(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26570(data);
	}
	public void BAU_26578(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26570(data);
	}
	public void BAU_26581(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(),"Corporate-->Individual");
		try {

			home.loginToEmmaApplication(executor.page, data.getTestCaseID());
			if(data.getInsurnaceFlowType().equals("Individual") && (!Assertion.assertURLContains(executor.page, "sme"))) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaLoginBox);
				Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.bikeInsuranceOption);
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Personal page after entring personal emma credentials", "PersonalLoginPageValidation",true);

			}else if((!data.getInsurnaceFlowType().equals("Individual")) && Assertion.assertURLContains(executor.page, "sme")) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaLoginBox);
				Assertion.assertIsDisabled(executor.page, Portal_ObjectRepository.bikeInsuranceOption);
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Personal page after entring Corporate emma credentials", "corporateloginPageValidation",true);
			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}

	public void BAU_26582(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26581(data);
	}
	public void BAU_26583(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26581(data);
	}
	public void BAU_26584(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26581(data);
	}


	public void BAU_26967(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		//****BAU-25409*****
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			policyHolder.dataEntryOnPolicyHolderInformationPage(executor.page, data.getTestCaseID());
			Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.accountMergePopupTitle);
			Report.logger.pass("Register account dialog is not displayed");
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"Successfully proceeded to Term and conditions page","proceededToTermAndConditionsPage",true);

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}

	public void BAU_26974(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26967(data);
	}
	public void BAU_26976(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26967(data);
	}

	public void BAU_26977(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		//****BAU-25409*****
		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			home.e2eFlowOfHomePgae(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType(),data.getPurchaseInsurnaceInceptionDate(),data.getInsurnaceFlowType());
			if(data.getInsurancePurchaseType().equals("現在他社で加入している")) {
				currentInsurnace.e2eFlowOfCurrentInsurnacePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}else if(data.getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				suspension.e2eFlowOfSuspensionCertificatePage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType());
			}
			quotationPage.e2eFlowOfCarBikeQuotationPage(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			mainDriver.e2eFlowOfAboutMainDriverAndPolicyPlan(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurnaceFlowType());
			vehicleInfo.e2eFlowOfVehicleInformation(executor.page, data.getTestCaseID(),data.getInsurnaceProductType(),data.getInsurancePurchaseType());
			policyHolder.dataEntryOnPolicyHolderInformationPage(executor.page, data.getTestCaseID());
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.accountMergePopupTitle);
			policyHolder.accountMergePopupValidation(executor.page, data.getTestCaseID(), "No");
			common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"Successfully proceeded to Term and conditions page","proceededToTermAndConditionsPage",true);

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}

	public void BAU_26972(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26977(data);
	}
	public void BAU_26978(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26977(data);
	}
	public void BAU_28816(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26967(data);
	}

	public void BAU_26548(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaNewProcedureTab);
			if(home.CORPORATE_URL) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaNewProcedureDOBNote);
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList);
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList+"//a[text()='お客さまページ複数契約割引の詳細はこちらから']");
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList+"//a[text()='ペット保険はこちら']");
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList+"//a[text()='「アクサダイレクトの入院手術保険」Ｗｅｂサービス終了のお知らせ']");
				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"Text '※お見積りやご契約で運転者情報（免許証の色や生年月日等）を更新された場合、他のお見積りやご契約に自動的に反映している場合がありますのでご注意ください。' is not present for corporate flow","licenceColorNoteValidation",true);
			}else {
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaNewProcedureDOBNote);
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList);
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList+"//a[text()='お客さまページ複数契約割引の詳細はこちらから']");
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList+"//a[text()='ペット保険はこちら']");
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList+"//a[text()='「アクサダイレクトの入院手術保険」Ｗｅｂサービス終了のお知らせ']");
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.emmaNewProcedureDOBNote, "※お見積りやご契約で運転者情報（免許証の色や生年月日等）を更新された場合、他のお見積りやご契約に自動的に反映している場合がありますのでご注意ください。");
				common.passStatusWithScreenshots(executor.page, data.getTestCaseID(),"Text '※お見積りやご契約で運転者情報（免許証の色や生年月日等）を更新された場合、他のお見積りやご契約に自動的に反映している場合がありますのでご注意ください。' is present for Personal flow","licenceColorNoteValidation",true);

			}

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}

	public void BAU_26991(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26548(data);
	}
	public void BAU_26993(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26548(data);
	}
	public void BAU_26994(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26548(data);
	}
	public void BAU_26995(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26548(data);
	}

	public void BAU_26550(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			int count=executor.page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
			if(count>=1) {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel, "2台目以降のお見積りを始める");
				common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.ampStartPolicyButtonLabel, data.getTestCaseID(),"Start quote button text is displayed as '2台目以降のお見積りを始める' since "+count+" quotation is present in account", "ampStartQuoteButtonTextValidation");

			}else {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel, "新規のお見積りを始める");
				common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.ampStartPolicyButtonLabel,data.getTestCaseID(), "Start quote button text is displayed as '新規のお見積りを始める' since "+count+" quotation is present in account", "ampStartQuoteButtonTextValidation");
			}

			common.clickAction(executor.page, Portal_ObjectRepository.emmaNewProcedureTab);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);
			common.clickAction(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);
			Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);

			if(home.CORPORATE_URL && Assertion.assertURLContains(executor.page, "sme")) {
				Report.logger.pass("Url is displayed as "+executor.page.url());

			}else if(!(home.CORPORATE_URL && Assertion.assertURLContains(executor.page, "sme"))) {
				Report.logger.pass("Url is displayed as "+executor.page.url());
			}else {
				Report.logger.fail("Url is displayed as expected as "+executor.page.url());
			}
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract type page", "ampContractTypePageNavigation",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}

	public void BAU_26378(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26550(data);
	}
	public void BAU_26379(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26550(data);
	}
	public void BAU_26380(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26550(data);
	}
	public void BAU_26381(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26550(data);
	}

	public void BAU_26050(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.ampOETab_PolicyListBodyTips);
			System.out.println(common.getText(executor.page, Portal_ObjectRepository.ampOETab_PolicyListBodyTips+"/div"));

			if(home.CORPORATE_URL) {
				Assertion.assertBytextContains(executor.page, Portal_ObjectRepository.ampOETab_PolicyListBodyTips+"/div","ご契約内容の確認電話番号・メールアドレスの変更事故のご報告はこちら");
			}else {
				Assertion.assertBytextContains(executor.page, Portal_ObjectRepository.ampOETab_PolicyListBodyTips+"/div","お車の変更・お車を手放す場合ご住所の変更ご契約内容の確認/変更 など事故のご報告はこちら");
			}
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Text of PolicyTips content is valiadated", "PolicyTipsValidation",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}

	public void BAU_26733(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26050(data);
	}
	public void BAU_26735(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26050(data);
	}
	public void BAU_26736(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26050(data);
	}
	public void BAU_26738(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26050(data);
	}

	public void BAU_26053(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.ampOETab_PolicyListBodyTips);
			System.out.println(common.getText(executor.page, Portal_ObjectRepository.ampOETab_PolicyListBodyTips+"/div"));

			if(home.CORPORATE_URL) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.ampOETab_PolicyListBodyTipsWarning);
				Report.logger.pass("Policy List tips warning content is not displayed for corporate flow");
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList);
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList+"//a[text()='お客さまページ複数契約割引の詳細はこちらから']");
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList+"//a[text()='ペット保険はこちら']");
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList+"//a[text()='「アクサダイレクトの入院手術保険」Ｗｅｂサービス終了のお知らせ']");
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.emmaBottomPolicyList, data.getTestCaseID(), "Bottom Policy list notice content validated for Corporate flow", "policyListBottomValidation");

			}else {
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.ampOETab_PolicyListBodyTipsWarning);
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.ampOETab_PolicyListBodyTipsWarning, data.getTestCaseID(), "Policy List Tips Warning is displayed for personal flow", "policyListTipsWarningValidation");
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList);
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList+"//a[text()='お客さまページ複数契約割引の詳細はこちらから']");
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList+"//a[text()='ペット保険はこちら']");
				Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.emmaBottomPolicyList+"//a[text()='「アクサダイレクトの入院手術保険」Ｗｅｂサービス終了のお知らせ']");
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.emmaBottomPolicyList, data.getTestCaseID(), "Bottom Policy list notice content validated for Personal flow", "policyListBottomValidation");
			}
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Policy List Tips warning and bottom notice validation is completed", "OeTabPolicyTipsValidation",true);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}

	public void BAU_26360(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26053(data);
	}
	public void BAU_26371(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26053(data);
	}
	public void BAU_26373(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26053(data);
	}
	public void BAU_26375(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26053(data);
	}

	public void BAU_26054(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			int count=executor.page.locator(Portal_ObjectRepository.ampOETab_PolicyListBoxLastItem).count();

			if(count>0) {
				if(home.CORPORATE_URL) {
					Assertion.assertIsNotAttached(executor.page,Portal_ObjectRepository.ampOETab_PolicyListBoxLastItem+"[1]//span[text()='お車の変更・お車を手放す場合']");
					Assertion.assertIsNotAttached(executor.page,Portal_ObjectRepository.ampOETab_PolicyListBoxLastItem+"[1]//span[text()='ご住所の変更']");
					Assertion.assertIsAttached(executor.page,Portal_ObjectRepository.ampOETab_PolicyListBoxLastItem+"[1]//span[text()='ご契約内容の確認/変更']");
					Assertion.assertIsAttached(executor.page,Portal_ObjectRepository.ampOETab_PolicyListBoxLastItem+"[1]//span[text()='電話番号・メールアドレスの変更']");
					Assertion.assertIsNotAttached(executor.page,Portal_ObjectRepository.ampOETab_PolicyListBoxLastItem+"[1]//span[text()='試算（お車の変更・契約内容変更）']");
					Assertion.assertIsNotAttached(executor.page,Portal_ObjectRepository.ampOETab_PolicyListBoxLastItem+"[1]//button[text()='お手続きはこちらから']");

				}else {
					Assertion.assertIsAttached(executor.page,Portal_ObjectRepository.ampOETab_PolicyListBoxLastItem+"[1]//span[text()='お車の変更・お車を手放す場合']");
					Assertion.assertIsAttached(executor.page,Portal_ObjectRepository.ampOETab_PolicyListBoxLastItem+"[1]//span[text()='ご住所の変更']");
					Assertion.assertIsAttached(executor.page,Portal_ObjectRepository.ampOETab_PolicyListBoxLastItem+"[1]//span[text()='ご契約内容の確認/変更']");
					Assertion.assertIsAttached(executor.page,Portal_ObjectRepository.ampOETab_PolicyListBoxLastItem+"[1]//span[text()='電話番号・メールアドレスの変更']");
					Assertion.assertIsAttached(executor.page,Portal_ObjectRepository.ampOETab_PolicyListBoxLastItem+"[1]//span[text()='試算（お車の変更・契約内容変更）']");
					Assertion.assertIsAttached(executor.page,Portal_ObjectRepository.ampOETab_PolicyListBoxLastItem+"[1]//button[text()='お手続きはこちらから']");
				}
				common.takeScreenShotsOfComponent(executor.page, Portal_ObjectRepository.ampPolicyListBox+"[1]", data.getTestCaseID(), "Text and button Validation of Policy list box", "policyListBoxTextValidation");
			}else {
				common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "Policy List box is not present for this BAU validation", "failed_PolicyListTextValidation",true);
			}

			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_26744(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26054(data);
	}
	public void BAU_26746(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26054(data);
	}
	public void BAU_26747(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26054(data);
	}
	public void BAU_26749(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26054(data);
	}
	
	public void BAU_26055(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		
		try {

			int count=executor.page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
			if(count>=1) {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel, "2台目以降のお見積りを始める");
				common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.ampStartPolicyButtonLabel, data.getTestCaseID(),"Start quote button text is displayed as '2台目以降のお見積りを始める' since "+count+" quotation is present in account", "ampStartQuoteButtonTextValidation");

			}else {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel, "新規のお見積りを始める");
				common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.ampStartPolicyButtonLabel,data.getTestCaseID(), "Start quote button text is displayed as '新規のお見積りを始める' since "+count+" quotation is present in account", "ampStartQuoteButtonTextValidation");
			}
			
			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);

			common.clickAction(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);
			Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);

			if(home.CORPORATE_URL && Assertion.assertURLContains(executor.page, "sme")) {
				Report.logger.pass("Url is displayed as "+executor.page.url());

			}else if(!(home.CORPORATE_URL && Assertion.assertURLContains(executor.page, "sme"))) {
				Report.logger.pass("Url is displayed as "+executor.page.url());
			}else {
				Report.logger.fail("Url is displayed as expected as "+executor.page.url());
			}
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract type page", "ampContractTypePageNavigation",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_26336(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26055(data);
	}
	public void BAU_26337(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26055(data);
	}
	public void BAU_26338(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26055(data);
	}
	public void BAU_26339(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26055(data);
	}
	
	public void BAU_28912(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract type page", "ampContractTypePageNavigation",false);

			int count=executor.page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
			if(count>0) {
				common.clickAction(executor.page, Portal_ObjectRepository.ampOETab_ContinueProcedureLink);
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				//Assertion.assertBytextNotContains(executor.page, Portal_ObjectRepository.ampPolicyDetailTimeText, "");
				if(common.getElementProperty(executor.page, Portal_ObjectRepository.ampPolicyDetailTimeText+"/..", "text-align").equals("center")) {
					Report.logger.pass(common.getText(executor.page, Portal_ObjectRepository.ampPolicyDetailTimeText)+" :is center aligned");
				}else {
					Report.logger.fail(common.getText(executor.page, Portal_ObjectRepository.ampPolicyDetailTimeText)+" :is not center aligned");
				}
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract summry page", "ampContractSummaryPageNavigation",true);
			}else {
				Report.logger.fail("No policy number is present for validation");
			}
			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_29530(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_28912(data);
	}
	public void BAU_29531(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_28912(data);
	}
	public void BAU_29532(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_28912(data);
	}
	public void BAU_29533(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_28912(data);
	}
	
	public void BAU_26759(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract type page", "ampContractTypePageNavigation",false);

			int count=executor.page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
			if(count>0) {
				common.clickAction(executor.page, Portal_ObjectRepository.ampOETab_ContinueProcedureLink);
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				//Assertion.assertBytextNotContains(executor.page, Portal_ObjectRepository.ampPolicyDetailTimeText, "");
				if(home.CORPORATE_URL) {
					Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.amp_PolicyDetails_Attention+"/..");
					Report.logger.pass("Attention section is not attached for corporate flow");
					Assertion.assertHasAttributePattern(executor.page, "//a[text()='弁護士費用等補償特約（自動車事故）']", "href", "https://www.axa-direct.co.jp/auto/services/coverages/other/lawyer_expense_auto.html");
					Assertion.assertHasAttributePattern(executor.page, "//a[text()='※ EV充電設備補償特約']", "href", "https://www.axa-direct.co.jp/auto/services/coverages/other/ev_charge.html");
					Assertion.assertHasAttributePattern(executor.page, "//a[text()='臨時代替自動車補償特約']", "href", "https://www.axa-direct.co.jp/auto/services/coverages/other/loaner_car.html");
				}else {
					
					Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.amp_PolicyDetails_Attention+"/..");
					common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.amp_PolicyDetails_Attention+"/..", data.getTestCaseID(),"Attention section is present for Personal flow", "ampAttentionSectionValidation");
					Assertion.assertIsNotAttached(executor.page, "//a[text()='弁護士費用等補償特約（自動車事故）']");
					Assertion.assertIsNotAttached(executor.page, "//a[text()='臨時代替自動車補償特約']");
					Assertion.assertHasAttributePattern(executor.page, "//a[text()='※ EV充電設備補償特約']", "href", "https://www.axa-direct.co.jp/auto/services/coverages/other/ev_charge.html");
				}
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract summry page", "ampContractSummaryPageNavigation",true);
			}else {
				Report.logger.fail("No policy number is present for validation");
			}
			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_27730(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26759(data);
	}
	public void BAU_27738(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26759(data);
	}
	public void BAU_27740(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26759(data);
	}
	public void BAU_27744(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26759(data);
	}
	
	public void BAU_25024(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract type page", "ampContractTypePageNavigation",false);

			int count=executor.page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
			if(count>0) {
				common.clickAction(executor.page, Portal_ObjectRepository.ampOETab_ContinueProcedureLink);
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				//Assertion.assertBytextNotContains(executor.page, Portal_ObjectRepository.ampPolicyDetailTimeText, "");
				if(home.CORPORATE_URL) {
					Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.amp_PolicyDetail_ChooseMenuText);
					Report.logger.pass("Choose Menu text for Policy details page is not displayed for corporate flow");
					Assertion.assertBytextIsNotEqual(executor.page, Portal_ObjectRepository.amp_PolicyDetail_TopSectionDescription+"[1]", "他のお見積りやご契約で運転者情報（免許証の色や生年月日等）を更新された場合、自動的に反映している場合があります。再度ご確認ください。");
					Assertion.assertBytextIsNotEqual(executor.page, Portal_ObjectRepository.amp_PolicyDetail_TopSectionDescription+"[2]", "他のお見積りやご契約で運転者情報（免許証の色や生年月日等）を更新された場合、自動的に反映している場合があります。再度ご確認ください。");
					
					Report.logger.pass("'他のお見積りやご契約で運転者情報（免許証の色や生年月日等）を更新された場合、自動的に反映している場合があります。再度ご確認ください。\r\n"
							+ "' for Policy details page is not displayed for corporate flow");
					Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.amp_PolicyDetailsButton);
					Report.logger.pass("Policy details button section of Policy details page is not displayed for corporate flow");
					
					Assertion.assertBytext(executor.page, Portal_ObjectRepository.amp_PolicyDetail_TopSectionDescription+"[1]", "電話番号・メールアドレスは");
					Assertion.assertBytext(executor.page, Portal_ObjectRepository.amp_PolicyDetail_TopSectionDescription+"[2]", "ご契約内容の変更や解約手続きご希望の場合はカスタマーサービスセンターまでご連絡ください。");
					
				}else {
					
					Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.amp_PolicyDetail_ChooseMenuText);
					Report.logger.pass("Choose Menu text for Policy details page is displayed for Personal flow");
					Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.amp_PolicyDetail_TopSectionDescription);
					Report.logger.pass("'他のお見積りやご契約で運転者情報（免許証の色や生年月日等）を更新された場合、自動的に反映している場合があります。再度ご確認ください。\r\n"
							+ "' for Policy details page is displayed for Personal flow");
					Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.amp_PolicyDetailsButton);
					Report.logger.pass("Policy details button section of Policy details page is displayed for Personal flow");
					
					Assertion.assertBytextIsNotEqual(executor.page, Portal_ObjectRepository.amp_PolicyDetail_TopSectionDescription, "電話番号・メールアドレスは");
					Assertion.assertBytextIsNotEqual(executor.page, Portal_ObjectRepository.amp_PolicyDetail_TopSectionDescription, "ご契約内容の変更や解約手続きご希望の場合はカスタマーサービスセンターまでご連絡ください。");
				}
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract summry page", "ampContractSummaryPageNavigation",true);
			}else {
				Report.logger.fail("No policy number is present for validation");
			}
			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_27385(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25024(data);
	}
	public void BAU_27386(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25024(data);
	}
	public void BAU_27387(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25024(data);
	}
	public void BAU_27389(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25024(data);
	}
	
	public void BAU_25028(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract type page", "ampContractTypePageNavigation",false);

			int count=executor.page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
			if(count>0) {
				common.clickAction(executor.page, Portal_ObjectRepository.ampOETab_ContinueProcedureLink);
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				Assertion.assertIsAttached(executor.page, "//span[text()='保険の区分']/../following-sibling::div");
				
				if(home.CORPORATE_URL) {
					
					Assertion.assertBytext(executor.page, "//span[text()='保険の区分']/../following-sibling::div", "法人​");
					
				}else {
					
					Assertion.assertBytext(executor.page, "//span[text()='保険の区分']/../following-sibling::div", "個人​");
				}
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract summry page", "ampContractSummaryPageNavigation",true);
			}else {
				Report.logger.fail("No policy number is present for validation");
			}
			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_27681(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25028(data);
	}
	public void BAU_27682(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25028(data);
	}
	public void BAU_27683(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25028(data);
	}
	public void BAU_27685(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25028(data);
	}
	
	public void BAU_25413(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract type page", "ampContractTypePageNavigation",false);

			String section="//span[text()='ご契約者（ご本人）について ']/../../..";
			
			int count=executor.page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
			if(count>0) {
				common.clickAction(executor.page, Portal_ObjectRepository.ampOETab_ContinueProcedureLink);
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				Assertion.assertIsAttached(executor.page, section);
				
				if(home.CORPORATE_URL) {
					
					Assertion.assertIsAttached(executor.page, section+"//span[text()='ご契約者★']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='ご契約者（カナ）']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='契約手続きを行う方の氏名']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='契約手続きを行う方の氏名（カナ）']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='ご住所の郵便番号']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='ご住所★']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='代表電話番号']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='メールアドレス']");
					common.takeScreenShotsOfComponent(executor.page, section, data.getTestCaseID(), "ご契約者（ご本人）について  section field validation is completed for corporate flow", "ampContractorDetailsValidation");
					
				}else {
					
					Assertion.assertIsAttached(executor.page, section+"//span[text()='お名前 ★']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='お名前（カナ）']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='ご住所の郵便番号']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='ご住所★']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='携帯電話番号']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='メールアドレス']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='生年月日']");
					common.takeScreenShotsOfComponent(executor.page, section, data.getTestCaseID(), "ご契約者（ご本人）について  section field validation is completed for Personal flow", "ampContractorDetailsValidation");
				}
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract summry page", "ampContractSummaryPageNavigation",true);
			}else {
				Report.logger.fail("No policy number is present for validation");
			}
			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_27610(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25413(data);
	}
	public void BAU_27611(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25413(data);
	}
	public void BAU_27612(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25413(data);
	}
	public void BAU_27613(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_25413(data);
	}
	
	public void BAU_26724(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract type page", "ampContractTypePageNavigation",false);

			String section="//span[text()='契約車両について']/../../..";
			
			int count=executor.page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
			if(count>0) {
				common.clickAction(executor.page, Portal_ObjectRepository.ampOETab_ContinueProcedureLink);
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				Assertion.assertIsAttached(executor.page, section);
				
				if(home.CORPORATE_URL) {
					
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='乳幼児童同乗（お子さまを乗せてのお車の使用）★']");
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='最若年のお子さまの年齢']");
					Report.logger.pass("'乳幼児童同乗（お子さまを乗せてのお車の使用）★' and '最若年のお子さまの年齢' section is not present for corporate flow");
					
					Assertion.assertIsAttached(executor.page, section+"//span[text()='お車の車検証上の所有者★']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='お車の車検証上の使用者★']");
					
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='お車の所有者のお名前★']");
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='お車の所有者のお名前（カナ）']");
					
					common.takeScreenShotsOfComponent(executor.page, section, data.getTestCaseID(), "契約車両について  section field validation is completed for corporate flow", "ampContractorVehicleValidation");
					
				}else {
					
					Assertion.assertIsAttached(executor.page, section+"//span[text()='乳幼児童同乗（お子さまを乗せてのお車の使用）★']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='最若年のお子さまの年齢']");				
					Assertion.assertIsAttached(executor.page, section+"//span[text()='お車の車検証上の所有者★']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='お車の所有者のお名前★']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='お車の所有者のお名前（カナ）']");			
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='お車の車検証上の使用者★']");
		
					common.takeScreenShotsOfComponent(executor.page, section, data.getTestCaseID(), "契約車両について  section field validation is completed for Personal flow", "ampContractorVehicleValidation");
				}
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract summry page", "ampContractSummaryPageNavigation",true);
			}else {
				Report.logger.fail("No policy number is present for validation");
			}
			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_27615(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26724(data);
	}
	public void BAU_27619(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26724(data);
	}
	public void BAU_27620(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26724(data);
	}
	public void BAU_27621(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26724(data);
	}
	
	public void BAU_26758(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract type page", "ampContractTypePageNavigation",false);
			String section="//span[text()='ご契約内容について']/../../..";
			
			int count=executor.page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
			if(count>0) {
				common.clickAction(executor.page, Portal_ObjectRepository.ampOETab_ContinueProcedureLink);
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				Assertion.assertIsAttached(executor.page, section);
				
				if(home.CORPORATE_URL) {
					
					Assertion.assertIsNotAttached(executor.page, section+"//a[text()='運転者限定特約']");
					Report.logger.pass("'運転者限定特約' section is not attached for corporate flow");
					Assertion.assertHasCount(executor.page, section+"//span[text()='搭乗中のみ補償特約']", 2);
					
					Assertion.assertEvaluateElementProperty(executor.page, "("+section+"//span[text()='搭乗中のみ補償特約'])[1]/../..", "margin-left", "20px");
					Assertion.assertEvaluateElementProperty(executor.page, "("+section+"//span[text()='搭乗中のみ補償特約'])[2]/../..", "margin-left", "20px");
					common.takeScreenShotsOfComponent(executor.page, section, data.getTestCaseID(), "搭乗中のみ補償特約' section is identnted compare to other items", "ampContractorContentValidation");
					
				}else {
					
					Assertion.assertIsAttached(executor.page, section+"//span[text()='搭乗中のみ補償特約']/../..");
					Assertion.assertEvaluateElementProperty(executor.page, section+"//span[text()='搭乗中のみ補償特約']/../..", "margin-left", "20px");
					common.takeScreenShotsOfComponent(executor.page, section, data.getTestCaseID(), "搭乗中のみ補償特約' section is identnted compare to other items", "ampContractorContentValidation");
				}
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract summry page", "ampContractSummaryPageNavigation",true);
			}else {
				Report.logger.fail("No policy number is present for validation");
			}
			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_27589(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26758(data);
	}
	public void BAU_27595(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26758(data);
	}
	public void BAU_27596(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26758(data);
	}
	public void BAU_27597(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26758(data);
	}
	
	public void BAU_26760(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract type page", "ampContractTypePageNavigation",false);
			String section="//span[text()='主な運転者(記名被保険者)について']/../../..";
			
			int count=executor.page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
			if(count>0) {
				common.clickAction(executor.page, Portal_ObjectRepository.ampOETab_ContinueProcedureLink);
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				Assertion.assertIsAttached(executor.page, section);
				
				if(home.CORPORATE_URL) {
					
					Assertion.assertIsAttached(executor.page, section+"//span[text()='主な運転者']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='お車の保管場所の都道府県★']");
					
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='お名前 ★']");
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='お名前（カナ）']");
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='主な運転者とご契約者との関係★']");
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='性別']");
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='生年月日★']");
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='お住まいの都道府県★']");
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='運転免許証の色★']");
					
					
					common.takeScreenShotsOfComponent(executor.page, section, data.getTestCaseID(), "'主な運転者(記名被保険者)について' section validation for corporate flow", "ampMainDriverSectionValidation");
					
				}else {
					
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='主な運転者']");
					Assertion.assertIsNotAttached(executor.page, section+"//span[text()='お車の保管場所の都道府県★']");
					
					Assertion.assertIsAttached(executor.page, section+"//span[text()='お名前 ★']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='お名前（カナ）']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='主な運転者とご契約者との関係★']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='性別']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='生年月日★']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='お住まいの都道府県★']");
					Assertion.assertIsAttached(executor.page, section+"//span[text()='運転免許証の色★']");
					
					
					common.takeScreenShotsOfComponent(executor.page, section, data.getTestCaseID(), "'主な運転者(記名被保険者)について' section validation for Personal flow", "ampMainDriverSectionValidation");
				}
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract summry page", "ampContractSummaryPageNavigation",true);
			}else {
				Report.logger.fail("No policy number is present for validation");
			}
			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_27748(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26760(data);
	}
	public void BAU_27749(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26760(data);
	}
	public void BAU_27750(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26760(data);
	}
	public void BAU_27751(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26760(data);
	}
	
	public void BAU_26761(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract type page", "ampContractTypePageNavigation",false);
			String section7="//span[text()='各種証明書ダウンロード']/../../..";
			String section8="//span[text()='契約内容証明書の発行']/../../..";
			
			int count=executor.page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
			if(count>0) {
				common.clickAction(executor.page, Portal_ObjectRepository.ampOETab_ContinueProcedureLink);
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				
				if(home.CORPORATE_URL) {
					
					Assertion.assertIsNotAttached(executor.page, section7);
					Assertion.assertIsNotAttached(executor.page, section8);
					
					Report.logger.pass(" '各種証明書ダウンロード' '契約内容証明書の発行' and section is not present for corporate flow");
					
				}else {
					
					Assertion.assertIsAttached(executor.page, section7);
					Assertion.assertIsAttached(executor.page, section8);
					
					
					common.takeScreenShotsOfComponent(executor.page, section7, data.getTestCaseID(), "'各種証明書ダウンロード' section validation for Personal flow", "ampVariousCertificateSectionValidation");
					common.takeScreenShotsOfComponent(executor.page, section8, data.getTestCaseID(), "'契約内容証明書の発行' section validation for Personal flow", "ampIssuenceOFCertificateSectionValidation");
				}
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to Contract summry page", "ampContractSummaryPageNavigation",true);
			}else {
				Report.logger.fail("No policy number is present for validation");
			}
			
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_27664(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26761(data);
	}
	public void BAU_27665(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26761(data);
	}
	public void BAU_27667(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26761(data);
	}
	public void BAU_27668(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26761(data);
	}
	
	public void BAU_26056(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			
			common.clickAction(executor.page, Portal_ObjectRepository.emmaNewProcedureTab);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.amp_PolicyHolderName);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.amp_lastLoginDateAndTime);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully validated Policyholder name and last login time of the NB page", "ampNBPageNavigation",false);

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.amp_PolicyHolderName);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.amp_lastLoginDateAndTime);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully validated Policyholder name and last login time of the OE page", "ampContractTypePageNavigation",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_26971(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26056(data);
	}
	public void BAU_26984(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26056(data);
	}
	public void BAU_26985(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26056(data);
	}
	public void BAU_26986(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_26056(data);
	}
	
	public void BAU_27144(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			
			String[] definedLegalEntity= {"株式会社","合同会社","有限会社","合資会社","合名会社","医療法人","一般財団法人","一般社団法人","宗教法人","社会福祉法人","特定非営利活動法人","弁護士法人","行政書士法人","司法書士法人","税理士法人","社会保険労務士法人","その他"};
			String accountHolderName="";
			common.clickAction(executor.page, Portal_ObjectRepository.emmaNewProcedureTab);
			accountHolderName=common.getText(executor.page, Portal_ObjectRepository.amp_PolicyHolderName);
			common.clickAction(executor.page, Portal_ObjectRepository.amp_threeLineMenuButton);
			if(home.CORPORATE_URL) {
			for(String items:definedLegalEntity) {
				try {
				Assertion.assertBytextNotContains(executor.page, Portal_ObjectRepository.amp_threeLineMenuHeaderName, items);
				}catch(AssertionError error) {
					Report.logger.fail(items+" : Legal entity is present under hamburger menu name of NB page");
				}
			}
			}else {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.amp_threeLineMenuHeaderName, accountHolderName);
			}
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully validated Policyholder name under hamburger menu of NB page", "ampHamburgerMenuNameValidationNBtab",false);
			common.clickAction(executor.page, Portal_ObjectRepository.amp_CloseThreeLineMenuButton);

			common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			common.clickAction(executor.page, Portal_ObjectRepository.amp_threeLineMenuButton);
			if(home.CORPORATE_URL) {
			for(String items:definedLegalEntity) {
				try {
				Assertion.assertBytextNotContains(executor.page, Portal_ObjectRepository.amp_threeLineMenuHeaderName, items);
				}catch(AssertionError error) {
					Report.logger.fail(items+" : Legal entity is present under hamburger menu name of NB page");
				}
			}
			}else {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.amp_threeLineMenuHeaderName, accountHolderName);
			}
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully validated Policyholder name under hamburger menu of OE page", "ampHamburgerMenuNameValidationOEtab",false);
			common.clickAction(executor.page, Portal_ObjectRepository.amp_CloseThreeLineMenuButton);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_27688(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27144(data);
	}
	public void BAU_27689(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27144(data);
	}
	public void BAU_27690(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27144(data);
	}
	public void BAU_27691(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27144(data);
	}
	
	public void BAU_27249(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			
			common.clickAction(executor.page, Portal_ObjectRepository.emmaNewProcedureTab);
			common.clickAction(executor.page, Portal_ObjectRepository.amp_threeLineMenuButton);
			if(home.CORPORATE_URL) {
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.amp_Hamburger_petInsuranceText);
				Assertion.assertIsNotAttached(executor.page, Portal_ObjectRepository.amp_Hamburger_HotelSurgeryText);
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "'ペット保険はこちら' and '「アクサダイレクトの入院手術保険」 Ｗｅｂサービス終了のお知らせ	' text is not displayed for hmaburger menu of Corporate flow", "hamburgerMenuOptionValidation",false);
			
			}else {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.amp_Hamburger_petInsuranceText,"> ペット保険はこちら");
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.amp_Hamburger_HotelSurgeryText,"> 「アクサダイレクトの入院手術保険」 Ｗｅｂサービス終了のお知らせ");
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "'ペット保険はこちら' and '「アクサダイレクトの入院手術保険」 Ｗｅｂサービス終了のお知らせ	' text is not displayed for hmaburger menu of Personal flow", "hamburgerMenuOptionValidation",false);
			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_27649(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27249(data);
	}
	public void BAU_27651(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27249(data);
	}
	public void BAU_27652(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27249(data);
	}
	public void BAU_27655(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27249(data);
	}
	
	public void BAU_27252(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			
			common.clickAction(executor.page, Portal_ObjectRepository.emmaNewProcedureTab);
			common.clickAction(executor.page, Portal_ObjectRepository.amp_threeLineMenuButton);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Hmaburger menu options are enabled", "HmaburgerMenuValidation",false);
			common.clickAction(executor.page, Portal_ObjectRepository.amp_Hamburger_newEstimationButton);
			if(home.CORPORATE_URL) {
				
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				Assertion.assertURLContains(executor.page, "sme");
				Report.logger.pass("ContractType page url is displaying as :"+executor.page.url());
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to contractType page of the Corporate flow", "corporateQ&BpageNavigation",false);
			
			}else {
				
				Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
				Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.bikeInsuranceOption);
				Report.logger.pass("ContractType page url is displaying as :"+executor.page.url());
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully navigated to contractType page of the Personal flow", "corporateQ&BpageNavigation",false);
			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_27659(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27252(data);
	}
	public void BAU_27660(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27252(data);
	}
	public void BAU_27661(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27252(data);
	}
	public void BAU_27662(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27252(data);
	}
	
	public void BAU_27374(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			
			String section="//span[text()='アカウント情報']/../../..";
			common.clickAction(executor.page, Portal_ObjectRepository.emmaNewProcedureTab);
			common.clickAction(executor.page, Portal_ObjectRepository.amp_threeLineMenuButton);
			common.clickAction(executor.page, Portal_ObjectRepository.amp_PolicyHolderName);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.amp_Hamburger_accountLoginDetailsButton);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "アカウント・ログイン情報について is displayed under Hmaburger menu", "accountLoginButtonValidation",false);
			common.clickAction(executor.page, Portal_ObjectRepository.amp_Hamburger_accountLoginDetailsButton);
			Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
			if(home.CORPORATE_URL) {
				
				Assertion.assertHasCount(executor.page, section+"//div[contains(@data-aem,'AccountDetailsTitle')]", 7);
				Assertion.assertIsAttached(executor.page, section+"//div[text()='ご契約者']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='ご契約者（カナ）']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='代表電話番号']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='連絡先電話番号']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='電子メールアドレス']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='郵便番号']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='住所']");
				
				common.takeScreenShotsOfComponent(executor.page, section, data.getTestCaseID(), "アカウント情報 section is displayed of the Corporate flow", "accountInformationSectionValidation");
			
			}else {
				
				Assertion.assertHasCount(executor.page, section+"//div[contains(@data-aem,'AccountDetailsTitle')]", 7);
				Assertion.assertIsAttached(executor.page, section+"//div[text()='お名前']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='お名前（カナ）']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='携帯電話']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='電子メールアドレス']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='郵便番号']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='住所']");
				
				common.takeScreenShotsOfComponent(executor.page, section, data.getTestCaseID(), "アカウント情報 section is displayed of the Personal flow", "accountInformationSectionValidation");
			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_27732(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27374(data);
	}
	public void BAU_27733(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27374(data);
	}
	public void BAU_27734(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27374(data);
	}
	public void BAU_27735(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27374(data);
	}
	
	public void BAU_27383(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		try {
			
			String section="//span[text()='アカウント情報']/../../..";
			common.clickAction(executor.page, Portal_ObjectRepository.emmaNewProcedureTab);
			common.clickAction(executor.page, Portal_ObjectRepository.amp_threeLineMenuButton);
			common.clickAction(executor.page, Portal_ObjectRepository.amp_PolicyHolderName);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.amp_Hamburger_accountLoginDetailsButton);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "アカウント・ログイン情報について is displayed under Hmaburger menu", "accountLoginButtonValidation",false);
			common.clickAction(executor.page, Portal_ObjectRepository.amp_Hamburger_accountLoginDetailsButton);
			Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
			Assertion.assertIsAttached(executor.page, section);
			common.clickAction(executor.page, section+"//p[text()='修正する']");
			
			if(home.CORPORATE_URL) {
				
				Assertion.assertIsAttached(executor.page, section+"//div[text()='ご契約者']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='ご契約者（カナ）']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='代表電話番号']");
				Assertion.assertIsEditable(executor.page, section+"//input[@name='userInformation_cellphone']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='連絡先電話番号']");
				Assertion.assertIsEditable(executor.page, section+"//input[@name='userInformation_homePhone']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='電子メールアドレス']");
				Assertion.assertIsEditable(executor.page, section+"//input[@name='userInformation_emailAddress']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='郵便番号']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='住所']");
				
				common.takeScreenShotsOfComponent(executor.page, section, data.getTestCaseID(), "アカウント情報 section is displayed in editble mode of the Corporate flow", "accountInformationSectionValidation");
			
			}else {
				
				Assertion.assertIsAttached(executor.page, section+"//div[text()='お名前']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='お名前（カナ）']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='携帯電話']");
				Assertion.assertIsEditable(executor.page, section+"//input[@name='userInformation_cellphone']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='自宅の電話番号']");
				Assertion.assertIsEditable(executor.page, section+"//input[@name='userInformation_homePhone']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='電子メールアドレス']");
				Assertion.assertIsEditable(executor.page, section+"//input[@name='userInformation_cellphone']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='郵便番号']");
				Assertion.assertIsAttached(executor.page, section+"//div[text()='住所']");
				
				common.takeScreenShotsOfComponent(executor.page, section, data.getTestCaseID(), "アカウント情報 section is displayed in editable mode of the Personal flow", "accountInformationSectionValidation");
			}
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_27741(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27383(data);
	}
	public void BAU_27743(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27383(data);
	}
	public void BAU_27745(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27383(data);
	}
	public void BAU_27747(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_27383(data);
	}
	
	public void BAU_30052(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		
		try {

			int count=executor.page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
			if(count>=1) {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel, "2台目以降のお見積りを始める");
				common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.ampStartPolicyButtonLabel, data.getTestCaseID(),"Start quote button text is displayed as '2台目以降のお見積りを始める' since "+count+" quotation is present in account", "ampStartQuoteButtonTextValidation");

			}else {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel, "新規のお見積りを始める");
				common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.ampStartPolicyButtonLabel,data.getTestCaseID(), "Start quote button text is displayed as '新規のお見積りを始める' since "+count+" quotation is present in account", "ampStartQuoteButtonTextValidation");
			}
			
			//common.clickAction(executor.page, Portal_ObjectRepository.emmaContractDetailsTab);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);

			common.clickAction(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);
			Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.amp_BadCustomer_ModelPopup);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Model pop-up for bad customer is displayed successfully", "badCutomerPopup",false);
			common.clickAction(executor.page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_30054(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_30052(data);
	}
	
	public void BAU_30055(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaNewProcedureTab);
			Assertion.assertIsAttached(executor.page, "("+Portal_ObjectRepository.ampPolicyListBox+")//button[text()='お見積り・お申込みの再開'");

			common.clickAction(executor.page, "("+Portal_ObjectRepository.ampPolicyListBox+")//button[text()='お見積り・お申込みの再開'");
			Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.amp_BadCustomer_ModelPopup);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Model pop-up for bad customer is displayed successfully", "badCutomerPopup",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_30057(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_30055(data);
	}
	
	public void BAU_30060(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		
		try {

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
			policyHolder.dataEntryOnPolicyHolderInformationPage(executor.page, data.getTestCaseID());
			policyHolder.accountMergePopupValidation(executor.page, data.getTestCaseID(), "No");
			Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
			if(Assertion.assertURLContains(executor.page, "page8")) {
				common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Can not proceed from contractor screen for bad customer saved data", "badCutomerValidation",true);
			}else {
				common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "Successfully procceed to next page which is not expected for bad customer", "Failed_badCutomerValidation",true);
			}
			
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "Can not proceed from contractor screen for bad customer saved data", "badCutomerValidation",true);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
			utility.storeResultData(data.getTestCaseID(), "Fail","Thank you page not displayed");
		}
	}
	
	public void BAU_30189(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_30060(data);
	}
	public void BAU_30190(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_30060(data);
	}
	public void BAU_30191(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_30060(data);
	}
	public void BAU_30193(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_30060(data);
	}
	public void BAU_30195(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_30060(data);
	}
	public void BAU_30196(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_30052(data);
	}
	public void BAU_30203(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_30052(data);
	}
	public void BAU_30204(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_30055(data);
	}
	public void BAU_30211(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_30055(data);
	}
	public void BAU_30212(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_30060(data);
	}
	public void BAU_30214(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_30060(data);
	}
	
	public void BAU_28967(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		
		try {

			int count=executor.page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
			if(count>=1) {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel, "2台目以降のお見積りを始める");
				common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.ampStartPolicyButtonLabel, data.getTestCaseID(),"Start quote button text is displayed as '2台目以降のお見積りを始める' since "+count+" quotation is present in account", "ampStartQuoteButtonTextValidation");

			}else {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel, "新規のお見積りを始める");
				common.takeScreenShotsOfComponent(executor.page,Portal_ObjectRepository.ampStartPolicyButtonLabel,data.getTestCaseID(), "Start quote button text is displayed as '新規のお見積りを始める' since "+count+" quotation is present in account", "ampStartQuoteButtonTextValidation");
			}
			
			common.clickAction(executor.page, Portal_ObjectRepository.emmaNewProcedureTab);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);

			common.clickAction(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);
			Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(), "Corporate");
			home.acceptSwithFlowPopup(executor.page, data.getTestCaseID(), "Corporate");
			Assertion.assertIsDisabled(executor.page, Portal_ObjectRepository.bikeInsuranceOption);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "successfully navigated to corporate flow", "corporateFlowNavigation",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	
	public void BAU_29008(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_28967(data);
	}
	
	public void BAU_29014(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaNewProcedureTab);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);

			common.clickAction(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);
			Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(), "Corporate");
			home.rejectSwithFlowPopup(executor.page, data.getTestCaseID());
			Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.bikeInsuranceOption);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "successfully return to Personal flow", "PersonalFlowNavigation",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	
	public void BAU_29017(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_29014(data);
	}
	
	public void BAU_29033(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaNewProcedureTab);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);

			common.clickAction(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);
			Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(), "Individual");
			home.acceptSwithFlowPopup(executor.page, data.getTestCaseID(), "Individual");
			Assertion.assertIsEnabled(executor.page, Portal_ObjectRepository.bikeInsuranceOption);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "successfully navigated to Personal flow", "personalFlowNavigation",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	
	public void BAU_29034(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_29033(data);
	}
	
	public void BAU_29035(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		
		try {

			common.clickAction(executor.page, Portal_ObjectRepository.emmaNewProcedureTab);
			Assertion.assertIsAttached(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);
			common.clickAction(executor.page, Portal_ObjectRepository.ampStartPolicyButtonLabel);
			Assertion.assertIsHidden(executor.page, Portal_ObjectRepository.loadingPageDialog);
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.validateFlowText(executor.page, data.getTestCaseID());
			home.switchToAnotherFlow(executor.page, data.getTestCaseID(), "Individual");
			home.rejectSwithFlowPopup(executor.page, data.getTestCaseID());
			Assertion.assertIsDisabled(executor.page, Portal_ObjectRepository.bikeInsuranceOption);
			common.passStatusWithScreenshots(executor.page,data.getTestCaseID(), "successfully return to corporate flow", "returnCorporateFlowNavigation",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);//executor.browser.close();
		}
	}
	
	public void BAU_29036(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_29035(data);
	}



	public void BAU_24992(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());

		try {

			claims.startClaim(executor.page, data.getTestCaseID());
			claims.selectPolicyForClaim(executor.page, data.getTestCaseID());
			claims.enterAccidentDetails(executor.page, data.getTestCaseID());
			claims.selectTypeOfAccident(executor.page, data.getTestCaseID());
			claims.selectAccidentInjuredPerson(executor.page, data.getTestCaseID());
			claims.selectAccidentMostExplanation(executor.page, data.getTestCaseID());
			claims.enterAdditionalAccidentInfo(executor.page, data.getTestCaseID());
			Assertion.assertBytext(executor.page, Portal_ObjectRepository.claim_VehiclePage_HeaderText, "事故のとき運転者が乗車（または駐車） していたお車を選んでください。");

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);
		}
	}

	public void BAU_29535(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_24992(data);
	}
	public void BAU_29539(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_24992(data);
	}
	public void BAU_29540(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_24992(data);
	}
	public void BAU_29541(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_24992(data);
	}

	public void BAU_24996(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());

		try {
			
			claims.claimInprogress(executor.page, data.getTestCaseID());
			claims.AccidentProgressReportValidation(executor.page, data.getTestCaseID(), 4);
			if(home.CORPORATE_URL) {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.claim_Inprogress_OtherCompensationTable+"//tr[2]/td[1]", "弁護士費用等補償特約（自動車事故）");
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.claim_Inprogress_OtherCompensationTable+"//tr[2]/td[2]", "ご契約の自動車における被害事故の弁護士への依頼費用および法律相談費用を補償します。");
			}else {
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.claim_Inprogress_OtherCompensationTable+"//tr[2]/td[1]", "弁護士費用等補償特約");
				Assertion.assertBytext(executor.page, Portal_ObjectRepository.claim_Inprogress_OtherCompensationTable+"//tr[2]/td[2]", "被害事故（自動車事故以外も含む）における弁護士への依頼費用および法律相談費用を補償します。");
			}
			

			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);

		}catch(AssertionError e) {
			System.out.println(e);
			common.failStatusWithScreenshots(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep",false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);
		}catch(Exception e) {
			System.out.println(e);
			common.failStatusWithExceptions(executor.page,data.getTestCaseID(), "There is problem in the flow", "FailedStep", e,false);
			//common.stopTracing(executor.context, data.getTestCaseID());
			executor.closeBrowser(executor.browser, executor.context);
		}
	}
	
	public void BAU_28896(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_24996(data);
	}
	public void BAU_28901(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_24996(data);
	}
	public void BAU_28902(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_24996(data);
	}
	public void BAU_28903(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {
		BAU_24996(data);
	}

	public void Claim_0001(ParameterOfHomeAndQuotationPage data) throws IOException, InterruptedException {

		executor.launchBrowser(data.getBrowserView(),data.getTestCaseID());
		url.launchAMPURL(executor.page, data.getTestCaseID(), data.getInsurnaceFlowType());
		common.clickAction(executor.page, Portal_ObjectRepository.claim_InprogressButton);
		System.out.println(common.getText(executor.page, "//div[@data-testid='CardItemBox']//p[4]"));
		//claims.e2eFlowOfClaims(executor.page,data.getTestCaseID());

	}
}


