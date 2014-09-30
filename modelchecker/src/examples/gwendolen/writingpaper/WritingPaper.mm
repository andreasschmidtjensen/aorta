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

OBLIGATIONS:
editor: wtitle < fdv | true.
editor: wabs < fdv | true.
editor: wsectitle < fdv | true.
editor: fdv < (wsec; wcon; wref) | (wtitle,wabs,wsectitle).
writer: wsec < sv | fdv.
writer: wref < sv | fdv.
editor: wcon < sv | fdv.
editor: sv < false | (wsec, wref, wcon).
