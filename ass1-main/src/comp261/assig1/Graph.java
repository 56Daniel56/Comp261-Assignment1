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

    private HashMap <String, Stop> makeTripMap(ArrayList<String> stopIdList){
        HashMap <String, Stop> stopMap = new HashMap<>();
        for(int i = 0; i<stopIdList.size(); i++){
            Stop currentStop = stops.get(stopIdList.get(i));
            if(currentStop == null){
                continue;
            }
            stopMap.put(stopIdList.get(i), currentStop);

        }        
        return stopMap;
    }

    // buildTripData into stops
    private void buildTripData(){
        // Todo: this could be used for trips
        
        Stop start = null;
        Stop end = null;
        for (String trip : tripData.keySet()) {     //for each trip in the set of trips included in the file
            ArrayList<String> stopListID = tripData.get(trip);    //get each list of ids of stops
            ArrayList <Edge> edges = new ArrayList<>();


            for(int i = 0; i<stopListID.size()-1;  i++){          //loop through every stop and stop 1 before the end                                         //-1 since you are starting at zero

                if(i == 0){
                    start = stops.get(stopListID.get(i));
                }
                else if (i >= stopListID.size()-1){   // for the last time it goes round the loop
                    end = stops.get(stopListID.get(i+1));
                }
                
                Stop fromStop = stops.get(stopListID.get(i));
                Stop toStop = stops.get(stopListID.get(i+1));

                if(fromStop == null){
                    if(stops.get(stopListID.get(i-1)) != null){   // if the location its going from == null then refer to the previous stop and map from there
                        fromStop = stops.get(stopListID.get(i-1));
                    }
                    else{
                        continue;
                    }
                }
                if(toStop == null){
                    if(i != stopListID.size()-2 && stops.get(stopListID.get(i+2)) != null){    // if the next stop == null then check the next stop in the list if there is another stop and put it to that one
                        toStop = stops.get(stopListID.get(i+2));
                    }
                    else{
                        continue;
                    }
                }
                edges.add(new Edge(fromStop, toStop, trip));    
            }

            HashMap tripMap = makeTripMap(stopListID);

            trips.add(new Trip(trip, edges, start, end, tripMap));
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
