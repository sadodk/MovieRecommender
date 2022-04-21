import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.List; // Import to use list
import java.util.ArrayList; // Import to use arrays


public class ReadFile {
  //The main method that executes the program
  public static void main(String[] args) {
        
        // A list with all book objects
        List<Book> books = new ArrayList<>();  
        
        // Try and catch block
        try {
        File myObj = new File("books.txt");
        
        // New object of scanner type with all data  
        Scanner myReader = new Scanner(myObj);
        
        //While loop that goes through object and print its rows  
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            // Split each row in attributes
            String[] attributes = data.split(",");

            // Create a new object for each row with attributes as input to the createBook method
            Book book = createBook(attributes);

            // adding book into ArrayList
            books.add(book);
            
            System.out.println(book.toString());
            //System.out.println(data);
            //System.out.println(attributes[0]);
            }
        
            //Closes the object
            myReader.close();
            
        //Error handling
        } 
        catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }

    }

    // This method "createProduct" create Product objects
    private static Book createBook(String[] metadata) {
        String name = metadata[0];
        int price = Integer.parseInt(metadata[1]);
        String author = metadata[2];
        // Create and return book of this metadata
        return new Book(name, price, author);
    }





}


class Book {
    private String name;
    private int price;
    private String author;


    // Product method in product class
    public Book(String name, int price, String author) {
        this.name = name;
        this.price = price;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book [name=" + name + ", price=" + price + ", author =" + author + "]";
    }
}