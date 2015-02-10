+!verified(Agent) 
	: not(badInfo(Agent)) & registered(Agent,_,_) 
	<- .print("Verifying ", Agent); verify(Agent).

+!banned(Agent) 
	: participant(Id,Agent) & auction(Id,Item,_,_,_) 
	<- remove_from_auction(Id,Agent); 
		.print("Removing ", Agent, " from auction for ", Item).
