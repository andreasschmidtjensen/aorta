+!verified(Agent) : not(badInfo(Agent)) & registered(Agent,_,_) <- .print("Verifying ", Agent); verify(Agent).

+!banned(Agent) <- +handled(Agent).
