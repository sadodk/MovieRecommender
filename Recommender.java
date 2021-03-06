import java.io.File;  					// Import the File class.
import java.io.FileNotFoundException;  	// Import this class to handle errors.
import java.util.*; 					// Import to use List, array and scanner.
import java.io.FileWriter; 				// Create constructor to BufferedWriter.
import java.io.BufferedWriter; 			// Write to textfile.

/*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Design choices: 
- Seperation of code into methods allow for easier readability and debugging of code.
- Indentation allow for easier readability of code.
- ORM layers allows for easier coding without using queries and parsing string replacements.
- Performace could be improved by sorting product list by Id's before looking up a match to viewed product. O^3 -> O^2
- Since popular products are chosed to be by user ratings the file "Users.txt" is not used.

Instructions:

- Program take userId and productId from CurrentUserSession.txt as input.
- Program outputs Top 3 popular movies back to CurrentUserSession.txt.
- Program outputs "recommended movies" based on product viewed by user to CurrentUserSession.txt.
- Output file has following output:

CurrentUserSessions.txt
-----------------------
userid, productid, popular (products seperated by ;), recommend (products seperated by ;) 



*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class Recommender {
  //main method that executes the program.
  public static void main(String[] args) {


    // Call methods to import data from CSV files into objects
    List<Product> products = importProducts();
    List<Session> sessions = importSessions();

    clearFile();
    

    // Sort product list by user rating in descending order.
    products.sort(new Comparator<Product>() {
        @Override
        public int compare(Product first, Product second)
        {
            double dif = first.getRating() - second.getRating();
            if (dif > 0) return -1;
            if (dif < 0) return 1;
            return 0;
        }
    });

    // Top 3 products with highest userratings. 
    String PopularProducts = products.get(0).getId() + ";" + products.get(1).getId() + ";" + products.get(2).getId();

    // Initialize variable.
    String viewingGenre = "none";

        // Loop through user sessions.
        for (Session Session: sessions){
            
            // Loop through products.
            for(Product Product: products) {        
                if (Product.getId() == Session.getViewingId()) {

                    // Loop through keywords
                    for(String Keyword:Product.getKeywords()) {
                        // Avoid empty keywords in genre list
                        if (!Keyword. equals(" ")){
                            viewingGenre = Keyword;
                        } 
                    }
                }
            }
            
            String RelatedProducts = "";
            // Get all ProductID's with the genre in variable viewingGenre
            for (Product Product: products) {
                for (String keyword:Product.getKeywords()) {
                    boolean areEqual = keyword.equals(viewingGenre);
                        if (areEqual){
                        String s = String.valueOf(Product.getId());
                        RelatedProducts += ";" + s;
                        }
                }
            }
            
            // Print all results to usersession.txt file
            try{
            // Create constructor to BufferWriter    
            FileWriter fw = new FileWriter("CurrentUserSession.txt",true);
            // Use constructor for new instance of BufferedWriter
            BufferedWriter writer = new BufferedWriter(fw);
            String output = Session.getUserId() + ", " + Session.getViewingId() + ", " + PopularProducts + ", " + RelatedProducts.substring(1);

            writer.write(output);
            writer.newLine();
            writer.close();
            }
            catch(Exception e){
                System.out.println(e);
            }   
        }
    }

    ///////////////////// Methods //////////////////////////////////////////////////////////////////////

    // Clear output file
    private static void clearFile() {

        try{
            FileWriter fw = new FileWriter("CurrentUserSession.txt");
            BufferedWriter writer = new BufferedWriter(fw);
            String output = "";
            writer.write(output);
            writer.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
    }

    // Import data and returns object list. (ORM layer)
    private static List<Product> importProducts() {
    // A list of type "product" named "products"
    List<Product> products = new ArrayList<>();  

    // Try and catch block.
    try {
    File myObj = new File("Products.txt");

    // New object of scanner type with all data.  
    Scanner myReader = new Scanner(myObj);

    //While loop that goes through object and print its rows. 
    while (myReader.hasNextLine()) {
        String data = myReader.nextLine();

        // Split each row in attributes.
        String[] attributes = data.split(",");

        // Create a new object for each row with attributes as input to the create method.
        Product product = createProduct(attributes);

        // Adding new product object into ArrayList
        products.add(product);
        
        //System.out.println(product.toString());
        }

        //Closes the object.
        myReader.close();

    //Error handling
    } 
    catch (FileNotFoundException e) {
    System.out.println("An error occurred.");
    e.printStackTrace();
    }

    return products;
    }

     // Import data and returns object list. (ORM layer)
     private static List<Session> importSessions() {
        // A list of type "product" named "products" with all product objects.
        List<Session> sessions = new ArrayList<>();  

        // Try and catch block.
        try {
        File myObj = new File("CurrentUserSession.txt");

        // New object of scanner type with all data.  
        Scanner myReader = new Scanner(myObj);

        //While loop that goes through object and print its rows. 
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            // Split each row in attributes.
            String[] attributes = data.split(",");

            // Create a new object for each row with attributes as input to the create method.
            Session session = createSession(attributes);

            // Adding new product object into ArrayList
            sessions.add(session);
            
            // System.out.println(user.toString());
            }

            //Closes the object.
            myReader.close();
            
        //Error handling
        } 
        catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }

        return sessions;
    }

    // Create Objects with appropriate datatype.
    private static Product createProduct(String[] metadata) {
        int id = Integer.parseInt(metadata[0]);
        String title = metadata[1];
        int year = Integer.parseInt(metadata[2]);
        String keyword1 = metadata[3];
        String keyword2 = metadata[4];
        String keyword3 = metadata[5];
        String keyword4 = metadata[6];
        String keyword5 = metadata[7];
        double rating = Double.parseDouble(metadata[8]);
        int price = Integer.parseInt(metadata[9]);

        // Create and return book of this metadata.
        return new Product(id, title, year,keyword1,keyword2,keyword3,keyword4,keyword5,rating,price);
    }

    // Create Objects with appropriate datatype.
    private static Session createSession(String[] metadata) {
        int userId = Integer.parseInt(metadata[0]);
        int viewingId = Integer.parseInt(metadata[1].trim());

        // Create and return book of this metadata.
        return new Session(userId, viewingId);
    }

}

///////////////////// Classes //////////////////////////////////////////////////////////////////////

// Class definition containing object attributes and methods
class Product {
    private int id;
    private String title;
    private int year;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String keyword4;
    private String keyword5;
    private double rating;
    private int price;

    // Method to inialise a product object.
    public Product(int id, String title, int year, String keyword1, String keyword2, String keyword3,String keyword4, String keyword5, double rating, int price) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;
        this.keyword4 = keyword4;
        this.keyword5 = keyword5;
        this.rating = rating;
        this.price = price;
    }

    // "Get methods" for product object listed below..
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }
    
    // We put all keywords into a keyword list.
    public List<String> getKeywords() {
        List<String> keywords = new ArrayList<>();
        keywords.add(keyword1);
        keywords.add(keyword2);
        keywords.add(keyword3);
        keywords.add(keyword4);
        keywords.add(keyword5);
        return keywords;
    }

    public double getRating() {
        return rating;
    }

    public int getPrice() {
        return price;
    }


    // Below method overrides the built in "toString method" when use on product objects.
    @Override 
    public String toString() {
        return "Product [id=" + id + ", title=" + title + ", year =" + year + ", Keywords =" + getKeywords() + ", rating =" + rating + ", price =" + price + "]";
    }
}

// Class definition containing object attributes and methods
class Session {
    private int userId;
    private int viewingId;

    // Method to inialise object.
    public Session(int userId, int viewingId) {
        this.userId = userId;
        this.viewingId = viewingId;

    }

    // "Get methods" listed below..
    public int getUserId() {
        return userId;
    }

    public int getViewingId() {
        return viewingId;
    }

    // Below method overrides the built in "toString method" when used on user objects.
    @Override 
    public String toString() {
        return "User [Userid=" + userId + ", viewingId=" + viewingId + "]";
    }
}