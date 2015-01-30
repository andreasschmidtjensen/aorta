ROLES:
customer: boughtItem(Item); soldItem(Item); registered(Me); paid(Id); delivered(Id).
buyer: bid(Id, Me).
seller: auction(Id, Item, Me).
manager: verified(Agent).

OBJECTIVES:
boughtItem(Item): bid(Id, Me); paid(Id); delivered(Id).
soldItem: auction(Id, Item, Me); paid(Id); delivered(Id).
registered(Me).
paid(Id).
delivered(Id).
bid(Id, Me).
auction(Id, Item, Me).
verified(Agent).

DEPENDENCIES:
seller > manager: verified(Agent).
buyer > manager: verified(Agent).
manager > seller: registered(Agent).
manager > buyer: registered(Agent).

% TODO: Deadlines should not be false --- sanctioned(Agent) represents what?
OBLIGATIONS:
customer: paid(Id) < \+ participant(Id, Agent) | auction_done(Id,Agent,_).
customer: delivered(Id) < \+ participant(Id, Agent) | paid(Id), auction_done(Id,_,_), auction(Id,_,Agent).
buyer: verified(Agent) < bid(Id, Agent) | registered(Agent).
seller: verified(Agent) < auction(Id, Item, Agent) | registered(Agent).

manager: sanctioned(Agent) < false | viol(Agent, buyer, verified(Agent)).
manager: sanctioned(Agent) < false | viol(Agent, seller, verified(Agent)).
manager: \+ participant(Id, Ag) < auction_done(Id,_,_) | bid_error(Id, Ag).
manager: \+ auction(Id, Item, Ag) < auction_done(Id,_,_) | \+ verified(Ag), auction(Id,Item,Ag).
manager: participant(Id, Ag) < false | auction_done(Id,Ag,Bid), \+ paid(Id), \+ participant(Id, Ag).
manager: participant(Id, Ag) < false | auction(Id, Item, Ag, SP, ET), auction_done(Id,Ag,Bid), paid(Id), \+ delivered(Id), \+ participant(Id, Ag).

manager: rea(Agent, customer) < participant(_,Agent) | registered(Agent).
manager: rea(Agent, buyer) < bid(_,Agent) | participant(Agent).
manager: rea(Agent, seller) < auction(_,_,Agent) | registered(Agent).

RULES:
%counts-as
registered(Agent) :- registered(Agent, _, _).
auction(Id, Item, Agent) :- auction(Id, Item, Agent, _, _).
bid(Id, Agent) :- bid(Id, Agent, _).















