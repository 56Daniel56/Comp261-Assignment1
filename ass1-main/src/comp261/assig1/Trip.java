package comp261.assig1;
import java.util.ArrayList;

public class Trip {
    private String stop_pattern_id;
    ArrayList<Edge> pointToPoint;

    public Trip(String stop_pattern_id, ArrayList<Edge> pointToPoint){
        this.stop_pattern_id = stop_pattern_id;
        this.pointToPoint = pointToPoint;
    }


    public String getId(){
        return stop_pattern_id;
    }

    public ArrayList<Edge> getEdges(){
        return pointToPoint;
    }

}
