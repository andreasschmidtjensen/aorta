wallet(500).

!bought(bike).

+!bought(Goods) : item(Agent, Goods, Price) & wallet(Amount) & Price < Amount <- !bought(Goods, Price, Agent).
+!bought(Goods, Amount, Seller) <- .print("I wanna buy ", Goods, " from ", Seller); buy(Goods, Amount, Seller).

+bought(Goods,Amount,Seller)[source(percept)] <- .print("Bought ", Goods, " from ", Seller).

+delivered(Goods,Buyer) : bought(Goods,Amount,Seller) <- !pay(Seller,Amount).