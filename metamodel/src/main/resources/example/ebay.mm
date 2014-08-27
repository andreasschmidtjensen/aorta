ROLES:
buyer: bought(Goods, Amount, Seller); paid(Amount, Seller); canceled(Seller, Goods, Amount).
seller: sold(Goods, Buyer); delivered(Goods, Buyer).

OBJECTIVES: 
bought(Goods, Amount, Seller).
paid(Amount, Seller).
canceled(Seller, Goods, Amount).
sold(Goods, Buyer).
delivered(Goods, Buyer).

DEPENDENCIES:
seller > buyer: delivered(Goods, Buyer).
buyer > seller: paid(Amount, Seller).

OBLIGATIONS:
seller: delivered(Goods, Buyer) < day(Deadline) | (sold(Goods,Buyer,Day), deadline(Day,3,Deadline)).
buyer: paid(Amount, Seller) < day(Deadline) | (delivered(Goods,Buyer,Day), bought(Goods, Amount, Seller), deadline(Day,2,Deadline)).
buyer: canceled(Seller, Goods, Amount) < day(Deadline) | (org(viol(delivered(Goods, Buyer))), bought(Goods, Amount, Seller), day(Now), Deadline is Now+2).
buyer: paid(NewAmount, Seller) < day(Deadline) | (org(viol(paid(Amount,Seller))), NewAmount is Amount*1.10, day(Now), Deadline is Now+3).

RULES:
deadline(Now,Days,Deadline) :- Deadline is Now + Days.
bought(G,A,S) :- bought(G,A,S,_).
sold(G,B) :- sold(G,B,_).
delivered(B,G) :- delivered(B,G,_).