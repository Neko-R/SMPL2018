package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

/**
 * Class to represent an addition AST node
 * @author newts
 */
public class IRExpBnot extends IRExpOp {

    /**
     * Construct a bitwise not expression from the two subexpressions
     * @param e1 the left subexpression.
     */
    public IRExpBnot(IRExp e1) {
      super("~", e1);
  }

  @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitExpBnot(this, arg);
    }

}

