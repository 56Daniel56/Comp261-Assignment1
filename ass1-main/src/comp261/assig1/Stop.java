package comp261.assig1;

import java.util.ArrayList;


// decide the data structure for stops
public class Stop {
    //probably always have these three    
    private GisPoint loc;
    private String name;
    private String id;

    //Todo: add additional data structures


        
    // Constructor
        public Stop(GisPoint loc, String name, String id) {
            this.loc = loc;
            this.name = name;
            this.id = id;
        }

        public GisPoint getLoc(){
            return loc;
        }

        public String getName(){
            return name;
        }

        public String getId(){
            return id;
        }

    // add getters and setters etc
        
    }
