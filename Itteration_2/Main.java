import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Main 
{
    static final int SCHEDUELER_PORT = 32000;
    static final int[] FLOOR_PORT = {49600, 57472, 65000};
    static final int ELEVATOR_PORT = 16384;

    private static ArrayList<Requests> requests = new ArrayList<>();
    private static File file;

    public static void main(String[] args) throws Exception
    {
        //click the comment thread to run on ur machine(the little text box on line 9)
        file = new File("./Input.txt");        
        floorParser();
        //Scheduler scheduler = new Scheduler();
    

        Floor floor_1 = new Floor(FLOOR_PORT[0],SCHEDUELER_PORT,1);
        Floor floor_2 = new Floor(FLOOR_PORT[1],SCHEDUELER_PORT,2);
        Floor floor_3 = new Floor(FLOOR_PORT[2],SCHEDUELER_PORT,3);

        for (int i=0; i<requests.size(); i++){
            if (requests.get(i).get_currentFloor() == 1)
            {
                floor_1.addRequest(requests.get(i));
            }
            else if (requests.get(i).get_currentFloor() == 2){
                floor_2.addRequest(requests.get(i));
            }
            else{
                floor_3.addRequest(requests.get(i));
            }
        }

        Scheduler scheduler = new Scheduler(SCHEDUELER_PORT, ELEVATOR_PORT);

        Elevator elevator = new Elevator(ELEVATOR_PORT,SCHEDUELER_PORT);

        Thread floorThread1 = new Thread(floor_1,"FLOOR 1");
        Thread floorThread2 = new Thread(floor_2,"FLOOR 2");
        Thread floorThread3 = new Thread(floor_3,"FLOOR 3");

        Thread schedulerThread = new Thread(scheduler,"SCHEDULER");
        Thread elevatorThread = new Thread(elevator, "ELEVATOR");

        schedulerThread.start();
        elevatorThread.start();
        
        floorThread1.start();
        floorThread2.start();
        floorThread3.start();

        
    }

    /** 
    Method used to parse the requests from the Test.txt file and store them in requests.
    @throws FileNotFoundException when the file cannot be found
    */
    static void floorParser() throws FileNotFoundException
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
}
