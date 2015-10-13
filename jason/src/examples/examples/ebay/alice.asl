+buys(Goods, Amount, Buyer) <- !sold(Goods, Buyer).

+!sold(Goods, Buyer) <- sell(Goods, Buyer).

+sold(Goods, Buyer, Day) <- !delivered(Goods, Buyer).

+!delivered(Goods, Buyer) /*: .random(N) & N < 0.25*/ <- deliver(Goods, Buyer).
/*+!delivered(Goods, Buyer) <- .wait(2000); !delivered(Goods, Buyer).*/