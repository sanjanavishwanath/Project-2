import java.util.Scanner;
import java.io.*;
import java.util.*;
public class CityDataRunner
{
    private static HashMap hash;
    private static PrintWriter out;
    public static void main( String args[] ) 
    {
        hash = new HashMap();
        try
        {
            File file = new File("CityData.txt");
            Scanner s = new Scanner(file);
            // Loop through each line of the file
            FileWriter fw = new FileWriter("output.dat", false);
            BufferedWriter bw = new BufferedWriter(fw);
            out = new PrintWriter(bw, true);
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                K key = new K(line.split("\t")[0]);
                hash.put(key, Integer.parseInt(line.split("\t")[1]));
            }
            s.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        while(true)
        {
           userInput(); 
        }
    }
    public static void userInput()
    {
        System.out.println("Which of the following would you like to do?");
        System.out.println("1. Get the population of a city.");
        System.out.println("2. Get the population of a state.");
        System.out.println("3. Get the highest city population overall.");
        System.out.println("4. Get the highest city population in a state.");
        System.out.println("5. Get a list of populations based on the first letter of the place.");
        System.out.println("6. Get the highest/lowest/mean population of a city in a state.");
        System.out.println("7. Get all the cities within a given range of populations.");
        System.out.println("8. EXIT");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your option:");
        String option = input.nextLine();
        int optionNumber = Integer.parseInt(option);
        if(optionNumber==1)
        {
            System.out.println("Enter city name: "); 
            String city = input.nextLine();
            System.out.println("Enter state abbreviation: "); 
            String state = input.nextLine();
            String ret = "Population of " + city + ", " + state + ": " + getCityPopulation(city, state);
            out.println(ret + "\n");
            System.out.println(ret);
        }
        else if(optionNumber==2)
        {
            System.out.println("Enter state abbreviation: "); 
            String state = input.nextLine();
            String ret = "Population of " + state + ": " + getStatePopulation(state);
            out.println(ret + "\n");
            System.out.println(ret);
        }
        else if(optionNumber==3)
        {
            String ret = "Highest city population is "+ getHighestCityPopulation();
            out.println(ret + "\n");
            System.out.println(ret);
        }
        else if(optionNumber==4)
        {
            System.out.println("Enter state abbreviation: "); 
            String state = input.nextLine();
            String ret = "Highest city population in " + state + " is " + getHighestCityStatePopulation(state);
            out.println(ret + "\n");
            System.out.println(ret);
        }
        else if(optionNumber==5)
        {
            System.out.println("Would you like the city or state populations based on its first letter? Type 1 for city and 2 for state.");
            String ans = input.nextLine();
            int opt = Integer.parseInt(ans);
            if(opt == 1)
            {
                System.out.println("Enter the letter: "); 
                String city = input.nextLine();
                String ret = "Populations of cities beginning with " + city + ": " + CityLetterPopulation(city);
                out.println(ret + "\n");
                System.out.println(ret);
            }
            else if(opt == 2)
            {
                System.out.println("Enter the letter: "); 
                String city = input.nextLine();
                String ret = "Populations of states beginning with " + city + ": " + StateLetterPopulation(city);
                out.println(ret + "\n");
                System.out.println(ret);
            }
        }
        else if(optionNumber==6)
        {
            System.out.println("Enter the state abbreviation: "); 
            String state = input.nextLine();
            System.out.println("Would you like the 1. highest, 2. lowest, 3. mean population of a city in a state? Type the corresponding number."); 
            String ans = input.nextLine();
            int opt = Integer.parseInt(ans);
            if(opt == 1)
            {
                String ret = "Highest population in " + state+ ": " + getHighestPopulationInState(state);
                out.println(ret + "\n");
                System.out.println(ret);
            }
            else if(opt == 2)
            {
                String ret = "Lowest population in " + state+ ": " + getLowestPopulationInState(state);
                out.println(ret + "\n");
                System.out.println(ret);
            }
            else if(opt == 3)
            {
                String ret = "Mean population of " + state+ ": " + getMeanPopulationOfState(state);
                out.println(ret + "\n");
                System.out.println(ret);
            }
        }
        else if(optionNumber==7)
        {
            System.out.println("Enter the lower limit: "); 
            String l = input.nextLine();
            int low = Integer.parseInt(l);
            System.out.println("Enter the upper limit: "); 
            String h = input.nextLine();
            int high = Integer.parseInt(h);
            String ret = "Cities with populations between " + low + " and " +high+ ": " + getRangeOfCityPopulations(high,low);
            out.println(ret + "\n");
            System.out.println(ret);
        }
        else if(optionNumber==8)
        {
            out.flush();
            out.close();
            System.exit(0);
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
        return ret;
    }
    public static String getHighestPopulationInState(String state)
    {
        int highest = 0;
        Set<K> keySet = hash.keySet();
        String highestCity = "";
        for(int i = 0; i<keySet.size(); i++)
        {
            K k = keySet.get(i);
            if(k.getState().equals(state))
            {
                String city = k.getCity();
                if(getCityPopulation(city, state) > highest)
                {
                    highest = getCityPopulation(city, state);
                    highestCity = city;
                }
            }
        }
        return highestCity + ": " + highest;
    }
    public static String getLowestPopulationInState(String state)
    {
        Set<K> keySet = hash.keySet();
        int lowest = 100000000;
        String lowestCity = "";
        for(int i = 0; i<keySet.size(); i++)
        {
            K k = keySet.get(i);
            if(k.getState().equals(state))
            {
                String city = k.getCity();
                if(getCityPopulation(city, state) < lowest)
                {
                    lowest = getCityPopulation(city, state);
                    lowestCity = city;
                }
            }
        }
        return lowestCity + ": " + lowest;
    }
    public static int getMeanPopulationOfState(String state)
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
        return sum/count;
    }
    public static ArrayList<String> getRangeOfCityPopulations (int max, int min)
    {
        //Something is wrong, look back at this later
        Set<K> keySet = hash.keySet();
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i<keySet.size(); i++)
        {
            K k = keySet.get(i);
            {
                String city = k.getCity();
                String state = k.getState();
                if(getCityPopulation(city, state) < max && getCityPopulation(city, state) > min)
                {
                    list.add(new String(city + ", " + state + ": " + getCityPopulation(city, state))); 
                }
            }
        }
        return list;
    }
}
