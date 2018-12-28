package smpl.syntax;

public class Clause {

    IRExp predicate;
    IRExp consequent;

    public Clause(IRExp p, IRExp c) {
        predicate = p;
        consequent = c;
    }

    public IRExp getConsequent() {
        return consequent;
    }

    public IRExp getPredicate() {
        return predicate;
    }

    @Override
    public String toString() {
        return predicate + " : " + consequent;
    }
}
