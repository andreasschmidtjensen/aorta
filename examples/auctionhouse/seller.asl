have("PH-lamp").

+have(Item) <- !sold(Item).

+!registered(_) <- register("Address", "Account").

+!sold(Item) 
	: .my_name(Me) & verified(Me)
	<- !auction(Me,Item).
+!sold(Item) <- .wait(1000); !sold(Item).

+!auction(Agent,Item) 
	:	.my_name(Me) & 
		registered(Me,_,_) & 
		have(Item) & 
		not(auction(_,Item,Me,_,_)) &
		Price = 10
	<- 	.print("Selling ", Item, " for ", Price); 
		start_auction(Item, Price, 10).
+!auction(Agent,Item) <- .wait(1000); !auction(Agent,Item).

// info
+auction_done(Id,no_winner,_) : my_auction(Id) & auction(Id,Item,_,_,_) <- .print(Item, " was not sold").
+auction_done(Id,_,_) : auction(Id,Item,Seller,_,_) & .my_name(Seller) <- .print("My auction for ", Item, " was successful!"). 

+paid(Id) : auction(Id,Product,Seller,_,_) & .my_name(Seller) & auction_done(Id,Winner,_) <- .print(Winner, " paid for ", Product). 
+bid(Id,Bidder,Bid) : my_auction(Id) & auction(Id,Item,_,_,_) <- .print("Bid on ", Item, ": ", Bid, " by ", Bidder).

