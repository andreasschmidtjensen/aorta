// Agent terranVulture in project SettlersInSpace.mas2j


+!enemyLocated
	:	enemy(_,_,_,_,_,_) &
		friendly(_,"Terran Command Center", _, Wx,Wy,_,_)
	<- 	+enemyLocated; .print("Enemy Located; going home"); move(Wx,Wy).
+!enemyLocated 
	: 	X = 68 & Y = 15 & not(visited(X,Y)) 
	<-	.print("Looking at: (",X,",",Y,")"); 
		!explore(X,Y); 
		!enemyLocated.
		
+!explore(X,Y)
	:	buildTilePosition(Bx, By) &
		jia.distance(X,Y,Bx,By,D) & D < 10
	<-  +visited(X,Y); -moving.
+!explore(X,Y)
	:	moving
	<-	.wait(1000); !explore(X,Y).
+!explore(X,Y)
	: 	jia.b2w(X,Y,Wx,Wy)
	<-	+moving; move(Wx,Wy); !explore(X,Y).
	
+!defensesBuilt : friendly(_,"Terran Bunker", _, Wx,Wy,_,_)
	<- move(Wx,Wy).

