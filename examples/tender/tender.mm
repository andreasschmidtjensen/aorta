ROLES:
bidder:
	informationRequested(BidId,Request);
	consortium(BidId,Partners);
	bid(BidId,Details);
	submitted(RFTId,BidId);
	biddersInformed(RFTId);
	contractSigned(ContractId).
contractor:
	publicationBody(RFTId,PB);
	terms(RFTId,Terms);
	conditions(RFTId,Conditions);
	deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline);
	evaluator(RFTId,Evaluator);
	bestTender(RFTId,BidId);
	won(BidId);
	lost(BidId);
	biddersInformed(RFTId);
	contract(ContractId,RFTId,BidId);
	contractSigned(ContractId);
	rft(RFTId).
evaluator:
	complianceChecked(RFTId,BidId);
	evaluation(BidId,Evaluation);
	bestTender(RFTId,BidId).
publicationBody:
	published(RFTId).
consortiumPartner:
	submitted(RFTId,BidId).

OBJECTIVES:
published(RFTId):
	rft(RFTId);
	publicationBody(RFTId,PB).
contractSigned(ContractId):
	contract(ContractId,RFTId,BidId);
	submitted(RFTId,BidId);
	bestTender(RFTId,BidId).
rft(RFTId):
	terms(RFTId,Terms);
	conditions(RFTId,Conditions);
	deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline).
contract(ContractId,RFTId,BidId).
publicationBody(RFTId,PB).
terms(RFTId,Terms).
conditions(RFTId,Conditions).
deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline).
submitted(RFTId,BidId):
	informationRequested(BidId,Request);
	consortium(BidId,Partners);
	bid(BidId,Details).
informationRequested(BidId,Request).
consortium(BidId,Partners).
bid(BidId,Details).
bestTender(RFTId,BidId):
	evaluator(RFTId,Evaluator);
	complianceChecked(RFTId,BidId);
	evaluation(BidId,Evaluation);
	biddersInformed(RFTId).
evaluator(RFTId,Evaluator).
complianceChecked(RFTId,BidId).
evaluation(BidId,Evaluation).
won(BidId).
lost(BidId).
biddersInformed(RFTId):
	won(BidId);
	lost(BidId).

DEPENDENCIES:
contractor > evaluator: bestTender(RFTId,BidId).
contractor > publicationBody: published(RFTId).
contractor > bidder: submitted(RFTId,BidId).
bidder > contractor: published(RFTId).
bidder > consortiumPartner: consortium(BidId,Partners).
publicationBody > contractor: conditions(RFTId,Conditions).
publicationBody > contractor: deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline).
publicationBody > contractor: rft(RFTId).
publicationBody > contractor: terms(RFTId,Terms).

OBLIGATIONS:
bidder: bel(informationRequested(BidId,Request)) < bel(bid(BidId,Details)) | true.
bidder: bel(consortium(BidId,Partners)) < bel(bid(BidId,Details)) | true.
bidder: bel(bid(BidId,Details)) < bel(submitted(RFTId,BidId)) | bel(consortium(BidId,Partners)),bel(informationRequested(BidId,Request)).
bidder: bel(submitted(RFTId,BidId)) < bel(complianceChecked(RFTId,BidId)) | bel(bid(BidId,Details)).
bidder: bel(biddersInformed(RFTId)) < bel(contract(ContractId,RFTId,BidId)) | bel(lost(BidId)),bel(won(BidId)).
bidder: bel(contractSigned(ContractId)) < false | bel(contract(ContractId,RFTId,BidId)).
bidder: bel(submitted(RFTId,BidId)) < bel(deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline)),bel(time(BidDeadline)) | bel(published(RFTId)).
consortiumPartner: bel(submitted(RFTId,BidId)) < bel(complianceChecked(RFTId,BidId)) | bel(bid(BidId,Details)).
contractor: bel(publicationBody(RFTId,PB)) < bel(terms(RFTId,Terms));bel(conditions(RFTId,Conditions));bel(deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline)) | goal(rft(RFTId)).
contractor: bel(evaluator(RFTId,Evaluator)) < bel(complianceChecked(RFTId,BidId)) | goal(rft(RFTId)).
contractor: bel(conditions(RFTId,Conditions)) < bel(rft(RFTId)),bel(terms(RFTId,Terms)),bel(conditions(RFTId,Conditions)),bel(deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline)) | bel(publicationBody(RFTId,PB)).
contractor: bel(terms(RFTId,Terms)) < bel(rft(RFTId)),bel(terms(RFTId,Terms)),bel(conditions(RFTId,Conditions)),bel(deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline)) | bel(publicationBody(RFTId,PB)).
contractor: bel(deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline)) < bel(rft(RFTId)),bel(terms(RFTId,Terms)),bel(conditions(RFTId,Conditions)),bel(deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline)) | bel(publicationBody(RFTId,PB)).
contractor: bel(rft(RFTId)) < bel(published(RFTId)) | bel(deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline)),bel(terms(RFTId,Terms)),bel(conditions(RFTId,Conditions)).
contractor: bel(bestTender(RFTId,BidId)) < bel(lost(BidId));bel(won(BidId)) | bel(evaluation(BidId,Evaluation)).
contractor: bel(won(BidId)) < bel(biddersInformed(RFTId)) | bel(bestTender(RFTId,BidId)).
contractor: bel(lost(BidId)) < bel(biddersInformed(RFTId)) | bel(bestTender(RFTId,BidId)).
contractor: bel(biddersInformed(RFTId)) < bel(contract(ContractId,RFTId,BidId)) | bel(lost(BidId)),bel(won(BidId)).
contractor: bel(contract(ContractId,RFTId,BidId)) < bel(contractSigned(ContractId)) | bel(biddersInformed(RFTId)).
contractor: bel(contractSigned(ContractId)) < false | bel(contract(ContractId,RFTId,BidId)).
contractor: findall(BidId,bel(bid(RFTId,BidId)),List),length(List,Length), Length > 2 < bel(deadline(RFTId,Deadline)),bel(time(Deadline)) | bel(published(RFTId)).
contractor: bel(me(Me)),\+(org(rea(Me,bidder))),\+(org(rea(Me,consortiumPartner))) < false | bel(rft(RFTId)).
evaluator: bel(complianceChecked(RFTId,BidId)) < bel(evaluation(BidId,Evaluation)) | bel(submitted(RFTId,BidId)),bel(evaluator(RFTId,Evaluator)),bel(published(RFTId)).
evaluator: bel(evaluation(BidId,Evaluation)) < bel(bestTender(RFTId,BidId)) | bel(complianceChecked(RFTId,BidId)).
evaluator: bel(bestTender(RFTId,BidId)) < bel(lost(BidId));bel(won(BidId)) | bel(evaluation(BidId,Evaluation)).
evaluator: bel(me(Me)),\+(org(rea(Me,bidder))),\+(org(rea(Me,consortiumPartner))) < false | bel(rft(RFTId)).
evaluator: bel(evaluation(BidId,Evaluation)) < bel(deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline)),bel(time(EvaluationDeadline)) | bel(deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline)),bel(time(BidDeadline)),bel(bid(RFTId,BidId)).
publicationBody: bel(published(RFTId)) < bel(complianceChecked(RFTId,BidId)) | bel(rft(RFTId)),bel(terms(RFTId,Terms)),bel(conditions(RFTId,Conditions)),bel(deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline)).
