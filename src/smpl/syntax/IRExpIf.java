package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRExpIf extends IRExp {
    IRExp predicate;
    Statement thenClause;
    Statement elseClause;

    public IRExpIf(IRExp p, Statement t, Statement els) {
        predicate = p;
        thenClause = t;
        elseClause = els;
    }

    public IRExpIf(IRExp p, Statement t) {
        predicate = p;
        thenClause = t;
    }

    public IRExp getPredicate() {
	      return predicate;
    }

    public Statement getThenClause() {
        return thenClause;
    }

    public Statement getElseClause() {
        return elseClause;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	      return v.visitIRExpIf(this, arg);
    }

    public String toString() {
	return "if (" + predicate + ") then (" + thenClause + ") else (" + elseClause +") ";
    }

}
