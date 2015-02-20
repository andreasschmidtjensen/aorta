package listener;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.annotation.JPFOption;
import gov.nasa.jpf.jvm.bytecode.FieldInstruction;
import gov.nasa.jpf.jvm.bytecode.InstanceFieldInstruction;
import gov.nasa.jpf.jvm.bytecode.InvokeInstruction;
import gov.nasa.jpf.jvm.bytecode.LockInstruction;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.ChoiceGenerator;
import gov.nasa.jpf.vm.ClassInfo;
import gov.nasa.jpf.vm.ElementInfo;
import gov.nasa.jpf.vm.Instruction;
import gov.nasa.jpf.vm.MethodInfo;
import gov.nasa.jpf.vm.StackFrame;
import gov.nasa.jpf.vm.ThreadInfo;
import gov.nasa.jpf.vm.VM;
import java.io.PrintWriter;

public class ExecTracker extends ListenerAdapter {

	@JPFOption(type = "Boolean", key = "et.print_insn", defaultValue = "true", comment = "print executed bytecode instructions")
	boolean printInsn = true;

	@JPFOption(type = "Boolean", key = "et.print_src", defaultValue = "false", comment = "print source lines")
	boolean printSrc = false;

	@JPFOption(type = "Boolean", key = "et.print_mth", defaultValue = "false", comment = "print executed method names")
	boolean printMth = false;

	@JPFOption(type = "Boolean", key = "et.skip_init", defaultValue = "true", comment = "do not log execution before entering main()")
	boolean skipInit = false;

	boolean showShared = false;
	PrintWriter out;
	String lastLine;
	MethodInfo lastMi;
	String linePrefix;
	boolean skip;
	MethodInfo miMain;

	public ExecTracker(Config config) {
		this.printInsn = config.getBoolean("et.print_insn", true);

		this.printSrc = config.getBoolean("et.print_src", false);

		this.printMth = config.getBoolean("et.print_mth", false);

		this.skipInit = config.getBoolean("et.skip_init", true);

		this.showShared = config.getBoolean("et.show_shared", true);

		if (this.skipInit) {
			this.skip = true;
		}

		this.out = new PrintWriter(System.out, true);
	}

	@Override
	public void stateRestored(Search search) {
		int id = search.getStateId();
		this.out.println("----------------------------------- [" + search.getDepth() + "] restored: " + id);
	}

	@Override
	public void searchStarted(Search search) {
		this.out.println("----------------------------------- search started");
		if (this.skipInit) {
			ThreadInfo tiCurrent = ThreadInfo.getCurrentThread();
			this.miMain = tiCurrent.getEntryMethod();

			this.out.println("      [skipping static init instructions]");
		}
	}

	@Override
	public void stateAdvanced(Search search) {
		int id = search.getStateId();

		this.out.print("----------------------------------- [" + search.getDepth() + "] forward: " + id);

		if (search.isNewState()) {
			this.out.print(" new");
		} else {
			this.out.print(" visited");
		}

		if (search.isEndState()) {
			this.out.print(" end");
		}

		this.out.println();

		this.lastLine = null;
		this.lastMi = null;
		this.linePrefix = null;
	}

	@Override
	public void stateProcessed(Search search) {
		int id = search.getStateId();
		this.out.println("----------------------------------- [" + search.getDepth() + "] done: " + id);
	}

	@Override
	public void stateBacktracked(Search search) {
		int id = search.getStateId();

		this.lastLine = null;
		this.lastMi = null;

		this.out.println("----------------------------------- [" + search.getDepth() + "] backtrack: " + id);
	}

	@Override
	public void searchFinished(Search search) {
		this.out.println("----------------------------------- search finished");
	}

	@Override
	public void gcEnd(VM vm) {
		this.out.println("\t\t # garbage collection");
	}

	@Override
	public void instructionExecuted(VM vm, ThreadInfo ti, Instruction nextInsn, Instruction executedInsn) {
		if (this.skip) {
			MethodInfo mi = executedInsn.getMethodInfo();
			if (mi == this.miMain) {
				this.skip = false;
			} else {
				return;
			}
		}

		int nNoSrc = 0;

		if (this.linePrefix == null) {
			this.linePrefix = (Integer.toString(ti.getId()) + " : ");
		}

		if (this.printSrc) {
			String line = executedInsn.getSourceLine();
			if (line != null) {
				if (nNoSrc > 0) {
					this.out.println("            [" + nNoSrc + " insn w/o sources]");
				}

				if (!line.equals(this.lastLine)) {
					this.out.print("            [");
					this.out.print(executedInsn.getFileLocation());
					this.out.print("] : ");
					this.out.println(line.trim());
				}

				nNoSrc = 0;
			} else {
				nNoSrc++;
			}

			this.lastLine = line;
		}

		if (this.printInsn) {
			if (this.printMth) {
				MethodInfo mi = executedInsn.getMethodInfo();
				if (mi != this.lastMi) {
					ClassInfo mci = mi.getClassInfo();
					this.out.print("      ");
					if (mci != null) {
						this.out.print(mci.getName());
						this.out.print(".");
					}
					this.out.println(mi.getUniqueName());
					this.lastMi = mi;
				}
			}

			this.out.print(this.linePrefix);

			this.out.print('[');
			this.out.print(executedInsn.getPosition());
			this.out.print("] ");

			this.out.print(executedInsn);

			if ((executedInsn instanceof InvokeInstruction)) {
				MethodInfo callee = ((InvokeInstruction) executedInsn).getInvokedMethod();
				if ((callee != null) && (callee.isMJI())) {
					this.out.print(" [native]");
				}
			} else if ((executedInsn instanceof FieldInstruction)) {
				this.out.print(" ");
				if ((executedInsn instanceof InstanceFieldInstruction)) {
					InstanceFieldInstruction iinsn = (InstanceFieldInstruction) executedInsn;
					this.out.print(iinsn.getId(iinsn.getLastElementInfo()));
				} else {
					this.out.print(((FieldInstruction) executedInsn).getVariableId());
				}
			} else if ((executedInsn instanceof LockInstruction)) {
				LockInstruction lockInsn = (LockInstruction) executedInsn;
				int lockRef = lockInsn.getLastLockRef();

				this.out.print(" ");
				this.out.print(ti.getElementInfo(lockRef));
			}
			this.out.println();
		}
	}

	@Override
	public void threadStarted(VM vm, ThreadInfo ti) {
		this.out.println("\t\t # thread started: " + ti.getName() + " index: " + ti.getId());
	}

	@Override
	public void threadTerminated(VM vm, ThreadInfo ti) {
		this.out.println("\t\t # thread terminated: " + ti.getName() + " index: " + ti.getId());
	}

	@Override
	public void exceptionThrown(VM vm, ThreadInfo ti, ElementInfo ei) {
		MethodInfo mi = ti.getTopFrameMethodInfo();
		for (StackFrame sf : ti.getStack()) {
			out.println("---" + sf.getClassName() + ":" + sf.getMethodName() + ":" + sf.getLine());
		}
		out.println("\t\t\t\t # exception: " + ei + " in " + mi);
	}

	@Override
	public void choiceGeneratorAdvanced(VM vm, ChoiceGenerator<?> currentCG) {
		this.out.println("\t\t # choice: " + currentCG);
	}

	@Override
	public void objectExposed(VM vm, ThreadInfo currentThread, ElementInfo sharedObject, ElementInfo exposedObject) {
		if (this.showShared) {
			this.out.println("\t\t # exposed " + exposedObject + " through shared " + sharedObject);
		}
	}

	@Override
	public void objectShared(VM vm, ThreadInfo currentThread, ElementInfo sharedObject) {
		if (this.showShared) {
			this.out.println("\t\t # shared " + sharedObject);
		}
	}

	void filterArgs(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if ((args[i] == null)
					|| (!args[i].equals("-print-lines"))) {
				continue;
			}
			this.printSrc = true;
			args[i] = null;
		}
	}
}
