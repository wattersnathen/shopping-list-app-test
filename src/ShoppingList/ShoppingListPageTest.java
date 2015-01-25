package ShoppingList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import ShoppingList.ShoppingListPage;

public class ShoppingListPageTest {
	
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        
        ShoppingListPage shoppinglist = new ShoppingListPage(driver);
        shoppinglist.load();
        shoppinglist.addItemToList("Apples", 2);
        shoppinglist.addItemToList("Oranges", 1);
        shoppinglist.addItemToList("Grapes", 1);
        
        WebElement kiwis = shoppinglist.addItemToList("Kiwis", 2);
        
        Thread.sleep(3000);
        
        shoppinglist.clickItemCheckbox(kiwis);
        shoppinglist.clickItemCheckbox("Grapes");
        
        Thread.sleep(3000);
        
        shoppinglist.clickItemCheckbox(kiwis);
        Thread.sleep(2000);
        shoppinglist.editItem("Grapes", "Red Grapes", 7);
        
        Thread.sleep(3000);
        
        shoppinglist.deleteItemFromPage("Oranges");
        shoppinglist.deleteItemFromPage(kiwis);
        Thread.sleep(5000);
        		
        shoppinglist.close();
    }
}
