package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseCompletePage extends BaseTest {

    public PurchaseCompletePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    public WebElement completePageTitle;

    @FindBy(className = "complete-header")  //Thank You message on complete purchase page
    public WebElement thankYouMsg;

    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;

    //---------------------------

    public String completePageTitleName() {
        return completePageTitle.getText();
    }

    public String getThankYouMsg() {
        return thankYouMsg.getText();
    }

    public void clickOnBackHomeButton() {
        backHomeButton.click();
    }
}
