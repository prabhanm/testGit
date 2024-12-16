package org.axa.portal.page;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.axa.framework.Assertion;
import org.axa.framework.CommonFunctions;
import org.axa.framework.Portal_ObjectRepository;
import org.axa.framework.Report;
import org.axa.portal.validation.ADJ_portal_CommonValidation;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ADJ_portal_suspensionCertificateScreen {
	
	utility util = new utility();
	CommonFunctions common = new CommonFunctions();
	ADJ_portal_CommonValidation validation=new ADJ_portal_CommonValidation();
	public static boolean CORPORATE_URL=false;
	

	public ParameterOfSuspensionCertificatePage SIC(String testCaseID) throws IOException {
		ParameterOfSuspensionCertificatePage para=utility.suspensionCertificateMap.get(testCaseID);
		 return para; 
		}
	
	/**
	*
	@Method implementation to handle Suspended policy pop-up
	*
	**/
	
	public void acceptSuspensionPageNavigationDialog(Page page,String testCaseID) throws IOException {
		common.passStatusWithScreenshots(page,testCaseID, "Suspended Insurance Policy pop-up displayed successfully ","suspendedInsurancePop-up",false);
		common.clickAction(page, Portal_ObjectRepository.suspentionCertificateDialog);

	}
	
	/**
	*
	@Method implementation to select suspended policy reason:
	*
	**/
	public void selectSuspensionReason(Page page, String testCaseID) throws IOException, InterruptedException {

		common.clickAction(page,"//div[contains(text(),'" + SIC(testCaseID).getSuspensionReason() + "')]");
		if(SIC(testCaseID).getSuspensionReason().equals("海外渡航")) {
			overseaseTravelPopupForSIC(page, testCaseID);
		}else {
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.suspendedPolicyOverseasTravelPopup+"/div[1]/span");
			Report.logger.pass("pop-up is not displayed on selecting the reason for issuing interruption certificate other than overseas travel");
		}

	}
	
	/**
	*
	@Method implementation to validate overseas travel pop-up
	*this pop-up will display when suspended reason is "海外渡航"
	*Checking the contact information for both Individual and corporate flow.
	*
	**/
	public void overseaseTravelPopupForSIC(Page page, String testCaseID) throws IOException, InterruptedException {
		   //CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		
		   Assertion.assertBytext(page, Portal_ObjectRepository.suspendedPolicyOverseasTravelPopup+"/div[1]/span", "海外渡航による中断はお電話にて承っております。カスタマーサービスセンターまでご確認ください。");

			if(CORPORATE_URL) {
				Assertion.assertBytext(page,"("+Portal_ObjectRepository.suspendedPolicyOverseasTravelPopup+"/div[2]//span)[1]", "0120-945-072");
				common.passStatusWithScreenshots(page, testCaseID,"Contact Number :0120-945-072 is displayed successfully", "overseaseTravelPopup.png",false);
			}
			else if(!CORPORATE_URL) {
			Assertion.assertBytext(page,"("+Portal_ObjectRepository.suspendedPolicyOverseasTravelPopup+"/div[2]//span)[1]", "0120-000-194");
			common.passStatusWithScreenshots(page, testCaseID,"Contact Number :0120-000-194 is displayed successfully", "overseaseTravelPopup.png",false);
			}
			common.clickAction(page,"//*[text()='閉じる']");
			common.passStatusWithScreenshots(page, testCaseID, "Contact center pop-up closed successfully ","AfrerOverseaseTravelPopupAccept",true);
	}
	
	/**
	*
	@Method implementation to validate start date limit pop-up
	*this pop-up will display when start date of suspended policy is <=20-10-2012.
	*Checking the contact information for both Individual and corporate flow.
	*
	**/
	public void startDateLimitPopupForSIC(Page page, String testCaseID) throws IOException, InterruptedException {
		//CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		System.out.println(common.getText(page, Portal_ObjectRepository.suspendedPolicyStartDateLimitPopup+"/div[1]/span"));
		
		   Assertion.assertBytext(page, Portal_ObjectRepository.suspendedPolicyStartDateLimitPopup+"/div[1]/span","ご入力頂いた補償開始日（前契約）では、インターネット上でお手続きすることが出来ません。カスタマーサービスセンターまでご確認ください。");

			if(CORPORATE_URL) {
				Assertion.assertBytext(page,"("+Portal_ObjectRepository.suspendedPolicyStartDateLimitPopup+"/div[2]//span)[1]", "0120-945-072");
				common.passStatusWithScreenshots(page, testCaseID,"Contact Number :0120-945-072 is displayed successfully", "startDateLimitPopup.png",false);
			}
			else if(!CORPORATE_URL) {
			Assertion.assertBytext(page,"("+Portal_ObjectRepository.suspendedPolicyStartDateLimitPopup+"/div[2]//span)[1]", "0120-000-194");
			common.passStatusWithScreenshots(page, testCaseID,"Contact Number :0120-000-194 is displayed successfully", "startDateLimitPopup.png",false);
			}
			common.clickAction(page,"//*[text()='閉じる']");
			common.passStatusWithScreenshots(page, testCaseID, "Contact center pop-up closed successfully ","AfrerStartDateLimitPopupAccept",true);
	}
	
	/**
	*
	@Method implementation to validate acquisition date limit pop-up
	*this pop-up will display when acquisition date of suspended policy is >IDE.
	*Checking the contact information for both Individual and corporate flow.
	*
	**/
	public void acquisitionDateLimitPopupForSIC(Page page, String testCaseID) throws IOException, InterruptedException {
		
		  Assertion.assertBytext(page, Portal_ObjectRepository.suspendedPolicyAcquisitionDateLimitPopup+"/div[1]/span","車両取得日が補償の開始日より後の日付のため、インターネットではお手続きすることが出来ません");

			if(CORPORATE_URL) {
				Assertion.assertBytext(page,"("+Portal_ObjectRepository.suspendedPolicyAcquisitionDateLimitPopup+"/div[2]//span)[1]", "0120-945-072");
				common.passStatusWithScreenshots(page, testCaseID,"Contact Number :0120-945-072 is displayed successfully", "acquisitionDateLimitPopup.png",false);
			}
			else if(!CORPORATE_URL) {
			Assertion.assertBytext(page,"("+Portal_ObjectRepository.suspendedPolicyAcquisitionDateLimitPopup+"/div[2]//span)[1]", "0120-000-194");
			common.passStatusWithScreenshots(page, testCaseID,"Contact Number :0120-000-194 is displayed successfully", "acquisitionDateLimitPopup.png",false);
			}
			common.clickAction(page,"//*[text()='閉じる']");
			common.passStatusWithScreenshots(page, testCaseID, "Contact center pop-up closed successfully ","AfrerAcquisitionDateLimitPopupAccept",true);
	}

	/**
	*
	@throws InterruptedException 
	 * @Method implementation to select suspended Insurance company 
	*.
	**/
	public void selectInsuranceSuspendedCompany(Page page, String testCaseID) throws IOException, InterruptedException {

		common.clickAction(page,"//span[contains(text(),'" + SIC(testCaseID).getCompanyNameOfSIC() + "')]");
		if(SIC(testCaseID).getCompanyNameOfSIC().equals("海外渡航")) {
		overseaseTravelPopupForSIC(page, testCaseID);
		}

	}

	/**
	*
	@Method implementation to select suspension certificate start date 
	*Validating pop-up when start date of suspended policy is <=20-10-2012.
	*
	**/
	public void suspensionCertificateStartDate(Page page, String testCaseID) throws IOException, InterruptedException, ParseException {
		String[] date = SIC(testCaseID).getStartdateOfSIC().split("-");
		
		common.selectDropdownByText(page, Portal_ObjectRepository.suspensionCertificateStartYear, date[2]);
		common.selectDropdownByText(page, Portal_ObjectRepository.suspensionCertificateStartMonth, date[1]);
		common.selectDropdownByText(page, Portal_ObjectRepository.suspensionCertificateStartDay, date[0]);

		if(common.compareTwoDates(SIC(testCaseID).getStartdateOfSIC(),"20-10-2012")<=0) {
			startDateLimitPopupForSIC(page, testCaseID);
		}else {
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.suspendedPolicyStartDateLimitPopup+"/div[1]/span");
			Report.logger.pass("Verify if pop-up is not displayed on selecting the start date of the previous contract after Oct 20, 2012.");
		}
     }
	
	/**
	*
	@Method implementation to select suspension certificate End date 
	*
	**/
	public void suspensionCertificateEndDate(Page page, String testCaseID) throws IOException {

		String[] date = SIC(testCaseID).getEndDateOfSIC().split("-");

		common.selectDropdownByText(page, Portal_ObjectRepository.suspensionCertificateEndYear, date[2]);
		common.selectDropdownByText(page, Portal_ObjectRepository.suspensionCertificateEndMonth, date[1]);
		common.selectDropdownByText(page, Portal_ObjectRepository.suspensionCertificateEndDay, date[0]);
	}
	
	/**
	*
	@Method implementation to select suspension certificate acquisition date 
	*this pop-up will display when acquisition date of suspended policy is >IDE.
	*
	**/
	public void suspensionCertificateRegistrationDate(Page page, String testCaseID) throws IOException, InterruptedException, ParseException {

		String[] date = SIC(testCaseID).getRegistrationDateOfSIC().split("-");
		String IDE = utility.homePageMap.get(testCaseID).getPurchaseInsurnaceInceptionDate();
		
		common.selectDropdownByText(page, Portal_ObjectRepository.vehicleInspectionRegistrationYear, date[2]);
		common.selectDropdownByText(page, Portal_ObjectRepository.vehicleInspectionRegistrationMonth, date[1]);
		common.selectDropdownByText(page, Portal_ObjectRepository.vehicleInspectionRegistrationDay, date[0]);
		
		
		if(common.compareTwoDates(IDE, SIC(testCaseID).getRegistrationDateOfSIC())<0) {
			acquisitionDateLimitPopupForSIC(page, testCaseID);
		}else {
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.suspendedPolicyStartDateLimitPopup);
			Report.logger.pass("Verify if pop-up is not displayed on selecting compensation starts is later than the date of acquisition of the vehicle");
		}
	}

	/**
	*
	@Method implementation to select Check box when suspended Insurance company is unknown
	*
	**/
	public void selectUnkownCompanyCheckboxOfSIC(Page page) {

		common.clickAction(page, Portal_ObjectRepository.unknownCurrentInsuranceCheckbox);

	}

	/**
	*
	@Method implementation to select Check box when grade unknown
	*
	**/
	public void selectUnknownGradeCheckboxOfSIC(Page page) {

		common.clickAction(page, Portal_ObjectRepository.unknownGradeCheckbox);

	}

	/**
	*
	@Method implementation to select Check box when number of accident coefficient is unknown
	*
	**/
	public void selectUnknownAccidentCheckboxOfSIC(Page page) {
		common.clickAction(page, Portal_ObjectRepository.unknownAccidentCheckbox);
	}

	
	/**
	*
	@Method implementation to select Car insurance questionnaires
	*
	**/
	public void carInsuranceQuestionaryOfSIC(Page page, String carInsurnaceConfirmation) {
		common.clickAction(page,"//div[@data-aem-id='qb_PreviousPolicy_Main_DivButtonBox']//li[text()='"+ carInsurnaceConfirmation + "']");
	}

	/**
	*
	@Method implementation to select not fleet grade type
	*This is method is also handling when grade type is unknown.
	*
	**/
	public void nonFleetGradeSelectionOfSIC(Page page, String testCaseID) throws IOException {

		if (SIC(testCaseID).getGradeTypeCheckboxOfSIC().equals("はい")) {
			selectUnknownGradeCheckboxOfSIC(page);
			common.selectDropdownByText(page, Portal_ObjectRepository.currentInsurnaceGradeYear,SIC(testCaseID).getGradeYearOfSIC());
		} else {
			common.selectDropdownByText(page, Portal_ObjectRepository.currentInsurnaceGradeSelection,SIC(testCaseID).getGradeTypeOfSIC());
		}

	}

	/**
	*
	@Method implementation to select accident coefficient
	*This is method is also handling when accident coefficient is unknown.
	*
	**/
	public void accidentCofficientSelectionOfSIC(Page page, String testCaseID) throws IOException {

		if (SIC(testCaseID).getAccidentCofficientCheckboxOfSIC().equals("はい")) {
			selectUnknownAccidentCheckboxOfSIC(page);
		} else {
			common.selectDropdownByText(page, Portal_ObjectRepository.currentInsuranceAccidentCoficient,SIC(testCaseID).getAccidentCofficientOfSIC());
		}

	}

	/**
	*
	@Method implementation to select number of accident
	*
	*
	**/
	public void numberOfAccidentCaseSelectionOfSIC(Page page, String testCaseID) throws IOException {

		if (SIC(testCaseID).getAccidentCaseNumberOfSIC().equals("１件")) {
			common.clickAction(page,"//li[text()='" + SIC(testCaseID).getAccidentCaseNumberOfSIC() + "']");
			common.clickAction(page, "//li[text()='" + SIC(testCaseID).getAccidentTypeOfSIC() + "']");

		} else if (SIC(testCaseID).getAccidentCaseNumberOfSIC().equals("２件以上")) {
			
			common.clickAction(page,"//li[text()='" + SIC(testCaseID).getAccidentCaseNumberOfSIC() + "']");
			common.clickAction(page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
		} else {
			common.clickAction(page,"//li[text()='" + SIC(testCaseID).getAccidentCaseNumberOfSIC() + "']");
		}
	}

	/**
	*
	@Method implementation to select Car insurance questionnaires
	*This is only applicable for Car insurance of Individual purchase type.
	*
	**/
	public void carInsuranceQuestionariesOfSIC(Page page, String testCaseID, String productType) throws IOException {
		if (productType.equals("自動車保険（四輪）")) {
			common.clickAction(page,"//div[@data-aem-id='qb_PreviousPolicy_Main_DivButtonBox']//li[text()='"+ SIC(testCaseID).getCarInsurnaceQuestionaryOfSIC() + "']");
		}

	}
	
	/**
	*
	@Method implementation to handle grade upgrade pop-up
	*This pop-up will appear while proceeding to next page.
	*
	**/
	public void upgreadPopupHandellingOfSIC(Page page, String testCaseID) throws IOException {
		
		common.passStatusWithScreenshots(page,testCaseID, "Grade upgrade pop-up displayed successfully ","gradeUpgradePop-up",true);
		common.clickAction(page, Portal_ObjectRepository.confrimationOfdialogCI);
		

	}
	
	/**
	*
	@Method implementation to validate menu option applicable for the suspended policy page
	*validation is happening for both enabled and grade out options
	*
	**/
	public void menuOptionValidationOfSuspendedPolicyPage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.threeLineMenuOptionNavigation(page, testCaseID);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuBeforeQuote);
		    Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuSuspendedPolicy);
		    Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.menuAboutCarAndBike+"//img", "src", "icon-tire-gray");
		    Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.menuMainDriver+"//img", "src", "icon-people-gray");
		    Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.menuQuotation+"//img", "src", "icon-check-gray");
		    Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuSaveContent);
		    Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuContactCenter);
		    common.passStatusWithScreenshots(page, testCaseID, "Menu options of Suspended Policy page is validated successfully" , "MenuOptionsOfSuspendedPolicyPage", true);
		    common.clickAction(page, Portal_ObjectRepository.closeMenuButton);
		}catch(AssertionError error) {
			System.out.println(error);
		    common.failStatusWithScreenshots(page, testCaseID, "Menu options of Suspended Policy page is not validated completely", "Failed_MenuOptionsOfSuspendedPolicyPage", true);
		}
 }
	
	/**
	*
	@Method implementation to validate error message for all mandatory field
	*
	*
	**/
	public void errorMessageValidationOfSuspendedPolicyPage(Page page, String testCaseID,String errorMessage) throws IOException {
		try {
		common.clickAction(page, Portal_ObjectRepository.proceedNext);
		Assertion.assertBytext(page, Portal_ObjectRepository.suspendedPolicyReason,errorMessage);
		Assertion.assertBytext(page, Portal_ObjectRepository.suspendedPolicySelectionError,errorMessage);
		Assertion.assertBytext(page, Portal_ObjectRepository.suspendedPolicyStartDateError,errorMessage);
		Assertion.assertBytext(page, Portal_ObjectRepository.suspendedPolicyEndDateError,errorMessage);
		Assertion.assertBytext(page, Portal_ObjectRepository.suspendedPolicyGradeError,errorMessage);
		Assertion.assertBytext(page, Portal_ObjectRepository.suspendedPolicyAccidentError,errorMessage);
		Assertion.assertBytext(page, Portal_ObjectRepository.suspendedPolicyVehicleInsurnaceError,errorMessage);
		Assertion.assertBytext(page, Portal_ObjectRepository.suspendedPolicyRegistrationDateError,errorMessage);
		common.passStatusWithScreenshots(page, testCaseID, "Error for all Mandetory field of Suspended Policy Page is displaying as:'"+errorMessage+"'", "SuspendedPolicyPageFieldErrorValidation", true);
		}catch(AssertionError error) {
		common.failStatusWithScreenshots(page, testCaseID, "Expected Error Message:'"+errorMessage+"' of one or all field of Suspended Policy page is different", "Failed_SuspendedPolicyPageFieldErrorValidation", true);
	  }
	}
	
	/**
	*
	@Method implementation to for functional (UI) validation of this page
	*we are validating logo, quotation number, ChatBot, menu options.
	*
	**/
	public void functionalValidationOfSuspendedPolicyPage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.PageLogoValidation(page, testCaseID);
			validation.quotationValidation(page, testCaseID);
			validation.chatBotValidation(page, testCaseID);
			menuOptionValidationOfSuspendedPolicyPage(page, testCaseID);
		}catch(AssertionError error) {
			System.out.println(error);
		    common.failStatusWithScreenshots(page, testCaseID, "Functional Validation of Previous Policy page is not completed", "Failed_PreviousPolicyFunctionalValidation", true);
		}	
}

	/**
	*
	@Method implementation of E2E flow for the suspended policy page
	*This method will take action based on the data Input sheet
	*
	**/
	public void e2eFlowOfSuspensionCertificatePage(Page page, String testCaseID, String productType) throws IOException, InterruptedException, ParseException {
	    //acceptSuspensionPageNavigationDialog(page,testCaseID);
		//CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		selectSuspensionReason(page, testCaseID);
		if (SIC(testCaseID).getUnkownCompanyCheckBoxOfSIC().equals("はい")) {
			selectUnkownCompanyCheckboxOfSIC(page);
		} else {
			selectInsuranceSuspendedCompany(page, testCaseID);
		}
		suspensionCertificateStartDate(page, testCaseID);
		suspensionCertificateEndDate(page, testCaseID);
		nonFleetGradeSelectionOfSIC(page, testCaseID);
		accidentCofficientSelectionOfSIC(page, testCaseID);
		numberOfAccidentCaseSelectionOfSIC(page, testCaseID);
		carInsuranceQuestionariesOfSIC(page, testCaseID, productType);
		suspensionCertificateRegistrationDate(page, testCaseID);
		common.passStatusWithScreenshots(page,testCaseID, "User entered data in Suspended Insurance page successfully ","SuspendedInsuranceData",true);
		common.clickAction(page, Portal_ObjectRepository.proceedNext);
		upgreadPopupHandellingOfSIC(page, testCaseID);
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		ADJ_portal_quotationScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");

	}


}
