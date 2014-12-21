// Agent commander in project SettlersInSpace.mas2j

cost("Terran SCV", 50).
cost("Terran Marine", 50).
cost("Terran Siege Tank", 150).
cost("Terran Wraith", 150).

building("Terran SCV", "Terran Command Center").
building("Terran Marine", "Terran Barracks").
building("Terran Siege Tank", "Terran Factory").
building("Terran Wraith", "Terran Starport").

+!train(Unit) : building(Unit, Bld) & link(Bld, Ag) <- .send(Ag, achieve, train(Unit)).

+!gathererTrained : count("Terran SCV", C) & C < 5 <- train("Terran SCV").
+!gathererTrained : count("Terran SCV", C) & C > 4 <- +gathererTrained.

/*+!repairerTrained.
+!offensiveUnitTrained.
+!defenseiveUnitTrained.*/



