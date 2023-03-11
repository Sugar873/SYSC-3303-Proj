/**
 * This class represents an Elevator
 * @author Sundar Vengadeswaran
 * @author Andrew Kong
 * @version 1.0
 * @since[2023-02-04]
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
     * @param receiverPortNum the port for the reciver
     * @return byte[] a byte array with the data
     * @throws IOException IO error
     */
    public byte[] receiveData(int receiverPortNum) throws IOException
    {
        byte[] dataReceived = new byte[256];
        DatagramSocket receiverPort = new DatagramSocket(receiverPortNum);
        DatagramPacket received = new DatagramPacket(dataReceived, dataReceived.length);

        receiverPort.receive(received);

        int len = received.getLength();
        String receiveString = new String(dataReceived, 0, len);
        System.out.println("Received by " + getClass() + ": " + receiveString);

        receiverPort.close();

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

