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
    private static boolean alreadyAdded(ArrayList<String> states, String state)
    {
        for(String s: states)
        {
            String x = s.split(":")[0];
            if(state.equals(x))
            {
                return true;
            }
        }
        return false;
    }
    public static ArrayList<String> StateLetterPopulation(String letter)
    {
        ArrayList<String> ret = new ArrayList<String>();
        Set<K> keySet = hash.keySet();
        for(int i = 0; i<keySet.size(); i++)
        {
            K k = keySet.get(i);
            if(k.getState().substring(0,1).equals(letter))
            {
                if(!alreadyAdded(ret, k.getState()))
                {
                   int pop = getStatePopulation(k.getState());
                    String x = k.getState() + ":" + pop;
                    ret.add(x); 
                }
            }
        }
        System.out.println(ret);
        return ret;
    }
        public static String getHighestPopulationInState (String state)
    {
        int highest = 0;
        Set<K> keySet = hash.keySet();
        for(int i = 0; i<keySet.size(); i++)
        {
            K k = keySet.get(i);
            if(k.getState().equals(state))
            {
                String city = k.getCity();
                if(getCityPopulation(city, state) > highest)
                {
                    highest = getCityPopulation(city, state);
                }
            }
        }
        return state + " Highest Population: " + highest;
    }
    public static String getLowestPopulationInState (String state)
    {
        Set<K> keySet = hash.keySet();
        int lowest = 100000000;
        for(int i = 0; i<keySet.size(); i++)
        {
            K k = keySet.get(i);
            if(k.getState().equals(state))
            {
                String city = k.getCity();
                if(getCityPopulation(city, state) < lowest)
                {
                    lowest = getCityPopulation(city, state);
                }
            }
        }
        return state + " Lowest Population: " + lowest;
    }
    public static String getMeanPopulationOfState (String state)
    {
        int sum = 0;
        int count = 0;
        Set<K> keySet = hash.keySet();
        for(int i = 0; i<keySet.size(); i++)
        {
            K k = keySet.get(i);
            if(k.getState().equals(state))
            {
                String city = k.getCity();
                sum += getCityPopulation(city, state);
                count++;
            }
        }
        return state + " Mean: " + sum/count;
    }
    public static String getMedianPopulationOfState (String state)
    {
        //Sort list! Otherwise values won't be accurate
        int median = 0;
        Set<K> keySet = hash.keySet();
        ArrayList<Integer> forward = new ArrayList<Integer>();
        ArrayList<Integer> backward = new ArrayList<Integer>();
        for(int i = 0; i<keySet.size(); i++)
        {
            K k = keySet.get(i);
            if(k.getState().equals(state))
            {
                String city = k.getCity();
                forward.add(0, getCityPopulation(city, state));
                backward.add(getCityPopulation(city, state));
            }
        }
        for(int i = 0; i<forward.size(); i++)
        {
            if(forward.get(i).equals(backward.get(i)))
            {
                median = forward.get(i);
            }
        }
        for(int i = 0; i<forward.size()-1; i++)
        {
            int sum = forward.get(i)+forward.get(i+1);
            int add = backward.get(i)+backward.get(i+1);
            if(sum == add)
            {
                median = sum/2;
            }
        }
        return state + " Median: " + median;
    }
    public static ArrayList<Integer> getRangeOfCityPopulationsInState (int max, int min, String state)
    {
        //Something is wrong, look back at this later
        Set<K> keySet = hash.keySet();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i<keySet.size(); i++)
        {
            K k = keySet.get(i);
            if(k.getState().equals(state))
            {
                String city = k.getCity();
                if(getCityPopulation(city, state) < max && getCityPopulation(city, state) > min)
                {
                    list.add(getCityPopulation(city, state));
                }
            }
        }
        return list;
    }
}
