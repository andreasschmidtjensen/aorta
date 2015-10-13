cap(fightsFound).
cap(fightsStopped).
cap(finished).

rendevouz(1, "LeftHallA").
rendevouz(2, "RightHallA").

// dynamic would be better (but .substring is a jason ia and does not directly convert to a prolog predicate)
team(1,f11).
team(1,f12).
team(1,f13).
team(1,f14).
team(2,f21).
team(2,f22).
team(2,f23).
team(2,f24).

possible_fight(P) :- navpoint(_,R,_,_,[P]) & R \== "DropZone".
fight(F) :- at(Ag1,F) & at(Ag2,F) & team(T1,Ag1) & team(T2,Ag2) & T1 \== T2.
fight(F,Ag1,Ag2) :- at(Ag1,F) & at(Ag2,F) & team(T1,Ag1) & team(T2,Ag2) & T1 \== T2.

+!fightsFound : .findall(P, (possible_fight(P) & not(checked(P))), L) & .shuffle(L,S) & .member(P,S) <- !checked(P).
+!fightsFound <- +fightsFound.

+!checked(R) : checked(R).
+!checked(R) : state(traveling) <- .wait("+state(_)", 200, _); !checked(R).
+!checked(R) : not(state(traveling)) <- goTo(R); !checked(R).
+checked(R)[source(X)] : not(me(X)) <- .succeed_goal(checked(R)).
+at(R) : possible_fight(R) <- +checked(R).

+!fightsStopped : .findall(F, (fight(F) & not(stopped(F))), L) & .shuffle(L,S) & .member(F,S) <- !stopped(F).
+!fightsStopped <- +fightsStopped.

+!stopped(F) : fight(F,Ag1,Ag2) & at(F) & team(T1,Ag1) & team(T2,Ag2) & rendevouz(T1,R1) & rendevouz(T2,R2) <- .send(Ag1, achieve, at(R1)); .send(Ag2, achieve, at(R2)); .wait(500).
+!stopped(F) : state(traveling) <- .wait("+state(_)", 200, _); !stopped(F).
+!stopped(F) : fight(F) <- goTo(F); !stopped(F).
+!stopped(F) <- +stopped(F).
+stopped(F)[source(X)] : not(me(X)) <- .succeed_goal(stopped(F)).

+!finished : at("FrontDropZone") <- +finished.
+!finished : state(traveling) <- .wait("+state(_)", 200, _); !finished.
+!finished <- goTo("FrontDropZone"); !finished.

+!unblock(X) <- .print("unblocked ", X); +unblock(X).