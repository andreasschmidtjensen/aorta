// Agent peter in project tender.mas2j
/* Initial beliefs and rules */
/* Initial goals */

/* Plans */
+!publicationBody(RFT)[source(S)] 	
	:	.my_name(Me)
	<-	+contractor(RFT, S);
		!!published(RFT);
		.send(S, tell, publicationBody(RFT, Me)).

+!published(RFT) 
	:	rft(RFT) & 
		terms(RFT, Terms) & 
		conditions(RFT, Conditions) &
		deadline(RFT, BidDeadline, EvaluationDeadline, DecisionDeadline)
	<-	.broadcast(tell, terms(RFT, Terms));
		.broadcast(tell, conditions(RFT, Conditions));
		.broadcast(tell, deadline(RFT, BidDeadline, EvaluationDeadline, DecisionDeadline));
		.broadcast(tell, rft(RFT));
		+published(RFT). 
+!published(RFT)
	<- .wait(1000); !published(RFT).
	
