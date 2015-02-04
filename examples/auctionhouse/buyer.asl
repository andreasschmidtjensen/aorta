want("PH-lamp").

+want(Item) <- !bought(Item).

+!registered(Agent) <- register("Address", "Account").

+!bought(Item) 
	:	not(auction(_,Item,_,_,_)) 
	<-	.wait({+auction(Id,Item,_,_,_)});
		.print("Auction for ", Item, " created!"); 
		enter_auction(Id);
		!bought(Item).
+!bought(Item) 
	:	auction(_,Item,_,_,_) & .my_name(Me) & not(verified(Me)) 
	<-	.wait({+verified(Me)});  
		!bought(Item).
+!bought(Item)
	:	auction(_,Item,_,_,_) & .my_name(Me) & verified(Me)
	<-	!bid(_, Item).
	
+!bid(_, Item) 
	: auction(Id,Item,_,_,_) & bid_high(Id,Me,P) & .my_name(Me)
	<- .wait(1000); !bid(_, Item).
+!bid(_, Item) 
	: auction(Id,Item,_,SP,_) & not(bid_high(Id,_,_)) 
	<- .print("Bidding ", SP, " on ", Item); bid(Id, SP); !bid(_,Item).
+!bid(_, Item) 
	: auction(Id,Item,_,_,_) & bid_high(Id,_,P) 
	<- .print("Bidding ", P+5, " on ", Item); bid(Id, P + 5); !bid(_,Item).

-participant(Id, Me)
	: .my_name(Me) & auction(Id,Item,_,_,_) & .intend(bid(_,Item))
	<- .drop_intention(bid(_,Item)); .print("Giving up on ", Item).
	
+won(Id) : auction(Id,Item,_,_,_)  
	<- .drop_intention(bid(_,Item)); .print("Won ", Item); 
		+bought(Item); leave_auction(Id).
	
+!paidFor(Item)
	: auction(Id,Item,_,_,_) & won(Id)
	<- pay(Id).
