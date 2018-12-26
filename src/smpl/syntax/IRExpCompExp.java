package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

import java.util.ArrayList;

public class IRExpCompExp extends IRExp {

    StmtSequence stmts;

    public IRExpCompExp(StmtSequence s) { this.stmts = s; }

    public StmtSequence getStmts() {
	    return stmts;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitExpCompExp(this, arg);
    }

    public IRExpCompExp add(Statement s){
        stmts.add(s);
        return this;
    }

    @Override
    public String toString() { return stmts.toString(); }
}
