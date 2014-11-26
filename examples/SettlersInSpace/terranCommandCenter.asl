// Command center (and commander) in SettlersInSpace.mas2j

cost("Terran SCV", 50).
cost("Terran Marine", 50).
cost("Terran Siege Tank", 150).
cost("Terran Wraith", 150).

building("Terran SCV", "Terran Command Center").
building("Terran Marine", "Terran Barracks").
building("Terran Siege Tank", "Terran Factory").
building("Terran Wraith", "Terran Starport").

+!train(Unit) : building(Unit, Bld) & link(Bld, Ag) <- .send(Ag, achieve, train(Unit)).

+!gathererTrained : not gameStart.
+!gathererTrained : count("Terran SCV", C) & C < 5 <- train("Terran SCV").
+!gathererTrained : count("Terran SCV", C) & C > 4 <- +gathererTrained.

/*+!repairerTrained.
+!offensiveUnitTrained.
+!defenseiveUnitTrained.*/


// +gameStart : me(Me) <- .send(commander, tell, link("Terran Command Center", Me)).
+gameStart : me(Me) <- +link("Terran Command Center", Me).

+!train(Unit) 
	: 	cost(Unit, M, G, S) & 
		minerals(MQ) & M <= MQ &
		gas(GQ) & G <= GQ & 
		supply(C, Max) & C + S <= Max
	<- train(Unit).
+!train(_).

