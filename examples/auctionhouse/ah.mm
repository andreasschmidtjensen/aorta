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
manager > seller: registered(Me).
manager > buyer: registered(Me).

% TODO: Deadlines should not be false --- sanctioned(Agent) represents what?
OBLIGATIONS:
customer: paid(Id) < \+ participant(Id, Me) | won(Id), me(Me).
customer: delivered(Id) < \+ participant(Id, Me) | paid(Id), me(Me).
buyer: verified(Me) < bid(Id, Me) | registered(Me), me(Me).
seller: verified(Me) < auction(Id, Item, Me) | registered(Me), me(Me).
manager: sanctioned(Agent) < false | viol(Agent, buyer, verified(Agent)).
manager: sanctioned(Agent) < false | viol(Agent, seller, verified(Agent)).
manager: \+ participant(Id, Ag) < done(Id) | bid_error(Id, Ag).
manager: \+ auction(Id, Item, Ag) < done(Id) | \+ verified(Ag).
manager: participant(Id, Ag) < false | auction_done(Id,Ag,Bid), \+ paid(Id), \+ participant(Id, Ag).
manager: participant(Id, Ag) < false | auction(Id, Item, Ag, SP, ET), auction_done(Id,Ag,Bid), paid(Id), \+ delivered(Id), \+ participant(Id, Ag).

RULES:
%counts-as
registered(Agent) :- registered(Agent, _, _).
auction(Id, Item, Agent) :- auction(Id, Item, Agent, _, _).
bid(Id, Agent) :- bid(Id, Agent, _).

