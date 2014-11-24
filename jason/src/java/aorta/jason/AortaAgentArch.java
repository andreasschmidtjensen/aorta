/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason;

import alice.tuprolog.Term;
import aorta.Aorta;
import aorta.AortaAgent;
import aorta.msg.IncomingOrganizationalMessage;
import aorta.ts.strategy.StrategyFailedException;
import jason.JasonException;
import jason.asSemantics.ActionExec;
import jason.asSemantics.Agent;
import jason.asSemantics.Message;
import jason.asSemantics.TransitionSystem;
import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.bb.DefaultBeliefBase;
import jason.infra.centralised.CentralisedAgArch;
import jason.infra.centralised.RunCentralisedMAS;
import jason.mas2j.ClassParameters;
import jason.runtime.Settings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asj
 */
public class AortaAgentArch extends CentralisedAgArch {

	private static final Logger logger = Logger.getLogger(AortaAgentArch.class.getName());

	private Aorta aorta;
	private AortaAgent aortaAgent;
	private AortaJasonAgent aortaJasonAgent;
	private AortaBB aortaBB;

	private ActionExec lastActionExecuted;

	@Override
	public void createArchs(List<String> agArchClasses, String agClass, ClassParameters bbPars, String asSrc, Settings stts, RunCentralisedMAS masRunner) throws JasonException {
		try {
			this.masRunner = masRunner;
			if (agClass.equals(Agent.class.getName())) {
				agClass = AortaJasonAgent.class.getName();
			} else {
				try {
					if (!AortaJasonAgent.class.isAssignableFrom(Class.forName(agClass))) {
						logger.log(Level.SEVERE, "WARNING: " + agClass + " does not extend " + AortaJasonAgent.class.getName() + "!");
					}
				} catch (ClassNotFoundException ex) {
				}
			}

			if (bbPars.getClassName().equals(DefaultBeliefBase.class.getName())) {
				bbPars = new ClassParameters(AortaBB.class.getName());
			} else {
				try {
					if (!AortaBB.class.isAssignableFrom(Class.forName(bbPars.getClassName()))) {
						logger.log(Level.SEVERE, "WARNING: " + bbPars.getClass() + " does not extend " + AortaBB.class.getName() + "!");
					}
				} catch (ClassNotFoundException ex) {
				}
			}

			super.createArchs(agArchClasses, agClass, bbPars, asSrc, stts, masRunner);

			aortaJasonAgent = (AortaJasonAgent) getTS().getAg();
			aortaBB = (AortaBB) aortaJasonAgent.getBB();
		} catch (JasonException e) {
			running = false;
			throw e;
		}
	}

	@Override
	public void reasoningCycleStarting() {
		if (aortaAgent != null) {
			try {
				aortaAgent.newCycle();
			} catch (StrategyFailedException ex) {
				logger.log(Level.SEVERE, null, ex);
			}
		}
		super.reasoningCycleStarting();
	}

	public ActionExec getLastActionExecuted() {
		return lastActionExecuted;
	}

	@Override
	public void actionExecuted(ActionExec action) {
		super.actionExecuted(action);

		lastActionExecuted = action;
	}

	@Override
	public void checkMail() {
		if (aortaAgent != null) {
			List<Message> oms = new ArrayList<>();

			Collection<Message> mbox = getMBox();
			Iterator<Message> it = mbox.iterator();
			while (it.hasNext()) {
				Message im = it.next();
				if (im.getPropCont() instanceof Structure) {
					Structure s = (Structure) im.getPropCont();
					if (s.getFunctor().equals(Aorta.ORG_MESSAGE_FUNCTOR)) {
						// Aorta message, remove from mailbox and process further
						oms.add(im);
						it.remove();
					}
				}
			}

			for (Message msg : oms) {
				Term contents = TermConverter.fromLiteral((Literal) ((Structure) msg.getPropCont()).getTerm(0));
				aortaAgent.getState().getExternalAgent().receiveMessage(new IncomingOrganizationalMessage(msg.getSender(), contents));
			}
		}

		super.checkMail();
	}

	public AortaAgent getAortaAgent() {
		return aortaAgent;
	}

	public AortaJasonAgent getAortaJasonAgent() {
		return aortaJasonAgent;
	}

	public void setAortaAgent(AortaAgent aortaAgent) {
		this.aortaAgent = aortaAgent;
		aortaJasonAgent.setAortaAgent(aortaAgent);
		aortaBB.setAortaAgent(aortaAgent);
	}

	public void setAorta(Aorta aorta) {
		this.aorta = aorta;
	}

	/**
	 * RUN SPECIFICS
	 */
	private RunCentralisedMAS masRunner = RunCentralisedMAS.getRunner();
	private volatile boolean running = true;
	private final Object syncStopRun = new Object();
	private Thread myThread = null;
	private int sleepTime;

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	@Override
	public void setThread(Thread t) {
		myThread = t;
		myThread.setName(getAgName());
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public void stopAg() {
		running = false;
		if (myThread != null) {
			myThread.interrupt();
		}
		synchronized (syncStopRun) {
			masRunner.delAg(getAgName());
		}
		getTS().getAg().stopAg();
		getUserAgArch().stop(); // stops all archs
	}

	@Override
	public void run() {
		synchronized (syncStopRun) {
			TransitionSystem ts = getTS();
			while (running) {
				incCycleNumber();
				ts.reasoningCycle();
				if (sleepTime > 0) {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException ex) {
						stopAg();
					}
				}
			}
		}
		logger.fine("I finished!");
	}

}
