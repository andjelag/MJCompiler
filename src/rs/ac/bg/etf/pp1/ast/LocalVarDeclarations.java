// generated with ast extension for cup
// version 0.8
// 24/7/2023 18:37:22


package rs.ac.bg.etf.pp1.ast;

public class LocalVarDeclarations extends LocalVarDecl {

    private LocalVarDeclType LocalVarDeclType;
    private LocalVarDeclList LocalVarDeclList;

    public LocalVarDeclarations (LocalVarDeclType LocalVarDeclType, LocalVarDeclList LocalVarDeclList) {
        this.LocalVarDeclType=LocalVarDeclType;
        if(LocalVarDeclType!=null) LocalVarDeclType.setParent(this);
        this.LocalVarDeclList=LocalVarDeclList;
        if(LocalVarDeclList!=null) LocalVarDeclList.setParent(this);
    }

    public LocalVarDeclType getLocalVarDeclType() {
        return LocalVarDeclType;
    }

    public void setLocalVarDeclType(LocalVarDeclType LocalVarDeclType) {
        this.LocalVarDeclType=LocalVarDeclType;
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
        if(LocalVarDeclType!=null) LocalVarDeclType.accept(visitor);
        if(LocalVarDeclList!=null) LocalVarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(LocalVarDeclType!=null) LocalVarDeclType.traverseTopDown(visitor);
        if(LocalVarDeclList!=null) LocalVarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(LocalVarDeclType!=null) LocalVarDeclType.traverseBottomUp(visitor);
        if(LocalVarDeclList!=null) LocalVarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("LocalVarDeclarations(\n");

        if(LocalVarDeclType!=null)
            buffer.append(LocalVarDeclType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LocalVarDeclList!=null)
            buffer.append(LocalVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [LocalVarDeclarations]");
        return buffer.toString();
    }
}
