
/**
 * Write a description of class Inventory here.
 *
 * @author JOrdan Breen
 * @version 02/09/2020
 */
public class Inventory
{
    //The maximum number of unique books that can be stored.
    public static final int MAX_BOOKS = 5;
    
    // the books being stored private
    private Book[] inventory;
    
    public Inventory()
    {
        inventory = new Book[MAX_BOOKS];
    }
    
    public boolean haveCopy(String inTitle)
    {
        for (int i = 0; i < inventory.length; i++)
        {
            if(inventory[i] != null && (inventory[i].getTitle()).equals(inTitle) && inventory[i].getNumInStock() > 0)
            return true;
        }
        return false;
    }
    
    public boolean addBook(String inTitle)
    {
        for (int i = 0; i < inventory.length; i++)
        {
            if(inventory[i] == null)
            {
                inventory[i] = new Book(inTitle, 0);
                return true;
            }
        }
        return false;
    }
    
    public boolean addBook(String inTitle, int inNum)
    {
        for (int i = 0; i < inventory.length; i++)
        {
            if(inventory[i] == null)
            {
                inventory[i] = new Book(inTitle, inNum);
                return true;
            }
        }
        return false;
    }
    
    public boolean removeBook(String inTitle)
    {
        for (int i = 0; i < inventory.length; i++)
        {
            if(inventory[i] != null && (inventory[i].getTitle()).equals(inTitle))
            {
                inventory[i] = null;
                return true;
            }
        }
        return false;
    }
    
    public int newShipment(String inTitle, int inOrder)
    {
        for (int i = 0; i < inventory.length; i++)
        {
            if(inventory[i] != null && (inventory[i].getTitle()).equals(inTitle))
            {
                inventory[i].addCopies(inOrder);
                return inventory[i].getNumInStock();
            }
            else
            {
                if(addBook(inTitle, inOrder))
                return inOrder;
            }
        }
        return 0;
    }
    
    public String getUnderstockedBooks(int inThreshold)
    {
        Book[] understockedBooks = new Book[MAX_BOOKS]; 
        int counter = 0;
        String outputString = "";
        
        for (int i = 0; i < inventory.length; i++)
        {
            if(inventory[i] != null && inventory[i].getNumInStock() < inThreshold)
            {
                understockedBooks[counter] = new Book(inventory[i].getTitle(), inventory[i].getNumInStock());
                counter++;
            }
        }
        for (int i = 0; i < counter; i++)
        {
            outputString += (understockedBooks[i].getTitle() + ": " + understockedBooks[i].getNumInStock());
            if(i < counter - 1)
            outputString += (", ");
        }
        return outputString;
    }
    
    public boolean sellCopy(String inTitle)
    {
        for (int i = 0; i < inventory.length; i++)
        {
            if(inventory[i] != null && (inventory[i].getTitle()).equals(inTitle))
            {
                inventory[i].sellCopy();
                return true;
            }
        }
        return false;
    }
    
    
    
    static void main ()
    {
        Inventory inventory = new Inventory(); 
        System.out.println("Have \"Conditionals Are Fun\"?: " + inventory.haveCopy("Conditionals Are Fun"));
       
        inventory.addBook("Conditionals Are Fun", 10); 
        inventory.addBook("A History of Siena College", 5); 
        inventory.addBook("Arrays for Everyone", 0); 
        inventory.addBook("Java Heroes", 4); 
        
        System.out.println("Have \"Conditionals Are Fun\"?: " + inventory.haveCopy("Conditionals Are Fun"));
        System.out.println("Have \"Arrays for Everyone\"?: " + inventory.haveCopy("Arrays for Everyone"));
        System.out.println("3 or fewer copies: " + inventory.getUnderstockedBooks(4));
        
        inventory.sellCopy("Java Heroes"); 
        System.out.println("3 or fewer copies: " + inventory.getUnderstockedBooks(4));
        
        int newCount = inventory.newShipment("Big Book of Computing", 10);
        
        System.out.println("Now have " + newCount + " copies of \"Big Book of Computing\".");
        newCount = inventory.newShipment("Little Book of Computing", 10);
        System.out.println("Now have " + newCount + " copies of \"Little Book of Computing\".");
    }
}
