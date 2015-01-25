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
     * Public access to all of the items on the current page.
     * @return list of WebElements found on the page when function was called
     */
    public List<WebElement> getAllItemsOnPage() {
        return this.driver.findElements(By.className("item"));
    }
        
    /**
     * Get a reference to an item on the page if it exists.
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
    
    /**
     * Add an item to the items list.
     * @param itemToAdd 
     * @param itemQuantity
     * @return WebElement that was added
     */
    public WebElement addItemToList(String itemToAdd, int itemQuantity) {
        this.enterItem.sendKeys(itemToAdd);
        // the enterQuantity input has been defaulted to a value of 1, clear it first
        this.enterQuantity.clear();
    
        // itemQuantity field must be 1 or greater. Default to 1 if value less than 1 is issued.
        if (itemQuantity < 1) {
            this.enterQuantity.sendKeys("1");
        }
        this.enterQuantity.sendKeys(Integer.toString(itemQuantity));
        this.addItem.click();
        
        return findItemOnPage(itemToAdd);
    }
    
    /**
     * Click the checkbox on the item
     * @param itemToClick
     */
    public void clickItemCheckbox(String itemToClick) {
        WebElement item = findItemOnPage(itemToClick);
        item.findElement(By.cssSelector("input[type='checkbox']")).click();
    }
    
    /**
     * Use a WebElement reference to click the checkbox
     * @param item
     */
    public void clickItemCheckbox(WebElement item) {
        item.findElement(By.cssSelector("input[type='checkbox']")).click();
    }
    
    /**
     * Edit an item that is currently on the page
     * @param itemToEdit
     * @param updatedText
     * @param updatedQty
     * @return
     */
    public WebElement editItem(String itemToEdit, String updatedText, int updatedQty) {
        WebElement item = toggleItemEditable(itemToEdit); // must make it editable first
        WebElement text = item.findElement(By.cssSelector("input[type='text']"));
        WebElement qty  = item.findElement(By.cssSelector("input[type='number']"));
        text.clear();
        text.sendKeys(updatedText);
        qty.clear();
        qty.sendKeys(Integer.toString(updatedQty));
        return toggleItemEditable(item);
    }
    
    /**
     * Click the 'Edit' input button
     * @param itemToEdit
     * @return
     */
    private WebElement toggleItemEditable(String itemToEdit) {
        WebElement item = findItemOnPage(itemToEdit);
        item.findElement(By.cssSelector(".btn-edit")).click();
        return item;
    }
    
    /**
     * Click the 'Edit' input button
     * @param item
     * @return
     */
    private WebElement toggleItemEditable(WebElement item) {
        item.findElement(By.cssSelector(".btn-edit")).click();
        return item;
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
    }
    
    /**
     * Delete an item from the page
     * @param elementToDelete WebElement reference
     */
    public void deleteItemFromPage(WebElement elementToDelete) {
        elementToDelete.findElement(By.cssSelector(".btn-delete")).click();
    }
    
    /**
     * Click the 'Clear List(s)' button to remove all items from the page.
     * NOTE: Also destroys the localStorage API data -- which is a feature of the site itself.
     */
    public void deleteAllItemsFromThePage() {
        this.clearLists.click();
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
}