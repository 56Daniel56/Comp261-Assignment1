package comp261.assig1;

// The edge class represents an edge in the graph.

public class Edge {
    private Stop fromStop;
    private Stop toStop;
    private String tripId;
    //array of edges aswell maybe??

    //todo: add a constructor
    public Edge(Stop fromStop, Stop toStop, String tripId){
        this.fromStop = fromStop;
        this.toStop = toStop;
        this.tripId = tripId;
    }

    //todo: add getters and setters
    public Stop getFrom(){
        return fromStop;
    }

    public Stop getToStop(){
        return toStop;
    }
  
    public String tripId(){
        return tripId;
    }
}
