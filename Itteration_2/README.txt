G10_Project Project Iteration 2
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
In this version of the project, we have added multiple states to the elevator that allows for 
improved concurrency. The scheduler has been modified that ensures threads do not overwrite or 
attempt to access the same data at the same time. This increases the efficiency, 
reliability and safety of the elevator operations.

Setup Instrutions:
1. Import the G10_Project folder into eclipse as a Project
2. Run "Main.java

Contribution:
Sangat Buttar -> Scheduler.java, README.txt, & Floor.java
Sagar Syal -> Requests.java, Scheduler.java, README.txt, JavaDocs, & Floor.java 
Ray Prina -> Elevator_Test.java, Floor_Test.java, Requests_Test.java, & Scheduler_Test.java
Sundar Vengadeswaran -> Elevator.java, Scheduler.java, & Main.java 
Andrew Kong -> UML class, sequence diagram, JavaDocs, & Elevator.java 
