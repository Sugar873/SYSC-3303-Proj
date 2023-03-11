/**
 * The Floor Test Java class is used to test the functions located in the Floor Java class.  
 * 
 * @author Ray Prina
 * @version 1.0
 * @since[2023-02-04]
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class Floor_Test 
{
    private File file;
    private ArrayList<Requests> requests;
    private int schedulerPort;
    private int floorPort;
   
    Floor floor;

    @BeforeEach
    public void init() throws IOException 
    {
        floor = new Floor(file, floorPort, schedulerPort);
        requests = new ArrayList<>();
    }

    @Test
    public void test_floorParser()
    {
        File file = new File("input.txt");

        
        //assertEquals(0, floor.floorParser(), "Floor Parser not working as intended.");
    }
}
