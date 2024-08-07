package org.axa.portal.page;

import java.io.IOException;
import java.util.List;
import org.axa.framework.Assertion;
import org.axa.framework.CommonFunctions;
import org.axa.framework.Portal_ObjectRepository;
import org.axa.framework.Report;
import org.axa.portal.validation.ADJ_portal_CommonValidation;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ADJ_portal_policyHolderInformationScreen {

	utility util = new utility();
	CommonFunctions common = new CommonFunctions();
	ADJ_portal_CommonValidation validation=new ADJ_portal_CommonValidation();
	ADJ_portal_aboutMainDriverAndPolicyPlanScreen mainDriver=new ADJ_portal_aboutMainDriverAndPolicyPlanScreen();
	public static boolean CORPORATE_URL=false;

	public ParameterOfPolicyHolderInformationPage contractorInfo(String testCaseId) throws IOException {
		ParameterOfPolicyHolderInformationPage para=utility.contractorInfoMap.get(testCaseId);
		return para;
	}

	public void certificateSelection(Page page,String testCaseId) throws IOException {
		if(!CORPORATE_URL) {
			switch(contractorInfo(testCaseId).getContractCertificateType()) {
			case "PDF Certificate":
				common.clickAction(page, Portal_ObjectRepository.pdfCertificateCheckbox);
				break;
			case "Insurance Certificate":
				common.clickAction(page, Portal_ObjectRepository.insuranceCertificateCheckbox);
				break;
			default:
				break;

			}
		}else if(CORPORATE_URL) {
			//Report.logger.info("BAU-18914 validation");
			//Assertion.assertIsNotAttached(page, Portal_ObjectRepository.TnC_ContractConfirmationDetailsSection);

			/*
			 * Report.logger.info("BAU-25419 validation"); Assertion.assertIsAttached(page,
			 * Portal_ObjectRepository.TnC_ContractConfirmationDetailsSection);
			 * Report.logger.
			 * pass("Contract details section is not displayed for Corporate flow");
			 * Assertion.assertIsNotAttached(page,
			 * Portal_ObjectRepository.TnC_EInsuranceCertificatesection); Report.logger.
			 * pass("E Insurance certificate section is not displayed for Corporate flow");
			 * Assertion.assertIsNotAttached(page,
			 * Portal_ObjectRepository.pdfCertificateCheckbox);
			 * Assertion.assertIsNotAttached(page,
			 * Portal_ObjectRepository.insuranceCertificateCheckbox);
			 */
		}

	}

	public void termAndConditionsPageTextUrlValidation(Page page,String testCaseId) throws IOException {
		try {
			Report.logger.info("BAU-18913 validation");
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.termAndConditionImpMatters, "href", "/auto/pop/auto_contract/20240313.html");
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.termAndConditionImpMatters, testCaseId, "重要事項説明書 contains URL: '"+common.getAttribute(page, Portal_ObjectRepository.termAndConditionImpMatters, "href")+"'", "重要事項説明書UrlValidation");
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.termAndConditionContractGuideLink, "href", "/pdf/auto/auto_conditions_202403.pdf");
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.termAndConditionContractGuideLink, testCaseId, "ご契約のしおり/普通保険約款  PDFダウンロード contains URL: "+common.getAttribute(page, Portal_ObjectRepository.termAndConditionContractGuideLink, "href"), "ご契約のしおりUrlValidation");
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseId,"URL validation of Term and condition page is not completed successfully","Failed_UrlTextValidation",true);

		}
		catch(Exception error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseId,"URL validation of Term and condition page is not completed successfully","Failed_UrlTextValidation",true);

		}
	}

	public void termAndConditionsConfirmationPage(Page page,String testCaseId) throws IOException {
		certificateSelection(page, testCaseId);
		termAndConditionsPageTextUrlValidation(page, testCaseId);

		if(!page.locator(Portal_ObjectRepository.termandConditionsCheckbox).isChecked()) {
			common.clickAction(page, Portal_ObjectRepository.termandConditionsCheckbox);
		}

		common.passStatusWithScreenshots(page, testCaseId,"Term and conditions selected successfully","TermAndCondition",true);
		common.clickAction(page, Portal_ObjectRepository.proceedNext);
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		common.passStatusWithScreenshots(page, testCaseId,"Successfully proceeded to contractor information confirmation page","proceededToContractorInfoConfirmationPage",true);
	}



	public void functionalValidationOfPolicyHolderInformationPage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.PageLogoValidation(page, testCaseID);
			validation.quotationValidation(page, testCaseID);
			validation.chatBotValidation(page, testCaseID);
			validation.headerImageIconValidationAndNavigation(page, testCaseID);
			//legalEntityValueValidation(page, testCaseID);
			//inputFieldValidationOfContractorPage(page, testCaseID);
			menuOptionValidationOfPolicyHolderInformationPage(page, testCaseID);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Functional Validation of Policy Holder Information page is not completed", "Failed_PolicyHolderInfoPageFunctionalValidation", true);
		}
	}

	public void functionalValidationOfTandCpage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.PageLogoValidation(page, testCaseID);
			validation.quotationValidation(page, testCaseID);
			validation.chatBotValidation(page, testCaseID);
			validation.headerImageIconValidationAndNavigation(page, testCaseID);
			menuOptionValidationOfTandCPage(page, testCaseID);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Functional Validation of Policy Holder Information page is not completed", "Failed_PolicyHolderInfoPageFunctionalValidation", true);
		}
	}

	public void legalEntityPositionValidation(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			if(utility.SME_ContractorInfoMap.get(testCaseID).getCorporateStatusPosition().equals("法人名の後")) {
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_corporateName+"//../div/span", utility.SME_ContractorInfoMap.get(testCaseID).getLegalEntityType());
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_corporateNameKana+"//../div/span", utility.SME_ContractorInfoMap.get(testCaseID).getLegalEntityType());	
			}else {
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_corporateName+"//../div/span", utility.SME_ContractorInfoMap.get(testCaseID).getLegalEntityType());
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_corporateNameKana+"//../div/span", utility.SME_ContractorInfoMap.get(testCaseID).getLegalEntityType());
			}
			common.passStatusWithScreenshots(page, testCaseID, "Legal entity position is correct based on status position selection", "legalEntityPositionValidation", false);

		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "There is problem with legal entity postion test display", "Failed_legalEntityPositionValidation", false);
		}
	}
	public void menuOptionValidationOfPolicyHolderInformationPage(Page page,String testCaseID) throws IOException, InterruptedException {
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
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuContractor);
			common.passStatusWithScreenshots(page, testCaseID, "Menu options of Policy Holder Information page is validated successfully" , "MenuOptionValidationPolicyHolderInfoPage", true);
			common.clickAction(page, Portal_ObjectRepository.closeMenuButton);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Menu options of Policy Holder Information page is not validated completely", "Failed_MenuOptionValidationPolicyHolderInfoPage", true);
		}
	}

	public void errorMessageAndDefaultCheckboxSelectionValidationOfTandCPage(Page page,String testCaseId) throws IOException {
		common.clickAction(page, Portal_ObjectRepository.proceedNext);
		common.waitForSelector(page, Portal_ObjectRepository.termandConditionsCheckbox);
		try {
			if(CORPORATE_URL) {
				Assertion.assertIsNotAttached(page, Portal_ObjectRepository.insuranceCertificateCheckbox);
				Assertion.assertIsNotAttached(page, Portal_ObjectRepository.pdfCertificateCheckbox);
				Assertion.assertIsNotChecked(page, Portal_ObjectRepository.termandConditionsCheckbox);

			}else {
				Assertion.assertIsNotChecked(page, Portal_ObjectRepository.insuranceCertificateCheckbox);
				Assertion.assertIsNotChecked(page, Portal_ObjectRepository.termandConditionsCheckbox);
				//common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.insuranceCertificateCheckbox, testCaseId, "By default Checkbox for Insurnace Certificate is not checked", "InsuranceCheckboxDefaultValidation");
				Assertion.assertBytext(page, Portal_ObjectRepository.insuranceCertificateCheckboxError,"次へ進む前に、いずれかのチェックボックスをご選択ください");
				Assertion.assertBytext(page, Portal_ObjectRepository.termandConditionsCheckboxError,"次へ進む前に、重要事項説明書をご確認の上、チェックボックスを入力ください");
			}
			common.passStatusWithScreenshots(page, testCaseId, "By default check box is not selected and Error for all Mandetory field of Term and Condition Page is displaying", "TermAndConditionCheckboxErrorValidation", true);

		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseId, "By default check box is not selected and Error for all Mandetory field of Term and Condition Page is not displaying", "Failed_TermAndConditionCheckboxErrorValidation", true);
			System.out.println(error);
		}
	}

	public void errorMessageAndDefaultCheckboxSelectionValidationOfContractConfirmationPage(Page page,String testCaseId,String errorMessage) throws IOException {
		common.waitForSelector(page, Portal_ObjectRepository.infoConfirmationCheckbox);
		common.clickAction(page, Portal_ObjectRepository.proceedNext);

		try {
			Assertion.assertIsNotChecked(page, Portal_ObjectRepository.infoConfirmationCheckbox);
			Assertion.assertBytext(page, Portal_ObjectRepository.infoConfirmationCheckboxError,errorMessage);

			common.passStatusWithScreenshots(page, testCaseId, "By default check box is not selected and Error for all Mandetory field as: "+errorMessage+" of contract confirmation Page is displaying", "contractConfirmCheckboxErrorValidation", true);

		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseId, "By default check box is not selected and Error for all Mandetory field as: "+errorMessage+"of contract confirmation Page is not displaying", "Failed_contractConfirmCheckboxErrorValidation", true);
			System.out.println(error);
		}
	}

	public void menuOptionValidationOfTandCPage(Page page,String testCaseID) throws IOException, InterruptedException {
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
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuContractor);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuMatterConfirmation);
			common.passStatusWithScreenshots(page, testCaseID, "Menu options of Term and Condition page is validated successfully" , "MenuOptionValidationOfTandCPage", true);
			common.clickAction(page, Portal_ObjectRepository.closeMenuButton);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Menu options of Term and Condition page is not validated completely", "Failed_MenuOptionValidationOfTandCPage", true);
		}
	}

	public void inputFieldValidationOfContractorPage(Page page,String testCaseId) {
		String maxCharector="先先先先先先先先先先先先先先先先先先先先先先先AAAAAAAAAAAAAAAA９９９９９９９９９９９９９９９９９カカカカカカカカカカカカカカカカカカ";
		String kanaMaxCharector="カカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカカ";
		String phoneDigit="887766552434662533443243";
		if(CORPORATE_URL) {
			try {
				//if(utility.SME_ContractorInfoMap.get(testCaseId).getLegalEntityType().equals("その他")){
					common.selectDropdownByText(page, Portal_ObjectRepository.contractor_legalEntityType, "その他");
					common.inputFieldLimitValidation(page, Portal_ObjectRepository.contractor_CorporateOther,maxCharector, 18);	
				//}
				common.inputFieldLimitValidation(page, Portal_ObjectRepository.contractor_corporateName, maxCharector, 21);
				common.inputFieldLimitValidation(page, Portal_ObjectRepository.contractor_corporateNameKana, kanaMaxCharector, 30);
				common.inputFieldLimitValidation(page, Portal_ObjectRepository.contractor_representativeTitle, maxCharector, 14);
				common.inputFieldLimitValidation(page, Portal_ObjectRepository.contractor_representativeTitleKana, kanaMaxCharector, 30);
				common.inputFieldLimitValidation(page, Portal_ObjectRepository.contractor_lastNameKanji, maxCharector, 7);
				common.inputFieldLimitValidation(page, Portal_ObjectRepository.contractor_FirstNameKanji, maxCharector, 7);
				common.inputFieldLimitValidation(page, Portal_ObjectRepository.contractor_lastNameFurigana, kanaMaxCharector, 30);
				common.inputFieldLimitValidation(page, Portal_ObjectRepository.contractor_FirstNameFurigana, kanaMaxCharector, 30);
				common.inputFieldLimitValidation(page, Portal_ObjectRepository.postalCode, phoneDigit, 7);
				common.clickAction(page, Portal_ObjectRepository.searchAddress);
				Assertion.assertIsEnabled(page, Portal_ObjectRepository.unableToFindPostalCode);
				common.passStatusWithScreenshots(page, testCaseId, "pop-up regarding unable to find postal address is displayed", "unableToFindPostalAddress", false);
				common.clickAction(page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
				common.inputFieldLimitValidation(page, Portal_ObjectRepository.contractor_RepresentativeMobileNumber, phoneDigit, 11);
				common.inputFieldLimitValidation(page, Portal_ObjectRepository.contractor_CorporateMobileNumber	, phoneDigit, 11);
				common.passStatusWithScreenshots(page, testCaseId, "max limit for all the fields of corporate is completed", "maxLimitValidationOfSMEContractPage", true);
			}catch(AssertionError error) {
				System.out.println(error);
				common.passStatusWithScreenshots(page, testCaseId, "max limit for all the fields of corporate is not completed", "Failed_maxLimitValidationOfSMEContractPage", true);
			}
		}
	}

	public void ErrorMessageValidationOfPolicyHolderInformationPage(Page page,String testCaseId,String ErrorMessage) throws IOException {
		CORPORATE_URL=Assertion.assertURLContains(page, "sme");

		common.waitForSelector(page, Portal_ObjectRepository.emailaddress);
		common.clickAction(page,Portal_ObjectRepository.proceedNext);
		try {

			if(CORPORATE_URL) {
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_legalEntityType+"//..//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_statusPosition+"//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_corporateName+"//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_corporateNameKana+"//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_representativeTitle+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_representativeTitleKana+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_lastNameKanji+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_FirstNameKanji+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_lastNameFurigana+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_FirstNameFurigana+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, "("+Portal_ObjectRepository.contractor_delegateInfoSection+"//following-sibling::div/div)[1]", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.postalCode+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_RepresentativeMobileNumber+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.contractor_CorporateMobileNumber+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.emailaddress+"//..//following-sibling::div/div", ErrorMessage);
			}else {
				Assertion.assertBytext(page, Portal_ObjectRepository.lastNameKanji+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.firstNameKanji+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.lastNameFurigana+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.firstNameFurigana+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.postalCode+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.mobileNumber+"//..//following-sibling::div/div", ErrorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.emailaddress+"//..//following-sibling::div/div", ErrorMessage);
				common.passStatusWithScreenshots(page, testCaseId, "Error for all Mandetory field of Policy Holder Information Page is displaying as:'"+ErrorMessage+"'", "PolicyHolderInfoPageFieldErrorValidation", true);
				try {
					Assertion.assertIsChecked(page, Portal_ObjectRepository.emailPermissionCheckbox);
					common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.emailPermissionCheckbox, testCaseId, "Check Box is by default selected", "PolicyHolderPageCheckBoxValidation");

				}catch(AssertionError error) {
					common.failStatusWithScreenshots(page, testCaseId, "By default check box of the Policy Holder Information page is not checked", "Failed_PolicyHolderInfoPageCheckbox", true);
				}
			}
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseId, "Expected Error Message:'"+ErrorMessage+"' of one or all field of Policy Holder Information page is different", "Failed_PolicyHolderInfoPageFieldErrorValidation", true);
			System.out.println(error);
		}

	}

	public void legalEntityValueValidation(Page page,String testCaseId) {
		String[] definedLegalEntity= {"-","株式会社","合同会社","有限会社","合資会社","合名会社","医療法人","一般財団法人","一般社団法人","宗教法人","社会福祉法人","特定非営利活動法人","弁護士法人","行政書士法人","司法書士法人","税理士法人","社会保険労務士法人","その他"};
		List<Locator>entityList=page.locator( Portal_ObjectRepository.contractor_legalEntityType+"//option").all();
		int i=0;
		try {
			for(Locator l:entityList) {
				System.out.println(l.getAttribute("value")+": "+l.textContent());
				Assertion.assertBytext(l, definedLegalEntity[i]);
				i++;
			}
			common.clickAction(page, Portal_ObjectRepository.contractor_legalEntityType);
			common.passStatusWithScreenshots(page, testCaseId,"List of Legal entity dropdown value is matching with predefined value","legalEntityValidation",false);
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseId,"List of Legal entity dropdown value is not matching with predefined value","Failed_legalEntityValidation",false);
		}
	}

	public void accountMergePopupValidation(Page page,String testCaseId,String popAction) {
		if(common.isElementPresent(page,Portal_ObjectRepository.accountMergePopupTitle)) {
			common.passStatusWithScreenshots(page,testCaseId, "Register account dialog is displayed","EmailRegisterDialog",true);
			if(CORPORATE_URL) {
				if(popAction.equals("Yes")) {

				}else {
					common.clickAction(page, "//label[@data-aem='AccountMergeModalSME_Close_StyledButton']");
				}
			}else {
				if(popAction.equals("No")) {
					common.clickAction(page, "//*[text()='いいえ']");
				}else {
					common.clickAction(page, "//*[text()='いいえ']");	
				}
			}	
		}else {
			Report.logger.pass("Register account dialog is not displayed");
			//common.passStatusWithScreenshots(page,testCaseId, "Register account dialog is not displayed","EmailRegisterDialog",true);
		}

	}

	public void dataEntryOnPolicyHolderInformationPage(Page page,String testCaseId) throws IOException, InterruptedException {
		//CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		if(utility.homePageMap.get(testCaseId).getMemberType().equals("Non Login Member")) {

			if(CORPORATE_URL) {
				common.selectDropdownByText(page, Portal_ObjectRepository.contractor_legalEntityType, utility.SME_ContractorInfoMap.get(testCaseId).getLegalEntityType());
				if(utility.SME_ContractorInfoMap.get(testCaseId).getLegalEntityType().equals("その他")){
					common.enterText(page, Portal_ObjectRepository.contractor_CorporateOther, "全角9文字以内");	
				}
				common.clickAction(page, Portal_ObjectRepository.contractor_companyInfoSection+"//span[text()='"+utility.SME_ContractorInfoMap.get(testCaseId).getCorporateStatusPosition()+"']");
				legalEntityPositionValidation(page, testCaseId);
				common.enterText(page, Portal_ObjectRepository.contractor_corporateName, utility.SME_ContractorInfoMap.get(testCaseId).getCorporateName());
				common.enterText(page, Portal_ObjectRepository.contractor_corporateNameKana, utility.SME_ContractorInfoMap.get(testCaseId).getCorporateNameKana());
				common.enterText(page, Portal_ObjectRepository.contractor_representativeTitle, utility.SME_ContractorInfoMap.get(testCaseId).getRepresentativeTitle());
				common.enterText(page, Portal_ObjectRepository.contractor_representativeTitleKana, utility.SME_ContractorInfoMap.get(testCaseId).getRepresentativeTitleKana());
				common.enterText(page, Portal_ObjectRepository.contractor_lastNameKanji, utility.SME_ContractorInfoMap.get(testCaseId).getCorporateLastNameKanji());
				common.enterText(page, Portal_ObjectRepository.contractor_FirstNameKanji, utility.SME_ContractorInfoMap.get(testCaseId).getCorporateFirstNameKanji());
				common.enterText(page, Portal_ObjectRepository.contractor_lastNameFurigana, utility.SME_ContractorInfoMap.get(testCaseId).getCorporateLastNameFurigana());
				common.enterText(page, Portal_ObjectRepository.contractor_FirstNameFurigana, utility.SME_ContractorInfoMap.get(testCaseId).getCorporateFirstNameFurigana());
				common.clickAction(page, Portal_ObjectRepository.contractor_delegateInfoSection+"//span[text()='"+utility.SME_ContractorInfoMap.get(testCaseId).getRepresentativePersona()+"']");
				if(utility.SME_ContractorInfoMap.get(testCaseId).getRepresentativePersona().equals("（委任を受けている方＊）")) {
					common.enterText(page, Portal_ObjectRepository.contractor_delegateLastNameKanji, utility.SME_ContractorInfoMap.get(testCaseId).getDelegateLastNameKanji());
					common.enterText(page, Portal_ObjectRepository.contractor_delegateFirstNameKanji, utility.SME_ContractorInfoMap.get(testCaseId).getDelegateFirstNameKanji());
					common.enterText(page, Portal_ObjectRepository.contractor_delegateLastNameKana, utility.SME_ContractorInfoMap.get(testCaseId).getDelegateLastNameKana());
					common.enterText(page, Portal_ObjectRepository.contractor_delegateFirstNameKana, utility.SME_ContractorInfoMap.get(testCaseId).getDelegateFirstNameKana());
				}

				common.enterText(page, Portal_ObjectRepository.postalCode, utility.SME_ContractorInfoMap.get(testCaseId).getCorporatePinCode());
				common.clickAction(page, Portal_ObjectRepository.searchAddress);

				if(!utility.SME_ContractorInfoMap.get(testCaseId).getCorporateAddress().isEmpty()) {
					Thread.sleep(2000);
					common.elementVisibility(page, Portal_ObjectRepository.confirmAddress);
					List<Locator>list=page.locator("//label[contains(@for,'city_address')]").all();
					for(Locator element:list) {
						String addressText=element.textContent();
						if(addressText.contains(utility.SME_ContractorInfoMap.get(testCaseId).getCorporateAddress())) {
							element.click();
							common.passStatusWithScreenshots(page, testCaseId,"Address selection pop is displayed successfully","AddressSelectionPop-up",false);
							break;
						}
					}
				}else {
					common.clickAction(page, "//label[@for='city_address_0']");
					String text=common.getText(null, "//label[@for='city_address_3']");
					System.out.println(text);
				}
				common.clickAction(page, Portal_ObjectRepository.confirmAddress);
				if(!utility.SME_ContractorInfoMap.get(testCaseId).getDoorNumber().isEmpty() || !utility.SME_ContractorInfoMap.get(testCaseId).getDoorNumber().equals(null)) {
					common.enterText(page, Portal_ObjectRepository.homeNumber, utility.SME_ContractorInfoMap.get(testCaseId).getDoorNumber());
				}
				if(!utility.SME_ContractorInfoMap.get(testCaseId).getBuildingName().isEmpty() || !utility.SME_ContractorInfoMap.get(testCaseId).getBuildingName().equals(null)) {
					common.enterText(page, Portal_ObjectRepository.buildingName, utility.SME_ContractorInfoMap.get(testCaseId).getBuildingName());
				}

				common.enterText(page, Portal_ObjectRepository.contractor_RepresentativeMobileNumber, utility.SME_ContractorInfoMap.get(testCaseId).getRepresentativePhoneNumber());
				common.enterText(page, Portal_ObjectRepository.contractor_CorporateMobileNumber, utility.SME_ContractorInfoMap.get(testCaseId).getContactNumber());
				common.enterText(page, Portal_ObjectRepository.emailaddress, utility.SME_ContractorInfoMap.get(testCaseId).getEmailAddress());
			}else {

				common.enterText(page, Portal_ObjectRepository.lastNameKanji, contractorInfo(testCaseId).getLastNameKanji());
				common.enterText(page, Portal_ObjectRepository.firstNameKanji, contractorInfo(testCaseId).getFirstNameKanji());
				common.enterText(page, Portal_ObjectRepository.lastNameFurigana, contractorInfo(testCaseId).getLastNameFurigana());
				common.enterText(page, Portal_ObjectRepository.firstNameFurigana, contractorInfo(testCaseId).getFirstNameFurigana());
				common.enterText(page, Portal_ObjectRepository.mobileNumber, contractorInfo(testCaseId).getMobileNumber());
				common.enterText(page, Portal_ObjectRepository.postalCode, contractorInfo(testCaseId).getPinCode());
				common.clickAction(page, Portal_ObjectRepository.searchAddress);

				if(!contractorInfo(testCaseId).getAddressName().isEmpty()) {
					Thread.sleep(2000);
					common.elementVisibility(page, Portal_ObjectRepository.confirmAddress);
					List<Locator>list=page.locator("//label[contains(@for,'city_address')]").all();
					for(Locator element:list) {
						String addressText=element.textContent();
						if(addressText.contains(contractorInfo(testCaseId).getAddressName())) {
							element.click();
							common.passStatusWithScreenshots(page, testCaseId,"Address selection pop is displayed successfully","AddressSelectionPop-up",false);
							break;
						}
					}
				}else {
					common.clickAction(page, "//label[@for='city_address_0']");
					String text=common.getText(null, "//label[@for='city_address_3']");
					System.out.println(text);
				}
				common.clickAction(page, Portal_ObjectRepository.confirmAddress);
				if(!contractorInfo(testCaseId).getDoorNumber().isEmpty() || !contractorInfo(testCaseId).getDoorNumber().equals(null)) {
					common.enterText(page, Portal_ObjectRepository.homeNumber, contractorInfo(testCaseId).getDoorNumber());
				}
				if(!contractorInfo(testCaseId).getBuildingName().isEmpty() || !contractorInfo(testCaseId).getBuildingName().equals(null)) {
					common.enterText(page, Portal_ObjectRepository.buildingName, contractorInfo(testCaseId).getBuildingName());
				}

				common.enterText(page, Portal_ObjectRepository.emailaddress, contractorInfo(testCaseId).getEmailAddress());
			}
			common.passStatusWithScreenshots(page, testCaseId,"Policy holder information entered successfully","policyHolderInformation",true);
		}
		common.clickAction(page, Portal_ObjectRepository.proceedNext);
		//page.locator(Portal_ObjectRepository.proceedNext).dblclick();
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
	}


	public void e2eFlowOfPolicyHolderInformation(Page page,String testCaseId) throws IOException, InterruptedException {
		dataEntryOnPolicyHolderInformationPage(page, testCaseId);
		accountMergePopupValidation(page, testCaseId, "No");
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		common.passStatusWithScreenshots(page, testCaseId,"Successfully proceeded to Term and conditions page","proceededToTermAndConditionsPage",true);
		ADJ_portal_ContractConfirmationScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");

	}
}
