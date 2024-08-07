package org.axa.framework;

//import com.microsoft.playwright.Locator;

public class Portal_ObjectRepository extends InitializeBrowser {

	public static class validationElements {
		public static final String logoImage = "//a[@data-testid='logoImg']";
		public static final String chatBotIcon = "//a[@class='exChatEnt-button-link']";
		public static final String chatBotenterText = "//textarea[@data-testid='gc-compose-message-input']";
		public static final String chatBotCloseButton = "//div[@data-testid='gc-toolbar-right-items']//button[1]";
		public static final String threeLineMenuButton = "//div[@data-aem-id='CommonHeader_ImgContainer']";
		public static final String menuInputContentConfirmation = "//p[text()='入力内容を確認・修正する']";
		public static final String menuBeforeQuote = "//li[text()='お見積りの前に']";
		public static final String menuCurrentInsurnace = "//li[text()='ご加入中の保険']";
		public static final String menuAboutCarAndBike = "//li[text()='お車/バイクについて']";
		public static final String menuMainDriver = "//li[text()='主な運転者']";
		public static final String menuQuotation = "//li[text()='お見積り']";
		public static final String menuContactCenter = "//p[text()='お問い合わせ']";
		public static final String menuSaveContent = "//p[text()='これまでの内容を保存する']";
		public static final String closeMenuButton = "//*[@data-aem='ImgClose']";
		public static final String saveDataDialogEmailInput = "//input[@name='save-email']";
		public static final String saveDataDialogEmailConfirmationButton = "//button[text()='保存する']";
		public static final String roughtEstimationButton = "//button[text()='試してみる']";
		public static final String saveDataDialogCheckbox="//input[@name='save-check']";
		public static final String saveDataDialogErrorMessage="//input[@name='save-email']//following::div[@class='invalid-feedback']";
		public static final String saveDataDialogboxCloseButton="//div[contains(text(),'このページから離れますか?')]//following::button[1]";
		public static final String loadingPageDialog="//div[@data-aem-id='Loading_Content']";
		public static final String logoModelBodyAfterAccountRegister="//div[@data-aem-id='afterAccountRegisterQuotationModal_ModalBody']";



	}

	/**
	 *
	@Common Validation elements:
	 *
	 **/
	public static final String logoImage = "//a[@data-testid='logoImg']";
	public static final String chatBotIcon = "//button[@data-testid='gc-floating-button']";
	public static final String chatBotenterText = "//textarea[@data-testid='gc-compose-message-input']";
	public static final String chatBotCloseButton = "//div[@data-testid='gc-toolbar-right-items']//button[1]";
	public static final String threeLineMenuButton = "//div[@data-aem-id='CommonHeader_ImgContainer']";
	public static final String menuInputContentConfirmation = "//p[text()='入力内容を確認・修正する']";
	public static final String menuBeforeQuote = "//li[text()='お見積りの前に']";
	public static final String menuCurrentInsurnace = "//li[text()='ご加入中の保険']";
	public static final String menuSuspendedPolicy = "//li[text()='中断した保険']";
	public static final String menuAboutCarAndBike = "//li[text()='お車/バイクについて']";
	public static final String menuMainDriver = "//li[text()='主な運転者']";
	public static final String menuQuotation = "//li[text()='お見積り']";
	public static final String menuContactCenter = "//p[text()='お問い合わせ']";
	public static final String menuBeforeApplication = "//li[text()='お申込みの前に']";
	public static final String menuContractor = "//li[text()='ご契約者']";
	public static final String menuMatterConfirmation = "//li[text()='重要事項説明書/約款の確認']";
	public static final String menuContractDetailsConfirmation = "//li[text()='契約内容の確認']";
	public static final String menuPayment = "//li[text()='お支払い']";
	public static final String menuSaveContent = "//div[@data-aem-id='CommonHeader_HeaderMenuDefault01']//p[text()='これまでの内容を保存する']";
	public static final String closeMenuButton = "//*[@data-aem='ImgClose']";
	public static final String imageOfDataSaveIcon = "//div[contains(@data-aem-id,'IconImgSave')]";
	public static final String imageOfContactIcon = "//div[contains(@data-aem-id,'IconImgCustom')]";
	public static final String contactPageInsuranceGlossaryButton = "//span[text()='保険用語集']";
	public static final String saveDataDialogEmailInput = "//input[@name='save-email']";
	public static final String saveDataDialogEmailConfirmationButton = "//button[text()='保存する']";
	public static final String roughtEstimationDialogButton = "//button[text()='試してみる']";
	public static final String roughtEstimationDialogTitle = "(//div[@data-aem-id='PreviousModalBoxBox']//p)[1]";
	public static final String roughEstimationDialogCloseButton="//div[@data-aem-id='PreviousModalBoxBox']//button[1]";
	public static final String saveDataDialogCheckbox="//input[@name='save-check']";
	public static final String saveDataDialogErrorMessage="//input[@name='save-email']//following::div[@class='invalid-feedback']";
	public static final String saveDataDialogboxCloseButton="//div[@data-aem='undefined_ConfirmModalBoxTitle']//following::button[1]";
	public static final String closeBanner = "//button[text()='バナーを閉じる']";
	public static final String backToPreviousSreen = "//span[text()='前へ']";
	public static final String backToPreviousSreenDialogTitle = "//div[contains(@data-aem,'_PreviousModalBoxBox')]/p";
	public static final String backToPreviousSreenDialogNextButton = "//button[@data-testid='next_btn']";
	public static final String loadingPageDialog="(//div[@data-aem-id='Loading_Content'])[1]";
	public static final String internetEstimationSection = "//div[contains(@data-aem,'QB_ContractType_EstimateAndInternetDiscountInfo_EstimateConditions')]";
	public static final String logoModelBodyAfterAccountRegister="//div[@data-aem-id='afterAccountRegisterQuotationModal_ModalBody']";


	/**
	 * *
	 * 
	 * @Home page
	 **
	 **/

	public static final String purchaseFlowText = "//span[@data-aem='QB_ContractType_InsuranceType_Text']";
	public static final String pageLoadingText="//div[@data-aem-id='Loading_LoadingText']";
	public static final String emmaLoginBox = "//div[contains(@data-aem,'QB_ContractType_InsuranceType_LoginBox')]";
	public static final String emmaLoginLink = "//a[@data-aem-id='QB_ContractType_InsuranceType_LoginBox_Link_1']";
	public static final String emmaLoginID = "//div[@class='p-content-login']//input[@name='email']";
	public static final String emmaLoginPassword = "//div[@class='p-content-login']//input[@name='password']";
	public static final String emmaLoginButton = "//button[@id='btn-login']";
	public static final String emmaUserIdRecoveryLink = "//a[text()='ユーザーIDがわからない方はこちら']";
	public static final String emmaUserIdRecovery_PolicyNumber = "//input[@name='policyNumber']";
	public static final String emmaUserIdRecovery_lastName = "//input[@name='lastName']";
	public static final String emmaUserIdRecovery_firstName = "//input[@name='firstName']";
	public static final String emmaUserIdRecovery_PhoneNumber = "//input[@name='phoneNumber']";
	public static final String emmaUserIdRecovery_Personal = "//label[@for='hasPersonalOrSMERetrieve01']";
	public static final String emmaUserIdRecovery_Corporate = "//label[@for='hasPersonalOrSMERetrieve02']";
	public static final String emmaUserIdRecovery_ProceedButton = "//label[contains(@data-aem,'ForgotId_SendButton')]";
	public static final String emmaPasswordRecovery_UserID = "//input[@name='userId']";
	public static final String emmaPasswordRecovery_ProceedButton = "//button[text()='送信する']";
	public static final String emmaPasswordRecoveryLink = "//a[text()='パスワードがわからない方はこちら']";
	public static final String emmaNewProcedureTab = "//div[contains(@data-aem,'TabBarItem')]//span[text()='新規のお手続き']";
	public static final String emmaContractDetailsTab = "//div[contains(@data-aem,'TabBarItem')]//span[text()='ご契約内容の']";
	public static final String emmaContinueProcedureTab = "//div[contains(@data-aem,'TabBarItem')]//span[text()='ご継続のお手続き']";
	public static final String emmaNewProcedureDOBNote = "//p[@data-testid='policyListBodyTipsWarnText6']";
	public static final String emmaBottomPolicyList = "//div[@data-aem='PolicyListBottom']";
	
	public static final String ampPolicyListBox = "(//div[@data-aem='Amp_Main_ListBox'])";
	public static final String ampStartPolicyButtonLabel = "//div[@data-aem='AmpButtonLabel']";
	public static final String ampOETab_PolicyListBodyTips = "//div[@data-aem='PolicyListBodyTips']";
	public static final String ampOETab_PolicyListBodyTipsWarning = "//p[contains(@data-testid,'policyListBodyTipsWarn')]";
	public static final String ampOETab_PolicyListBoxLastItem = "(//div[@data-aem='PolicyListTableItemMainLastItem'])";
	public static final String ampOETab_ContinueProcedureLink = "(//button[text()='お手続きはこちらから'])";
	public static final String ampPolicyDetailTimeText = "//span[@data-aem='AMP_AccountPolicyDetails_PolicyDetailsTimeText']";
	public static final String amp_PolicyDetails_Attention = "//span[@type='attention']";
	public static final String amp_PolicyDetailsButton = "//div[@data-aem='AMP_AccountPolicyDetails_PolicyDetailsButtonDiv']";
	public static final String amp_PolicyDetail_ChooseMenuText = "//p[text()='ご希望のメニューをお選びください']";
	public static final String amp_PolicyDetail_TopSectionDescription = "(//li[contains(@data-aem,'AMP_AccountPolicyDetails_TopSection_Description')])";
	//public static final String amp_PolicyHolderName = "(//div[@data-aem='Amp_Main']/div[1]/span)[1]";
	public static final String amp_PolicyHolderName = "//div[@data-aem='HeaderMenuActive']//p";
	public static final String amp_lastLoginDateAndTime = "(//div[@data-aem='Amp_Main']/div[1]/span)[2]";
	public static final String amp_threeLineMenuButton = "//div[@data-aem-id='Amp_Header_HeaderOpenButton']";
	public static final String amp_threeLineMenuHeaderName = "//div[@data-testid='openButton']/p";
	public static final String amp_CloseThreeLineMenuButton = "//img[@data-testid='close']";
	public static final String amp_Hamburger_petInsuranceText = "//a[@data-testid='jumpToPet']";
	public static final String amp_Hamburger_HotelSurgeryText = "//a[@data-testid='jumpToPet']//following-sibling::a";
	public static final String amp_Hamburger_newEstimationButton = "//div[@data-testid='jumpToQB']";
	public static final String amp_Hamburger_accountLoginDetailsButton = "//li[text()='アカウント・ログイン情報について']";
	public static final String amp_BadCustomer_ModelPopup = "//div[@data-aem-id='qb_OutOfRangeModal_ModalDialog']";
	
	
	public static final String startNewQuote = "//button[contains(text(),'新規のお見積りを始める')]";
	public static final String switchFlowLink = "//button[contains(text(),'こちら。')]";
	public static final String switchFlowPopupContent = "//div[@data-aem-id='entrance_switchPage_modal_ModalBody']";
	public static final String corporateFlowPopupAccept = "//label[@for='callToActionButton']";
	public static final String corporateFlowPopupDeny = "//label[@for='closeButton']";
	public static final String tentativErrorBox = "//div[contains(@data-aem-id,'InsuranceType_TentativeItems')]";
	public static final String tentativErrorContent = tentativErrorBox+"//span";

	public static final String menuButtonOfMobileView = "//div[@data-aem-id='CommonHeader_HeaderOpenButton']";
	public static final String getQuoteValueOfMobileView = "//p[@data-aem-id='CommonHeader_QuoteIDValue']";
	public static final String closeMenuButtonOfMobileView = "//*[@data-aem='ImgClose']";

	public static final String internetOptionImage = "//div[contains(@data-aem-id,'InternetDiscountInfo_BoxContainer_Id')]//img";
	public static final String CarInsuranceOption = "//label[@for='vehicleTypeRadioButtons01']//span";
	public static final String bikeInsuranceOption = "//label[@for='vehicleTypeRadioButtons02']//span";
	public static final String firstTimePurchase = "//label[@for='submissionPattern01']";
	public static final String otherCompanayEnrollment = "//label[@for='submissionPattern02']";
	public static final String suspendedCertificate = "//label[@for='submissionPattern03']";
	public static final String inceptionDateYear = "//select[@name='periodStartDateYear']";
	public static final String inceptionDateMonth = "//select[@name='periodStartDateMonth']";
	public static final String inceptionDateDay = "//select[@name='periodStartDateDay']";
	public static final String uncertainPeriodCheckbox = "//label[@for='uncertainPeriodStartDateCheckBox01']";
	public static final String wrongIdError = "//div[contains(text(),'明日以降の日付をお選びください')]";
	public static final String suspensionCertificateDialogAccept = "//button[text()='すべてに該当します']";
	public static final String proceedNext = "(//*[text()='次へ進む'])[1]";

	/**
	 * @ Home page Error Message Validation
	 */
	public static final String productTypeSelectionError = "//div[contains(@data-aem-id,'RadioButtons_Error_Id')]";
	public static final String InceptionDateSelectionError = "//div[contains(@data-aem-id,'DateDropdown_Error_Id')]";

	
	/**
	 * * @ AMP Claim portal
	 **/
	
	public static final String claim_ReportAccidentButton = "//li[text()='事故のご報告は']/span";
	public static final String claim_InprogressButton = "//span[text()='事故経過WEB照会']";
	public static final String claim_Inprogress_PolicyBox = "//div[@data-testid='CardItemBox']";
	public static final String claim_Inprogress_CheckInsuranceCoverageButton = "//button[@data-testid='contentInnerButton']";
	public static final String claim_Inprogress_OtherCompensationTable = "//h4[text()='その他の補償']/..";
	public static final String claim_AccidentDate = "//input[@name='accidentDateAndTime.calendarOnPage']";
	public static final String claim_AccidentTime = "//input[@name='accidentDateAndTime.timeOnPage']";
	public static final String claim_AccidentLocation = "//select[@name='locationOnPage']";
	public static final String claim_AccidentAddress = "//textarea[@name='addressOnPage']";
	public static final String claim_CarHitByOther = "//input[@name='crashedOnPage']";
	public static final String claim_accidentDetailsTextArea = "//textarea[@name='situationDetailOnPage']";
	public static final String claim_accidentSituationAdditionalInfo = "//textarea[@name='textContent']";
	public static final String claim_VehicleRepairShopName = "//input[@name='qVendorName']";
	public static final String claim_VehicleRepairShopPhoneNumber = "//input[@name='qVendorPhone']";
	public static final String claim_uploadFile = "//input[@data-testid='uploadInput']";
	public static final String claim_FileUploadConfirmation = "//div[@data-testid='iconTrash']/../..";
	public static final String claim_licenceDate = "//input[@name='licenseDate']";
	public static final String claim_licenceNumber = "//input[@name='licenseNumber']";
	public static final String claim_driverName_lastName = "//input[@name='surname01']";
	public static final String claim_driverName_firstName = "//input[@name='name01']";
	public static final String claim_driverName_lastNameKana = "//input[@name='surname02']";
	public static final String claim_driverName_firstNameKana = "//input[@name='name02']";
	public static final String claim_contactAddressie = "//select[@data-testid='contactSelect']";
	public static final String claim_phoneNumberType = "//select[@data-testid='telTypeSelect']";
	public static final String claim_contactor_lastName = "//input[@data-testid='lastName']";
	public static final String claim_contactor_firstName = "//input[@data-testid='firstName']";
	public static final String claim_contactor_lastNameKana = "//input[@data-testid='lastNm']";
	public static final String claim_contactor_firstNameKana = "//input[@data-testid='firstNm']";
	public static final String claim_contactor_MobileNumber = "//input[@name='tel1Value']";
	public static final String claim_contactor_NotificationMobileNumber = "//input[@name='tel2Value']";
	public static final String claim_contactor_contactTime = "//select[@data-testid='contactTimeSelect']";
	public static final String claim_contactor_queriesTextBox = "//textarea[@name='textValue']";
	public static final String claim_contactor_confirmationButton = "//div[@data-testid='nextButton']";
	public static final String claim_checkInput_termAndConditionCheckBox = "//span[text()='プライバシーポリシーに同意する']/..";
	public static final String claim_checkInput_registerButton = "//button[text()='登録する']";
	public static final String claim_VehiclePage_HeaderText = "//input[@id='policyCarinsured']/../../div[1]/h2";

	/**
	 * * @ Current Insurance screen for CAR
	 **/

	public static final String currentInsuranceSelectOtherCar = "//select[@name='carrIdAll']";
	public static final String currentInsurnaceExpiaryYear = "//div[contains(@data-aem-id,'DatePicker_SelectContent')]//select";
	public static final String currentInsurnaceExpiaryMonth = "//div[contains(@data-aem-id,'DatePicker_DateSelect01')]//select";
	public static final String currentInsurnaceExpiaryDay = "//div[contains(@data-aem-id,'DatePicker_DateSelect02')]//select";
	public static final String unknownCurrentInsuranceCheckbox = "//input[@name='noCarrId']";
	public static final String otherInsurnaceNameOfCI = "//select[@name='carrIdAll']";
	public static final String currentInsuranceContactPeriod = "//select[@name='policyTime']";
	public static final String currentInsurnaceGradeSelection = "//select[@name='grade']";
	public static final String currentInsurnaceGradeYear = "//select[@name='gradeYear']";
	public static final String unknownPolicyExpiaryCheckbox = "//input[@name='policyTimeCheck']";
	public static final String unknownGradeCheckbox = "//input[@name='gradeCheck']";
	public static final String currentInsuranceAccidentCoficient = "//select[@name='periodTime']";
	public static final String unknownAccidentCheckbox = "//input[@name='accidentCheck']";
	public static final String UndefinedConfirmBoxCloseButton = "//*[text()='閉じる']";
	//public static final String confrimationOfdialogCI = "//div[@data-testid='grade_next']";
	public static final String confrimationOfdialogCI = "//*[text()='次へ']";
	public static final String contactCenterDialogBox = "(//div[@data-aem-id='qb_Previous_Policy_Confirm_Modal_ModalBody']//span)[1]";
	public static final String oldContactCenterDialogBox = "//div[@data-aem='undefined_ConfirmModalBoxTitle']";

	/**
	 * @ Current Insurance field Error message validation
	 **/

	public static final String previousPolicyselectionError = "//div[contains(@data-aem-id,'qb_PreviousPolicy_Main_BoxDiv')]//p[@data-testid='error_text']";
	public static final String previousPolicyExpiaryDateError = "//div[contains(@data-aem-id,'Main_PolicyTime_DatePicker_DatePickerBox')]//..//p";
	public static final String previousPolicyGradeSelectionError = "//select[@data-testid='select_grade']//..//..//p";
	public static final String previousPolicyAccidentCases = "//div[contains(@data-aem-id,'qb_PreviousPolicy_Main_DivButtonBox_01')]//..//div[@data-aem-id='ValidationWarningText']";
	public static final String priviousPolicyVehicleInsuranceError = "(//div[@data-aem-id='ValidationWarningText'])[2]";
	public static final String newInsurnaceStartDate = "//div[@data-aem-id='qb_PreviousPolicy_Main_PolicyTime_ErrorBox']";
	public static final String previousPolicyValidationPopup = "//div[@data-aem-id='PreviousPolicy_PreviousPolicyValidationModal_ModalBody']";
	//public static final String previousPolicyValidationPopup = "//div[@data-aem-id='qb_Previous_Policy_Confirm_Modal_ModalContent']";

	/**
	 * @ Suspension Certificate(for Bike) option
	 *
	 */

	public static final String suspentionCertificateDialog = "//*[text()='すべてに該当します']";
	public static final String suspentionCertificateDialogPoint5 = "//span[contains(@data-aem-id,'ContractTypeModalInterruption_Text_10')]";
	public static final String suspentionCertificateDialogPoint6 = "//span[contains(@data-aem-id,'ContractTypeModalInterruption_Text_11')]";

	public static final String suspensionCertificateStartYear = "//div[@id='headerId-03']//following::div[1]//div[@data-aem-id='_SelectContent']//select";
	public static final String suspensionCertificateStartMonth = "//div[@id='headerId-03']//following::div[1]//div[@data-aem-id='_DateSelect01']//select";
	public static final String suspensionCertificateStartDay = "//div[@id='headerId-03']//following::div[1]//div[@data-aem-id='_DateSelect02']//select";

	public static final String suspensionCertificateEndYear = "//div[@id='headerId-04']//following::div[1]//div[@data-aem-id='_SelectContent']//select";
	public static final String suspensionCertificateEndMonth = "//div[@id='headerId-04']//following::div[1]//div[@data-aem-id='_DateSelect01']//select";
	public static final String suspensionCertificateEndDay = "//div[@id='headerId-04']//following::div[1]//div[@data-aem-id='_DateSelect02']//select";
	public static final String carInsurnaceQuestionariesOfSIC = "//div[@data-aem-id='qb_PreviousPolicy_Main_DivButtonBox']//li[text()='はい']";
	public static final String vehicleInspectionRegistrationYear = "//div[@class='is-invalid']//div[@data-aem-id='_SelectContent']//select";
	public static final String vehicleInspectionRegistrationMonth = "//div[@class='is-invalid']//div[@data-aem-id='_DateSelect01']//select";
	public static final String vehicleInspectionRegistrationDay = "//div[@class='is-invalid']//div[@data-aem-id='_DateSelect02']//select";


	/**
	 * @ Suspended Policy field Error message validation
	 **/

	public static final String suspendedPolicyReason= "//div[@class='invalid-feedback']";
	public static final String suspendedPolicySelectionError = "//div[contains(@data-aem-id,'qb_PreviousPolicy_Main_BoxDiv')]//p[@data-testid='error_text']";
	public static final String suspendedPolicyStartDateError = "(//div[@id='headerId-03']//following::div[1]//div[@data-aem-id='_BoxContain']//div)[6]";
	public static final String suspendedPolicyEndDateError = "(//div[@id='headerId-04']//following::div[1]//div[@data-aem-id='_BoxContain']//div)[6]";
	public static final String suspendedPolicyGradeError = "//div[contains(@data-aem-id,'SelectDiv_YearSelect01')]//following::p[@data-testid='error_txt']";
	public static final String suspendedPolicyAccidentError = "//div[@data-aem='UpperContainer']//div[@data-aem-id='ValidationWarningText']";
	public static final String suspendedPolicyVehicleInsurnaceError = "//div[@data-aem='UpperContainer']//div[@data-aem-id='ValidationWarningText']";
	public static final String suspendedPolicyRegistrationDateError = "(//div[@id='headerId-09']//following::div[1]//div[@data-aem-id='_BoxContain']//div)[6]";
	public static final String suspendedPolicyOverseasTravelPopup = "//div[@data-aem-id='overseasTravel_ModalBody']";
	public static final String suspendedPolicyStartDateLimitPopup = "//div[@data-aem-id='startDateLimit_ModalBody']";
	public static final String suspendedPolicyAcquisitionDateLimitPopup = "//div[@data-aem-id='acquisitionDateLimit_ModalBody']";

	/**
	 *@Vehicle page (Car)
	 **/

	public static final String carRegistrationYear = "//select[@name='firstRegister.year']";
	public static final String carRegistrationMonth = "//select[@name='firstRegister.month']";
	public static final String unkownCarRegistrationCheckbox = "//label[@for='noFirstRegister01']";
	public static final String carTempRegistrationYear = "//select[@name='exYear']";
	public static final String enterCarModelNumber = "//input[@name='carType']";
	public static final String searchCarModelNumber = "//button[text()='車両検索']";
	public static final String searchCarByManufacturerCheckbox = "//label[@for='noCarType01']";
	public static final String selectCarName = "//select[@name='exCarName']";
	public static final String selectCarType = "//select[@name='exCarType']";
	public static final String carMileage = "//select[@name='annualDistance']";
	public static final String carUsingWithChildConfirmation = "//label[@for='infantRiding_Adj01']";
	public static final String carUsingWithChildDeny = "//label[@for='infantRiding_Adj02']";
	public static final String unlownChildAgeCheckbox = "//input[@name='noInfantAge']";
	public static final String selectChilderenAge = "//select[@name='infantAge_Adj']";
	public static final String childCareDiscountBanner = "//img[@data-aem='VehicleInfo_Banner']";
	public static final String multipleOwnershipDiscount = "//li[@data-aem='qb_VehicleInfo_Main_CenterBox_ReferenceList_ListItem']//a";
	public static final String selectCarManufacturerAfterSelection = "//div[@data-aem-id='VehicleInfo_Car_Form_Info_03']//p[2]";
	public static final String selectCarNameAfterSelection = "//div[@data-aem-id='VehicleInfo_Car_Form_Info_04']//p[2]";


	/**
	 *@Vehicle page (Bike)
	 **/
	public static final String bikeThreeWheelConfirmation = "//label[@for='threeWheelsVehicle01']";
	public static final String bikeThreeWheelDeny = "//label[@for='threeWheelsVehicle02']";
	public static final String enterBikeModelNumber = "//input[@data-testid='bikeNameInput']";
	public static final String bikeMileage = "//select[@name='mileage']";

	/**
	 *@Vehicle page (Car) field Error message validation
	 **/

	public static final String quotationCarRegistrationPeriodFieldError ="//div[@data-aem='VehicleInfo_Form_DateBox_Select_Invalid_Error']";
	public static final String quotationCarModelFieldError ="//input[@name='carType']//following-sibling::div";
	public static final String quotationCarMileageFieldError ="//div[@data-aem='VehicleInfo_CenterBox']//div[@class='invalid-feedback']";
	public static final String quotationCarBikePurposeTypeFieldError ="//div[@data-aem='VehicleInfo_CardItem_ImageRadioButtons_Error']";
	public static final String quotationCarUsingWithChildFieldError ="//div[@data-aem='VehicleInfo_TagCom_SelectRadioButtons_Error']";

	//SME Validation
	public static final String otherCarInsurancePolicySection = "//section[@data-aem-id='QB_Vehicle_PageSection_04']";
	public static final String carUsingWithChildrenSection = "//div[@data-aem-id='qb_VehicleInfo_Main_CenterBox_05']";

	/**
	 *@Vehicle page (Bike) field Error message validation
	 **/

	public static final String quotationBikeEngineFieldError ="//div[@data-aem='VehicleInfo_Form_DateBox_Select_Invalid_Error']";
	public static final String quotationBikeManufactureFieldError ="//input[@name='carType']//following-sibling::div";
	public static final String quotationBikeNameFieldError ="//div[@data-aem='VehicleInfo_CenterBox']//div[@class='invalid-feedback']";
	public static final String quotationBikeMileageFieldError ="//div[@data-aem='VehicleInfo_CardItem_ImageRadioButtons_Error']";
	public static final String quotationBikeUsageFieldError ="//div[@data-aem='VehicleInfo_TagCom_SelectRadioButtons_Error']";

	/**
	 *@Driver page
	 **/

	public static final String mainDriverPolicyHolderConfrimation = "//label[@for='isMainDriverPolicyHolder01']";
	public static final String mainDriverPolicyHolderDeny = "//label[@for='isMainDriverPolicyHolder02']";
	public static final String prefectureSelection = "//select[@name='prefecture']";
	public static final String driverBirthYear = "//select[@name='driverBirthDateYear']";
	public static final String driverBirthMonth = "//select[@name='driverBirthDateMonth']";
	public static final String driverBirthDay = "//select[@name='driverBirthDateDay']";
	public static final String carDriverLimitConfirmation = "//label[@for='driversLimit01']";
	public static final String carDriverLimitDeny = "//label[@for='driversLimit02']";

	/**
	 *@Driver page field Error message validation
	 **/
	public static final String maindriverPrefectureFieldError = "//div[contains(@data-aem,'DriverPrefecture_Dropdown_Error')]";
	public static final String mainDriverDOBFieldError = "//div[contains(@data-aem,'DriverBirthDate_DateDropdown_Error')]";
	public static final String mainDriverLicenceFieldError = "//div[contains(@data-aem,'LicenseSelect_ImageRadioButtons_Error')]";
	public static final String mainDriverRangeFieldError = "//div[contains(@data-aem,'AgeConditions_RadioButtons_RadioButtons_Error')]";

	//SME Validation
	public static final String driverBirthDateSection = "//section[contains(@data-aem-id,'DriverBirthDate_PageSection')]";
	public static final String driverLicenceSection = "//section[contains(@data-aem-id,'DriverLicense_PageSection')]";
	public static final String driverRange = "//section[contains(@data-aem-id,'DriverRange_PageSection')]";
	public static final String isMainDriverPolicyHolderSectionTitle = "//span[contains(@data-aem,'IsMainDriverPolicyHolder_PageSection_SectionHeader_Title')]";
	public static final String isMainDriverPolicyHolderSectionText = "//span[contains(@data-aem,'IsMainDriverPolicyHolder_Text')]";
	public static final String isMainDriverPopup = "//div[@data-aem-id='isNotMainDriver_ModalDialog']";
	public static final String isMainDriverPopupBody = "//div[@data-aem-id='isNotMainDriver_ModalBody']";
	public static final String driverPrefectureSectionTitle = "//span[contains(@data-aem,'DriverPrefecture_PageSection_SectionHeader_Title')]";
	public static final String driverPrefectureSection = "//section[contains(@data-aem,'DriverPrefecture_PageSection')]";
	public static final String driverAgeRangeSection = "//div[contains(@data-aem,'DriversRange_PageSection_Container')]";
	public static final String driverAgeRangeCondition = "//div[contains(@data-aem-id,'DriversRange_AgeContent_AgeConditions_RadioButtons_Id')]";
	public static final String driverAgeRangeSectionRefrence = "//li[contains(@data-aem,'DriversRange_AgeContent_ReferenceList_ListItem')]";
	public static final String driverLimitPopup = "//div[@data-aem-id='QB_DriverLimitModal_ModalBody']";
	public static final String driverAemQuestionerySection = "//section[contains(@data-aem-id,'dataAem_PageSection')]";

	/**
	 *@Tarrif page
	 **/

	public static final String policyPlan1 = "//p[contains(@data-aem-id,'qb_Tariff_Main_MainBox_PriceChoice_WrapperNextButtonP0')]//button";
	public static final String policyPlan2 = "//p[contains(@data-aem-id,'qb_Tariff_Main_MainBox_PriceChoice_WrapperNextButtonP1')]//button";
	public static final String policyPlan3 = "//p[contains(@data-aem-id,'qb_Tariff_Main_MainBox_PriceChoice_WrapperNextButtonP2')]//button";
	public static final String policyPlan1Quotation = "(//div[text()=' 円/年'])[1]//preceding-sibling::div";
	public static final String policyPlan2Quotation = "(//div[text()=' 円/年'])[2]//preceding-sibling::div";
	public static final String policyPlan3Quotation = "(//div[text()=' 円/年'])[3]//preceding-sibling::div";
	public static final String policyPlanConfirmationDialog = "//div[@data-aem='DialogModal_SimpleButton']//button";
	public static final String earthquackConfirmationDialog = "//div[@data-aem='EarthquakeModalContent_ModalContent']//following::div//button";
	public static final String undefinedPopup = "//div[@data-aem='undefined_ModalContainer']";
	public static final String infoCorrectionDialog = undefinedPopup+"//button";
	public static final String previousPageReturnDialogConfirmation = "//div[@data-aem='undefined_PreviousModal_PreviousModalBoxBox']//button[@data-testid='next_btn']";
	public static final String IDEMoreThan60DaysPopup= "//div[@data-aem='undefined_PreviousModalBoxContent']//p";
	public static final String IDEMoreThan60DaysPopupConfirmation= "//button[text()='保存する']";
	public static final String mobileViewGetPolicyQuoteAmount =  "//span[@class='odometer-value']";
	public static final String mobileViewSelectPolicyPlan1Tab =  "//button[@data-aem-id='qb_Tariff_Main_MobilePage_HeaderTab01']";
	public static final String mobileViewSelectPolicyPlan2Tab =  "//button[@data-aem-id='qb_Tariff_Main_MobilePage_HeaderTab02']";
	public static final String mobileViewSelectPolicyPlan3Tab = "//button[@data-aem-id='qb_Tariff_Main_MobilePage_HeaderTab03']";
	public static final String mobileViewSelectPolicyPlanButton=  "//p[@data-aem='MobilePage_HeaderButton']";

	/**
	 *@Tarrif page content validation
	 **/

	public static final String tarrifPage_ItemDetailSection1=  "//div[@data-aem='MainBox_ChoiceDetail_ItemDetailSection0']";
	public static final String tarrifPage_ChoiceDetailContainers=  "(//div[contains(@data-aem-id,'MainBox_ChoiceDetail_ChoiceDetailItemContainer')])";
	public static final String tarrifPage_ChoiceDetailsSectionsHeader=  "//div[contains(@data-aem,'ChoiceDetail_DetailHeaderText')]";
	public static final String tarrifPage_ChoiceDetailsSectionsBody=  "//div[contains(@data-aem,'ChoiceDetail_DetailBody')]";

	public static final String tarrifPage_DriverSpecialAgreementSection=  "//div[text()='運転者限定特約']/../..";
	public static final String tarrifPage_DriverAgeRangeSection=  "//div[text()='運転者年齢条件特約']/../..";
	public static final String tarrifPage_Mobile_DriverSpecialAgreementSection=  "//p[text()='運転者限定特約']//following-sibling::p";
	public static final String tarrifPage_Mobile_DriverAgeRangeSection=  "//div[text()='運転者年齢条件特約']/../..";
	public static final String tarrifPage_vedioLink=  "//button[text()='詳細動画を見る']";

	/**
	 *@Vehicle Information page
	 **/
	public static final String genderMale = "//label[@for='insuredSex02']";
	public static final String genderFemale = "//label[@for='insuredSex01']";
	public static final String carModficationConfirmation = "//label[@for='vehicleModifications01']";
	public static final String vehicleCurrentInsurancePolicyNumber = "//input[@name='currentInsurance']";
	public static final String vehicleCurrentInsurnaceQuestionariesDeny = "//label[@for='driverIdentity02']";
	public static final String vehicleCurrentInsurnaceQuestionariesConfirm = "//label[@for='driverIdentity01']";
	public static final String vehicleSuspendedInsurancePolicyNumber = "//input[@name='suspendedInsurance']";
	public static final String vehicleSuspendedInsurnaceQuestionariesDeny = "//label[@for='driverIdentity02']";
	public static final String vehicleSuspendedInsurnaceQuestionariesConfirm = "//label[@for='driverIdentity01']";
	public static final String vehicleChassisNumber = "//input[@name='chassisNumber']";
	public static final String carLicensePlatePrefecture = "//select[@name='licensePlatePrefecture']";
	public static final String carlicensePlateClassCode = "//input[@name='licensePlateClassCode']";
	public static final String bikelicensePlateKanji = "//input[@name='licensePlateKanji']";
	public static final String bikelicensePlateKatakana = "//input[@name='licensePlateKatakana']";
	public static final String vehicleSecondaryOwnerLastNameKanji = "//input[@name='ownerOnVehicleInspectionCertificateLastNameKanji']";
	public static final String vehicleSecondaryOwnerFirstNameKanji = "//input[@name='ownerOnVehicleInspectionCertificateFirstNameKanji']";
	public static final String vehicleSecondaryOwnerLastNameKatakana = "//input[@name='ownerOnVehicleInspectionCertificateLastNameKatakana']";
	public static final String vehicleSecondaryOwnerFirstNameKatakana = "//input[@name='ownerOnVehicleInspectionCertificateFirstNameKatakana']";
	public static final String vehiclelicensePlateHiragana = "//input[@name='licensePlateHiragana']";
	public static final String vehiclelicensePlateSerialNumber = "//input[@name='licensePlateSerialNumber']";
	public static final String vehicleMileage = "//input[@name='mileage']";
	public static final String vehicleMileageConfirmationYear = "//select[@name='mileageConfirmationDateYear']";
	public static final String vehicleMileageConfirmationMonth = "//select[@name='mileageConfirmationDateMonth']";
	public static final String vehicleMileageConfirmationDay = "//select[@name='mileageConfirmationDateDay']";
	public static final String gradeConfirmationPopupNextButton = "//div[@data-testid='grade_next']";
	public static final String gradeConfirmationPopupFixButton = "//div[@data-testid='grade_fix']";
	public static final String vehicleInfo_CurrentInsuranceInfo_Section = "//section[@data-aem='QB_BeforeApplication_CurrentInsuranceInformation_PageSection']";
	public static final String vehicleInfo_SuspendedInsuranceInfo_Section = "//section[@data-aem='QB_BeforeApplication_SuspendedInsuranceInformation_PageSection']";
	public static final String vehicleInfo_CurrentInsurance_PolicyNumber= "("+vehicleInfo_CurrentInsuranceInfo_Section+"//input[@name='currentInsurance'])[1]";
	public static final String vehicleInfo_suspendedInsurance_PolicyNumber = "("+vehicleInfo_SuspendedInsuranceInfo_Section+"//input[@name='suspendedInsurance'])[1]";
	public static final String vehicleInfo_CurrentInsurance_BranchNumber="("+vehicleInfo_CurrentInsuranceInfo_Section+"//input[@name='currentInsurance'])[2]";
	public static final String vehicleInfo_suspendedInsurance_BranchNumber = "("+vehicleInfo_SuspendedInsuranceInfo_Section+"//input[@name='suspendedInsurance'])[2]";
	public static final String vehicleInfo_InsuredCurrentAndInterruptionContractPopup = "//div[@data-aem-id='InsuredCurrentAndInterruptionContract_ModalContent']";
	public static final String vehicleInfo_CurrentInsuranceInsuredRelation= "//section[contains(@data-aem-id,'CurrentInsuranceInsuredRelationImageRadioButtons')]";
	public static final String vehicleInfo_SuspendedInsuranceInsuredRelation= "//section[contains(@data-aem-id,'SuspendedInsuranceInsuredRelationImageRadioButtons')]";
	public static final String vehicleInfo_CurrentContractDriverIdentityText= "//span[contains(@data-aem,'CurrentContractDriverIdentityButtons')]";
	public static final String vehicleInfo_SuspendedInsuranceDriverIdentityText= "//div[contains(@data-aem,'SuspendedInsuranceDriverIdentityButtons')]/span";
	public static final String vehicleInfo_FreightVehicleLoadCapacitySection= "//section[contains(@data-aem,'VehicleInformation_LoadCapacityOverTwoTonsButtons')]";
	public static final String vehicleInfo_BusinessUseVehicleSection= "//section[contains(@data-aem,'VehicleInformation_BusinessUseVehicleButtons')]";
	public static final String vehicleInfo_BusinessUseVehicleSectionTitle= "//div[contains(@data-aem,'BusinessUseVehicleButtons_SelectRadioButtons_Title')]";
	public static final String vehicleInfo_BusinessUseVehicleModlePopup= "//div[@data-aem-id='BusinessUseVehicle_ModalBody']";
	public static final String vehicleInfo_FreightVehicleSectionTitle= "//div[contains(@data-aem,'LoadCapacityOverTwoTonsButtons_SelectRadioButtons_Title')]";
	public static final String vehicleInfo_FreightVehicleModlePopup= "//div[@data-aem-id='CapacityOver2T_ModalBody']";
	public static final String vehicleInfo_OwnerOnVehicleInspectionCertificateRelationImageSection= "//div[contains(@data-aem-id,'OwnerOnVehicleInspectionCertificateRelationImageRadioButtons_ImageRadioButtons_Id')]";
	public static final String vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityDeny= "//label[@for='ownerOnVehicleInspectionCertificateAndPolicyholderIdentity02']";
	public static final String vehicleInfo_ownerOnVehicleInspectionCertificateAndPolicyholderIdentityConfirm= "//label[@for='ownerOnVehicleInspectionCertificateAndPolicyholderIdentity01']";
	public static final String vehicleInfo_ownerOnVehicleInspectionCertificateNameInputField= "//div[contains(@data-aem-id,'OwnerOnVehicleInspectionCertificateNameInputFields_NameInputFields_Id')]";
	public static final String vehicleInfo_UserOnVehicleInspectionCertificateRelationImage= "//section[contains(@data-aem-id,'UserOnVehicleInspectionCertificateRelationImageRadioButtons_Id')]";
	public static final String vehicleInfo_UserOnVehicleInspectionDealer= "//section[contains(@data-aem-id,'OwnerOnVehicleInspectionDealerButtons_Id')]";
	public static final String vehicleInfo_HolderRelationModelContentPopup= "//div[@data-aem-id='HolderRelationModalContent_ModalContent']";
	public static final String vehicleInfo_SMEOwnerIntruptionOtherModelContentPopup= "//div[@data-aem-id='OwnerInterruptionOther_ModalContent']";
	public static final String vehicleInfo_PersonalOwnerIntruptionOtherModelContentPopup= "//div[contains(@data-aem,'OwnerInterruptionOther_ConfirmModalWrapper')]";
	public static final String vehicleInfo_SMEOwnerInterruptionIndiModelContentPopup= "//div[@data-aem-id='OwnerInterruptionIndi_ModalContent']";
	public static final String vehicleInfo_PersonalOwnerInterruptionIndiModelContentPopup= "//div[contains(@data-aem,'OwnerInterruptionIndi_ConfirmModalWrapper')]";

	/**
	 * #
	 * @Vehicle Information filed Error message validation
	 * #
	 **/

	public static final String vehicleInfoGenderFieldError = "//div[contains(@data-aem,'InsuredSexButtons_SelectRadioButtons_Error')]";
	public static final String vehicleInfoChassisNumberFieldError = "//div[contains(@data-aem,'ChassisNumberInputField_RegularInputField_Error')]";
	public static final String vehicleInfoLicencePlatePrefectureFieldError = "//div[contains(@data-aem,'LicensePlate_Prefecture_Dropdown_Error')]";
	public static final String vehicleInfoLicencePlateClassCodeFieldError = "//div[contains(@data-aem,'ClassCode_RegularInputField_Error')]";
	public static final String vehicleInfoLicencePlateHiraganaFieldError = "//div[contains(@data-aem,'LicensePlate_Hiragana_RegularInputField_Error')]";
	public static final String vehicleInfoLicencePlateSerialNumberFieldError = "//div[contains(@data-aem,'LicensePlate_SerialNumber_RegularInputField_Error')]";
	public static final String vehicleInfoMilageFieldError = "//div[contains(@data-aem,'VehicleMileageInformation_InputFieldWithText_Error')]";
	public static final String vehicleInfoCurrentInsuranceInputFieldError = "//div[contains(@data-aem,'CurrentInsuranceInputField_RegularInputField_Error')]";
	public static final String vehicleInfoSuspendedInsuranceInputFieldError = "//div[contains(@data-aem,'SuspendedInsuranceInputField_RegularInputField_Error')]";
	public static final String vehicleInfoVehicleWithNumberEightModal = "//div[@data-aem-id='VehicleWithNumberEightModal_ModalDialog']";

	//SME

	public static final String mainDriverGenderSection = "//section[contains(@data-aem-id,'BeforeApplication_MainDriverInformation_PageSection')]";
	public static final String vehicleInfoGenderSectionText = "//div[contains(@data-aem,'MainDriverInformation_InsuredSexButtons_SelectRadioButtons_Title')]";
	public static final String vehicleInfo_SMEAEBJikenkyoUnknownAnswerPopup = "//div[@data-aem-id='AEBJikenkyoUnknownAnswer_ModalContent']";
	public static final String vehicleInfo_PersonalAEBJikenkyoUnknownAnswerPopup = "//div[@data-aem='AEBJikenkyoUnknownAnswer_WhiteBlock']";


	/**
	 *@Contractor page
	 **/

	public static final String lastNameKanji = "//input[@name='lastNameKanji']";
	public static final String firstNameKanji = "//input[@name='firstNameKanji']";
	public static final String lastNameFurigana = "//input[@name='lastName']";
	public static final String firstNameFurigana = "//input[@name='firstName']";
	public static final String postalCode = "//input[@name='postalCode']";
	//public static final String searchAddress = "//span[text()='住所検索']";
	public static final String searchAddress = "//*[text()='住所検索']";
	public static final String confirmAddress = "//button[text()='決定']";
	public static final String homeNumber = "//input[@name='addressDoorNumber']";
	public static final String buildingName = "//input[@name='buildingName']";
	public static final String mobileNumber = "//input[@name='mobileNumber']";
	public static final String emailaddress = "//input[@name='email']";
	public static final String emailPermissionCheckbox = "//label[@for='emailPermission01']";


	/**
	 *@Contractor Information for Corporate
	 **/

	public static final String contractor_legalEntityType = "//select[@name='legalEntityType']";
	public static final String contractor_CorporateOther = "//input[@name='corporateStatusOther']";
	public static final String contractor_companyInfoSection = "//section[contains(@data-aem,'CompanyInfo_PageSection')]";
	public static final String contractor_statusPosition = "(//div[@data-aem='QB_Contractor_Info_SME_SelectRadioButtons'])[1]";
	public static final String contractor_corporateName = "//input[@name='corporateName']";
	public static final String contractor_corporateNameKana = "//input[@name='corporateNameKana']";
	public static final String contractor_representativeTitle = "//input[@name='representativeTitle']";
	public static final String contractor_representativeTitleKana = "//input[@name='representativeTitleKana']";
	public static final String contractor_delegateInfoSection = "//section[contains(@data-aem,'DelegateInfo_PageSection')]";
	public static final String contractor_delegateLastNameKanji = "//input[@name='delegateLastNameKanji']";
	public static final String contractor_delegateFirstNameKanji = "//input[@name='delegateFirstNameKanji']";
	public static final String contractor_delegateLastNameKana = "//input[@name='delegateLastName']";
	public static final String contractor_delegateFirstNameKana = "//input[@name='delegateFirstName']";
	public static final String contractor_lastNameKanji = "//input[@name='representativeLastNameKanji']";
	public static final String contractor_FirstNameKanji = "//input[@name='representativeFirstNameKanji']";
	public static final String contractor_lastNameFurigana = "//input[@name='representativeLastName']";
	public static final String contractor_FirstNameFurigana = "//input[@name='representativeFirstName']";
	public static final String contractor_RepresentativeMobileNumber = "//input[@name='representativePhoneNumber']";
	public static final String contractor_CorporateMobileNumber = "//input[@name='contactPhoneNumber']";
	public static final String unableToFindPostalCode = "//div[@data-aem-id='cannotFindPostalAddress_ModalBody']";
	public static final String contractor_frontPageError = "//li[@data-aem='QB_Contractor_Info_SME_ReferenceList_ListItem' and @type='error']";
	public static final String accountMergePopupTitle = "//*[text()='お客さまのアカウントはすでに登録されています']";


	/**
	 *@Term and Conditions page.
	 **/

	public static final String pdfCertificateCheckbox = "//label[@for='eInsuranceCertificate01']";
	public static final String insuranceCertificateCheckbox = "//label[@for='eInsuranceCertificate02']";
	public static final String termandConditionsCheckbox = "//label[@for='importantMattersAgreement01']";
	public static final String infoConfirmationCheckbox = "//label[@for='ConfirmAgree01']";
	public static final String TnC_EInsuranceCertificatesection = "(//div[contains(@data-aem-id,'TermsConfirmForm_EInsuranceCertificate')])[1]";
	public static final String TnC_ContractConfirmationDetailsSection = "//div[contains(@data-aem-id,'ConfirmationOfContractDetails_PageSection_Container')]";

	/**
	 * @Term and conditions Check box Error message validation
	 *
	 */
	public static final String insuranceCertificateCheckboxError = "//div[contains(@data-aem,'ermsConfirmForm_EInsuranceCertificate_BorderCheckBoxGroup_CheckBoxGroup_Error')]";
	public static final String termandConditionsCheckboxError = "//div[contains(@data-aem,'TermsConfirmForm_ImportantMattersAgreement_CheckBoxGroup_Error')]";
	public static final String infoConfirmationCheckboxError = "//div[contains(@data-aem,'ContractForm_ContractBody_CheckBoxGroup_Error')]";
	public static final String termAndConditionContractGuideLink = "//a[@data-aem='QB_TermsConfirm_ContractGuide_Link']";
	public static final String termAndConditionImpMatters = "//a[@data-aem='QB_TermsConfirm_ImportantMattersManual_ButtonLink']";
	public static final String termAndConditionDialogText3 = "//li[@data-aem='DialogModal_SimpleLi2']";
	public static final String termAndConditionDialogText4 = "//li[@data-aem='DialogModal_SimpleLi3']";


	/**
	 * 
	 * 
	 * @Payment page * * 
	 * 
	 **/

	public static final String fullPaymentOption = "//label[@data-aem='qb_Payment_Main_FirstTitle_CustomRadio01_RadioButton']";
	public static final String installmentPaymentOption = "//label[@data-aem='qb_Payment_Main_FirstTitle_CustomRadio02_RadioButton']";
	public static final String CreditCarPayment = "//label[@for='bbbcreditCard']";
	public static final String konbiniPayment = "//label[@for='bbbocvs']";
	public static final String proceedPayment = "//label[@for='singleBottomPageButtonId']";
	public static final String enterCardNumber = "//input[@name='cardNumberField']";
	public static final String enterCardExpiaryMonth = "//select[@name='creditCardValidDateMonth']";
	public static final String enterCardExpiaryYear = "//select[@name='creditCardValidDateYear']";
	public static final String enterCardSicurityNumber = "//input[@name='securityNumberField']";
	//public static final String PaymentContentConfirmationButton = "//span[text()='この内容で確認']";
	public static final String PaymentContentConfirmationButton = "//*[text()='この内容で確認']";
	//public static final String confirmCreditCardPaymentInfo = "//span[text()='支払い']";
	public static final String confirmCreditCardPaymentInfo = "//*[text()='支払い']";
	public static final String finalPaymentConfirmation = "//label[@for='callToActionButton']";
	public static final String getPolicyNumber = "//span[text()='見積り番号']//following::span[1]";
	public static final String registerAccount = "//span[text()='アカウント登録する']";
	public static final String emmaButtonOnFinalPage = "//label[@for='singleBottomPageButtonId']//span[contains(text(),'Emma')]";
	public static final String paymentAmountInfo = "(//div[@data-aem='qb_Payment_Main_AmountInfo']//span)[2]";
	public static final String creditCardNumberFieldError = "//div[contains(@data-aem,'CardNumberField_RegularInputField_Error')]";
	public static final String creditCardCVVFieldError = "//div[contains(@data-aem,'Securitynumber_Error')]";
	public static final String konbiniPaymentCritariaPopup = "//div[@data-aem-id='qb_Payment_ValidationModal_ModalDialog']";
	public static final String payment_cardNote = "//li[contains(@data-aem,'CardNote_ReferenceList_ListItem')]";
	public static final String payment_installmentPaymentPopupInfo = "//ul[@data-aem='QB_CCInstallmentModal_dd1_ReferenceList']";
	public static final String payment_lumpsumPaymentPopupInfo = "//ul[@data-aem='QB_CCLumpSumModal_dd1_ReferenceList']";
	public static final String payment_cardOwnerNameConfirmationDetailSection = "//div[text()='カードのご名義人']/..";
	public static final String closing_RegisterAccountImage = "//p[text()='アカウント登録する']/..";
	public static final String closing_CreateAccountSection = "//section[@data-aem='qb_Closing_Section_CreateAccount_PageSection']";
	public static final String closing_ContractDiscountImage = "//img[@src='/qb/static/media/banner-04.1aa2cd8013026a6e2d10.png']";
	public static final String closing_EmmaAccordionSection = "//span[@data-aem='qb_Closing_Section_Accordion_Emma_SectionHeader_Title']";
	public static final String closing_EmmaAccordionSectionText = "//section[@data-aem='qb_Closing_Section_Accordion_Emma']";
	public static final String closing_NoPDConditionText = "(//div[@data-aem-id='qb_Closing_Section_PageSection_Container_Id']//p)[3]";
	public static final String closing_HeaderText = "//div[@data-aem='Headers']/p";
	public static final String RegisterAccountPassword = "//input[@autocomplete='new-password']";
	public static final String RegisterAccountRePassword = "//input[@name='password_re']";
	public static final String RegisterAccountShowPassword = "//input[@name='show_password_signup']/..";
	public static final String RegisterAccountSignupButton = "//button[@id='btn-signup']";
	public static final String RegisterAccountMailSendConfirmation = "//div[@data-aem-id='MailSend_AuthVerifyContain']";
	public static final String RegisterAccountError = "//div[@class='p-title-error has-error']/div";

	/**
	 * 
	 * 
	 * @Browser Back navigation * * 
	 * 
	 **/

	public static final String browserBack_Popup = "//div[@data-aem-id='qb_previousPageValidationModal_ModalDialog']";
	public static final String browserBack_PopupTitle = "//div[@data-aem-id='qb_previousPageValidationModal_ModalHeader']";
	public static final String browserBack_PopupContent = "//div[@data-aem-id='qb_previousPageValidationModal_ModalContent']";
	public static final String browserBack_PopupCloseButton = "//label[@for='qb_previousPageValidationModal_ModalCloseId']";
	public static final String browserBack_PopupAdditionalInfo = "//span[text()='もっと簡単にお見積りを知りたい方へ']/../../..";
}
