/**
 * Requests class is used to store and manage data for elevator requests made by users. 
 * It contains the information about the time, current floor, destination floor 
 * and direction of the request.
 * @author Sagar Syal
 * @version 1.0
 * @since [2023-02-04]
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;


public class Requests 
{
    private ArrayList<String> requestString;
    private String currentFloor_S;
    private String destinationFloor_S;
    private String direction_S;

    private Date time;
    private int currentFloor;
    private int destinationFloor;
    private boolean direction;

    /**
    The defeault Requests constructor  
    @param time_S 
    @param currentFloor_S
    @param destinationFloor_S
    @param direction_S 
     */
    public Requests(String time_S, String currentFloor_S, String destinationFloor_S, String direction_S)
    {
        requestString = new ArrayList<>();
        requestString.add(time_S);
        requestString.add(currentFloor_S);
        requestString.add(destinationFloor_S);
        requestString.add(direction_S);

        makeRequest(); //convert the strings in "requetString" into the appropriate data types
    }

    
    
    /**
    Takes the strings stored in the ArrayList and converts them into the required data types
     */
    public void makeRequest()
    {

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); //used for parsing the time string from the input

        try 
        {
            time = timeFormat.parse(requestString.get(0)); //uses the parse method of the SimpleDateFormat object to parse the time string from the input and stores it in time
        } 
        catch (Exception e) 
        {
            System.out.println("Please input an correct format for time");
            System.exit(1);
        }

       try 
       {
            this.currentFloor = Integer.parseInt(requestString.get(1)); //converts string representing current floor from the input and assigns it to the currentFloor
            this.destinationFloor = Integer.parseInt(requestString.get(2)); //converts string representing destination floor from the input and assigns it to the destinationFloor
       } 
       catch (NumberFormatException e) 
       {
            System.out.println("Please input an integer for floors");
            System.exit(1);
       }
       //checks if the direction of the request is "up" and if it is, sets the direction variable to true
       if(requestString.get(3).toLowerCase().equals("up"))
       {
            this.direction = true;
       }
       //checks if the direction of the request is "down" and if it is, sets the direction variable to false
       else if(requestString.get(3).toLowerCase().equals("down"))
       {
            this.direction = false;
       }
    }
    //Returns the time of the request as a Date object
    public Date get_time()
    {
        return this.time;
    }
    //Returns the current floor of the request as an integer
    public int get_currentFloor()
    {
        return this.currentFloor;
    }
    //Returns the destination floor of the request as an integer
    public int get_destinationFloor()
    {
        return this.destinationFloor;
    }
    //Returns the direction of the request as a boolean value, true for "up" and false for "down"
    public boolean get_direction()
    {
        return this.direction;
    }

    /**
    Returns a formatted string representation of the request that includes the time, current floor, destination floor and direction of the request
     */
    public String print()
    {
        return(String)
        (
        ("\nTIME: "+get_time()+ //The time
        "\nCURRENT FLOOR: "+get_currentFloor()+ //The current Floor
        "\nDESTINATION FLOOR: "+get_destinationFloor()+ // The destination floor
        "\nDIRECTION: "+get_direction()+"\n") //The direction
        );
    }
    
}
