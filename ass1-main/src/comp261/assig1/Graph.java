package comp261.assig1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class Graph {

    //Todo add your data structures here
    private HashMap <String, Stop> stops;
    private ArrayList <Stop> stopList;
    private ArrayList <Trip> trips;
    private ArrayList <Edge> edgeList;
    private HashMap<String, ArrayList<String>> tripData;
    
// constructor post parsing
    public Graph(ArrayList<Stop> stopList, ArrayList<Edge> edgeList) {
        this.stopList = stopList;
        this.edgeList = edgeList;
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
        stopList = new ArrayList<>();
        for(String stop : stops.keySet()){
            stopList.add(stops.get(stop));
        }

       //load the data into a hashmap

    }

    // buildTripData into stops
    private void buildTripData(){
        // Todo: this could be used for trips
        ArrayList <Edge> edges = new ArrayList<>();
        Stop start = null;
        Stop end = null;
        int count = 0;
        int countTo = 0;
        for (String trip : tripData.keySet()) {     //for each trip in the set of trips included in the file
            ArrayList<String> stopList = tripData.get(trip);    //get each list of ids of stops
            for(int i = 0; i<stopList.size()-1;  i++){          //loop through every stop
                if(i == 0){
                    start = stops.get(stopList.get(i));
                }
                else if (i == stopList.size()-2){   //-2 for the last time it goes round the loop
                    end = stops.get(stopList.get(i));
                }
                Stop fromStop = stops.get(stopList.get(i));
                Stop toStop = stops.get(stopList.get(i+1));
                if(fromStop == null){
                    //still 45 null values?????
                    System.out.println("From: " + count++ + " I: "+ i);
                    continue;
                }
                if(toStop == null){
                    //still 45 null values?????
                    System.out.println("To: " + countTo++ + " I: "+ i);
                    continue;
                }
                edges.add(new Edge(fromStop, toStop, trip));    
            }
            trips.add(new Trip(trip, edges, start, end));
        }
        //trip is a  list of edges



    }

    // Todo: getters and setters
    public ArrayList<Trip> getTripList(){
        return trips;
    }

    public ArrayList<Stop> getStopList(){
        return stopList;
    }




 

}
