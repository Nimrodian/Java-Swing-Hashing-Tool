//Importing relevant Swing packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//Importing relevant Hashing packages
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
public class NewHashingProgram { //Creation of Main class, that also instantiates the instance of the hash panel.
public static void main(String[] args) //main method
 {
 JFrame main_frame = new JFrame("File Explorer"); //Creating the main frame that is the basis of the GUI
 main_frame.setSize(300, 130); //setting the size of frame 
 main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Setting close operation to exist on close (terminate program)
 main_frame.add(new HashingPanel()); //instantiating the instance of the hashing panel
 main_frame.setVisible(true); //making the main frame visible
 }
}
class HashingPanel extends JPanel implements ActionListener //Class that is the main bulk of the code, and holds the instance of hash panel that is made
{
//Below code is creating the extra buttons and text fields that are contained within the main frame
 JLabel introduction;
 JTextField FileDir;
 JButton button;
 JButton button2;
 JButton button3;
 JLabel Label;
 JLabel Output;
 HashingPanel()
 {
 //Below code is then defining the parameters of the buttons/text fields that were just made.
 //Also adding an ActionListener to the buttons, so we are able to
 //when one of them is pressed.
 introduction = new JLabel("Please enter the file name you want to hash: ");
 FileDir = new JTextField(20);
 Label = new JLabel();
 button = new JButton("Text Hash");
 button2 = new JButton("Image Hash");
 button3 = new JButton("Quit");
 button.addActionListener((ActionListener)this);
 button2.addActionListener((ActionListener)this);
 button3.addActionListener((ActionListener)this);
 Output = new JLabel("");
 add(introduction);
 add(FileDir);
 add(Label);
 add(button);
 add(button2);
 add(button3);
 add(Output);
 System.out.println("Hashing Tool Program\nConsole output:");
 }
public void actionPerformed(ActionEvent e) //Method to deal with a button being pressed (with parameter action event)
 {
 if(e.getSource()==button) //First if statement for the event of the first button being pressed (Text file hash)
 {
 String choice;
 try //try and exception to catch possible errors listed below under the catch statement.
 { 
 choice = Hash.HashFile(FileDir);//Makes the variable choice equal to whatever the user enters in the text field, from this

//it will then call the hash class, and the method "Hash image" within it. This method will
//return the hash value of the image file the user has entered

 System.out.println("\nThe MD5 hash of the text file you have selected is: "); //Printing what they have entered
 System.out.println(choice);

 String file_name = "listofhashes.txt"; //Creating "file_name" variable to store the address of the list of existing hashes
 Scanner final_array = new Scanner(new File(file_name));
//Creates a new scanner that reads the text file storing the list of hashes
 List<String> hashed_lines = new ArrayList<String>();
//Creates an array list to be appended to with the lines in the hash file
 while(final_array.hasNextLine()) //while loop, continues running as long as there is another line to run in the text file
 {
 hashed_lines.add(final_array.next()); //For each line in the hash file, append that line to the array that was created previously
 }
 final_array.close(); //close the array instance
 System.out.println("\n"+hashed_lines); //outputs the contents of that array to the console

 if(hashed_lines.contains(choice)) //If statement to decide what to do if the users hashed file is stored in the array that was just made
 {
 System.out.println("\nThe file you entered to hash has been matched to an existing hash in our system!"); //Displayed if it is a match
 }
 else //else statement for if the file that has been hashed is not present in the array "hashed_lines"
 {
 System.out.println("\nThe file you entered to hash has not been matched to an existing hash in our system"); //Displayed if its not a match
 System.out.println("It will now be appended to our current list, to increase the accuracy of our system. Thanks!");
 append.file_append(choice); //Calling the method inside the "append" class, that will then add the new hash value (that was not a match) into the hash storage file, to increase the accuracy of this system
 }
 }
 catch (IOException | NoSuchAlgorithmException e1) //Required catch clause for if there is no such algorithm found
 {
 e1.printStackTrace();
 }
 }
 else if(e.getSource()==button2) //Second if statement for the event of the second button being pressed (Image file hash)
 {
 String choice;
 try //try and exception to catch possible errors listed below under the catch statement
 {
 choice = Hash.HashImage(FileDir); //Makes the variable choice equal to whatever the user enters in the text field, from this

//it will then call the hash class, and the method "Hash image" within it. This method will
//return the hash value of the image file the user has entered
 System.out.println("\nThe MD5 hash of the image file you have selected is: "); //Printing what they have entered
 System.out.println(choice);

 String file_name = "listofhashes.txt"; //Creating "file_name" variable to store the address of the list of existing hashes
 Scanner final_array = new Scanner(new File(file_name));
//Creates a new scanner that reads the text file storing the list of hashes
 List<String> hashed_lines = new ArrayList<String>();
//Creates an array list to be appended to with the lines in the hash file
 while(final_array.hasNextLine()) //while loop, continues running as long as there is another line to run in the text file
 {
 hashed_lines.add(final_array.next()); //For each line in the hash file, append that line to the array that was created previously
 }
 final_array.close(); //close the array instance
 System.out.println("\n"+hashed_lines); //outputs the contents of that array to the console

 if(hashed_lines.contains(choice)) //If statement to decide what to do if the users hashed file is stored in the array that was just made
 {
 System.out.println("\nThe file you entered to hash has been matched to an existing hash in our system!"); //Displayed if it is a match
 }
 else //else statement for if the file that has been hashed is not present in the array "hashed_lines"
 {
 System.out.println("\nThe file you entered to hash has not been matched to an existing hash in our system"); //Displayed if its not a match
 System.out.println("It will now be appended to our current list, to increase the accuracy of our system. Thanks!");
 append.file_append(choice);//Calling the method inside the "append" class, that will then add the new hash value (that was not a match)
 //into the hash storage file, to increase the accuracy of this system
 }
 }
 catch (NoSuchAlgorithmException | IOException e1) //Required catch clause for if there is no such algorithm found
 {
 e1.printStackTrace(); 
 }
 }
 else if(e.getSource()==button3) //Third if statement that will run if the third button is pressed (QUIT)
 {
 Hash.Quit(); //Runs the method "Quit" inside the class "Hash". this will terminate the program properly.
 }
 }
}
class Hash //Hashing class that actually performs the hashing. The methods in this class are called in the if statements in the Hash panel class
{
public static String HashFile(JTextField FileDir) throws IOException,
NoSuchAlgorithmException //Method that will return the hexString of the hash created

//from the text file the user has entered.
 {
 String file_name = FileDir.getText(); //Creates the variable "file_name" to hold the file name of the file to be hashed
 MessageDigest hash_code = MessageDigest.getInstance("MD5");
//Creates an instance of messageDigest in MD5. Used to hash the upcoming files
 String currentdirectory = Paths.get(".").toAbsolutePath().normalize().toString(); //Creates a string to store the file path.
 String filepath;
 filepath= currentdirectory+"\\"+file_name; //creates a string to concat the file path and the file name
 System.out.println("\nfile path is: "+filepath); //prints the file along with its filepath
 @SuppressWarnings("resource")
 FileInputStream fis = new FileInputStream(filepath); //Creates a new instance of a file input stream to read the data in the file
 byte[] dataBytes = new byte[1024]; //creates an array "dataBytes" to store the byte data in the file
 int nread = 0;
 while ((nread = fis.read(dataBytes)) != -1) //A while loop to update the message digest with the byte data from each byte. Closing once data has ran out
 {
 hash_code.update(dataBytes, 0, nread);
 }

 byte byteData[] = hash_code.digest(); //Digests the instance of MessageDigest and appends it to a new array called "byteData"
 StringBuffer hexString = new StringBuffer(); //Creates a new string buffer called "hexString" that will store the hexData of the contents of the file
 for (int i=0;i<byteData.length;i++) //For loops that will repeat for how long the byteData array is
 {
 String hex=Integer.toHexString( byteData[i] & 0xff ); //takes each byte in the array and converts it to a string
 if(hex.length()==1) hexString.append('0'); //Appends each string made to the variables "hexString" until there is a complete hash done.
 hexString.append(hex);
 } 
 return(hexString.toString()); //Returns the final hexString, ready to be displayed to the user.
 }
public static String HashImage(JTextField FileDir) throws IOException,
NoSuchAlgorithmException //Method that will return the hexString of the hash created

//from the image file the user has entered.
 {
 String file_name = FileDir.getText(); //Creates the variable "file_name" to hold the file name of the file to be hashed
 File input_image = new File(file_name); //Creates a new variable to store the file name that the user has entered.
 BufferedImage buffImg = ImageIO.read(input_image); //Creates an instance of BufferedImage that will read the input file
 ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); //Creates and instance of an outputstream to convert the image file to bytes
 ImageIO.write(buffImg, "png", outputStream);
 byte[] data = outputStream.toByteArray(); //Creates a new array to hold the bytes

 String currentdirectory =
Paths.get(".").toAbsolutePath().normalize().toString(); //Creates a string to store the file path.
 String filepath;
 filepath= currentdirectory+"\\"+file_name; //Concats the filepath with the filename
 System.out.println("\nfile path is: "+filepath); // displays the file path

 MessageDigest md = MessageDigest.getInstance("MD5"); //Creates an instance of messageDigest in MD5. Used to hash the upcoming files
 md.update(data); //updates the message digest instance with the array holding the bytes of data that was made previously
 byte[] hash = md.digest(); //creates a new array to story the byte data so that this can then be hashed..

 String hexString = null; //Creates the string hexString to store the final hashed version of the image, so it can then be returned to where it was called
 for(int i=0; i < hash.length; i++) //for loop to run through each byte in the array and produce a substring of it, apply it to "hexString"
 //to then be returned
 {
 hexString +=
 Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1);
 }
 return (hexString.toString()); //return the final hashed string "hexString" to then be displayed to the user
 }
public static void Quit() //Method inside the hashing class that will terminate the program.
 {
 System.out.println("\nYou have quit the Hashing Tool");
 System.exit(0);
 }
} 
class append //Append class that is ran once a hash is complete, if a match is not found, it will call the "file_append" method inside this class
 //to then append the new hash to the file of existing hashes
{
public static void file_append(String choice) throws IOException //Method to write a new line to the existing hash file. Parameter choice (the new hash)
 {
 BufferedWriter fileWriter = new BufferedWriter(new
FileWriter("listofhashes.txt", true)); //creates a buffered writer instance to write the hash to the file
 fileWriter.newLine(); //Appends a new blank line to the file so each hash is on its own line, making it possible to pull it into an arraylist
 fileWriter.write(choice); //Appends the new hash
 fileWriter.close(); //closes the instance of filewriter, to prevent a resource leak
 }
}
