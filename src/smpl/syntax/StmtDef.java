package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class StmtDef extends Statement {

    String var;
    IRExp exp;

    public StmtDef(String id, IRExp e) {
	var = id;
	exp = e;
    }

    public String getVar(){
	return var;
    }

    public IRExp getExp() {
	return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitStmtDef(this, arg);
    }

    @Override
    public String toString() {
        return String.format("%s := %s", var, exp);
    }
}
