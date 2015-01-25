package ShoppingList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Test;

import static org.junit.Assert.*;
import ShoppingList.ShoppingListPage;

public class ShoppingListPageTest {

	
	public static void main(String[] args) throws InterruptedException {
		ShoppingListPage shoppinglist = new ShoppingListPage(new FirefoxDriver());
		shoppinglist.load();
		shoppinglist.addItemToList("Apples", 2);
		shoppinglist.addItemToList("Oranges", 1);
		shoppinglist.addItemToList("Grapes", 1);
		shoppinglist.addItemToList("Kiwis", 2);
		Thread.sleep(2500);
		
		shoppinglist.deleteAllItemsFromThePage();
		Thread.sleep(5000);
		
		shoppinglist.close();
	}
}
