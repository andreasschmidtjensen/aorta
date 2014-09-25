// Agent peter in project tender.mas2j
/* Initial beliefs and rules */
/* Initial goals */

/* Plans */
+!publicationBody(A)[source(S)] 	
	:	.my_name(Me)
	<-	.send(S, tell, publicationBody(A, Me)); 
		!published(A).

+!published(RFT) 
	:	rft(RFT) & 
		terms(RFT, Terms) & 
		conditions(RFT, Conditions) &
		deadline(RFT, BidDeadline, EvaluationDeadline, DecisionDeadline)
	<-	.broadcast(tell, rft(RFT));
		.broadcast(tell, terms(RFT, Terms));
		.broadcast(tell, conditions(RFT, Conditions));
		.broadcast(tell, deadline(RFT, BidDeadline, EvaluationDeadline, DecisionDeadline));
		+published(RFT). 
