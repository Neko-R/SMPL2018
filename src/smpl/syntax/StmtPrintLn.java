package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class StmtPrintLn extends Statement {

    IRExp exp;

    public StmtPrintLn(IRExp e) {
	exp = e;
    }

    public IRExp getExp() {
	return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	    v.visitStmtPrintLn(this, arg);
	    return null;
    }

    @Override
    public String toString() {
	return "print "+ exp.toString();
    }
}
