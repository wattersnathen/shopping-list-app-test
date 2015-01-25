package ShoppingList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import ShoppingList.ShoppingListPage;

public class ShoppingListPageTest {
	
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        
        // maximize the browser window 
        driver.manage().window().maximize();
        
        // create an instance of our Shopping List
        ShoppingListPage shoppinglist = new ShoppingListPage(driver);
        
        // load the shopping list page
        shoppinglist.load();
        
        // add items to the list
        shoppinglist.addItemToList("Apples", 2);
        shoppinglist.addItemToList("Oranges", 1);
        shoppinglist.addItemToList("Grapes", 1);
        
        WebElement kiwis = shoppinglist.addItemToList("Kiwis", 2);
        WebElement nuts  = shoppinglist.addItemToList("Mixed Nuts", 9);
        
        Thread.sleep(1500); // pause for visual checking
        
        // move items to the purchased list
        shoppinglist.clickItemCheckbox(kiwis);
        shoppinglist.clickItemCheckbox("Grapes");
        shoppinglist.clickItemCheckbox(nuts);
        
        Thread.sleep(1500); // pause for visual checking
        
        // move kiwis back to 'Items' list
        shoppinglist.clickItemCheckbox(kiwis);
 
        Thread.sleep(1500); // pause for visual checking
        
        // edit the 'Grapes' item (currently in 'Purchased' list)
        shoppinglist.editItem("Grapes", "Red Grapes", 7);
        
        // edit the 'Apples' item (currently in 'Items' list)
        shoppinglist.editItem("Apples", "Apples", 6);
        
        Thread.sleep(1500); // pause for visual checking
        
        // delete items from page
        shoppinglist.deleteItemFromPage("Oranges");
        shoppinglist.deleteItemFromPage(kiwis);
        
        Thread.sleep(1500); // pause for visual checking
        
        // delete the rest of the items on the page
        shoppinglist.deleteAllItemsFromThePage();
        
        // close the browser/driver
        shoppinglist.close();
    }
}
