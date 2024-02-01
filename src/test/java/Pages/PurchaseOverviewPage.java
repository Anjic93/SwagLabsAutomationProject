package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseOverviewPage extends BaseTest {

    public PurchaseOverviewPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    public WebElement overviewPageTitle;

    @FindBy(className = "summary_info")
    public WebElement purchaseInfo;

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(id = "cancel")
    public WebElement cancelButton2;


    //-----------------------

    public String overviewPageTitleName() {
        return overviewPageTitle.getText();
    }

    public void clickOnFinishButton() {
        finishButton.click();
    }

    public void clickOnCancelButton() {
        cancelButton2.click();
    }
}
