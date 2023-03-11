/**
 * The Scheduler Test Java class is used to test the functions located in the Scheduler Java class.  
 * 
 * @author Ray Prina
 * @version 1.0
 * @since[2023-02-04]
 */

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class Scheduler_Test
{
    private DatagramSocket socket;
    private int schedulerPortNum;
    private int elevatorPortNum;
    private InetAddress address;
    
    @BeforeEach
    public void init()
    {

    }

    @Test
    public void test_receiveData()
    {

    }
}