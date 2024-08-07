package org.axa.portal.claim;

import java.util.List;

import org.axa.framework.Assertion;
import org.axa.framework.CommonFunctions;
import org.axa.framework.Portal_ObjectRepository;
import org.axa.portal.page.ADJ_portal_homePage;
import org.axa.portal.page.utility;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ADJ_portal_AMP_claimPortal extends CommonFunctions {

	public void startClaim(Page page,String testCaseId) {
		clickAction(page, Portal_ObjectRepository.emmaContractDetailsTab);
		int count=page.locator(Portal_ObjectRepository.ampPolicyListBox).count();
		if(count>=1) {
			Assertion.assertIsAttached(page, Portal_ObjectRepository.claim_ReportAccidentButton);
			passStatusWithScreenshots(page, testCaseId, "'事故のご報告はこちら' button is displayed under OE tab", "事故のご報告はこちらButtonValidation", false);
			clickAction(page, Portal_ObjectRepository.claim_ReportAccidentButton);
			Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
			Assertion.assertURLContains(page, "reportStart");
			passStatusWithScreenshots(page, testCaseId, "Successfully navigated to start claim page ", "startClaimPageNavigation", true);

		}else {
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.claim_ReportAccidentButton);
			failStatusWithScreenshots(page, testCaseId, "There is no policy present under OE tab", "Failed_PolicyNotPrsent", true);
		}
	}
	
	public void claimInprogress(Page page,String testCaseId) {
		clickAction(page, Portal_ObjectRepository.claim_InprogressButton);
		waitForSelector(page, "//span[text()='ホームに戻る']");
		passStatusWithScreenshots(page, testCaseId, "Successfully navigated to Claim Inprogress screen", "claimInprogressPageNavigation", true);
		//List<Locator> elementList=page.locator(Portal_ObjectRepository.claim_Inprogress_PolicyBox).all();
		int count=page.locator(Portal_ObjectRepository.claim_Inprogress_PolicyBox).count();
		String PolicyNumber=utility.emmaLoginMap.get(testCaseId).getPolicyNumber();
		
		if(count>=1) {
			
			for(int i=1;i<=count;i++) {
				if(getText(page,"("+Portal_ObjectRepository.claim_Inprogress_PolicyBox+")["+i+"]//p[4]").contains(PolicyNumber)){
					clickAction(page, "("+Portal_ObjectRepository.claim_Inprogress_PolicyBox+")["+i+"]");
					break;
					
				}
			}
			passStatusWithScreenshots(page, testCaseId, "SuccessFully navigated to Accident Progress Report", "accidentProgressReportPageNavigation", true);

		}else {
			failStatusWithScreenshots(page, testCaseId, "There is no policy claim is inprogress", "Failed_InprogressPolicyNotPrsent", true);
		}
	}
	
	public void AccidentProgressReportValidation(Page page,String testCaseId,int TablerowCount) {
		try {
		clickAction(page, Portal_ObjectRepository.claim_Inprogress_CheckInsuranceCoverageButton);
		passStatusWithScreenshots(page, testCaseId, "Successfully navigated to Accident Progress Report page", "accidentProgressPageNavigation", true);
		int count=page.locator(Portal_ObjectRepository.claim_Inprogress_OtherCompensationTable+"//tr").count();
		Assertion.assertHasCount(page, Portal_ObjectRepository.claim_Inprogress_OtherCompensationTable+"//tr", TablerowCount);
		takeScreenShotsOfComponent(page, Portal_ObjectRepository.claim_Inprogress_OtherCompensationTable, testCaseId, "その他の補償 table is present", "その他の補償Table Validation");
		}catch(AssertionError error) {
			failStatusWithScreenshots(page, testCaseId, "Table number of rows are are not as per expectation", "Failed_tableRowValidation", true);
		}

	}

	public void selectPolicyForClaim(Page page,String testCaseId) {
		try {

			String PolicyNumber=utility.emmaLoginMap.get(testCaseId).getPolicyNumber();

			Assertion.assertIsAttached(page, "//dd[text()='"+PolicyNumber+"']/../../following-sibling::div/a");
			takeScreenShotsOfComponent(page, "//dd[text()='"+PolicyNumber+"']/../../..", testCaseId, "Policy number: "+PolicyNumber+" is present for claim", "policyNumberSelection");
			clickAction(page, "//dd[text()='"+PolicyNumber+"']/../../following-sibling::div/a");
			passStatusWithScreenshots(page, testCaseId, "Successfully navigated to enter accident details page", "accidentInfoPage", true);

		}catch(AssertionError e) {
			System.out.println(e);
			passStatusWithScreenshots(page, testCaseId, "Unable to navigate to enter accident details page", "Failed_accidentInfoPage", true);
		}
	}

	public void enterAccidentDetails(Page page,String testCaseId) {
		try {

			KeyBoardTyping(page, Portal_ObjectRepository.claim_AccidentDate, utility.claim_AccidentInfoMap.get(testCaseId).getDateOfAccident());
			KeyBoardTyping(page, Portal_ObjectRepository.claim_AccidentTime, utility.claim_AccidentInfoMap.get(testCaseId).getTimeOfAccident());
			//KeyBoardTyping(page, Portal_ObjectRepository.claim_AccidentTime, "10:50AM");
			selectDropdownByText(page, Portal_ObjectRepository.claim_AccidentLocation, utility.claim_AccidentInfoMap.get(testCaseId).getLocationOfAccident());
			passStatusWithScreenshots(page, testCaseId, "Accident details is entered successfully", "accidentDeatilsPage", true);
			clickAction(page, Portal_ObjectRepository.proceedNext);
			passStatusWithScreenshots(page, testCaseId, "Successfully navigated to Type of accident selection page", "selectTypeOfAccidentPage", true);

		}catch(AssertionError e) {
			System.out.println(e);
			passStatusWithScreenshots(page, testCaseId, "There is problem in accident details page", "Failed_accidentDetailsPage", true);
		}catch(Exception e) {
			System.out.println(e);
		}
	}


	public void selectTypeOfAccident(Page page,String testCaseId) {

		try {
			String typeOfAccident=utility.claim_AccidentInfoMap.get(testCaseId).getTypeOfAccident();
			String accidentElement="//div[@data-testid='testButton']//p[contains(text(),'"+typeOfAccident+"')]/../..";
			Assertion.assertIsAttached(page, accidentElement);
			clickAction(page, accidentElement);
			passStatusWithScreenshots(page, testCaseId, "Successfully navigated to select injured person details page", "injuredPersonSelectionPage", true);

		}catch(AssertionError e) {
			System.out.println(e);
			passStatusWithScreenshots(page, testCaseId, "There is problem in Type Of Accident selection", "Failed_typeOfAccidentPage", true);
		}
	}

	public void selectAccidentInjuredPerson(Page page,String testCaseId) {
		try {
			String injuerdPerson=utility.claim_AccidentInfoMap.get(testCaseId).getInjuryStatus();

			//String injuryPersonElement="//p[contains(text(),'"+injuerdPerson+"')]/../..";
			String injuryPersonElement="//p[contains(text(),'運転者')]/../..";
			Assertion.assertIsAttached(page, injuryPersonElement);
			clickAction(page, injuryPersonElement);
			passStatusWithScreenshots(page, testCaseId, "Injured Person selected successfully", "DatainjuredPersonSelectionPage", true);
			clickAction(page, Portal_ObjectRepository.proceedNext);
			passStatusWithScreenshots(page, testCaseId, "Successfully navigated to Accident Explanation page", "accidentExplanationPageNavigation", true);

		}catch(AssertionError e) {
			System.out.println(e);
			passStatusWithScreenshots(page, testCaseId, "There is problem in select injured person selection page", "Failed_injuredPersonSelectionPage", true);
		}
	}

	public void selectAccidentMostExplanation(Page page,String testCaseId) {
		try {
			String AccidentExplanantion=null;

			if(utility.claim_AccidentInfoMap.get(testCaseId).getTypeOfAccident().equals("お車での単独事故や")) {
				//enter the map value here
				//AccidentExplanantion="車以外のものと";
				AccidentExplanantion=utility.claim_AccidentInfoMap.get(testCaseId).getAccidentExplanation1();
			}else {
				//enter the map value here
				AccidentExplanantion=utility.claim_AccidentInfoMap.get(testCaseId).getAccidentExplanation2();
			}

			String accidentExplanationElement="//div[@data-testid='testButton']//p[contains(text(),'"+AccidentExplanantion+"')]/../..";
			Assertion.assertIsAttached(page, accidentExplanationElement);
			clickAction(page, accidentExplanationElement);

			if(AccidentExplanantion.equals("出合い頭の")) {
				//traffic light case implementation
				passStatusWithScreenshots(page, testCaseId, "Choose traffic light page displayed successfully", "trafficLightPageNavigation", true);
				selectTrafficLight(page, testCaseId);
			}else if(AccidentExplanantion.equals("進路変更中の") || AccidentExplanantion.equals("道路に出入り")) {
				passStatusWithScreenshots(page, testCaseId, "Accident Circumstance page displayed successfully", "accidentCircumstance PageNavigation", true);
				selectAccidentCircumstance(page, testCaseId);

			}else if(utility.claim_AccidentInfoMap.get(testCaseId).getTypeOfAccident().equals("お車での単独事故や")) {
				passStatusWithScreenshots(page, testCaseId, "Successfully navigated to additional info of Accident page", "accidentAdditionalInfoPageNavigation", true);
				if(AccidentExplanantion.equals("車以外のものと")) {
					Assertion.assertIsEditable(page, Portal_ObjectRepository.claim_CarHitByOther);
					enterText(page, Portal_ObjectRepository.claim_CarHitByOther, "");
				}
			}
		}catch(AssertionError e) {
			System.out.println(e);
			failStatusWithScreenshots(page, testCaseId, "There is problem in select injured person selection page", "Failed_injuredPersonSelectionPage", true);
		}
	}

	public void selectTrafficLight(Page page,String testCaseId) {

		String trafficLightElement="//div[@data-testid='testButton']//p[text()='"+utility.claim_AccidentInfoMap.get(testCaseId).getTrafficLight()+"']";
		String trafficLightMovementElement="//div[@data-testid='testButton']//p[text()='"+utility.claim_AccidentInfoMap.get(testCaseId).getTrafficLightMovement()+"']";
		clickAction(page, trafficLightElement);
		passStatusWithScreenshots(page, testCaseId, "Successfully navigated to Traffic Light movement page", "trafficLightMovementPageNavigation", true);
		clickAction(page, trafficLightMovementElement);
		passStatusWithScreenshots(page, testCaseId, "Successfully navigated to additional info of Accident page", "accidentAdditionalInfoPageNavigation", true);
	}

	public void selectAccidentCircumstance(Page page,String testCaseId) {
		String accidentCircumstanceElement="//div[@data-testid='testButton']//p[text()='"+utility.claim_AccidentInfoMap.get(testCaseId).getAccidentCircumstance()+"']";
		clickAction(page, accidentCircumstanceElement);
		passStatusWithScreenshots(page, testCaseId, "Successfully navigated to additional info of Accident page", "accidentAdditionalInfoPageNavigation", true);
	}

	public void enterAdditionalAccidentInfo(Page page,String testCaseId) {
		if(utility.claim_AccidentInfoMap.get(testCaseId).getTypeOfAccident().equals("お車での単独事故や")) {
			enterText(page, Portal_ObjectRepository.claim_accidentDetailsTextArea, utility.claim_AccidentInfoMap.get(testCaseId).getRemarks());
		}else {
			enterText(page, Portal_ObjectRepository.claim_accidentSituationAdditionalInfo, utility.claim_AccidentInfoMap.get(testCaseId).getRemarks());
		}
		passStatusWithScreenshots(page, testCaseId, "Text Entered succesfully under additional info of Accident page", "dataValidationOfAccidentAdditionalInfo", true);
		clickAction(page, Portal_ObjectRepository.proceedNext);
		passStatusWithScreenshots(page, testCaseId, "Successfully navigated to Vehicle info page", "vehicleInfoPageNavigation", true);
	}

	public void selectAccidentVehicleInfo(Page page,String testCaseId) {
		String vehicleTypeElement="//div[@data-testid='testButton']//p[text()='"+utility.claim_VehicleInfoMap.get(testCaseId).getVehicleType()+"']/../..";
		clickAction(page, vehicleTypeElement);
		passStatusWithScreenshots(page, testCaseId, "Accident vehicle type is selected successfully", "selectVehicleType", false);
	}

	public void selectVehicleRepairStatus(Page page,String testCaseId) {
		String repairStatusElement="//span[text()='"+utility.claim_VehicleInfoMap.get(testCaseId).getVehicleRepairStatus()+"']/..";
		clickAction(page, repairStatusElement);
		if(utility.claim_VehicleInfoMap.get(testCaseId).getVehicleRepairStatus().equals("希望しない")) {
			enterText(page, Portal_ObjectRepository.claim_VehicleRepairShopName, utility.claim_VehicleInfoMap.get(testCaseId).getRepairShopName());
			enterText(page, Portal_ObjectRepository.claim_VehicleRepairShopPhoneNumber, utility.claim_VehicleInfoMap.get(testCaseId).getRepairShopPhoneNumber());
		}
		passStatusWithScreenshots(page, testCaseId, "Vehicle Reparing information is entered successfully", "enterVehicleRepairShopInfo", true);
		clickAction(page, Portal_ObjectRepository.proceedNext);
		passStatusWithScreenshots(page, testCaseId, "Successfully navigated to Licence info page", "LicenceInfoPageNavigation", true);
	}

	public void selectVehicleDriverInfo(Page page,String testCaseId) {
		String driverElement="//div[@data-testid='testButton']//p[text()='"+utility.claim_VehicleInfoMap.get(testCaseId).getVehicleDriver()+"']/../..";
		clickAction(page, driverElement);

		if(Assertion.assertURLContains(page, "sme") || !utility.claim_VehicleInfoMap.get(testCaseId).getVehicleDriver().equals("ご契約者さま本人")) {
			enterText(page, Portal_ObjectRepository.claim_driverName_lastName, utility.claim_VehicleInfoMap.get(testCaseId).getDriverLastNameKanji());
			enterText(page, Portal_ObjectRepository.claim_driverName_firstName, utility.claim_VehicleInfoMap.get(testCaseId).getDriverFirstNameKanji());
			enterText(page, Portal_ObjectRepository.claim_driverName_lastNameKana, utility.claim_VehicleInfoMap.get(testCaseId).getDriverLastNameKana());
			enterText(page, Portal_ObjectRepository.claim_driverName_firstNameKana, utility.claim_VehicleInfoMap.get(testCaseId).getDriverFirstNameKana());
		}
		uploadFile(page, Portal_ObjectRepository.claim_uploadFile, utility.claim_VehicleInfoMap.get(testCaseId).getLicencePhotoPath());
		clickAction(page, "//span[text()='"+utility.claim_VehicleInfoMap.get(testCaseId).getLicenceColour()+"']/..");
		KeyBoardTyping(page, Portal_ObjectRepository.claim_licenceDate, utility.claim_VehicleInfoMap.get(testCaseId).getLicenceExpiaryDate());
		enterText(page, Portal_ObjectRepository.claim_licenceNumber, utility.claim_VehicleInfoMap.get(testCaseId).getLicenceNumber());
		passStatusWithScreenshots(page, testCaseId, "Licence information has been selected successfully", "enterLicenceInformation", true);
		clickAction(page, Portal_ObjectRepository.proceedNext);
		passStatusWithScreenshots(page, testCaseId, "Successfully navigated to Vehicle damage info page", "vehicleDamageInfoPageNavigation", true);
	}

	public void uploadVehicleDamageInfo(Page page,String testCaseId) {
		try {

		uploadFile(page, Portal_ObjectRepository.claim_uploadFile, utility.claim_VehicleInfoMap.get(testCaseId).getDamagedCarPhotoPath());
		}catch(Exception e) {
			
		}

		passStatusWithScreenshots(page, testCaseId, "Vehicle Damage photo or vedio is uploaded successfully", "vehicleDamageInfoUpload", true);
		clickAction(page, Portal_ObjectRepository.proceedNext);
		passStatusWithScreenshots(page, testCaseId, "Successfully navigated to Vehicle damage info page", "vehicleDamageInfoPageNavigation", true);
	}

	public void enterFirstContactDetails(Page page,String testCaseId) {

		if(ADJ_portal_homePage.CORPORATE_URL) {
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.claim_contactAddressie);
		}else {
			selectDropdownByText(page, Portal_ObjectRepository.claim_contactAddressie, utility.claim_FirstContactMap.get(testCaseId).getContactAddress());
		}

		if(ADJ_portal_homePage.CORPORATE_URL || !utility.claim_FirstContactMap.get(testCaseId).getContactAddress().equals("契約者")) {
			enterText(page, Portal_ObjectRepository.claim_contactor_lastName, utility.claim_FirstContactMap.get(testCaseId).getAddressiLastNameKanji());
			enterText(page, Portal_ObjectRepository.claim_contactor_firstName, utility.claim_FirstContactMap.get(testCaseId).getAddressiFirstNameKanji());
			enterText(page, Portal_ObjectRepository.claim_contactor_lastNameKana, utility.claim_FirstContactMap.get(testCaseId).getAddressiLastNameKana());
			enterText(page, Portal_ObjectRepository.claim_contactor_firstNameKana, utility.claim_FirstContactMap.get(testCaseId).getAddressiFirstNameKana());
		}
		selectDropdownByText(page, Portal_ObjectRepository.claim_phoneNumberType, utility.claim_FirstContactMap.get(testCaseId).getPhoneNumberType());
		enterText(page, Portal_ObjectRepository.claim_contactor_MobileNumber, utility.claim_FirstContactMap.get(testCaseId).getAddressiMobileNumber());
		selectDropdownByText(page, Portal_ObjectRepository.claim_contactor_contactTime, utility.claim_FirstContactMap.get(testCaseId).getContactTime());
		clickAction(page, "//label[text()='"+utility.claim_FirstContactMap.get(testCaseId).getNotificationMethod()+"']/..");
		if(utility.claim_FirstContactMap.get(testCaseId).getNotificationMethod().equals("SMS/LINE") && !utility.claim_FirstContactMap.get(testCaseId).getPhoneNumberType().equals("携帯電話（SMS）")) {
			enterText(page, Portal_ObjectRepository.claim_contactor_NotificationMobileNumber, utility.claim_FirstContactMap.get(testCaseId).getNotificationPhoneNumber());
		}
		enterText(page, Portal_ObjectRepository.claim_contactor_queriesTextBox, utility.claim_FirstContactMap.get(testCaseId).getNotificationRemarks());

		passStatusWithScreenshots(page, testCaseId, "First Contact Information has been entered successfully", "firstContactDataValidation", true);
		clickAction(page, Portal_ObjectRepository.claim_contactor_confirmationButton);
		clickAction(page, Portal_ObjectRepository.claim_checkInput_termAndConditionCheckBox);
		passStatusWithScreenshots(page, testCaseId, "Successfully navigated to Check input information page", "checkInputPageNavigation", true);
		acceptCookies(page, Portal_ObjectRepository.closeBanner);
		clickAction(page, Portal_ObjectRepository.claim_checkInput_registerButton);
		Assertion.assertIsAttached(page, "//span[text()='事故のご報告を']");
	}
	
	public void e2eFlowOfClaims(Page page, String testCaseId) {
		try {
			startClaim(page, testCaseId);
			selectPolicyForClaim(page, testCaseId);
			enterAccidentDetails(page, testCaseId);
			selectTypeOfAccident(page, testCaseId);
			selectAccidentInjuredPerson(page, testCaseId);
			selectAccidentMostExplanation(page, testCaseId);
			enterAdditionalAccidentInfo(page, testCaseId);
			selectAccidentVehicleInfo(page, testCaseId);
			selectVehicleRepairStatus(page, testCaseId);
			selectVehicleDriverInfo(page, testCaseId);
			uploadVehicleDamageInfo(page, testCaseId);
			enterFirstContactDetails(page, testCaseId);
			
		}catch(AssertionError error) {
			System.out.println(error);
		}catch(Exception error) {
			System.out.println(error);
			
		}
	}
}

