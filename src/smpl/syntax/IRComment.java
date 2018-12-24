package smpl.syntax;

import smpl.semantics.Visitor;

public class IRComment extends IRExp {
  String var;

  public IRComment (String var) {
    this.var = var;
  }

  public String getVar() {
    return var;
  }

  @Override
  public Object visit(Visitor v, Object arg) throws Exception {
    return v.visitIRComment(this, arg);
  }

}
