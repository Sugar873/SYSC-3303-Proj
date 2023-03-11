G10_Project Project Iteration 1
Multithreading Elevator Subsystem

Table of Contents
-List of files
-Overview
-Setup Instructions
-Contribution

List of files:
Elevator_Test.java -> test class with junit testing for Elevators methods
Elevator.java -> class that represents the elevator system 
Floor_Test.java -> test class with junit testing for floors methods
Floor.java -> class that represents the floors 
Requests_Test.java -> test class with junit testing for Requests methods
Request.java -> class that represent the requests
Scheduler_Test.java -> test class with junit testing for schdulers methods
Scheduler.java -> class that represents the scheduler system 
Main.java -> The main class which sets up the elevator simulation

Overview:
We used what we learned in Assignment 1 to create three threads, one for each of the subsystems.
Floor and elevators are clients, while Scheduler is the server in this case. The data is read from 
a Test.txt file, where we used semicolons to seperate the data (Date;Current_Floor;Destination_Floor;
Direction -> 12:40:15;2;3;up). The data is then sent from the floor as a packet to the scheduler, which 
then sends a packet to the elevator. The Elevator then sends a packet as a reply back to the scheduler, 
followed by a reply packet sent from the scheduler to the floor. 

Setup Instrutions:
1. Import the G10_Project folder into eclipse as a Project
2. Run "Main.java

Contribution:
Sangat Buttar -> Scheduler.java, README.txt, & Floor.java
Sagar Syal -> Requests.java, Scheduler.java, README.txt, JavaDocs, & Floor.java 
Ray Prina -> Elevator_Test.java, Floor_Test.java, Requests_Test.java, & Scheduler_Test.java
Sundar Vengadeswaran -> Elevator.java, Scheduler.java, & Main.java 
Andrew Kong -> UML class, sequence diagram, JavaDocs, & Elevator.java 
