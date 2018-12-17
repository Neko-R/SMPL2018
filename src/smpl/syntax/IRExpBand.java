package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

/**
 * Class to represent an addition AST node
 * @author newts
 */
public class IRExpBand extends IRExpOp {

    /**
     * Construct a bitwise and expression from the two subexpressions
     * @param e1 the left subexpression.
     * @param e2 the right subexpression
     */
    public IRExpBand(IRExp e1, IRExp e2) {
      super("&", e1, e2);
  }

  @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitExpBand(this, arg);
    }

}

