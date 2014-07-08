package aorta.kr.language;

import alice.tuprolog.Prolog;
import alice.tuprolog.Term;

public interface Language {

    public boolean inLanguage(Term term);

    public boolean isValid(Prolog prolog);
}
