// Agent carol in project tender.mas2j
/* Initial beliefs and rules */
ignore(consortium(_,_)).
ignore(informationRequested(_,_)).
ignore(submitted(_,_)).

/* Initial goals */
!rft("Skyscraper X").

/* Plans */
+!rft(X) : terms(X,_) & conditions(X,_) & deadline(X,_,_,_) <- +rft(X).
+!rft(X) : true <- .wait(1000); !rft(X).

// Publication body
+!publicationBody(RFT, _) 
	:	not(requestedPB(RFT))
	<-	.all_names(L); .print(L);
		.broadcast(achieve, publicationBody(RFT)); 
		+requestedPB(RFT); 
		!publicationBody(RFT,_).
+!publicationBody(RFT, _)
	:	publicationBody(RFT, PB)[source(X)]
	<-	.print(PB, " is my publication body").
+!publicationBody(RFT, _)
	: 	requestedPB(RFT)
	<-	.wait(1000); !publicationBody(RFT,_).

// Create RFT
+!terms(RFT, _) <- +terms(RFT, terms).
+!conditions(RFT, _) <- +conditions(RFT, conditions).
+!deadline(RFT, _, _, _) <- +deadline(RFT, day(15), day(30), day(35)).

+!evaluator(RFT, _) : true <- +evaluator(RFT, e).


+bid(RFT, X)[source(S)] <- .print("Received bid for ", RFT, " for ", X, " from ", S).

+!A <- .wait(false).
