// generated with ast extension for cup
// version 0.8
// 24/7/2023 18:37:22


package rs.ac.bg.etf.pp1.ast;

public class LastConstNumber extends LastConstDecl {

    private String constName;
    private Assignop Assignop;
    private Integer value;

    public LastConstNumber (String constName, Assignop Assignop, Integer value) {
        this.constName=constName;
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.value=value;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value=value;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Assignop!=null) Assignop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("LastConstNumber(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+value);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [LastConstNumber]");
        return buffer.toString();
    }
}
