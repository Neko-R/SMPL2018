package smpl.semantics;
import java.util.*;

import smpl.builtIn_necessities.*;
import smpl.syntax.*;
import smpl.syntax.IRExpProc.*;
import smpl.sys.SmplUnboundVarException;
import smpl.values.SmplProc;

/**
 * An instance of class <code>Environment</code> maintains a
 * collection of bindings from valid identifiers to program values.
 * It supports storing and retrieving bindings, just as would
 be expected in any frame.
 *
 * @author <a href="mailto:dcoore@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 * @param <T> The type of values stored in this environment
 */
public class Environment<T> {

    private Environment<T> parent;
    private HashMap<String, T> frame;

    /**
     * Create a new (empty) top level Environment.
     *
     */
    public Environment() {
        parent = null;
	frame = new HashMap<>();
    }
    
    protected Environment(HashMap<String, T> frame, Environment<T> parent) {
        this.parent = parent;
        this.frame = new HashMap<>();
        this.frame.putAll(frame);
    }

    /**
     * Creates a new <code>Environment</code> instance that extends the given 
     * parent with the given collection of bindings
     * (presented as separate arrays of names and values).
     *
     * @param ids the collection of identifiers to be bound.
     * @param values the corresponding collection of values
     * for the identifiers.  Note that the two arrays must
     * have the same length.
     * @param parent The environment being extended.
     */
    public Environment(String[] ids, T[] values, Environment<T> parent) {
        this.parent = parent;
	frame = new HashMap<>();
	for (int i = 0; i < ids.length; i++) {
	    frame.put(ids[i], values[i]);
	}
    }

    public Environment(String[] ids, T[] values) {
        this(ids, values, null);
    }

    /**
     *
     * creates an environment without any new bindings but has a parent
     * used for evaluating def statements with compound expressions.
     * @param parent
     */

    public Environment(Environment<T> parent) {
        this.parent = parent;
        this.frame = new HashMap<>();
    }
    
    public Environment(ArrayList<String> ids, ArrayList<T> values, Environment<T> parent) {
        this.parent = parent;
        frame = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            frame.put(ids.get(i), values.get(i));
        }
    }
    
    /**
     * Create an instance of a global environment suitable for
     * evaluating an program.
     *
     * @param <T> The type of environment to create
     * @param cls The runtime class instance of T
     * @return the <code>Environment</code> created.
     */
    public static <T> Environment<T> makeGlobalEnv(Class<T> cls) {
	Environment<T> result =  new Environment<>();
	// add definitions for any primitive procedures or
	// constants here

        /**
         * Premititve procedures for dealing with pairs
         */
        ParamLst p1 = new ParamLst();
        p1.add("norm", "e1");
        p1.add("norm", "e2");
        Statement b1 = new StmtExpr(new IRExpCreatePair("e1", "e2"));
        SmplProc pair = new SmplProc( new IRExpProc(p1,b1,"fixed"));

        result.put("pair",(T) pair);

        ParamLst p2 = new ParamLst();
        p2.add("norm", "carP");
        Statement b2 = new StmtExpr(new IRExpCar("carP"));
        SmplProc car = new SmplProc(new IRExpProc(p2,b2,"fixed"));

        result.put("car",(T) car);

        ParamLst p3 = new ParamLst();
        p3.add("norm", "cdrP");
        Statement b3 = new StmtExpr(new IRExpCdr("cdrP"));
        SmplProc cdr = new SmplProc(new IRExpProc(p3,b3,"fixed"));

        result.put("cdr",(T) cdr);

        ParamLst p4 = new ParamLst();
        p4.add("norm", "qP");
        Statement b4 = new StmtExpr(new IRExpPairQ("qP"));
        SmplProc pq = new SmplProc(new IRExpProc(p4,b4,"fixed"));

        result.put("pair?",(T) pq);

        ParamLst p5 = new ParamLst();
        p5.add("norm", "p");
        Statement b5 = new StmtExpr(new IRExpCreateList("p"));
        SmplProc list = new SmplProc(new IRExpProc(p5,b5,"any"));

        result.put("list",(T) list);

        ParamLst p6 = new ParamLst();
        p6.add("norm", "vec");
        Statement b6 = new StmtExpr(new IRExpGetSize("vec"));
        SmplProc size = new SmplProc(new IRExpProc(p6,b6,"fixed"));

        result.put("size",(T) size);

        ParamLst p7 = new ParamLst();
        p7.add("norm", "eqv1");
        p7.add("norm", "eqv2");
        Statement b7 = new StmtExpr(new IRExpEquivalent("eqv1", "eqv2"));
        SmplProc eqv = new SmplProc( new IRExpProc(p7,b7,"fixed"));

        result.put("eqv?",(T) eqv);

        ParamLst p8 = new ParamLst();
        p8.add("norm", "equal1");
        p8.add("norm", "equal2");
        Statement b8 = new StmtExpr(new IRExpEqual("equal1", "equal2"));
        SmplProc equal = new SmplProc( new IRExpProc(p8,b8,"fixed"));

        result.put("equal?",(T) equal);

        ParamLst p9 = new ParamLst();
        p9.add("norm", "sub1");
        p9.add("norm", "sub2");
        p9.add("norm", "sub3");
        Statement b9 = new StmtExpr(new IRExpSubStr("sub1", "sub2", "sub3"));
        SmplProc substr = new SmplProc( new IRExpProc(p9,b9,"fixed"));

        result.put("substr",(T) substr);

	return result;
    }
    
    /**
     * Extend this environment with a single binding, and return the new
     * environment thus created.
     * @param var The name of the variable to be bound in the new environment
     * @param val The value of the variable
     * @return The extending environment (which starts with the newly created 
     * frame)
     */
    public Environment<T> extend(String var, T val) {
        HashMap<String, T> newFrame = new HashMap<>();
        newFrame.put(var, val);
        return new Environment<>(newFrame, this);
    }

    public void setParent(Environment<T> env){
        this.parent = env;
    }
    
    /**
     *
     * @param vars
     * @param values
     * @return
     */
    public Environment<T> extend(ArrayList<String> vars, ArrayList<T> values) {
        return new Environment<>(vars, values, this);
    }

    /**
     * Store a binding for the given identifier to the given
     * int within this environment.
     *
     * @param id the name to be bound
     * @param value the value to which the name is bound.
     */
    public void put(String id, T value) {
	frame.put(id, value);
    }

    /**
     * Return the int associated with the given identifier.
     *
     * @param id the identifier.
     * @return the int associated with the identifier in
     * this environment.
     * @exception SmplUnboundVarException <code>id</code> is unbound
     */
    public T get(String id) throws SmplUnboundVarException {
	T result = frame.get(id);
	if (result == null) {
            if (parent == null) {
                throw new SmplUnboundVarException("Unbound variable " + id);
            } else {
                return parent.get(id);
            }
        }
	else
	    return result;
    }

    /**
     * Create a string representation of this environment.
     *
     * @return a string of all the names bound in this
     *         environment.
     */
    @Override
    public String toString() {
	StringBuffer result = new StringBuffer();

	Iterator<String> iter = frame.keySet().iterator();
	while (iter.hasNext()) {
	    result = result.append(iter.next());
	}
	return result.toString();
    }

}
