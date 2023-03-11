/**
 * The Requests Test Java class is used to test the functions located in the Requests Java class.  
 * 
 * @author Ray Prina
 * @author Sagar Syal
 * @version 1.0
 * @since[2023-02-04]
 */

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Requests_Test
{
    Requests requests;

    @BeforeEach
    public void init()
    {
        requests =  new Requests("12:40:15", "2", "3", "up");
    }

    @Test
    public void test_get_time() throws ParseException
    {
        Date x;
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        x = timeFormat.parse("12:40:15");
        assertEquals(x, requests.get_time(), "Time was printed incorrectly.");
    }

    @Test
    public void test_get_currentFloor()
    {
        assertEquals(2, requests.get_currentFloor(), "Current Floor was printed incorrectly.");
    }

    @Test
    public void test_get_destinationFloor()
    {
        assertEquals(3, requests.get_destinationFloor(), "Destination Floor was printed incorrectly.");
    }

    @Test
    public void test_get_direction()
    {
        assertEquals(true, requests.get_direction(), "Direction was printed incorrectly.");
    }
    
}
