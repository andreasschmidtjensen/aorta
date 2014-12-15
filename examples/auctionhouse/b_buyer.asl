want("Book").
want("DVD").

+want(X) : .random(N) <- -want(X); +want(X, (N*35)+10 - ((N*35) mod 1)); .print("I will pay ", (N*35)+10 - ((N*35) mod 1), " for ", X).

+!registered(_) : .my_name(Me) <- register("Some Other Road", "Some Other Account").

+auction(Id,Item,Seller,Price,EndTime) : want(Item, _) <- !boughtItem(Item).

+!boughtItem(Item) : .my_name(Me) & auction(Id,Item,_,_,_) & not(participant(Id,Me)) <- enter_auction(Id); !boughtItem(Item).
+!boughtItem(Item) : .my_name(Me) & auction(Id,Item,_,_,_) & not(bid_high(Id,Me,_)) <- !bid(Id,Me); !boughtItem(Item).
+!boughtItem(Item).

+!bid(Id,_) : want(Item, Price) & not(bid(Id,_,_)) & auction(Id,Item,_,StartPrice,_) & StartPrice <= Price <- .print("Bidding ", StartPrice); bid(Id, StartPrice).
+!bid(Id,_) : want(Item, Price) & not(auction_done(Id,_,_)) & bid_high(Id,_,Bid) & (Bid+2) <= Price <- .print("Bidding ", Bid+2); bid(Id, Bid + 2).
+!bid(Id,_) : want(Item, Price) & auction(Id,Item,_,StartPrice,_)<- .print("Giving up on ", Item); .fail_goal(boughtItem(Item)).

+won(Id) : auction(Id,Item,_,_,_) & want(Item,X) & .random(N) & N < 0.2 <- .print("Leaving auction"); !leave_auction(Id).

+!paid(Id) : won(Id) <- pay(Id).

+delivered(Id) : won(Id) & auction(Id,Item,_,_,_) & want(Item,X) <- .print("I received ", Item); !leave_auction(Id); -want(Item,X); +boughtItem(Item).

@l[atomic]
+!leave_auction(Id) : .my_name(Me) & participant(Id,Me) <- leave_auction(Id).
+!leave_auction(_).
