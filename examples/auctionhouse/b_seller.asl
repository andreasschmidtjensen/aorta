for_sale("Book", 15).
for_sale("DVD", 20).
for_sale("Chair", 100).

+for_sale(Item,Price) <- !soldItem(Item).

+!registered(Me) : .my_name(Me) <- register("Some Road", "Some Account").

+!soldItem(Item) : .my_name(Me) & aorta.org(rea(Me,seller)) <- !auction(_,Item,Me).
+!soldItem(Item) <- .print("Not seller yet..."); .wait(1000); !soldItem(Item).

+!auction(_Id,Item,Agent) :	.my_name(Me) & registered(Me,_,_) & for_sale(Item,Price) & not(auction(_,Item,Me,Price,_)) <- .print("Selling ", Item, " for ", Price); start_auction(Item, Price, 10).
+!auction(_Id,Item,Agent) <- .wait(1000); !auction(_Id,Item,Agent).

+!delivered(Id) : auction(Id,Item,Seller,_,_) & .my_name(Seller) <-	deliver(Id); -for_sale(Item,_); +soldItem(Item).

+auction_done(Id,no_winner,_) : my_auction(Id) & auction(Id,Item,_,_,_) <- .print(Item, " was not sold"); leave_auction(Id).
+auction_done(Id,_,_) : auction(Id,Item,Seller,_,_) & .my_name(Seller) <- .print("My auction for ", Item, " was successful!"); leave_auction(Id). 

+paid(Id) : auction(Id,Product,Seller,_,_) & .my_name(Seller) & auction_done(Id,Winner,_) <- .print(Winner, " paid for ", Product). 
+bid(Id,Bidder,Bid) : my_auction(Id) & auction(Id,Item,_,_,_) <- .print("Bid on ", Item, ": ", Bid, " by ", Bidder).

