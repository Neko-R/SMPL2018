package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

import java.util.ArrayList;

public class IRExpCompExp extends IRExp {

    ArrayList<Statement> stmts;

    public IRExpCompExp() {
        stmts = new ArrayList<>();
    }

    public IRExpCompExp(Statement s) { this(); stmts.add(s);
    }

    public ArrayList<Statement> getStmts() {
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
