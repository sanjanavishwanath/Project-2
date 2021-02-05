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
        String city = citystate.split(", ")[0];
        String state = citystate.split(", ")[1];
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
}
