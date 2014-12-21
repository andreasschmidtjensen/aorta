want("Book").
want("DVD").

!start.

+!start : want(X) & .random(N) <- -want(X); +want(X, (N*35)+10 - ((N*35) mod 1)); .print("I will pay ", (N*35)+10 - ((N*35) mod 1), " for ", X); !start.
+!start.

+auction(Id,Item,Seller,Price,EndTime) : want(Item, _) <- !boughtItem(Item).

@l[atomic] // atomic, otherwise might try to register twice
+!boughtItem(Item) : .my_name(Me) & not(registered(Me,_,_)) <- !register; !boughtItem(Item).
+!boughtItem(Item) : auction_done(Id,_,_).
+!boughtItem(Item) : .my_name(Me) & auction(Id,Item,_,_,_) & not(participant(Id,Me)) <- enter_auction(Id); !boughtItem(Item).
+!boughtItem(Item) : .my_name(Me) & auction(Id,Item,_,_,_) & not(bid_high(Id,Me,_)) <- !bid(Id,Item); !boughtItem(Item).
+!boughtItem(Item) : .my_name(Me) & auction(Id,Item,_,_,_) <- .wait(1000); !boughtItem(Item).

+!register : .my_name(Me) <- register("Some Other Road", "Some Other Account").

+!bid(Id,Item) : want(Item, Price) & not(bid(Id,_,_)) & auction(Id,Item,_,StartPrice,_) & StartPrice < Price <- .print("Bidding ", StartPrice); bid(Id, StartPrice).
+!bid(Id,Item) : want(Item, Price) & not(auction_done(Id,_,_)) & bid_high(Id,_,Bid) & (Bid+2) < Price <- .print("Bidding ", Bid+2); bid(Id, Bid + 2).
+!bid(Id,Item) <- .print("Giving up on ", Item); .fail_goal(boughtItem(Item)).

+won(Id) : auction(Id,Item,_,_,_) & want(Item,X) & .random(N) & N < 0.2 <- !leave_auction(Id); -want(Item,X).
+won(Id) : auction(Id,Item,_,_,_) & want(Item,_) <- !get(Item).

+!get(Item) : auction(Id,Item,_,_,_) & not(paid(Id)) <- pay(Id); !get(Item).
+!get(Item) : auction(Id,Item,_,_,_) & not(delivered(Id)) <- .wait(1000); !get(Item).
+!get(Item) : auction(Id,Item,_,_,_) & delivered(Id) <- .print("I received ", Item); !leave_auction(Id).

+!leave_auction(Id) : .my_name(Me) & participant(Id,Me) <- leave_auction(Id).
+!leave_auction(_).

