// Agent bob in project tender.mas2j
/* Initial beliefs and rules */
capable("Skyscraper X").

/* Initial goals */

/* Plans */

+!bid(RFT, _) : not(terms(RFT,_) & conditions(RFT,_) & deadline(RFT,_,_,_)) <- .wait(1000); !bid(RFT, _).
+!bid(RFT, _) : contractor(RFT, C) <- +bid(RFT, "Bob the Builder").
+!bid(RFT, _) <- .print("bidding"); .wait(1000); !bid(RFT,_).

+rft(RFT)[source(PB)] : capable(RFT) <- +publicationBody(RFT, PB); !bid(RFT, _).

+!informationRequested(RFT, _) : publicationBody(RFT, PB) <- .send(PB, askOne, contractor(RFT, Contractor)); +informationRequested(RFT, information).
+!informationRequested(RFT, _) <- .wait(1000); !informationRequested(RFT, _).

+!submitted(RFT, _) : contractor(RFT, C) & bid(RFT, X) <- +submitted(RFT, X); .send(C, tell, bid(RFT, X)).
//+!submitted(RFT, _) <- .wait(1000); !submitted(RFT, _).

+!A <- .wait(false).

