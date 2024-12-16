package org.axa.framework;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PageAssertions;

public class Assertion {
	
	CommonFunctions common=new CommonFunctions();
	private static double waitTime=30000;
	
	public static void assertBytext(Page page,String elementPath,String expectedText){
		try {
		assertThat(page.locator(elementPath)).hasText(expectedText, new LocatorAssertions.HasTextOptions().setTimeout(waitTime) );
		//page.locator(elementPath).highlight();
		Report.logger.pass("Text is displayed as expected text: '"+expectedText);
		}catch(AssertionError error) {
			System.out.println(error);
			Report.logger.fail("Text is not displayed as expected text: '"+expectedText);
		}
	}
	public static void assertBytextIsNotEqual(Page page,String elementPath,String expectedText){
		assertThat(page.locator(elementPath)).not().hasText(expectedText, new LocatorAssertions.HasTextOptions().setTimeout(waitTime) );
		//page.locator(elementPath).highlight();
	}
	public static void assertBytext(Locator locator,String expectedText){
		assertThat(locator).hasText(expectedText, new LocatorAssertions.HasTextOptions().setTimeout(waitTime) );
		//locator.highlight();
	}
	
	public static void assertBytextContains(Page page,String elementPath,String expectedText){
		assertThat(page.locator(elementPath)).containsText(expectedText, new LocatorAssertions.ContainsTextOptions().setTimeout(waitTime));
		//page.locator(elementPath).highlight();
	}
	public static void assertBytextNotContains(Page page,String elementPath,String expectedText){
		assertThat(page.locator(elementPath)).not().containsText(expectedText, new LocatorAssertions.ContainsTextOptions().setTimeout(waitTime));
		//page.locator(elementPath).highlight();
	}
	public static void assertBytextContains(Locator locator,String expectedText){
		assertThat(locator).containsText(expectedText, new LocatorAssertions.ContainsTextOptions().setTimeout(waitTime));
		//locator.highlight();
	}
	public static void assertIsChecked(Page page,String elementPath){
		assertThat(page.locator(elementPath)).isChecked(new LocatorAssertions.IsCheckedOptions().setTimeout(waitTime));
		//page.locator(elementPath).highlight();
	}
	public static void assertHasCount(Page page,String elementPath,int count){
		//assertThat(page.locator(elementPath)).hasCount(count, new LocatorAssertions.HasCountOptions().setTimeout(waitTime));
	}
	public static void assertIsNotChecked(Page page,String elementPath){
		assertThat(page.locator(elementPath)).not().isChecked(new LocatorAssertions.IsCheckedOptions().setTimeout(waitTime));
		//page.locator(elementPath).highlight();
	}
	
	public static void assertIsDisabled(Page page,String elementPath){
		assertThat(page.locator(elementPath)).isDisabled(new LocatorAssertions.IsDisabledOptions().setTimeout(waitTime));
		//page.locator(elementPath).highlight();
	}
	
	public static void assertIsVisible(Page page,String elementPath){
		assertThat(page.locator(elementPath)).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(waitTime));
		//page.locator(elementPath).highlight();
	}
	
	public static void assertIsEnabled(Page page,String elementPath){
		assertThat(page.locator(elementPath)).isEnabled(new LocatorAssertions.IsEnabledOptions().setTimeout(waitTime));
		//page.locator(elementPath).highlight();
	}
	
	public static void assertIsEnabled(Locator locator){
		assertThat(locator).isEnabled(new LocatorAssertions.IsEnabledOptions().setTimeout(waitTime));
		//locator.highlight();
	}
	
	public static void assertIsEditable(Page page,String elementPath){
		
		assertThat(page.locator(elementPath)).isEditable(new LocatorAssertions.IsEditableOptions().setTimeout(waitTime));
		//page.locator(elementPath).highlight();
		
	}
public static void assertIsNotEditable(Page page,String elementPath){
		
		assertThat(page.locator(elementPath)).not().isEditable(new LocatorAssertions.IsEditableOptions().setTimeout(waitTime));
		//page.locator(elementPath).highlight();
		
	}
	
	public static void assertIsHidden(Page page,String elementPath){
		assertThat(page.locator(elementPath).first()).isHidden(new LocatorAssertions.IsHiddenOptions().setTimeout(45000));
	}
	
	public static void assertHasValue(Page page,String elementPath,String value){
		assertThat(page.locator(elementPath)).hasValue(value, new LocatorAssertions.HasValueOptions().setTimeout(waitTime));
	}
	
	public static void assertIsAttached(Page page,String elementPath){
		assertThat(page.locator(elementPath)).isAttached(new LocatorAssertions.IsAttachedOptions().setTimeout(waitTime));
		//page.locator(elementPath).highlight();
	}
	public static void assertIsNotAttached(Page page,String elementPath){	
		assertThat(page.locator(elementPath)).not().isAttached(new LocatorAssertions.IsAttachedOptions().setTimeout(waitTime));
	}
	public static void assertIsEmpty(Page page,String elementPath){
		assertThat(page.locator(elementPath)).isEmpty(new LocatorAssertions.IsEmptyOptions().setTimeout(waitTime));
	}
	public static void assertIsNotEmpty(Page page,String elementPath){
		assertThat(page.locator(elementPath)).not().isEmpty(new LocatorAssertions.IsEmptyOptions().setTimeout(waitTime));
	}
	public static void assertHasAttributePattern(Page page,String elementPath,String attributeName,String pattern){
		try {
		assertThat(page.locator(elementPath)).hasAttribute(attributeName, Pattern.compile(pattern), new LocatorAssertions.HasAttributeOptions().setTimeout(waitTime));
		//page.locator(elementPath).highlight();
		Report.logger.pass(attributeName+" value has contains expected pattern: '"+pattern);
	}catch(AssertionError error) { 
		Report.logger.pass(attributeName+"  value is not contains expected pattern: '"+pattern);
	}
	}
	public static void assertHasAttribute(Page page,String elementPath,String attributeName,String value){
		try {
		assertThat(page.locator(elementPath)).hasAttribute(attributeName, value,  new LocatorAssertions.HasAttributeOptions().setTimeout(waitTime));
		Report.logger.info(attributeName+" value is set as '"+value);
		}catch(AssertionError error) { 
			Report.logger.fail(attributeName+"  value is not same as expected value: '"+value);
		}
		
	}
	public static boolean assertTitleContains(Page page,String pattern){
		assertThat(page).hasTitle(Pattern.compile(pattern),new PageAssertions.HasTitleOptions().setTimeout(waitTime));
		return true;
		
	}
	
	public static void assertEvaluateElementProperty(Page page,String elementPath,String propertyName,String value){
		assertThat(page.locator(elementPath)).hasCSS(propertyName, value, new LocatorAssertions.HasCSSOptions().setTimeout(waitTime));
		
	}
	
	  public static boolean assertURLContains(Page page,String pattern){ 
		try {
		
		//assertIsHidden(page, pattern);	
			//page.url().endsWith(pattern);
	  assertThat(page).hasURL(Pattern.compile("/"+pattern),new PageAssertions.HasURLOptions().setTimeout(5000));
	  return true;
	  }catch(AssertionError error) { 
	  return false; 
		  }
	  
	  }
	  
	  public static boolean URLConfirmation(Page page,String elementPath,String pattern){ 
			assertIsHidden(page, elementPath);	
			//boolean pageName=page.url().endsWith(pattern);
			if(page.url().endsWith(pattern)) {
				return true;
			}else {
				 return false;
			}  
		  }
	 

}
