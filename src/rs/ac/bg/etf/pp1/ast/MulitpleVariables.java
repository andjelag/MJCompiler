// generated with ast extension for cup
// version 0.8
// 24/7/2023 18:37:22


package rs.ac.bg.etf.pp1.ast;

public class MulitpleVariables extends VarDeclList {

    private BeforeLastVarDecl BeforeLastVarDecl;
    private VarDeclList VarDeclList;

    public MulitpleVariables (BeforeLastVarDecl BeforeLastVarDecl, VarDeclList VarDeclList) {
        this.BeforeLastVarDecl=BeforeLastVarDecl;
        if(BeforeLastVarDecl!=null) BeforeLastVarDecl.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
    }

    public BeforeLastVarDecl getBeforeLastVarDecl() {
        return BeforeLastVarDecl;
    }

    public void setBeforeLastVarDecl(BeforeLastVarDecl BeforeLastVarDecl) {
        this.BeforeLastVarDecl=BeforeLastVarDecl;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(BeforeLastVarDecl!=null) BeforeLastVarDecl.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BeforeLastVarDecl!=null) BeforeLastVarDecl.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BeforeLastVarDecl!=null) BeforeLastVarDecl.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MulitpleVariables(\n");

        if(BeforeLastVarDecl!=null)
            buffer.append(BeforeLastVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MulitpleVariables]");
        return buffer.toString();
    }
}
