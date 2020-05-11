import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;




public class TestGoogleMap {
	


	public AppiumDriver driver;
	
		
	
	@Before
	public void setUp() throws MalformedURLException{
		
	
		
		//Here I am Setting up the desired capabilities and passing the Android app-activity and app-package to Appium
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("VERSION", "4.4"); 
		capabilities.setCapability("deviceName","Android Emulator");
		capabilities.setCapability("platformName","Android");
		
	    
	 // This is the package name of my app 
	    capabilities.setCapability("appPackage", "com.google.android.apps.maps");
	    
	 // This is the Launcher activity of my app 
		capabilities.setCapability("appActivity","com.google.android.maps.MapsActivity"); 
		
		
	 //It will launch the google maps App in my Android emulator using the configurations specified in Desired Capabilities
	   driver = new AppiumDriver (new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	   driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);  //this is the implicit wait time that I gave
	}

    @Test
	public void testCal() throws Exception {
	  
    	//Below I am locating various elements on my google map app screens by using By.className() and By.id() locators
      
       WebElement titleText=driver.findElement(By.className("android.widget.TextView"));
       System.out.println("The Title of home screen is : "+titleText.getText());//this will give title of the homescreen of google maps app
       
	   WebElement signInButton=driver.findElement(By.className("android.widget.Button"));
	   signInButton.click();  //this will click on the button which will take user to the sign in screen 
	   
	   WebElement addAccntButton=driver.findElement(By.className("android.widget.TextView"));
	   System.out.println("Clicking the sign in button opens a pop up which says : "+addAccntButton.getText());//add account pop up will appear
	   
	   addAccntButton.click();//this will click on "add account" , which will take user to second screen to enter email or phone number 
	  
	   System.out.println("Clicking the add account opens the second screen ");
	   
	   WebElement textBoxUsername=driver.findElement(By.className("android.widget.EditText"));
	   textBoxUsername.sendKeys("mobiletestertrial"); //this will enter the email in textbox 
	  
	  
	   
	   WebElement nextButton=driver.findElement(By.id("identifierNext"));
	   nextButton.click();// this will click the next button after entering the email
	   
	   
	   WebElement textBoxPassword=driver.findElement(By.className("android.widget.EditText"));
	   textBoxPassword.sendKeys("tester@11111111"); //this will enter password
	   driver.hideKeyboard(); 
	   
	   WebElement nextButtonPasswrd=driver.findElement(By.id("passwordNext"));
	   nextButtonPasswrd.click(); //this will click on next button after entering the password
	   
	   
	   WebElement signedInScreenTitle=driver.findElement(By.id("view_container")); //this will locate the next screen i.e. "I agree" page title after entering correct email and password
		
	   //Checking The test case has passed successfully only when the next screen after sign-in appears with the correct title
	assert signedInScreenTitle.getText().equals("Hi Mobile"):"Actual screen is : "+signedInScreenTitle.getText()+" did not match with expected title: Hi Mobile"; 
	   
	   
	   
	}  
    

	@After
	public void teardown(){
		//this will close the app
		driver.quit();
	} 
}
	

	


	


