package comp261.assig1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class Graph {

    //Todo add your data structures here
    private HashMap <String, Stop> stops;
    private ArrayList <Trip> trips;
    private HashMap<String, ArrayList<String>> tripData;
    
// constructor post parsing
    public Graph() {
        
    }

    // constructor with parsing
    public Graph(File stopFile, File tripFile) {
        //Todo: instantiate your data structures here
        this.stops = new HashMap<String, Stop>();
        this.trips = new ArrayList<Trip>();
        this.tripData = new HashMap<>();
        //Then you could parse them using the Parser
        stops = Parser.parseStops(stopFile);
        tripData = Parser.parseTrips(tripFile);

        buildStopList();
        buildTripData();
    }

    // buildStoplist from other data structures
    private void buildStopList() {
       // Todo: you could use this sort of method to create additional data structures


       //load the data into a hashmap

    }

    // buildTripData into stops
    private void buildTripData(){
        // Todo: this could be used for trips
        ArrayList <Edge> edges = new ArrayList<>();
        for (String trip : tripData.keySet()) {
            for(int i = 0; i<tripData.get(trip).size()-1;  i++){
                ArrayList<String> stopList = tripData.get(trip);
                Stop fromStop = stops.get(stopList.get(i));
                Stop toStop = stops.get(stopList.get(i+1));
                edges.add(new Edge(fromStop, toStop, trip));    
            }
            trips.add(new Trip(trip, edges));
        }
        //trip is a  list of edges



    }

    // Todo: getters and setters





 

}
