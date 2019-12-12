Nicholas Mayer
mayer379@umn.edu
CSCI 1933
Chris Dovolis
Project 4

In order to use my project 4 program one must execute the main method in the BusSim class.
To change the simulation parameters the following actions must be taken with the BusSim class:

-Total event simulation time: change the variable totalSimTime
-Average rider arrival time: change the variable riderTime
-Number of buses: change the variable numBuses
-Number of express buses: change the variable numExpressBuses

The system will, by default, output the simulation statistics within the command window.

The BusSim class contains the main method for this project. The PQ class handles the priority queueing
for the event simulation. Arrival class handles rider creation and distribution among the stops, though
the main method in BusSim instantiates the arrivals for each stop. BusEvent class handles the movement
and scheduling of buses, but the Bus class holds all methods for loading/unloading including checking
whether the rider can board an express bus. Rider class holds the info for each bus passenger and
holds the statistics used to calculate final statistics relating to rider times. Stats class collects
all relevant info needed to prepare and output final statistics for evaluation of the simulation.