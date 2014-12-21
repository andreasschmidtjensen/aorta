!start.

+!start : .my_name(Me) <- register("Some Road", "Some Account").

+registered(Me, _, _) : .my_name(Me) <- start_auction("Ming Vase", 2000, 2).

+paid(Id) : auction(Id,Product,Seller,_,_) & .my_name(Seller) <- .print("Someone bought ", Product); deliver(Id). 
