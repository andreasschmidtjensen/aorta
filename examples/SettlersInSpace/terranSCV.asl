// SCV (worker) -- SettlersInSpace.mas2j

cost("Terran Bunker", 100, 0).

busy :- gathering(_) |  building(_).

getBunkerLocation(X, Y) 
	:- 	minerals(MQ) & MQ >= 100 &
		jia.findBunkerLocation(X, Y).

+gameStart <- !work.
+constructing <- -building(_).

+!work 
	:	not(busy) & 
		mineralField(Id, _, _, _, _)
	<-	gather(Id); .wait(1000); !!work.
+!work <- .wait(200); !work.
-!work <- .wait(200); !work.

/** WORKER **/
+!resourcesGathered : mineralField(Id, _, _, _, _) <- gather(Id).
+gathering(_) <- +resourcesGathered.

+!defensiveStructuresBuilt 
	:	jia.findBunkerLocation(X, Y)
	<- 	+building("Terran Bunker"); !build("Terran Bunker", X, Y).
+!defensiveStructuresBuilt : unit("Terran Bunker", N) & N > 2 <- +defensiveStructuresBuilt.
+!defensiveStructuresBuilt <- !!defensiveStructuresBuilt.

+!build(B, X, Y) : minerals(M) & M >= 100 & jia.b2w(X,Y,Wx,Wy) <- move(Wx,Wy); build(B,X,Y).
+!build(B, X, Y) <- !build(B,X,Y).

+!print(X) <- .print(X).

