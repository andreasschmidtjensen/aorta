/*!start.

+!start : .my_name(Me) <- register("Some Other Road", "My Account").

+auction(Id,Name,Seller,StartPrice,EndTime) <- .print("Entering auction for ", Name); enter_auction(Id).
+participant(Id,Name) : .my_name(Name) & auction(Id,Product,_,StartPrice,_) <- .print("Bidding on ", Product); bid(Id, StartPrice + 20).
+auction_done(Id,Winner,Bid) : .my_name(Winner) & auction(Id,Product,_,_,_) <- .print("I won ", Product, "!"); pay(Id).
+delivered(Id) : auction(Id,Product,_,_,_) <- .print("I received ", Product). 
*/

want("Book").
want("DVD").

+!boughtItem(Item).


+!bid(Id, Agent).
