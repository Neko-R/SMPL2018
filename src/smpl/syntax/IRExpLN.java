package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

/**
 * Class to represent an addition AST node
 * @author newts
 */
public class IRExpLN extends IRExpOp {

    /**
     * Construct a not equal to expression from the two subexpressions
     * @param e1 the left subexpression.
     */
    public IRExpLN(IRExp e1) {
      super("not", e1);
  }

  @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitExpLN(this, arg);
    }

}

