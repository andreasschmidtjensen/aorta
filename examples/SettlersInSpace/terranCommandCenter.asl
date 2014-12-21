// Command center (and commander) in SettlersInSpace.mas2j

{ include("rules.asl") }

+!delegateTrain(Unit) : building(Unit, Bld) & link(Bld, Ag) <- .print("Building ", Unit, " at ", Ag); .send(Ag, achieve, train(Unit)).

+!workersTrained : not gameStart.
+!workersTrained : unit("Terran SCV", C) & C < 5 <- !train("Terran SCV").
+!workersTrained : unit("Terran SCV", C) & C > 4 <- +workersTrained.

// +!offensiveUnitTrained.
+!defensiveUnitTrained : U = "Terran Marine" & unit("Terran Marine", N) & N < 4 <- !delegateTrain(U); !defensiveUnitTrained.
+!defensiveUnitTrained : U = "Terran Vulture" & unit("Terran Vulture", N) & N < 2 <- !delegateTrain(U); !defensiveUnitTrained.
+!defensiveUnitTrained : unit("Terran Marine", M) & M >= 4 & unit("Terran Vulture", T) & T >= 2 <- +defensiveUnitTrained.
+!defensiveUnitTrained <- .wait(500); !defensiveUnitTrained.

+!enemyLocated : not(delegated) & not(friendly(_,"Terran Vulture",_)) <- +delegated; !!delegateTrain("Terran Vulture"); !enemyLocated.
+!enemyLocated : friendly(Name, "Terran Vulture", _) & agent(Name) <- -delegated; .print("Telling ", Name, " to achieve +enemyLocated"); .send(Name, achieve, enemyLocated); .wait("+enemyLocated").
+!enemyLocated <- .wait(200); !enemyLocated.

+gameStart : .my_name(Me) <- +link("Terran Command Center", Me).

+!train(Unit) 
	: 	cost(Unit, M) & 
		minerals(MQ) & M <= MQ
	<- train(Unit).
+!train(_).

