package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRExpIf extends IRExp {
    IRExp predicate;
    IRExp thenClause;
    IRExp elseClause;

    public IRExpIf(IRExp p, IRExp t, IRExp els) {
        predicate = p;
        thenClause = t;
        elseClause = els;
    }

    public IRExpIf(IRExp p, IRExp t) {
        predicate = p;
        thenClause = t;
    }

    public IRExp getPredicate() {
	      return predicate;
    }

    public IRExp getThenClause() {
        return thenClause;
    }

    public IRExp getElseClause() {
        return elseClause;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	      return v.visitIRExpIf(this, arg);
    }

    public String toString() {
	return "if (" + predicate + ") then (" + thenClause + ") else (" + elseClause +") ";
    }

}
