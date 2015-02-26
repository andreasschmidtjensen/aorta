ROLES:
customer: registered(Agent); bought(Item); sold(Item); paidFor(Item).
buyer: bid_done(Agent, Item); verified(Agent).
seller: auction_created(Agent, Item); verified(Agent).
manager: verified(Agent).

OBJECTIVES:
registered(Agent).
verified(Agent): registered(Agent).
bought(Item): bid_done(Agent, Item); paid(Item). 
sold(Item): auction_created(Agent, Item).
paidFor(Item).
bid_done(Agent, Item).
auction_created(Agent, Item).

DEPENDENCIES:
customer > manager: verified(Agent).

NORMS:
customer=Me [obliged]: registered(Me) < verified(Me) | agent(Me).   
customer=Me [obliged]: paidFor(Item) < ~participates(Me,Item) | won(Me, Item).
buyer=Me [obliged]: verified(Me) < bid_done(Me, Item) | registered(Me).
seller=Me [obliged]: verified(Me) < auction_created(Me, Item) | registered(Me).
manager [forbidden]: verified(Agent) < false | badInfo(Agent).