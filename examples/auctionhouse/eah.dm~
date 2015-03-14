% C = bid(bob,lamp) && participates(bob,lamp) 
% I = bid(bob,lamp) && ~bid(bob,lamp)
% B = badInfo(bob) && registered(bob)
%% ==> Dec=[~bid(bob,lamp)]
% B = ~badInfo(bob) && registered(bob)
%% ==> Dec=[bid(bob,lamp)]

Preferences:
	bid(bob,lamp) => participates(bob,lamp).

Expectations:
	% RULE: buyer=Me [obliged]: verified(Me) < bid(Me,Item) | registered(Me).
	%
	registered(bob) && bid(bob,lamp) => verified(bob).
	%~verified(bob) && registered(bob) && bid(bob,lamp) => viol(bob,buyer,obliged,verified(bob)).
	~verified(bob) && registered(bob) && bid(bob,lamp) => ~participates(bob,lamp).

	% RULE: manager [obliged]: \+ participates(Ag,Item) < true | viol(Ag,buyer,obliged,verified(Ag)).
	%
	%viol(bob,buyer,obliged,verified(bob)) => ~participates(bob,lamp).
	participates(bob,lamp) && viol(bob,buyer,obliged,verified(bob)) => viol(mike,manager,obliged,~participates(bob,lamp)).

	% RULE: manager [forbidden]: verified(Ag) < false | badInfo(Ag),registered(Ag).
	%
	badInfo(bob) && registered(bob) => ~verified(bob).
	badInfo(bob) && registered(bob) && verified(bob) => viol(mike,manager,forbidden,verified(bob)).

Impossible states:
	~registered(bob) && verified(bob).
