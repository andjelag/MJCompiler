// generated with ast extension for cup
// version 0.8
// 24/7/2023 18:37:22


package rs.ac.bg.etf.pp1.ast;

public class ArrayElementDesignator extends Designator {

    private IdentForArrayWhenUsingElement IdentForArrayWhenUsingElement;
    private Expr Expr;

    public ArrayElementDesignator (IdentForArrayWhenUsingElement IdentForArrayWhenUsingElement, Expr Expr) {
        this.IdentForArrayWhenUsingElement=IdentForArrayWhenUsingElement;
        if(IdentForArrayWhenUsingElement!=null) IdentForArrayWhenUsingElement.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public IdentForArrayWhenUsingElement getIdentForArrayWhenUsingElement() {
        return IdentForArrayWhenUsingElement;
    }

    public void setIdentForArrayWhenUsingElement(IdentForArrayWhenUsingElement IdentForArrayWhenUsingElement) {
        this.IdentForArrayWhenUsingElement=IdentForArrayWhenUsingElement;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentForArrayWhenUsingElement!=null) IdentForArrayWhenUsingElement.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentForArrayWhenUsingElement!=null) IdentForArrayWhenUsingElement.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentForArrayWhenUsingElement!=null) IdentForArrayWhenUsingElement.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ArrayElementDesignator(\n");

        if(IdentForArrayWhenUsingElement!=null)
            buffer.append(IdentForArrayWhenUsingElement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ArrayElementDesignator]");
        return buffer.toString();
    }
}
