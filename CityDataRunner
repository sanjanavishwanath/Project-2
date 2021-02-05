import java.util.Scanner;
import java.io.*;

public class CityDataRunner
{
    public static void main( String args[] ) 
    {
        String data = "";
        try
        {
            // Open a Stream to a file you can read from.  The file name is "primes.dat".
            File file = new File("CityData.dat");
            Scanner s = new Scanner(file);
            // Loop through each line of the file
            while(s.hasNextLine())
            {
                // Read each line and append it to a String
                String line = s.nextLine();
                data += line + "\n";
            }
            // 
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        try
        {
            // THIS IS WHERE YOU OPEN THE STREAM TO WRITE TO THE FILE
            // When you write to the file, you will over-write what was there
            // (i.e. make a new copy)
            // In the FileWriter code you write, the second parameter is false, not true.
            // Set it to false to overwrite
            
            // Open a Stream to a file that you can write to
            // FileWriter code: 
            FileWriter fw = new FileWriter("output.dat", false);
            // BufferedWriter code:
            BufferedWriter bw = new BufferedWriter(fw);
            // PrintWriter code:
            PrintWriter out = new PrintWriter(bw, true);
            
            // Create an array called numbers by splitting data with the .split() method
            String[] numbers = data.split("\n");
            // For each string in the array...
            for(String n : numbers)
            {
                // Parse the string into an int
                int i = Integer.parseInt(n);
                // Create a Prime object and pass that int as a parameter into the Prime class
                Prime p = new Prime(i);
                // Use the .toString() method and write to the file
                out.print(p.toString());
            }
            // Close the stream
            out.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        
    }
}
