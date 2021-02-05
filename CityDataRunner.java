import java.util.Scanner;
import java.io.*;
import java.util.*;
public class CityDataRunner
{
    private static HashMap hash;
    public static void main( String args[] ) 
    {
        hash = new HashMap();
        try
        {
            // Open a Stream to a file you can read from.  The file name is "primes.dat".
            File file = new File("CityData.txt");
            Scanner s = new Scanner(file);
            // Loop through each line of the file
            while(s.hasNextLine())
            {
                // Read each line and append it to a String
                String line = s.nextLine();
                K key = new K(line.split("\t")[0]);
                hash.put(key, Integer.parseInt(line.split("\t")[1]));
            }
            s.close();
            //System.out.println(getCityPopulation("Austin", "TX"));
            //System.out.println(getStatePopulation("TX"));
            // 
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static int getCityPopulation(String city, String state)
    {
        K k = new K(city, state);
        return (int)hash.get(k);
    }
    public static int getStatePopulation(String state)
    {
        int population = 0;
        Set<K> keySet = hash.keySet();
        for(int i = 0; i<keySet.size(); i++)
        {
            K k = keySet.get(i);
            if(k.getState().equals(state))
            {
                population += (int)hash.get(k);
            }
        }
        return population;
    }
    public static String getHighestCityPopulation()
    {
        int high = 0;
        String ret = "";
        Set<K> keySet = hash.keySet();
        for(int i = 0; i<keySet.size(); i++)
        {
            K k = keySet.get(i);
            int population = (int)hash.get(k);
            if(i==0)
            {
                 high = population;
                 ret = k.getCity();
            }
            if(high<population)
            {
                high = population;
                ret = k.getCity();
            }
        }
        return ret + ": " + high;
    }
    public static String getHighestCityStatePopulation(String state)
    {
        int high = 0;
        String ret = "";
        Set<K> keySet = hash.keySet();
        for(int i = 0; i<keySet.size(); i++)
        {
            K k = keySet.get(i);
            if(k.getState().equals(state))
            {
                int population = (int)hash.get(k);
                if(i==0)
                {
                     high = population;
                     ret = k.getCity();
                }
                if(high<population)
                {
                    high = population;
                    ret = k.getCity();
                }
            }
        }
        return ret + ": " + high;
    }
    public static ArrayList<String> CityLetterPopulation(String letter)
    {
        ArrayList<String> ret = new ArrayList<String>();
        Set<K> keySet = hash.keySet();
        for(int i = 0; i<keySet.size(); i++)
        {
            K k = keySet.get(i);
            if(k.getCity().substring(0,1).equals(letter))
            {
                int pop = (int)hash.get(k);
                String x = k.getCity() + ", " + k.getState() + ": " + pop;
                ret.add(x);
            }
        }
        System.out.println(ret);
        return ret;
    }
}
