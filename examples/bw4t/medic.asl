// Agent capabilities (We should look into fetching these directly from the asl-file)
cap(injuredFound).
cap(injuredSaved).
cap(finished).

dark("RoomA2").
dark("RoomB1").

// the following are used as convenience since the BW4T scenario is not perfect for our scenario 
injured(N) :- color(N,"Blue",_).
injured(N,X) :- injured(N) & color(N,_,X).

// a room has only one neighbour
room(R) :- navpoint(_,R,_,_,[_]) & R \== "DropZone".

// finds all unvisited rooms and picks one to visit (random)
+!injuredFound : .findall(R, (room(R) & not(visited(R))), L) & .shuffle(L,S) & .member(R,S) <- !visited(R).
// the agent is done when no more rooms need to be visited
+!injuredFound <- +injuredFound.

// Plans for visiting a room
+!visited(R) : dark(R) <- +visited(R).
+!visited(R) : in(R) <- +visited(R).
+!visited(R) : state(collided) <- .concat("Front", R, FrontR); goTo(FrontR).
+!visited(R) : state(traveling) <- .wait("+state(_)", 200, _); !visited(R).
+!visited(R) : not(state(traveling)) <- goTo(R); !visited(R).

// Another agent has visited a room
+visited(R)[source(A)] : not(me(A)) & .intend(visited(R)) & at(X)<- .drop_intention(visited(R)); goTo(X).
+visited(R)[source(A)] : not(me(A)) <- .drop_intention(visited(R)).

// find all victims yet to be saved and pick one (random)
+!injuredSaved : .findall(V, (injured(V) & not(rescued(V))), L) & .shuffle(L,S) & .member(V,S) <- !rescued(V).
// the agent is done when no more victims need to be saved.
+!injuredSaved <- +injuredSaved.

// Plans for rescuing a victim
+!rescued(V) : holding(X,V) & not(me(X)) <- .drop_intention(rescued(V)).
+!rescued(V) : state(collided) & not(holding(_)) & injured(V,R) <- .concat("Front", R, FrontR); goTo(FrontR).
+!rescued(V) : state(traveling) <- .wait("+state(_)", 200, _); !rescued(V).
+!rescued(V) : injured(V) & holding(V) & in("DropZone") & color(V,C,R) <- putDown; -color(V,C,R); +rescued(V); goTo("FrontDropZone").
+!rescued(V) : injured(V) & holding(V) <- goTo("DropZone"); !rescued(V).
+!rescued(V) : injured(V) & atBlock(V) <- pickUp; !rescued(V).
+!rescued(V) : injured(V,R) & not(in(R)) <- goTo(R); !rescued(V).
+!rescued(V) : injured(V,R) & in(R) & not(color(V,_)) <- .drop_intention(rescued(V)).
+!rescued(V) : injured(V,R) & in(R) <- goToBlock(V); !rescued(V).

// Another agent has rescued a victim
+rescued(V)[source(A)] : not(me(A)) & .intend(rescued(V)) & color(V,C,R) & at(X) <- -color(V,C,R); .drop_intention(rescued(V)); goTo(X).
+rescued(V)[source(A)] : not(me(A)) & color(V,C,R) & at(X) <- -color(V,C,R); .drop_intention(rescued(V)).

// save mental note of victim location when one is perceived
+color(N,C)[source(percept)] : in(R) & not(color(N,C,R)) <- +color(N,C,R).

// Just return to the DropZone when finished
+!finished : at("FrontDropZone") <- +finished.
+!finished : state(traveling) <- .wait("+state(_)", 200, _); !finished.
+!finished <- goTo("FrontDropZone"); !finished.