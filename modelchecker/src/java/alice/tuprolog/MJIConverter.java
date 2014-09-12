/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alice.tuprolog;

import gov.nasa.jpf.vm.ElementInfo;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author asj
 */
public class MJIConverter {
	
	public static int createTerm(MJIEnv env, Term t) {
		int tRef = MJIEnv.NULL;
		if (t instanceof alice.tuprolog.Double) {
			tRef = env.newObject(alice.tuprolog.Double.class.getName());
			env.setDoubleField(tRef, "value", ((alice.tuprolog.Double)t).doubleValue());
		} else if (t instanceof alice.tuprolog.Float) {
			tRef = env.newObject(alice.tuprolog.Float.class.getName());		
			env.setDoubleField(tRef, "value", ((alice.tuprolog.Float)t).floatValue());						
		} else if (t instanceof Int) {
			tRef = env.newObject(Int.class.getName());
			env.setDoubleField(tRef, "value", ((Int)t).intValue());						
		} else if (t instanceof alice.tuprolog.Long) {
			tRef = env.newObject(alice.tuprolog.Long.class.getName());
			env.setDoubleField(tRef, "value", ((alice.tuprolog.Long)t).longValue());						
		} else if (t instanceof Struct) {
			tRef = env.newObject(Struct.class.getName());
			Struct s = (Struct) t;
			env.setReferenceField(tRef, "name", env.newString(s.getName()));
			int argRef = env.newObjectArray(Term[].class.getName(), s.getArity());
			for (int i = 0; i < s.getArity(); i++) {
				Term subTerm = s.getTerm(i);
				int stRef = createTerm(env, subTerm);
				env.setReferenceArrayElement(argRef, i, stRef);
			}
			env.setReferenceField(tRef, "name", env.newString(s.getName())); 
			env.setReferenceField(tRef, "arg", argRef);
			env.setIntField(tRef, "arity", s.getArity()); 
			env.setReferenceField(tRef, "predicateIndicator", env.newString(s.getPredicateIndicator()));
			env.setReferenceField(tRef, "primitive", env.newString(s.getName())); 
			env.setBooleanField(tRef, "resolved", false); // XXX:?

		} else if (t instanceof Var) {
			tRef = env.newObject(Var.class.getName());
			Var v = (Var) t;
			int cnRef = env.newObject(StringBuilder.class.getName());
			StringBuilder completeName = new StringBuilder(v.getName());
			int linkRef = createTerm(env, v.getLink());
			int valueRef = env.newCharArray(completeName.capacity());
			for (int i = 0; i < completeName.length(); i++) {
				env.setCharArrayElement(valueRef, i, completeName.charAt(i));
			}
			env.setReferenceField(cnRef, "value", valueRef);
			env.setIntField(cnRef, "count", completeName.length());						

			env.setReferenceField(tRef, "name", env.newString(v.getOriginalName()));
			env.setReferenceField(tRef, "completeName", cnRef);
			env.setReferenceField(tRef, "link", linkRef);
			env.setLongField(tRef, "timestamp", 0); // XXX: This is probably wrong
			env.setIntField(tRef, "id", -1); // XXX: This one too
		}
		return tRef;
	}

	public static Term getTerm(MJIEnv env, ElementInfo ei) {
		String termClass = ei.getClassInfo().getName();
		Term term = null;
		switch (termClass) {
			case "alice.tuprolog.Double":
				term = new alice.tuprolog.Double(ei.getDoubleField("value"));
				break;
			case "alice.tuprolog.Float":
				term = new alice.tuprolog.Float(ei.getFloatField("value"));
				break;
			case "alice.tuprolog.Int":
				term = new Int(ei.getIntField("value"));
				break;
			case "alice.tuprolog.Long":
				term = new alice.tuprolog.Long(ei.getLongField("value"));
				break;
			case "alice.tuprolog.Struct":
				ElementInfo argInfo = (ElementInfo) ei.getFieldValueObject("arg");

				if (argInfo != null) {
					Term[] args = new Term[argInfo.arrayLength()];
					int[] refArgs = argInfo.asReferenceArray();
					for (int i = 0; i < args.length; i++) {
						args[i] = getTerm(env, env.getElementInfo(refArgs[i]));
					}
					term = new Struct(ei.getStringField("name"), args);
				} else {
					term = new Struct(ei.getStringField("name"));
				}
				break;
			case "alice.tuprolog.Var":
				Var var = new Var(ei.getStringField("name"));
				if (ei.getReferenceField("link") != MJIEnv.NULL) {
					ElementInfo linkEi = env.getElementInfo(ei.getReferenceField("link"));
					var.setLink(getTerm(env, linkEi));
				}
				
				term = var;
				break;
		}

		return term;
	}

}
