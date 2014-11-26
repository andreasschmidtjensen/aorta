ROLES:
commander: enemyEliminated; defensesBuilt; repairerTrained; gathererTrained.
gatherer: resourcesGathered. 
repairer: unitRepaired(Unit).
defender: defensesBuilt.
attacker: enemyEliminated; enemyLocated.
explorer: enemyLocated.
builder: defensesBuilt.

OBJECTIVES:
enemyEliminated: armyBuilt; enemyLocated; unitRepaired(Unit).
offensiveUnitTrained.
repairerTrained.
enemyLocated.
gathererTrained.
resourcesGathered: gathererTrained.
armyBuilt: resourcesGathered; offensiveUnitTrained.
unitRepaired(Unit): repairerTrained; resourcesGathered.
defensesBuilt: defensiveUnitTrained; unitRepaired(Unit).
defensiveUnitTrained.

DEPENDENCIES:
commander > gatherer: resourcesGathered.
defender > repairer: unitRepaired(Unit).
attacker > repairer: unitRepaired(Unit).
repairer > commander: resourcesGathered.
commander > defender: defensesBuilt.
commander > attacker: enemyEliminated.
commander > explorer: enemyLocated.
attacker > explorer: enemyLocated.
defender > builder: defensesBuilt.
builder > commander: resourcesGathered.

OBLIGATIONS:
attacker: enemyLocated < unit(soldier, Soldiers), unit(tank, Tanks), unit(aircraft, Aircrafts), Soldiers > 0, Tanks > 0, Aircrafts > 0; unit(soldier, Soldiers), unit(tank, Tanks), Soldiers > 0, Tanks > 0 | true.
attacker: enemyEliminated < false | spaceProvided(N, M), N = M; attacking(Attacker, Target).
builder: defensesBuilt < \+(enemy(Id, Type, X, Y)) | unit(soldier, Soldiers), unit(tank, Tanks), Soldiers > 0, Tanks > 0.
commander: gathererTrained < unit(bunker, Count), Count > 2 | true.
commander: repairerTrained < unit(bunker, Count), Count > 2 | true.
commander: enemyEliminated < false | spaceProvided(N, M), N = M; attacking(Attacker, Target).
commander: defensesBuilt < \+(enemy(Id, Type, X, Y)) | unit(soldier, Soldiers), unit(tank, Tanks), Soldiers > 0, Tanks > 0.
defender: defensesBuilt < \+(enemy(Id, Type, X, Y)) | unit(soldier, Soldiers), unit(tank, Tanks), Soldiers > 0, Tanks > 0.
explorer: enemyLocated < unit(soldier, Soldiers), unit(tank, Tanks), unit(aircraft, Aircrafts), Soldiers > 0, Tanks > 0, Aircrafts > 0; unit(soldier, Soldiers), unit(tank, Tanks), Soldiers > 0, Tanks > 0 | true.
repairer: repaired(Id) < \+(friendly(Name, Type, Id, WX, WY, BX, BY)) | repairRequired(Id, Type, HP, MaxHP).

RULES:
unit(worker, Count) :- unit("Terran SCV", Count).
unit(soldier, Count) :- unit("Terran Marine", Count).
unit(tank, Count) :- unit("Terran Siege Tank", Count).
unit(aircraft, Count) :- unit("Terran Wraith", Count).
unit(bunker, Count) :- unit("Terran Bunker", Count).
enemy(Id,commandCenter, X,Y) :- enemy(Id, "Terran Command Center", X, Y).