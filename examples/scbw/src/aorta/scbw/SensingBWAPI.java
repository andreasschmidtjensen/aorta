/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.scbw;

import alice.tuprolog.Int;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.organization.EnvironmentSensor;
import eis.exceptions.NoEnvironmentException;
import eis.exceptions.PerceiveException;
import eis.iilang.Action;
import eis.iilang.DataContainer;
import eis.iilang.Function;
import eis.iilang.IILElement;
import eis.iilang.IILObjectVisitor;
import eis.iilang.Identifier;
import eis.iilang.Numeral;
import eis.iilang.Parameter;
import eis.iilang.ParameterList;
import eis.iilang.Percept;
import eis.iilang.TruthValue;
import eisbw.BWAPIBridge;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author asj
 */
public class SensingBWAPI extends BWAPIBridge implements EnvironmentSensor {

	@Override
	public List<Struct> getEnvState() {
		List<Struct> structs = new ArrayList<>();
//		JNIBWAPI api = getGame();
		for (String entity : getEntities()) {
			try {
				List<Percept> percepts = getAllPerceptsFromEntity(entity);
				structs.addAll(convert(percepts));
			} catch (PerceiveException | NoEnvironmentException ex) {
				ex.printStackTrace();
			}
		}
		return structs;
	}

	private List<Struct> convert(List<Percept> percepts) {
		List<Struct> structs = new ArrayList<>();
		for (Percept p : percepts) {
			structs.add(convert(p));
		}
		return structs;
	}

	private Struct convert(Percept p) {
		Struct percept = (Struct) p.accept(new IILObjectVisitor() {
			@Override
			public Object visit(Action action, Object obj) {
				throw new UnsupportedOperationException("Not supported."); //To change body of generated methods, choose Tools | Templates.
			}

			@Override
			public Object visit(DataContainer dc, Object obj) {
				throw new UnsupportedOperationException("Not supported."); //To change body of generated methods, choose Tools | Templates.
			}

			@Override
			public Struct visit(Function function, Object obj) {
				Struct result = new Struct(function.getName());
				for (Parameter param : function.getParameters()) {
					result.append((Term) visit(param, obj));
				}
				return result;
			}

			@Override
			public Struct visit(Identifier identifier, Object obj) {
				return new Struct(identifier.getValue());
			}

			@Override
			public Object visit(IILElement iile, Object obj) {
				throw new UnsupportedOperationException("Not supported."); //To change body of generated methods, choose Tools | Templates.
			}

			@Override
			public alice.tuprolog.Number visit(Numeral numeral, Object obj) {
				Number num = numeral.getValue();
				alice.tuprolog.Number result;
				if (num instanceof Double) {
					result = new alice.tuprolog.Double(num.doubleValue());
				} else if (num instanceof Float) {
					result = new alice.tuprolog.Float(num.floatValue());
				} else if (num instanceof Integer) {
					result = new Int(num.intValue());
				} else if (num instanceof Long) {
					result = new alice.tuprolog.Long(num.longValue());
				} else if (num instanceof Short) {
					result = new Int(num.shortValue());
				} else {
					throw new UnsupportedOperationException("Number of type " + num.getClass() + " not supported.");
				}
				return result;
			}

			@Override
			public Term visit(Parameter p, Object obj) {
				Term t;
				if (p instanceof Function) {
					t = visit((Function) p, obj);
				} else if (p instanceof Identifier) {
					t = visit((Identifier) p, obj);
				} else if (p instanceof Numeral) {
					t = visit((Numeral) p, obj);
				} else if (p instanceof ParameterList) {
					t = visit((ParameterList) p, obj);
				} else if (p instanceof TruthValue) {
					t = visit((TruthValue) p, obj);
				} else {
					throw new UnsupportedOperationException("Parameter of type " + p.getClass() + " not supported.");
				}
				return t;
			}

			@Override
			public Struct visit(ParameterList pl, Object obj) {
				Struct list = new Struct();
				Iterator<Parameter> it = pl.iterator();
				while (it.hasNext()) {
					Parameter p = it.next();
					list.append(visit(p, obj));
				}
				return list;
			}

			@Override
			public Struct visit(Percept percept, Object obj) {
				Term[] terms = new Term[percept.getParameters().size()];
				for (int i = 0; i < terms.length; i++) {
					Parameter param = percept.getParameters().get(i);
					terms[i] = ((Term) visit(param, obj));
				}
				Struct result = new Struct(percept.getName(), terms);
				return result;
			}

			@Override
			public Term visit(TruthValue tv, Object obj) {
				return tv.getBooleanValue() ? Term.TRUE : Term.FALSE;
			}
		}, null);
		
		return percept;
	}
	
}
