// generated with ast extension for cup
// version 0.8
// 24/7/2023 18:37:22


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementAssignment extends DesignatorStatement {

    private DesignatorAssignStatement DesignatorAssignStatement;

    public DesignatorStatementAssignment (DesignatorAssignStatement DesignatorAssignStatement) {
        this.DesignatorAssignStatement=DesignatorAssignStatement;
        if(DesignatorAssignStatement!=null) DesignatorAssignStatement.setParent(this);
    }

    public DesignatorAssignStatement getDesignatorAssignStatement() {
        return DesignatorAssignStatement;
    }

    public void setDesignatorAssignStatement(DesignatorAssignStatement DesignatorAssignStatement) {
        this.DesignatorAssignStatement=DesignatorAssignStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorAssignStatement!=null) DesignatorAssignStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorAssignStatement!=null) DesignatorAssignStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorAssignStatement!=null) DesignatorAssignStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementAssignment(\n");

        if(DesignatorAssignStatement!=null)
            buffer.append(DesignatorAssignStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementAssignment]");
        return buffer.toString();
    }
}
