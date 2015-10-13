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
contractSigned(ContractId):
	contract(ContractId,RFTId,BidId);
	submitted(RFTId,BidId);
	bestTender(RFTId,BidId).
contract(ContractId,RFTId,BidId).
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
biddersInformed(RFTId):
	won(BidId);
	lost(BidId).
won(BidId).
lost(BidId).
published(RFTId):
	rft(RFTId);
	publicationBody(RFTId,PB).
publicationBody(RFTId,PB).
rft(RFTId):
	terms(RFTId,Terms);
	conditions(RFTId,Conditions);
	deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline).
terms(RFTId,Terms).
conditions(RFTId,Conditions).
deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline).

DEPENDENCIES:
contractor > evaluator: bestTender(RFTId,BidId).
contractor > publicationBody: published(RFTId).
contractor > bidder: submitted(RFTId,BidId).
bidder > contractor: published(RFTId).
bidder > consortiumPartner: consortium(BidId,Partners).

OBLIGATIONS:
bidder: informationRequested(BidId,Request) < bid(BidId,Details) | true.
bidder: consortium(BidId,Partners) < bid(BidId,Details) | true.
bidder: bid(BidId,Details) < submitted(RFTId,BidId) | consortium(BidId,Partners),informationRequested(BidId,Request).
bidder: submitted(RFTId,BidId) < complianceChecked(RFTId,BidId) | bid(BidId,Details).
bidder: biddersInformed(RFTId) < contract(ContractId,RFTId,BidId) | lost(BidId),won(BidId).
bidder: contractSigned(ContractId) < false | contract(ContractId,RFTId,BidId).
bidder: submitted(RFTId,BidId) < deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline),time(BidDeadline) | published(RFTId).
consortiumPartner: submitted(RFTId,BidId) < complianceChecked(RFTId,BidId) | bid(BidId,Details).
contractor: publicationBody(RFTId,PB) < conditions(RFTId,Conditions);terms(RFTId,Terms);deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline) | goal(rft(RFTId)).
contractor: evaluator(RFTId,Evaluator) < complianceChecked(RFTId,BidId) | goal(rft(RFTId)).
contractor: conditions(RFTId,Conditions) < rft(RFTId),terms(RFTId,Terms),conditions(RFTId,Conditions),deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline) | publicationBody(RFTId,PB).
contractor: terms(RFTId,Terms) < rft(RFTId),terms(RFTId,Terms),conditions(RFTId,Conditions),deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline) | publicationBody(RFTId,PB).
contractor: deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline) < rft(RFTId),terms(RFTId,Terms),conditions(RFTId,Conditions),deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline) | publicationBody(RFTId,PB).
contractor: rft(RFTId) < published(RFTId) | deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline),terms(RFTId,Terms),conditions(RFTId,Conditions).
contractor: bestTender(RFTId,BidId) < lost(BidId);won(BidId) | evaluation(BidId,Evaluation).
contractor: won(BidId) < biddersInformed(RFTId) | bestTender(RFTId,BidId).
contractor: lost(BidId) < biddersInformed(RFTId) | bestTender(RFTId,BidId).
contractor: biddersInformed(RFTId) < contract(ContractId,RFTId,BidId) | lost(BidId),won(BidId).
contractor: contract(ContractId,RFTId,BidId) < contractSigned(ContractId) | biddersInformed(RFTId).
contractor: contractSigned(ContractId) < false | contract(ContractId,RFTId,BidId).
contractor: findall(BidId,bid(RFTId,BidId),List),length(List,Length), Length > 2 < deadline(RFTId,Deadline),time(Deadline) | published(RFTId).
contractor: \+(org(rea(Me,bidder))) < org(rea(Me,bidder)) | me(Me), rft(RFTId).
contractor: \+(org(rea(Me,consortiumPartner))) < org(rea(Me,consortiumPartner)) | me(Me), rft(RFTId).
evaluator: complianceChecked(RFTId,BidId) < evaluation(BidId,Evaluation) | submitted(RFTId,BidId),evaluator(RFTId,Evaluator),published(RFTId).
evaluator: evaluation(BidId,Evaluation) < bestTender(RFTId,BidId) | complianceChecked(RFTId,BidId).
evaluator: bestTender(RFTId,BidId) < lost(BidId);won(BidId) | evaluation(BidId,Evaluation).
evaluator: \+(org(rea(Me,bidder))) < org(rea(Me,bidder)) | me(Me), rft(RFTId).
evaluator: \+(org(rea(Me,consortiumPartner))) < org(rea(Me,consortiumPartner)) | me(Me), rft(RFTId).
evaluator: evaluation(BidId,Evaluation) < deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline),time(EvaluationDeadline) | deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline),time(BidDeadline),bid(RFTId,BidId).
publicationBody: published(RFTId) < complianceChecked(RFTId,BidId) | rft(RFTId),terms(RFTId,Terms),conditions(RFTId,Conditions),deadline(RFTId,BidDeadline,EvaluationDeadline,DecisionDeadline).
