An Elevator simulation exercise using multi-threading and synchronisation.

**Features and presumptions:**
- Each thread is an elevator
- All elevators start at the G floor.

**Structure**
- Passengers are represented as Passenger objects
- Elevators are represented as Elevator objects, and the movement of elevators is managed by an Elevator Service
- There is a passenger floor map to track the passengers waiting on each level
- There is an elevator floor map to track the position of elevators in relation to the floors of the building
- Requests are built as objects and handled by a Request Manager

**Program Flow**
- When the program is initialised, it pulls a pre-loaded set of requests from a .txt file and loads it into a static queue of requests
- Elevators are initialised together with the elevator floor map and an empty passenger floor map
- Requests are released into a pool of active requests every 5 seconds to simulate passengers calling for lifts
- Each time a request is released into the pool of active requests, the program will start to populate the floor map with passengers. The program only dynamically creates floors based on requests and does not presume a fixed number of floors for the building (to ensure scalability)

  
