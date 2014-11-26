// SCV (worker) -- SettlersInSpace.mas2j

cost("Terran Bunker", 100, 0).

canBuild(Building, X, Y) 
	:- 	cost(Building, M, G) & 
		minerals(MQ) & M <= MQ &
		gas(GQ) & G <= GQ &
		friendly(_, "Terran Command Center", Id, _, _, _, _) & 
		jia.findBuildingLocation(Id, Building, X, Y).

+gameStart <- !work.
+constructing <- -building(_).

+!doing(O) <- .print("Doing ", O).

+!work 
	:	not(gathering(_)) & 
		mineralField(Id, _, _, _, _)
	<-	gather(Id); .wait(1000); !!work.
+!work <- .wait(200); !work.
-!work <- .wait(200); !work.

+!build(Building, X, Y)
	:	cost(Building, M, G) & 
		minerals(MQ) & M <= MQ &
		gas(GQ) & G <= GQ
	<-	+building(Building); build(Building, X, Y).
+!build(Building, X, Y)
	<-	.wait(200); !build(Building, X, Y).

/** **/
+!resourcesGathered : mineralField(Id, _, _, _, _) <- gather(Id).
+gathering(_) <- +resourcesGathered.

+!defensesBuilt : B = "Terran Bunker" & canBuild(B, X, Y) <- build(B, X, Y).
+friendly(_, "Terran Bunker", _,_,_, _, _) : count("Terran Bunker", N) & N > 2 <- +defensesBuilt.
