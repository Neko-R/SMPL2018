package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ExprStmt extends Statement {

    Statement stmt;

    public ExprStmt(Statement e) {
	stmt = e;
    }

    /**
     *
     * @return the expression wrapped in this statement
     */
    public Statement getStmt() {
	return stmt;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	return v.visitExprStmt(this, arg);
    }

    @Override
    public String toString() {
	return stmt.toString();
    }
}
