ROLES:
customer:
	boughtItem(Item);
	soldItem(Item);
	registered(Agent);
	paid(Id);
	delivered(Id).
buyer:
	bid(Id,Agent).
seller:
	auction(Id,Item,Agent).
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
manager > customer: registered(Agent).
seller > buyer: bid(Id, Agent).
buyer > seller: auction(Id, Item, Agent).
customer > customer: delivered(Id).
customer > customer: paid(Id).

OBLIGATIONS:
% not really needed, but does not matter
buyer: registered(Agent) < verified(Agent) | true.
customer: registered(Agent) < verified(Agent) | true.
buyer: bid(Id, Agent) < paid(Id) | verified(Agent).
customer: boughtItem(Item) < false | delivered(Id).
customer: soldItem(Item) < paid(Id) | auction(Id, Item, Agent).
manager: registered(Agent) < verified(Agent) | true.
seller: registered(Agent) < verified(Agent) | true.
seller: auction(Id, Item, Agent) < auction_done(Id, Winner, Bid) | verified(Agent).

% wrong
customer: verified(Agent) < bid(Id, Agent); auction(Id, Item, Agent) | registered(Agent).
customer: bid(Id, Agent) < paid(Id) | verified(Agent).
customer: auction(Id, Item, Agent) < auction_done(Id, Winner, Bid) | verified(Agent).

% makes sense
manager: verified(Agent) < bid(Id, Agent); auction(Id, Item, Agent) | registered(Agent).
buyer: verified(Agent) < bid(Id, Agent); auction(Id, Item, Agent) | registered(Agent). 
seller: verified(Agent) < bid(Id, Agent); auction(Id, Item, Agent) | registered(Agent).

% should be altered
customer: paid(Id) < delivered(Id) | bid(Id, Agent), auction_done(Id, Winner, Bid). %Winner=Agent
customer: delivered(Id) < \+(participant(Id, Agent)) | paid(Id). % winner of auction is Agent













