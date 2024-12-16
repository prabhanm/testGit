package org.axa.portal.page;

import java.io.IOException;

import org.axa.framework.Assertion;
import org.axa.framework.CommonFunctions;
import org.axa.framework.Portal_ObjectRepository;
import org.axa.framework.Report;
import org.axa.portal.validation.ADJ_portal_CommonValidation;

import com.microsoft.playwright.Page;

public class ADJ_portal_paymentInformationScreen {

	utility util = new utility();
	CommonFunctions common = new CommonFunctions();
	ADJ_portal_homePage home=new ADJ_portal_homePage();
	ADJ_portal_CommonValidation validation=new ADJ_portal_CommonValidation();
	public String PolicyNumber="";
	public static String FINAL_PREMIUM_AMOUNT=null;
	public static boolean CORPORATE_URL=false;

	public ParameterOfPaymentInformationPage paymentInfo(String testCaseId) throws IOException {
		ParameterOfPaymentInformationPage para=utility.paymentInfoMap.get(testCaseId);
		return para;
	}

	public ParameterOfCreditCardDetails cardDetails(String testCaseId) throws IOException {
		ParameterOfCreditCardDetails para=utility.cardDetailsMap.get(testCaseId);
		return para;
	}

	public void defaultPaymentOptionselectionValidation(Page page,String testCaseId) {
		Assertion.assertIsNotChecked(page, Portal_ObjectRepository.fullPaymentOption);
		Assertion.assertIsChecked(page, Portal_ObjectRepository.installmentPaymentOption);
		Assertion.assertIsChecked(page, Portal_ObjectRepository.CreditCarPayment);
		common.passStatusWithScreenshots(page, testCaseId,"by default Credit card and  Installment payment option is selected","DefaultPaymentOptionValidation",true);
	}

	public void konbiniOptionsValidation(Page page,String testCaseId) {
		Assertion.assertBytext(page, "//label[@for='convenience01']", "セイコーマート");
		Assertion.assertBytext(page, "//label[@for='convenience02']", "セブンイレブン");
		Assertion.assertBytext(page, "//label[@for='convenience03']", "ファミリーマート");
		Assertion.assertBytext(page, "//label[@for='convenience04']", "ミニストップ");
		Assertion.assertBytext(page, "//label[@for='convenience05']", "ローソン");
		common.passStatusWithScreenshots(page, testCaseId,"All 5 options of Konbini is present","KonbiniOptionValidation",true);
	}

	public void selectPaymentMethod(Page page,String testCaseId) throws IOException, InterruptedException {
		//System.out.println(common.getText(page, Portal_ObjectRepository.fullPaymentOption));
		//System.out.println(common.getText(page, Portal_ObjectRepository.installmentPaymentOption));
		Assertion.assertBytextContains(page, Portal_ObjectRepository.fullPaymentOption, ADJ_portal_aboutMainDriverAndPolicyPlanScreen.POLICY_PLAN_AMOUNT);
		common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.fullPaymentOption, testCaseId, "selected Policy plan chargs is same as fullPayment option description", "PolicyPlanAmountValidation");

		if(paymentInfo(testCaseId).getPaymentMode().equals("CC_Full Payment")) {
			//FINAL_PREMIUM_AMOUNT=common.getText(page, Portal_ObjectRepository.fullPaymentOption).replaceAll("[一括払保険料（割引適用後）, ,/年]", "");
			FINAL_PREMIUM_AMOUNT=common.getText(page, Portal_ObjectRepository.fullPaymentOption).replace("一括払保険料（割引適用後）", "").replace("/年", "").replace(" ","");

			common.clickAction(page, Portal_ObjectRepository.fullPaymentOption);

		}else if(paymentInfo(testCaseId).getPaymentMode().equals("Konbini")) {
			FINAL_PREMIUM_AMOUNT=common.getText(page, Portal_ObjectRepository.fullPaymentOption).replace("一括払保険料（割引適用後）", "").replace("/年", "").replace(" ","");
			common.clickAction(page, Portal_ObjectRepository.fullPaymentOption);
			common.clickAction(page, Portal_ObjectRepository.konbiniPayment);
		}else {
			FINAL_PREMIUM_AMOUNT=common.getText(page, Portal_ObjectRepository.installmentPaymentOption).replace("分割払保険料（割引適用後）", "").replace("/年", "").replace(" ","");
			//FINAL_PREMIUM_AMOUNT=common.getText(page, Portal_ObjectRepository.installmentPaymentOption).replaceAll("[分割払保険料（割引適用後）,/年]", "").replace(" ", "");
		}

		common.passStatusWithScreenshots(page, testCaseId,"Payment option selected successfully","paymentPage",true);
		Thread.sleep(3000);
		common.clickAction(page, Portal_ObjectRepository.proceedPayment);
	}

	public void enterCreditCardInformation(Page page,String testCaseId) throws IOException, InterruptedException {
		try {
			Assertion.assertIsDisabled(page, Portal_ObjectRepository.PaymentContentConfirmationButton);
		}catch(AssertionError error) {
			System.out.println(error);
		}
		String cardType=paymentInfo(testCaseId).getCardOrStoreType();
		String[] expiaryDate=cardDetails(cardType).getCardExpiaryDate().split("-");

		common.enterText(page, Portal_ObjectRepository.enterCardNumber, cardDetails(cardType).getCardNumber());
		Assertion.assertIsDisabled(page, Portal_ObjectRepository.PaymentContentConfirmationButton);
		common.selectDropdownByText(page, Portal_ObjectRepository.enterCardExpiaryMonth, expiaryDate[0]);
		Assertion.assertIsDisabled(page, Portal_ObjectRepository.PaymentContentConfirmationButton);
		common.selectDropdownByText(page, Portal_ObjectRepository.enterCardExpiaryYear, expiaryDate[1]);
		Assertion.assertIsDisabled(page, Portal_ObjectRepository.PaymentContentConfirmationButton);
		common.enterText(page, Portal_ObjectRepository.enterCardSicurityNumber, cardDetails(cardType).getCvvNumber());
		Assertion.assertIsEnabled(page, Portal_ObjectRepository.PaymentContentConfirmationButton);
		common.passStatusWithScreenshots(page,testCaseId, cardType+" type Credit Card Information entered successfully successfully","creditCardDetails",true);
		Thread.sleep(5000);
		common.clickAction(page, Portal_ObjectRepository.PaymentContentConfirmationButton);
		if(CORPORATE_URL) {
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.payment_cardOwnerNameConfirmationDetailSection);
			Report.logger.pass("'カードのご名義人' information is not displayed for corporate flow");
		}else {
			Assertion.assertIsAttached(page, Portal_ObjectRepository.payment_cardOwnerNameConfirmationDetailSection);
			Report.logger.pass("'カードのご名義人' information is displayed for Personal flow");
		}

		common.passStatusWithScreenshots(page, testCaseId,"Credit Card details confirmation page","creditCardDetailConfirmation",true);
		Thread.sleep(3000);
		common.clickAction(page, Portal_ObjectRepository.confirmCreditCardPaymentInfo);

	}

	public void creditCardNumberFieldValidation(Page page,String testCaseId) throws IOException {
		try {
			String cardType=paymentInfo(testCaseId).getCardOrStoreType();
			common.enterText(page, Portal_ObjectRepository.enterCardNumber, cardDetails(cardType).getCardNumber().substring(0, 13));
			Assertion.assertBytext(page, Portal_ObjectRepository.creditCardNumberFieldError, "有効なクレジットカード番号が必要です");
			common.passStatusWithScreenshots(page, testCaseId, "Error Message is displaying when entering card details up to 13 digit ", "CardNumberErrorValidation",true);
			common.enterText(page, Portal_ObjectRepository.enterCardNumber, cardDetails(cardType).getCardNumber()+"12345566");
			System.out.println(cardDetails(cardType).getCardNumber()+"12345566");
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.enterCardNumber, "value", (cardDetails(cardType).getCardNumber()+"12345566").substring(0, 19));
			common.passStatusWithScreenshots(page,testCaseId, "System is allowed to enter max 19 digit of the card number though we are entering card detais as:"+cardDetails(cardType).getCardNumber()+"12345566", "MaxCardNumberValidation",true);
		}catch(AssertionError error) {
			System.out.println(error);
		}
	}

	public void creditCardNumberCvvValidation(Page page,String testCaseId) throws IOException {

		try {
			String cardType=paymentInfo(testCaseId).getCardOrStoreType();
			common.enterText(page, Portal_ObjectRepository.enterCardSicurityNumber, cardDetails(cardType).getCvvNumber().substring(0, 2));
			Assertion.assertBytext(page, Portal_ObjectRepository.creditCardCVVFieldError, "これは必須フィールドです");
			common.passStatusWithScreenshots(page, testCaseId, "Error Message is displaying when entering CVV number up to 2 digit ", "CVVnumberErrorValidation",true);
			common.enterText(page, Portal_ObjectRepository.enterCardSicurityNumber, cardDetails(cardType).getCvvNumber()+"066");
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.enterCardSicurityNumber, "value", (cardDetails(cardType).getCvvNumber()+"066").substring(0, 4));
			common.passStatusWithScreenshots(page,testCaseId, "System is allowed to enter max 19 digit of the card number though we are entering card detais as:"+cardDetails(cardType).getCardNumber()+"12345566", "MaxCardNumberValidation",true);
		}catch(AssertionError error) {
			System.out.println(error);
		}
	}
	public void creditCardNumberExpiaryDateValidation(Page page,String testCaseId) throws IOException {
		try {
			String[] date=CommonFunctions.getCurrentDate("MM-yyyy").split("-");

			String futureMonth=null;
			String futureYear=null;

			if(date[0].equals("12")) {
				futureMonth="01";
				int year=(Integer.parseInt(date[1]))+1;
				futureYear=""+year;
			}else {
				futureMonth=""+(Integer.parseInt(date[0])+1);
				if(futureMonth.length()==1) {
					futureMonth="0"+futureMonth;
				}
				futureYear=date[1];
			}

			common.selectDropdownByText(page, Portal_ObjectRepository.enterCardExpiaryMonth, date[0]);
			common.selectDropdownByText(page, Portal_ObjectRepository.enterCardExpiaryYear, date[1]);
			Assertion.assertIsDisabled(page, Portal_ObjectRepository.PaymentContentConfirmationButton);
			common.passStatusWithScreenshots(page,testCaseId, "Proceed button is disabled while selecting the Expiary date as current Month :"+date,"NegativeCardExiaryDateValidation",false);

			common.selectDropdownByText(page, Portal_ObjectRepository.enterCardExpiaryMonth, futureMonth);
			common.selectDropdownByText(page, Portal_ObjectRepository.enterCardExpiaryYear, futureYear);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.PaymentContentConfirmationButton);
			common.passStatusWithScreenshots(page,testCaseId, "Proceed button is Enabled while selecting the Expiary date as Future date :"+futureMonth+futureYear,"FutureExiaryDateValidation",false);

		}catch(AssertionError error) {
			System.out.println(error);
		}
	}

	public void CreditCardInformationFieldValidation(Page page,String testCaseId) throws IOException {
		creditCardNumberFieldValidation(page, testCaseId);
		creditCardNumberCvvValidation(page, testCaseId);
		creditCardNumberExpiaryDateValidation(page, testCaseId);
		page.locator(Portal_ObjectRepository.enterCardSicurityNumber).clear();
	}

	public void SelectKonbiniStore(Page page,String testCaseId) throws IOException, InterruptedException {
		String storeType=paymentInfo(testCaseId).getCardOrStoreType();
		common.clickAction(page,"//*[text()='"+storeType+"']");
		common.passStatusWithScreenshots(page, testCaseId,"Konbini Store option get selected successfully","KonbiniPage",false);

		/*
		 * String PolicyNumber=common.getText(page,
		 * Portal_ObjectRepository.getPolicyNumber);
		 * System.out.println("Policy Number: "+PolicyNumber);
		 */
		//common.passStatusWithScreenshots(page, testCaseId,"Konbini Option got selected successfully","konbiniOption",true);
		Thread.sleep(3000);
		common.clickAction(page, Portal_ObjectRepository.PaymentContentConfirmationButton);
	}

	public void functionalValidationOfPaymentPage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.PageLogoValidation(page, testCaseID);
			validation.quotationValidation(page, testCaseID);
			validation.chatBotValidation(page, testCaseID);
			menuOptionValidationOfPaymentPage(page, testCaseID);
		}catch(AssertionError error) {
			System.out.println(error);

			common.failStatusWithScreenshots(page, testCaseID, "Functional Validation of Vehicle page is not completed", "Failed_VehiclePageFunctionalValidation", true);
		}
	}

	public void menuOptionValidationOfPaymentPage(Page page,String testCaseID) throws IOException, InterruptedException {
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
			/*
			 * Assertion.assertIsHidden(page, Portal_ObjectRepository.menuQuotation);
			 * Assertion.assertIsHidden(page,
			 * Portal_ObjectRepository.menuBeforeApplication);
			 * Assertion.assertIsHidden(page, Portal_ObjectRepository.menuContractor);
			 * Assertion.assertIsHidden(page,
			 * Portal_ObjectRepository.menuMatterConfirmation);
			 */
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuContractDetailsConfirmation);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuPayment);
			common.passStatusWithScreenshots(page, testCaseID, "Menu options of Term and Condition page is validated successfully" , "MenuOptionValidationOfTandCPage", true);
			common.clickAction(page, Portal_ObjectRepository.closeMenuButton);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Menu options of Term and Condition page is not validated completely", "Failed_MenuOptionValidationOfTandCPage", true);
		}
	}

	public void finalPaymentConfirmation(Page page,String testCaseId) throws IOException, InterruptedException {
		try {

			if(paymentInfo(testCaseId).getPaymentMode().equals("Konbini")) {
				common.passStatusWithScreenshots(page, testCaseId,"Final payment confirmation pop-up for Konbini is displayed successfully","KonbiniFinalPaymentPop-up",false);
				common.clickAction(page, Portal_ObjectRepository.finalPaymentConfirmation);
				if(Integer.parseInt(ADJ_portal_aboutMainDriverAndPolicyPlanScreen.POLICY_PLAN_AMOUNT.replace(",", ""))>300000) {
					Assertion.assertIsEnabled(page, Portal_ObjectRepository.konbiniPaymentCritariaPopup);
					Assertion.assertBytext(page, Portal_ObjectRepository.konbiniPaymentCritariaPopup, "保険料のお支払いが30万円を超える場合");
					common.passStatusWithScreenshots(page, testCaseId,"Expected Konbini payment popup when payment amount is: "+ADJ_portal_aboutMainDriverAndPolicyPlanScreen.POLICY_PLAN_AMOUNT+" which is 30万円以下 ","KonbiniPaymentValidationPop-up",false);
					common.clickAction(page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
				}else {
					Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
					if(!Assertion.assertURLContains(page, "thankYou")) {
						common.waitForSelector(page, Portal_ObjectRepository.emmaButtonOnFinalPage);
					}

					System.out.println("Policy Number: "+validation.getQuotationNumber(page, testCaseId));
					if(validation.getQuotationNumber(page, testCaseId).equals(ADJ_portal_CommonValidation.quotationNumberOfVehiclePage)) {
						common.passStatusWithScreenshots(page,testCaseId, "Policy: "+ADJ_portal_CommonValidation.quotationNumberOfVehiclePage+" created successfully","ThankyouPage",true);
					}else {
						common.failStatusWithScreenshots(page,testCaseId, "Policy: "+ADJ_portal_CommonValidation.quotationNumberOfVehiclePage+" is not macing with final page","Failed_ThankyouPage",true);
					}
				}
			}else {
				Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
				if(!Assertion.assertURLContains(page, "thankYou")) {
					common.waitForSelector(page, Portal_ObjectRepository.emmaButtonOnFinalPage);
				}
				System.out.println("Policy Number: "+validation.getQuotationNumber(page, testCaseId));
				if(validation.getQuotationNumber(page, testCaseId).equals(ADJ_portal_CommonValidation.quotationNumberOfVehiclePage)) {
					common.passStatusWithScreenshots(page,testCaseId, "Policy: "+ADJ_portal_CommonValidation.quotationNumberOfVehiclePage+" created successfully","ThankyouPage",true);
				}else {
					common.failStatusWithScreenshots(page,testCaseId, "Policy: "+ADJ_portal_CommonValidation.quotationNumberOfVehiclePage+" is not macing with final page","Failed_ThankyouPage",true);
				}
			}
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page,testCaseId, "Policy generated on 2nd page is not matching with Tankyou page","Failed_ThankyouPage",true);
		}
	}

	public void e2eFlowOfPaymentPage(Page page,String testCaseId) throws IOException, InterruptedException {
		selectPaymentMethod(page, testCaseId);
		if(paymentInfo(testCaseId).getPaymentMode().equals("Konbini")) {
			SelectKonbiniStore(page, testCaseId);
		}else {
			enterCreditCardInformation(page, testCaseId);
		}
		//Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		finalPaymentConfirmation(page, testCaseId);
	}

	/**
	 *@throws InterruptedException 
	 * @Method Implementation for Emma sign-up page
	 **/
	public void registerAccountInEmma(Page page,String testCaseID,String password,String rePassword) throws IOException, InterruptedException {

		if(utility.homePageMap.get(testCaseID).getMemberType().equals("Non Login Member")) {
			common.clickAction(page, Portal_ObjectRepository.emmaButtonOnFinalPage);
			common.waitForSelector(page, Portal_ObjectRepository.RegisterAccountPassword);
			common.passStatusWithScreenshots(page,testCaseID, "User successfully navigated to Emma Signup page", "signupPageNavigation",true);
			common.enterText(page, Portal_ObjectRepository.RegisterAccountPassword, password);
			common.enterText(page, Portal_ObjectRepository.RegisterAccountRePassword, rePassword);
			page.check(Portal_ObjectRepository.RegisterAccountShowPassword);
			common.clickAction(page,Portal_ObjectRepository.closeBanner);
			common.passStatusWithScreenshots(page,testCaseID, "User entered Passowrd and re-password successfully ","PasswordValidation",true);
			common.clickAction(page, Portal_ObjectRepository.RegisterAccountSignupButton);
			if(common.elementVisibility(page, Portal_ObjectRepository.RegisterAccountError)) {
				common.passStatusWithScreenshots(page,testCaseID, "Error 'ご入力いただいたメールアドレスはすでに登録されています。別のメールアドレスをご利用ください。' displayed successfully ","accountAlreadyRegisteredError",true);
			}else {
				Thread.sleep(5000);
				page.reload();
				Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
			}
		} 
	}

}
