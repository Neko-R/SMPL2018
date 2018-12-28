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
    public SmplObj visitStmtExpr(StmtExpr s, Environment<SmplObj> arg) throws SmplException {
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

    public SmplObj visitStmtDefinition(StmtDefinition sd, Environment<SmplObj> arg) throws SmplException {
        Environment<SmplObj> env = arg;

        IRExp var = sd.getVar();
        SmplObj result = sd.getExp().visit(this,env);
        if(var.visit(this,env) instanceof SmplAlias){
            SmplAlias temp = (SmplAlias) var.visit(this,env);
            Environment<SmplObj> tempEnv = temp.getEnv();
            tempEnv.put(temp.getLocation(), result);
        } else if (var instanceof IRExpVar)
            env.put(((IRExpVar) var).getVar(), result);
        else if (var instanceof IRExpGetIndex) {
            SmplObj vector = ((IRExpGetIndex) var).getVector().visit(this, env);
            SmplObj n = ((IRExpGetIndex) var).getN().visit(this, env);
            if(vector instanceof SmplAlias){
                ((SmplVector) env.get(((SmplAlias) vector).getLocation()) ).getArray().remove(((SmplInt) n).value());
                ((SmplVector) env.get(((SmplAlias) vector).getLocation()) ).getArray().add((((SmplInt) n).value()), result);
            }else {
                ((SmplVector) vector).getArray().remove(((SmplInt) n).value());
                ((SmplVector) vector).getArray().add((((SmplInt) n).value()), result);
            }
        }

        /*Environment<SmplObj> env = (Environment<SmplObj>) arg;
        ArrayList<IRExp> vars = sd.getVar();
        ArrayList<IRExp> exps = sd.getExp();

        if(vars.size() == exps.size()) {
            for(int i = 0; i<vars.size();i++) {
                IRExp var = vars.get(i);
                SmplObj result = exps.get(i).visit(this,arg);
                if (var instanceof IRExpVar)
                    env.put(((IRExpVar) var).getVar(), result);
                else if (var instanceof IRExpGetIndex) {
                    SmplObj vector = ((IRExpGetIndex) var).getVector().visit(this, arg);
                    SmplObj n = ((IRExpGetIndex) var).getN().visit(this, arg);
                    ((SmplVector) vector).getArray().remove(((SmplInt) n).value());
                    ((SmplVector) vector).getArray().add((((SmplInt) n).value()), result);
                }
            }
        }
        */

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
        }//(val instanceof SmplAlias)
                // = arg.get(((SmplAlias)val).getLocation());
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
        SmplObj val1;
        val1 = exp.getOperand1().visit(this, arg);
        return val1.notBits();
    }

    @Override
    public SmplObj visitExpLO(IRExpLO exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);
        return val1.orLogical(val2);
    }

    @Override
    public SmplObj visitExpLA(IRExpLA exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);
        return val1.andLogical(val2);
    }

    @Override
    public SmplObj visitExpLN(IRExpLN exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj val1;
        val1 = exp.getOperand1().visit(this, arg);
        return val1.notLogical();
    }

    @Override
    public SmplObj visitExpCat(IRExpCat exp, Environment<SmplObj> arg) throws SmplException {
        SmplObj val1, val2;
        val1 = exp.getOperand1().visit(this, arg);
        val2 = exp.getOperand2().visit(this, arg);

        return val1.add(val2);
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
        StmtSequence stmts = exp.getStmts();
        Environment<SmplObj> newEnv = new Environment(arg);
        return stmts.visit(this, newEnv);
    }

    @Override
    public SmplObj visitExpMultiExp(IRExpMultiExp exp, Environment<SmplObj> args)  throws SmplException {
        ArrayList<IRExp> exps = exp.getExps();

        ArrayList<SmplObj> values = new ArrayList<>();
        for (IRExp s: exps) {
            SmplObj val = s.visit(this, args);
            values.add(val);
        }
        return new SmplTuple(values);

    }

    @Override
    public SmplObj visitIRExpIf(IRExpIf stmt, Environment<SmplObj> arg) throws SmplException {

        IRExp predicate = stmt.getPredicate();
        IRExp thenClause = stmt.getThenClause();
        IRExp elseClause = stmt.getElseClause();

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
    public SmplObj visitIRExpCase(IRExpCase exp, Environment<SmplObj> arg) throws SmplException {
        ArrayList<Clause> clauses = exp.getClauses();
        for (Clause clause:clauses) {
            if(clause.getPredicate().visit(this,arg).isTrue())
                return clause.getConsequent().visit(this,arg);
        }

        if(exp.getElseClause() != null){
            return exp.getElseClause().visit(this,arg);
        }else
            return null;

    }

    @Override
    public SmplObj visitStmtDef(StmtDef stmt, Environment<SmplObj> arg) throws SmplException {
        String id = stmt.getVar();
        Environment newEnv = new Environment(arg);
        SmplObj val = stmt.getExp().visit(this, newEnv);

        arg.put(id, val);

        return val;
    }

    @Override
    public SmplObj visitIRExpFor(IRExpFor exp, Environment<SmplObj> arg) throws SmplException {
        IRExp bound = exp.getBound();
        Statement body = exp.getBody();

        String var = exp.getBind().getVar();
        SmplObj start = exp.getBind().getValExp().visit(this,arg);
        Environment<SmplObj> newEnv = new Environment(new String[] {var}, new SmplObj[] {start}, arg);
        int s = ((SmplInt) start).value();
        SmplObj result = SmplObj.DEFAULT;
        if(exp.getType().equals("++")){
            while( ((SmplBool)bound.visit(this,newEnv)).val() ){
                result = body.visit(this, newEnv);
                s+=1;
                newEnv.put(var,new SmplInt(s));
            }
        }else{
            while( ((SmplBool)bound.visit(this,newEnv)).val() ){
                result = body.visit(this, newEnv);
                s-=1;
                newEnv.put(var, new SmplInt(s));
            }
        }
        return result;
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
                    if((a instanceof IRExpVar) && (val instanceof SmplVector))
                        val = new SmplAlias(((IRExpVar)a).getVar(),args);
                }else if (p.getModifier() == "lazy"){
                    val = new SmplPromise(a,args);
                }else if (p.getModifier() == "ref"){
                    if(a instanceof IRExpVar)
                        val = new SmplAlias(((IRExpVar)a).getVar(),args);
                    else
                        val = a.visit(this,args);
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
                    if((a instanceof IRExpVar) && (val instanceof SmplVector))
                        val = new SmplAlias(((IRExpVar)a).getVar(),args);
                }else if (p.getModifier() == "lazy"){
                    val = new SmplPromise(a,args);
                }else if (p.getModifier() == "ref"){
                    if(a instanceof IRExpVar)
                        val = new SmplAlias(((IRExpVar)a).getVar(),args);
                    else
                        val = a.visit(this,args);
                }

                env.put(p.getId(),val);
            }
            if(i < arglst.size() - 1){
                String id = params.get(params.size() - 1).getId();

                ArrayList<SmplObj> temp = new ArrayList<>();
                for (IRExp exp: arglst.subList(i,arglst.size())) {
                    temp.add(exp.visit(this,args));
                }
                SmplList list = new SmplList(temp);

                env.put(id,list);
            }

        }else{
            Parameter p = params.get(0);
            ArrayList<SmplObj> temp = new ArrayList<>();
            if(arglst.get(0) != null  ) {
                for (IRExp exp : arglst) {
                    temp.add(exp.visit(this, args));
                }
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
                String id = params.get(params.size() - 1).getId();

                ArrayList<SmplObj> temp = new ArrayList<>();
                for (SmplObj obj: arglst.subList(i,arglst.size())) {
                    temp.add(obj);
                }
                SmplList list = new SmplList(temp);

                env.put(id,list);
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
    public SmplObj visitStmtPrint(StmtPrint stmt, Environment<SmplObj> arg) throws SmplException{
        if(stmt.getType() == "Line")
            System.out.println(stmt.getExp().visit(this, arg) + "\n");
        else
            System.out.print(stmt.getExp().visit(this, arg) + " ");
        return stmt.getExp().visit(this, arg);
    }

    @Override
    public SmplObj visitRead(Read exp, Environment<SmplObj> arg) {
        Scanner sc = new Scanner(System.in);
        SmplObj result;
        if(exp.getType() == "Int") {
            System.out.println("Enter an integer: ");
            result = new SmplInt(sc.nextInt());
        }else {
            System.out.println("Enter a string: ");
            result = new SmplString(sc.nextLine());
        }

        return result;
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
        SmplObj result = SmplObj.DEFAULT;
        if(pair instanceof SmplPair)
            result =  pair.first();
        else if(pair instanceof SmplList)
            result = ((SmplList)pair).getList().first();

        return result;
    }

    @Override
    public SmplObj visitIRExpCdr(IRExpCdr cdr, Environment<SmplObj> arg) throws SmplException {
        SmplObj pair = arg.get(cdr.getP());
        SmplObj result = SmplObj.DEFAULT;
        if(pair instanceof SmplPair)
            result =  pair.second();
        else if(pair instanceof SmplList)
            result = ((SmplList)pair).getList().second();
        return result;
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

    @Override
    public SmplObj visitIRExpVector(IRExpVector exp, Environment<SmplObj> args) throws SmplException{
        ArrayList<Specification> slst = exp.getSpecLst();
    
        ArrayList<SmplObj> objs = new ArrayList<>();
        for (Specification spec: slst) {
            SmplObj obj = spec.visit(this, args);
            if(obj instanceof SmplList){
                for (SmplObj o: ((SmplList)obj).getArray()) {
                    objs.add(o);
                }
            }else
                objs.add(obj);
        }
        return new SmplVector(objs);
    }

    @Override
    public SmplObj visitSpecification(Specification spec, Environment<SmplObj> args) throws SmplException{

        if (spec.getProc() == null)
            return spec.getExp().visit(this, args);
        else{
            StmtDef func = new StmtDef("spec",spec.getProc());
            func.visit(this, args);
            SmplInt size = (SmplInt) spec.getExp().visit(this, args);
            ArrayList<SmplObj> objs = new ArrayList<>();
            for(int i = 0; i<size.value(); i++){
                IRExpProcCallShort call = new IRExpProcCallShort(new IRExpVar("spec"), new ArrayList<>( Arrays.asList(new IRExpLit(i)) ) );
                objs.add(call.visit(this, args));
            }
            return new SmplList(objs);
        }
    }

    @Override
    public SmplObj visitIRExpGetIndex(IRExpGetIndex exp, Environment<SmplObj> args) throws SmplException {
        SmplObj vector = exp.getVector().visit(this, args);
        SmplObj n = exp.getN().visit(this, args);
        if(vector instanceof SmplAlias) {
            SmplAlias temp = (SmplAlias) vector;
            Environment<SmplObj> env = temp.getEnv();
            return ((SmplVector) env.get(temp.getLocation())).getArray().get(((SmplInt) n).value());
        }else
            return ((SmplVector) vector).getArray().get(((SmplInt) n).value());
    }

    @Override
    public SmplObj visitIRExpGetSize(IRExpGetSize exp, Environment<SmplObj> arg) throws SmplException{
        SmplObj vector = arg.get(exp.getVec());
        if(vector instanceof SmplAlias)
            return ((SmplAlias) vector).getEnv().get(((SmplAlias) vector).getLocation()).size();
        else
            return vector.size();

    }

    @Override
    public SmplObj visitIRExpEquivalent(IRExpEquivalent exp, Environment<SmplObj> arg) throws SmplException{
        SmplObj e1 = arg.get(exp.getE1());
        SmplObj e2 = arg.get(exp.getE2());
        return e1.eq(e2);
    }

    @Override
    public SmplObj visitIRExpEqual(IRExpEqual exp, Environment<SmplObj> arg) throws SmplException{
        SmplObj e1 = arg.get(exp.getE1());
        SmplObj e2 = arg.get(exp.getE2());
        return SmplBool.getConst(e1.equals(e2));
    }

    @Override
    public SmplObj visitIRExpSubStr(IRExpSubStr exp, Environment<SmplObj> arg) throws SmplException {
        String e1 = ((SmplString) arg.get(exp.getE1())).getValue();

        int e2 = ((SmplInt) arg.get(exp.getE2())).value();

        int e3 = ((SmplInt) arg.get(exp.getE3())).value();

        if(e3 < e2)
            return new SmplString("");
        else if(((e2 >= 0) && (e2 < e1.length())) && (e3 <= e1.length()))
            return new SmplString(e1.substring(e2, e3));
        else
            return null;
    }
}
