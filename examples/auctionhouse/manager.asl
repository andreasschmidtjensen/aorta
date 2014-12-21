+!verified(Agent) : .random(N) & N < 0.33 <- .print("Not verifying ", Agent); +wrong_details(Agent).
+!verified(Agent) : registered(Agent,_,_) <- .print("Verifying ", Agent); verify(Agent).

+!makeEnact(Agent,Role) : aorta.org(role(Role,_)) <- .send(Agent, achieve, rea(Role)); +triedEnact(Agent, Role).
+!ban(Agent) <- +handled(Agent).
+!participate(Id,Agent) : auction(Id,Item,_,_,_) <- .print("Putting ", Agent, " back to auction for ", Item); add_to_auction(Id, Agent).
