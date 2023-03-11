/**
 * The Elevator Test Java class is used to test the functions located in the Elevator Java class.  
 * 
 * @author Ray Prina
 * @version 1.0
 * @since[2023-02-04]
 */

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Elevator_Test
{
    private DatagramSocket socket;
    private int elevatorPortNum;
    private int schedulerPortNum;
    private InetAddress address;

    Elevator elevator;

    @BeforeEach
    public void init() throws UnknownHostException, SocketException, IOException
    {
        elevator = new Elevator(elevatorPortNum, schedulerPortNum);
        this.socket = new DatagramSocket();
        this.address = InetAddress.getByName("224.0.0.1");
    }

    @Test
    public void test_receiveData() throws ParseException, IOException
    {
        Date x;
        int receiverPortNum = 0;
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        x = timeFormat.parse("12:40:15");
    
		assertEquals(x, elevator.receiveData(receiverPortNum), "Receive Data is not working as intended.");
    }

}

