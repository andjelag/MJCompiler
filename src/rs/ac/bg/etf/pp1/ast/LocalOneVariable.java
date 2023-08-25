// generated with ast extension for cup
// version 0.8
// 24/7/2023 18:37:22


package rs.ac.bg.etf.pp1.ast;

public class LocalOneVariable extends LocalVarDeclList {

    private LastLocalVarDecl LastLocalVarDecl;

    public LocalOneVariable (LastLocalVarDecl LastLocalVarDecl) {
        this.LastLocalVarDecl=LastLocalVarDecl;
        if(LastLocalVarDecl!=null) LastLocalVarDecl.setParent(this);
    }

    public LastLocalVarDecl getLastLocalVarDecl() {
        return LastLocalVarDecl;
    }

    public void setLastLocalVarDecl(LastLocalVarDecl LastLocalVarDecl) {
        this.LastLocalVarDecl=LastLocalVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(LastLocalVarDecl!=null) LastLocalVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(LastLocalVarDecl!=null) LastLocalVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(LastLocalVarDecl!=null) LastLocalVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("LocalOneVariable(\n");

        if(LastLocalVarDecl!=null)
            buffer.append(LastLocalVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [LocalOneVariable]");
        return buffer.toString();
    }
}
