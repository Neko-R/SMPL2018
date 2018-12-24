package smpl.syntax;

import smpl.semantics.Clause;
import smpl.semantics.Visitor;
import java.util.ArrayList;

public class IRCase extends IRExp {
  ArrayList<Clause> body;

  public IRCase(ArrayList<Clause> s) {
    this.body = s;
  }
  
  public ArrayList<Clause> getBody() {
      return body;
  }

  @Override
  public Object visit(Visitor v, Object arg) throws Exception {
    return v.visitIRCase(this, arg);
  }

}
