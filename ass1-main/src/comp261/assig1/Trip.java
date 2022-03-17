package comp261.assig1;
import java.util.ArrayList;
import java.util.HashMap;

public class Trip {
    private String stop_pattern_id;
    private ArrayList<Edge> pointToPoint;
    private Stop start;
    private Stop end;
    private HashMap<String, Stop> stopMap;

    public Trip(String stop_pattern_id, ArrayList<Edge> pointToPoint, Stop start, Stop end,  HashMap<String, Stop> stopMap){
        this.stop_pattern_id = stop_pattern_id;
        this.pointToPoint = pointToPoint;
        this.start = start;
        this.end = end;
        this.stopMap = stopMap;
    }

    public HashMap <String, Stop> getStopMap(){
        return stopMap;
    }

    public ArrayList <String> getStops(){
        ArrayList <String> stopList = new ArrayList<>();
        for(String s : stopMap.keySet()){
            stopList.add(s);
        }
        return stopList;
    }

    public Stop getStart(){
        return start;
    }

    public Stop getEnd(){
        return end;
    }

    public String getId(){
        return stop_pattern_id;
    }

    public ArrayList<Edge> getEdges(){
        return pointToPoint;
    }

}
