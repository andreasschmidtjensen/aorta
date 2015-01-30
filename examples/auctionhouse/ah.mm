ROLES:
customer: registered(Agent); bought(Item); sold(Item); paid(Item).
buyer: bid(Agent, Item); verified(Agent).
seller: auction(Agent, Item); verified(Agent).
manager: verified(Agent).

OBJECTIVES:
registered(Agent).
verified(Agent): registered(Agent).
bought(Item): bid(Agent, Item); paid(Item). 
sold(Item): auction(Agent, Item).
paid(Item).
bid(Agent, Item).
auction(Agent, Item).

DEPENDENCIES:
customer > manager: verified(Agent).

NORMS:
customer [obliged]: registered(Me) < verified(Me) | me(Me).   
customer [obliged]: paid(Item) < \+ participant(Me) | (me(Me), won(Me, Item)).
buyer [obliged]: verified(Me) < bid(Me, Item) | (me(Me), registered(Me)).
seller [obliged]: verified(Me) < auction(Me, Item) | (me(Me), registered(Me)).
manager [forbidden]: verified(Ag) < false | (badInfo(Ag), registered(Ag)).

RULES:
registered(Agent) :- registered(Agent, Address, Account).
auction(Agent,Item) :- auction(_Id, Item, Agent, _StartPrice, _EndTime).
bid(Agent,Item) :- bid(Id, Agent, _B), auction(Id, Item, _A, _SP, _ET).
won(Agent,Item) :- auction_done(Id,Agent,_B), auction(Id, Item, _A, _SP, _ET). 
