package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(css = ".error-message-container.error")
    public WebElement errorMessage;



    //--------------------------

    public void inputUsername(String userName) {
        usernameField.clear();  // Before each entry, it is necessary to do clear in case the descriptive text
                                // of the field remains displayed inside the field when we enter data
        usernameField.sendKeys(userName);
    }

    public void inputPassword(String passWord) {
        passwordField.clear();
        passwordField.sendKeys(passWord);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }


}
