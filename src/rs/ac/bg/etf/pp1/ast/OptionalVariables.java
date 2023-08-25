// generated with ast extension for cup
// version 0.8
// 24/7/2023 18:37:22


package rs.ac.bg.etf.pp1.ast;

public class OptionalVariables extends OptionalLocalVarDecl {

    private OptionalLocalVarDecl OptionalLocalVarDecl;
    private LocalVarDecl LocalVarDecl;

    public OptionalVariables (OptionalLocalVarDecl OptionalLocalVarDecl, LocalVarDecl LocalVarDecl) {
        this.OptionalLocalVarDecl=OptionalLocalVarDecl;
        if(OptionalLocalVarDecl!=null) OptionalLocalVarDecl.setParent(this);
        this.LocalVarDecl=LocalVarDecl;
        if(LocalVarDecl!=null) LocalVarDecl.setParent(this);
    }

    public OptionalLocalVarDecl getOptionalLocalVarDecl() {
        return OptionalLocalVarDecl;
    }

    public void setOptionalLocalVarDecl(OptionalLocalVarDecl OptionalLocalVarDecl) {
        this.OptionalLocalVarDecl=OptionalLocalVarDecl;
    }

    public LocalVarDecl getLocalVarDecl() {
        return LocalVarDecl;
    }

    public void setLocalVarDecl(LocalVarDecl LocalVarDecl) {
        this.LocalVarDecl=LocalVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalLocalVarDecl!=null) OptionalLocalVarDecl.accept(visitor);
        if(LocalVarDecl!=null) LocalVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalLocalVarDecl!=null) OptionalLocalVarDecl.traverseTopDown(visitor);
        if(LocalVarDecl!=null) LocalVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalLocalVarDecl!=null) OptionalLocalVarDecl.traverseBottomUp(visitor);
        if(LocalVarDecl!=null) LocalVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalVariables(\n");

        if(OptionalLocalVarDecl!=null)
            buffer.append(OptionalLocalVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LocalVarDecl!=null)
            buffer.append(LocalVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalVariables]");
        return buffer.toString();
    }
}
