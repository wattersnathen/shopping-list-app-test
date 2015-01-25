package ShoppingList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Test;

import static org.junit.Assert.*;
import ShoppingList.ShoppingListPage;

public class ShoppingListPageTest {
	
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        ShoppingListPage shoppinglist = new ShoppingListPage(driver);
        shoppinglist.load();
        shoppinglist.addItemToList("Apples", 2);
        shoppinglist.addItemToList("Oranges", 1);
        shoppinglist.addItemToList("Grapes", 1);
        shoppinglist.addItemToList("Watermelon", 3);
        shoppinglist.addItemToList("Cat Food", 1);
        
        WebElement kiwis = shoppinglist.addItemToList("Kiwis", 2);
        Thread.sleep(2500);
        
        shoppinglist.deleteItemFromPage("Oranges");
        shoppinglist.deleteItemFromPage(kiwis);
        Thread.sleep(5000);
        		
        shoppinglist.close();
    }
}
