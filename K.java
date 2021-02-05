public class K
{
    // instance variables - replace the example below with your own
    private String city;
    private String state;
    /**
     * Constructor for objects of class K
     */
    public K(String citystate)
    {
        // initialise instance variables
        String city = citystate.split(",")[0];
        String state = citystate.split(",")[1];
        this.city = city;
        this.state = state.substring(1);
    }
    public K(String city, String state)
    {
        // initialise instance variables
        this.city = city;
        this.state= state;
    }

    public String getCity()
    {
        return city;
    }
    public String getState()
    {
        return state;
    }
    public int hashCode()
    {
        return (city+state).hashCode();
    }
    public boolean equals(Object key)
    {
        K key1 = (K)key;
        if(this.getCity().equals(key1.getCity()) && this.getState().equals(key1.getState()))
        {
            return true;
        }
        return false;
    }
}
