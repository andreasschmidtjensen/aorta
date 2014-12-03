ROLES:
commander:
	enemyEliminated;
	defensesBuilt;
	workersTrained;
	enemyLocated.
defender:
	defensesBuilt.
attacker:
	enemyEliminated.
explorer:
	enemyLocated.
worker:
	unitRepaired(Unit);
	resourcesGathered;
	defensiveStructuresBuilt.

OBJECTIVES:
enemyEliminated:
	armyBuilt;
	enemyLocated;
	unitRepaired(Unit).
offensiveUnitTrained.
enemyLocated.
resourcesGathered.
armyBuilt:
	resourcesGathered;
	offensiveUnitTrained.
unitRepaired(Unit):
	resourcesGathered.
defensesBuilt:
	defensiveUnitTrained;
	unitRepaired(Unit);
	defensiveStructuresBuilt.
workersTrained.
defensiveStructuresBuilt:
	workersTrained.
defensiveUnitTrained.

DEPENDENCIES:
commander > worker: resourcesGathered.
defender > worker: unitRepaired(Unit).
attacker > worker: unitRepaired(Unit).
commander > defender: defensesBuilt.
commander > attacker: enemyEliminated.
commander > explorer: enemyLocated.
attacker > explorer: enemyLocated.
defender > worker: defensiveStructuresBuilt.

OBLIGATIONS:
attacker: enemyLocated < unit(soldier, Soldiers), unit(tank, Tanks), unit(aircraft, Aircrafts), Soldiers > 0, Tanks > 0, Aircrafts > 0; unit(soldier, Soldiers), unit(tank, Tanks), Soldiers > 0, Tanks > 0 | true.
attacker: enemyEliminated < false | spaceProvided(N, M), N = M; attacking(Attacker, Target).
attacker: offensiveUnitTrained < attacking(Attacker, Target) | unit(bunker, Count), Count > 1; enemy(commandCenter, Id, Wx, Wy, Bx, By).
attacker: armyBuilt < \+(enemy(Type, Id, X, Y, Bx, By)) | unit(soldier, Soldiers), unit(tank, Tanks), unit(aircraft, Aircrafts), Soldiers > 0, Tanks > 0, Aircrafts > 0.
commander: workersTrained < unit(bunker, Count), Count > 2 | true.
commander: defensiveStructuresBuilt < unit(soldier, Soldiers), unit(tank, Tanks), unit(aircraft, Aircrafts), Soldiers > 0, Tanks > 0, Aircrafts > 0; unit(soldier, Soldiers), unit(tank, Tanks), Soldiers > 0, Tanks > 0 | unit(worker, Count), Count > 4.
commander: enemyLocated < unit(soldier, Soldiers), unit(tank, Tanks), unit(aircraft, Aircrafts), Soldiers > 0, Tanks > 0, Aircrafts > 0; unit(soldier, Soldiers), unit(tank, Tanks), Soldiers > 0, Tanks > 0 | true.
commander: enemyEliminated < false | spaceProvided(N, M), N = M; attacking(Attacker, Target).
commander: offensiveUnitTrained < attacking(Attacker, Target) | unit(bunker, Count), Count > 1; enemy(commandCenter, Id, Wx, Wy, Bx, By).
commander: armyBuilt < \+(enemy(Type, Id, X, Y, Bx, By)) | unit(soldier, Soldiers), unit(tank, Tanks), unit(aircraft, Aircrafts), Soldiers > 0, Tanks > 0, Aircrafts > 0.
commander: defensiveUnitTrained < spaceProvided(N, M), N = M | unit(bunker, Count), Count > 1; enemy(commandCenter, Id, Wx, Wy, Bx, By).
commander: defensesBuilt < \+(enemy(Type, Id, X, Y, Bx, By)) | unit(soldier, Soldiers), unit(tank, Tanks), Soldiers > 0, Tanks > 0.
defender: workersTrained < unit(bunker, Count), Count > 2 | true.
defender: defensiveStructuresBuilt < unit(soldier, Soldiers), unit(tank, Tanks), unit(aircraft, Aircrafts), Soldiers > 0, Tanks > 0, Aircrafts > 0; unit(soldier, Soldiers), unit(tank, Tanks), Soldiers > 0, Tanks > 0 | unit(worker, Count), Count > 4.
defender: defensiveUnitTrained < spaceProvided(N, M), N = M | unit(bunker, Count), Count > 1; enemy(commandCenter, Id, Wx, Wy, Bx, By).
defender: defensesBuilt < \+(enemy(Type, Id, X, Y, Bx, By)) | unit(soldier, Soldiers), unit(tank, Tanks), Soldiers > 0, Tanks > 0.
explorer: enemyLocated < unit(soldier, Soldiers), unit(tank, Tanks), unit(aircraft, Aircrafts), Soldiers > 0, Tanks > 0, Aircrafts > 0; unit(soldier, Soldiers), unit(tank, Tanks), Soldiers > 0, Tanks > 0 | true.
worker: workersTrained < unit(bunker, Count), Count > 1 | true.
worker: defensiveStructuresBuilt < unit(soldier, Soldiers), unit(tank, Tanks), unit(aircraft, Aircrafts), Soldiers > 0, Tanks > 0, Aircrafts > 0; unit(soldier, Soldiers), unit(tank, Tanks), Soldiers > 0, Tanks > 0 | unit(worker, Count), Count > 4.
worker: repaired(Id) < \+(friendly(Name, Type, Id, WX, WY, BX, BY)) | repairRequired(Id, Type, HP, MaxHP).

RULES:
unit(worker, Count) :- unit("Terran SCV", Count).
unit(soldier, Count) :- unit("Terran Marine", Count).
unit(tank, Count) :- unit("Terran Vulture", Count).
unit(aircraft, Count) :- unit("Terran Wraith", Count).
unit(bunker, Count) :- unit("Terran Bunker", Count).
enemy(commandCenter,Id, X,Y,Bx,By) :- enemy(X, Id, X,Y,Bx,By).