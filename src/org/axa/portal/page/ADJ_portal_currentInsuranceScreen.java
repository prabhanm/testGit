package org.axa.portal.page;

import java.io.IOException;
import java.text.ParseException;

import org.axa.framework.Assertion;
import org.axa.framework.CommonFunctions;
import org.axa.framework.Portal_ObjectRepository;
import org.axa.portal.validation.ADJ_portal_CommonValidation;

import com.microsoft.playwright.Page;

public class ADJ_portal_currentInsuranceScreen {

	public static String INSURANCE_START_DATE;
	public static boolean CORPORATE_URL=false;
	String otherInsuranceCompnay="";
	CommonFunctions common = new CommonFunctions();
	ADJ_portal_CommonValidation validation=new ADJ_portal_CommonValidation();

	public ParameterOfCurrentInsurancePage CI(String testCaseID) throws IOException {
		ParameterOfCurrentInsurancePage para=utility.currentInsuranceMap.get(testCaseID);
		return para; 
	}

	/**
	 *
	@Method implementation to select current insurance company
	 *
	 **/
	public void selectCurrentInsurance(Page page, String testCaseID) throws IOException {
		if(CI(testCaseID).getCurrentInsurnaceNameOfCI().contains("その他の保険会社")) {
			String[] text=CI(testCaseID).getCurrentInsurnaceNameOfCI().split("/");
			otherInsuranceCompnay=text[1].trim();
			common.clickAction(page,"//span[contains(text(),'" + text[0]+ "')]");
			common.selectDropdownByText(page, Portal_ObjectRepository.currentInsuranceSelectOtherCar,otherInsuranceCompnay);
		}else {
			common.clickAction(page,"//span[contains(text(),'" + CI(testCaseID).getCurrentInsurnaceNameOfCI() + "')]");
		}

	}

	/**
	 *
	@Method implementation to select current insurance policy expiry date
	 *
	 **/
	public void currentInsurancePolicyExpiaryDate(Page page, String testCaseID) throws IOException, ParseException {

		String[] date = CI(testCaseID).getExpiaryDateOfCI().split("-");

		common.selectDropdownByText(page, Portal_ObjectRepository.currentInsurnaceExpiaryYear, date[2]);
		common.selectDropdownByText(page, Portal_ObjectRepository.currentInsurnaceExpiaryMonth, date[1]);
		if(date[0].split("")[0].equals("0")) {
			common.selectDropdownByText(page, Portal_ObjectRepository.currentInsurnaceExpiaryDay, date[0].split("")[1]);
		}else {
			common.selectDropdownByText(page, Portal_ObjectRepository.currentInsurnaceExpiaryDay, date[0]);
		}
		System.out.println("date selected as per the dataSheet: "+date[2]+"-"+date[1]+"-"+date[0]);

		if(common.compareTwoDates(CI(testCaseID).getExpiaryDateOfCI(),CommonFunctions.getCurrentDate("dd-MM-yyyy"))<=0) {
			INSURANCE_START_DATE=(common.getText(page,Portal_ObjectRepository.newInsurnaceStartDate+"//p/span")).replace("「","").replace("午前0時」", "").trim();
			System.out.println("When date box is displayed: "+INSURANCE_START_DATE); 
		}


		/*
		 * if(common.isElementPresent(page,
		 * Portal_ObjectRepository.newInsurnaceStartDate)) {
		 * INSURANCE_START_DATE=(common.getText(page,
		 * Portal_ObjectRepository.newInsurnaceStartDate+"//p/span")).replace("「",
		 * "").replace("午前0時」", "").trim();
		 * System.out.println("When date box is displayed: "+INSURANCE_START_DATE); }
		 */

	}

	/**
	 *
	@Method implementation to select check box when current insurance company is unknown
	 *
	 **/
	public void selectUnkownCurrentInsuranceCheckbox(Page page) {
		common.clickAction(page, Portal_ObjectRepository.unknownCurrentInsuranceCheckbox);
	}

	/**
	 *
	@Method implementation to select check box when current insurance policy expire date is unknown
	 *
	 **/
	public void selectUnknownPolicyExpiaryCheckbox(Page page) {
		common.clickAction(page, Portal_ObjectRepository.unknownPolicyExpiaryCheckbox);
	}

	/**
	 *
	@Method implementation to select check box when non fleet grade type is unknown
	 *
	 **/
	public void selectUnknownGradeCheckbox(Page page) {
		common.clickAction(page, Portal_ObjectRepository.unknownGradeCheckbox);
	}

	/**
	 *
	@Method implementation to select check box when accident coefficient is unknown
	 *
	 **/
	public void selectUnknownAccidentCheckbox(Page page) {
		common.clickAction(page, Portal_ObjectRepository.unknownAccidentCheckbox);
	}

	/**
	 *
	@Method implementation to select Car insurance questionnaires
	 *This is only applicable for Car insurance of Individual purchase type.
	 *
	 **/
	public void carInsuranceQuestionary(Page page, String carInsurnaceConfirmation) {
		common.clickAction(page,"//div[@data-aem-id='qb_PreviousPolicy_Main_DivButtonBox']//li[text()='"+ carInsurnaceConfirmation + "']");
	}

	/**
	 *
	@Method implementation to select Car insurance contract type
	 *
	 **/
	public void currentInsuranceContractType(Page page, String testCaseID) throws IOException, ParseException {
		if (CI(testCaseID).getContractualConditionOfCI().equals("いいえ")) {
			common.clickAction(page, "//p[text()='現在のご契約は1年契約ですか？']//..//li[text()='"
					+ CI(testCaseID).getContractualConditionOfCI() + "']");
			common.selectDropdownByText(page, Portal_ObjectRepository.currentInsuranceContactPeriod,
					CI(testCaseID).getContractPeriodOfCI());
		}

		if (CI(testCaseID).getExpairyDateChekboxOfCI().equals("はい")) {
			selectUnknownPolicyExpiaryCheckbox(page);
		} else {
			currentInsurancePolicyExpiaryDate(page, testCaseID);

		}

	}

	/**
	 *
	@Method implementation to select not fleet grade type
	 *This is method is also handling when grade type is unknown.
	 *
	 **/
	public void nonFleetGradeSelection(Page page, String testCaseID) throws IOException {

		if (CI(testCaseID).getGradeTypeCheckboxOfCI().equals("はい")) {
			selectUnknownGradeCheckbox(page);
			common.selectDropdownByText(page, Portal_ObjectRepository.currentInsurnaceGradeYear,
					CI(testCaseID).getGradeYearOfCI());
		} else {
			common.selectDropdownByText(page, Portal_ObjectRepository.currentInsurnaceGradeSelection,
					CI(testCaseID).getGradeTypeOfCI());
		}

	}

	/**
	 *
	@Method implementation to select accident coefficient
	 *This is method is also handling when accident coefficient is unknown.
	 *
	 **/
	public void accidentCofficientSelection(Page page, String testCaseID) throws IOException {

		if (CI(testCaseID).getAccidentCofficientCheckboxOfCI().equals("はい")) {
			selectUnknownAccidentCheckbox(page);
		} else {
			common.selectDropdownByText(page, Portal_ObjectRepository.currentInsuranceAccidentCoficient,
					CI(testCaseID).getAccidentCofficientOfCI());
		}

	}

	/**
	 *
	@Method implementation to select number of accident
	 *
	 *
	 **/
	public void numberOfAccidentCaseSelection(Page page, String testCaseID) throws IOException {

		if (CI(testCaseID).getAccidentCaseNumberOfCI().equals("１件")) {
			common.clickAction(page,
					"//li[text()='" + CI(testCaseID).getAccidentCaseNumberOfCI() + "']");
			common.clickAction(page, "//li[text()='" + CI(testCaseID).getAccidentTypeOfCI() + "']");

		} else if (CI(testCaseID).getAccidentCaseNumberOfCI().equals("２件以上")) {
			common.clickAction(page,
					"//li[text()='" + CI(testCaseID).getAccidentCaseNumberOfCI() + "']");
			common.clickAction(page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
		} else {
			common.clickAction(page,
					"//li[text()='" + CI(testCaseID).getAccidentCaseNumberOfCI() + "']");
		}
	}

	/**
	 *
	@Method implementation to select Car insurance questionnaires
	 *This is only applicable for Car insurance of Individual purchase type.
	 *
	 **/
	public void carInsuranceQuestionariesOfCI(Page page, String testCaseID, String productType) throws IOException {
		if (productType.equals("自動車保険（四輪）")) {
			common.clickAction(page,
					"//div[@data-aem-id='qb_PreviousPolicy_Main_HeaderDiv06']//li[text()='"+ CI(testCaseID).getCarInsurnaceQuestionaryOfCI() + "']");
		}

	}

	/**
	 *
	@Method implementation to handle grade upgrade pop-up
	 *This pop-up will appear while proceeding to next page.
	 *
	 **/
	public void upgreadPopupHandellingOfCI(Page page, String testCaseID) throws IOException {

		page.locator(Portal_ObjectRepository.confrimationOfdialogCI).isEnabled();
		common.passStatusWithScreenshots(page,testCaseID, "Grade upgrade pop-up displayed successfully ","gradeUpgradePop-up",false);
		common.clickAction(page, Portal_ObjectRepository.confrimationOfdialogCI);


	}

	/**
	 *
	@Method implementation to validate menu option applicable for the current insurance policy page
	 *validation is happening for both enabled and grade out options
	 *
	 **/
	public void menuOptionValidationOfCI(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.threeLineMenuOptionNavigation(page, testCaseID);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuBeforeQuote);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuCurrentInsurnace);
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.menuAboutCarAndBike+"//img", "src", "icon-tire-gray");
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.menuMainDriver+"//img", "src", "icon-people-gray");
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.menuQuotation+"//img", "src", "icon-check-gray");
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuSaveContent);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuContactCenter);
			common.passStatusWithScreenshots(page, testCaseID, "Menu options of Previous Policy page is validated successfully" , "MenuOptionsOfPreviousPolicyPage", true);
			common.clickAction(page, Portal_ObjectRepository.closeMenuButton);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Menu options of Previous Policy page is not validated completely", "Failed_MenuOptionsOfPreviousPolicyPage", true);
		}
	}

	/**
	 *
	@Method implementation to validate error message for all mandatory field
	 *
	 *
	 **/
	public void errorMessageValidationOfCurrentInsurancePage(Page page, String testCaseID,String errorMessage) throws IOException {
		try {
			common.clickAction(page, Portal_ObjectRepository.proceedNext);
			Assertion.assertBytext(page, Portal_ObjectRepository.previousPolicyselectionError,errorMessage);
			Assertion.assertBytext(page, Portal_ObjectRepository.previousPolicyExpiaryDateError,errorMessage);
			Assertion.assertBytext(page, Portal_ObjectRepository.previousPolicyGradeSelectionError,errorMessage);
			Assertion.assertBytext(page, Portal_ObjectRepository.previousPolicyAccidentCases,errorMessage);
			if (utility.homePageMap.get(testCaseID).getInsurnaceProductType().equals("自動車保険（四輪）")) {
				Assertion.assertBytext(page, Portal_ObjectRepository.priviousPolicyVehicleInsuranceError,errorMessage);
			}
			common.passStatusWithScreenshots(page, testCaseID, "Error for all Mandetory field of Previous Policy Page is displaying as:'"+errorMessage+"'", "PreviousPolicyPageFieldErrorValidation", true);
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseID, "Expected Error Message:'"+errorMessage+"' of one or all field of Previous Policy page is different", "Failed_PreviousPolicyPageFieldErrorValidation", true);
		}
	}

	/**
	 *
	@Method implementation to for functional (UI) validation of this page
	 *we are validating logo, quotation number, ChatBot, menu options.
	 *
	 **/
	public void functionalValidationOfCI(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.PageLogoValidation(page, testCaseID);
			validation.quotationValidation(page, testCaseID);
			validation.chatBotValidation(page, testCaseID);
			menuOptionValidationOfCI(page, testCaseID);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Functional Validation of Previous Policy page is not completed", "Failed_PreviousPolicyFunctionalValidation", true);
		}


	}

	/**
	 *
	@Method implementation of E2E flow for the current insurance policy page
	 *This method will take action based on the data Input sheet
	 *
	 **/
	public void e2eFlowOfCurrentInsurnacePage(Page page, String testCaseID, String productType) throws IOException, ParseException {

		//CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		if (CI(testCaseID).getUnkownCompanyCheckBoxOfCI().equals("はい")) {
			selectUnkownCurrentInsuranceCheckbox(page);
		} else {
			selectCurrentInsurance(page, testCaseID);
		}
		currentInsuranceContractType(page, testCaseID);
		nonFleetGradeSelection(page, testCaseID);
		accidentCofficientSelection(page, testCaseID);
		numberOfAccidentCaseSelection(page, testCaseID);
		carInsuranceQuestionariesOfCI(page, testCaseID, productType);
		common.passStatusWithScreenshots(page,testCaseID, "User entered data in current insurance page successfully ","currentInsuranceData",true);
		common.clickAction(page, Portal_ObjectRepository.proceedNext);
		upgreadPopupHandellingOfCI(page, testCaseID);
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		if((otherInsuranceCompnay!=null && (otherInsuranceCompnay.equals("教職員共済") || otherInsuranceCompnay.equals("日火連（旧中小企業共済）") || otherInsuranceCompnay.equals("その他")))){
			System.out.println(common.getText(page, "("+Portal_ObjectRepository.previousPolicyValidationPopup+"//span)[1]"));
			Assertion.assertBytext(page, "("+Portal_ObjectRepository.previousPolicyValidationPopup+"//span)[1]", "選択いただいたケースの場合は、インターネットでお申込みいただけません。カスタマーサービスセンターまでお電話下さい。");
			try {
			if(CORPORATE_URL) {
				Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.previousPolicyValidationPopup+"//a", "href", "tel:0120945072");
			}else {
				Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.previousPolicyValidationPopup+"//a", "href", "tel:0120000194");
			}
			common.passStatusWithScreenshots(page,testCaseID, "Previous Policy confirmation pop-up displayed successfully ","PreviousPolicyModelConfirmation",false);
			common.clickAction(page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
			}catch(AssertionError error){
				common.failStatusWithScreenshots(page,testCaseID, "Expected Previous Policy confirmation pop-up is not displayed","Failed_PreviousPolicyModelConfirmation",true);
			}
			
		}
		common.passStatusWithScreenshots(page,testCaseID, "User successfully procreeded to Vehicle page ","ProceededToVehiclePage",true);
		ADJ_portal_quotationScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");
	}

}
