/**
 * This class represents a scheduler in a building elevator system~~
 * @author Sagar Syal
 * @author Sanagt Buttar
 * @author Sundar Vengadeswaran
 * @version 1.0
 * @since[2023-02-04]
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.InetAddress;

public class Scheduler implements Runnable
{
    private DatagramSocket socket;
    private int schedulerPortNum;
    private int elevatorPortNum;
    private InetAddress address;

    /**
     * Constructs a scheduler Obj, that initalizes the a new Datagram socket 
     * initalizes the scheduler's port number and initalizes the Elevator's
     * port number. As well as retrevies the localhost address
     * 
     * @param SchedulerPortNum the schduler's port number
     * @param ElevatorPortNum the schduler's port number
     * @throws UnknownHostException is the local host cannot be retrieved
     * @throws SocketException if there is anerror in creating 
     */
    public Scheduler(int SchedulerPortNum, int ElevatorPortNum) throws UnknownHostException, SocketException
    {
        this.socket = new DatagramSocket();
        this.schedulerPortNum = SchedulerPortNum;
        this.elevatorPortNum = ElevatorPortNum;
        this.address = InetAddress.getByName("localhost");
    }

    /**
     * Used to recive Data from specifed Port using Datagram and 
     * returns the recieved data as a byte array
     * 
     * @param receiverPortNum the port number to recive data through
     * @throws IOException if an IO error occurs while reciveing data
     * @return  The recived data from the byte array
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
     * Run function for thread
     */
    @Override
    public synchronized void run() 
    {
        while(true)
        {
            try   
            {

                //Receive data from FLOOR CLASS
                //Store data received in byte array
                byte[] dataReceiveFloor = receiveData(schedulerPortNum);

                //Send data received from FLOOR CLASS to ELEVATOR CLASS
                DatagramPacket packet_to_send_elevator = new DatagramPacket(dataReceiveFloor, dataReceiveFloor.length, address, elevatorPortNum);
                socket.send(packet_to_send_elevator);

                //Send data received from ELEVATOR CLASS to FLOOR CLASS
                //DatagramPacket packet_to_send_floor = new DatagramPacket(dataReceiveElevator, dataReceiveElevator.length, address, elevatorPortNum);
                //socket.send(packet_to_send_floor);
                //socket.close();
                
            } 
            catch (IOException e) 
                {
                // TODO Auto-generated catch block
                e.printStackTrace();
                break;
            }  
        }
    }
}