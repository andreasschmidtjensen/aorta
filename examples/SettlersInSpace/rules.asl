cost("Terran SCV", 50).
cost("Terran Marine", 50).
cost("Terran Vulture", 75).
cost("Terran Wraith", 150).

building("Terran SCV", "Terran Command Center").
building("Terran Marine", "Terran Barracks").
building("Terran Vulture", "Terran Factory").
building("Terran Wraith", "Terran Starport").

friendly(Name,Type,Id) :- friendly(Name,Type,Id,_,_,_,_).

