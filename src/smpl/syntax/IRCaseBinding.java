package smpl.syntax;

public class IRCaseBinding {

    private IRExp logic;
    private IRExp result;
    private IRStmtSequence results;

    public IRCaseBinding(IRExp logic, IRExp result) {

        this.logic = logic;
        this.result = result;
    }

    public IRCaseBinding(IRExp logic, IRStmtSequence results)
    {
        this.logic = logic;
        this.results = results;
    }

    public IRExp getLogic()
    {
        return logic;
    }

    public IRExp getResult()
    {
        return this.result;
    }

    public IRExp getResults()
    {
        return this.results;
    }

}
