package org.axa.portal.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.axa.framework.Assertion;
import org.axa.framework.CommonFunctions;
import org.axa.framework.Portal_ObjectRepository;
import org.axa.framework.Report;
import org.axa.portal.validation.ADJ_portal_CommonValidation;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ADJ_portal_ContractConfirmationScreen extends ADJ_portal_CommonValidation {
	public static boolean CORPORATE_URL=false;
	public int FAIL_COUNT=0;
	public String POLICYHOLDER_NAME=null;
	public String POLICYHOLDER_NAME_KANA=null;
	public String VEHICLE_TYPE=null;
	public String VEHICLE_NAME=null;
	public String VEHICLE_MODEL=null;
	public String POSTLE_CODE=null;
	public String ADDRESS=null;
	public String CORPOARTE_NAME=null;
	public String CORPOARTE_NAME_KANA=null;
	public String PHONE_NUMBER=null;
	public String EMAIL=null;
	public String PAYMENT_MODE=null;
	public String EXECUTION_FLOW=null;

	public String MATURITY_DATE=null;
	public String INCEPTION_DATE=null;
	public String PREMIUM_AMOUNT=null;
	public String COVERAGE_TYPE=null;
	public String COVERAGE_AMOUNT=null;
	public String DOB=null;
	public String CORPORATE_STATUS=null;
	public String CORPORATE_POSITION=null;
	public String CORPORATE_TITLE=null;
	public String CORPORATE_TITLE_KANA=null;
	public static List<String> dataList;

	public void dataValidationOfContractTypePage(Page page,String testCaseID) {
		ParameterOfHomeAndQuotationPage excelData=utility.homePageMap.get(testCaseID);
		String[] ide;
		if(excelData.getInsurancePurchaseType().equals("現在他社で加入している")) {
			ide=utility.currentInsuranceMap.get(testCaseID).getExpiaryDateOfCI().split("-");
		}else {
			ide=excelData.getPurchaseInsurnaceInceptionDate().split("-");
		}

		if((ide[1].length()==2 && ide[1].substring(0,1).equals("0")) ) {
			ide[1]=ide[1].replaceFirst("0", "");
		}
		if((ide[0].length()==2 && ide[0].substring(0,1).equals("0") )) {
			ide[0]=ide[0].replaceFirst("0", "");
		}

		String sectionNumber=getText(page, "//span[text()='ご契約状況について']//preceding-sibling::div");
		String section="//section[@data-aem-id='ContractConfirm_ContractForm_PageSection_"+sectionNumber+"']";
		try {
			/*
			 * *@INS33 * is present for ご契約状況
			 */
			if(excelData.getInsurnaceProductType().equals("自動車保険（四輪）")) {
				VEHICLE_TYPE="AUTO";
			}else {
				VEHICLE_TYPE="BIKE";
			}
			PAYMENT_MODE=utility.paymentInfoMap.get(testCaseID).getPaymentMode();
			EXECUTION_FLOW=excelData.getInsurnaceFlowType();
			Assertion.assertBytextContains(page, section+"//div[text()='ご契約状況']//following-sibling::div//div", excelData.getInsurancePurchaseType());
			Assertion.assertBytext(page, section+"//div[text()='保険の種類']//following-sibling::div//div", excelData.getInsurnaceProductType());
			if(CORPORATE_URL) {
				Assertion.assertBytext(page, section+"//div[text()='保険の区分★']//following-sibling::div//div", "法人");
			}else{
				Assertion.assertBytext(page, section+"//div[text()='保険の区分★']//following-sibling::div//div", "個人");
			}
			/*
			 * *@INS33 * is present for 保険始期日
			 */
			if(ADJ_portal_currentInsuranceScreen.INSURANCE_START_DATE==null) {
				System.out.println("in case of perviousPolicy previousDate: "+ADJ_portal_currentInsuranceScreen.INSURANCE_START_DATE);
				Assertion.assertBytextContains(page, section+"//div[text()='保険始期日']//following-sibling::div//div", ide[2]+"年"+ide[1]+"月"+ide[0]+"日");
			}else {
				System.out.println("in case of perviousPolicy previousDate: "+ADJ_portal_currentInsuranceScreen.INSURANCE_START_DATE);
				Assertion.assertBytextContains(page, section+"//div[text()='保険始期日']//following-sibling::div//div",ADJ_portal_currentInsuranceScreen.INSURANCE_START_DATE );
			}
			System.out.println("There is no error in date validation");
		}catch(AssertionError error) {
			FAIL_COUNT++;
			System.out.println(error);
			failStatusWithScreenshots(page, testCaseID, "One of the Data entered in ContractType page is not matching", "ContractTypeDataValidation", false);
		}
	}

	public void dataValidationOfVehiclePage(Page page,String testCaseID) {
		ParameterOfHomeAndQuotationPage excelData=utility.quotationPageMap.get(testCaseID);
		String[] registrationMonth=excelData.getCarRegistrationPeriod().split("-");

		String sectionNumber=getText(page, "//span[text()='契約車両について']//preceding-sibling::div");
		String section="//section[@data-aem-id='ContractConfirm_ContractForm_PageSection_"+sectionNumber+"']";
		try {

			if(utility.homePageMap.get(testCaseID).getInsurnaceProductType().equals("バイク保険（二輪）")) {
				Assertion.assertBytext(page, section+"//div[text()='排気量/定格出力★']//following-sibling::div//div",excelData.getBikeDisplacement());
				String threeWheelInfo=utility.quotationPageMap.get(testCaseID).getBikeDisplacement();

				if(threeWheelInfo.equals("51cc以上125cc以下") || threeWheelInfo.equals("0.61kW以上1.00kW以下")) {
					switch(utility.quotationPageMap.get(testCaseID).getThreeWheeVechileConfirmation()) {
					case "いいえ":
						Assertion.assertBytext(page, section+"//div[text()='側車']//following-sibling::div//div","なし");
						break;
					case "はい":
						Assertion.assertBytext(page, section+"//div[text()='側車']//following-sibling::div//div","あり");
						break;
					}
				}
				Assertion.assertBytext(page, section+"//div[text()='メーカー名★']//following-sibling::div//div",excelData.getManufacturerCompanay());
				Assertion.assertBytext(page, section+"//div[text()='バイク名★']//following-sibling::div//div",excelData.getCar_BikeModel());
				Assertion.assertBytext(page, section+"//div[text()='年間予想最大走行距離★']//following-sibling::div//div", excelData.getCarBikeMileage());
				Assertion.assertBytext(page, section+"//div[text()='主な使用目的★']//following-sibling::div//div", excelData.getVechilePurpose());
				VEHICLE_NAME=getText(page, section+"//div[text()='メーカー名★']//following-sibling::div//div")+" "+getText(page, section+"//div[text()='バイク名★']//following-sibling::div//div");

			}else {
				if(excelData.getSearchCarByManufacturerCheckbox().equals("はい")) {
					Assertion.assertBytext(page, section+"//div[text()='メーカー名★']//following-sibling::div//div",ADJ_portal_quotationScreen.CAR_MANUFACTURERE_AFTER_SELECTION );
					Assertion.assertBytext(page, section+"//div[text()='車名★']//following-sibling::div//div", ADJ_portal_quotationScreen.CAR_NAME_AFTER_SELECTION);
					Assertion.assertBytextContains(page, section+"//div[text()='型式★']//following-sibling::div//div", ADJ_portal_quotationScreen.CAR_MODEL_NUMBER_AFTER_SELECTION);
				}else {
					Assertion.assertBytextContains(page, section+"//div[text()='型式★']//following-sibling::div//div", excelData.getCar_BikeModel());	
				}
				System.out.println(("（"+registrationMonth[0]+"年） "+registrationMonth[1]+"月"));
				Assertion.assertBytextContains(page, section+"//div[text()='初度登録年月/初度検査年月★']//following-sibling::div//div", ("（"+registrationMonth[0]+"年）"+registrationMonth[1]+"月"));
				Assertion.assertBytext(page, section+"//div[text()='年間予想最大走行距離★']//following-sibling::div//div", excelData.getCarBikeMileage());
				Assertion.assertBytext(page, section+"//div[text()='主な使用目的★']//following-sibling::div//div", excelData.getVechilePurpose());
				VEHICLE_NAME=getText(page, section+"//div[text()='メーカー名★']//following-sibling::div//div")+" "+getText(page, section+"//div[text()='車名★']//following-sibling::div//div");

				if(!CORPORATE_URL) {
					if(excelData.getCarUsingwithChildren().equals("いいえ")) {
						Assertion.assertBytext(page, section+"//div[text()='乳幼児童同乗（お子さまを乗せてのお車の使用）★']//following-sibling::div//div", "なし");
					}else {
						Assertion.assertBytext(page, section+"//div[text()='乳幼児童同乗（お子さまを乗せてのお車の使用）★']//following-sibling::div//div", "あり");
						Assertion.assertBytext(page, section+"//div[text()='最若年のお子さまの年齢']//following-sibling::div//div", excelData.getChildrenAge());
					}
				}

			}
		}catch(AssertionError error) {
			FAIL_COUNT++;
			System.out.println(error);
			failStatusWithScreenshots(page, testCaseID, "One of the Data entered in Vehicle page is not matching", "VehicleDataValidation", false);
		}
	}

	public void dataValidationOfDriverPage(Page page,String testCaseID) {
		ParameterOfAboutMainDriverPage excelData=utility.aboutdriverMap.get(testCaseID);
		String[] dob=excelData.getPolicyHolderDOB().split("-");
		DOB=dob[2]+"/"+dob[1]+"/"+dob[0];

		if(dob[1].length()==2 && dob[1].substring(0,1).equals("0") ) {
			dob[1]=dob[1].replaceFirst("0", "");
		}
		if(dob[0].length()==2 && dob[0].substring(0,1).equals("0") ) {
			dob[0]=dob[0].replaceFirst("0", "");
		}

		String sectionNumber=getText(page, "//span[text()='主な運転者(記名被保険者)について']//preceding-sibling::div");
		String section="//section[@data-aem-id='ContractConfirm_ContractForm_PageSection_"+sectionNumber+"']";
		try {
			if(CORPORATE_URL) {
				DOB="";
				Assertion.assertBytext(page, section+"//div[text()='主な運転者']//following-sibling::div//div", "契約者と同一の法人");
				Assertion.assertBytext(page, section+"//div[text()='お車の保管場所の都道府県★']//following-sibling::div//div", excelData.getPolicyHolderPrefecture());
			}else {
				if(excelData.getPolicyHolderIsMainDriver().equals("はい")) {
					/*
					 * *@INS33 * is present for 主な運転者とご契約者との関係
					 */
					Assertion.assertBytext(page, section+"//div[text()='主な運転者とご契約者との関係']//following-sibling::div//div", "本人");

				}else {
					/*
					 * *@INS33 * is present for 主な運転者とご契約者との関係
					 */
					Assertion.assertBytext(page, section+"//div[text()='主な運転者とご契約者との関係★']//following-sibling::div//div", "本人");	
				}

				Assertion.assertBytext(page, section+"//div[text()='お住まいの都道府県★']//following-sibling::div//div", excelData.getPolicyHolderPrefecture());
				Assertion.assertBytext(page, section+"//div[text()='運転免許証の色★']//following-sibling::div//div", excelData.getPolicyHolderLicenceType());
				//System.out.println(dob[2]+"年"+dob[1]+"月"+dob[0]+"日");
				Assertion.assertBytext(page, section+"//div[text()='生年月日★']//following-sibling::div//div", dob[2]+"年"+dob[1]+"月"+dob[0]+"日");
			}

		}catch(AssertionError error) {
			FAIL_COUNT++;
			System.out.println(error);
			failStatusWithScreenshots(page, testCaseID, "One of the Data entered in Driver page is not matching", "driverPageDataValidation", false);
		}
	}

	public void dataValidationOfVehicleInfoPage(Page page,String testCaseID) {

		ParameterOfCorporate_PolicyHolderInformationPage SME_ContractorInfo=utility.SME_ContractorInfoMap.get(testCaseID);
		ParameterOfVehicleInformationPage excelData=utility.vehicleInfoMap.get(testCaseID);
		String CORPORATE_NAME;
		String CORPORATE_NAME_KANA;

		String[] mileageCheckDate=excelData.getVehicleMileageCheckDate().split("-");

		if(mileageCheckDate[1].length()==2 && mileageCheckDate[1].substring(0,1).equals("0") ) {
			mileageCheckDate[1]=mileageCheckDate[1].replaceFirst("0", "");
		}
		if(mileageCheckDate[0].length()==2 && mileageCheckDate[0].substring(0,1).equals("0") ) {
			mileageCheckDate[0]=mileageCheckDate[0].replaceFirst("0", "");
		}

		String sectionNumber=getText(page, "//span[text()='ご確認内容/詳細情報について']//preceding-sibling::div");
		String section="//section[@data-aem-id='ContractConfirm_ContractForm_PageSection_"+sectionNumber+"']";

		try {
			Assertion.assertIsChecked(page, "//label[@for='NoneOfThem01']");

			if(CORPORATE_URL) {

				if(SME_ContractorInfo.getCorporateStatusPosition().equals("法人名の前")) {
					CORPORATE_NAME=SME_ContractorInfo.getLegalEntityType()+" "+SME_ContractorInfo.getCorporateName();
					CORPORATE_NAME_KANA=SME_ContractorInfo.getLegalEntityType()+" "+SME_ContractorInfo.getCorporateNameKana();

				}else {
					CORPORATE_NAME=SME_ContractorInfo.getCorporateName()+" "+SME_ContractorInfo.getLegalEntityType();
					CORPORATE_NAME_KANA=SME_ContractorInfo.getCorporateNameKana()+" "+SME_ContractorInfo.getLegalEntityType();
				}

				Assertion.assertBytext(page, section+"//div[text()='法人名称★']//following-sibling::div//div",CORPORATE_NAME);
				Assertion.assertBytext(page, section+"//div[text()='法人名称（カナ）']//following-sibling::div//div",CORPORATE_NAME_KANA);
				Assertion.assertBytext(page, section+"//div[text()='お車の車検証上の所有者★']//following-sibling::div//div", "契約者と同一の法人");	
			}else {
				Assertion.assertBytext(page, section+"//div[text()='性別']//following-sibling::div//div",excelData.getGender());

				if(excelData.getPolicyHolderIsOwner().equals("はい")) {
					Assertion.assertBytext(page, section+"//div[text()='お車の車検証上の所有者★']//following-sibling::div//div", "契約者");
				}else {
					Assertion.assertBytext(page, section+"//div[text()='お車の車検証上の所有者★']//following-sibling::div//div", "契約者");	
				}
			}

			if(utility.homePageMap.get(testCaseID).getInsurancePurchaseType().equals("現在他社で加入している") || utility.homePageMap.get(testCaseID).getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				Assertion.assertBytext(page, section+"//div[text()='現在のご契約の証券番号★' or text()='中断したご契約の証券番号★']//following-sibling::div//div",excelData.getCurrentOrSuspendedInsurancePolicyNumber());
				if(excelData.getBranchNumber().equals("")) {
					Assertion.assertIsNotAttached(page, section+"//div[text()='枝番★']//following-sibling::div//div");
					Report.logger.pass("'枝番★' section is not present inside Contract confirmation page when branch number is not inputted in 'Vehicle Information page'");
				}else {
					Assertion.assertBytext(page, section+"//div[text()='枝番★']//following-sibling::div//div",excelData.getBranchNumber());
				}

			}else {
				Assertion.assertIsNotAttached(page, section+"//div[text()='枝番★']//following-sibling::div//div");
				Report.logger.pass("'枝番★' section is not present inside Contract confirmation page for new contract'");
			}

			Assertion.assertBytext(page, section+"//div[text()='車台番号★']//following-sibling::div//div", excelData.getVehicleChassisNumber());
			if(utility.homePageMap.get(testCaseID).getInsurnaceProductType().equals("バイク保険（二輪）")) {
				Assertion.assertBytext(page, section+"//div[text()='登録番号（ナンバープレート）★']//following-sibling::div//div", excelData.getLicencePlateKanjiOrPrefecture()
						+" "+excelData.getLicencePlateHiragana()+" "+excelData.getLicencePlateSerialNumber()+" "+excelData.getLicencePlateNumberOrKatakana());
				VEHICLE_MODEL=excelData.getLicencePlateKanjiOrPrefecture()
						+" "+excelData.getLicencePlateHiragana()+" "+excelData.getLicencePlateSerialNumber()+" "+excelData.getLicencePlateNumberOrKatakana();
			}else {
				Assertion.assertBytext(page, section+"//div[text()='登録番号（ナンバープレート）★']//following-sibling::div//div", excelData.getLicencePlateKanjiOrPrefecture()
						+" "+excelData.getLicencePlateNumberOrKatakana()+" "+excelData.getLicencePlateHiragana()+" "+excelData.getLicencePlateSerialNumber());
				VEHICLE_MODEL=excelData.getLicencePlateKanjiOrPrefecture()+" "+excelData.getLicencePlateNumberOrKatakana()+" "+excelData.getLicencePlateHiragana()+" "+excelData.getLicencePlateSerialNumber();
			}
			(excelData.getVehicleMileage()+" km").equals(getText(page, section+"//div[text()='メーター値']//following-sibling::div//div").replaceAll(",", ""));
			Assertion.assertBytext(page, section+"//div[text()='メーター値確認日']//following-sibling::div//div", mileageCheckDate[2]+"年"+mileageCheckDate[1]+"月"+mileageCheckDate[0]+"日");

		}catch(AssertionError error) {
			FAIL_COUNT++;
			System.out.println(error);
			failStatusWithScreenshots(page, testCaseID, "One of the Data entered in Vehicle information page is not matching", "VehicleInfoPageDataValidation", false);
		}
	}

	public void dataValidationOfPolicyHolderPage(Page page,String testCaseID) {
		if(CORPORATE_URL) {
			dataValidationOfPolicyHolderPageOfCorporate(page, testCaseID);
		}else {
			dataValidationOfPolicyHolderPageOfIndividual(page, testCaseID);
		}
	}

	public void dataValidationOfPolicyHolderPageOfIndividual(Page page,String testCaseID) {
		ParameterOfPolicyHolderInformationPage excelData=utility.contractorInfoMap.get(testCaseID);

		String sectionNumber=getText(page, "//span[text()='ご契約者（ご本人）について']//preceding-sibling::div");
		String section="//section[@data-aem-id='ContractConfirm_ContractForm_PageSection_"+sectionNumber+"']";
		try {
			Assertion.assertBytext(page, section+"//div[text()='お名前★']//following-sibling::div//div",excelData.getLastNameKanji()+" "+excelData.getFirstNameKanji());
			POLICYHOLDER_NAME=getText(page, section+"//div[text()='お名前★']//following-sibling::div//div");
			Assertion.assertBytext(page, section+"//div[text()='お名前（カナ）']//following-sibling::div//div",excelData.getLastNameFurigana()+" "+excelData.getFirstNameFurigana());
			POLICYHOLDER_NAME_KANA=getText(page, section+"//div[text()='お名前（カナ）']//following-sibling::div//div");
			Assertion.assertBytext(page, section+"//div[text()='ご住所の郵便番号']//following-sibling::div//div",(excelData.getPinCode().substring(0, 3))+"-"+(excelData.getPinCode().substring(3)));
			POSTLE_CODE=getText(page, section+"//div[text()='ご住所の郵便番号']//following-sibling::div//div");

			if(!excelData.getBuildingName().isEmpty()) {
				Assertion.assertBytextContains(page, section+"//div[text()='ご住所★']//following-sibling::div//div[1]",(excelData.getAddressName()+" "+excelData.getDoorNumber()).trim());
				Assertion.assertBytextContains(page, section+"//div[text()='ご住所★']//following-sibling::div//div[2]",excelData.getBuildingName());
				ADDRESS=getText(page, section+"//div[text()='ご住所★']//following-sibling::div//div[1]")+" "+getText(page, section+"//div[text()='ご住所★']//following-sibling::div//div[2]");
			}else {
				Assertion.assertBytextContains(page, section+"//div[text()='ご住所★']//following-sibling::div//div",(excelData.getAddressName()+" "+excelData.getDoorNumber()).trim());
				ADDRESS=getText(page, section+"//div[text()='ご住所★']//following-sibling::div//div");
			}



			Assertion.assertBytext(page, section+"//div[text()='携帯電話番号']//following-sibling::div//div", excelData.getMobileNumber());
			PHONE_NUMBER=getText(page, section+"//div[text()='携帯電話番号']//following-sibling::div//div");
			Assertion.assertBytext(page, section+"//div[text()='メールアドレス']//following-sibling::div//div", excelData.getEmailAddress());
			EMAIL=getText(page, section+"//div[text()='メールアドレス']//following-sibling::div//div");

		}catch(AssertionError error) {
			FAIL_COUNT++;
			System.out.println(error);
			failStatusWithScreenshots(page, testCaseID, "One of the Data entered in Policy Holder information page is not matching", "Failed_PolicyHolderInfoPageDataValidation", false);
		}
	}

	public void dataValidationOfPolicyHolderPageOfCorporate(Page page,String testCaseID) {
		ParameterOfCorporate_PolicyHolderInformationPage excelData=utility.SME_ContractorInfoMap.get(testCaseID);

		String sectionNumber=getText(page, "//span[text()='ご契約者（ご本人）について']//preceding-sibling::div");
		String section="//section[@data-aem-id='ContractConfirm_ContractForm_PageSection_"+sectionNumber+"']";
		String CORPORATE_NAME;
		String CORPORATE_NAME_KANA;

		if(excelData.getCorporateStatusPosition().equals("法人名の前")) {
			CORPORATE_NAME=excelData.getLegalEntityType()+" "+excelData.getCorporateName();
			CORPORATE_NAME_KANA=excelData.getLegalEntityType()+" "+excelData.getCorporateNameKana();

		}else {
			CORPORATE_NAME=excelData.getCorporateName()+" "+excelData.getLegalEntityType();
			CORPORATE_NAME_KANA=excelData.getCorporateNameKana()+" "+excelData.getLegalEntityType();
		}

		try {
			CORPORATE_POSITION=excelData.getCorporateStatusPosition();
			Assertion.assertBytext(page, section+"//div[text()='ご契約者★']//following-sibling::div//div[1]",CORPORATE_NAME);
			CORPOARTE_NAME=getText(page, section+"//div[text()='ご契約者★']//following-sibling::div//div[1]");
			Assertion.assertBytext(page, section+"//div[text()='ご契約者★']//following-sibling::div//div[2]",excelData.getRepresentativeTitle());
			CORPORATE_TITLE=getText(page, section+"//div[text()='ご契約者★']//following-sibling::div//div[2]");
			Assertion.assertBytext(page, section+"//div[text()='ご契約者★']//following-sibling::div//div[3]",excelData.getCorporateLastNameKanji()+" "+excelData.getCorporateFirstNameKanji());
			POLICYHOLDER_NAME=getText(page, section+"//div[text()='ご契約者★']//following-sibling::div//div[3]");

			Assertion.assertBytext(page, section+"//div[text()='ご契約者（カナ）']//following-sibling::div//div[1]",CORPORATE_NAME_KANA);
			CORPOARTE_NAME_KANA=getText(page, section+"//div[text()='ご契約者（カナ）']//following-sibling::div//div[1]");
			Assertion.assertBytext(page, section+"//div[text()='ご契約者（カナ）']//following-sibling::div//div[2]",excelData.getRepresentativeTitleKana());
			CORPORATE_TITLE_KANA=getText(page, section+"//div[text()='ご契約者（カナ）']//following-sibling::div//div[2]");
			Assertion.assertBytext(page, section+"//div[text()='ご契約者（カナ）']//following-sibling::div//div[3]",excelData.getCorporateLastNameFurigana()+" "+excelData.getCorporateFirstNameFurigana());
			POLICYHOLDER_NAME_KANA=getText(page, section+"//div[text()='ご契約者（カナ）']//following-sibling::div//div[3]");

			Assertion.assertBytext(page, section+"//div[text()='契約手続きを行う方の氏名']//following-sibling::div//div",excelData.getCorporateLastNameKanji()+" "+excelData.getCorporateFirstNameKanji());
			Assertion.assertBytext(page, section+"//div[text()='契約手続きを行う方の氏名（カナ）']//following-sibling::div//div",excelData.getCorporateLastNameFurigana()+" "+excelData.getCorporateFirstNameFurigana());

			Assertion.assertBytext(page, section+"//div[text()='ご住所の郵便番号']//following-sibling::div//div",(excelData.getCorporatePinCode().substring(0, 3))+"-"+(excelData.getCorporatePinCode().substring(3)));
			POSTLE_CODE=getText(page, section+"//div[text()='ご住所の郵便番号']//following-sibling::div//div");

			if(!excelData.getBuildingName().isEmpty()) {
				Assertion.assertBytextContains(page, section+"//div[text()='ご住所★']//following-sibling::div//div[1]",(excelData.getCorporateAddress()+" "+excelData.getDoorNumber()).trim());

				Assertion.assertBytextContains(page, section+"//div[text()='ご住所★']//following-sibling::div//div[2]",excelData.getBuildingName());
				ADDRESS=getText(page, section+"//div[text()='ご住所★']//following-sibling::div//div[1]")+" "+getText(page, section+"//div[text()='ご住所★']//following-sibling::div//div[2]");
			}else {
				Assertion.assertBytextContains(page, section+"//div[text()='ご住所★']//following-sibling::div//div",(excelData.getCorporateAddress()+" "+excelData.getDoorNumber()).trim());
				ADDRESS=getText(page, section+"//div[text()='ご住所★']//following-sibling::div//div");
			}

			Assertion.assertBytext(page, section+"//div[text()='代表電話番号']//following-sibling::div//div", excelData.getRepresentativePhoneNumber());
			PHONE_NUMBER=getText(page, section+"//div[text()='代表電話番号']//following-sibling::div//div");
			Assertion.assertBytext(page, section+"//div[text()='連絡先電話番号']//following-sibling::div//div", excelData.getContactNumber());
			Assertion.assertBytext(page, section+"//div[text()='メールアドレス']//following-sibling::div//div", excelData.getEmailAddress());
			EMAIL=getText(page, section+"//div[text()='メールアドレス']//following-sibling::div//div");

		}catch(AssertionError error) {
			FAIL_COUNT++;
			System.out.println(error);
			failStatusWithScreenshots(page, testCaseID, "One of the Data entered in Policy Holder information page for SME is not matching", "Failed_SME_PolicyHolderInfoPageDataValidation", false);
		}
	}

	public void dataValidationOfPolicyPlanPage(Page page,String testCaseID) {
		ParameterOfAboutMainDriverPage excelData=utility.aboutdriverMap.get(testCaseID);

		try {

			String sectionNumber=getText(page, "//span[text()='ご契約内容について']//preceding-sibling::div");
			String section="//section[@data-aem-id='ContractConfirm_ContractForm_PageSection_"+sectionNumber+"']";
			Assertion.assertBytext(page, section+"//div[text()='一括払い（割引適用後）']//following-sibling::div//div",ADJ_portal_aboutMainDriverAndPolicyPlanScreen.POLICY_PLAN_AMOUNT+"円");
			Assertion.assertBytextContains(page, section+"//div[text()='運転者年齢条件特約']//following-sibling::div//div",excelData.getPolicyHolderAgeRange());
			PREMIUM_AMOUNT=getText(page, section+"//div[text()='一括払い（割引適用後）']//following-sibling::div//div");

			String[] maturityDateText=getText(page, section+"//div[text()='保険期間']//following-sibling::div//div[2]").split(" ");
			String[] inceptionDateText=getText(page, section+"//div[text()='保険期間']//following-sibling::div//div[1]").split(" ");
			String[] IDE=(inceptionDateText[0].replace("年", "/").replace("月", "/").replace("日", "")).split("/");
			String[] maturity=(maturityDateText[0].replace("年", "/").replace("月", "/").replace("日", "")).split("/");

			if(IDE[1].length()==1) {
				IDE[1]="0"+IDE[1];
			}
			if(IDE[2].length()==1) {
				IDE[2]="0"+IDE[2];
			}
			if(maturity[1].length()==1) {
				maturity[1]="0"+maturity[1];
			}
			if(maturity[2].length()==1) {
				maturity[2]="0"+maturity[2];
			}
			INCEPTION_DATE=IDE[0]+"/"+IDE[1]+"/"+IDE[2]+" "+inceptionDateText[1]+":00";
			MATURITY_DATE=maturity[0]+"/"+maturity[1]+"/"+maturity[2]+" "+maturityDateText[1]+":00";

			if((CORPORATE_URL==false && utility.homePageMap.get(testCaseID).getInsurnaceProductType().equals("自動車保険（四輪）") && !utility.currentInsuranceMap.get(testCaseID).getCarInsurnaceQuestionaryOfCI().equals("はい"))) {
				//COVERAGE_TYPE=(getText(page, section+"//div[text()='車両保険の種類']//following-sibling::div//div")).replace("保険", "");
				//COVERAGE_AMOUNT=getText(page, section+"//div[text()='車両保険金額']//following-sibling::div//div").replace("万", "0000").replace("円", " JPY");
				COVERAGE_AMOUNT=getText(page, section+"//div[text()='車両保険金額']//following-sibling::div//div");
			}else {
				COVERAGE_TYPE="";
				COVERAGE_AMOUNT="";
			}

		}catch(AssertionError error) {
			FAIL_COUNT++;
			System.out.println(error);
			failStatusWithScreenshots(page, testCaseID,"Selected Policy plan amount is not matching", "PolicyAmountDataValidation", false);
		}
	}

	public void dataValidationOfPreviousPolicyPage(Page page,String testCaseID) {
		ParameterOfCurrentInsurancePage excelData=utility.currentInsuranceMap.get(testCaseID);
		String previousPolicyStartYear=null;
		String contractPeriod=null;

		String[] expiaryDate=excelData.getExpiaryDateOfCI().split("-");

		if(expiaryDate[1].length()==2 && expiaryDate[1].substring(0,1).equals("0") ) {
			expiaryDate[1]=expiaryDate[1].replaceFirst("0", "");
		}
		if(expiaryDate[0].length()==2 && expiaryDate[0].substring(0,1).equals("0") ) {
			expiaryDate[0]=expiaryDate[0].replaceFirst("0", "");
		}
		if(utility.currentInsuranceMap.get(testCaseID).getContractualConditionOfCI().equals("はい")) {
			previousPolicyStartYear=""+(Integer.parseInt(expiaryDate[2])-1);
			contractPeriod="1年";
		}else {
			previousPolicyStartYear=""+(Integer.parseInt(expiaryDate[2])-(Integer.parseInt(excelData.getContractPeriodOfCI().replace("年", ""))));
			contractPeriod=excelData.getContractPeriodOfCI();
		}
		String sectionNumber=getText(page, "//span[text()='現在ご契約中の保険について']//preceding-sibling::div");
		String section="//section[@data-aem-id='ContractConfirm_ContractForm_PageSection_"+sectionNumber+"']";

		try {
			Assertion.assertBytext(page, section+"//div[text()='保険会社名★']//following-sibling::div//div",excelData.getCurrentInsurnaceNameOfCI());
			Assertion.assertBytext(page, section+"//div[text()='保険始期日★']//following-sibling::div//div",previousPolicyStartYear+"年"+expiaryDate[1]+"月"+expiaryDate[0]+"日");
			Assertion.assertBytext(page, section+"//div[text()='保険満期日★']//following-sibling::div//div",expiaryDate[2]+"年"+expiaryDate[1]+"月"+expiaryDate[0]+"日");
			Assertion.assertBytext(page, section+"//div[text()='保険期間★']//following-sibling::div//div",contractPeriod);
			Assertion.assertBytext(page, section+"//div[text()='ノンフリート等級★']//following-sibling::div//div",excelData.getGradeTypeOfCI());
			Assertion.assertBytext(page, section+"//div[text()='事故有係数適用期間(年)★']//following-sibling::div//div",excelData.getAccidentCofficientOfCI().replace("年", ""));

			switch(excelData.getAccidentCaseNumberOfCI()) {
			case "０件":
				Assertion.assertBytext(page, section+"//div[text()='事故の件数(件)★']//following-sibling::div//div","0");
				break;
			case "１件":
				Assertion.assertBytext(page, section+"//div[text()='事故の件数(件)★']//following-sibling::div//div","1");
				if(excelData.getAccidentTypeOfCI().equals("１等級ダウン事故")){
					Assertion.assertBytext(page, section+"//div[text()='事故の種類★']//following-sibling::div//div",excelData.getAccidentTypeOfCI().replace("１", "1"));
				}else if(excelData.getAccidentTypeOfCI().equals("３等級ダウン事故")){
					Assertion.assertBytext(page, section+"//div[text()='事故の種類★']//following-sibling::div//div",excelData.getAccidentTypeOfCI().replace("３", "3"));
				}
				break;
			case "２件以上":
				Assertion.assertBytext(page, section+"//div[text()='事故の件数(件)★']//following-sibling::div//div","2");
				break;
			}

		}catch(AssertionError error) {
			FAIL_COUNT++;
			System.out.println(error);
			failStatusWithScreenshots(page, testCaseID, "One of the Data entered in Previous Policy page is not matching", "Failed_PreviousPolicyPageDataValidation", false);
		}
	}

	public void dataValidationOfSuspendedPolicyPage(Page page,String testCaseID) {
		ParameterOfSuspensionCertificatePage excelData=utility.suspensionCertificateMap.get(testCaseID);

		String[] SCIstartDate=excelData.getStartdateOfSIC().split("-");
		String[] SCIendDate=excelData.getEndDateOfSIC().split("-");
		String[] SCIregistrationDate=excelData.getRegistrationDateOfSIC().split("-");

		if(SCIstartDate[1].length()==2 && SCIstartDate[1].substring(0,1).equals("0") ) {
			SCIstartDate[1]=SCIstartDate[1].replaceFirst("0", "");
		}
		if(SCIstartDate[0].length()==2 && SCIstartDate[0].substring(0,1).equals("0") ) {
			SCIstartDate[0]=SCIstartDate[0].replaceFirst("0", "");
		}
		if(SCIendDate[1].length()==2 && SCIendDate[1].substring(0,1).equals("0") ) {
			SCIendDate[1]=SCIendDate[1].replaceFirst("0", "");
		}
		if(SCIendDate[0].length()==2 && SCIendDate[0].substring(0,1).equals("0") ) {
			SCIendDate[0]=SCIendDate[0].replaceFirst("0", "");
		}
		if(SCIregistrationDate[1].length()==2 && SCIregistrationDate[1].substring(0,1).equals("0") ) {
			SCIregistrationDate[1]=SCIregistrationDate[1].replaceFirst("0", "");
		}
		if(SCIregistrationDate[0].length()==2 && SCIregistrationDate[0].substring(0,1).equals("0") ) {
			SCIregistrationDate[0]=SCIregistrationDate[0].replaceFirst("0", "");
		}

		String sectionNumber=getText(page, "//span[text()='中断証明書の内容について​']//preceding-sibling::div");
		String section="//section[@data-aem-id='ContractConfirm_ContractForm_PageSection_"+sectionNumber+"']";

		try {
			/*
			 * *@INS33 * is present for 中断証明書の発行事由
			 */
			Assertion.assertBytext(page, section+"//div[text()='中断証明書の発行事由']//following-sibling::div//div",excelData.getSuspensionReason());
			Assertion.assertBytext(page, section+"//div[text()='保険会社名★']//following-sibling::div//div",excelData.getCompanyNameOfSIC());
			Assertion.assertBytext(page, section+"//div[text()='保険始期日★']//following-sibling::div//div",SCIstartDate[2]+"年"+SCIstartDate[1]+"月"+SCIstartDate[0]+"日");
			Assertion.assertBytext(page, section+"//div[text()='中断日★']//following-sibling::div//div",SCIendDate[2]+"年"+SCIendDate[1]+"月"+SCIendDate[0]+"日");
			Assertion.assertBytext(page, section+"//div[text()='ノンフリート等級★']//following-sibling::div//div",excelData.getGradeTypeOfSIC());
			Assertion.assertBytext(page, section+"//div[text()='事故有係数適用期間(年)★']//following-sibling::div//div",excelData.getAccidentCofficientOfSIC().replace("年", ""));
			switch(excelData.getAccidentCaseNumberOfSIC()) {
			case "０件":
				Assertion.assertBytext(page, section+"//div[text()='事故の件数(件)★']//following-sibling::div//div","0");
				break;
			case "１件":
				Assertion.assertBytext(page, section+"//div[text()='事故の件数(件)★']//following-sibling::div//div","1");
				if(excelData.getAccidentTypeOfSIC().equals("１等級ダウン事故")){
					Assertion.assertBytext(page, section+"//div[text()='事故の種類★']//following-sibling::div//div",excelData.getAccidentTypeOfSIC().replace("１", "1"));
				}else if(excelData.getAccidentTypeOfSIC().equals("３等級ダウン事故")){
					Assertion.assertBytext(page, section+"//div[text()='事故の種類★']//following-sibling::div//div",excelData.getAccidentTypeOfSIC().replace("３", "3"));
				}
				break;
			case "２件以上":
				Assertion.assertBytext(page, section+"//div[text()='事故の件数(件)★']//following-sibling::div//div","2");
				break;
			}
			/*
			 * *@INS33 * is present for 新しいお車の取得日
			 */
			Assertion.assertBytext(page, section+"//div[text()='新しいお車の取得日']//following-sibling::div//div",SCIregistrationDate[2]+"年"+SCIregistrationDate[1]+"月"+SCIregistrationDate[0]+"日");

		}catch(AssertionError error) {
			FAIL_COUNT++;
			System.out.println(error);
			failStatusWithScreenshots(page, testCaseID, "One of the Data entered in Suspended Policy page is not matching", "Failed_SuspendedPolicyPageDataValidation", false);
		}
	}

	public void contractConfirmationPageDataValidation(Page page,String testCaseId) {
		try {
			dataValidationOfContractTypePage(page, testCaseId);
			if(utility.homePageMap.get(testCaseId).getInsurancePurchaseType().equals("現在他社で加入している")) {
				dataValidationOfPreviousPolicyPage(page, testCaseId);
			}else if(utility.homePageMap.get(testCaseId).getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				dataValidationOfSuspendedPolicyPage(page, testCaseId);
			}
			dataValidationOfVehiclePage(page, testCaseId);
			dataValidationOfDriverPage(page, testCaseId);
			dataValidationOfVehicleInfoPage(page, testCaseId);
			dataValidationOfPolicyHolderPage(page, testCaseId);
			dataValidationOfPolicyPlanPage(page, testCaseId);
			if(FAIL_COUNT==0) {
				passStatusWithScreenshots(page, testCaseId, "Data entered in all previous page is matching with our data sheet", "dataValidationCompleted", true);
			}else {
				failStatusWithScreenshots(page, testCaseId, "Data mismatch is there in one of the sections", "Failed_dataValidationCompleted", true);
			}
		}catch(Exception e) {
			System.out.println(e);

		}

	}

	public void contractDetailsConfirmationPage(Page page,String testCaseId) throws IOException {
		clickAction(page, Portal_ObjectRepository.infoConfirmationCheckbox);
		//CORPORATE_URL=Assertion.assertURLContains(page, "sme");
		if(utility.homePageMap.get(testCaseId).getBrowserView().equals("Mobile")) {
			List<Locator> list=page.locator("//button[@data-testid='svgIconButton']").all();
			for(int i=0;i<list.size()-1;i++) {
				clickAction(list.get(i));

			}
		}
		contractConfirmationPageDataValidation(page, testCaseId);
		consolidateSummaryData();
		//passStatusWithScreenshots(page,testCaseId, "Information confirmation page displayed successfully","infoConfirmationPage",true);
		clickAction(page, Portal_ObjectRepository.proceedNext);
		Assertion.assertIsHidden(page, Portal_ObjectRepository.loadingPageDialog);
		ADJ_portal_paymentInformationScreen.CORPORATE_URL=Assertion.assertURLContains(page, "sme");
	}

	public void errorMessageAndDefaultCheckboxSelectionValidationOfContractConfirmationPage(Page page,String testCaseId,String errorMessage) throws IOException {
		waitForSelector(page, Portal_ObjectRepository.infoConfirmationCheckbox);
		clickAction(page, Portal_ObjectRepository.proceedNext);

		try {
			Assertion.assertIsNotChecked(page, Portal_ObjectRepository.infoConfirmationCheckbox);
			Assertion.assertBytext(page, Portal_ObjectRepository.infoConfirmationCheckboxError,errorMessage);

			passStatusWithScreenshots(page, testCaseId, "By default check box is not selected and Error for all Mandetory field as: "+errorMessage+" of contract confirmation Page is displaying", "contractConfirmCheckboxErrorValidation", true);

		}catch(AssertionError error) {
			failStatusWithScreenshots(page, testCaseId, "By default check box is not selected and Error for all Mandetory field as: "+errorMessage+"of contract confirmation Page is not displaying", "Failed_contractConfirmCheckboxErrorValidation", true);
			System.out.println(error);
		}
	}

	public void modifyDataLinkValidation(Page page,String testCaseId) throws InterruptedException {
		clickAction(page, "(//a[text()='修正する​'])[3]");
		pageURLValidation(page, testCaseId);
	}

	public void functionalValidationOfContractConfirmationPage(Page page,String testCaseId) throws IOException, InterruptedException {
		try {
			PageLogoValidation(page, testCaseId);
			quotationValidation(page, testCaseId);
			chatBotValidation(page, testCaseId);
			headerImageIconValidationAndNavigation(page, testCaseId);
			menuOptionValidationOfContractConfirmationPage(page, testCaseId);
		}catch(AssertionError error) {
			System.out.println(error);
			failStatusWithScreenshots(page, testCaseId, "Functional Validation of Contract Confirmation page is not completed", "Failed_contractConfirmationPageFunctionalValidation", true);
		}
	}

	public List<String> consolidateSummaryData() {
		dataList.add(EXECUTION_FLOW);
		dataList.add(getCurrentDate("yyyy/MM/dd"));
		dataList.add(INCEPTION_DATE);
		dataList.add(MATURITY_DATE);
		dataList.add(VEHICLE_TYPE);
		dataList.add(VEHICLE_NAME);
		dataList.add(VEHICLE_MODEL);
		dataList.add(POLICYHOLDER_NAME);
		dataList.add(POLICYHOLDER_NAME_KANA);
		if(CORPORATE_URL) {
			dataList.add(CORPOARTE_NAME);
			dataList.add(CORPOARTE_NAME_KANA);
		}else {
			dataList.add("");
			dataList.add("");
		}

		dataList.add(POSTLE_CODE);
		dataList.add(ADDRESS);
		dataList.add(PREMIUM_AMOUNT);
		dataList.add(COVERAGE_TYPE);
		dataList.add(COVERAGE_AMOUNT);
		dataList.add(PAYMENT_MODE);
		dataList.add(PHONE_NUMBER);
		dataList.add(EMAIL);
		dataList.add(DOB);
		if(CORPORATE_URL) {
			dataList.add(CORPORATE_POSITION);
			dataList.add(CORPORATE_TITLE);
			dataList.add(CORPORATE_TITLE_KANA);
		}else {
			dataList.add("");
			dataList.add("");
			dataList.add("");
		}
		return dataList;

	}

	public void menuOptionValidationOfContractConfirmationPage(Page page,String testCaseID) throws IOException, InterruptedException {
		try {
			threeLineMenuOptionNavigation(page, testCaseID);
			Assertion.assertIsHidden(page, Portal_ObjectRepository.menuBeforeQuote);

			if(utility.homePageMap.get(testCaseID).getInsurancePurchaseType().equals("現在他社で加入している")) {
				Assertion.assertIsHidden(page, Portal_ObjectRepository.menuCurrentInsurnace);
			}else if (utility.homePageMap.get(testCaseID).getInsurancePurchaseType().equals("中断証明書を使用して加入する")) {
				Assertion.assertIsHidden(page, Portal_ObjectRepository.menuSuspendedPolicy);
			}else {
				Assertion.assertIsHidden(page, Portal_ObjectRepository.menuCurrentInsurnace);
				Assertion.assertIsHidden(page, Portal_ObjectRepository.menuSuspendedPolicy);
			}
			System.out.println(getAttribute(page, Portal_ObjectRepository.menuPayment, "color"));
			Assertion.assertIsHidden(page, Portal_ObjectRepository.menuAboutCarAndBike);
			Assertion.assertIsHidden(page, Portal_ObjectRepository.menuMainDriver);
			Assertion.assertIsHidden(page, Portal_ObjectRepository.menuSaveContent);
			Assertion.assertIsHidden(page, Portal_ObjectRepository.menuContactCenter);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuQuotation);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuBeforeApplication);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuContractor);
			Assertion.assertIsEnabled(page, Portal_ObjectRepository.menuMatterConfirmation);
			passStatusWithScreenshots(page, testCaseID, "Menu options of contract Conformation page is validated successfully" , "MenuOptionValidationOfcontractConformationPage", true);
			clickAction(page, Portal_ObjectRepository.closeMenuButton);
		}catch(AssertionError error) {
			System.out.println(error);
			failStatusWithScreenshots(page, testCaseID, "Menu options of contract Conformation page is not validated completely", "Failed_MenuOptionValidationOfcontractConformationPage", true);
		}
	}

}
