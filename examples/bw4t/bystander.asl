!myteam.

+!myteam : ownName(X) & .substring("F1", X) <- +team(1).
+!myteam : ownName(X) & .substring("F2", X) <- +team(2).

+team(X) <- .print("I am team ", X).

+!at(X) <- goTo(X).