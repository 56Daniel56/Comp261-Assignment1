package comp261.assig1;
import java.util.ArrayList;

public class Trip {
    private String stop_pattern_id;
    private ArrayList<Edge> pointToPoint;
    private Stop start;
    private Stop end;

    public Trip(String stop_pattern_id, ArrayList<Edge> pointToPoint, Stop start, Stop end){
        this.stop_pattern_id = stop_pattern_id;
        this.pointToPoint = pointToPoint;
        this.start = start;
        this.end = end;
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
