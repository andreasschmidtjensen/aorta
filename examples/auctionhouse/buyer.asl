want("PH-lamp").

+want(Item) <- !bought(Item).

+!registered(Agent) <- register("Address", "Account").

+!bought(Item) 
	:	not(auction(_,Item,_,_,_)) 
	<-	.wait({+auction(Id,Item,_,_,_)});  
		enter_auction(Id);
		!bought(Item).
+!bought(Item) 
	:	auction(_,Item,_,_,_) & .my_name(Me) & not(verified(Me)) 
	<-	.wait({+verified(Me)});  
		!bought(Item).
+!bought(Item)
	:	auction(_,Item,_,_,_) & .my_name(Me) & verified(Me)
	<-	.print("Auction for ", Item, " created!"); !bid(_, Item).
		
+!bid(_, Item) : auction(Id,Item,_,SP,_) & not(bid(Id,_,_)) <- bid(Id, SP).
+!bid(_, Item) : auction(Id,Item,_,_,_) & bid(Id,_,P) <- bid(Id, P + 5).

+won(Id) : auction(Id,Item,_,_,_)  <- .print("Won ", Item).
