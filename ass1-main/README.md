# COMP261 Assignment 1 template

## The Wellington Bus Network 

The first assignment aims to read data from the Wellington Metlink system, display it on screen, and let the user view and search the data in several ways. The program will need several large data structures, and the key challenge of the assignment is to implement those data structures. 

## Resources 

The assignment repository contains: 
* A folder of the template code and a small example. 
* A folder with the Wellington Metlink data files. 
* The marking guide. 

## Q&A
I have added a [Q&A.md](Q&A.md))file for important questions and answers.

## To Submit 

You should submit these things: 
* All the source code for your program, including the template code. Please make sure you do this, without it we cannot give you any marks. Again: submit all your .java files. 
* Any other files your program needs to run that aren’t the data files provided. 
* A **readme.md** in the repository of your program to help the marker understand the code. The readme should:
    * describe what your code does and doesn’t do. 
    * describe the important data structures you used. The readme should be clear, but it does not have to be fancy, very plain formatting is all that is needed.
    * You can link to code in the repository with line numbers to highlight specific parts of the code for example 
	 [**Show Main lines 11-20**](/src/comp261/assig1/Main.java#L11-20) done like this 
     `[**Show Main lines 11-20**](/src/comp261/assig1/Main.java#L11-20)`
    * It does not need to be long, but you need to help the marker see what you did. Note that for marking, you will need to sign up for a 15-minute slot with the marker. 


## Requirements for Assignment 1 

Your program should:
* Read the data from the files described below and construct an appropriate data structure to store all the information in the files. The two files of interest are
    * stops.txt - The bus stops
    * stop_patterns.txt - The sequence of stops in a trip
* Display the data visually by drawing all the nodes and edges. Each edge should be drawn as a straight line. The program must allow the user to either view the whole network or zoom in on a smaller region. Ideally, the user should be able to zoom and pan to arbitrary views of the data. 
* Allow the user to enter the name of a stop in a text box, and then highlight the stop, and all the trips(stop_patterns) going through the stop. To do this, the program should store all the stops in a trie structure which will act as a searchable index into the collection of stop objects.
* Allow the user to click on a place on the visualisation, and then highlight and display information about the closest stop to the clicked position. The provided marking guide describes what you need to do for the minimum, core, completion, and challenge, as does this document. 


## The data 

The data is for Wellington City, obtained from [https://opendata.metlink.org.nz/](https://opendata.metlink.org.nz/).  The data was downloaded in Feb 2022 and the txt files have not been altered. There are a large collections of files as these are the data dump from a database.  We will only include the two files of interest in the Assignment template but you can get the rest of the files from [https://static.opendata.metlink.org.nz/v1/gtfs/full.zip](https://static.opendata.metlink.org.nz/v1/gtfs/full.zip) if you want to explore the associated data.

**stops.txt** stores the stops in the journey planning network. In the file, the first line is the column header. From the second line, each line represents a stop, including:

stop_id	, stop_code, stop_name, stop_desc, stop_lat, stop_lon, zone_id, stop_url, location_type, parent_station, stop_timezone.

As you can tell there is more data here than you need.  You only need to extract:

stop_id, stop_name, stop_lat, stop_lon This will be token 0,2,4,5

The entries are separated by tabs. 

**stops_patterns.txt** stores the pattern of stops for each trip in the bus network. Each pattern  is a sequence of stops. In the file, the first line is the title. From the second line, each line is a stop in the sequence. Lines have the same trip_id are connected and have a unique sequential stop_id which matches to the stop_id in the stop file.


## Your program 

Your program should read the data from the data files into an appropriate set of data structures, and then draw the graph. 

The program should also have the proper functionalities when the user presses the buttons, clicks on the map to select a stop, and type something into the search box. The details of the functionalities are given in the next section. 

You are not expected to create your own GUI for this assignment, rather, one is provided in the model code with the GUI description being in the MapView.fxml and the controller file GraphController. The fxml file contain an area for drawing the graph “mapcanvas”, a “load” button, some navigation buttons, a search box, a node label, and a text output area. 

You will implement: 
* the drawing commands for your data structures to show on the canvas
* the loading of the data into your data structure
* what happens when you press the navigation buttons
* click the screen
* or enter something in the search box

You will need to add code to:
* For the model
    * Graph.java
    * Stop.java
    * Edge.java
    * Trip.java
    * Trie.java
* For the controller
    * GraphController.java
    * Parser.java 

You will need to read and understand the provided code to write your program. 

While UIs and JavaFX are not a focus of this course, it is recommended you try and understand how the GUI system works. You are also free to modify it to suit your program, or build your own. 

Your program will need a collection of Stop objects, a collection of Trip objects. You will need to access stops both by their IDs and by their names. The first can be accomplished with a HashMap. The second should use a Trie structure since you need to be able to access all the stops whose names start with a specified prefix. 

The Stops and Trips form a graph, with the Stops as the nodes, and the Trips as the collection of edges. The graph is a directed (each trip is directed) multi-graph (there can be more than one connection between two stops belonging to different trips). You need to choose an appropriate data structure for this graph. 

We recommend using Stop objects and Trip objects. The Stop objects need to store their geographical location (GisPoint), both for drawing them and for finding the stop the user clicks on. The Trip objects store the Stops on the Edges that form the connections. You should decide whether to use the IDs in the data structure or to simply connect Stops, Trips and Edge objects directly to each other. 


## Solving the problem in stages 

**Minimum**– Parsing, data structures, and drawing.
* Construct classes to represent Stops, Trips, and Edges between two stops. The class should have methods to read the data from the files and construct the objects. Hint: Use the GisPoint class to represent the positions of the Stops. The Controller class includes methods for converting from longitude/latitude (GisPoint) to x/y (Point2D) coordinates.
* Make methods to read the data files, parse them, and create your data structures. If you’re using the JavaFX example, these methods should run when the **onLoad** method is called  from the “load” button. This method is passed a File object for the stop and trip files. Hint: Loading the data is best done with a BufferedReader. A tutorial can be found from [https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/](https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/).
* Draw the graph by filling in the **displayGraph **method. This method should loop through the objects in the data structures to display them. \

**Core**– Using the graph structure and other functionality. 
* Allow the user to navigate the map, i.e. implement panning and zooming with the buttons. Whenever these buttons are pressed, the method listed in **onAction** is called, and is passed the event;
* Make the program respond to the mouse so that the user can select a stop with the mouse, and the program will then highlight it, and print out the name of the stop and the ids of all the trips going through the stop. This can be done by implementing the **handleMouseClick** method which is called from the fxml function associated with canvas **onMouseClicked**. This method is passed a MouseEvent object, which contains, among other things, the coordinates of click within the graphics area. The simplest method will do a linear search through the collection of stops to find the closest one to the mouse position. (Use the methods in the “Controller” to convert between pixel-based positions on the screen and the locations in the Stops.)
* Implement the behaviour of the search box in the top right, which should allow a user to select a Stop by entering its name. This can be done in the handleSearch method, which is called whenever the user presses ‘enter’ in the search box. When they complete their entry, the program should highlight the stop with the name (exactly) matching their input, and highlight all the trips going through that stop. Hint: Remember that there may be multiple Trip objects going through the stop, and each Trip object may have multiple Edges in it. Hint: The search box, and its contents, can be accessed with the **searchText.getText()**;
* The text output area at the bottom of the window should be used to show information about stops and trips, and can be accessed via the **tripText.getText()**. 

**Completion** – Making a trie and improving search. 
* To make the program more usable, we want the search function to highlight all the stops whose name is prefixed by the search input and not just an exact match. A linear search is not an efficient way of doing this, and using some hashing scheme wouldn’t work either, as we need to do prefix-matching (unless the hashing scheme was very cleverly designed). A better way is to construct a trie data structure to store all the Stop objects, indexed by their name. This data structure will need methods to add a new stop, and to retrieve all stops matching a given prefix.
* Improve your search using your trie. Highlight all stops that start with the prefix typed into the search box. For example, there are many stop names starting with “Rata”. If the user’s search query exactly matches a stop name, it should only highlight and show exact matches. Display the name of the matched stops in the text “tripText” area. Also, highlight all the trips going through the matched stops. Hint: onAction="#handleSearch" is call when enter is pressed onKeyReleased="#handleSearchKey" is called after each key is released. 

**Challenge** – Multilingual, Quad-trees and UI improvements.
* The template code has a strings_ en_NZ.properties file and a strings_mi_NZ.properties file. These contain key:value pairs which are used to change strings of text using the Resource Bundle system.  Add any additional strings to these files, add another language if you have another language that you know. Implement language switching buttons or menu.
* A linear search through the stops to find the closest one is also inefficient, but searching for inexact matches for 2D positions doesn’t work with hashing or binary search. (This problem is a standard problem in computer graphics.) The right structure to use for searching for the closest 2D point is called a quad-tree (see more details from Wikipedia: https://en.wikipedia.org/wiki/Quadtree). Learn about and implement a quad-tree index of all the nodes and use it to search for the closest node to the mouse location.
* The user interface in the GUI class is functional, but could be improved further; find a way to significantly improve it. Two ideas are:
    * Implement navigation with the mouse and scroll wheel. 
    * Add a drop-down suggestions box to the search box, which the user can select completions from. 


## Writing it yourself 

Make sure that you write the code for the data structures yourself. You will not learn what you need to learn if you use code from somewhere else. You can build on code examples from somewhere else, but do not simply copy large segments of code and make sure that you acknowledge the source appropriately. If we identify any plagiarism, we will penalise it.


