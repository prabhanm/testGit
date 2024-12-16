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
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PageAssertions;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ADJ_portal_aboutMainDriverAndPolicyPlanScreen {
	public static String POLICY_PLAN_AMOUNT;
	public static boolean CORPORATE_URL=false;

	utility util = new utility();
	CommonFunctions common = new CommonFunctions();
	ADJ_portal_CommonValidation validation=new ADJ_portal_CommonValidation();

	public ParameterOfAboutMainDriverPage mainDriver(String testCaseID) throws IOException {
		ParameterOfAboutMainDriverPage para=utility.aboutdriverMap.get(testCaseID);
		return para; 
	}

	/**
	 *@Method Implementation for header validation of Main Driver questionnaires confirmation section
	 **/
	public void IsMainDriverPolicyHolderContentValidation(Page page,String testCaseId) throws IOException {

		if(CORPORATE_URL) {
			Assertion.assertBytext(page,Portal_ObjectRepository.isMainDriverPolicyHolderSectionTitle, "今回のご契約の記名被保険者は、ご契約者と同じ法人ですか？");
			Assertion.assertIsNotAttached(page,Portal_ObjectRepository.isMainDriverPolicyHolderSectionText);
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.isMainDriverPolicyHolderSectionTitle+"/../../..", testCaseId, "'IsMain driver Policy holder' section validation for SME flow", "isMainDriverPolicyHolderSectionValidation");

		}else {
			Assertion.assertBytext(page, Portal_ObjectRepository.isMainDriverPolicyHolderSectionTitle, "今回のお車を主に運転する方はご契約者と同じ方ですか？");
			Assertion.assertBytext(page, Portal_ObjectRepository.isMainDriverPolicyHolderSectionText, "「いいえ」をご選択の方は、「お申込みの前に」のページで追加のご質問にお答えください。");	
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.isMainDriverPolicyHolderSectionTitle+"/../../..", testCaseId, "'IsMain driver Policy holder' section validation for Individual flow", "isMainDriverPolicyHolderSectionValidation");
		}
	}

	/**
	 *@Method Implementation for Main Driver questionnaires confirmation
	 **/
	public void policyHolderIsMainDriverQuestionaries(Page page,String testCaseId) throws IOException {
		if(mainDriver(testCaseId).getPolicyHolderIsMainDriver().equals("はい")) {
			common.clickAction(page, Portal_ObjectRepository.mainDriverPolicyHolderConfrimation);
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.isMainDriverPopupBody);
			common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.mainDriverPolicyHolderConfrimation+"/../../../..", testCaseId,"Pop is not displayed after cliking in 'はい' button", "isMainDriverPopupValidation");
		}else if(mainDriver(testCaseId).getPolicyHolderIsMainDriver().equals("いいえ")){
			common.clickAction(page, Portal_ObjectRepository.mainDriverPolicyHolderDeny);
			if(CORPORATE_URL) {
				try {
					Assertion.assertBytext(page, Portal_ObjectRepository.isMainDriverPopupBody+"//span", "主な運転者（記名被保険者）とご契約者が同一の法人でない場合は、お申込みができません。ご不明な点がありましたら、カスタマーサービスセンターまでお電話ください。");
					System.out.println(common.getAttribute(page, "("+Portal_ObjectRepository.isMainDriverPopupBody+"//a)[1]", "href"));
					Assertion.assertHasAttribute(page, "("+Portal_ObjectRepository.isMainDriverPopupBody+"//a)[1]", "href", "https://www.axa-direct.co.jp/inquiry/?popup=true");
					Assertion.assertHasAttribute(page, "("+Portal_ObjectRepository.isMainDriverPopupBody+"//a)[2]", "href", "https://www.axa-direct.co.jp/auto/goodprice/easy_quote/range.html?popup=true");
					common.passStatusWithScreenshots(page, testCaseId, "isMainDriver pop-up content and hyperlink validation is completed", "SMEisMainDriverPopupValidation", false);
					common.clickAction(page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
				}catch(AssertionError error) {
					System.out.println(error);
					common.failStatusWithScreenshots(page, testCaseId, "isMainDriver pop-up content and hyperlink validation is not completed", "Failed_isMainDriverPopupValidation", false);
				}

			}else {
				Assertion.assertIsNotAttached(page, Portal_ObjectRepository.isMainDriverPopupBody);
				common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.mainDriverPolicyHolderDeny+"/../../../..", "Pop is not displayed after cliking in 'いいえ' button for personal", "", "PersonalisMainDriverPopupValidation");
			}
		}
	}

	/**
	 *@Method Implementation to select perfecter
	 **/
	public void selectPrefecture(Page page,String testCaseId) throws IOException {

		if(CORPORATE_URL) {
			Assertion.assertBytext(page, Portal_ObjectRepository.driverPrefectureSectionTitle, "今回お見積りのお車の保管場所となる都道府県は？");
		}else {
			Assertion.assertBytext(page, Portal_ObjectRepository.driverPrefectureSectionTitle, "主な運転者のお住まいの都道府県は？");
		}
		common.selectDropdownByText(page, Portal_ObjectRepository.prefectureSelection, mainDriver(testCaseId).getPolicyHolderPrefecture());
	}

	/**
	 *@Method Implementation to select driver DOB
	 **/
	public void selectPolicyHolderDOB(Page page,String testCaseId) throws IOException {
		if(CORPORATE_URL) {
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.driverBirthDateSection);
		}else if(utility.homePageMap.get(testCaseId).getMemberType().equals("Non Login Member")) {
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.driverBirthDateSection);
			String[] DOB=mainDriver(testCaseId).getPolicyHolderDOB().split("-");
			common.selectDropdownByText(page, Portal_ObjectRepository.driverBirthYear, DOB[2]);
			common.selectDropdownByText(page, Portal_ObjectRepository.driverBirthMonth, DOB[1]);
			common.selectDropdownByText(page, Portal_ObjectRepository.driverBirthDay,  DOB[0]);
		}
	}

	/**
	 *@Method Implementation to validate behavior of driver age range section display
	 *for both SME and Individual flow
	 **/
	public void driverAgeRangeOptionValidation(Page page,String testCaseId) throws IOException {
		try {
			if(CORPORATE_URL) {
				Assertion.assertIsEnabled(page, Portal_ObjectRepository.driverAgeRangeCondition);
				Assertion.assertBytext(page, "("+Portal_ObjectRepository.driverAgeRangeSectionRefrence+")[1]", "お車を運転される方で最も若い方を基準にご選択ください");
				Assertion.assertBytext(page, "("+Portal_ObjectRepository.driverAgeRangeSectionRefrence+")[2]", "保険始期日時点の年齢にもとづいて、運転者の年齢条件を選択してください");
				common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.driverAgeRangeSection, testCaseId, "By default Driver age range section is displayed for SME", "SME_ageRangeSectionValidation");
			}else {
				Assertion.assertIsNotAttached(page, Portal_ObjectRepository.driverAgeRangeCondition);
				selectPolicyHolderDOB(page, testCaseId);
				Assertion.assertIsEnabled(page, Portal_ObjectRepository.driverAgeRangeCondition);
				Assertion.assertBytext(page, "("+Portal_ObjectRepository.driverAgeRangeSectionRefrence+")[1]", "保険始期日時点の年齢にもとづいて、運転者の年齢条件を選択してください");
				common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.driverAgeRangeSection, testCaseId, "Driver age range section is displayed after driver DOB selection for Individul", "Individual_ageRangeSectionValidation");
			}
		}catch(AssertionError error) {
			System.out.println(error);
		}catch(Exception error) {
			System.out.println(error);
		}
	}

	/**
	 *@Method Implementation to validate model (pop-up) validation of Driver Age Limit
	 *This pop-up will display after clicking in water icon.
	 **/
	public void driverAgeLimitModelContentValidation(Page page,String testCaseId) throws IOException {
		try {
		if(CORPORATE_URL) {
			common.clickAction(page, Portal_ObjectRepository.driverAgeRangeSection+"//button");
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.driverLimitPopup+"//span[text()='運転者限定特約について']");
			Assertion.assertBytext(page, Portal_ObjectRepository.driverLimitPopup+"//span", "運転者年齢条件特約とは");
			common.passStatusWithScreenshots(page, testCaseId, "Age Limit pop-up validation for SME is completed", "ageLimitModelValidation", true);
			common.clickAction(page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
		}else {
			common.clickAction(page, "("+Portal_ObjectRepository.driverAgeRangeSection+"//button)");
			Assertion.assertBytext(page, "("+Portal_ObjectRepository.driverLimitPopup+"//span)[1]","運転者限定特約について");
			Assertion.assertBytext(page, "("+Portal_ObjectRepository.driverLimitPopup+"//span)[2]", "運転者年齢条件特約とは");
			common.passStatusWithScreenshots(page, testCaseId, "Age Limit pop-up validation for Individual flow is completed", "ageLimitModelValidation", true);
			common.clickAction(page, Portal_ObjectRepository.UndefinedConfirmBoxCloseButton);
		}
	}catch(AssertionError Error){
		common.failStatusWithScreenshots(page, testCaseId, "Age Limit pop-up validation for SME is not completed", "failed_ageLimitModelValidation", true);
	}catch(Exception Error) {
		System.out.println(Error);
	}
	}
	/**
	 *@Method Implementation to select License Type
	 **/
	public void selectLicenceType(Page page,String testCaseId) throws IOException {
		if(CORPORATE_URL) {
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.driverLicenceSection);
		}else {
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.driverLicenceSection);
			common.clickAction(page, "//img[@alt='"+mainDriver(testCaseId).getPolicyHolderLicenceType()+"']");
		}
	}

	/**
	 *@Method Implementation to select Car Passengers Limitation Questionnaires
	 **/
	public void selectCarPassengersLimitationQuestionaries(Page page,String testCaseId) throws IOException {
		if(CORPORATE_URL) {
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.carDriverLimitConfirmation);
			Assertion.assertIsNotAttached(page, Portal_ObjectRepository.carDriverLimitDeny);
		}else {
			if(mainDriver(testCaseId).getCarPaxLimitation().equals("はい")) {
				common.clickAction(page, Portal_ObjectRepository.carDriverLimitConfirmation);
			}else {
				common.clickAction(page, Portal_ObjectRepository.carDriverLimitDeny);
			}
		}
	}

	/**
	 *@Method Implementation to select driver age range
	 **/
	public void mainDriverAgeRange(Page page,String testCaseId) throws IOException {
		common.clickAction(page, Portal_ObjectRepository.driverAgeRangeSection+"//label[contains(text(),'"+mainDriver(testCaseId).getPolicyHolderAgeRange()+"')]");
	}

	/**
	 *@Method Implementation to select AEB Device Questioner
	 **/
	public void AEBdeviceQuestionerySelection(Page page,String testCaseId) throws IOException {
		if(common.isElementPresent(page, Portal_ObjectRepository.driverAemQuestionerySection))
			common.clickAction(page, Portal_ObjectRepository.driverAemQuestionerySection+"//span[contains(text(),'はい')]");
	}

	/**
	 *@Method Implementation to get Tariff plan quotation amount
	 **/
	public String getPolicyPlanAmount(Page page,String elementPath) {
		String planCharge=common.getText(page, elementPath);
		return planCharge;

	}

	/**
	 *@Method Implementation to select Tariff plan
	 **/
	public void selectPolicyPlan(Page page,String testCaseId) throws IOException, InterruptedException {
		String plan=mainDriver(testCaseId).getPolicyPlan();
		//Assertion.assertIsEnabled(page, Portal_ObjectRepository.policyPlan1);
		switch(plan) {

		case "Plan1":
			if(utility.homePageMap.get(testCaseId).getBrowserView().equals("Mobile")) {
				Assertion.assertIsEnabled(page, Portal_ObjectRepository.mobileViewSelectPolicyPlan1Tab);
				common.passStatusWithScreenshots(page,testCaseId, "Policy plan1 selected successfully","PolicyPlan1",true);
				POLICY_PLAN_AMOUNT=getQuoteAmountOfMobileView(page);
				common.clickAction(page, Portal_ObjectRepository.mobileViewSelectPolicyPlanButton);
			}else {
				POLICY_PLAN_AMOUNT=common.getText(page,  Portal_ObjectRepository.policyPlan1Quotation);
				common.clickAction(page, Portal_ObjectRepository.policyPlan1);
			}
			break;
		case "Plan2":
			if(utility.homePageMap.get(testCaseId).getBrowserView().equals("Mobile")) {
				common.clickAction(page, Portal_ObjectRepository.mobileViewSelectPolicyPlan2Tab);
				Thread.sleep(3000);
				common.passStatusWithScreenshots(page,testCaseId, "Policy plan2 selected successfully","PolicyPlan2",true);
				POLICY_PLAN_AMOUNT=getQuoteAmountOfMobileView(page);
				common.clickAction(page,  Portal_ObjectRepository.mobileViewSelectPolicyPlanButton);
			}else {
				POLICY_PLAN_AMOUNT=common.getText(page, Portal_ObjectRepository.policyPlan2Quotation);
				common.clickAction(page, Portal_ObjectRepository.policyPlan2);
			}
			break;
		case "Plan3":
			if(utility.homePageMap.get(testCaseId).getBrowserView().equals("Mobile")) {
				common.clickAction(page,  Portal_ObjectRepository.mobileViewSelectPolicyPlan3Tab);
				Thread.sleep(3000);
				common.passStatusWithScreenshots(page,testCaseId, "Policy plan3 selected successfully","PolicyPlan3",true);
				POLICY_PLAN_AMOUNT=getQuoteAmountOfMobileView(page);
				common.clickAction(page,  Portal_ObjectRepository.mobileViewSelectPolicyPlanButton);
			}else {
				POLICY_PLAN_AMOUNT=common.getText(page, Portal_ObjectRepository.policyPlan3Quotation);
				common.clickAction(page, Portal_ObjectRepository.policyPlan3);
			}
			break;
		default:
			break;
		}
	}

	/**
	 *@Method Implementation to get quotation amount for mobile view
	 **/
	public String getQuoteAmountOfMobileView(Page page) {
		String quoteamount="";
		//int count=0;
		common.waitForSelector(page, Portal_ObjectRepository.mobileViewGetPolicyQuoteAmount);
		Locator element=page.locator(Portal_ObjectRepository.mobileViewGetPolicyQuoteAmount);
		List<Locator>digitList=element.all();
		for(Locator list:digitList) {
			quoteamount=quoteamount+list.textContent();
		}
		if(quoteamount.length()<7 && quoteamount.length()>=4 && !quoteamount.contains(",")) {
			quoteamount=quoteamount.substring(0,quoteamount.length()-3)+","+quoteamount.substring(quoteamount.length()-3);
			return quoteamount;
		}else if(quoteamount.length()>6 && !quoteamount.contains(",")) {
			quoteamount=quoteamount.substring(0,quoteamount.length()-6)+","+quoteamount.substring(quoteamount.length()-6,quoteamount.length()-3)+","+quoteamount.substring(quoteamount.length()-3);
			return quoteamount;
		}else {
			return quoteamount;
		}

	}

	/**
	 *@throws InterruptedException 
	 * @Method Implementation to handle Information correction dialog
	 **/
	public void infoCorrectionDialog(Page page,String testCaseId) throws IOException, InterruptedException {
		if(utility.homePageMap.get(testCaseId).getBrowserView().equals("Mobile")) {
			if(common.elementVisibility(page, "//div[@data-aem='MobilePage_BeforeApplyModal_ModalContainer']")) {
				Assertion.assertBytext(page, "//div[@data-aem='MobilePage_BeforeApplyModal_TitleDiv']", "お申込みの前に修正が必要な情報があります。");
				Assertion.assertBytext(page, "//div[contains(@data-aem-id,'MobilePage_BeforeApplyModal_WrapperDiv01')]//p", "以下のお見積り条件が仮となっているため、保険料は概算です。お申込みにあたり、「修正する」ボタンから赤字項目の修正をお願いします。");
				common.passStatusWithScreenshots(page,testCaseId, "Information correction pop-up is displayed successfully","infoCorrectionPop-up",false);
				common.clickAction(page, "//div[@data-aem='MobilePage_BeforeApplyModal_ModalContainer']//button");
				Assertion.assertBytext(page,Portal_ObjectRepository.backToPreviousSreenDialogTitle ,"前のページへ戻りますか？");
				common.passStatusWithScreenshots(page, testCaseId, "Pop-up about 前のページへ戻りますか？ is displayed successfully after clicking 修正する button","infoCorrectionBackscreenPop-up", false);
				common.clickAction(page, Portal_ObjectRepository.backToPreviousSreenDialogNextButton);
				validation.pageURLValidation(page, testCaseId);
			}
		}else {
		if(common.elementVisibility(page, Portal_ObjectRepository.infoCorrectionDialog)) {
			Assertion.assertBytext(page, "//div[@data-aem='undefined_TitleDiv']", "お申込みの前に修正が必要な情報があります。");
			Assertion.assertBytext(page, "//div[@data-aem-id='undefined_WrapperDiv01']//p", "以下のお見積り条件が仮となっているため、保険料は概算です。お申込みにあたり、「修正する」ボタンから赤字項目の修正をお願いします。");
			common.passStatusWithScreenshots(page,testCaseId, "Information correction pop-up is displayed successfully","infoCorrectionPop-up",false);
			common.clickAction(page, Portal_ObjectRepository.infoCorrectionDialog);
			Assertion.assertBytext(page,Portal_ObjectRepository.backToPreviousSreenDialogTitle ,"前のページへ戻りますか？");
			common.passStatusWithScreenshots(page, testCaseId, "Pop-up about 前のページへ戻りますか？ is displayed successfully after clicking 修正する button","infoCorrectionBackscreenPop-up", false);
			common.clickAction(page, Portal_ObjectRepository.backToPreviousSreenDialogNextButton);
			validation.pageURLValidation(page, testCaseId);
		}
		}
	}

	/**
	 *@Method Implementation to return to previous page dialog handling
	 **/
	public void perviousPageReturnConfirmationDialog(Page page,String testCaseId) throws IOException {
		if(common.elementVisibility(page, Portal_ObjectRepository.previousPageReturnDialogConfirmation)) {
			common.passStatusWithScreenshots(page, testCaseId,"Return to Prevoius Page confirmation pop-up is displayed successfully","returnPreviousPagePop-up",false);
			common.clickAction(page, Portal_ObjectRepository.previousPageReturnDialogConfirmation);
		}
	}

	/**
	 *@Method Implementation to handle Tariff plan confirmation dialog
	 **/
	public void selectedPolicyPlanDialogConfirmation(Page page,String testCaseId) throws IOException {
		try {

			page.isEnabled(Portal_ObjectRepository.policyPlanConfirmationDialog);
			Locator element=page.locator("//li[contains(@data-aem,'DialogModal_SimpleLi')]");
			List<Locator>infoList=element.all();
			System.out.println(infoList.size());
			if(utility.homePageMap.get(testCaseId).getInsurancePurchaseType().equals("初めて自動車保険/バイク保険に加入する")) {
				if(CORPORATE_URL) {
					Assertion.assertHasCount(page, "//li[contains(@data-aem,'DialogModal_SimpleLi')]", 3);
					Assertion.assertBytext(infoList.get(0), "今回お申込みいただく記名被保険者である法人が加入していた自動車保険(共済保険を含む)で､過去13ヵ月以内に解約･満期･解除等となり､継続されていない契約がある｡※カスタマーサービスセンターまでお電話ください。");
					Assertion.assertBytext(infoList.get(1), "今回ご契約されるお車を含め、ご契約者となる法人が所有・使用し、自動車保険を契約しているお車・バイクが10台以上ある。");
					Assertion.assertBytext(infoList.get(2), "今回のご契約のご契約者または主な運転者が個人である。");

				}else {

					Assertion.assertHasCount(page, "//li[contains(@data-aem,'DialogModal_SimpleLi')]", 4);
					Assertion.assertBytext(infoList.get(0), "お車/バイクの主な運転者・配偶者またはそのご同居のご家族が加入していた自動車保険（共済保険を含む）で、過去13ヵ月以内に解約・満期・解除等となり、継続されていない契約がある。※カスタマーサービスセンターまでお電話ください。");
					Assertion.assertBytext(infoList.get(1), "今回ご契約されるお車/バイクを含め、ご契約者が所有・使用し、自動車保険を契約しているお車・バイクが10台以上ある。​");
					Assertion.assertBytext(infoList.get(2), "今回のご契約のご契約者または主な運転者が法人である。");
					Assertion.assertBytext(infoList.get(3), "今回のお車/バイクの車検証等の「自家用・事業用の別」欄が「事業用」である。​");
				}
			}else if(utility.homePageMap.get(testCaseId).getInsurancePurchaseType().equals("現在他社で加入している")) {
				if(CORPORATE_URL) {
					Assertion.assertHasCount(page, "//li[contains(@data-aem,'DialogModal_SimpleLi')]", 3);
					Assertion.assertBytext(infoList.get(0), "今回のご契約と同時にお車の変更がある。※カスタマーサービスセンターまでお電話ください。");
					Assertion.assertBytext(infoList.get(1), "今回ご契約されるお車を含め、ご契約者となる法人が所有・使用し、自動車保険を契約しているお車・バイクが10台以上ある。");
					Assertion.assertBytext(infoList.get(2), "今回のご契約のご契約者または主な運転者が個人である。");

				}else {
					Assertion.assertHasCount(page, "//li[contains(@data-aem,'DialogModal_SimpleLi')]", 4);
					Assertion.assertBytext(infoList.get(0), "今回のご契約と同時にお車/バイクの変更がある。※カスタマーサービスセンターまでお電話ください。");
					Assertion.assertBytext(infoList.get(1), "今回ご契約されるお車/バイクを含め、ご契約者が所有・使用し、自動車保険を契約しているお車・バイクが10台以上ある。");
					Assertion.assertBytext(infoList.get(2), "今回のご契約のご契約者または主な運転者が法人である。");
					Assertion.assertBytext(infoList.get(3), "今回のお車/バイクの車検証等の「自家用・事業用の別」欄が「事業用」である。​");
				}
			}else if(utility.homePageMap.get(testCaseId).getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				if(CORPORATE_URL) {
					Assertion.assertHasCount(page, "//li[contains(@data-aem,'DialogModal_SimpleLi')]", 2);
					Assertion.assertBytext(infoList.get(0), "今回ご契約されるお車を含め、ご契約者となる法人が所有・使用し、自動車保険を契約しているお車・バイクが10台以上ある。");
					Assertion.assertBytext(infoList.get(1), "今回のご契約のご契約者または主な運転者が個人である。");

				}else {
					Assertion.assertHasCount(page, "//li[contains(@data-aem,'DialogModal_SimpleLi')]", 3);
					Assertion.assertBytext(infoList.get(0), "今回ご契約されるお車を含め、ご契約者が所有・使用し、自動車保険を契約しているお車・バイクが10台以上ある。");
					Assertion.assertBytext(infoList.get(1), "今回のご契約のご契約者または主な運転者が法人である。");
					Assertion.assertBytext(infoList.get(2), "今回のお車の車検証上の「自家用・事業用の別」欄が「事業用」である。");

				}
			}
			common.passStatusWithScreenshots(page,testCaseId, "Policy plan confirmation pop-up is displayed successfully","PolicyPlanPop-up",false);
			common.clickAction(page, Portal_ObjectRepository.policyPlanConfirmationDialog);
		}catch(AssertionError error) {
			System.out.println(error);
			common.passStatusWithScreenshots(page,testCaseId, "Policy plan confirmation pop-up Text validation is not completed","Failed_PolicyPlanPop-upValidation",false);
		}
		catch(Exception error) {
			//System.out.println(error);
			common.passStatusWithScreenshots(page,testCaseId, "Policy plan confirmation pop-up Text validation is not completed","Failed_PolicyPlanPop-upValidation",false);
		}

	}

	/**
	 *@Method Implementation to handle Earthquake confirmation dialog
	 **/
	public void selectedEarthquackDialogConfirmation(Page page,String testCaseId) throws IOException {
		if((utility.homePageMap.get(testCaseId).getInsurnaceProductType().equals("自動車保険（四輪）") && mainDriver(testCaseId).getPolicyPlan().equals("Plan2"))){
			common.elementVisibility(page, Portal_ObjectRepository.earthquackConfirmationDialog);
			common.passStatusWithScreenshots(page,testCaseId, "Earthquack confirmation pop-up is displayed successfully","earthquackPop-up",false);
			common.clickAction(page, Portal_ObjectRepository.earthquackConfirmationDialog);
		}
	}

	/**
	 *@Method Implementation for Driver page functional validation
	 **/
	public void functionalValidationOfMainDriverPage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.PageLogoValidation(page, testCaseID);
			validation.quotationValidation(page, testCaseID);
			validation.chatBotValidation(page, testCaseID);
			IsMainDriverPolicyHolderContentValidation(page, testCaseID);
			driverAgeRangeOptionValidation(page, testCaseID);
			driverAgeLimitModelContentValidation(page, testCaseID);
			menuOptionValidationOfMainDriverPage(page, testCaseID);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Functional Validation of Driver page is not completed", "Failed_DriverPageFunctionalValidation", true);
			//common.failStatusWithExceptions(page, testCaseID,"Exception of Functional Validation of Driver page is recorded", "Failed_ExceptionDriverPageFunctionalValidation", error,true);
		}catch(Exception e) {
			//System.out.println(error);
			//common.failStatusWithScreenshots(page, testCaseID, "Functional Validation of Driver page is not completed", "Failed_DriverPageFunctionalValidation", true);
			common.failStatusWithExceptions(page, testCaseID,"Exception of Functional Validation of Driver page is recorded", "Failed_ExceptionDriverPageFunctionalValidation", e,true);
		}
	}

	/**
	 *@Method Implementation to validate menu options for Driver page
	 **/
	public void menuOptionValidationOfMainDriverPage(Page page,String testCaseID) throws IOException, InterruptedException {
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
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuMainDriver);
			Assertion.assertHasAttributePattern(page, Portal_ObjectRepository.menuQuotation+"//img", "src", "icon-check-gray");
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuSaveContent);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuContactCenter);
			common.passStatusWithScreenshots(page, testCaseID, "Menu options of Main Driver page is validated successfully" , "MenuOptionValidationMainDriverPage", true);
			common.clickAction(page, Portal_ObjectRepository.closeMenuButton);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Menu options of MainDriver page is not validated completely", "Failed_MenuOptionValidationMainDriverPage", true);
		}
	}

	/**
	 *@Method Implementation to validate menu options for Tariff page
	 **/
	public void menuOptionValidationOfPolicyPlanPage(Page page,String testCaseID) throws IOException, InterruptedException {
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
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuMainDriver);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuQuotation);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuSaveContent);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuContactCenter);
			common.passStatusWithScreenshots(page, testCaseID, "Menu options of Tarrif page is validated successfully" , "MenuOptionValidationOfTarrifPage", true);
			common.clickAction(page, Portal_ObjectRepository.closeMenuButton);
		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Menu options of Tarrif page is not validated completely", "Failed_MenuOptionValidationOftarrifPage", true);
		}
	}

	/**
	 *@Method Implementation for Tariff page functional validation
	 **/
	public void functionalValidationOfPolicyPlanPage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			validation.PageLogoValidation(page, testCaseID);
			validation.quotationValidation(page, testCaseID);
			validation.chatBotValidation(page, testCaseID);
			policyPlanAndQuoteAmountValidation(page, testCaseID);
			validation.headerImageIconValidationAndNavigation(page, testCaseID);
			menuOptionValidationOfPolicyPlanPage(page, testCaseID);

		}catch(AssertionError error) {
			System.out.println(error);
			common.failStatusWithScreenshots(page, testCaseID, "Functional Validation of Driver page is not completed", "Failed_DriverPageFunctionalValidation", true);
		}
	}


	/**
	 *@Method Implementation to validate Tariff plan quotation amount
	 **/
	public void policyPlanAndQuoteAmountValidation(Page page,String testCaseId) {
		try {
			if(utility.homePageMap.get(testCaseId).getBrowserView().equals("Mobile")){

			}else {
				Assertion.assertIsNotEmpty(page, Portal_ObjectRepository.policyPlan1Quotation);
				Assertion.assertIsNotEmpty(page, Portal_ObjectRepository.policyPlan2Quotation);
				Assertion.assertIsNotEmpty(page, Portal_ObjectRepository.policyPlan3Quotation);
				Assertion.assertIsEnabled(page, Portal_ObjectRepository.policyPlan1);
				Assertion.assertIsEnabled(page, Portal_ObjectRepository.policyPlan2);
				Assertion.assertIsEnabled(page, Portal_ObjectRepository.policyPlan3);
			}
			common.passStatusWithScreenshots(page, testCaseId, "All 3 policy Plan with quotation amount is displaying" , "PolicyPlanQuoteAmountValidation", false);
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseId, "There is problem in display of  policy Plan with quotation amount" , "Failed_PolicyPlanQuoteAmountValidation", false);
		}
	}

	/**
	 *@Method Implementation for error message validation of driver page.
	 **/
	public void ErrorMessageValidationOfMainDriverPage(Page page,String testCaseId,String errorMessage) throws IOException {
		//CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		common.waitForSelector(page, Portal_ObjectRepository.prefectureSelection);
		common.clickAction(page, Portal_ObjectRepository.proceedNext);
		try {
			Assertion.assertBytext(page, Portal_ObjectRepository.maindriverPrefectureFieldError, errorMessage);
			if(!CORPORATE_URL) {
				Assertion.assertBytext(page, Portal_ObjectRepository.mainDriverDOBFieldError, errorMessage);
				Assertion.assertBytext(page, Portal_ObjectRepository.mainDriverLicenceFieldError, errorMessage);
			}
			common.passStatusWithScreenshots(page, testCaseId, "Error for all Mandetory field of Main Driver page is displaying as:'"+errorMessage+"'", "MainDriverPageFieldErrorValidation", true);
		}catch(AssertionError error) {
			common.failStatusWithScreenshots(page, testCaseId, "Expected Error Message:'"+errorMessage+"' of one or all field of Main Driver page is different", "Failed_MainDriverPageFieldErrorValidation", true);
		}

	}

	/**
	 *@Method Implementation for E2E flow of Driver page
	 **/
	public void e2eFlowOfAboutMainDriver(Page page,String testCaseId,String productType,String flowType) throws IOException, InterruptedException {
		policyHolderIsMainDriverQuestionaries(page, testCaseId);
		selectPrefecture(page, testCaseId);
		selectPolicyHolderDOB(page, testCaseId);
		selectLicenceType(page, testCaseId);
		if(productType.equals("自動車保険（四輪）")) {
			selectCarPassengersLimitationQuestionaries(page, testCaseId);
		}
		mainDriverAgeRange(page, testCaseId);
		AEBdeviceQuestionerySelection(page, testCaseId);
		common.passStatusWithScreenshots(page,testCaseId, "Data has been entered successfully in Main Driver page","MainDriverPage",true);
		common.clickAction(page, Portal_ObjectRepository.proceedNext);
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		if(!utility.homePageMap.get(testCaseId).getBrowserView().equals("Mobile")) {
			common.waitForSelector(page, Portal_ObjectRepository.policyPlan1);
			common.passStatusWithScreenshots(page,testCaseId, "Policy plan page displayed successfully","PolicyPlanPage",false);
		}
	}

	/**
	 *@Method Implementation for E2E flow of Tariff page
	 **/
	public void e2eFlowOfPolicyPlan(Page page,String testCaseId) throws IOException, InterruptedException {

		selectPolicyPlan(page, testCaseId);
		selectedPolicyPlanDialogConfirmation(page, testCaseId);
		selectedEarthquackDialogConfirmation(page, testCaseId);
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		common.passStatusWithScreenshots(page, testCaseId,"Successfully proceeded to Vehicle Information page","proceededToVehicleInformationPage",true);
		ADJ_portal_vehicleInformationScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");
	}

	/**
	 *@Method Implementation for E2E flow of Driver and Tariff page
	 **/
	public void e2eFlowOfAboutMainDriverAndPolicyPlan(Page page,String testCaseId,String productType,String flowType) throws IOException, InterruptedException {
		//CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		e2eFlowOfAboutMainDriver(page, testCaseId, productType, flowType);
		e2eFlowOfPolicyPlan(page, testCaseId);
		//ADJ_portal_vehicleInformationScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");

	}

	/**
	 *@Method Implementation for E2E flow of Driver and Tariff page
	 **/
	public void tarrifPageContentValidation(Page page,String testCaseId,String sectionHeaderName) throws IOException, InterruptedException {
		String sectionHeaderElement="//div[contains(text(),'"+sectionHeaderName+"')]";
		String sectionElement=sectionHeaderElement+"/../..//div[contains(@data-aem,'ChoiceDetail_DetailBody')]";


	}

	/**
	 *@Method Method Implementation for Range of people being driven(運転される方の範囲)Tariff page
	 **/
	public void tarrifPageAgeRangeSectionValidation(Page page,String testCaseId) throws IOException, InterruptedException {
		try{
			//String sectionHeaderElement="//div[contains(text(),'"+sectionHeaderName+"')]";
			if(CORPORATE_URL) {
				if(utility.homePageMap.get(testCaseId).getBrowserView().equals("Mobile")) {
					selectPolicyPlan(page, testCaseId);
					Assertion.assertIsNotAttached(page, "//p[text()='運転者限定特約']/..");
					Report.logger.info("運転者限定特約 section is not attached for SME flow");
					Assertion.assertIsEnabled(page, "//p[text()='運転者年齢条件特約']/..");
					//common.passStatusWithScreenshots(page, testCaseId,"運転者年齢条件特約 Section is present for SME flow", "運転者年齢条件特約SectionValidation",false);
					common.takeScreenShotsOfComponent(page, "//p[text()='運転者年齢条件特約']/../..", testCaseId, "運転者年齢条件特約 Section is present for SME flow", "運転者年齢条件特約SectionValidation");

				}else {
					Assertion.assertIsNotAttached(page, Portal_ObjectRepository.tarrifPage_DriverSpecialAgreementSection);
					Report.logger.info("運転者限定特約 section is not attached for SME flow");
					Assertion.assertIsEnabled(page, Portal_ObjectRepository.tarrifPage_DriverAgeRangeSection);
					common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.tarrifPage_DriverAgeRangeSection, testCaseId, "運転者年齢条件特約 Section is present for SME flow", "運転者年齢条件特約SectionValidation");
				}

			}else {
				if(utility.homePageMap.get(testCaseId).getBrowserView().equals("Mobile")) {
					selectPolicyPlan(page, testCaseId);
					Assertion.assertIsEnabled(page,"//p[text()='運転者限定特約']/..");
					common.takeScreenShotsOfComponent(page, "//p[text()='運転者限定特約']/..", testCaseId, "運転者限定特約 Section is present for Individual flow", "運転者限定特約SectionValidation");
					Assertion.assertIsEnabled(page, "//p[text()='運転者年齢条件特約']/..");
					common.takeScreenShotsOfComponent(page, "//p[text()='運転者年齢条件特約']/..", testCaseId, "運転者年齢条件特約 Section is present for Individual flow", "運転者年齢条件特約SectionValidation");

				}else {
					Assertion.assertIsEnabled(page, Portal_ObjectRepository.tarrifPage_DriverSpecialAgreementSection);
					common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.tarrifPage_DriverSpecialAgreementSection, testCaseId, "運転者限定特約 Section is present for Individual flow", "運転者限定特約SectionValidation");
					Assertion.assertIsEnabled(page, Portal_ObjectRepository.tarrifPage_DriverAgeRangeSection);
					common.takeScreenShotsOfComponent(page, Portal_ObjectRepository.tarrifPage_DriverAgeRangeSection, testCaseId, "運転者年齢条件特約 Section is present for Individual flow", "運転者年齢条件特約SectionValidation");
				}
			}

		}catch(Exception e){

		}

	}

	/**
	 *@Method Method Implementation to check drop-down default value of Tariff page
	 **/
	public void tarrifPageDropdownDefaultContentValidation(Page page,String testCaseId,String sectionHeaderName) throws IOException, InterruptedException {
		try{
			String sectionHeaderElement="(//div[text()='"+sectionHeaderName+"']/../..//select[@class='form-select'])";
			//int locatorCount=page.locator(sectionHeaderElement).count();
			common.clickAction(page, sectionHeaderElement);

			/*
			 * if(CORPORATE_URL) { Assertion.assertIsNotAttached(page,
			 * Portal_ObjectRepository.tarrifPage_DriverSpecialAgreementSection);
			 * common.takeScreenShotsOfComponent(page,
			 * Portal_ObjectRepository.tarrifPage_DriverSpecialAgreementSection, testCaseId,
			 * "運転者限定特約 Section is not attached for SME flow", "運転者限定特約SectionValidation");
			 * }
			 */
			String defaultSelectedValue = page.evalOnSelector(sectionHeaderElement, "select => select.selectedOptions[0].text").toString();
			common.passStatusWithScreenshots(page, testCaseId,"drop-down is present and default delected value is "+defaultSelectedValue,"DefaultdropdownValueValidation",false);
			System.out.println("Default selected value: " + defaultSelectedValue);

		}catch(Exception e){
			common.failStatusWithScreenshots(page, testCaseId,"Either dropdown is not present or default selected value is not matching  ","Failed_DefaultdropdownValueValidation",false);
		}

	}


	/**
	 *@Method Implementation to click on infoIcon of criteria section of Tariff page
	 **/
	public void tarrifPageSectionInfoIconClick(Page page,String testCaseId,String sectionHeaderName,int iconNumber){
		try {
			String sectionHeaderElement="(//div[text()='"+sectionHeaderName+"']/../..//img)";
			int locatorCount=page.locator(sectionHeaderElement).count();
			if(locatorCount==1 || iconNumber>locatorCount ) {
				common.clickAction(page, sectionHeaderElement+"[1]");

			}else if(iconNumber>1) {
				common.clickAction(page, sectionHeaderElement+"["+iconNumber+"]");
			}
			common.passStatusWithScreenshots(page, testCaseId,"Info pop-up is displayed successfully after clicking on info icon of "+sectionHeaderName+ " section",sectionHeaderName+"SectionIconClick",false);;

		}catch(Exception e) {
			common.failStatusWithScreenshots(page, testCaseId,"Info pop-up is not displayed successfully after clicking on info icon of "+sectionHeaderName+" section",sectionHeaderName+"SectionIconClick",false);
		}



	}

	/**
	 *@Method Implementation of header Info navigation of Tariff page
	 **/
	public void tarrifPageHeaderInfoModelPopupNavigation(Page page,String testCaseId,String sectionHeaderName){
		try {
			if(utility.homePageMap.get(testCaseId).getBrowserView().equals("Mobile")) {
				Assertion.assertIsAttached(page, "//p[text()='"+sectionHeaderName+"']/../..");
				common.takeScreenShotsOfComponent(page, "//p[text()='"+sectionHeaderName+"']/../..",testCaseId, "image of "+sectionHeaderName+" is displayed successfully", sectionHeaderName+"ImageValidation");
				common.clickAction(page, "//p[text()='"+sectionHeaderName+"']/../../div[1]//img");
			}else {
				Assertion.assertIsAttached(page, "(//div[text()='"+sectionHeaderName+"']/../..)[1]");
				common.takeScreenShotsOfComponent(page, "(//div[text()='"+sectionHeaderName+"']/../..)[1]",  testCaseId, "image of "+sectionHeaderName+" is displayed successfully", sectionHeaderName+"ImageValidation");
				common.clickAction(page, "(//div[text()='"+sectionHeaderName+"'])[1]/..//div[@data-testid='OpenModalButtonContainer']");
			}

			common.passStatusWithScreenshots(page, testCaseId,"Model pop-up is displayed successfully after clicking on 詳細 button of "+sectionHeaderName+" section",sectionHeaderName+"詳細Pop-up",false);
		}catch(Exception e) {
			common.passStatusWithScreenshots(page, testCaseId,"Model pop-up is displayed successfully after clicking on 詳細 button of "+sectionHeaderName+" section","Failed_"+sectionHeaderName+"詳細Pop-up",false);
		}



	}

	/**
	 *@Method Implementation of model block footer more info(もっと詳しく) pop-up navigation of Tariff page
	 **/
	public void tarrifPageModelBlockMoreInfoPopupNavigation(Page page,String testCaseId,String modelFooterSection){
		try {
			if(utility.homePageMap.get(testCaseId).getBrowserView().equals("Mobile")) {
				Assertion.assertIsAttached(page, "//div[text()='"+modelFooterSection+"']/../../..");
				common.takeScreenShotsOfComponent(page, "//div[text()='"+modelFooterSection+"']/../../..",testCaseId, "Model footer section as "+modelFooterSection+" is displayed successfully", modelFooterSection+"DisplayValidation");
				Thread.sleep(3000);
				common.clickAction(page, "//div[text()='"+modelFooterSection+"']/../../../..//button[text()='もっと詳しく']");
			}else {
				Assertion.assertIsAttached(page, "//p[text()='"+modelFooterSection+"']/../..");
				common.takeScreenShotsOfComponent(page,"//p[text()='"+modelFooterSection+"']/../..",  testCaseId, "Model footer section as "+modelFooterSection+" is displayed successfully", modelFooterSection+"DisplayValidation");
				Thread.sleep(3000);
				common.clickAction(page, "//p[text()='"+modelFooterSection+"']/../..//button[text()='もっと詳しく']");
			}

			common.passStatusWithScreenshots(page, testCaseId,"Model pop-up is displayed successfully after clicking on もっと詳しく button of "+modelFooterSection+" section",modelFooterSection+"もっと詳しくPop-up",true);

		}catch(Exception e) {
			common.failStatusWithScreenshots(page, testCaseId,"Model pop-up is displayed successfully after clicking on もっと詳しく button of "+modelFooterSection+" section","Failed_"+modelFooterSection+"SectionIconClick",true);
		}



	}


}
