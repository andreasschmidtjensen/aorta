ROLES:
customer:
	boughtItem(Item);
	soldItem(Item);
	registered(Agent);
	paid(Id);
	delivered(Id).
buyer:
	bid(Id,Agent);
	verified(Agent).
seller:
	auction(Id,Item,Agent);
	verified(Agent).
manager:
	verified(Agent).

OBJECTIVES:
boughtItem(Item):
	paid(Id);
	delivered(Id);
	bid(Id, Agent).
soldItem(Item):
	paid(Id);
	delivered(Id);
	auction(Id, Item, Agent).
registered(Agent).
paid(Id).
delivered(Id).
bid(Id, Agent):
	verified(Agent).
auction(Id, Item, Agent):
	verified(Agent).
verified(Agent):
	registered(Agent).

DEPENDENCIES:
customer > manager: verified(Agent).
seller > buyer: bid(Id, Agent).
buyer > seller: auction(Id, Item, Agent).
customer > seller: delivered(Id).
customer > buyer: paid(Id).

OBLIGATIONS:
buyer: verified(Agent) < bid(_, Agent) | me(Agent), registered(Agent).
customer: registered(Agent) < verified(Agent) | me(Agent).
customer: paid(Id) < \+(participant(Id, Winner)) | bid(Id, Winner), auction(Id, Item, Agent), auction_done(Id, Winner, Bid), me(Winner).
customer: delivered(Id) < \+(participant(Id, Agent)) | bid(Id, Winner), auction(Id, Item, Agent), auction_done(Id, Winner, Bid), me(Agent).
manager: verified(Agent) < bid(Id, Winner); auction(Id, Item, Agent) | me(Agent), registered(Agent).
manager: rea(Agent, customer) < participant(Id, Agent) | registered(Agent).
manager: rea(Agent, buyer) < bid(Id, Agent) | participant(Agent).
manager: rea(Agent, seller) < auction(Id, Item, Agent) | registered(Agent).
manager: \+(participant(Id, Agent)) < auction_done(Id) | viol(Agent, buyer, bel(verified(Agent))), participant(Id, Agent).
manager: \+(auction(Id, Item, Agent)) < auction_done(Id) | viol(Agent, seller, bel(verified(Agent))), auction(Id, Item, Agent).
manager: participant(Id, Agent) < false | viol(Agent, customer, bel(paid(Id))).
manager: participant(Id, Agent) < false | viol(Agent, customer, bel(delivered(Id))).
manager: \+(participant(Id, Agent)) < auction_done(Id) | bid_error(Id, Agent).
seller: verified(Agent) < auction(Id, Item, Agent) | me(Agent), registered(Agent).

RULES:
registered(Agent) :- registered(Agent, Address, Account).
auction(Id,Item,Agent) :- auction(Id, Item, Agent, StartPrice, EndTime).
bid(Id,Agent) :- bid(Id, Agent, Bid).
auction_done(Id) :- auction_done(Id, Winner, Bid).
