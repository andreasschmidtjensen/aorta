cl([], []).
cl([O|Os], Out) :- org(obj(O, SOs)), cl(SOs, SOut), cl(Os, OOut), append([O|SOut], OOut, Out).

select(X, [X|Tail], Tail).
select(Elem, [Head|Tail], [Head|Rest]) :- select(Elem, Tail, Rest).