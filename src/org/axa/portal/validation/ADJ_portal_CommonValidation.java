package org.axa.portal.validation;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.io.IOException;

import org.axa.framework.Assertion;
import org.axa.framework.CommonFunctions;
import org.axa.framework.Portal_ObjectRepository;
import org.axa.framework.Report;
import org.axa.portal.page.utility;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ADJ_portal_CommonValidation extends CommonFunctions {
	public static String quotationNumberOfVehiclePage;

	public boolean URLConfirmation(Page page,String pattern) throws InterruptedException{ 
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		Thread.sleep(3000);
		System.out.println(page.url());
		if(page.url().endsWith(pattern)) {
			return true;
		}else {
			return false;
		}  
	}

	public void PageLogoValidation(Page page,String testCaseID) {
		String[] pageUrl=page.url().split("/");
		String pageName=pageUrl[pageUrl.length-1];
		try {
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.logoImage);
			if(Assertion.assertURLContains(page, "ContractType")==true) {
				System.out.println(quotationValidation(page, testCaseID));
				if(quotationValidation(page, testCaseID).equals("-")) {
					if(Assertion.assertURLContains(page, "sme")) {
						clickAction(page, Portal_ObjectRepository.logoImage);
						assertThat(page).hasURL("https://www.axa-direct.co.jp/");
						passStatusWithScreenshots(page, testCaseID, "Successfully navigated to Grand top screen", "SME_logoNavigation", true);
						page.goBack();
						clickAction(page, Portal_ObjectRepository.closeBanner);

					}else {
						clickAction(page, Portal_ObjectRepository.logoImage);
						Assertion.assertBytext(page, Portal_ObjectRepository.roughtEstimationDialogTitle, "概算見積りを試しませんか？");
						passStatusWithScreenshots(page, testCaseID, "Rought Quotation dialogbox is displaying while clicking on AXA logo from 'ContactType' page", "logoNavigation", false);
						clickAction(page, Portal_ObjectRepository.roughEstimationDialogCloseButton);
					}
				}else {
						clickAction(page, Portal_ObjectRepository.logoImage);
						SaveDataDialogboxValidation(page, testCaseID,"AXA logo");
					}

					//Assertion.assertURLContains(page, "page9") ||
					//}else if(Assertion.assertURLContains(page, "page9") || Assertion.assertURLContains(page, "page10") || Assertion.assertURLContains(page, "page11")) {
				}else if(pageName.equals("page9") || pageName.equals("page10") || pageName.equals("page11")) {
					clickAction(page, Portal_ObjectRepository.logoImage);
					/*if(!Assertion.assertURLContains(page, "sme")) {
					Assertion.assertBytext(page, Portal_ObjectRepository.logoModelBodyAfterAccountRegister+"//..//..//p", "このページから離れますか？");
					Assertion.assertBytext(page, Portal_ObjectRepository.logoModelBodyAfterAccountRegister+"/div/span", "このままページを離れると、お見積りを再開することができません。");
					Assertion.assertBytext(page, Portal_ObjectRepository.logoModelBodyAfterAccountRegister+"/span", "再度お手続きいただく場合は、新しくお見積りを作成いただくか、カスタマーサービスセンターへお電話いただく必要がございますので、ご了承ください。");
					passStatusWithScreenshots(page, testCaseID, "Previous Informaion confirmation dialogbox is displaying", "logoNavigation", false);
					clickAction(page, "//span[text()='お手続きに戻る']");
				}else {*/
					Assertion.assertBytext(page, Portal_ObjectRepository.roughtEstimationDialogTitle, "このページから離れますか？");
					passStatusWithScreenshots(page, testCaseID, "Previous Information confirmation dialogbox is displaying", pageName+"LogoNavigation", false);
					clickAction(page, "//button[text()='いいえ']");
					Assertion.assertURLContains(page, pageName);
					passStatusWithScreenshots(page, testCaseID, "successfully return to same page after clicking on 'いいえ' button of Logo dialog", pageName+"samePageReturnValidation", true);
					//}
				}
				else {
					clickAction(page, Portal_ObjectRepository.logoImage);
					SaveDataDialogboxValidation(page, testCaseID,"AXA logo");
				}
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "Logo functionality validation is not completed", "Failed_logoValidation", false);

			}

		}

		public void SaveDataDialogboxValidation(Page page,String testCaseID,String clickActionFrom) {
			Assertion.assertIsChecked(page, Portal_ObjectRepository.saveDataDialogCheckbox);
			if(!(Assertion.assertURLContains(page, "page9") || Assertion.assertURLContains(page, "page10")|| Assertion.assertURLContains(page, "page11"))){
				clickAction(page, Portal_ObjectRepository.saveDataDialogEmailConfirmationButton);
				Assertion.assertBytext(page, Portal_ObjectRepository.saveDataDialogErrorMessage, "これは必須フィールドです");
				passStatusWithScreenshots(page, testCaseID, "Save data dialogbox is displaying while clicking on "+clickActionFrom, clickActionFrom.replaceAll(" ", "")+"Navigation", false);
			}else {
				Assertion.assertHasValue(page, Portal_ObjectRepository.saveDataDialogEmailInput, utility.contractorInfoMap.get(testCaseID).getEmailAddress());
				passStatusWithScreenshots(page, testCaseID, "Save data dialogbox is displaying while clicking on "+clickActionFrom+ " and Email is already present", clickActionFrom.replaceAll(" ", "")+"Navigation", false);
			}
			clickAction(page, Portal_ObjectRepository.saveDataDialogboxCloseButton);

		}

		public void chatBotValidation(Page page,String testCaseID) {
			try {
				if(!Assertion.assertURLContains(page, "sme")){
					if(elementVisibility(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("チャットで 相談する")))) {
						passStatusWithScreenshots(page, testCaseID, "Chat bot icon is displayed", "chatBotIconValidation", true);
						clickAction(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("チャットで 相談する")));
					}else if(elementVisibility(page, Portal_ObjectRepository.chatBotIcon)) {
						passStatusWithScreenshots(page, testCaseID, "Chat bot icon is displayed", "chatBotIconValidation", true);
						//clickAction(page, Portal_ObjectRepository.closeBanner);
						clickAction(page, Portal_ObjectRepository.chatBotIcon);
					}
					enterText(page, Portal_ObjectRepository.chatBotenterText, "Automation testing text");
					passStatusWithScreenshots(page, testCaseID, "We are successfully entered the text in chat bot", "botInput", true);
					clickAction(page, Portal_ObjectRepository.chatBotCloseButton);
					clickAction(page, "//button[text()='終了']");
					clickAction(page, Portal_ObjectRepository.chatBotCloseButton);

					if(isElementPresent(page, "//button[text()='終了']")) {
						clickAction(page, "//button[text()='終了']");
						clickAction(page, Portal_ObjectRepository.chatBotCloseButton);
					}
				}
			}catch(AssertionError error) {
				System.out.println(error);
				failStatusWithScreenshots(page, testCaseID, "ChatBot functionality validation is not completed", "Failed_ChatBotValidation", false);
			}catch(Exception e) {
				System.out.println(e);
				failStatusWithExceptions(page, testCaseID, "ChatBot functionality validation is not completed", "Failed_ChatBotValidation", e,false);
			}
		}

		public void threeLineMenuOptionNavigation(Page page,String testCaseID) throws InterruptedException {
			String[] pageUrl=page.url().split("/");
			String pageName=pageUrl[pageUrl.length-1];
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.threeLineMenuButton);
			clickAction(page, Portal_ObjectRepository.threeLineMenuButton);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuInputContentConfirmation);
			if(!elementVisibility(page, Portal_ObjectRepository.menuQuotation)) {
				clickAction(page, Portal_ObjectRepository.menuInputContentConfirmation);
			}
			passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to menuOption screen",pageName+"ThreeLineMenuValidation", false);
			//clickAction(page, Portal_ObjectRepository.menuInputContentConfirmation);
		}

		public void BackToPreviousScreen(Page page,String testCaseID) throws InterruptedException {
			clickAction(page, Portal_ObjectRepository.backToPreviousSreen);
			Assertion.assertBytext(page,Portal_ObjectRepository.backToPreviousSreenDialogTitle ,"前のページへ戻りますか？");
			passStatusWithScreenshots(page, testCaseID, "Pop-up about 前のページへ戻りますか？ is displayed successfully after clicking on '閉じる' button ","BackscreenPop-up", false);
			clickAction(page, Portal_ObjectRepository.backToPreviousSreenDialogNextButton);
			pageURLValidation(page, testCaseID);
		}

		public void navigateToAboutVehiclePageFromThreeMenuList(Page page,String testCaseID) throws InterruptedException {

			try {
				threeLineMenuOptionNavigation(page, testCaseID);
				clickAction(page, Portal_ObjectRepository.menuAboutCarAndBike);
				if(!Assertion.assertURLContains(page, "Vehicle")) {
					Assertion.assertBytext(page,Portal_ObjectRepository.backToPreviousSreenDialogTitle ,"前のページへ戻りますか？");
					passStatusWithScreenshots(page, testCaseID, "Pop-up about 前のページへ戻りますか？ is displayed successfully after clicking on 'お車/バイクについて'","VehicleLandingPagePop-up", false);
					clickAction(page, Portal_ObjectRepository.backToPreviousSreenDialogNextButton);
					Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to vehicle screen","LandedToVehiclePage", true);
				}
				else if(Assertion.assertURLContains(page, "Vehicle")) {
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to vehicle screen","LandedToVehiclePage", true);
				}
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "Not landed to expected vehicle screen","Failed_LandedToVehiclePage", false);

			}

		}

		public void navigateToMainDriverPageFromThreeMenuList(Page page,String testCaseID) throws InterruptedException {

			try {
				threeLineMenuOptionNavigation(page, testCaseID);
				clickAction(page, Portal_ObjectRepository.menuMainDriver);
				if(!Assertion.assertURLContains(page, "Driver")) {
					Assertion.assertBytext(page,Portal_ObjectRepository.backToPreviousSreenDialogTitle ,"前のページへ戻りますか？");
					passStatusWithScreenshots(page, testCaseID, "Pop-up about 前のページへ戻りますか？ is displayed successfully after clicking on '主な運転者'","DriverLandingPagePop-up", false);
					clickAction(page, Portal_ObjectRepository.backToPreviousSreenDialogNextButton);
					Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Driver screen","LandedToDriverPage", true);
				}else if(Assertion.assertURLContains(page, "Driver")) {
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Driver screen","LandedToDriverPage", true);
				}

			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "Not landed to expected Driver screen","Failed_LandedToDriverPage", false);

			}
		}

		public void navigateToQuotationPageFromThreeMenuList(Page page,String testCaseID) throws InterruptedException {
			try {
				threeLineMenuOptionNavigation(page, testCaseID);
				clickAction(page, Portal_ObjectRepository.menuQuotation);
				if(!Assertion.assertURLContains(page, "page6")) {
					Assertion.assertBytext(page,Portal_ObjectRepository.backToPreviousSreenDialogTitle ,"前のページへ戻りますか？");
					passStatusWithScreenshots(page, testCaseID, "Pop-up about 前のページへ戻りますか？ is displayed successfully after clicking on 'お見積り'","QuotationLandingPagePop-up", false);
					clickAction(page, Portal_ObjectRepository.backToPreviousSreenDialogNextButton);
					Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Quotation screen","LandedToQuotationPage", true);
				}else if(Assertion.assertURLContains(page, "page6")) {
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Quotation screen","LandedToQuotationPage", true);
				}
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "Not landed to expected Quotation screen","Failed_LandedToQuotationPage", false);	
			}
		}

		public void navigateToBeforeQuotationPageFromThreeMenuList(Page page,String testCaseID) throws InterruptedException {
			try {
				threeLineMenuOptionNavigation(page, testCaseID);
				clickAction(page, Portal_ObjectRepository.menuBeforeQuote);
				if(!Assertion.assertURLContains(page, "ContractType")) {
					Assertion.assertBytext(page, Portal_ObjectRepository.backToPreviousSreenDialogTitle,"前のページへ戻りますか？");
					passStatusWithScreenshots(page, testCaseID, "Pop-up about 前のページへ戻りますか？ is displayed successfully after clicking on 'お見積りの前に'","BeforeQuoteLandingPagePop-up", false);
					clickAction(page, Portal_ObjectRepository.backToPreviousSreenDialogNextButton);
					Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Contract type screen","LandedToContractTypePage", true);
				}
				else if(Assertion.assertURLContains(page, "ContractType")) {
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Contract type screen","LandedToContractTypePage", true);
				}
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "Not landed to expected Quotation screen","Failed_LandedToQuotationPage", false);	
			}
		}

		public void navigateToPreviosPolicyPageFromThreeMenuList(Page page,String testCaseID) throws InterruptedException {
			try {
				threeLineMenuOptionNavigation(page, testCaseID);
				clickAction(page, Portal_ObjectRepository.menuCurrentInsurnace);
				if(!Assertion.assertURLContains(page, "PreviousPolicy")) {
					Assertion.assertBytext(page, Portal_ObjectRepository.backToPreviousSreenDialogTitle,"前のページへ戻りますか？");
					passStatusWithScreenshots(page, testCaseID, "Pop-up about 前のページへ戻りますか？ is displayed successfully after clicking on 'ご加入中の保険'","PreviousPolicyLandingPagePop-up", false);
					clickAction(page, Portal_ObjectRepository.backToPreviousSreenDialogNextButton);
					Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
				}
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Previous Policy screen","LandedToPreviousPolicyPage", true);
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "Not landed to expected Previous Policy screen","Failed_LandedToPreviousPolicyPage", false);	
			}
		}

		public void navigateToSuspendedPolicyPageFromThreeMenuList(Page page,String testCaseID) throws InterruptedException {
			try {
				threeLineMenuOptionNavigation(page, testCaseID);
				clickAction(page, Portal_ObjectRepository.menuSuspendedPolicy);
				if(!Assertion.assertURLContains(page, "PreviousPolicy")) {
					Assertion.assertBytext(page, Portal_ObjectRepository.backToPreviousSreenDialogTitle,"前のページへ戻りますか？");
					passStatusWithScreenshots(page, testCaseID, "Pop-up about 前のページへ戻りますか？ is displayed successfully after clicking on '中断した保険'","PreviousPolicyLandingPagePop-up", false);
					clickAction(page, Portal_ObjectRepository.backToPreviousSreenDialogNextButton);
					Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
				}
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Previous Policy screen","LandedToPreviousPolicyPage", true);
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "Not landed to expected Previous Policy screen","Failed_LandedToPreviousPolicyPage", false);	
			}
		}

		public void navigateToBeforeApplicationFromThreeMenuList(Page page,String testCaseID) throws InterruptedException, IOException {
			try {
				threeLineMenuOptionNavigation(page, testCaseID);
				clickAction(page, Portal_ObjectRepository.menuBeforeApplication);
				if(!Assertion.assertURLContains(page, "page7")) {
					Assertion.assertBytext(page, Portal_ObjectRepository.backToPreviousSreenDialogTitle,"前のページへ戻りますか？");
					passStatusWithScreenshots(page, testCaseID, "Pop-up about 前のページへ戻りますか？ is displayed successfully after clicking on 'お申込みの前に'","BeforeApplicationLandingPagePop-up", false);
					clickAction(page, Portal_ObjectRepository.backToPreviousSreenDialogNextButton);
					elementVisibility(page, Portal_ObjectRepository.policyPlanConfirmationDialog);
					passStatusWithScreenshots(page,testCaseID, "Policy plan confirmation pop-up is displayed successfully","PolicyPlanPop-up",false);
					clickAction(page, Portal_ObjectRepository.policyPlanConfirmationDialog);
					if(utility.aboutdriverMap.get(testCaseID).getPolicyPlan().equals("Plan2")) {
						elementVisibility(page, Portal_ObjectRepository.earthquackConfirmationDialog);
						passStatusWithScreenshots(page,testCaseID, "Earthquack confirmation pop-up is displayed successfully","earthquackPop-up",false);
						clickAction(page, Portal_ObjectRepository.earthquackConfirmationDialog);
					}

					//Driver.selectedPolicyPlanDialogConfirmation(page, testCaseID);
					Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Before Application screen","LandedToBeforeApplicationPage", true);
				}
				else if(Assertion.assertURLContains(page, "page7")) {
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Before Application screen","LandedToBeforeApplicationPage", true);
				}
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "Not landed to expected Before Application screen","Failed_LandedToBeforeApplicationPage", false);	
			}
		}

		public void navigateToPolicyHolderInformationPageFromThreeMenuList(Page page,String testCaseID) throws InterruptedException, IOException {
			try {
				threeLineMenuOptionNavigation(page, testCaseID);
				clickAction(page, Portal_ObjectRepository.menuContractor);
				if(!Assertion.assertURLContains(page, "Page8")) {
					Assertion.assertBytext(page, Portal_ObjectRepository.backToPreviousSreenDialogTitle,"前のページへ戻りますか？");
					passStatusWithScreenshots(page, testCaseID, "Pop-up about 前のページへ戻りますか？ is displayed successfully after clicking on 'ご契約者'","ContractorPagePop-up", false);
					clickAction(page, Portal_ObjectRepository.backToPreviousSreenDialogNextButton);
					Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Contractor screen","LandedToContractorPage", true);
				}
				else if(Assertion.assertURLContains(page, "page8")) {
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Contractor screen","LandedToContractorPage", true);
				}
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "Not landed to expected Contractor screen","Failed_LandedToContractorPage", false);	
			}
		}

		public void navigateToTermAndConditionPageFromThreeMenuList(Page page,String testCaseID) throws InterruptedException, IOException {
			try {
				threeLineMenuOptionNavigation(page, testCaseID);
				clickAction(page, Portal_ObjectRepository.menuMatterConfirmation);
				if(!Assertion.assertURLContains(page, "Page9")) {
					Assertion.assertBytext(page, Portal_ObjectRepository.backToPreviousSreenDialogTitle,"前のページへ戻りますか？");
					passStatusWithScreenshots(page, testCaseID, "Pop-up about 前のページへ戻りますか？ is displayed successfully after clicking on '重要事項説明書/約款の確認'","MatterConfirmationPagePop-up", false);
					clickAction(page, Portal_ObjectRepository.backToPreviousSreenDialogNextButton);
					Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Matter Confirmation screen","LandedToMatterConfirmationPage", true);
				}
				else if(Assertion.assertURLContains(page, "page9")) {
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Matter Confirmation screen","LandedToMatterConfirmationPage", true);
				}
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "Not landed to expected Matter Confirmation screen","Failed_LandedToMatterConfirmationPage", false);	
			}
		}

		public void navigateToContractDetailsConfirmationPageFromThreeMenuList(Page page,String testCaseID) throws InterruptedException, IOException {
			try {
				threeLineMenuOptionNavigation(page, testCaseID);
				clickAction(page, Portal_ObjectRepository.menuContractDetailsConfirmation);
				Assertion.assertURLContains(page, "Page10");
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to 契約内容の確認 screen","LandedTo契約内容の確認Page", true);
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "Not landed to expected 契約内容の確認 screen","Failed_LandedTo契約内容の確認Page", false);	
			}
		}

		public void navigateToPaymentPageFromThreeMenuList(Page page,String testCaseID) throws InterruptedException, IOException {
			try {
				threeLineMenuOptionNavigation(page, testCaseID);
				clickAction(page, Portal_ObjectRepository.menuPayment);
				Assertion.assertURLContains(page, "Page11");
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to お支払い screen","LandedToお支払いPage", true);
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "Not landed to expected お支払い screen","Failed_LandedToお支払いPage", false);	
			}
		}

		public String getQuotationNumber(Page page,String testCaseID) {
			String quotationNumber=null;
			try {
				Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
				boolean mobileview=false;
				if(utility.homePageMap.get(testCaseID).getBrowserView().equals("Mobile")) {
					clickAction(page, Portal_ObjectRepository.threeLineMenuButton);
					mobileview=true;
				}

				quotationNumber=getText(page, Portal_ObjectRepository.getPolicyNumber);

				if(mobileview==true) {
					clickAction(page, Portal_ObjectRepository.closeMenuButton);
				}
			}catch(AssertionError error) {
				System.out.println(error);
				failStatusWithScreenshots(page, testCaseID, "Quotation validation is not completed", "Failed_QuotationValidation", false);

			}
			return quotationNumber;

		}

		public String quotationValidation(Page page,String testCaseID) {
			String policyNumber="";
			try {
				
				boolean mobileview=false;
				if(utility.homePageMap.get(testCaseID).getBrowserView().equals("Mobile")) {
					clickAction(page, Portal_ObjectRepository.threeLineMenuButton);
					mobileview=true;
				}
				Assertion.assertIsNotEmpty(page, Portal_ObjectRepository.getPolicyNumber);
				//if(Assertion.assertURLContains(page, "ContractType")) {
					if(page.textContent(Portal_ObjectRepository.getPolicyNumber).equals("-")) {
					Assertion.assertBytext(page,Portal_ObjectRepository.getPolicyNumber,"-");
					passStatusWithScreenshots(page, testCaseID, "Quotation number is not generated", "quotationNumberValidation", false);
				}else {
					Assertion.assertBytextIsNotEqual(page,Portal_ObjectRepository.getPolicyNumber,"-");
					Assertion.assertBytext(page,Portal_ObjectRepository.getPolicyNumber,quotationNumberOfVehiclePage);
					passStatusWithScreenshots(page, testCaseID, "Quotation number is generated and same as Vehicle page as:"+getText(page, Portal_ObjectRepository.getPolicyNumber), "quotationNumberValidation", false);
				}
					policyNumber=page.textContent(Portal_ObjectRepository.getPolicyNumber);
				if(mobileview==true) {
					clickAction(page, Portal_ObjectRepository.closeMenuButton);
				}
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "Quotation validation is not completed", "Failed_QuotationValidation", false);

			}
			return policyNumber;

		}

		public void pageURLValidation(Page page,String testCaseID) throws InterruptedException {

			Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
			Thread.sleep(3000);
			String[] pageName=page.url().split("/");
			switch(pageName[pageName.length-1]){
			case "ContractType":
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Contract type screen","NavigatedToContractTypePage", true);
				break;
			case "PreviousPolicy":
				if(utility.homePageMap.get(testCaseID).getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Suspended Policy screen","NavigatedToSuspendedPolicyPage", true);
				}else {
					passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Previous Policy screen","NavigatedToPreviousPolicyPage", true);
				}
				break;
			case "Vehicle":
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Vehicle screen","NavigatedToVehiclePage", true);	
				break;
			case "Bike":
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Vehicle screen","NavigatedToVehiclePage", true);	
				break;
			case "Driver":
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Driver screen","NavigatedToDriverPage", true);	
				break;
			case "page6":
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Quotation screen","NavigatedToQuotationPage", true);	
				break;
			case "page7":
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Before Application screen","NavigatedToBeforeApplicationPage", true);	
				break;
			case "page8":
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Contractor information screen","NavigatedToContractorInfoPage", true);	
				break;
			case "page9":
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to 重要事項説明書/約款の確認 screen","NavigatedTo重要事項説明書約款の確認Page", true);	
				break;
			case "page10":
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to ご契約内容の確認 screen","NavigatedToご契約内容の確認Page", true);	
				break;
			case "page11":
				passStatusWithScreenshots(page, testCaseID, "Successfully Navigated to Payment screen","NavigatedToPaymentPage", true);	
				break;

			default:
				failStatusWithScreenshots(page, testCaseID, "unable to Navigated to expected screen","Failed_ExpectedNavigation", true);	
				break;

			}
		}

		public void headerImageIconValidationAndNavigation(Page page,String testCaseID) {
			saveDataIconValidation(page, testCaseID);
			contactCenterIconValidation(page, testCaseID);

		}
		public void saveDataIconValidation(Page page,String testCaseID) {
			try {
				String[] pageUrl=page.url().split("/");
				String pageName=pageUrl[pageUrl.length-1];
				if(pageName.startsWith("page") && !pageName.equals("page11")) {
					//if((!pageName.equals("page9") && !pageName.equals("page10") && !pageName.equals("page11"))) {
					Assertion.assertIsEnabled(page, Portal_ObjectRepository.imageOfDataSaveIcon);
					clickAction(page, Portal_ObjectRepository.imageOfDataSaveIcon);
					SaveDataDialogboxValidation(page, testCaseID,"Save data Icon");
				}else {
					Assertion.assertIsNotAttached(page, Portal_ObjectRepository.imageOfDataSaveIcon);
					Report.logger.pass("save data icon is not present for '"+pageName+"' page");
				}
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "There is problem with Save data icon navigation","Failed_SaveDataIconNavigation", true);
			}catch(Exception e) {
				//failStatusWithScreenshots(page, testCaseID, "There is problem with contact center icon page","Failed_ContactCenterPageNavigation", true);	
			}

		}

		public void contactCenterIconValidation(Page page,String testCaseID) {
			try {
				Assertion.assertIsEnabled(page, Portal_ObjectRepository.imageOfContactIcon);
				Page nextPage=page.waitForPopup(() -> {
					clickAction(page, Portal_ObjectRepository.imageOfContactIcon);	
				});
				waitForSelector(nextPage, Portal_ObjectRepository.contactPageInsuranceGlossaryButton);
				passStatusWithScreenshots(nextPage, testCaseID, "Successfully Navigated to contact Center screen after clicking on 'Contact' Icon","ContactCenterPageNavigation", true);	
				nextPage.close();
			}catch(AssertionError error) {
				failStatusWithScreenshots(page, testCaseID, "There is problem with contact center icon page","Failed_ContactCenterPageNavigation", true);	
			}catch(Exception e) {
				//failStatusWithScreenshots(page, testCaseID, "There is problem with contact center icon page","Failed_ContactCenterPageNavigation", true);	
			}

		}

		public void pageNavigation(Page page,String testCaseID,String pageName) throws InterruptedException {
			try {
				do{
					clickAction(page, Portal_ObjectRepository.proceedNext);
				}
				while(Assertion.assertURLContains(page, pageName)==false);
				pageURLValidation(page, testCaseID);
			}catch(AssertionError error) {
				System.out.println(error);
				failStatusWithScreenshots(page, testCaseID, "There is problem in page navigation","Failed_PageNavigation", true);	
			}
			catch(Exception error) {
				System.out.println(error);
				failStatusWithScreenshots(page, testCaseID, "There is problem in page navigation","Failed_PageNavigation", true);	
			}

		}

		public void BrowserBackNavigation(Page page,String testCaseID) throws InterruptedException {
			String[] pageUrl=page.url().split("/");
			String pageName=pageUrl[pageUrl.length-1];
			try {

				page.goBack();
				Assertion.assertIsEnabled(page, Portal_ObjectRepository.browserBack_Popup);
				Assertion.assertBytext(page, Portal_ObjectRepository.browserBack_PopupTitle, "ブラウザの「戻る」ボタンはご利用いただけません​");
				//System.out.println(getText(page, Portal_ObjectRepository.browserBack_PopupContent));
				Assertion.assertBytext(page, Portal_ObjectRepository.browserBack_PopupContent, "前の画面に戻る場合は、お手続き画面に戻り、画面下の「前へ」ボタンをご利用ください。Emma by AXAにログイン中のお客さまで、お見積り・ご契約の一覧ページ（ホーム）に戻りたい方は、お手続き画面に戻り、画面左上のロゴマークをクリックしてください。");
				if(!Assertion.assertURLContains(page, "sme")) {

					if(pageName.equals("contractType") || pageName.equals("PreviousPage") || pageName.equals("Vehicle") || pageName.equals("Driver") || pageName.equals("Bike")) {
						Assertion.assertIsEnabled(page, Portal_ObjectRepository.browserBack_PopupAdditionalInfo);
					}
				}
				passStatusWithScreenshots(page, testCaseID, "Pop-up after clicking on browser back button is displayed successfully", pageName+"PagebrowserBackPopupValidation",false);
				clickAction(page, Portal_ObjectRepository.browserBack_PopupCloseButton);

			}catch(AssertionError error) {
				System.out.println(error);
				failStatusWithScreenshots(page, testCaseID, "There is problem in browser back pop-up validation","Failed_"+pageName+"browserBackPopupValidation", false);	
			}

		}

	}
