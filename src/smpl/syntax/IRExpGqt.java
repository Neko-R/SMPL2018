package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

/**
 * Class to represent an addition AST node
 * @author newts
 */
public class IRExpGqt extends IRExpOp {

    /**
     * Construct a greater or equal to expression from the two subexpressions
     * @param e1 the left subexpression.
     * @param e2 the right subexpression
     */
    public IRExpGqt(IRExp e1, IRExp e2) {
      super(">=", e1, e2);
  }

  @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitExpGqt(this, arg);
    }

}

