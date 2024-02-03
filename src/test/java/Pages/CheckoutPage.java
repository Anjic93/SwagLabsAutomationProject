package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends BaseTest {


    public CheckoutPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")   //check out page title
    public WebElement checkoutPageTitle;

    @FindBy(className = "checkout_info")  //form to be filled, for checkout
    public WebElement checkoutForm;

    @FindBy(id = "first-name")
    public WebElement firstNameField;

    @FindBy(id = "last-name")
    public WebElement lastNameField;

    @FindBy(id = "postal-code")
    public WebElement postalCodeField;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(css = ".error-message-container.error")  //error message for invalid inputs
    public WebElement errorMessage;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    //-------------------------------


    public String getCheckoutPageTitle() {
        return checkoutPageTitle.getText();
    }


    public void inputFirstName(String name) {
        firstNameField.clear();
        firstNameField.sendKeys(name);
    }

    public void inputLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void inputPostalCode(String postalCode) {
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    public void clickOnContinueButton() {
        continueButton.click();
    }

    public void clickOnCancelButton() {
        cancelButton.click();
    }

}
