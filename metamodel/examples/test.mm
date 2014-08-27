ROLES:
roleOne: obj(One); obj(Two).
role2: obj(Two).

%test

OBJECTIVES:
obj(One): obj(Three).
obj(Two).

DEPENDENCIES:
roleOne > role2: obj(Two).

OBLIGATIONS:
seller: delivered(Goods, Buyer) < day(Deadline) | sold(Goods, Buyer, Day), deadline(Day,3,Deadline).

RULES:
deadline(Now,Days,Deadline) :- Deadline is Now + Days.
bought(G,A,S) :- bought(G,A,S,_).