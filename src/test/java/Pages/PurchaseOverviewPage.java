package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

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

    @FindBy(className = "inventory_item_price")
    public List<WebElement> productPrice;


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

    public void getTotalPrice() {
        productPrice.toArray(new WebElement[0]);
        WebElement totalPriceCount = driver.findElement(By.className("summary_subtotal_label"));

        double totalPrice = 0.0;
        double subtotal;

        for (WebElement productPriceElement : productPrice) {
            String productPriceText = productPriceElement.getText();
            double productPrice = Double.parseDouble(productPriceText.replaceAll("[^\\d.]", ""));
            totalPrice += productPrice;
        }
        System.out.println(totalPrice);

        String subtotalText = totalPriceCount.getText();
        subtotal = Double.parseDouble(subtotalText.replaceAll("[^\\d.]", ""));

       if(totalPrice == subtotal) {
           System.out.println("Individual product prices sum up to the subtotal price");
       } else {
           System.out.println("Individual product prices do not sum up to the subtotal price");
        }
    }

}
