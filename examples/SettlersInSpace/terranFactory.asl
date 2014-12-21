// TerranFactory (part of commander) in SettlersInSpace.mas2j

{ include("rules.asl") }

+gameStart <- .wait(500); !link.
+!link
	: 	friendly(Commander, "Terran Command Center", _,_,_,_,_) & .my_name(Me)
	<- 	.send(Commander, tell, link("Terran Factory", Me)).
+!link <- .wait(500); !link.
-!link <- .wait(500); !link.
	
	
+!train(Unit)[source(Ag)]
	: 	cost(Unit, M) & 
		minerals(MQ) & M <= MQ
	<- .print("Training ", Unit, " from ", Ag); train(Unit).
+!train(Unit) <- .wait(1000); !train(Unit).

