An Elevator simulation exercise using multi-threading and synchronisation.

**Features and presumptions:**
- Each thread is an elevator
- All elevators start at the G floor
- Tracking of elevator direction is done in the elevator object
- Elevators do not track passenger direction; this is done in the passenger object as well as the associated requests. This simulates reality where passengers have autonomy of where they go, not the elevators
- Elevators will only move in one direction until there are no more requests
- Elevators will not stop for requests along the way (e.g. if the elevator is moving from G -> 6, it will not stop at any point from floors 2-5 to get requests)
- Elevators, however, will receive requests at the floors that they stop on, if the requests are moving in the same direction as the elevator
- The loading and unloading of passengers is done at every floor based on a passenger's destination floor matching the elevator's destination floor
- CONFIG: 3s for each elevator movement and 5s for each elevator stop; 1000s simulation, all rates and periods may be pre-configured by the user.

**Structure**
- Passengers are represented as Passenger objects
- Elevators are represented as Elevator objects, and the movement of elevators is managed by an Elevator Service
- There is a passenger floor map to track the passengers waiting on each level
- There is an elevator floor map to track the position of elevators in relation to the floors of the building
- Requests are built as objects and handled by a Request Manager
- Multithreading handled by a ThreadManager (to create threads and Elevator objects at initialisation), and a MyRunnable class for the main program logic extending the MyRunnable interface

**Program Flow**
- When the program is initialised, it pulls a pre-loaded set of requests from a .txt file and loads it into a static queue of requests
- Elevators are initialised together with the elevator floor map and an empty passenger floor map
- Requests are released into a pool of active requests every 5 seconds to simulate passengers calling for elevator
- Each time a request is released into the pool of active requests, the program will start to populate the floor map with passengers. The program only dynamically creates floors based on requests and does not presume a fixed number of floors for the building (to ensure scalability)
- Elevators will keep moving in the same direction until it has no more requests
- When there are no more requests left, the lift will stop and a request will be assigned to the lift. The presumption is that the request with the closest floor to the lift will be assigned and upward direction will be prioritised


  
