package smpl.semantics;

import smpl.builtIn_necessities.*;
import smpl.syntax.*;
import smpl.syntax.IRExpProc.*;
import smpl.sys.SmplException;

/**
 *
 * @author newts
 * @param <S> The type of the state passed down to children nodes while visiting
 * @param <T> The return type of the visit process
 */
public interface Visitor<S, T> {

    // program

    /**
     * Visit a top level Smpl program
     * @param p the value of p
     * @param state the value of state
     * @return the T
     * @throws smpl.sys.SmplException
     */
    public T visitSmplProgram(SmplProgram p, S state)
	throws SmplException;

    // statements

    /**
     * Visit an expression being used as a statement
     * @param exp the expression / statement
     * @param state the context for the visitor.
     * @return the result of the visit
     * @throws SmplException if an error arises during the visit
     */
    public T visitStmtExpr(StmtExpr exp, S state)
	throws SmplException ;

    /**
     * Visit a sequence of statements
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitStmtSequence(StmtSequence exp, S state)
	throws SmplException ;

    /**
     * Visit a statement definition
     * @param sd the value of sd
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitStmtDefinition(StmtDefinition sd, S state)
	throws SmplException;

    /**
     * Visit an addition expression
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitExpAdd(IRExpAdd exp, S state)
	throws SmplException ;

    /**
     * Visit a subtraction expression
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitExpSub(IRExpSub exp, S state)
	throws SmplException;

    /**
     * Visit a multiplication expression
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitExpMul(IRExpMul exp, S state)
	throws SmplException;

    /**
     * Visit a division expression
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitExpDiv(IRExpDiv exp, S state)
	throws SmplException;

    /**
     * Visit a modulo expression
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitExpMod(IRExpMod exp, S state)
	throws SmplException;

    public T visitExpExpt(IRExpExpt exp, S state) throws SmplException;

    /**
     * Visit a literal expression (Eg. an integer or character)
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitExpLit(IRExpLit exp, S state)
            throws SmplException;

    /**
     * Visit a variable expression
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitExpVar(IRExpVar exp, S state)
            throws SmplException;

    public T visitExpLO(IRExpLO exp, S state) throws SmplException;

    public T visitExpLA(IRExpLA exp, S state) throws SmplException;

    public T visitExpLN(IRExpLN exp, S state) throws SmplException;

    public T visitExpCat(IRExpCat exp, S state) throws SmplException;

    //Relational operations
    public T visitExpEqv(IRExpEqv exp, S state) throws SmplException;

    public T visitExpLst(IRExpLst exp, S state) throws SmplException;

    public T visitExpLqt(IRExpLqt exp, S state) throws SmplException;

    public T visitExpGrt(IRExpGrt exp, S state) throws SmplException;

    public T visitExpGqt(IRExpGqt exp, S state) throws SmplException;

    public T visitExpNqt(IRExpNqt exp, S state) throws SmplException;

    //bitwise operations
    public T visitExpBand(IRExpBand exp, S state) throws SmplException;

    public T visitExpBor(IRExpBor exp, S state) throws SmplException;

    public T visitExpBnot(IRExpBnot exp, S state) throws SmplException;


    // additionals
    public T visitIRExpLet(IRExpLet sd, S state) throws SmplException;

    public T visitExpCompExp(IRExpCompExp exp, S state) throws SmplException;

    public T visitExpMultiExp(IRExpMultiExp exp, S state) throws SmplException;

    public T visitIRExpIf(IRExpIf exp, S state) throws SmplException;

    public T visitIRExpFor(IRExpFor exp, S state) throws SmplException;

    public T visitIRExpCase(IRExpCase exp, S state) throws SmplException;

    public T visitStmtDef(StmtDef stmt, S state) throws SmplException;

    public T visitStmtPrint(StmtPrint stmt, S state) throws SmplException;

    public T visitRead(Read exp, S state) throws SmplException;

    public T visitExprStmt(ExprStmt stmt, S state) throws SmplException;

    public T visitIRExpProc(IRExpProc exp, S state) throws SmplException;

    public T visitIRExpProcCallShort(IRExpProcCallShort call, S state) throws SmplException;
    public T visitIRExpProcCallFull(IRExpProcCallFull call, S state) throws SmplException;


    public T visitIRExpCreatePair(IRExpCreatePair exp, S state) throws SmplException;

    public T visitIRExpCar(IRExpCar car, S state) throws SmplException;

    public T visitIRExpCdr(IRExpCdr cdr, S state) throws SmplException;

    public T visitIRExpPairQ(IRExpPairQ pq, S state) throws SmplException;

    public T visitIRExpCreateList(IRExpCreateList exp, S state) throws SmplException;

    public T visitIRExpVector(IRExpVector exp, S state) throws SmplException;

    public T visitSpecification(Specification exp, S state) throws SmplException;

    public T visitIRExpGetIndex(IRExpGetIndex exp, S state) throws SmplException;

    public T visitIRExpGetSize(IRExpGetSize exp, S state) throws SmplException;

    public T visitIRExpEquivalent(IRExpEquivalent exp, S arg) throws SmplException;

    public T visitIRExpEqual(IRExpEqual exp, S arg) throws SmplException;

    public T visitIRExpSubStr(IRExpSubStr exp, S arg) throws SmplException;

}
