package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

import java.util.ArrayList;

/**
 * AST node to represent a variable definition.
 * @author newts
 */
public class StmtDefinition extends Statement {

    IRExp  var;
    IRExp  exp;

    /**
     * Create an instance of a variable definition
     * @param id The variable being defined
     * @param e The expression producing its value
     */
    public StmtDefinition(IRExp id, IRExp e) {
        var = id;
        exp = e;
    }

    public IRExp  getVar(){
	return var;
    }

    public IRExp  getExp() {
	return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitStmtDefinition(this, arg);
    }

    @Override
    public String toString() {
        return String.format("%s = %s", var.toString(), exp);
    }
}
