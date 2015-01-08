/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language;

import alice.tuprolog.Int;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class NumberConverter {

	public static String test() { return ""; };
	
	public static alice.tuprolog.Number parseNumber(String string) {
		try {
			Number number = NumberFormat.getInstance(Locale.ENGLISH).parse(string);

			alice.tuprolog.Number result;
			if (number instanceof Integer) {
				result = new Int(number.intValue());
			} else if (number instanceof Double) {
				result = new alice.tuprolog.Double(number.doubleValue());
			} else if (number instanceof Float) {
				result = new alice.tuprolog.Float(number.floatValue());
			} else if (number instanceof Long) {
				result = new alice.tuprolog.Long(number.longValue());
			} else {
				throw new RuntimeException("Number " + string + " not supported");
			}

			return result;
		} catch (ParseException ex) {
			throw new RuntimeException("Number " + string + " could not be parsed", ex);
		}
	}
}
