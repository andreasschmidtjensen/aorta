for_sale("Book", 15).
for_sale("DVD", 20).

!register.

+!register : .my_name(Me) <- register("Some Road", "Some Account").

+!participate : .my_name(Me) & not(registered(Me,_,_)) <- !register; !participate.
+!participate : .my_name(Me) & registered(Me,_,_) & for_sale(Item,Price) & not(auction(_,Item,Me,_,_)) <- !sell(Item,Price); !participate.
+!participate : not(for_sale(Item,_)) <- .print("No more items").
+!participate <- .wait(1000); !participate.

+!sell(Item,Price) <- start_auction(Item, Price, 5).

+registered(Me, _, _) : .my_name(Me) <- !participate.

+auction_done(Id,no_winner,_) : my_auction(Id) & auction(Id,Item,_,_,_) <- .print(Item, " was not sold"); leave_auction(Id).
+auction_done(Id,_,_) : auction(Id,Item,Seller,_,_) & .my_name(Seller) <- .print("My auction for ", Item, " was successful!"); leave_auction(Id).  

+delivered(Id) : auction(Id,Item,Seller,_,_) & .my_name(Seller) <- -for_sale(Item,_).
+paid(Id) : auction(Id,Product,Seller,_,_) & .my_name(Seller) & auction_done(Id,Winner,_) <- .print(Winner, " paid for ", Product); deliver(Id). 
+bid(Id,Bidder,Bid) : my_auction(Id) & auction(Id,Item,_,_,_) <- .print("Bid on ", Item, ": ", Bid, " by ", Bidder).
