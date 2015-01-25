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
		
		// the enterQuantity input has been defaulted to a value of 1, clear it first
		this.enterQuantity.clear();
		this.enterQuantity.sendKeys(Integer.toString(itemQuantity));
		this.addItem.click();
	}
	
	/**
	 * Delete item from the shopping list application.
	 * @param itemToDelete
	 */
	public void deleteItemFromPage(String itemToDelete) {
	    WebElement deletableItem = findItemOnPage(itemToDelete);
	    if (deletableItem != null) {
	        deletableItem.findElement(By.cssSelector(".btn-delete")).click();
	    }
	    // TODO: implement handler for when the item isn't found on the page.
	    else {
	        
	    }
		
	}
	
	/**
	 * Public access to all of the items on the current page.
	 * @return list of WebElements found on the page when function was called
	 */
	public List<WebElement> getAllItemsOnPage() {
	    return this.driver.findElements(By.className("item"));
	}
	
	/**
	 * Get a reference to an item on the page.
	 * @param itemToFind text value of the item to find 
	 * @return the WebElement if found, otherwise return null
	 */
	public WebElement findItemOnPage(String itemToFind) {
	    List<WebElement> itemsOnPage = getAllItemsOnPage();
	    for (WebElement item : itemsOnPage) {
	        if (item.findElement(By.cssSelector("input[type='text']")).getAttribute("value").equals(itemToFind)) {
	            return item;
	        }
	    }
	    
	    return null; // Item was not found on the page
	}
}
