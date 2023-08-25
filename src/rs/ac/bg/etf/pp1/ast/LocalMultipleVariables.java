// generated with ast extension for cup
// version 0.8
// 24/7/2023 18:37:22


package rs.ac.bg.etf.pp1.ast;

public class LocalMultipleVariables extends LocalVarDeclList {

    private BeforeLastLocalVarDecl BeforeLastLocalVarDecl;
    private LocalVarDeclList LocalVarDeclList;

    public LocalMultipleVariables (BeforeLastLocalVarDecl BeforeLastLocalVarDecl, LocalVarDeclList LocalVarDeclList) {
        this.BeforeLastLocalVarDecl=BeforeLastLocalVarDecl;
        if(BeforeLastLocalVarDecl!=null) BeforeLastLocalVarDecl.setParent(this);
        this.LocalVarDeclList=LocalVarDeclList;
        if(LocalVarDeclList!=null) LocalVarDeclList.setParent(this);
    }

    public BeforeLastLocalVarDecl getBeforeLastLocalVarDecl() {
        return BeforeLastLocalVarDecl;
    }

    public void setBeforeLastLocalVarDecl(BeforeLastLocalVarDecl BeforeLastLocalVarDecl) {
        this.BeforeLastLocalVarDecl=BeforeLastLocalVarDecl;
    }

    public LocalVarDeclList getLocalVarDeclList() {
        return LocalVarDeclList;
    }

    public void setLocalVarDeclList(LocalVarDeclList LocalVarDeclList) {
        this.LocalVarDeclList=LocalVarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(BeforeLastLocalVarDecl!=null) BeforeLastLocalVarDecl.accept(visitor);
        if(LocalVarDeclList!=null) LocalVarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BeforeLastLocalVarDecl!=null) BeforeLastLocalVarDecl.traverseTopDown(visitor);
        if(LocalVarDeclList!=null) LocalVarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BeforeLastLocalVarDecl!=null) BeforeLastLocalVarDecl.traverseBottomUp(visitor);
        if(LocalVarDeclList!=null) LocalVarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("LocalMultipleVariables(\n");

        if(BeforeLastLocalVarDecl!=null)
            buffer.append(BeforeLastLocalVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LocalVarDeclList!=null)
            buffer.append(LocalVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [LocalMultipleVariables]");
        return buffer.toString();
    }
}
