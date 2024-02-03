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

    @FindBy(className = "summary_info")  //payment informations
    public WebElement purchaseInfo;

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(id = "cancel")
    public WebElement cancelButton2;

    @FindBy(className = "inventory_item_price")  //price of single product
    public List<WebElement> productPrice;

    @FindBy(className = "summary_tax_label")  //tax amount
    public WebElement tax;


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

    public void getTotalPrice() {   //to verify that count prices of added products are equal to total price
        productPrice.toArray(new WebElement[0]); //added products prices are in new array
        WebElement totalPriceCount = driver.findElement(By.className("summary_subtotal_label")); //items total price

        double totalPrice = 0.0;
        double subtotal;
        double taxNumb;
        double total;

        for (WebElement productPriceElement : productPrice) {
            String productPriceText = productPriceElement.getText(); //get price of single product as a String
            double productPrice = Double.parseDouble(productPriceText.replaceAll("[^\\d.]", ""));  //String price of single product parsed in Double
            System.out.println("Product price: " + productPrice);
            totalPrice += productPrice; //sum of single product prices
        }
        System.out.println("Item total: " + totalPrice);

        String subtotalText = totalPriceCount.getText();  //get items total price as a String
        subtotal = Double.parseDouble(subtotalText.replaceAll("[^\\d.]", "")); //String items total price parsed in Double
        System.out.println("Subtotal: " + subtotal);

        String taxText = tax.getText();  //get tax amount as a String
        taxNumb = Double.parseDouble(taxText.replaceAll("[^\\d.]", ""));  //String tax amount parsed in Double
        System.out.println("Tax: " + taxNumb);
        total = subtotal + taxNumb;  //Count Total, sum of items total price and tax amount together
        System.out.println("Total(with tax): " + total);

       if(totalPrice == subtotal) {  //to verify that sum of all added products is equal to items total price
           System.out.println("Individual product prices sum up to the subtotal price");
       } else {
           System.out.println("Individual product prices do not sum up to the subtotal price");
        }


    }

}
