ROLES:
borrower: borrowed(Book); returned(Book).

OBJECTIVES: 
borrowed(Book).
returned(Book).

OBLIGATIONS:
borrower: returned(Book) < day(ReturnDate) | borrowed(Book), returnDate(Book, ReturnDate).
borrower: paidFine(Book) < day(FineDate) | org(viol(Agent, borrower, bel(returned(Book)), FineDate)).

RULES:
borrowed(Book, ReturnDate) :- borrowed(Book), returnDate(Book, ReturnDate).
org(viol(Agent, borrower, bel(returned(Book)), FineDate)) :- org(viol(Agent, borrower, bel(returned(Book)))), borrowed(Book, ReturnDate), FineDate is ReturnDate + 2.