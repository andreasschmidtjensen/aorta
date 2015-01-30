
import alice.tuprolog.Struct;
import alice.tuprolog.Term;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author asj
 */
public class Test {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Struct s = new Struct("registered", new Struct("#"));
		System.out.println(s);
		System.out.println(Term.createTerm(s.toString()));
	}
	
}
