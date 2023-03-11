/**
 * This class represents an Elevator
 * @author Sundar Vengadeswaran
 * @author Andrew Kong
 * @version 1.0
 * @since [2023-02-04]
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.InetAddress;


public class Elevator implements Runnable
{
    
    private DatagramSocket socket;
    private int elevatorPortNum;
    private int schedulerPortNum;
    private InetAddress address;

    /**
     * constructor for the elevator
     * @param ReceivePortNum reciver port
     * @param SchedulerPortNum the port for the scheduler
     * @throws UnknownHostException Error for unknow host
     * @throws SocketException socket exception
     */
    public Elevator(int ReceivePortNum, int SchedulerPortNum) throws UnknownHostException, SocketException
    {
        this.socket = new DatagramSocket();
        this.elevatorPortNum = ReceivePortNum;
        this.schedulerPortNum = SchedulerPortNum;
        this.address = InetAddress.getByName("localhost");

    }

    /**
     * receiveData from source
     * @param receiverPortNum the port for the receiver
     * @return dataReceived a byte array with the data
     * @throws IOException IO error
     */
    public synchronized byte[] receiveData(int receiverPortNum) throws IOException
    {
        // Declare a byte array to store received data
        byte[] dataReceived = new byte[256];
        // Create a datagram socket on the mentioned port
        DatagramSocket receiverPort = new DatagramSocket(receiverPortNum);
        // Create a datagram packet to receive data
        DatagramPacket received = new DatagramPacket(dataReceived, dataReceived.length);
        
        // Receive data from the port
        receiverPort.receive(received);
        
        // Get the length of received data
        int len = received.getLength();
        // Create a string using the bytes of the received data
        String receiveString = new String(dataReceived, 0, len);
        System.out.println("Received by " + getClass() + ": " + receiveString);

        // Close the datagram socket
        receiverPort.close();
        
        // Return the received data
        return dataReceived;
    }



    /**
     * Run the program
     */
    @Override
    public synchronized void run() 
    {
        try 
        {
            //Receive data from SCHEDULER CLASS
            //Store data received in byte array
            byte[] datareceive = receiveData(elevatorPortNum);
            
            //Send data received from SCHEDULER CLASS to SCHEDULER CLASS
            DatagramPacket packet_to_send = new DatagramPacket(datareceive, datareceive.length, address, schedulerPortNum);
            socket.send(packet_to_send); //Sends the packets

        }
        catch (IOException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //break;
        }  
            
    }      
}

