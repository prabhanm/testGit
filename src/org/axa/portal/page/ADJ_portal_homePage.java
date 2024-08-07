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


public class ADJ_portal_homePage {

	CommonFunctions common=new CommonFunctions();
	utility util=new utility();
	ADJ_portal_CommonValidation validation=new ADJ_portal_CommonValidation();
	Portal_URL_setup URL=new Portal_URL_setup();
	public static String quotationNumber;
	public static String PURCHASE_FLOW_TYPE;
	public static boolean CORPORATE_URL=false;

	public ParameterOfHomeAndQuotationPage homePage(String testCaseID) throws IOException {
		ParameterOfHomeAndQuotationPage para=utility.homePageMap.get(testCaseID);
		return para; 
	}

	/**
	 *@Method Implementation to spit string
	 **/
	public String[] splitInsuranceFlowType(String insuranceFlowType) {
		String[] data=insuranceFlowType.split("-->");
		return data;
	}


	public void navigateToCorporateFlow(Page page,String testCaseID,String insuranceFlowType) throws IOException {

		if(insuranceFlowType.equals("Corporate")) {
			//common.clickAction(page, Portal_ObjectRepository.closeBanner);
			common.acceptCookies(page, Portal_ObjectRepository.closeBanner);
			common.passStatusWithScreenshots(page,testCaseID, "SuccessFully navigated to SME flow Top Page ","SMETopPage",false);
		}
	}

	/**
	 *@throws InterruptedException 
	 * @Method Implementation for Emma login
	 *This method is also used to validate login text/sentence
	 **/
	public void loginToEmmaApplication(Page page,String testCaseID) throws IOException, InterruptedException {

		//loginTextboxValidation(page, testCaseID);

		if(utility.homePageMap.get(testCaseID).getMemberType().equals("Login Member")) {
			common.clickAction(page, Portal_ObjectRepository.emmaLoginLink);
			common.waitForSelector(page, Portal_ObjectRepository.emmaLoginID);
			common.passStatusWithScreenshots(page,testCaseID, "User successfully navigated to Emma login page", "LoginPageNavigation",true);
			if(!utility.homePageMap.get(testCaseID).getInsurnaceFlowType().equals("Individual")) {
				common.enterText(page, Portal_ObjectRepository.emmaLoginID, utility.property.getProperty("CorporateEmmaLoginId"));
				common.enterText(page, Portal_ObjectRepository.emmaLoginPassword, utility.property.getProperty("CorporateEmmaPassword"));
				CORPORATE_URL=true;
			}else {
				common.enterText(page, Portal_ObjectRepository.emmaLoginID, utility.property.getProperty("PersonalEmmaLoginId"));
				common.enterText(page, Portal_ObjectRepository.emmaLoginPassword, utility.property.getProperty("PersonalEmmaPassword"));
				CORPORATE_URL=false;
			}
			common.passStatusWithScreenshots(page,testCaseID, "User entered login credentilas successfully ","loginPage",false);
			common.clickAction(page, Portal_ObjectRepository.emmaLoginButton);
			Thread.sleep(5000);
			common.passStatusWithScreenshots(page,testCaseID,"OTP Authentication screen displayed successfully ","otpAuthenticationScreen",true);
			//page.reload();
			Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
			Assertion.assertIsVisible(page, Portal_ObjectRepository.proceedNext);
			common.passStatusWithScreenshots(page,testCaseID,"User successfully navigated to AMP ContractType page ","AMPHomePage",true);
			//CORPORATE_URL=Assertion.assertURLContains(page, "sme");
			/*
			 * common.getText(page, Portal_ObjectRepository.startNewQuote);
			 * common.passStatusWithScreenshots(page,
			 * testCaseID,"User successfully navigated to Home page ","HomePage",true);
			 * Assertion.assertIsDisabled(page, Portal_ObjectRepository.emmaLoginBox)
			 */;
		}
	} 
	
	/**
	 *@Method Implementation to for Emma login ID recovery
	 *
	 **/
	
	public void emmaIDRecovery(Page page,String testCaseID) throws IOException, InterruptedException {

		//loginTextboxValidation(page, testCaseID);

		if(utility.homePageMap.get(testCaseID).getMemberType().equals("Login Member")) {
			common.clickAction(page, Portal_ObjectRepository.emmaLoginLink);
			common.waitForSelector(page, Portal_ObjectRepository.emmaLoginID);
			common.passStatusWithScreenshots(page,testCaseID, "User successfully navigated to Emma login page", "LoginPageNavigation",true);
			common.clickAction(page, Portal_ObjectRepository.emmaUserIdRecoveryLink);
			Assertion.assertIsChecked(page, Portal_ObjectRepository.emmaUserIdRecovery_Personal);
			Report.logger.pass("By default '個人のお客さまはこちら' button is selected");
			if(utility.homePageMap.get(testCaseID).getInsurnaceFlowType().equals("Corporate")) {
				Assertion.assertIsChecked(page, Portal_ObjectRepository.emmaUserIdRecovery_Corporate);
				Report.logger.pass("By default '法人のお客さまはこちら' button is selected");
			}
			common.enterText(page, Portal_ObjectRepository.emmaUserIdRecovery_PolicyNumber,utility.emmaLoginMap.get(testCaseID).getPolicyNumber());
			common.enterText(page, Portal_ObjectRepository.emmaUserIdRecovery_lastName,utility.emmaLoginMap.get(testCaseID).getLastName());
			common.enterText(page, Portal_ObjectRepository.emmaUserIdRecovery_firstName,utility.emmaLoginMap.get(testCaseID).getFirstName());
			common.enterText(page, Portal_ObjectRepository.emmaUserIdRecovery_PhoneNumber,utility.emmaLoginMap.get(testCaseID).getMobileNumber());
			common.passStatusWithScreenshots(page,testCaseID, "Details of UserID recovery entered successfully ","userIdRecoveryDetails",true);
			common.clickAction(page, Portal_ObjectRepository.emmaUserIdRecovery_ProceedButton);
			Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		}
	}
	
	/**
	 *@Method Implementation to for Emma password ID recovery
	 *
	 **/
	
	public void emmaPasswordRecovery(Page page,String testCaseID) throws IOException, InterruptedException {

		if(utility.homePageMap.get(testCaseID).getMemberType().equals("Login Member")) {
			common.clickAction(page, Portal_ObjectRepository.emmaLoginLink);
			common.waitForSelector(page, Portal_ObjectRepository.emmaLoginID);
			common.passStatusWithScreenshots(page,testCaseID, "User successfully navigated to Emma login page", "LoginPageNavigation",true);
			common.clickAction(page, Portal_ObjectRepository.emmaPasswordRecoveryLink);
			common.waitForSelector(page, Portal_ObjectRepository.emmaPasswordRecovery_UserID);
			common.enterText(page, Portal_ObjectRepository.emmaPasswordRecovery_UserID,utility.emmaLoginMap.get(testCaseID).getLoginID());
		
			common.passStatusWithScreenshots(page,testCaseID, "UserID has been entered for password recovery ","PasswordRecoveryDetails",true);
			common.clickAction(page, Portal_ObjectRepository.emmaPasswordRecovery_ProceedButton);
			Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		}
	}

	/**
	 *@Method Implementation to validate login text/sentence
	 *
	 **/
	public void loginTextboxValidation(Page page,String testCaseID) throws IOException {
		try {
			if(CORPORATE_URL==false) {
				Assertion.assertIsEnabled(page, Portal_ObjectRepository.emmaLoginBox);
				//System.out.println(page.locator("("+Portal_ObjectRepository.emmaLoginBox+"//span)[1]").textContent());
				//Assertion.assertBytextContains(page, "("+Portal_ObjectRepository.emmaLoginBox+"//span)[1]", "当社でご契約中の車・バイク保険がある場合、ログイン後のお見積りでお客さまページ複数契約割引の対象になる可能性があります");
				Assertion.assertBytextContains(page, "("+Portal_ObjectRepository.emmaLoginBox+"//span)[1]", "当社でご契約中の車・バイク保険（個人契約）がある場合、ログイン後のお見積りでお客さまページ複数契約割引の対象になる可能性があります。");
				Assertion.assertBytextContains(page, "("+Portal_ObjectRepository.emmaLoginBox+"//span)[2]", "個人契約");
				common.takeScreenShotsOfComponent(page,Portal_ObjectRepository.emmaLoginBox, testCaseID, "Login text section is displayed successfully", "PersonalLogintextsection");
				if(utility.property.getProperty("Environment").equals("-preprod")) {
					Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.emmaLoginLink, "href", "https://www3.axa-direct.co.jp/amp/html/#/auth/login");
				}else {
					Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.emmaLoginLink, "href", "https://www-inssuite"+utility.property.getProperty("Environment")+".web.dev.aws.axa-direct-jp.intraxa/amp/html/#/auth/login");
				}
				common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.emmaLoginBox, testCaseID, "Login Text: '"+common.getText(page, Portal_ObjectRepository.emmaLoginBox+"//span")+"' displayed", "LoginTextBox");
			}else {
				Assertion.assertIsAttached(page, Portal_ObjectRepository.emmaLoginBox);
				System.out.println(common.getText(page, "("+Portal_ObjectRepository.emmaLoginBox+"//span)[1]"));
				Assertion.assertBytextContains(page, "("+Portal_ObjectRepository.emmaLoginBox+"//span)[1]", "当社でご契約中の自動車保険（法人契約）がある場合、ログイン後のお見積りでお客さまページ複数契約割引の対象になる可能性があります。");
				Assertion.assertBytextContains(page, "("+Portal_ObjectRepository.emmaLoginBox+"//span)[2]", "法人契約");
				common.takeScreenShotsOfComponent(page,Portal_ObjectRepository.emmaLoginBox, testCaseID, "Login text section is displayed successfully", "CorporateLogintextsection");
			}

		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseID,"Login box is not present in the page or content has been changed ","Failed_LoginTextBox",true); 
		}	    
	}

	/**
	 *@Method Implementation to start new quote as a login member
	 *
	 **/ 
	public void startNewQuoteFromHomePage(Page page,String testCaseID) throws IOException {
		common.clickAction(page, Portal_ObjectRepository.startNewQuote);
	}

	/**
	 *@Method Implementation to validate Insurance purchase flow text
	 *This method is used to assert whether flow is 個人 or 法人
	 **/
	public void validateFlowText(Page page,String testCaseID) throws IOException {

		if(!CORPORATE_URL) {
			Assertion.assertBytext(page, Portal_ObjectRepository.purchaseFlowText, "【個人のお客さま専用ページ】");
			Assertion.assertBytext(page, Portal_ObjectRepository.purchaseFlowText+"//following-sibling::span", "法人契約は");
			Assertion.assertBytext(page, Portal_ObjectRepository.purchaseFlowText+"//following-sibling::button", "こちら。");
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.purchaseFlowText, testCaseID, "Purchase flow text for Individual flow displayed successfully", "IndividualPurchaseFlowTextValidation");
		}else if (CORPORATE_URL)  {
			Assertion.assertBytext(page, Portal_ObjectRepository.purchaseFlowText, "【法人のお客さま専用ページ】");
			Assertion.assertBytext(page, Portal_ObjectRepository.purchaseFlowText+"//following-sibling::span", "個人契約は");
			Assertion.assertBytext(page, Portal_ObjectRepository.purchaseFlowText+"//following-sibling::button", "こちら。");
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.purchaseFlowText, testCaseID, "Purchase flow text for Corporate flow displayed successfully", "CorporatePurchaseFlowTextValidation");
		}

		/*
		 * if(common.getText(page,
		 * Portal_ObjectRepository.purchaseFlowText).equals("法人契約は")){
		 * common.passStatusWithScreenshots(page,
		 * testCaseID,"Switch link from individual to corporate flow is present"
		 * ,"CorporateSwitchLink",false); PURCHASE_FLOW_TYPE="個人"; }else
		 * if(common.getText(page,
		 * Portal_ObjectRepository.purchaseFlowText).equals("個人契約は")) {
		 * common.passStatusWithScreenshots(page,testCaseID,
		 * "Switch link from corporate to Individual flow is present"
		 * ,"IndividualFlowSwitchLink",false); PURCHASE_FLOW_TYPE="法人"; }
		 */
	}
	/**
	 * 
	 *@Method Implementation to switch from 個人 to 法人 or vice versa
	 *This method is used to validate text displayed in switch flow pop-up
	 *
	 **/
	public void switchToAnotherFlow(Page page,String testCaseID,String insuranceFlowType) throws IOException {

		common.clickAction(page, Portal_ObjectRepository.switchFlowLink);

		if((CORPORATE_URL==false && !insuranceFlowType.equals("Individual"))) {
			System.out.println(common.getText(page, Portal_ObjectRepository.switchFlowPopupContent));
			Assertion.assertBytext(page, Portal_ObjectRepository.switchFlowPopupContent, "法人のお客さま専用お見積りページに切り替えますか？個人のアカウントにログインしている​場合はログアウトされます。");
			//Assertion.assertBytext(page, Portal_ObjectRepository.switchFlowPopupContent+"/span[2]", "に切り替えますか？");
			Report.logger.pass("text '法人のお客さま専用お見積りページに切り替えますか？個人のアカウントにログインしている​場合はログアウトされます。' is displayed inside Corporate--->Personal Switch flow pop-up");

			common.passStatusWithScreenshots(page, testCaseID,"Switch pop-up to navigate Corporate flow is displayed Successfully","CorporateFlowSwitch-pop-up",false);
		}else if ((CORPORATE_URL==true && !insuranceFlowType.equals("Corporate"))) {
			System.out.println(common.getText(page, Portal_ObjectRepository.switchFlowPopupContent));
			Assertion.assertBytext(page, Portal_ObjectRepository.switchFlowPopupContent, "個人のお客さま専用お見積りページに切り替えますか？法人のアカウントにログインしている​場合はログアウトされます。");
			//Assertion.assertBytext(page, Portal_ObjectRepository.switchFlowPopupContent+"/span[1]", "個人のお客さま専用お見積りページ");
			//Assertion.assertBytext(page, Portal_ObjectRepository.switchFlowPopupContent+"/span[2]", "に切り替えますか？");
			Report.logger.pass("text '個人のお客さま専用お見積りページに切り替えますか？法人のアカウントにログインしている​場合はログアウトされます。' is displayed inside Personal--->Corporate Switch flow pop-up");

			common.passStatusWithScreenshots(page, testCaseID,"Switch pop-up to navigate Individual flow is displayed Successfully","IndividualFlowSwitch-pop-up",false);
		}

	}

	/**
	 *@Method Implementation to accept switch flow pop-up
	 **/
	public void acceptSwithFlowPopup(Page page,String testCaseID,String insuranceFlowType) throws IOException {
		common.clickAction(page, Portal_ObjectRepository.corporateFlowPopupAccept);
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);

		if(insuranceFlowType.contains("-->")) {
			common.passStatusWithScreenshots(page, testCaseID,"Successfully navigated to Contract Type page of "+insuranceFlowType+" flow","ContractTypePageOf_"+splitInsuranceFlowType(insuranceFlowType)[1],true);
		}else {
			common.passStatusWithScreenshots(page, testCaseID,"Successfully navigated to Contract Type page of "+insuranceFlowType+" flow","ContractTypePageOf_"+insuranceFlowType,true);
		}
		//common.clickAction(page, Portal_ObjectRepository.closeBanner);
		common.acceptCookies(page, Portal_ObjectRepository.closeBanner);

		CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		//validateFlowText(page, testCaseID);

		/*
		 * if(common.getText(page,
		 * Portal_ObjectRepository.purchaseFlowText).equals("法人契約は")){
		 * PURCHASE_FLOW_TYPE="個人"; }else if(common.getText(page,
		 * Portal_ObjectRepository.purchaseFlowText).equals("個人契約は")) {
		 * PURCHASE_FLOW_TYPE="法人"; }
		 */
	}

	/**
	 *@Method Implementation to reject switch flow pop-up
	 **/
	public void rejectSwithFlowPopup(Page page,String testCaseID) throws IOException {
		common.clickAction(page, Portal_ObjectRepository.corporateFlowPopupDeny);	
		common.passStatusWithScreenshots(page, testCaseID,"pop-up is rejected successfully","RejectFlowPop-up",true);
	}

	/**
	 *@Method Implementation to switch flow based on the Input data sheet criteria
	 **/
	public void insurancePurchaseFlow(Page page,String testCaseID,String insuranceFlowType) throws IOException {

		//CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		if(!insuranceFlowType.equals("Individual")) {
			validateFlowText(page, testCaseID);
			switchToAnotherFlow(page, testCaseID, insuranceFlowType);
			acceptSwithFlowPopup(page, testCaseID, insuranceFlowType);
			//CORPORATE_URL=Assertion.assertURLContains(page, "sme");
			//common.clickAction(page, Portal_ObjectRepository.closeBanner);
			common.acceptCookies(page, Portal_ObjectRepository.closeBanner);

		}
	}

	public String getQuotaionNumber(Page page,String testCaseID,String browserView) throws IOException, InterruptedException {
		String text="";
		if(browserView.equals("Mobile")) {
			common.clickAction(page, Portal_ObjectRepository.menuButtonOfMobileView);
			text=common.getText(page, Portal_ObjectRepository.getQuoteValueOfMobileView);
			common.passStatusWithScreenshots(page, testCaseID,"quoatation as: '"+text+"' is displayed successfully", "mobileMenueSection",false);
			common.clickAction(page, Portal_ObjectRepository.closeMenuButtonOfMobileView);
		}else {
			text=common.getText(page, Portal_ObjectRepository.getPolicyNumber);
			common.passStatusWithScreenshots(page, testCaseID,"quoatation as: '"+text+"' is displayed successfully", "Policynumber",false);
		}
		return text;
	}

	/**
	 *@Method Implementation to select product type
	 *This method is also validating enablity and disability for product type w.r.t flow type
	 **/
	public void productSelection(Page page,String testCaseID,String productType) throws IOException {
		Assertion.assertIsEnabled(page, Portal_ObjectRepository.CarInsuranceOption);
		if(CORPORATE_URL) {

			Assertion.assertIsDisabled(page, Portal_ObjectRepository.bikeInsuranceOption);
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.bikeInsuranceOption, testCaseID, "Bike option is disabled for Corporate flow", "BikeDisabledValidation");
		}else {
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.bikeInsuranceOption);
		}
		Assertion.assertIsChecked(page, Portal_ObjectRepository.CarInsuranceOption);
		if(productType.equals("バイク保険（二輪）")) {
			common.clickAction(page, Portal_ObjectRepository.bikeInsuranceOption);
		}
	}

	/**
	 *@Method Implementation to validate purchase type for Car for both the flow
	 *
	 **/
	public void purchaseTypeValidationForCar(Page page) throws IOException {
		Assertion.assertIsVisible(page, Portal_ObjectRepository.firstTimePurchase);
		Assertion.assertIsVisible(page, Portal_ObjectRepository.otherCompanayEnrollment);
		Assertion.assertIsVisible(page, Portal_ObjectRepository.suspendedCertificate);
	}

	/**
	 *@Method Implementation to validate purchase type for Bike for both the flow
	 *
	 **/
	public void purchaseTypeValidationForBike(Page page,String testCaseID) throws IOException {
		try {
			if(CORPORATE_URL) {
				Assertion.assertIsDisabled(page, Portal_ObjectRepository.bikeInsuranceOption);
				common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.bikeInsuranceOption, testCaseID, "Bike option is disabled for Corporate flow", "BikeDisabledValidation");
			}else {
				common.clickAction(page, Portal_ObjectRepository.bikeInsuranceOption);
				Assertion.assertIsVisible(page, Portal_ObjectRepository.firstTimePurchase);
				Assertion.assertIsVisible(page, Portal_ObjectRepository.otherCompanayEnrollment);
				Assertion.assertIsHidden(page, Portal_ObjectRepository.suspendedCertificate);
				common.passStatusWithScreenshots(page, testCaseID, "Only New Business and Previous Policy Option is displaying for Bike", "purchaseTypeOptionValidationForBike", false);
			}
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseID, "Expected Options are not displaying for Bike", "Failed_purchaseTypeOptionValidationForBike", false);
		}
	}

	/**
	 *@Method Implementation to validate BIKE product type Enability and Disability
	 *
	 **/
	public void validationOfBikeButtonEnability(Page page,String testCaseID,String insuranceFlowType) throws IOException {
		String text=common.getAttribute(page, Portal_ObjectRepository.bikeInsuranceOption,"value");
		if(CORPORATE_URL) {
			Assertion.assertIsDisabled(page, Portal_ObjectRepository.bikeInsuranceOption);
			//Assertion.assertIsAttribute(page, Portal_ObjectRepository.bikeInsuranceOption,"value", "disabled");
			common.passStatusWithScreenshots(page,testCaseID, "Bike button is disabled in corporate flow", "bikeButton_disabled",false);
		}else if(!CORPORATE_URL) {
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.bikeInsuranceOption);
			common.clickAction(page, Portal_ObjectRepository.bikeInsuranceOption);
			common.passStatusWithScreenshots(page,testCaseID, "Bike button is enabled in Individual flow", "bikeButton_enabled",false);
		}else {
			common.failStatusWithScreenshots(page, testCaseID,"Bike button is not "+text+" for "+insuranceFlowType+ " flow", "Failed-BikeButton_Validation",true);

		}
	}

	/**
	 *@Method Implementation to select Insurance purchase type.
	 *
	 **/
	public void insurancePurchaseType(Page page,String testCaseID,String insurnacePurchaseType) throws IOException {

		if(insurnacePurchaseType.equals("初めて自動車保険/バイク保険に加入する")) {
			Assertion.assertIsVisible(page, Portal_ObjectRepository.firstTimePurchase);
			common.clickAction(page, Portal_ObjectRepository.firstTimePurchase);

		}else if(insurnacePurchaseType.equals("現在他社で加入している")) {
			Assertion.assertIsVisible(page, Portal_ObjectRepository.otherCompanayEnrollment);
			common.clickAction(page, Portal_ObjectRepository.otherCompanayEnrollment);

		}else if(insurnacePurchaseType.equals("中断証明書を使用して加入する")) {
			Assertion.assertIsVisible(page, Portal_ObjectRepository.suspendedCertificate);
			common.clickAction(page, Portal_ObjectRepository.suspendedCertificate);
		}

	}

	/**
	 *@Method Implementation to Enter Inception date (IDE)
	 *
	 **/
	public void enterInceptionDate(Page page,String testCaseID,String insurnacePurchaseType,String inceptionDate) throws IOException {

		if(insurnacePurchaseType.equals("初めて自動車保険/バイク保険に加入する") || insurnacePurchaseType.equals("中断証明書を使用して加入する")) {
			String[] date=inceptionDate.split("-");
			if(homePage(testCaseID).getUnknownInceptionDateCheckbox().equals("はい")) {
				common.clickAction(page, Portal_ObjectRepository.uncertainPeriodCheckbox);
			}else {

				common.selectDropdownByText(page, Portal_ObjectRepository.inceptionDateYear, date[2]);
				common.selectDropdownByText(page, Portal_ObjectRepository.inceptionDateMonth, date[1]);
				common.selectDropdownByText(page, Portal_ObjectRepository.inceptionDateDay, date[0]);
			}
		}
	}
	/**
	 *@Method Implementation to accept pop-up for Suspended policy type Insurance purchase
	 *
	 **/
	public void acceptSuspensionPageNavigationDialog(Page page,String testCaseID) throws IOException {

		common.passStatusWithScreenshots(page,testCaseID, "Suspended Insurance Policy pop-up displayed successfully ","suspendedInsurancePop-up",false);
		suspendedPolicyDialogConditionsValidation(page, testCaseID);
		common.clickAction(page, Portal_ObjectRepository.suspentionCertificateDialog);

	}

	/**
	 *@Method Implementation to handle Tariff plan confirmation dialog
	 **/
	public void suspendedPolicyDialogConditionsValidation(Page page,String testCaseId) throws IOException {
		int count=0;
		try {

			page.isEnabled(Portal_ObjectRepository.suspentionCertificateDialog);
			Locator element=page.locator("//ol[@data-aem='QB_ContractType_SuspensionCertificateModal_OrderedList']/div");
			List<Locator>infoList=element.all();
			Report.logger.info("***This the validation of BAU-17310 and BAU-17311***");
			Assertion.assertHasCount(page, "//ol[@data-aem='QB_ContractType_SuspensionCertificateModal_OrderedList']/div", 6);
			count++;
			Assertion.assertBytext(infoList.get(0), "中断証明書を発行した理由が海外渡航ではない");
			count++;
			Assertion.assertBytext(infoList.get(1), "補償を開始する日が中断証明書の有効期限内である");
			count++;
			Assertion.assertBytext(infoList.get(2), "今回お見積りする車は、補償を開始する日より過去１年以内に新たに取得している");
			count++;
			Assertion.assertBytext(infoList.get(3), "今回お見積りする車を含めて、所有・使用し、自動車保険を契約している車が9台以下である＊所有・使用する車が10台以上ある方（フリート契約者）または他社のご契約にフリート契約者料率が適用されている場合は、お見積り・お申込みいただけません。");
			count++;
			Assertion.assertBytext(infoList.get(4), "中断証明書を発行した時に契約していた車両の用途車種が次のいずれかである自家用普通乗用車自家用小型乗用車自家用軽四輪乗用車自家用普通貨物車（最大積載量0.5トン以下）自家用普通貨物車（最大積載量0.5トン超２トン以下）自家用小型貨物車自家用軽四輪貨物車特種用途自動車（キャンピング車）");
			count++;
			Assertion.assertBytext(infoList.get(5), "今回お見積りする車の車検証上の所有者（＊）が次のいずれかである個人契約の場合中断証明書に記載されている所有者「中断証明書に記載されている記名被保険者」またはその配偶者「中断証明書に記載されている記名被保険者」の同居の親族「中断証明書に記載されている記名被保険者」の配偶者の同居の親族法人契約の場合今回のご契約の契約者と同一の法人車検証上の所有者がカーディーラー、ローン会社などの場合は、車検証上の使用者とします。");
			common.passStatusWithScreenshots(page, testCaseId, "The content of all 6 suspended policy dialog information is matching as per the expectation", "suspendedPolicyDialogInfoValidation", true);

		}catch(AssertionError error){
			common.failStatusWithScreenshots(page, testCaseId, "The content of "+count+" point of suspended policy dialog information is not matching", "Failed_suspendedPolicyDialogInfoValidation", true);
		}
	}

	/**
	 *
		@Method implementation to validate error message for all mandatory field
	 *
	 *
	 **/
	public void ErrorMessageValidationOfHomePage(Page page,String testCaseID,String errorMessage) throws IOException {
		//functionalValidationOfHomePage(page, testCaseID);
		try {
			common.clickAction(page, Portal_ObjectRepository.proceedNext);
			Assertion.assertBytext(page, Portal_ObjectRepository.productTypeSelectionError, errorMessage);
			common.passStatusWithScreenshots(page, testCaseID, "Error Message for product selction is displayed succesfully", "ProductSelectionError", true);
			//productSelection(page, testCaseID,utility.homePageMap.get(testCaseID).getInsurnaceProductType());
			insurancePurchaseType(page, testCaseID, utility.homePageMap.get(testCaseID).getInsurancePurchaseType());
			if(!utility.homePageMap.get(testCaseID).getInsurancePurchaseType().equals("現在他社で加入している")) {
				Assertion.assertBytext(page, Portal_ObjectRepository.InceptionDateSelectionError, errorMessage);
				common.passStatusWithScreenshots(page, testCaseID, "Error for all Mandetory field of Contract Page is displaying as:'"+errorMessage+"'", "ContractPageFieldErrorValidation", true);
			}
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseID, "Expected Error Message:'"+errorMessage+"' of one or all field of Contract page is different", "Failed_ContractPageFieldErrorValidation", true);
		}


	}

	/**
	 *
		@Method implementation to validate menu option applicable for the contract type page
	 *
	 *
	 **/
	public void menuOptionValidationOfHomePage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.threeLineMenuOptionNavigation(page, testCaseID);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuBeforeQuote);
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.menuCurrentInsurnace+"//img", "src", "icon-note-gray");
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.menuAboutCarAndBike+"//img", "src", "icon-tire-gray");
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.menuMainDriver+"//img", "src", "icon-people-gray");
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.menuQuotation+"//img", "src", "icon-check-gray");
			Assertion.assertIsHidden(page, Portal_ObjectRepository.menuSaveContent);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuContactCenter);
			common.passStatusWithScreenshots(page, testCaseID, "Menu options of ContractType page is validated successfully" , "MenuOptionsOfContractTypePage", true);
			common.clickAction(page, Portal_ObjectRepository.closeMenuButton);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Menu options of ContractType page is not validated completely", "Failed_MenuOptionValidationOfContractTypePage", true);
		}
	}

	/**
	 *
	@Method implementation to for functional (UI) validation of this page
	 *we are validating logo, quotation number, ChatBot, menu options.
	 *
	 **/
	public void functionalValidationOfHomePage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.PageLogoValidation(page, testCaseID);
			validation.quotationValidation(page, testCaseID);
			validation.chatBotValidation(page, testCaseID);
			menuOptionValidationOfHomePage(page, testCaseID);
			internetEstimationContentValidation(page, testCaseID);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Functional Validation of ContractType page is not completed", "Failed_ContractTypeFunctionalValidation", true);
		}
	}	

	/**
	 *
		@Method implementation to validate Internet Estimation contents as displayed in Contract type page
	 *
	 *
	 **/
	public void internetEstimationContentValidation(Page page,String testCaseID) throws IOException, InterruptedException {
		try {

			Assertion.assertBytext(page, Portal_ObjectRepository.internetEstimationSection+"//li[1]", "今回ご契約を検討されているお車を含めて、所有・使用するお車の総付保台数が10台以上ある場合、または他社のご契約にフリート契約者料率が適用されている場合");
			Assertion.assertBytext(page, Portal_ObjectRepository.internetEstimationSection+"//li[2]", "お車のナンバープレートの色が緑地または黒地の場合（事業用登録のお車）");
			Assertion.assertBytext(page, Portal_ObjectRepository.internetEstimationSection+"//li[3]", "過去13か月以内に終了したご契約がある場合");
			Assertion.assertBytext(page, Portal_ObjectRepository.internetEstimationSection+"//li[4]", "ご契約の切り替えと同時に、お車/バイクの変更がある場合");
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.internetEstimationSection, testCaseID, "Internet estimation section contents validated successfully", "internetContentValidation");
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "FInternet estimation section contents is not validated", "Failed_internetContentValidation", true);
		}


	}

	/**
	 *
			@throws InterruptedException 
	 * @Method implementation of E2E flow for the contract type page
	 *This method will take action based on the data Input sheet
	 *
	 **/
	public void e2eFlowOfHomePgae(Page page,String testCaseID,String productType,String insurnacePurchaseType,String inceptionDate, String insuranceFlowType) throws IOException, InterruptedException {
		//loginToEmmaApplication(page, testCaseID);
		insurancePurchaseFlow(page, testCaseID, insuranceFlowType);
		loginToEmmaApplication(page, testCaseID);
		CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		productSelection(page, testCaseID,productType);
		insurancePurchaseType(page,testCaseID,insurnacePurchaseType);
		enterInceptionDate(page, testCaseID, insurnacePurchaseType, inceptionDate);
		common.passStatusWithScreenshots(page, testCaseID,"Data has been selected successfully in the Contract Type page","ContractTypePage",true);
		if(common.elementVisibility(page, Portal_ObjectRepository.closeBanner)) {
			common.clickAction(page, Portal_ObjectRepository.closeBanner);
		}
		common.clickAction(page, Portal_ObjectRepository.proceedNext);
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		if(insurnacePurchaseType.equals("中断証明書を使用して加入する") ) {
			acceptSuspensionPageNavigationDialog(page, testCaseID);
			ADJ_portal_CommonValidation.quotationNumberOfVehiclePage=validation.getQuotationNumber(page, testCaseID);
			common.passStatusWithScreenshots(page, testCaseID, "Successfully proceeded to Suspended Policy page", "proceededToSuspendedPolicy", true);
			ADJ_portal_suspensionCertificateScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");

		}else if(insurnacePurchaseType.equals("現在他社で加入している")) {

			ADJ_portal_CommonValidation.quotationNumberOfVehiclePage=validation.getQuotationNumber(page, testCaseID);
			common.passStatusWithScreenshots(page, testCaseID, "Successfully proceeded to Previous Policy page", "proceededToPerviousPolicy", true);
			ADJ_portal_currentInsuranceScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		}else if (Assertion.assertURLContains(page, "Vehicle") || Assertion.assertURLContains(page, "Bike")){
			ADJ_portal_CommonValidation.quotationNumberOfVehiclePage=validation.getQuotationNumber(page, testCaseID);
			common.passStatusWithScreenshots(page, testCaseID, "Successfully proceeded to Vehicle page", "proceededToVehiclePage", true);
			ADJ_portal_quotationScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		}
	}

	public void normalFlowOfHomePgae(Page page,String testCaseID,String productType,String insurnacePurchaseType,String inceptionDate, String insuranceFlowType) throws IOException, InterruptedException {
		//navigateToCorporateFlow(page, testCaseID,insuranceFlowType);
		loginToEmmaApplication(page, testCaseID);
		CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		productSelection(page, testCaseID,productType);
		insurancePurchaseType(page,testCaseID,insurnacePurchaseType);
		enterInceptionDate(page, testCaseID, insurnacePurchaseType, inceptionDate);
		common.passStatusWithScreenshots(page, testCaseID,"Data has been selected successfully in the Contract Type page","ContractTypePage",true);
		if(common.elementVisibility(page, Portal_ObjectRepository.closeBanner)) {
			common.clickAction(page, Portal_ObjectRepository.closeBanner);
		}
		common.clickAction(page, Portal_ObjectRepository.proceedNext);
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		if(insurnacePurchaseType.equals("中断証明書を使用して加入する") ) {
			acceptSuspensionPageNavigationDialog(page, testCaseID);
			ADJ_portal_CommonValidation.quotationNumberOfVehiclePage=validation.getQuotationNumber(page, testCaseID);
			common.passStatusWithScreenshots(page, testCaseID, "Successfully proceeded to Suspended Policy page", "proceededToSuspendedPolicy", true);
			ADJ_portal_suspensionCertificateScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");

		}else if(insurnacePurchaseType.equals("現在他社で加入している")) {

			ADJ_portal_CommonValidation.quotationNumberOfVehiclePage=validation.getQuotationNumber(page, testCaseID);
			common.passStatusWithScreenshots(page, testCaseID, "Successfully proceeded to Previous Policy page", "proceededToPerviousPolicy", true);
			ADJ_portal_currentInsuranceScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		}else if (Assertion.assertURLContains(page, "Vehicle") || Assertion.assertURLContains(page, "Bike")){
			ADJ_portal_CommonValidation.quotationNumberOfVehiclePage=validation.getQuotationNumber(page, testCaseID);
			common.passStatusWithScreenshots(page, testCaseID, "Successfully proceeded to Vehicle page", "proceededToVehiclePage", true);
			ADJ_portal_quotationScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		}
	}

	public void homePageCampainCodeValidation(Page page,String testCaseID,String insuranceFlowType,String campaignCode) throws IOException {
		try {
			String url=utility.property.getProperty("IndividualURL").replace("#","?CampaignCode="+campaignCode+"#");
			page.navigate(utility.property.getProperty("MainURL")+utility.property.getProperty("Environment")+url);
			Report.logger.info("Campaign Code: "+campaignCode+" URL is launched as: "+utility.property.getProperty("MainURL")+utility.property.getProperty("Environment")+url);
			if(campaignCode.equals("3888000") || campaignCode.equals("77777") || campaignCode.equals("38800") || campaignCode.equals("49509")) {
				insurancePurchaseFlow(page, testCaseID, insuranceFlowType);
			}else {
				//Assertion.assertBytextIsNotEqual(page, Portal_ObjectRepository.purchaseFlowText+"/..", "【個人のお客さま専用ページ】法人契約はこちら。");
				Assertion.assertIsAttached(page, Portal_ObjectRepository.purchaseFlowText+"/..");
				Assertion.assertBytextIsNotEqual(page, Portal_ObjectRepository.purchaseFlowText+"/..", "【個人のお客さま専用ページ】法人契約はこちら。");
				common.passStatusWithScreenshots(page, testCaseID, " Text '【個人のお客さま専用ページ】法人契約はこちら。' is not displayed when URL have campaign code: "+campaignCode+" in personal flow", campaignCode+"PersonalFlowSwitchTextValidation", false);
				URL.launchURL(page, testCaseID, insuranceFlowType);
				insurancePurchaseFlow(page, testCaseID, insuranceFlowType);
				url=url.replace("html/", "html/sme/");
				page.navigate(utility.property.getProperty("MainURL")+utility.property.getProperty("Environment")+url);
				Report.logger.info("Campaign Code: "+campaignCode+" URL is launched as: "+utility.property.getProperty("MainURL")+utility.property.getProperty("Environment")+url);
				//Assertion.assertIsNotAttached(page, Portal_ObjectRepository.purchaseFlowText+"/..");
				Assertion.assertIsAttached(page, Portal_ObjectRepository.purchaseFlowText+"/..");
				Assertion.assertBytextIsNotEqual(page, Portal_ObjectRepository.purchaseFlowText+"/..", "【法人のお客さま専用ページ】個人契約はこちら。");
				common.passStatusWithScreenshots(page, testCaseID, " Text '【法人のお客さま専用ページ】個人契約はこちら。' is not displayed when URL have campaign code: "+campaignCode+" in corporate flow", campaignCode+"CorporateFlowSwitchTextValidation", false);
			}
		}catch(AssertionError error){
			System.out.println(error);
		}

	}


}
