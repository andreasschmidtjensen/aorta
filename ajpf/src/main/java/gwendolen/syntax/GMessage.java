// ----------------------------------------------------------------------------
// Copyright (C) 2012 Louise A. Dennis, and  Michael Fisher 
//
// This file is part of Gwendolen
// 
// Gwendolen is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 3 of the License, or (at your option) any later version.
// 
// Gwendolen is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with Gwendolen; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
// 
// To contact the authors:
// http://www.csc.liv.ac.uk/~lad
//
//----------------------------------------------------------------------------

package gwendolen.syntax;

import ail.syntax.Literal;
import ail.syntax.Term;
import ail.syntax.Predicate;
import ail.syntax.StringTerm;
import ail.syntax.StringTermImpl;

/**
 * Class for Gwendolen messages, making them simpler to constuct.
 * @author louiseadennis
 *
 */
public class GMessage extends Literal {
	
	public GMessage() {
		super("message");
	}

	public GMessage(StringTerm sender, StringTerm receiver, int performative, Term content) {
		super("message");
		this.addTerm(new StringTermImpl("mid"));
		this.addTerm(new StringTermImpl("thid"));
		this.addTerm(sender);
		this.addTerm(receiver);
		this.addTerm(new Predicate(((Integer) performative).toString()));
		this.addTerm(content);
	}
	
	public GMessage(Term sender, String receiver, Byte performative, Term content) {
		super("message");
		this.addTerm(new StringTermImpl("mid"));
		this.addTerm(new StringTermImpl("thid"));
		this.addTerm(sender);
		this.addTerm(new Predicate(receiver));
		this.addTerm(new Predicate(performative.toString()));
		this.addTerm(content);
	}
	
	public GMessage(Term sender, Term receiver, Byte performative, Term content) {
		super("message");
		this.addTerm(new StringTermImpl("mid"));
		this.addTerm(new StringTermImpl("thid"));
		this.addTerm(sender);
		this.addTerm(receiver);
		this.addTerm(new Predicate(performative.toString()));
		this.addTerm(content);
	}
	
	public GMessage(Term sender, Term receiver, int performative, Term content) {
		super("message");
		this.addTerm(new StringTermImpl("mid"));
		this.addTerm(new StringTermImpl("thid"));
		this.addTerm(sender);
		this.addTerm(receiver);
		this.addTerm(new Predicate(((Integer) performative).toString()));
		this.addTerm(content);
	}
	
}
