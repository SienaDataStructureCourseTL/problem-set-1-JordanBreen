
/**
 * A class to encapsulate information about a book in a store
 * inventory: its title and the number of copies in stock.
 *
 * DO NOT EDIT THIS CLASS
 *
 * @author Jim Teresco
 * @version Fall 2019
 */
public class Book
{
    private String title;
    private int numInStock;
    
    /**
     * Construct a book with the given title and a default
     * inventory of 0.
     * 
     * @param title The title of the book.
     */
    public Book(String title) {
        
        this.title = title;
        this.numInStock = 0;
    }
    
    /**
     * Construct a book with the given title and initial 
     * inventory.  The initial inventory must be in the range [0,100]
     * 
     * @param title The title of the book.
     * @param initialInventory The initial number of copies of the book in stock, which
     * must be in the range [0,100].  If not, a default of 0 is used and an error message
     * is printed.
     * 
     */
    public Book(String title, int initialInventory) {
        
        this.title = title;
        if (initialInventory < 0 || initialInventory > 100) {
            numInStock = 0;
            System.out.println("Invalid initial inventory " + initialInventory +
            ", setting to 0");
        }
        else {
            numInStock = initialInventory;
        }
    }
     
    /**
     * Return the title of the book.
     * 
     * @return The title of the book.
     * 
     */
    public String getTitle() {
        
        return title;
    }
    
    /**
     * Return the current number of copies of the book in stock.
     * 
     * @return The current number of copies of the book in stock.
     * 
     */
    public int getNumInStock() {
        
        return numInStock;
    }
    
    /**
     * Sell a copy of the book, reducing the number of copies in stock by 1.
     * Prints an error message if no copies are in stock when called.
     * 
     */
    public void sellCopy() {
       
        if (numInStock > 0) {
            numInStock--;
        }
        else {
            System.out.println("Cannot sell copy, none in stock!");
        }
    }
    
    /**
     * Add a shipment of a title to the inventory for the book.  The number of
     * copies received must be positive and cannot exceed 100.
     * 
     * @param numCopies The number of copies to add to inventory, must be [1,100].  If not,
     * the inventory is not changed and an error is printed.
     */
    public void addCopies(int numCopies) {
        
        if (numCopies < 1 || numCopies > 100) {
            System.out.println("Invalid number of copies " + numCopies);
        }
        else {
            numInStock += numCopies;
        }
    }
    
    /**
     * Return a String representing the book: it's title and current stock.
     * 
     * @return a String representing the book: it's title and current stock.
     */
    public String toString() {
        
        return title + ": " + numInStock;
    }
}
