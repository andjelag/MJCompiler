// generated with ast extension for cup
// version 0.8
// 24/7/2023 18:37:22


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private TypeOrVoid TypeOrVoid;
    private MethodName MethodName;
    private OptionalLocalVarDecl OptionalLocalVarDecl;
    private StatementList StatementList;

    public MethodDecl (TypeOrVoid TypeOrVoid, MethodName MethodName, OptionalLocalVarDecl OptionalLocalVarDecl, StatementList StatementList) {
        this.TypeOrVoid=TypeOrVoid;
        if(TypeOrVoid!=null) TypeOrVoid.setParent(this);
        this.MethodName=MethodName;
        if(MethodName!=null) MethodName.setParent(this);
        this.OptionalLocalVarDecl=OptionalLocalVarDecl;
        if(OptionalLocalVarDecl!=null) OptionalLocalVarDecl.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public TypeOrVoid getTypeOrVoid() {
        return TypeOrVoid;
    }

    public void setTypeOrVoid(TypeOrVoid TypeOrVoid) {
        this.TypeOrVoid=TypeOrVoid;
    }

    public MethodName getMethodName() {
        return MethodName;
    }

    public void setMethodName(MethodName MethodName) {
        this.MethodName=MethodName;
    }

    public OptionalLocalVarDecl getOptionalLocalVarDecl() {
        return OptionalLocalVarDecl;
    }

    public void setOptionalLocalVarDecl(OptionalLocalVarDecl OptionalLocalVarDecl) {
        this.OptionalLocalVarDecl=OptionalLocalVarDecl;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TypeOrVoid!=null) TypeOrVoid.accept(visitor);
        if(MethodName!=null) MethodName.accept(visitor);
        if(OptionalLocalVarDecl!=null) OptionalLocalVarDecl.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TypeOrVoid!=null) TypeOrVoid.traverseTopDown(visitor);
        if(MethodName!=null) MethodName.traverseTopDown(visitor);
        if(OptionalLocalVarDecl!=null) OptionalLocalVarDecl.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TypeOrVoid!=null) TypeOrVoid.traverseBottomUp(visitor);
        if(MethodName!=null) MethodName.traverseBottomUp(visitor);
        if(OptionalLocalVarDecl!=null) OptionalLocalVarDecl.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl(\n");

        if(TypeOrVoid!=null)
            buffer.append(TypeOrVoid.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodName!=null)
            buffer.append(MethodName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalLocalVarDecl!=null)
            buffer.append(OptionalLocalVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecl]");
        return buffer.toString();
    }
}
