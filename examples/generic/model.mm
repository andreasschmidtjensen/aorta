ROLES:
roleA: eat; run.
roleB: sleep.

OBJECTIVES:
eat.
sleep.
run.

DEPENDENCIES:
roleA > roleB: sleep.

OBLIGATIONS:
roleA: run < eat | true.
roleA: eat < run | goal(run).
roleA: sleep < false | eat, run.