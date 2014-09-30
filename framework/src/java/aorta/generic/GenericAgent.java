/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.generic;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AortaAgent;
import aorta.AortaBridge;
import aorta.logging.Logger;
import aorta.msg.IncomingOrganizationalMessage;
import aorta.msg.OutgoingOrganizationalMessage;
import aorta.ts.strategy.StrategyFailedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 *
 * @author asj
 */
public class GenericAgent implements AortaBridge {

    public static final Logger logger = Logger.getLogger(GenericAgent.class.getName());

    private List<Struct> capabilities;
    private AortaAgent aortaAgent;

    private Set<Struct> goals;

    private GenericMAS MAS;

    public GenericAgent(List<Struct> capabilities) {
        this.capabilities = capabilities;

        goals = new HashSet<>();
    }

    public void setAortaAgent(AortaAgent aortaAgent) {
        this.aortaAgent = aortaAgent;
        for (Struct cap : capabilities) {
            aortaAgent.getState().getExternalAgent().addCapability(cap);
        }
    }

    public void receive(String sender, Term msg) {
        if (msg instanceof Var) {
            Var v = (Var) msg;
            msg = v.getTerm();
        }
        if (msg instanceof Struct) {
            Struct struct = (Struct) msg;
            if (struct.getName().equals("om")) {
                aortaAgent.getState().getExternalAgent().receiveMessage(new IncomingOrganizationalMessage(sender, struct.getArg(0)));
            } else {
                System.out.println("[" + getName() + "] Received " + msg + " from " + sender);
            }
        }
    }

    public void setMAS(GenericMAS MAS) {
        this.MAS = MAS;
    }

    public void reason() {
        try {
            aortaAgent.newCycle();
        } catch (StrategyFailedException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        __reason();
    }

    private void __reason() {
        if (goals.size() > 0) {
            if (Math.random() < 0.4) {
                final ArrayList<Struct> goalList = new ArrayList<>(goals);
                Collections.shuffle(goalList);
                Struct goal = goalList.get(0);
                for (Struct cap : capabilities) {
                    if (cap.match(goal)) {
                        System.out.println("[" + getName() + "] DONE " + goal);
                        aortaAgent.getState().getExternalAgent().addBelief(goal);
                        aortaAgent.getState().getExternalAgent().removeGoal(goal);
                        removeGoal(goal);
                    }
                }
            } else {
                System.out.println("[" + getName() + "] SKIP.");
            }
        } else {
            System.out.println("[" + getName() + "] NOOP.");
        }
    }

    @Override
    public boolean addBelief(Term belief) {
        logger.fine("[" + aortaAgent.getName() + "] Adding belief: " + belief);
        return true;
    }

    @Override
    public boolean addReceivedBelief(String sender, Term msg) {
        logger.fine("[" + aortaAgent.getName() + "] Adding received belief: " + msg + "[" + sender + "]");
        return true;
    }

    @Override
    public boolean removeBelief(Term belief) {
        logger.fine("[" + aortaAgent.getName() + "] Removing belief: " + belief);
        return true;
    }

    @Override
    public boolean addGoal(Term goal) {
        logger.fine("[" + aortaAgent.getName() + "] Adding goal: " + goal);
        if (goal instanceof Var) {
            Var v = (Var) goal;
            goal = v.getTerm();
        }
        if (goal instanceof Struct) {
            goals.add((Struct) goal);
        } else {
            logger.warning("Goal " + goal + " not supported");
        }
        return true;
    }

    @Override
    public boolean addReceivedGoal(String sender, Term msg) {
        logger.fine("[" + aortaAgent.getName() + "] Adding received goal: " + msg + "[" + sender + "]");
        if (msg instanceof Var) {
            Var v = (Var) msg;
            msg = v.getTerm();
        }
        if (msg instanceof Struct) {
            goals.add((Struct) msg);
        } else {
            logger.warning("Goal " + msg + " not supported");
        }
        return true;
    }

    @Override
    public boolean removeGoal(Term goal) {
        logger.fine("[" + aortaAgent.getName() + "] Removing goal: " + goal);
        return goals.remove(goal);
    }

    @Override
    public void sendMessage(OutgoingOrganizationalMessage msg) {
        for (Term rcp : msg.getRecipients()) {
            MAS.send(aortaAgent.getName(), rcp.toString(), msg.getMessage());
        }
    }

    String getName() {
        return aortaAgent.getName();
    }

}
