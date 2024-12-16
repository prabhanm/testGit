package org.axa.portal.page;

public class ParameterOfCreditCardDetails {
	
	private String cardType;
	private String CardNumber;
	private String cardExpiaryDate;
	private String cvvNumber;
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNumber() {
		return CardNumber;
	}
	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}
	public String getCardExpiaryDate() {
		return cardExpiaryDate;
	}
	public void setCardExpiaryDate(String cardExpiaryDate) {
		this.cardExpiaryDate = cardExpiaryDate;
	}
	public String getCvvNumber() {
		return cvvNumber;
	}
	public void setCvvNumber(String cvvNumber) {
		this.cvvNumber = cvvNumber;
	}

}
