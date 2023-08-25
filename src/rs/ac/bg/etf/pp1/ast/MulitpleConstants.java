// generated with ast extension for cup
// version 0.8
// 24/7/2023 18:37:22


package rs.ac.bg.etf.pp1.ast;

public class MulitpleConstants extends ConstDeclList {

    private BeforeLastConstDecl BeforeLastConstDecl;
    private ConstDeclList ConstDeclList;

    public MulitpleConstants (BeforeLastConstDecl BeforeLastConstDecl, ConstDeclList ConstDeclList) {
        this.BeforeLastConstDecl=BeforeLastConstDecl;
        if(BeforeLastConstDecl!=null) BeforeLastConstDecl.setParent(this);
        this.ConstDeclList=ConstDeclList;
        if(ConstDeclList!=null) ConstDeclList.setParent(this);
    }

    public BeforeLastConstDecl getBeforeLastConstDecl() {
        return BeforeLastConstDecl;
    }

    public void setBeforeLastConstDecl(BeforeLastConstDecl BeforeLastConstDecl) {
        this.BeforeLastConstDecl=BeforeLastConstDecl;
    }

    public ConstDeclList getConstDeclList() {
        return ConstDeclList;
    }

    public void setConstDeclList(ConstDeclList ConstDeclList) {
        this.ConstDeclList=ConstDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(BeforeLastConstDecl!=null) BeforeLastConstDecl.accept(visitor);
        if(ConstDeclList!=null) ConstDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BeforeLastConstDecl!=null) BeforeLastConstDecl.traverseTopDown(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BeforeLastConstDecl!=null) BeforeLastConstDecl.traverseBottomUp(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MulitpleConstants(\n");

        if(BeforeLastConstDecl!=null)
            buffer.append(BeforeLastConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclList!=null)
            buffer.append(ConstDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MulitpleConstants]");
        return buffer.toString();
    }
}
