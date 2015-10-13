package gwendolen;

import ail.mas.ActionScheduler;
import ail.mas.DefaultEnvironment;
import ail.syntax.Action;
import ail.syntax.Literal;
import ail.syntax.Predicate;
import ail.syntax.Term;
import ail.syntax.Unifier;
import ail.util.AILexception;
import ajpf.util.AJPFLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BlocksWorldEnv extends DefaultEnvironment {

	String logname = "gwendolen.blocksworld.BlocksWorldEnv";
	List<List<String>> stacks;
	
	public BlocksWorldEnv() {
		// TODO: Find out which scheduler to use
//		if (!Verify.isRunningInJPF()) {
//			RoundRobinScheduler s = new RoundRobinScheduler();
//			setScheduler(s);
//			addPerceptListener(s);
//		} else {
			ActionScheduler s = new ActionScheduler();
			setScheduler(s);
			addPerceptListener(s);
//		}
//		DefaultScheduler s = new DefaultScheduler();
//		setScheduler(s);
		
		setupSimpleStacks();
		
		AJPFLogger.info(logname, "Initial configuration: " + stacks);
		AJPFLogger.info(logname, "Initial percepts: " + getPercepts("",true).toString());
	}

	@Override
	public boolean done() {
		return true;
	}

	private void setupSimpleStacks() {
		stacks = new ArrayList<>();
		stacks.add(new ArrayList<String>(){{ add("a"); }});
		stacks.add(new ArrayList<String>(){{ add("b"); }});
	}
	
	private void setupStacks() {
		stacks = new ArrayList<>();
		int blocks = (int) ((Math.random() * 10) + 5);
		
		List<String> blockList = new ArrayList<>();
		for (int i = 0; i < blocks; i++) {
			blockList.add(String.valueOf((char)(i + 'a')));
		}
		Collections.shuffle(blockList);
		
		for (int i = 0; i < blocks / 4; i++) {
			stacks.add(new ArrayList<String>());
		}
		
		for (String b : blockList) {
			stacks.get((int) (Math.random()*stacks.size())).add(b);
		}
		
		Iterator<List<String>> it = stacks.iterator();
		while (it.hasNext()) {
			if (it.next().isEmpty()) {
				it.remove();
			}
		}
	}

	@Override
	public Unifier executeAction(String agName, Action act) throws AILexception {
		switch (act.getFunctor()) {
			case "shuffle":
				setupStacks();
				AJPFLogger.info(logname, "ACTION: " + act.toString() + " (" + stacks + ")");
				
				return new Unifier();
			case "move":
				
				Term x = act.getTerm(0);
				Term y = act.getTerm(1);
				
				if (isFree(x.toString()) && isFree(y.toString())) {
					move(x.toString(), y.toString());
				} else {
					AJPFLogger.warning(logname, "ACTION: " + act + " failed");
					throw new AILexception("One of the blocks was not free");
				}
				AJPFLogger.info(logname, "ACTION: " + act.toString() + " (" + stacks + ")");
							
				return new Unifier();
			default:
				return super.executeAction(agName, act);
		}
	}

	@Override
	public final Set<Predicate> getPercepts(String agName, boolean update) {
		Set<Predicate> result = new HashSet<>();
		
		if ("ag1".equals(agName)) {
			for (List<String> stack : stacks) {
				Literal table = new Literal("on");
				table.addTerm(new Literal(stack.get(0)));
				table.addTerm(new Literal("table"));
				result.add(table);
				for (int i = 0; i < stack.size() - 1; i++) {
					Literal lit = new Literal("on");
					lit.addTerm(new Literal(stack.get(i + 1)));
					lit.addTerm(new Literal(stack.get(i)));
					result.add(lit);
				}
			}	
		}
		
		return result;
		
	}

	private boolean isFree(String block) {
		if ("table".equals(block)) {
			return true;
		}
		for (List<String> stack : stacks) {
			if (stack.get(stack.size() - 1).equals(block)) {
				return true;
			}
		}
		return false;
	}
	
	private void move(String x, String y) {
		List<String> from = null, to = null;
		for (List<String> stack : stacks) {
			if (stack.get(stack.size() - 1).equals(x)) {
				from = stack;
			} else if (stack.get(stack.size() - 1).equals(y)) {
				to = stack;
			}
		}
		
		if ("table".equals(y)) {
			to = new ArrayList<>();
			stacks.add(to);
		}
		
		to.add(from.remove(from.size() - 1));
		
		if (from.isEmpty()) {
			stacks.remove(from);
		}
	}

}


