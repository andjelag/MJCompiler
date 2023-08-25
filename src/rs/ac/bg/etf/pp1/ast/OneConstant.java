// generated with ast extension for cup
// version 0.8
// 24/7/2023 18:37:22


package rs.ac.bg.etf.pp1.ast;

public class OneConstant extends ConstDeclList {

    private LastConstDecl LastConstDecl;

    public OneConstant (LastConstDecl LastConstDecl) {
        this.LastConstDecl=LastConstDecl;
        if(LastConstDecl!=null) LastConstDecl.setParent(this);
    }

    public LastConstDecl getLastConstDecl() {
        return LastConstDecl;
    }

    public void setLastConstDecl(LastConstDecl LastConstDecl) {
        this.LastConstDecl=LastConstDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(LastConstDecl!=null) LastConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(LastConstDecl!=null) LastConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(LastConstDecl!=null) LastConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneConstant(\n");

        if(LastConstDecl!=null)
            buffer.append(LastConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneConstant]");
        return buffer.toString();
    }
}
