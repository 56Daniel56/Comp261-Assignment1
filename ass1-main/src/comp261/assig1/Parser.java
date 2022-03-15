package comp261.assig1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This utility class parses the files, and return the relevant data structure.
 * Internally it uses BufferedReaders instead of Scanners to read in the files,
 * as Scanners are slow.
 * 
 * @author Simon
 */
public class Parser {

    // read the stop file
    // tab separated stop descriptions
    // stop_id	stop_code	stop_name	stop_desc	stop_lat	stop_lon	zone_id	stop_url	location_type	parent_station	stop_timezone

	public static HashMap parseStops(File nodeFile){
    	// data types to be returned to the graph 
        HashMap <String, Stop> stops = new HashMap<>(); /*Data initialise*/ //fix me...
		try {
			// make a reader
			BufferedReader br = new BufferedReader(new FileReader(nodeFile));
			br.readLine(); // throw away the top line of the file
			String line;
			// read in each line of the file
            int count = 0;
			while ((line = br.readLine()) != null) {
				// tokenise the line by splitting it on tabs
				String[] tokens = line.split("[\t]");
                if (tokens.length >= 6) {
                    // process the tokens
                    String stopId = tokens[0];
                    String stopName = tokens[2];
                    double lat = Double.valueOf(tokens[4]);
                    double lon = Double.valueOf(tokens[5]);

                    //Todo: Decide how to store the stop data
                    GisPoint location = new GisPoint(lon, lat);
                    Stop stopObj =new Stop(location, stopName, stopId);
                    stops.put(stopId, stopObj);
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("file reading failed.");
        }
        return stops;
    }

    // parse the trip file
    // header: stop_pattern_id,stop_id,stop_sequence,timepoint
    public static HashMap parseTrips(File tripFile){

        HashMap <String, ArrayList<String>> trips = new HashMap<>();/*Data Init*/
        ArrayList <String> stops = new ArrayList<String>();
		try {
			// make a reader
			BufferedReader br = new BufferedReader(new FileReader(tripFile));
			br.readLine(); // throw away the top line of the file.
			String line;
			// read in each line of the file
            while ((line = br.readLine()) != null) {
              
                // tokenise the line by splitting it at ",".
                String[] tokens = line.split("[,]");
                if (tokens.length >= 4) {
                    // process the tokens
                    String stopPatternId = tokens[0];
                    String stopId = tokens[1];
                    int stopSequence = Integer.parseInt(tokens[2]);
                    String timepoint = tokens[3];
                    
                    // Decide how to store the trip data

                    //need to work out how to get the from stop and the tostop
                    
                    //stops.add(stopId);
                    if(!trips.containsKey(stopPatternId) ){
                        trips.put(stopPatternId, stops = new ArrayList<String>());
                    }
                    stops.add(stopId);
                   

                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("file reading failed.");
        }
        return trips;
    }
}





// code for COMP261 assignments