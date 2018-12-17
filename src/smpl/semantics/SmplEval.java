package smpl.semantics;

import cs34q.gfx.GraphingPanel;
import java.util.*;

import smpl.builtIn_necessities.*;
import smpl.syntax.*;
import smpl.syntax.IRExpProc.*;
import smpl.sys.SmplException;
import smpl.values.*;

public class SmplEval implements Visitor<Environment<SmplObj>, SmplObj> {
    /* For this visitor, the argument passed to all visit
       methods will be the environment object that used to
       be passed to the eval method in the first style of
       implementation. */

    // allocate state here
    protected SmplObj lastResult;	// lastResult of evaluation
    private GraphingPanel graphicsPanel;
    private Environment<SmplObj> env;
    
    public SmplEval() {
	// perform initialisations here
	lastResult = SmplObj.DEFAULT;
    }
    
    /**
     * Set the graphics panel (in case one is to be used)
     * @param panel
     */
    public void setView(GraphingPanel panel) {
        graphicsPanel = panel;
    }
    
    /**
     * Initialise this interpeter with a fresh global environment
     */
    public void init() {
        env = Environment.makeGlobalEnv(SmplObj.class);
    }
    
    /**
     *
     * @return The top level environment being used by this interpreter instance
     */
    public Environment<SmplObj> getGlobalEnv() {
        return env;
    }

    @Override
    public SmplObj visitSmplProgram(SmplProgram p,
				    Environment<SmplObj> arg)
	throws SmplException 
    {
	lastResult = p.getSeq().visit(this, arg);
	return lastResult;
    }

    @Override
    public SmplObj visitStmtExpr(StmtExpr s, Environment<SmplObj> arg)
	throws SmplException {
	return s.getExp().visit(this, arg);
    }

    @Override
    public SmplObj visitExprStmt(ExprStmt stmt, Environment<SmplObj> args) throws SmplException  {
        return stmt.getStmt().visit(this, args);
    }

    @Override
    public SmplObj visitStmtSequence(StmtSequence sseq, Environment<SmplObj> arg) throws SmplException {
        // remember that arg is the environment
    //	Statement s;
        ArrayList<Statement> seq = sseq.getSeq();
    //	Iterator iter = seq.iterator();
        SmplObj result = SmplObj.DEFAULT; // default lastResult
    //        while(iter.hasNext()) {
    //	    s = (Statement) iter.next();
        for (Statement s : seq) {
            result = s.visit(this, arg);
        }
        // return last value evaluated
        return result;
    }

    public SmplObj visitStmtDefinition(StmtDefinition sd,
				      Environment<SmplObj> arg)
	throws SmplException
    {
	Environment<SmplObj> env = (Environment<SmplObj>) arg;
	SmplObj result;
	result = sd.getExp().visit(this, env);
	env.put(sd.getVar(), result);
	return result;
    }

    @Override
    public SmplObj visitExpAdd(IRExpAdd exp, Environment<SmplObj> arg)
	throws SmplException
    {
	SmplObj val1, val2;
	val1 = exp.getOperand1().visit(this, arg);
	val2 = exp.getOperand2().visit(this, arg);
        return val1.add(val2);	
    }

    @Override
    public SmplObj visitExpSub(IRExpSub exp, Environment<SmplObj> arg)
	throws SmplException
    {
	SmplObj val1, val2;
	val1 = exp.getOperand1().visit(this, arg);
	val2 = exp.getOperand2().visit(this, arg);
        return val1.sub(val2);	
    }

    @Override
    public SmplObj visitExpMul(IRExpMul exp, Environment<SmplObj> arg)
	throws SmplException
    {
	SmplObj val1, val2;
	val1 = exp.getOperand1().visit(this, arg);
	val2 = exp.getOperand2().visit(this, arg);
        return val1.mul(val2);	
    }

    @Override
    public SmplObj visitExpDiv(IRExpDiv exp, Environment<SmplObj> arg) throws SmplException {

        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);
        return val1.div(val2);
    }

    @Override
    public SmplObj visitExpMod(IRExpMod exp, Environment<SmplObj> arg)
	throws SmplException
    {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);
        return val1.mod(val2);
    }

    @Override
    public SmplObj visitExpExpt(IRExpExpt exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);
        return val1.expt(val2);
    }

    @Override
    public SmplObj visitExpLit(IRExpLit exp, Environment<SmplObj> arg)  throws SmplException{ return exp.getVal(); }

    @Override
    public SmplObj visitExpVar(IRExpVar exp, Environment<SmplObj> arg) throws SmplException {
        // remember that arg is really the environment
        SmplObj val = arg.get(exp.getVar());
        SmplObj result = val;
        if(val instanceof SmplPromise){
            if( ((SmplPromise)val).getValue() == null){
                Environment<SmplObj> env = ((SmplPromise)val).getEnvironment();
                IRExp pexp = ((SmplPromise)val).getExp();
                SmplObj temp = pexp.visit(this,env);
                ((SmplPromise)val).setValue(temp);
                //result = ((SmplPromise) val).getValue();
            }//else {
            result = ((SmplPromise) val).getValue();
                //System.out.println("true " + result);
            //}
        }
        return result;
    }

    //Relational Operations

    @Override
    public SmplObj visitExpEqv(IRExpEqv exp, Environment<SmplObj> arg)  throws SmplException {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);
        return val1.eq(val2);
    }

    @Override
    public SmplObj visitExpLst(IRExpLst exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);
        return val1.lt(val2);
    }

    @Override
    public SmplObj visitExpLqt(IRExpLqt exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);
        return val1.le(val2);
    }

    @Override
    public SmplObj visitExpGrt(IRExpGrt exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);
        return val1.gt(val2);
    }

    @Override
    public SmplObj visitExpGqt(IRExpGqt exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);
        return val1.ge(val2);
    }

    @Override
    public SmplObj visitExpNqt(IRExpNqt exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);
        return val1.neq(val2);
    }

    //bitwise operations

    @Override
    public SmplObj visitExpBand(IRExpBand exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);
        return val1.andBits(val2);
    }

    @Override
    public SmplObj visitExpBor(IRExpBor exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);
        return val1.orBits(val2);
    }

    @Override
    public SmplObj visitExpBnot(IRExpBnot exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        return val1.notBits();
    }

    @Override
    public SmplObj visitIRExpLet(IRExpLet exp, Environment<SmplObj> arg) throws SmplException {
        ArrayList<Binding> bindings = exp.getBindings().getSeq(); //get bindinglist from let expression and then get the arraylist of bindings from bindinglist
        Statement body = exp.getBody();

        ArrayList<String> ids =  new ArrayList<String>();
        ArrayList<SmplObj> values = new ArrayList<SmplObj>();

        for (Binding b: bindings) {
            ids.add(b.getVar());
            SmplObj value = b.getValExp().visit(this, arg);
            values.add(value);
        }

        Environment newEnv = new Environment(ids, values, arg);
        SmplObj result = body.visit(this, newEnv);
        return result;
    }

    @Override
    public SmplObj visitExpCompExp(IRExpCompExp exp, Environment<SmplObj> arg) throws SmplException {
        ArrayList<Statement> stmts = exp.getStmts();

        SmplObj result = SmplObj.DEFAULT;
        for (Statement s : stmts) {
            result = s.visit(this, arg);
        }
        return result;
    }

    @Override
    public SmplObj visitExpMultiExp(IRExpMultiExp exp, Environment<SmplObj> args)  throws SmplException {
        ArrayList<IRExp> exps = exp.getExps();

        SmplTuple tuple = new SmplTuple();
        SmplObj val;
        for (IRExp s: exps) {
            val = s.visit(this, args);
            tuple.add(val);
        }

        return tuple;

    }

    @Override
    public SmplObj visitIRExpIf(IRExpIf stmt, Environment<SmplObj> arg) throws SmplException {

        IRExp predicate = stmt.getPredicate();
        Statement thenClause = stmt.getThenClause();
        Statement elseClause = stmt.getElseClause();

        SmplObj pred = predicate.visit(this, arg);
        SmplObj result = SmplObj.DEFAULT;

        if (pred instanceof SmplBool){
            if (pred.isTrue()){
                result = thenClause.visit(this, arg);
                //System.out.println("true "+ result);
            }else if (elseClause != null){
                result = elseClause.visit(this, arg);
                //System.out.println("false" + result);
            }

        }
        return result;
    }

    @Override
    public SmplObj visitStmtDef(StmtDef stmt, Environment<SmplObj> arg) throws SmplException {
        String id = stmt.getVar();
        Environment newEnv = new Environment(arg);
        SmplObj val = stmt.getExp().visit(this, newEnv);

        env.put(id, val);

        return val;
    }

    @Override
    public SmplObj visitIRExpProc(IRExpProc exp, Environment<SmplObj> arg) {
        Environment<SmplObj> env = arg;
        return new SmplProc(exp, env);
    }

    @Override
    public SmplObj visitIRExpProcCallShort(IRExpProcCallShort call, Environment<SmplObj> args) throws SmplException {
        SmplObj closure = call.getName().visit(this, args);
        ArrayList<IRExp> arglst = call.getArguments();

        if(closure instanceof SmplPromise){
            if(((SmplPromise) closure).getValue() == null){
                Environment<SmplObj> irenv = ((SmplPromise) closure).getEnvironment();
                SmplObj val = ((SmplPromise) closure).getExp().visit(this, irenv);
                ((SmplPromise) closure).setValue(val);
            }
            closure = ((SmplPromise) closure).getValue();
        }

        IRExpProc proc = ((SmplProc)closure).getProc();
        Environment<SmplObj> env = new Environment(((SmplProc) closure).getEnvironment());
        ArrayList<Parameter> params = proc.getParameters().getparamLst();
        Statement body = proc.getBody();

        if (proc.isFixed()) {
            for (int i = 0; i < params.size(); i++) {
                Parameter p = params.get(i);
                IRExp a = arglst.get(i);
                SmplObj val = SmplObj.DEFAULT;

                if(p.getModifier() == "norm"){
                    val = a.visit(this, args);
                }else if (p.getModifier() == "lazy"){
                    val = new SmplPromise(a,args);
                }

                env.put(p.getId(),val);
            }

        }else if (proc.isLeast()){
            int i;
            for (i = 0; i < params.size() - 1; i++) {
                Parameter p = params.get(i);
                IRExp a = arglst.get(i);
                SmplObj val = SmplObj.DEFAULT;

                if(p.getModifier() == "norm"){
                    val = a.visit(this, args);
                }else if (p.getModifier() == "lazy"){
                    val = new SmplPromise(a,args);
                }

                env.put(p.getId(),val);
            }
            if(i < arglst.size() - 1){
                SmplObj prest = arglst.get(arglst.size() - 1).visit(this, args);
                String id = params.get(params.size() - 1).getId();
                Environment<SmplObj> newEnv = args.extend(id, prest);

                ArrayList<SmplObj> temp = new ArrayList<>();
                for (IRExp exp: arglst.subList(i,arglst.size() - 1)) {
                    temp.add(exp.visit(this,args));
                }
                IRExp list = new IRExpLit(new SmplList(temp));
                IRExpProcCallFull prestCall = new IRExpProcCallFull(new IRExpVar(id), list);

                env.put(id,prestCall.visit(this,newEnv));
            }

        }else{
            Parameter p = params.get(0);
            ArrayList<SmplObj> temp = new ArrayList<>();
            for (IRExp exp: arglst) {
                temp.add(exp.visit(this,args));
            }

            env.put(p.getId(),new SmplList(temp));
        }

        return body.visit(this, env);
    }

    public SmplObj visitIRExpProcCallFull(IRExpProcCallFull call, Environment<SmplObj> args) throws SmplException {
        SmplObj closure = call.getName().visit(this, args);
        ArrayList<SmplObj> arglst = ((SmplList)call.getArguments().visit(this,args)).getArray();

        if(closure instanceof SmplPromise){
            if(((SmplPromise) closure).getValue() == null){
                Environment<SmplObj> irenv = ((SmplPromise) closure).getEnvironment();
                SmplObj val = ((SmplPromise) closure).getExp().visit(this, irenv);
                ((SmplPromise) closure).setValue(val);
            }
            closure = ((SmplPromise) closure).getValue();
        }

        IRExpProc proc = ((SmplProc)closure).getProc();
        Environment<SmplObj> env = new Environment(((SmplProc) closure).getEnvironment());
        ArrayList<Parameter> params = proc.getParameters().getparamLst();
        Statement body = proc.getBody();

        if (proc.isFixed()) {
            for (int i = 0; i < params.size(); i++) {
                Parameter p = params.get(i);
                SmplObj a = arglst.get(i);
                SmplObj val = SmplObj.DEFAULT;

                if(p.getModifier() == "norm"){
                    val = a;
                }else if (p.getModifier() == "lazy"){
                    val = new SmplPromise(a,args);
                }

                env.put(p.getId(),val);
            }

        }else if (proc.isLeast()){
            int i;
            for (i = 0; i < params.size() - 1; i++) {
                Parameter p = params.get(i);
                SmplObj a = arglst.get(i);
                SmplObj val = SmplObj.DEFAULT;

                if(p.getModifier() == "norm"){
                    val = a;
                }else if (p.getModifier() == "lazy"){
                    val = new SmplPromise(a,args);
                }

                env.put(p.getId(),val);
            }
            if(i < arglst.size() - 1){
                SmplObj prest = arglst.get(arglst.size() - 1);
                String id = params.get(params.size() - 1).getId();
                Environment<SmplObj> newEnv = args.extend(id, prest);

                ArrayList<SmplObj> temp = new ArrayList<>();
                for (SmplObj obj: arglst.subList(i,arglst.size() - 1)) {
                    temp.add(obj);
                }
                IRExp list = new IRExpLit(new SmplList(temp));
                IRExpProcCallFull prestCall = new IRExpProcCallFull(new IRExpVar(id), list);

                env.put(id,prestCall.visit(this,newEnv));
            }

        }else{
            Parameter p = params.get(0);
            ArrayList<SmplObj> temp = new ArrayList<>();
            for (SmplObj obj: arglst) {
                temp.add(obj);
            }
            env.put(p.getId(),new SmplList(temp));
        }

        return body.visit(this, env);
    }

    @Override
    public void visitStmtPrintLn(StmtPrintLn stmt, Environment<SmplObj> arg) throws SmplException{
        System.out.print(stmt.getExp().visit(this, arg) + "\n");
    }

    /**
     * Visitor for Builtin functions/Primitive procedures
     *
     */
    @Override
    public SmplObj visitIRExpCreatePair(IRExpCreatePair exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj obj1 = arg.get(exp.getE1());
        SmplObj obj2 = arg.get(exp.getE2());

        return  new SmplPair(obj1,obj2);

    }

    @Override
    public SmplObj visitIRExpCar(IRExpCar car, Environment<SmplObj> arg) throws SmplException {
        SmplObj pair = arg.get(car.getP());

        return pair.first();
    }

    @Override
    public SmplObj visitIRExpCdr(IRExpCdr cdr, Environment<SmplObj> arg) throws SmplException {
        SmplObj pair = arg.get(cdr.getP());

        return pair.second();
    }

    @Override
    public SmplObj visitIRExpPairQ(IRExpPairQ pq, Environment<SmplObj> arg) throws SmplException{
        SmplObj pair = arg.get(pq.getP());

        return SmplBool.getConst(pair.isPair());
    }

    @Override
    public SmplObj visitIRExpCreateList(IRExpCreateList exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj list = arg.get(exp.getParameter());

        return list;
    }
}
