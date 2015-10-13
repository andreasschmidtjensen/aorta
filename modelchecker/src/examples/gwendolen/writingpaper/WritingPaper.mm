ROLES:
editor: wtitle; wabs; wsectitle; fdv; wcon; sv.
writer: wsec; wref.

OBJECTIVES:
wtitle.
wabs.
wsectitle.
fdv.
wcon.
sv.
wsec.
wref.

DEPENDENCIES:
writer > editor: fdv.
editor > writer: wsec.
editor > writer: wref.

NORMS:
editor [obliged]: wtitle < fdv | true.
editor [obliged]: wabs < fdv | true.
editor [obliged]: wsectitle < fdv | true.
editor [obliged]: fdv < (wsec; wcon; wref) | (wtitle,wabs,wsectitle).
writer [obliged]: wsec < sv | fdv.
writer [obliged]: wref < sv | fdv.
editor [obliged]: wcon < sv | fdv.
editor [obliged]: sv < false | (wsec, wref, wcon).
