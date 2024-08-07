package org.axa.portal.page;

import java.io.IOException;

import org.axa.framework.Assertion;
import org.axa.framework.CommonFunctions;
import org.axa.framework.Portal_ObjectRepository;
import org.axa.framework.Report;
import org.axa.portal.validation.ADJ_portal_CommonValidation;

import com.microsoft.playwright.Page;

public class ADJ_portal_quotationScreen {

	utility util = new utility();
	CommonFunctions common = new CommonFunctions();
	ADJ_portal_CommonValidation validation=new ADJ_portal_CommonValidation();
	public static String CAR_MODEL_NUMBER_AFTER_SELECTION;
	public static String CAR_MANUFACTURERE_AFTER_SELECTION;
	public static String CAR_NAME_AFTER_SELECTION;
	public static boolean CORPORATE_URL=false;

	public ParameterOfHomeAndQuotationPage quotation(String testCaseID) throws IOException {
		ParameterOfHomeAndQuotationPage para=utility.quotationPageMap.get(testCaseID);
		return para; 
	}

	/**
	 *
	@Method implementation for Car Registration period selection:
	 *
	 **/
	public void selectCarRegistrationPeriod(Page page,String testCaseId) throws IOException{
		String[] registration=quotation(testCaseId).getCarRegistrationPeriod().split("-");
		common.selectDropdownByValue(page, Portal_ObjectRepository.carRegistrationYear,registration[0] );
		common.selectDropdownByText(page, Portal_ObjectRepository.carRegistrationMonth,registration[1] );
	}

	/**
	 *
	@Method implementation for unknown Car Registration period Check-box:
	 *
	 **/
	public void selectUnkownCarRegistrationCheckbox(Page page,String testCaseId) {
		common.clickAction(page, Portal_ObjectRepository.unkownCarRegistrationCheckbox);
	}

	/**
	 *
	@Method implementation for Temp Car Registration period selection:
	 *this is applicable when "unknown Car Registration period Check-box" is selected
	 *
	 **/
	public void selectTempCarRegistrationYear(Page page,String testCaseId) throws IOException {
		common.selectDropdownByText(page, Portal_ObjectRepository.carTempRegistrationYear,quotation(testCaseId).getTempCarRegistrationYear());
	}

	/**
	 *
	@Method implementation for Bike displacement selection:
	 *
	 **/
	public void selectBikeDisplacement(Page page,String testCaseId) throws IOException {
		common.clickAction(page,"//label[contains(text(),'"+quotation(testCaseId).getBikeDisplacement()+"')]");
	}

	/**
	 *
	@Method implementation for 3 wheel Bike confirmation:
	 *
	 **/
	public void threeWheelBikeconfirmation(Page page,String testCaseId) throws IOException {

		if(quotation(testCaseId).getBikeDisplacement().equals("51cc以上125cc以下") || quotation(testCaseId).getBikeDisplacement().equals("0.61kW以上1.00kW以下")) {
			if(quotation(testCaseId).getThreeWheeVechileConfirmation().equals("はい")) {
				common.clickAction(page,Portal_ObjectRepository.bikeThreeWheelConfirmation);
				common.passStatusWithScreenshots(page, testCaseId,"Three wheel vehicle confirmation pop-up is displayed successfully","ThreeWheelPop-up",false);
				common.clickAction(page,Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
			}else {
				common.clickAction(page,Portal_ObjectRepository.bikeThreeWheelDeny);
			}
		}
	}

	/**
	 *
	@Method implementation for Car model number search:
	 *
	 **/
	public void searchCarByModelNumber(Page page,String testCaseId) throws IOException {
		common.enterText(page, Portal_ObjectRepository.enterCarModelNumber, quotation(testCaseId).getCar_BikeModel());
		common.clickAction(page,Portal_ObjectRepository.searchCarModelNumber);

	}

	/**
	 *
	@Method implementation to select Check-box for Car Manufacturer:
	 *This is required if you want to search car from manufacturer list.
	 **/
	public void searchCarByManufacturerCheckbox(Page page,String testCaseId) {
		common.clickAction(page, Portal_ObjectRepository.searchCarByManufacturerCheckbox);
	}

	/**
	 *
	@Method implementation to select Car manufacturer name:
	 *
	 **/
	public void searchCarByManufacturer(Page page,String testCaseId) throws IOException {

		common.clickAction(page, "//*[text()='"+quotation(testCaseId).getManufacturerCompanay()+"']");
		common.selectDropdownByText(page, Portal_ObjectRepository.selectCarName,quotation(testCaseId).getCarName());
		if(common.isElementPresent(page, Portal_ObjectRepository.selectCarType)) {
			common.selectDropdownByText(page, Portal_ObjectRepository.selectCarType,quotation(testCaseId).getCar_BikeModel());
		}else {
		common.clickAction(page, "//span[text()='"+quotation(testCaseId).getCar_BikeModel()+"']");
		}
	}

	/**
	 *
	@Method implementation to select Bike manufacturer name:
	 *
	 **/
	public void selectBikeManufacturerdeatils(Page page,String testCaseId,String productType) throws IOException {
		String manufacturer="";
		System.out.println("before companay: "+quotation(testCaseId).getManufacturerCompanay());

		if(productType.equals("バイク保険（二輪）")) {
			manufacturer=common.toHalfWidth(quotation(testCaseId).getManufacturerCompanay());
			System.out.println("after conversion: "+manufacturer);
		}else {
			manufacturer=quotation(testCaseId).getManufacturerCompanay();
		}
		common.clickAction(page, "//span[text()='"+manufacturer+"']");
		common.enterText(page, Portal_ObjectRepository.enterBikeModelNumber, quotation(testCaseId).getCar_BikeModel());
	}


	/**
	 *
	@Method implementation to select Car Mileage:
	 *
	 **/
	public void selectCarMileage(Page page,String testCaseId) throws IOException {
		common.selectDropdownByText(page, Portal_ObjectRepository.carMileage,quotation(testCaseId).getCarBikeMileage());  
	}

	/**
	 *
 	@Method implementation to select Bike Mileage:
	 *
	 **/
	public void selectBikeMileage(Page page,String testCaseId) throws IOException {
		common.selectDropdownByText(page, Portal_ObjectRepository.bikeMileage,quotation(testCaseId).getCarBikeMileage());  
	}

	/**
	 *
 	@Method implementation to Vehicle purpose:
	 *
	 **/
	public void selectVechilePurposeType(Page page,String testCaseId,String productType) throws IOException {
		common.clickAction(page, "//img[@alt='"+quotation(testCaseId).getVechilePurpose()+"']");
		if(productType.equals("バイク保険（二輪）") && quotation(testCaseId).getVechilePurpose().equals("業務用")) {
			try {
				Assertion.assertBytext(page, "//div[@data-aem='undefined_ConfirmModalBoxTitle']", "ご確認ください");
				common.passStatusWithScreenshots(page, testCaseId, "pop-up about ご契約のお車が「原動機付自転車」で使用目的が「業務用」の場合は、お申込みいただけません。" , "Bike業務用Pop-up", false);
				common.clickAction(page, "//button[text()='閉じる']");
				//page.close();
			}catch(AssertionError error) {
				common.failStatusWithScreenshots(page, testCaseId, "expected pop-up about ご契約のお車が「原動機付自転車」で使用目的が「業務用」の場合は、お申込みいただけません。" , "Failed_Bike業務用Pop-up", false);
			}
		}
	}
	
	/**
	 *
	@Method implementation to select Children questionnaires for Car :
	 *
	 **/
	public void QuestionariesvalidationOfSME(Page page,String testCaseId) throws IOException {
		try {
		if(CORPORATE_URL) {
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.carUsingWithChildrenSection);
			Report.logger.pass("question field asking child care discount is applicable is hidden for Corporate flow");
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.otherCarInsurancePolicySection);
			Report.logger.pass("question field about other car insurance is hidden for Corporate flow");
		}else {
			Assertion.assertIsAttached(page, Portal_ObjectRepository.carUsingWithChildrenSection);
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.carUsingWithChildrenSection, testCaseId, "question field asking child care discount is applicable should NOT be hidden for Personal Auto Policies", "carUsingWithChildrenSectionValidation");
			Assertion.assertIsAttached(page, Portal_ObjectRepository.otherCarInsurancePolicySection);
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.otherCarInsurancePolicySection, testCaseId, "question field about other car insurance should NOT be hidden for Personal Auto Policies", "otherCarInsurancePolicySectionValidation");
		}
			}catch(AssertionError error) {
				
			}
		}
	

	/**
	 *
 	@Method implementation to select Children questionnaires for Car :
	 *
	 **/
	public void carWithChilderenQuestionaries(Page page,String testCaseId,String insuranceFlowType) throws IOException {
		if(CORPORATE_URL) {
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.carUsingWithChildrenSection);
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.otherCarInsurancePolicySection);
		}else {
			//Assertion.assertIsEnabled(page, Portal_ObjectRepository.carUsingWithChildrenSection);
			//Assertion.assertIsEnabled(page, Portal_ObjectRepository.otherCarInsurancePolicySection);
			if(quotation(testCaseId).getCarUsingwithChildren().equals("はい")) {

				common.clickAction(page, Portal_ObjectRepository.carUsingWithChildConfirmation);
				if(quotation(testCaseId).getUnkownChildrenAgeCheckbox().equals("はい")) {
					common.clickAction(page, Portal_ObjectRepository.unlownChildAgeCheckbox);	   
				}else {
					common.selectDropdownByText(page, Portal_ObjectRepository.selectChilderenAge,quotation(testCaseId).getChildrenAge());
				}

			}else {
				common.clickAction(page, Portal_ObjectRepository.carUsingWithChildDeny);
			}  
		}
	}

	/**
	 *
 	@Method implementation to validated mandatory field error message for Vehicle page:
	 *
	 **/
	public void ErrorMessageValidationOfQuotationPage(Page page,String testCaseId,String ErrorMessage) throws IOException {

		common.clickAction(page,Portal_ObjectRepository.proceedNext);
		String productType=utility.homePageMap.get(testCaseId).getInsurnaceProductType();
		try {
			if(productType.equals("自動車保険（四輪）")) {
				Assertion.assertBytext(page, Portal_ObjectRepository.quotationCarRegistrationPeriodFieldError, ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.quotationCarModelFieldError, ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.quotationCarMileageFieldError, ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.quotationCarBikePurposeTypeFieldError, ErrorMessage);
				if(!CORPORATE_URL) {
				Assertion.assertBytext(page, Portal_ObjectRepository.quotationCarUsingWithChildFieldError, ErrorMessage);
				}
			}else if(productType.equals("バイク保険（二輪）")) {
				Assertion.assertBytext(page, Portal_ObjectRepository.quotationBikeEngineFieldError, ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.quotationBikeManufactureFieldError, ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.quotationBikeNameFieldError, ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.quotationBikeMileageFieldError, ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.quotationBikeUsageFieldError, ErrorMessage);

			}
			common.passStatusWithScreenshots(page, testCaseId, "Error for all Mandetory field of Quotation Page is displaying as:'"+ErrorMessage+"'", "QuotationPageFieldErrorValidation", true);
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseId, "Expected Error Message:'"+ErrorMessage+"' of one or all field of Quotation page is different", "Failed_QuotationPageFieldErrorValidation", true);
			System.out.println(error);
		}

	}

	/**
	 *
 	@Method implementation of Menu option validation of Vehicle page.
	 *
	 **/
	public void menuOptionValidationOfVehiclePage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.threeLineMenuOptionNavigation(page, testCaseID);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuBeforeQuote);
			if(utility.homePageMap.get(testCaseID).getInsurancePurchaseType().equals("現在他社で加入している")) {
				Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuCurrentInsurnace);
			}else if (utility.homePageMap.get(testCaseID).getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuSuspendedPolicy);
			}else {
				Assertion.assertIsHidden(page, Portal_ObjectRepository.menuCurrentInsurnace);
				Assertion.assertIsHidden(page, Portal_ObjectRepository.menuSuspendedPolicy);
			}
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuAboutCarAndBike);
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.menuMainDriver+"//img", "src", "icon-people-gray");
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.menuQuotation+"//img", "src", "icon-check-gray");
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuSaveContent);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuContactCenter);
			common.passStatusWithScreenshots(page, testCaseID, "Menu options of Vehicle page is validated successfully" , "MenuOptionValidationVehiclePage", true);
			common.clickAction(page, Portal_ObjectRepository.closeMenuButton);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Menu options of Vehicle page is not validated completely", "Failed_MenuOptionValidationVehiclePage", true);
		}
	}

	/**
	 *
 	@Method implementation of functional validation of Vehicle page:
	 *
	 **/
	public void functionalValidationOfVehiclePage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.PageLogoValidation(page, testCaseID);
			validation.quotationValidation(page, testCaseID);
			validation.chatBotValidation(page, testCaseID);
			menuOptionValidationOfVehiclePage(page, testCaseID);
		}catch(AssertionError error) {
			System.out.println(error);

			common.failStatusWithScreenshots(page, testCaseID, "Functional Validation of Vehicle page is not completed", "Failed_VehiclePageFunctionalValidation", true);
		}
	}
	/**
	 *
	@Method implementation get Car details after selection:
	 *
	 **/
	public void getCarDetailsAfterSelection(Page page) {
		CAR_MODEL_NUMBER_AFTER_SELECTION=common.getAttribute(page, Portal_ObjectRepository.enterCarModelNumber,"value");
		CAR_MANUFACTURERE_AFTER_SELECTION=common.getText(page, Portal_ObjectRepository.selectCarManufacturerAfterSelection);
		CAR_NAME_AFTER_SELECTION=common.getText(page, Portal_ObjectRepository.selectCarNameAfterSelection);
	}

	/**
	 *
	@Method implementation for End2End flow of vehicle page:
	 *
	 **/
	public void e2eFlowOfCarBikeQuotationPage(Page page,String testCaseId,String productType,String insuranceFlowType) throws IOException {
		CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		if(productType.equals("バイク保険（二輪）")) {

			selectBikeDisplacement(page, testCaseId);
			threeWheelBikeconfirmation(page, testCaseId);
			selectBikeManufacturerdeatils(page, testCaseId,productType);
			selectBikeMileage(page, testCaseId);
			selectVechilePurposeType(page, testCaseId,productType);

		}else if (productType.equals("自動車保険（四輪）")) {

			if(quotation(testCaseId).getUnkownCarRegistrationCheckbox().equals("はい")){
				selectUnkownCarRegistrationCheckbox(page, testCaseId);
				selectTempCarRegistrationYear(page, testCaseId);
			}else {
				selectCarRegistrationPeriod(page, testCaseId);
			}
			if(quotation(testCaseId).getSearchCarByManufacturerCheckbox().equals("はい")) {
				searchCarByManufacturerCheckbox(page, testCaseId);
				searchCarByManufacturer(page, testCaseId);

			}else {
				searchCarByModelNumber(page, testCaseId);
			}
			getCarDetailsAfterSelection(page);
			selectCarMileage(page, testCaseId);
			selectVechilePurposeType(page, testCaseId,productType);
			carWithChilderenQuestionaries(page, testCaseId,insuranceFlowType);
		}
		//selectVechilePurposeType(page, testCaseId);
		common.passStatusWithScreenshots(page, testCaseId,"Data has been Entered successfully in the Quotation page","QuotationPage",true);
		common.clickAction(page, Portal_ObjectRepository.proceedNext);
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		common.waitForSelector(page, Portal_ObjectRepository.prefectureSelection);
		common.passStatusWithScreenshots(page, testCaseId,"Successfully proceeded to Driver page","proceededToDriverPage",true);
		ADJ_portal_aboutMainDriverAndPolicyPlanScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");
	}
}
