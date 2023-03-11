import java.io.FileNotFoundException;
import java.io.File;

public class Main 
{
    static final int SCHEDUELER_PORT = 0123;
    static final int FLOOR_PORT = 2345;
    static final int ELEVATOR_PORT = 3456;

    public static void main(String[] args) throws Exception
    {
        //click the comment thread to run on ur machine(the little text box on line 9)
        File file = new File("./Input.txt");
        // Floor f = new Floor(file);
        
        //Scheduler scheduler = new Scheduler(); 
        Floor floor = new Floor(file,FLOOR_PORT,SCHEDUELER_PORT);

        Scheduler scheduler = new Scheduler(SCHEDUELER_PORT, ELEVATOR_PORT);

        Elevator elevator = new Elevator(ELEVATOR_PORT,SCHEDUELER_PORT);

        Thread floorThread = new Thread(floor,"FLOOR");
        Thread schedulerThread = new Thread(scheduler,"SCHEDULER");
        Thread elevatorThread = new Thread(elevator, "ELEVATOR");

        floorThread.start();
        schedulerThread.start();
        elevatorThread.start();
        
    }
}
