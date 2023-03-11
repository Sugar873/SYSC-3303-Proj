/**
 * The Floor class is used to simulate a floor in an elevator system. It contains 
 * requests for the elevator that are stored in an ArrayList and are read from a file.
 * @author Sagar Syal
 * @author Sanagt Bu
 * @version 1.0
 * @since[2023-02-04]
 */

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Floor implements Runnable
{
    private File file; //file is an instance that contains requests for the elevator
    private ArrayList<Requests> requests = new ArrayList<>(); //An ArrayList that will store requests read from file

    private DatagramSocket socket; // an instance that will be used to send requests to scheduler
    private int schedulerPort; // an integer that represents the port number of the scheduler
    private int floorPort; // an integer that represents the port number of the floor
    private InetAddress address; // an instance that represents local host address
    


    /**
    The defeault Floor constructor
    @param File is the input file that contains the requests for the elevator
    @param FloorPort is the port number of the floor
    @param SchedulerPort is the port number of the scheduler
    @throws FileNotFoundException for when the file cannot be found
    @throws UnknownHostException when the host cannot be found
    @throws SocketException socket exception
    @throws IOException IO exception
     */
    public Floor(File File, int FloorPort, int SchedulerPort) throws FileNotFoundException, UnknownHostException, SocketException, IOException
    {
        this.socket = new DatagramSocket();
        this.file = File;
        this.schedulerPort = SchedulerPort;
        this.floorPort = FloorPort;
        this.address = InetAddress.getByName("localhost");
    }


    /** 
    Method used to parse the requests from the Test.txt file and store them in requests.
    @throws FileNotFoundException when the file cannot be found
    */
    synchronized void floorParser() throws FileNotFoundException
    {
        ArrayList<String> tmp = new ArrayList<>(); //Used to temporarily store values of each request
        try 
        {
            Scanner s = new Scanner(file); //Reads the file contents
            while(s.hasNextLine()) //loops until through every line in the file
            {
                String line = s.nextLine(); 
                Scanner line_S = new Scanner(line); 
                
                line_S.useDelimiter(";"); //Used to separate tokens every time a ";" is encountered

                while(line_S.hasNext())
                {
                    tmp.add(line_S.next());
                }
                requests.add(new Requests(tmp.get(0), tmp.get(1), tmp.get(2), tmp.get(3))); //creates new Requests object with the tmp values and adds to the requests ArrayList
                tmp.clear();
                line_S.close();
            }
            s.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    /**
    Responsible for parsing the requests from a file and sending them as a message through the DatagramSocket to the specified address and port
    */
   
    @Override
    public synchronized void run()
    {
        try 
        {
            floorParser(); //Parses the request from file
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
        for (Requests byte_to_send : requests) // iterate through all the requests
        {
            byte[] msg = byte_to_send.print().getBytes(); //Converts the requests into a string then into an array of bytes
            DatagramPacket packet_to_send = new DatagramPacket(msg, msg.length, address, schedulerPort); //Creates the new DaragramPacket with the message in bytes to send to the address
            try 
            {
                socket.send(packet_to_send); //Sends the packets
                System.out.println("Sending by Floor: " + byte_to_send.print());
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            
        }

    }

}