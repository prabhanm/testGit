package org.axa.portal.page;

import java.io.IOException;

import org.axa.framework.Assertion;
import org.axa.framework.CommonFunctions;
import org.axa.framework.Portal_ObjectRepository;
import org.axa.framework.Report;
import org.axa.portal.validation.ADJ_portal_CommonValidation;

import com.microsoft.playwright.Page;

public class ADJ_portal_vehicleInformationScreen {

	utility util = new utility();
	CommonFunctions common = new CommonFunctions();
	ADJ_portal_CommonValidation validation=new ADJ_portal_CommonValidation();
	ADJ_portal_aboutMainDriverAndPolicyPlanScreen mainDriver=new ADJ_portal_aboutMainDriverAndPolicyPlanScreen();
	public static boolean CORPORATE_URL=false;

	public ParameterOfVehicleInformationPage vehicleInfo(String testCaseId) throws IOException {
		ParameterOfVehicleInformationPage para=utility.vehicleInfoMap.get(testCaseId);
		return para;
	}

	public void enterCurrentOrSuspendedInsurnaceDetailsInVechileInfoPage(Page page,String testCaseId,String insurancePurchaseType) throws IOException {
		System.out.println(insurancePurchaseType);
		if(insurancePurchaseType.equals("現在他社で加入している")) {
			common.enterText(page, Portal_ObjectRepository.vehicleCurrentInsurancePolicyNumber, vehicleInfo(testCaseId).getCurrentOrSuspendedInsurancePolicyNumber());
			common.enterText(page, Portal_ObjectRepository.vehicleInfo_CurrentInsurance_BranchNumber, vehicleInfo(testCaseId).getBranchNumber());
			if(vehicleInfo(testCaseId).getCurrentOrSuspendedInsurancePolicyNumber().equals("いいえ")){
				common.clickAction(page, Portal_ObjectRepository.vehicleCurrentInsurnaceQuestionariesDeny);
			}else {
				common.clickAction(page, Portal_ObjectRepository.vehicleCurrentInsurnaceQuestionariesConfirm);
			}
		}else if(insurancePurchaseType.equals("中断証明書を使用して加入する")) {
			common.enterText(page, Portal_ObjectRepository.vehicleSuspendedInsurancePolicyNumber, vehicleInfo(testCaseId).getCurrentOrSuspendedInsurancePolicyNumber());
			common.enterText(page, Portal_ObjectRepository.vehicleInfo_suspendedInsurance_BranchNumber, vehicleInfo(testCaseId).getBranchNumber());
			if(vehicleInfo(testCaseId).getCurrentOrSuspendedInsurancePolicyNumber().equals("いいえ")){
				common.clickAction(page, Portal_ObjectRepository.vehicleSuspendedInsurnaceQuestionariesDeny);
			}else {
				common.clickAction(page, Portal_ObjectRepository.vehicleSuspendedInsurnaceQuestionariesConfirm);
			}

		}
	}

	public void enterSMECurrentOrSuspendedInsurnaceDetailsInVechileInfoPage(Page page,String testCaseId,String insurancePurchaseType) throws IOException {
		if(insurancePurchaseType.equals("現在他社で加入している")) {
			common.enterText(page, Portal_ObjectRepository.vehicleCurrentInsurancePolicyNumber, vehicleInfo(testCaseId).getCurrentOrSuspendedInsurancePolicyNumber());
			if(vehicleInfo(testCaseId).getCurrentOrSuspendedInsurancePolicyNumber().equals("いいえ")){
				common.clickAction(page, Portal_ObjectRepository.vehicleCurrentInsurnaceQuestionariesDeny);
			}else {
				common.clickAction(page, Portal_ObjectRepository.vehicleCurrentInsurnaceQuestionariesConfirm);
			}
		}else if(insurancePurchaseType.equals("中断証明書を使用して加入する")) {
			common.enterText(page, Portal_ObjectRepository.vehicleSuspendedInsurancePolicyNumber, vehicleInfo(testCaseId).getCurrentOrSuspendedInsurancePolicyNumber());
			if(vehicleInfo(testCaseId).getCurrentOrSuspendedInsurancePolicyNumber().equals("いいえ")){
				common.clickAction(page, Portal_ObjectRepository.vehicleSuspendedInsurnaceQuestionariesDeny);
			}else {
				common.clickAction(page, Portal_ObjectRepository.vehicleSuspendedInsurnaceQuestionariesConfirm);
			}

		}
	}

	public void InsuredCurrentAndInterruptionContractPopupForSME(Page page,String testCaseId,String insurancePurchaseType) throws IOException {
		try {
			Assertion.assertIsAttached(page, Portal_ObjectRepository.vehicleInfo_InsuredCurrentAndInterruptionContractPopup);
			if(insurancePurchaseType.equals("現在他社で加入している")) {
				Assertion.assertBytext(page, Portal_ObjectRepository.vehicleInfo_InsuredCurrentAndInterruptionContractPopup, " 今回のご契約の記名被保険者と「現在のご契約の記名被保険者」が同一名称の法人でない場合、インターネットでのお申込みができません。ご不明な点がありましたら、カスタマーサービスセンターまでお電話ください。お取扱い範囲について");
			}else if(insurancePurchaseType.equals("中断証明書を使用して加入する")){
				Assertion.assertBytext(page, Portal_ObjectRepository.vehicleInfo_InsuredCurrentAndInterruptionContractPopup+"//span", "今回のご契約の記名被保険者と「中断証明書記載の記名被保険者」が同一名称の法人でない場合、お申込みができません。ご不明な点がありましたら、カスタマーサービスセンターまでお電話ください。");
			}
			Assertion.assertHasAttribute(page, "//a[text()='カスタマーサービスセンター']", "href", "https://www.axa-direct.co.jp/inquiry/?popup=true");
			Assertion.assertHasAttribute(page, "//a[text()='お取扱い範囲について']", "href", "https://www.axa-direct.co.jp/auto/goodprice/easy_quote/range.html?popup=true");
			common.passStatusWithScreenshots(page, testCaseId, "Insured Current And Interruption Contract Popup validation is completed", "InsuredCurrentAndInterruptionContractPopup", false);
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseId, "Insured Current And Interruption Contract Popup validation is not completed", "Failed_InsuredCurrentAndInterruptionContractPopup", false);
		}
	}

	public void genderSectionValidation(Page page,String testCaseId) throws IOException {
		if(CORPORATE_URL) {
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.mainDriverGenderSection);
			Assertion.assertIsNotAttached(page, "//span[text()='今回のお車を主に運転する方（記名被保険者）についてお伺いします']");
			Report.logger.pass("Gender section is not attached for corporate flow");
		}else {
			Assertion.assertIsAttached(page, Portal_ObjectRepository.mainDriverGenderSection);
			Assertion.assertBytext(page, Portal_ObjectRepository.vehicleInfoGenderSectionText,"ﾄﾖﾀ ｱｸｱ を主に運転する方の性別を教えてください");
			Assertion.assertIsAttached(page, Portal_ObjectRepository.genderMale);
			Assertion.assertIsAttached(page, Portal_ObjectRepository.genderFemale);
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.mainDriverGenderSection, testCaseId, "Gender section is present for personal flow", "personalGenderSectionValidation");
		}
	}
	
	public void selectGender(Page page,String testCaseId) throws IOException {
		if(CORPORATE_URL) {
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.mainDriverGenderSection);
			Assertion.assertIsNotAttached(page, "//span[text()='今回のお車を主に運転する方（記名被保険者）についてお伺いします']");
		}else {
			if(vehicleInfo(testCaseId).getGender().equals("男性")) {
				common.clickAction(page, Portal_ObjectRepository.genderMale);
			}else if(vehicleInfo(testCaseId).getGender().equals("女性")) {
				common.clickAction(page, Portal_ObjectRepository.genderFemale);
			}else {
				common.clickAction(page, "//span[text()='"+vehicleInfo(testCaseId).getGender()+"']");
			}
		}
	}

	public void selectCarModificationQuestionaries(Page page,String testCaseId,String productType) throws IOException {

		if(productType.equals("自動車保険（四輪）") && vehicleInfo(testCaseId).getCarModificationQuestionaries().equals("はい")) {
			common.clickAction(page, Portal_ObjectRepository.carModficationConfirmation);
			common.passStatusWithScreenshots(page, testCaseId,"Car Modification confirmation pop-up is displayed successfully","CarModificationPop-up",false);
			common.clickAction(page, "//*[text()='閉じる']");
		}
	}

	public void enterVehicleInformation(Page page,String testCaseId,String productType) throws IOException {

		common.enterText(page, Portal_ObjectRepository.vehicleChassisNumber, vehicleInfo(testCaseId).getVehicleChassisNumber());
		if(vehicleInfo(testCaseId).getPolicyHolderIsOwner().equals("いいえ")) {
			common.clickAction(page, Portal_ObjectRepository.vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityDeny);
			if(vehicleInfo(testCaseId).getSecondaryOwnerRelation().equals("配偶者") || vehicleInfo(testCaseId).getSecondaryOwnerRelation().equals("親族") || vehicleInfo(testCaseId).getSecondaryOwnerRelation().equals("ディーラー/ローン")) {

				common.clickAction(page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection+"//span[contains(text(),'"+vehicleInfo(testCaseId).getSecondaryOwnerRelation()+"')]");
				if(!CORPORATE_URL) {
					common.enterText(page, Portal_ObjectRepository.vehicleSecondaryOwnerLastNameKanji,vehicleInfo(testCaseId).getSecondaryOwnerLastNameKanji());
					common.enterText(page, Portal_ObjectRepository.vehicleSecondaryOwnerFirstNameKanji,vehicleInfo(testCaseId).getSecondaryOwnerFirstNameKanji());
					common.enterText(page, Portal_ObjectRepository.vehicleSecondaryOwnerLastNameKatakana,vehicleInfo(testCaseId).getSecondaryOwnerLastNameKatakana());
					common.enterText(page, Portal_ObjectRepository.vehicleSecondaryOwnerFirstNameKatakana,vehicleInfo(testCaseId).getSecondaryOwnerLastNameKatakana());
				}


			}else {
				common.clickAction(page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection+"//span[contains(text(),'"+vehicleInfo(testCaseId).getSecondaryOwnerRelation()+"')]");
				common.passStatusWithScreenshots(page, testCaseId,"Vehicle secondary owner confirmation pop-up is displayed successfully","secondaryOwnerConfirmationPop-up",false);
				common.clickAction(page, "//*[text()='閉じる']");
			}

		}
		if(productType.equals("自動車保険（四輪）")) {
			common.selectDropdownByText(page, Portal_ObjectRepository.carLicensePlatePrefecture, vehicleInfo(testCaseId).getLicencePlateKanjiOrPrefecture());
			common.enterText(page, Portal_ObjectRepository.carlicensePlateClassCode, vehicleInfo(testCaseId).getLicencePlateNumberOrKatakana());
		}else {
			if(common.isElementPresent(page, Portal_ObjectRepository.carLicensePlatePrefecture)) {
				common.selectDropdownByText(page, Portal_ObjectRepository.carLicensePlatePrefecture, vehicleInfo(testCaseId).getLicencePlateKanjiOrPrefecture());
			}else {
				common.enterText(page, Portal_ObjectRepository.bikelicensePlateKanji, vehicleInfo(testCaseId).getLicencePlateKanjiOrPrefecture());
				common.enterText(page, Portal_ObjectRepository.bikelicensePlateKatakana, vehicleInfo(testCaseId).getLicencePlateNumberOrKatakana());
			}	
		}
		common.enterText(page, Portal_ObjectRepository.vehiclelicensePlateHiragana, vehicleInfo(testCaseId).getLicencePlateHiragana());
		common.enterText(page, Portal_ObjectRepository.vehiclelicensePlateSerialNumber, vehicleInfo(testCaseId).getLicencePlateSerialNumber());
		common.enterText(page, Portal_ObjectRepository.vehicleMileage, vehicleInfo(testCaseId).getVehicleMileage());

	}

	public void selectVehicleMileageCheckDate(Page page,String testCaseId) throws IOException {
		String[] mileageDate=vehicleInfo(testCaseId).getVehicleMileageCheckDate().split("-");
		common.selectDropdownByText(page, Portal_ObjectRepository.vehicleMileageConfirmationYear, mileageDate[2]);
		common.selectDropdownByText(page, Portal_ObjectRepository.vehicleMileageConfirmationMonth, mileageDate[1]);
		common.selectDropdownByText(page, Portal_ObjectRepository.vehicleMileageConfirmationDay,  mileageDate[0]);
	}

	public void ErrorMessageValidationOfVehicleInformationPage(Page page,String testCaseId,String ErrorMessage) throws IOException {
		common.waitForSelector(page, Portal_ObjectRepository.carLicensePlatePrefecture);
		common.clickAction(page,Portal_ObjectRepository.proceedNext);

		try {
			if(!CORPORATE_URL) {
				Assertion.assertBytext(page, Portal_ObjectRepository.vehicleInfoGenderFieldError, ErrorMessage);
			}

			Assertion.assertBytext(page, Portal_ObjectRepository.vehicleInfoChassisNumberFieldError, ErrorMessage);
			Assertion.assertBytextContains(page, Portal_ObjectRepository.vehicleInfoLicencePlatePrefectureFieldError, ErrorMessage);
			Assertion.assertBytextContains(page, Portal_ObjectRepository.vehicleInfoLicencePlateClassCodeFieldError, ErrorMessage);
			Assertion.assertBytextContains(page, Portal_ObjectRepository.vehicleInfoLicencePlateHiraganaFieldError, ErrorMessage);
			Assertion.assertBytextContains(page, Portal_ObjectRepository.vehicleInfoLicencePlateSerialNumberFieldError, ErrorMessage);
			Assertion.assertBytext(page, Portal_ObjectRepository.vehicleInfoMilageFieldError, ErrorMessage);
			common.passStatusWithScreenshots(page, testCaseId, "Error for all Mandetory field of Vehicle Information Page is displaying as:'"+ErrorMessage+"'", "Vehicle InformationPageFieldErrorValidation", true);
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseId, "Expected Error Message:'"+ErrorMessage+"' of one or all field of Vehicle Information is different", "Failed_Vehicle InformationPageFieldErrorValidation", true);
			System.out.println(error);
		}
	}

	public void functionalValidationOfVehicleInformationPage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.PageLogoValidation(page, testCaseID);
			validation.quotationValidation(page, testCaseID);
			validation.chatBotValidation(page, testCaseID);
			validation.headerImageIconValidationAndNavigation(page, testCaseID);
			menuOptionValidationOfVehicleInformationPage(page, testCaseID);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Functional Validation of Before Application page is not completed", "Failed_BeforeApplicationPageFunctionalValidation", true);
		}
	}

	public void menuOptionValidationOfVehicleInformationPage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.threeLineMenuOptionNavigation(page, testCaseID);
			Assertion.assertIsHidden(page, Portal_ObjectRepository.menuBeforeQuote);

			if(utility.homePageMap.get(testCaseID).getInsurancePurchaseType().equals("現在他社で加入している")) {
				Assertion.assertIsHidden(page, Portal_ObjectRepository.menuCurrentInsurnace);
			}else if (utility.homePageMap.get(testCaseID).getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				Assertion.assertIsHidden(page, Portal_ObjectRepository.menuSuspendedPolicy);
			}else {
				Assertion.assertIsHidden(page, Portal_ObjectRepository.menuCurrentInsurnace);
				Assertion.assertIsHidden(page, Portal_ObjectRepository.menuSuspendedPolicy);
			}
			Assertion.assertIsHidden(page, Portal_ObjectRepository.menuAboutCarAndBike);
			Assertion.assertIsHidden(page, Portal_ObjectRepository.menuMainDriver);
			Assertion.assertIsHidden(page, Portal_ObjectRepository.menuSaveContent);
			Assertion.assertIsHidden(page, Portal_ObjectRepository.menuContactCenter);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuQuotation);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuBeforeApplication);
			common.passStatusWithScreenshots(page, testCaseID, "Menu options of Vehicle Information page is validated successfully" , "MenuOptionValidationVehicleInfoPage", true);
			common.clickAction(page, Portal_ObjectRepository.closeMenuButton);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Menu options of Vehicle Holder Information page is not validated completely", "Failed_MenuOptionValidationVehicleInfoPage", true);
		}
	}


	public void e2eFlowOfVehicleInformation(Page page,String testCaseId,String productType,String insurancePurchaseType) throws IOException {
		//CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		selectGender(page, testCaseId);
		enterCurrentOrSuspendedInsurnaceDetailsInVechileInfoPage(page, testCaseId, insurancePurchaseType);
		selectCarModificationQuestionaries(page, testCaseId, productType);
		enterVehicleInformation(page, testCaseId, productType);
		selectVehicleMileageCheckDate(page, testCaseId);
		common.passStatusWithScreenshots(page,testCaseId, "Data has been entered successfully in the Vehicle Information page","VehicleInformationPage",true);
		common.clickAction(page, Portal_ObjectRepository.proceedNext);
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		ADJ_portal_policyHolderInformationScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		common.passStatusWithScreenshots(page, testCaseId,"Successfully proceeded to Policy Holder Information page","proceededToPolicyHolderInformationPage",true);
	}

	public void currentAndSuspendedInsuranceInformationSectionValidation(Page page,String testCaseId) throws IOException {

		if(utility.homePageMap.get(testCaseId).getInsurancePurchaseType().equals("現在他社で加入している")) {
			Assertion.assertHasAttribute(page, Portal_ObjectRepository.vehicleInfo_CurrentInsurance_PolicyNumber, "placeholder", "証券番号");
			Assertion.assertBytext(page, Portal_ObjectRepository.vehicleInfo_CurrentInsuranceInfo_Section+"//li[contains(@data-aem,'CurrentInsuranceInputField_ReferenceList_ListItem')]", "半角英数字のみ");
			Assertion.assertIsEditable(page, Portal_ObjectRepository.vehicleInfo_CurrentInsurance_BranchNumber);
			Assertion.assertHasAttribute(page, Portal_ObjectRepository.vehicleInfo_CurrentInsurance_BranchNumber, "placeholder", "枝番（任意）");
			common.inputFieldLimitValidation(page, Portal_ObjectRepository.vehicleInfo_CurrentInsurance_BranchNumber, "122343345545", 5);
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.vehicleInfo_CurrentInsuranceInfo_Section, testCaseId,"Current Insurance information field validation is completed", "CurrentInsuranceInfoFieldValidation");

		}else if(utility.homePageMap.get(testCaseId).getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
			Assertion.assertHasAttribute(page, Portal_ObjectRepository.vehicleInfo_suspendedInsurance_PolicyNumber, "placeholder", "証券番号");
			Assertion.assertBytext(page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceInfo_Section+"//li[contains(@data-aem,'SuspendedInsuranceInputField_ReferenceList_ListItem')]", "半角英数字のみ");
			Assertion.assertIsEditable(page, Portal_ObjectRepository.vehicleInfo_suspendedInsurance_BranchNumber);
			Assertion.assertHasAttribute(page, Portal_ObjectRepository.vehicleInfo_suspendedInsurance_BranchNumber, "placeholder", "枝番（任意）");
			common.inputFieldLimitValidation(page, Portal_ObjectRepository.vehicleInfo_suspendedInsurance_BranchNumber, "122343345545", 5);
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.vehicleInfo_SuspendedInsuranceInfo_Section, testCaseId,"Suspended Insurance information field validation is completed", "suspendedInsuranceInfoFieldValidation");
		}

	}

	public void businessVehicleModificationQuestionariesValidation(Page page,String testCaseId,String productType) throws IOException {
		try {

			if(productType.equals("自動車保険（四輪）")) {
				if(CORPORATE_URL) {
					Assertion.assertIsAttached(page, Portal_ObjectRepository.vehicleInfo_BusinessUseVehicleSection);
					common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.vehicleInfo_BusinessUseVehicleSection, testCaseId, "Business use vehicle section is attached for corporate flow", "BusinessUseVehicleSectionValidation");
					Assertion.assertBytext(page, Portal_ObjectRepository.vehicleInfo_BusinessUseVehicleSectionTitle, "車検証上の「自家用・事業用の別」欄が「事業用」と記載されていますか？");
					Assertion.assertIsChecked(page, Portal_ObjectRepository.vehicleInfo_BusinessUseVehicleSection+"//span[text()='いいえ']");
					Report.logger.pass("By default 'いいえ' button is selected for Business use vehicle for Corporate flow");
					common.clickAction(page, Portal_ObjectRepository.vehicleInfo_BusinessUseVehicleSection+"//span[text()='はい']");
					Assertion.assertIsAttached(page, Portal_ObjectRepository.vehicleInfo_BusinessUseVehicleModlePopup);
					common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.vehicleInfo_BusinessUseVehicleModlePopup, testCaseId, "Business use vehicle Model pop-up is displayed after clicking on 'はい' button", "BusinessUseVehicleModlePopup");
					Assertion.assertBytext(page, Portal_ObjectRepository.vehicleInfo_BusinessUseVehicleModlePopup, "車検証上の「自家用・事業用の別」が「事業用」の場合、お申込みいただけません。お取扱い範囲について");
					common.clickAction(page, "//*[text()='閉じる']");
				}else {
					Assertion.assertIsAttached(page, Portal_ObjectRepository.vehicleInfo_BusinessUseVehicleSection);
					Report.logger.pass("Business use vehicle section is not attached for Personal flow");
				}
			}
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseId, "There is problem in Business use vehicle section validation", "Failed_BusinessUseVehicleSectionValidation", false);
		}
	}

	public void frieghtVehicleModificationQuestionariesValidation(Page page,String testCaseId,String productType) throws IOException {
		try {

			if(productType.equals("自動車保険（四輪）") && util.quotationPageMap.get(testCaseId).getVechilePurpose().equals("業務用")) {
				Assertion.assertIsAttached(page, Portal_ObjectRepository.vehicleInfo_FreightVehicleLoadCapacitySection);
				common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.vehicleInfo_FreightVehicleLoadCapacitySection, testCaseId, "Freight vehicle section is attached for corporate flow", "FreightVehicleSectionValidation");
				Assertion.assertBytext(page, Portal_ObjectRepository.vehicleInfo_FreightVehicleSectionTitle, "最大積載量が2.0tを超えるお車、または、車検証上の「車体の形状」に「トラクタ」と記載されているお車ですか？");
				Assertion.assertIsChecked(page, Portal_ObjectRepository.vehicleInfo_FreightVehicleLoadCapacitySection+"//span[text()='いいえ']");
				Report.logger.pass("By default 'いいえ' button is selected for Freight vehicle");
				common.clickAction(page, Portal_ObjectRepository.vehicleInfo_FreightVehicleLoadCapacitySection+"//span[text()='はい']");
				Assertion.assertIsAttached(page, Portal_ObjectRepository.vehicleInfo_FreightVehicleModlePopup);
				common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.vehicleInfo_FreightVehicleModlePopup, testCaseId, "Freight use vehicle Model pop-up is displayed after clicking on 'はい' button", "FreightVehicleModlePopup");
				Assertion.assertBytext(page, Portal_ObjectRepository.vehicleInfo_FreightVehicleModlePopup, "最大積載量が2.0t超、または、車検証上の「車体の形状」に「トラクタ」と記載されている普通貨物車の場合、お申込みいただけません。お取扱い範囲について");
				common.clickAction(page, "//*[text()='閉じる']");
			}
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseId, "There is problem in Freight vehicle section validation", "Failed_FreightVehicleSectionValidation", false);
		}

	}

	public void vehicleInfoOwnerIntruptionOtherModelContentPopup(Page page,String testCaseId,String imageName) throws IOException {
		try {

			common.clickAction(page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection+"//span[contains(text(),'"+imageName+"')]");
			if(CORPORATE_URL) {
				Assertion.assertIsAttached(page, Portal_ObjectRepository.vehicleInfo_SMEOwnerIntruptionOtherModelContentPopup);
			}else {
				Assertion.assertIsAttached(page, Portal_ObjectRepository.vehicleInfo_PersonalOwnerIntruptionOtherModelContentPopup);
			}
			common.passStatusWithScreenshots(page,testCaseId, "Model content pop-up after selecting on "+imageName+" image",imageName+"ModelContentPop-up",false);
			common.clickAction(page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page,testCaseId, "There is problem in Model content pop-up is after selecting on "+imageName+" image","Failed_"+imageName+"ModelContentPop-up",false);
		}

	}

	public void vehicleInfoOwnerInterruptionIndiModelContentPopupValidation(Page page,String testCaseId,String imageName) throws IOException {
		try {

			common.clickAction(page, Portal_ObjectRepository.vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection+"//span[contains(text(),'"+imageName+"')]");
			if(CORPORATE_URL) {
				Assertion.assertIsAttached(page, Portal_ObjectRepository.vehicleInfo_SMEOwnerInterruptionIndiModelContentPopup);
				Report.logger.pass("Model content pop-up displayed after selecting on "+imageName+" image");
				Assertion.assertBytext(page,"("+Portal_ObjectRepository.vehicleInfo_SMEOwnerInterruptionIndiModelContentPopup+"//div[2]//span)[1]", "0120-945-072");
				Report.logger.pass("Phone number prsent as '0120-945-072' inside model content pop-up for Corporate flow");

			}else {
				Assertion.assertIsAttached(page, Portal_ObjectRepository.vehicleInfo_PersonalOwnerInterruptionIndiModelContentPopup);
				Report.logger.pass("Model content pop-up displayed after selecting on "+imageName+" image");
				Assertion.assertBytext(page,"("+Portal_ObjectRepository.vehicleInfo_PersonalOwnerInterruptionIndiModelContentPopup+"//div[2]//a)[1]", "0120-000-194");
				Report.logger.pass("Phone number prsent as '0120-000-194' inside model content pop-up for Personal flow");
			}
			common.passStatusWithScreenshots(page,testCaseId, "Model content pop-up after selecting on "+imageName+" image",imageName+"ModelContentPop-up",false);
			common.clickAction(page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page,testCaseId, "There is problem in Model content pop-up is after selecting on "+imageName+" image","Failed_"+imageName+"ModelContentPop-up",false);
		}

	}
}
