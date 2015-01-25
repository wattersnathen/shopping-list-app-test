package ShoppingList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingListPage {
	
	// -----------------------------------
	// PageFactory WebElements, use @CacheLookup for static WebElements
	// ----------------------------------- 
	@FindBy(id="entered-item-text")
	@CacheLookup
	private WebElement enterItem;
	
	@FindBy(id="entered-item-quantity")
	@CacheLookup
	private WebElement enterQuantity;
	
	@FindBy(id="add-button")
	@CacheLookup
	private WebElement addItem;
	
	@FindBy(id="clear-options")
	@CacheLookup
	private WebElement clearLists;
	
	@FindBy(id="save-options")
	@CacheLookup
	private WebElement saveLists;
	
	@FindBy(id="items-to-purchase")
	private WebElement itemsList;
	
	@FindBy(id="items-purchased")
	private WebElement purchasedList;
	 
	private String url = "http://wattersnathen.github.io/shopping-list-app/";
	private WebDriver driver;
	
	/**
	 * ShoppingListPage constructor. Calls initElements
	 * from the PageFactory class.
	 * @param driver
	 */
	public ShoppingListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	/**
	 * Load this page. Calls the WebDriver.get(URL) method.
	 */
	public void load() {
		this.driver.get(url);
	}
	
	/**
	 * Close this page. Calls the WebDriver.close() method.
	 */
	public void close() {
		this.driver.close();
	}
	
	/**
	 * Add an item to the items list.
	 * @param itemToAdd 
	 * @param itemQuantity
	 */
	public void addItemToList(String itemToAdd, int itemQuantity) {
		this.enterItem.sendKeys(itemToAdd);
		this.enterQuantity.clear(); // the enterQuantity input has been defaulted to a value of 1, clear it first
		this.enterQuantity.sendKeys(Integer.toString(itemQuantity));
		this.addItem.click();
	}
	
	/**
	 * Delete item from the shopping list application.
	 * @param itemToDelete
	 */
	public void deleteItemFromPage(String itemToDelete) {
		
		// grab all items on the current page
		List<WebElement> itemsOnPage = this.driver.findElements(By.className("item"));
		for (WebElement item : itemsOnPage) {
			
			// our item text is found in the value attribute of the text input field
			if (item.findElement(By.cssSelector("input[type='text']")).getAttribute("value").equals(itemToDelete)) {
				
				// found the itemtoDelete on the page, find and click its delete button
				item.findElement(By.cssSelector(".btn-delete")).click();
				
				// no need to continue searching through the items
				break;
			}
		}

	}
	
	
}
