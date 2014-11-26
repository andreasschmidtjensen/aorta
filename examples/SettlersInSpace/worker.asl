// Agent worker in project SettlersInSpace.mas2j

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+!resourcesGathered : mineralField(Id, _, _, _, _) <- gather(Id).
+gathering(_) <- +resourcesGathered.

