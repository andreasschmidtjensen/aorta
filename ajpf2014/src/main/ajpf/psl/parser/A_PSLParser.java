// $ANTLR 3.4 /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g 2015-03-03 21:41:57

package ajpf.psl.parser;

import ajpf.psl.ast.*;
import ajpf.*;


import mcaplantlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class A_PSLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACTION", "ALWAYS", "AND", "BELIEVE", "CAP", "CLOSEBRACKET", "COMMASEP", "COMMENT", "CONST", "FALSE", "FINALLY", "GOAL", "IMP", "INTENTION", "NEWLINE", "NOT", "NUMBER", "OPENBRACKET", "OPT", "OR", "ORG", "PERCEPT", "RELEASE", "SQCLOSEBRACKET", "SQOPENBRACKET", "TRUE", "UNNAMED_VAR", "UNTIL", "WS"
    };

    public static final int EOF=-1;
    public static final int ACTION=4;
    public static final int ALWAYS=5;
    public static final int AND=6;
    public static final int BELIEVE=7;
    public static final int CAP=8;
    public static final int CLOSEBRACKET=9;
    public static final int COMMASEP=10;
    public static final int COMMENT=11;
    public static final int CONST=12;
    public static final int FALSE=13;
    public static final int FINALLY=14;
    public static final int GOAL=15;
    public static final int IMP=16;
    public static final int INTENTION=17;
    public static final int NEWLINE=18;
    public static final int NOT=19;
    public static final int NUMBER=20;
    public static final int OPENBRACKET=21;
    public static final int OPT=22;
    public static final int OR=23;
    public static final int ORG=24;
    public static final int PERCEPT=25;
    public static final int RELEASE=26;
    public static final int SQCLOSEBRACKET=27;
    public static final int SQOPENBRACKET=28;
    public static final int TRUE=29;
    public static final int UNNAMED_VAR=30;
    public static final int UNTIL=31;
    public static final int WS=32;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public A_PSLParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public A_PSLParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return A_PSLParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g"; }


    	MCAPLmas mas;
    	MCAPLcontroller c;



    // $ANTLR start "spec"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:48:1: spec returns [Abstract_Property p1] : p= property ;
    public final Abstract_Property spec() throws RecognitionException {
        Abstract_Property p1 = null;


        Abstract_Property p =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:48:37: (p= property )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:48:39: p= property
            {
            pushFollow(FOLLOW_property_in_spec72);
            p=property();

            state._fsp--;


            p1 = p;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p1;
    }
    // $ANTLR end "spec"



    // $ANTLR start "property"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:50:1: property returns [Abstract_Property p1] : p= untilproperty ;
    public final Abstract_Property property() throws RecognitionException {
        Abstract_Property p1 = null;


        Abstract_Property p =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:50:41: (p= untilproperty )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:50:43: p= untilproperty
            {
            pushFollow(FOLLOW_untilproperty_in_property88);
            p=untilproperty();

            state._fsp--;


            p1 = p;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p1;
    }
    // $ANTLR end "property"



    // $ANTLR start "untilproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:52:1: untilproperty returns [Abstract_Property p] : rp= releaseproperty ( UNTIL a= releaseproperty )* ;
    public final Abstract_Property untilproperty() throws RecognitionException {
        Abstract_Property p = null;


        Abstract_Property rp =null;

        Abstract_Property a =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:52:45: (rp= releaseproperty ( UNTIL a= releaseproperty )* )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:53:2: rp= releaseproperty ( UNTIL a= releaseproperty )*
            {
            pushFollow(FOLLOW_releaseproperty_in_untilproperty110);
            rp=releaseproperty();

            state._fsp--;


            Abstract_Property prop = rp;

            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:53:57: ( UNTIL a= releaseproperty )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==UNTIL) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:53:58: UNTIL a= releaseproperty
            	    {
            	    match(input,UNTIL,FOLLOW_UNTIL_in_untilproperty115); 

            	    pushFollow(FOLLOW_releaseproperty_in_untilproperty121);
            	    a=releaseproperty();

            	    state._fsp--;


            	    prop = new Abstract_Until(prop, a);

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            p = prop;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "untilproperty"



    // $ANTLR start "releaseproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:55:1: releaseproperty returns [Abstract_Property p] : ip= impproperty ( RELEASE a= impproperty )* ;
    public final Abstract_Property releaseproperty() throws RecognitionException {
        Abstract_Property p = null;


        Abstract_Property ip =null;

        Abstract_Property a =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:55:47: (ip= impproperty ( RELEASE a= impproperty )* )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:56:2: ip= impproperty ( RELEASE a= impproperty )*
            {
            pushFollow(FOLLOW_impproperty_in_releaseproperty145);
            ip=impproperty();

            state._fsp--;


            Abstract_Property prop = ip;

            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:56:53: ( RELEASE a= impproperty )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RELEASE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:56:54: RELEASE a= impproperty
            	    {
            	    match(input,RELEASE,FOLLOW_RELEASE_in_releaseproperty150); 

            	    pushFollow(FOLLOW_impproperty_in_releaseproperty156);
            	    a=impproperty();

            	    state._fsp--;


            	    prop = new Abstract_Release(prop, a);

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            p = prop;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "releaseproperty"



    // $ANTLR start "impproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:58:1: impproperty returns [Abstract_Property p] : ap= andproperty ( IMP a= andproperty )* ;
    public final Abstract_Property impproperty() throws RecognitionException {
        Abstract_Property p = null;


        Abstract_Property ap =null;

        Abstract_Property a =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:58:43: (ap= andproperty ( IMP a= andproperty )* )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:59:2: ap= andproperty ( IMP a= andproperty )*
            {
            pushFollow(FOLLOW_andproperty_in_impproperty180);
            ap=andproperty();

            state._fsp--;


            Abstract_Property prop = ap;

            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:59:53: ( IMP a= andproperty )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==IMP) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:59:54: IMP a= andproperty
            	    {
            	    match(input,IMP,FOLLOW_IMP_in_impproperty185); 

            	    pushFollow(FOLLOW_andproperty_in_impproperty191);
            	    a=andproperty();

            	    state._fsp--;


            	    prop = new Abstract_Or(new Abstract_Not(prop), a);

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            p = prop;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "impproperty"



    // $ANTLR start "andproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:61:1: andproperty returns [Abstract_Property p] : at= orproperty ( AND a= orproperty )* ;
    public final Abstract_Property andproperty() throws RecognitionException {
        Abstract_Property p = null;


        Abstract_Property at =null;

        Abstract_Property a =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:61:43: (at= orproperty ( AND a= orproperty )* )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:62:2: at= orproperty ( AND a= orproperty )*
            {
            pushFollow(FOLLOW_orproperty_in_andproperty215);
            at=orproperty();

            state._fsp--;


            Abstract_Property prop = at;

            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:62:52: ( AND a= orproperty )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==AND) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:62:53: AND a= orproperty
            	    {
            	    match(input,AND,FOLLOW_AND_in_andproperty220); 

            	    pushFollow(FOLLOW_orproperty_in_andproperty226);
            	    a=orproperty();

            	    state._fsp--;


            	    prop = new Abstract_And(prop, a);

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            p = prop;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "andproperty"



    // $ANTLR start "orproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:64:1: orproperty returns [Abstract_Property p] : at= alwaysproperty ( OR a= alwaysproperty )* ;
    public final Abstract_Property orproperty() throws RecognitionException {
        Abstract_Property p = null;


        Abstract_Property at =null;

        Abstract_Property a =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:64:42: (at= alwaysproperty ( OR a= alwaysproperty )* )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:65:2: at= alwaysproperty ( OR a= alwaysproperty )*
            {
            pushFollow(FOLLOW_alwaysproperty_in_orproperty250);
            at=alwaysproperty();

            state._fsp--;


            Abstract_Property prop = at;

            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:65:56: ( OR a= alwaysproperty )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==OR) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:65:57: OR a= alwaysproperty
            	    {
            	    match(input,OR,FOLLOW_OR_in_orproperty255); 

            	    pushFollow(FOLLOW_alwaysproperty_in_orproperty261);
            	    a=alwaysproperty();

            	    state._fsp--;


            	    prop = new Abstract_Or(prop, a);

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            p = prop;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "orproperty"



    // $ANTLR start "alwaysproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:67:1: alwaysproperty returns [Abstract_Property p] : (np= finallyproperty | ALWAYS pl= finallyproperty );
    public final Abstract_Property alwaysproperty() throws RecognitionException {
        Abstract_Property p = null;


        Abstract_Property np =null;

        Abstract_Property pl =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:67:46: (np= finallyproperty | ALWAYS pl= finallyproperty )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ACTION||(LA6_0 >= BELIEVE && LA6_0 <= CAP)||(LA6_0 >= FALSE && LA6_0 <= GOAL)||LA6_0==INTENTION||LA6_0==NOT||(LA6_0 >= OPENBRACKET && LA6_0 <= OPT)||(LA6_0 >= ORG && LA6_0 <= PERCEPT)||LA6_0==TRUE) ) {
                alt6=1;
            }
            else if ( (LA6_0==ALWAYS) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:68:2: np= finallyproperty
                    {
                    pushFollow(FOLLOW_finallyproperty_in_alwaysproperty285);
                    np=finallyproperty();

                    state._fsp--;


                    p = np;

                    }
                    break;
                case 2 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:69:2: ALWAYS pl= finallyproperty
                    {
                    match(input,ALWAYS,FOLLOW_ALWAYS_in_alwaysproperty292); 

                    pushFollow(FOLLOW_finallyproperty_in_alwaysproperty298);
                    pl=finallyproperty();

                    state._fsp--;


                    p = new Abstract_Always(pl);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "alwaysproperty"



    // $ANTLR start "finallyproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:71:1: finallyproperty returns [Abstract_Property p] : (np= notproperty | FINALLY pl= notproperty );
    public final Abstract_Property finallyproperty() throws RecognitionException {
        Abstract_Property p = null;


        Abstract_Property np =null;

        Abstract_Property pl =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:71:47: (np= notproperty | FINALLY pl= notproperty )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==ACTION||(LA7_0 >= BELIEVE && LA7_0 <= CAP)||LA7_0==FALSE||LA7_0==GOAL||LA7_0==INTENTION||LA7_0==NOT||(LA7_0 >= OPENBRACKET && LA7_0 <= OPT)||(LA7_0 >= ORG && LA7_0 <= PERCEPT)||LA7_0==TRUE) ) {
                alt7=1;
            }
            else if ( (LA7_0==FINALLY) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:72:2: np= notproperty
                    {
                    pushFollow(FOLLOW_notproperty_in_finallyproperty317);
                    np=notproperty();

                    state._fsp--;


                    p = np;

                    }
                    break;
                case 2 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:73:2: FINALLY pl= notproperty
                    {
                    match(input,FINALLY,FOLLOW_FINALLY_in_finallyproperty324); 

                    pushFollow(FOLLOW_notproperty_in_finallyproperty330);
                    pl=notproperty();

                    state._fsp--;


                    p = new Abstract_Finally(pl);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "finallyproperty"



    // $ANTLR start "notproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:75:1: notproperty returns [Abstract_Property p] : (at= atom | NOT p1= atom );
    public final Abstract_Property notproperty() throws RecognitionException {
        Abstract_Property p = null;


        Abstract_Property at =null;

        Abstract_Property p1 =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:75:43: (at= atom | NOT p1= atom )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==ACTION||(LA8_0 >= BELIEVE && LA8_0 <= CAP)||LA8_0==FALSE||LA8_0==GOAL||LA8_0==INTENTION||(LA8_0 >= OPENBRACKET && LA8_0 <= OPT)||(LA8_0 >= ORG && LA8_0 <= PERCEPT)||LA8_0==TRUE) ) {
                alt8=1;
            }
            else if ( (LA8_0==NOT) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:76:2: at= atom
                    {
                    pushFollow(FOLLOW_atom_in_notproperty349);
                    at=atom();

                    state._fsp--;


                    p = at;

                    }
                    break;
                case 2 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:77:2: NOT p1= atom
                    {
                    match(input,NOT,FOLLOW_NOT_in_notproperty355); 

                    pushFollow(FOLLOW_atom_in_notproperty361);
                    p1=atom();

                    state._fsp--;


                    p = new Abstract_Not(p1);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "notproperty"



    // $ANTLR start "atom"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:79:1: atom returns [Abstract_Property p] : (prop= proposition | OPENBRACKET tp= property CLOSEBRACKET );
    public final Abstract_Property atom() throws RecognitionException {
        Abstract_Property p = null;


        Abstract_Proposition prop =null;

        Abstract_Property tp =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:79:36: (prop= proposition | OPENBRACKET tp= property CLOSEBRACKET )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==ACTION||(LA9_0 >= BELIEVE && LA9_0 <= CAP)||LA9_0==FALSE||LA9_0==GOAL||LA9_0==INTENTION||LA9_0==OPT||(LA9_0 >= ORG && LA9_0 <= PERCEPT)||LA9_0==TRUE) ) {
                alt9=1;
            }
            else if ( (LA9_0==OPENBRACKET) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }
            switch (alt9) {
                case 1 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:80:2: prop= proposition
                    {
                    pushFollow(FOLLOW_proposition_in_atom382);
                    prop=proposition();

                    state._fsp--;


                    p = prop;

                    }
                    break;
                case 2 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:81:2: OPENBRACKET tp= property CLOSEBRACKET
                    {
                    match(input,OPENBRACKET,FOLLOW_OPENBRACKET_in_atom389); 

                    pushFollow(FOLLOW_property_in_atom395);
                    tp=property();

                    state._fsp--;


                    p = tp;

                    match(input,CLOSEBRACKET,FOLLOW_CLOSEBRACKET_in_atom399); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "atom"



    // $ANTLR start "proposition"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:83:1: proposition returns [Abstract_Proposition p1] : (p2= beliefproperty |p3= goalproperty |p4= falseprop |p5= intentionproperty |p6= lastactionproperty |p7= perceptproperty |p8= orgproperty |p9= optproperty |p10= capproperty |p= trueprop );
    public final Abstract_Proposition proposition() throws RecognitionException {
        Abstract_Proposition p1 = null;


        Abstract_AgBelief p2 =null;

        Abstract_AgGoal p3 =null;

        Abstract_FalseProp p4 =null;

        Abstract_AgIntention p5 =null;

        Abstract_LastAction p6 =null;

        Abstract_Percept p7 =null;

        Abstract_OrgBelief p8 =null;

        Abstract_OrgOption p9 =null;

        Abstract_AgCap p10 =null;

        Abstract_TrueProp p =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:83:47: (p2= beliefproperty |p3= goalproperty |p4= falseprop |p5= intentionproperty |p6= lastactionproperty |p7= perceptproperty |p8= orgproperty |p9= optproperty |p10= capproperty |p= trueprop )
            int alt10=10;
            switch ( input.LA(1) ) {
            case BELIEVE:
                {
                alt10=1;
                }
                break;
            case GOAL:
                {
                alt10=2;
                }
                break;
            case FALSE:
                {
                alt10=3;
                }
                break;
            case INTENTION:
                {
                alt10=4;
                }
                break;
            case ACTION:
                {
                alt10=5;
                }
                break;
            case PERCEPT:
                {
                alt10=6;
                }
                break;
            case ORG:
                {
                alt10=7;
                }
                break;
            case OPT:
                {
                alt10=8;
                }
                break;
            case CAP:
                {
                alt10=9;
                }
                break;
            case TRUE:
                {
                alt10=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }

            switch (alt10) {
                case 1 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:84:2: p2= beliefproperty
                    {
                    pushFollow(FOLLOW_beliefproperty_in_proposition417);
                    p2=beliefproperty();

                    state._fsp--;


                    p1 = (Abstract_Proposition) p2;

                    }
                    break;
                case 2 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:85:2: p3= goalproperty
                    {
                    pushFollow(FOLLOW_goalproperty_in_proposition429);
                    p3=goalproperty();

                    state._fsp--;


                    p1 = (Abstract_Proposition) p3;

                    }
                    break;
                case 3 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:85:60: p4= falseprop
                    {
                    pushFollow(FOLLOW_falseprop_in_proposition439);
                    p4=falseprop();

                    state._fsp--;


                    p1 = (Abstract_Proposition) p4;

                    }
                    break;
                case 4 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:86:2: p5= intentionproperty
                    {
                    pushFollow(FOLLOW_intentionproperty_in_proposition451);
                    p5=intentionproperty();

                    state._fsp--;


                    p1 = (Abstract_Proposition) p5;

                    }
                    break;
                case 5 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:86:65: p6= lastactionproperty
                    {
                    pushFollow(FOLLOW_lastactionproperty_in_proposition461);
                    p6=lastactionproperty();

                    state._fsp--;


                    p1 = (Abstract_Proposition) p6;

                    }
                    break;
                case 6 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:87:2: p7= perceptproperty
                    {
                    pushFollow(FOLLOW_perceptproperty_in_proposition472);
                    p7=perceptproperty();

                    state._fsp--;


                    p1 = (Abstract_Proposition) p7;

                    }
                    break;
                case 7 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:88:2: p8= orgproperty
                    {
                    pushFollow(FOLLOW_orgproperty_in_proposition483);
                    p8=orgproperty();

                    state._fsp--;


                    p1 = (Abstract_Proposition) p8;

                    }
                    break;
                case 8 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:88:59: p9= optproperty
                    {
                    pushFollow(FOLLOW_optproperty_in_proposition493);
                    p9=optproperty();

                    state._fsp--;


                    p1 = (Abstract_Proposition) p9;

                    }
                    break;
                case 9 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:89:2: p10= capproperty
                    {
                    pushFollow(FOLLOW_capproperty_in_proposition504);
                    p10=capproperty();

                    state._fsp--;


                    p1 = (Abstract_Proposition) p10;

                    }
                    break;
                case 10 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:90:2: p= trueprop
                    {
                    pushFollow(FOLLOW_trueprop_in_proposition515);
                    p=trueprop();

                    state._fsp--;


                    p1 = (Abstract_Proposition) p;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p1;
    }
    // $ANTLR end "proposition"



    // $ANTLR start "beliefproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:92:1: beliefproperty returns [Abstract_AgBelief p] : BELIEVE OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET ;
    public final Abstract_AgBelief beliefproperty() throws RecognitionException {
        Abstract_AgBelief p = null;


        String ag =null;

        Abstract_Formula f =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:92:46: ( BELIEVE OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:93:2: BELIEVE OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET
            {
            match(input,BELIEVE,FOLLOW_BELIEVE_in_beliefproperty531); 

            match(input,OPENBRACKET,FOLLOW_OPENBRACKET_in_beliefproperty533); 

            pushFollow(FOLLOW_agentname_in_beliefproperty537);
            ag=agentname();

            state._fsp--;


            match(input,COMMASEP,FOLLOW_COMMASEP_in_beliefproperty539); 

            pushFollow(FOLLOW_formula_in_beliefproperty543);
            f=formula();

            state._fsp--;


            match(input,CLOSEBRACKET,FOLLOW_CLOSEBRACKET_in_beliefproperty545); 

            p = new Abstract_AgBelief(ag, (Abstract_Formula) f);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "beliefproperty"



    // $ANTLR start "goalproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:94:1: goalproperty returns [Abstract_AgGoal p] : GOAL OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET ;
    public final Abstract_AgGoal goalproperty() throws RecognitionException {
        Abstract_AgGoal p = null;


        String ag =null;

        Abstract_Formula f =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:94:42: ( GOAL OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:95:2: GOAL OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET
            {
            match(input,GOAL,FOLLOW_GOAL_in_goalproperty559); 

            match(input,OPENBRACKET,FOLLOW_OPENBRACKET_in_goalproperty561); 

            pushFollow(FOLLOW_agentname_in_goalproperty565);
            ag=agentname();

            state._fsp--;


            match(input,COMMASEP,FOLLOW_COMMASEP_in_goalproperty567); 

            pushFollow(FOLLOW_formula_in_goalproperty571);
            f=formula();

            state._fsp--;


            match(input,CLOSEBRACKET,FOLLOW_CLOSEBRACKET_in_goalproperty573); 

            p = new Abstract_AgGoal(ag, (Abstract_Formula) f);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "goalproperty"



    // $ANTLR start "orgproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:96:1: orgproperty returns [Abstract_OrgBelief p] : ORG OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET ;
    public final Abstract_OrgBelief orgproperty() throws RecognitionException {
        Abstract_OrgBelief p = null;


        String ag =null;

        Abstract_Formula f =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:96:44: ( ORG OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:97:2: ORG OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET
            {
            match(input,ORG,FOLLOW_ORG_in_orgproperty587); 

            match(input,OPENBRACKET,FOLLOW_OPENBRACKET_in_orgproperty589); 

            pushFollow(FOLLOW_agentname_in_orgproperty593);
            ag=agentname();

            state._fsp--;


            match(input,COMMASEP,FOLLOW_COMMASEP_in_orgproperty595); 

            pushFollow(FOLLOW_formula_in_orgproperty599);
            f=formula();

            state._fsp--;


            match(input,CLOSEBRACKET,FOLLOW_CLOSEBRACKET_in_orgproperty601); 

            p = new Abstract_OrgBelief(ag, (Abstract_Formula) f);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "orgproperty"



    // $ANTLR start "optproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:98:1: optproperty returns [Abstract_OrgOption p] : OPT OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET ;
    public final Abstract_OrgOption optproperty() throws RecognitionException {
        Abstract_OrgOption p = null;


        String ag =null;

        Abstract_Formula f =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:98:44: ( OPT OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:99:2: OPT OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET
            {
            match(input,OPT,FOLLOW_OPT_in_optproperty616); 

            match(input,OPENBRACKET,FOLLOW_OPENBRACKET_in_optproperty618); 

            pushFollow(FOLLOW_agentname_in_optproperty622);
            ag=agentname();

            state._fsp--;


            match(input,COMMASEP,FOLLOW_COMMASEP_in_optproperty624); 

            pushFollow(FOLLOW_formula_in_optproperty628);
            f=formula();

            state._fsp--;


            match(input,CLOSEBRACKET,FOLLOW_CLOSEBRACKET_in_optproperty630); 

            p = new Abstract_OrgOption(ag, (Abstract_Formula) f);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "optproperty"



    // $ANTLR start "capproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:100:1: capproperty returns [Abstract_AgCap p] : CAP OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET ;
    public final Abstract_AgCap capproperty() throws RecognitionException {
        Abstract_AgCap p = null;


        String ag =null;

        Abstract_Formula f =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:100:40: ( CAP OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:101:2: CAP OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET
            {
            match(input,CAP,FOLLOW_CAP_in_capproperty646); 

            match(input,OPENBRACKET,FOLLOW_OPENBRACKET_in_capproperty648); 

            pushFollow(FOLLOW_agentname_in_capproperty652);
            ag=agentname();

            state._fsp--;


            match(input,COMMASEP,FOLLOW_COMMASEP_in_capproperty654); 

            pushFollow(FOLLOW_formula_in_capproperty658);
            f=formula();

            state._fsp--;


            match(input,CLOSEBRACKET,FOLLOW_CLOSEBRACKET_in_capproperty660); 

            p = new Abstract_AgCap(ag, (Abstract_Formula) f);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "capproperty"



    // $ANTLR start "falseprop"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:102:1: falseprop returns [Abstract_FalseProp p] : FALSE ;
    public final Abstract_FalseProp falseprop() throws RecognitionException {
        Abstract_FalseProp p = null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:102:42: ( FALSE )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:103:2: FALSE
            {
            match(input,FALSE,FOLLOW_FALSE_in_falseprop674); 

            p = new Abstract_FalseProp();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "falseprop"



    // $ANTLR start "intentionproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:104:1: intentionproperty returns [Abstract_AgIntention p] : INTENTION OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET ;
    public final Abstract_AgIntention intentionproperty() throws RecognitionException {
        Abstract_AgIntention p = null;


        String ag =null;

        Abstract_Formula f =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:104:52: ( INTENTION OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:105:2: INTENTION OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET
            {
            match(input,INTENTION,FOLLOW_INTENTION_in_intentionproperty688); 

            match(input,OPENBRACKET,FOLLOW_OPENBRACKET_in_intentionproperty690); 

            pushFollow(FOLLOW_agentname_in_intentionproperty694);
            ag=agentname();

            state._fsp--;


            match(input,COMMASEP,FOLLOW_COMMASEP_in_intentionproperty696); 

            pushFollow(FOLLOW_formula_in_intentionproperty700);
            f=formula();

            state._fsp--;


            match(input,CLOSEBRACKET,FOLLOW_CLOSEBRACKET_in_intentionproperty702); 

            p = new Abstract_AgIntention(ag, (Abstract_Formula) f);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "intentionproperty"



    // $ANTLR start "lastactionproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:106:1: lastactionproperty returns [Abstract_LastAction p] : ACTION OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET ;
    public final Abstract_LastAction lastactionproperty() throws RecognitionException {
        Abstract_LastAction p = null;


        String ag =null;

        Abstract_Formula f =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:106:52: ( ACTION OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:107:2: ACTION OPENBRACKET ag= agentname COMMASEP f= formula CLOSEBRACKET
            {
            match(input,ACTION,FOLLOW_ACTION_in_lastactionproperty716); 

            match(input,OPENBRACKET,FOLLOW_OPENBRACKET_in_lastactionproperty718); 

            pushFollow(FOLLOW_agentname_in_lastactionproperty722);
            ag=agentname();

            state._fsp--;


            match(input,COMMASEP,FOLLOW_COMMASEP_in_lastactionproperty724); 

            pushFollow(FOLLOW_formula_in_lastactionproperty728);
            f=formula();

            state._fsp--;


            match(input,CLOSEBRACKET,FOLLOW_CLOSEBRACKET_in_lastactionproperty730); 

            p = new Abstract_LastAction(ag, (Abstract_Formula) f);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "lastactionproperty"



    // $ANTLR start "perceptproperty"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:108:1: perceptproperty returns [Abstract_Percept p] : PERCEPT OPENBRACKET f= formula CLOSEBRACKET ;
    public final Abstract_Percept perceptproperty() throws RecognitionException {
        Abstract_Percept p = null;


        Abstract_Formula f =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:108:46: ( PERCEPT OPENBRACKET f= formula CLOSEBRACKET )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:109:2: PERCEPT OPENBRACKET f= formula CLOSEBRACKET
            {
            match(input,PERCEPT,FOLLOW_PERCEPT_in_perceptproperty744); 

            match(input,OPENBRACKET,FOLLOW_OPENBRACKET_in_perceptproperty746); 

            pushFollow(FOLLOW_formula_in_perceptproperty752);
            f=formula();

            state._fsp--;


            match(input,CLOSEBRACKET,FOLLOW_CLOSEBRACKET_in_perceptproperty754); 

            p = new Abstract_Percept((Abstract_Formula) f);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "perceptproperty"



    // $ANTLR start "trueprop"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:110:1: trueprop returns [Abstract_TrueProp p] : TRUE ;
    public final Abstract_TrueProp trueprop() throws RecognitionException {
        Abstract_TrueProp p = null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:110:40: ( TRUE )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:111:2: TRUE
            {
            match(input,TRUE,FOLLOW_TRUE_in_trueprop768); 

            p = new Abstract_TrueProp();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "trueprop"



    // $ANTLR start "agentname"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:113:1: agentname returns [String s] : CONST ;
    public final String agentname() throws RecognitionException {
        String s = null;


        Token CONST1=null;

        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:114:2: ( CONST )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:114:4: CONST
            {
            CONST1=(Token)match(input,CONST,FOLLOW_CONST_in_agentname785); 

            s = CONST1.getText();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return s;
    }
    // $ANTLR end "agentname"



    // $ANTLR start "formula"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:116:1: formula returns [Abstract_Formula f] : CONST ( OPENBRACKET terms[$f] CLOSEBRACKET )? ;
    public final Abstract_Formula formula() throws RecognitionException {
        Abstract_Formula f = null;


        Token CONST2=null;

        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:116:37: ( CONST ( OPENBRACKET terms[$f] CLOSEBRACKET )? )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:117:5: CONST ( OPENBRACKET terms[$f] CLOSEBRACKET )?
            {
            CONST2=(Token)match(input,CONST,FOLLOW_CONST_in_formula803); 

            f = new Abstract_MCAPLPredicate(CONST2.getText());

            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:118:5: ( OPENBRACKET terms[$f] CLOSEBRACKET )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==OPENBRACKET) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:118:6: OPENBRACKET terms[$f] CLOSEBRACKET
                    {
                    match(input,OPENBRACKET,FOLLOW_OPENBRACKET_in_formula813); 

                    pushFollow(FOLLOW_terms_in_formula815);
                    terms(f);

                    state._fsp--;


                    match(input,CLOSEBRACKET,FOLLOW_CLOSEBRACKET_in_formula818); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return f;
    }
    // $ANTLR end "formula"



    // $ANTLR start "subformula"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:120:1: subformula returns [Abstract_MCAPLTerm f] : ( NUMBER | UNNAMED_VAR | SQOPENBRACKET h= subformula ( COMMASEP t= subformula )* SQCLOSEBRACKET | CONST ( OPENBRACKET terms[$f] CLOSEBRACKET )? ) ;
    public final Abstract_MCAPLTerm subformula() throws RecognitionException {
        Abstract_MCAPLTerm f = null;


        Token NUMBER3=null;
        Token UNNAMED_VAR4=null;
        Token CONST5=null;
        Abstract_MCAPLTerm h =null;

        Abstract_MCAPLTerm t =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:120:42: ( ( NUMBER | UNNAMED_VAR | SQOPENBRACKET h= subformula ( COMMASEP t= subformula )* SQCLOSEBRACKET | CONST ( OPENBRACKET terms[$f] CLOSEBRACKET )? ) )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:120:44: ( NUMBER | UNNAMED_VAR | SQOPENBRACKET h= subformula ( COMMASEP t= subformula )* SQCLOSEBRACKET | CONST ( OPENBRACKET terms[$f] CLOSEBRACKET )? )
            {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:120:44: ( NUMBER | UNNAMED_VAR | SQOPENBRACKET h= subformula ( COMMASEP t= subformula )* SQCLOSEBRACKET | CONST ( OPENBRACKET terms[$f] CLOSEBRACKET )? )
            int alt14=4;
            switch ( input.LA(1) ) {
            case NUMBER:
                {
                alt14=1;
                }
                break;
            case UNNAMED_VAR:
                {
                alt14=2;
                }
                break;
            case SQOPENBRACKET:
                {
                alt14=3;
                }
                break;
            case CONST:
                {
                alt14=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }

            switch (alt14) {
                case 1 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:120:45: NUMBER
                    {
                    NUMBER3=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_subformula832); 

                    f = new Abstract_MCAPLNumberTermImpl(NUMBER3.getText());

                    }
                    break;
                case 2 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:121:31: UNNAMED_VAR
                    {
                    UNNAMED_VAR4=(Token)match(input,UNNAMED_VAR,FOLLOW_UNNAMED_VAR_in_subformula868); 

                    f = new Abstract_TermImpl(UNNAMED_VAR4.getText());

                    }
                    break;
                case 3 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:122:31: SQOPENBRACKET h= subformula ( COMMASEP t= subformula )* SQCLOSEBRACKET
                    {
                    match(input,SQOPENBRACKET,FOLLOW_SQOPENBRACKET_in_subformula904); 

                    pushFollow(FOLLOW_subformula_in_subformula908);
                    h=subformula();

                    state._fsp--;


                    f = new Abstract_MCAPLListTermImpl(h);

                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:122:103: ( COMMASEP t= subformula )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==COMMASEP) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:122:105: COMMASEP t= subformula
                    	    {
                    	    match(input,COMMASEP,FOLLOW_COMMASEP_in_subformula914); 

                    	    pushFollow(FOLLOW_subformula_in_subformula918);
                    	    t=subformula();

                    	    state._fsp--;


                    	    ((Abstract_MCAPLListTerm) f).addEnd(t);

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    match(input,SQCLOSEBRACKET,FOLLOW_SQCLOSEBRACKET_in_subformula924); 

                    }
                    break;
                case 4 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:123:10: CONST ( OPENBRACKET terms[$f] CLOSEBRACKET )?
                    {
                    CONST5=(Token)match(input,CONST,FOLLOW_CONST_in_subformula937); 

                    f = new Abstract_TermImpl(CONST5.getText());

                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:124:10: ( OPENBRACKET terms[$f] CLOSEBRACKET )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==OPENBRACKET) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:124:11: OPENBRACKET terms[$f] CLOSEBRACKET
                            {
                            match(input,OPENBRACKET,FOLLOW_OPENBRACKET_in_subformula951); 

                            pushFollow(FOLLOW_terms_in_subformula953);
                            terms(f);

                            state._fsp--;


                            match(input,CLOSEBRACKET,FOLLOW_CLOSEBRACKET_in_subformula956); 

                            }
                            break;

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return f;
    }
    // $ANTLR end "subformula"



    // $ANTLR start "terms"
    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:126:1: terms[Abstract_MCAPLTerm f] : t= subformula ( COMMASEP terms[$f] )? ;
    public final void terms(Abstract_MCAPLTerm f) throws RecognitionException {
        Abstract_MCAPLTerm t =null;


        try {
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:126:29: (t= subformula ( COMMASEP terms[$f] )? )
            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:126:31: t= subformula ( COMMASEP terms[$f] )?
            {
            pushFollow(FOLLOW_subformula_in_terms970);
            t=subformula();

            state._fsp--;


            ((Abstract_TermImpl) f).addTerm(t);

            // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:126:86: ( COMMASEP terms[$f] )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==COMMASEP) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /Users/asj/Dropbox/code/phd/aorta/ajpf2014/src/main/ajpf/psl/parser/A_PSL.g:126:87: COMMASEP terms[$f]
                    {
                    match(input,COMMASEP,FOLLOW_COMMASEP_in_terms975); 

                    pushFollow(FOLLOW_terms_in_terms977);
                    terms(f);

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "terms"

    // Delegated rules


 

    public static final BitSet FOLLOW_property_in_spec72 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_untilproperty_in_property88 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_releaseproperty_in_untilproperty110 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_UNTIL_in_untilproperty115 = new BitSet(new long[]{0x00000000236AE1B0L});
    public static final BitSet FOLLOW_releaseproperty_in_untilproperty121 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_impproperty_in_releaseproperty145 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_RELEASE_in_releaseproperty150 = new BitSet(new long[]{0x00000000236AE1B0L});
    public static final BitSet FOLLOW_impproperty_in_releaseproperty156 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_andproperty_in_impproperty180 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_IMP_in_impproperty185 = new BitSet(new long[]{0x00000000236AE1B0L});
    public static final BitSet FOLLOW_andproperty_in_impproperty191 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_orproperty_in_andproperty215 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_AND_in_andproperty220 = new BitSet(new long[]{0x00000000236AE1B0L});
    public static final BitSet FOLLOW_orproperty_in_andproperty226 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_alwaysproperty_in_orproperty250 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_OR_in_orproperty255 = new BitSet(new long[]{0x00000000236AE1B0L});
    public static final BitSet FOLLOW_alwaysproperty_in_orproperty261 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_finallyproperty_in_alwaysproperty285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALWAYS_in_alwaysproperty292 = new BitSet(new long[]{0x00000000236AE190L});
    public static final BitSet FOLLOW_finallyproperty_in_alwaysproperty298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_notproperty_in_finallyproperty317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINALLY_in_finallyproperty324 = new BitSet(new long[]{0x00000000236AA190L});
    public static final BitSet FOLLOW_notproperty_in_finallyproperty330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_notproperty349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_notproperty355 = new BitSet(new long[]{0x000000002362A190L});
    public static final BitSet FOLLOW_atom_in_notproperty361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_proposition_in_atom382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPENBRACKET_in_atom389 = new BitSet(new long[]{0x00000000236AE1B0L});
    public static final BitSet FOLLOW_property_in_atom395 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_CLOSEBRACKET_in_atom399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beliefproperty_in_proposition417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_goalproperty_in_proposition429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_falseprop_in_proposition439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intentionproperty_in_proposition451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lastactionproperty_in_proposition461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_perceptproperty_in_proposition472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orgproperty_in_proposition483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_optproperty_in_proposition493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_capproperty_in_proposition504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trueprop_in_proposition515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BELIEVE_in_beliefproperty531 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_OPENBRACKET_in_beliefproperty533 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_agentname_in_beliefproperty537 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_COMMASEP_in_beliefproperty539 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_formula_in_beliefproperty543 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_CLOSEBRACKET_in_beliefproperty545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GOAL_in_goalproperty559 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_OPENBRACKET_in_goalproperty561 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_agentname_in_goalproperty565 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_COMMASEP_in_goalproperty567 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_formula_in_goalproperty571 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_CLOSEBRACKET_in_goalproperty573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ORG_in_orgproperty587 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_OPENBRACKET_in_orgproperty589 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_agentname_in_orgproperty593 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_COMMASEP_in_orgproperty595 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_formula_in_orgproperty599 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_CLOSEBRACKET_in_orgproperty601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPT_in_optproperty616 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_OPENBRACKET_in_optproperty618 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_agentname_in_optproperty622 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_COMMASEP_in_optproperty624 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_formula_in_optproperty628 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_CLOSEBRACKET_in_optproperty630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAP_in_capproperty646 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_OPENBRACKET_in_capproperty648 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_agentname_in_capproperty652 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_COMMASEP_in_capproperty654 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_formula_in_capproperty658 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_CLOSEBRACKET_in_capproperty660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_falseprop674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTENTION_in_intentionproperty688 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_OPENBRACKET_in_intentionproperty690 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_agentname_in_intentionproperty694 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_COMMASEP_in_intentionproperty696 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_formula_in_intentionproperty700 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_CLOSEBRACKET_in_intentionproperty702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_lastactionproperty716 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_OPENBRACKET_in_lastactionproperty718 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_agentname_in_lastactionproperty722 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_COMMASEP_in_lastactionproperty724 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_formula_in_lastactionproperty728 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_CLOSEBRACKET_in_lastactionproperty730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCEPT_in_perceptproperty744 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_OPENBRACKET_in_perceptproperty746 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_formula_in_perceptproperty752 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_CLOSEBRACKET_in_perceptproperty754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_trueprop768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONST_in_agentname785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONST_in_formula803 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_OPENBRACKET_in_formula813 = new BitSet(new long[]{0x0000000050101000L});
    public static final BitSet FOLLOW_terms_in_formula815 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_CLOSEBRACKET_in_formula818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_subformula832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNNAMED_VAR_in_subformula868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SQOPENBRACKET_in_subformula904 = new BitSet(new long[]{0x0000000050101000L});
    public static final BitSet FOLLOW_subformula_in_subformula908 = new BitSet(new long[]{0x0000000008000400L});
    public static final BitSet FOLLOW_COMMASEP_in_subformula914 = new BitSet(new long[]{0x0000000050101000L});
    public static final BitSet FOLLOW_subformula_in_subformula918 = new BitSet(new long[]{0x0000000008000400L});
    public static final BitSet FOLLOW_SQCLOSEBRACKET_in_subformula924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONST_in_subformula937 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_OPENBRACKET_in_subformula951 = new BitSet(new long[]{0x0000000050101000L});
    public static final BitSet FOLLOW_terms_in_subformula953 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_CLOSEBRACKET_in_subformula956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subformula_in_terms970 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_COMMASEP_in_terms975 = new BitSet(new long[]{0x0000000050101000L});
    public static final BitSet FOLLOW_terms_in_terms977 = new BitSet(new long[]{0x0000000000000002L});

}