// generated with ast extension for cup
// version 0.8
// 24/7/2023 18:37:23


package rs.ac.bg.etf.pp1.ast;

public abstract class VisitorAdaptor implements Visitor { 

    public void visit(BeforeLastVarDecl BeforeLastVarDecl) { }
    public void visit(Mulop Mulop) { }
    public void visit(DesignatorAssignStatement DesignatorAssignStatement) { }
    public void visit(Assignop Assignop) { }
    public void visit(LastLocalVarDecl LastLocalVarDecl) { }
    public void visit(ProgDeclList ProgDeclList) { }
    public void visit(StatementList StatementList) { }
    public void visit(LastVarDecl LastVarDecl) { }
    public void visit(Addop Addop) { }
    public void visit(BeforeLastLocalVarDecl BeforeLastLocalVarDecl) { }
    public void visit(Factor Factor) { }
    public void visit(OptionalLocalVarDecl OptionalLocalVarDecl) { }
    public void visit(Designator Designator) { }
    public void visit(Term Term) { }
    public void visit(ConstDeclList ConstDeclList) { }
    public void visit(BeforeLastConstDecl BeforeLastConstDecl) { }
    public void visit(LocalVarDecl LocalVarDecl) { }
    public void visit(VarDeclList VarDeclList) { }
    public void visit(Expr Expr) { }
    public void visit(OptionalConstant OptionalConstant) { }
    public void visit(TypeOrVoid TypeOrVoid) { }
    public void visit(LastConstDecl LastConstDecl) { }
    public void visit(DesignatorStatement DesignatorStatement) { }
    public void visit(Statement Statement) { }
    public void visit(VarDecl VarDecl) { }
    public void visit(ConstDecl ConstDecl) { }
    public void visit(LocalVarDeclList LocalVarDeclList) { }
    public void visit(Type Type) { visit(); }
    public void visit(OpModulus OpModulus) { visit(); }
    public void visit(OpSolidus OpSolidus) { visit(); }
    public void visit(OpTimes OpTimes) { visit(); }
    public void visit(OpMinus OpMinus) { visit(); }
    public void visit(OpPlus OpPlus) { visit(); }
    public void visit(OpEqual OpEqual) { visit(); }
    public void visit(IdentForArrayWhenUsingElement IdentForArrayWhenUsingElement) { visit(); }
    public void visit(NotArrayElementDesignator NotArrayElementDesignator) { visit(); }
    public void visit(ArrayElementDesignator ArrayElementDesignator) { visit(); }
    public void visit(FactorInParanthesis FactorInParanthesis) { visit(); }
    public void visit(FactorNewConstructor FactorNewConstructor) { visit(); }
    public void visit(FactorBool FactorBool) { visit(); }
    public void visit(FactorChar FactorChar) { visit(); }
    public void visit(FactorNumber FactorNumber) { visit(); }
    public void visit(FactorDesignator FactorDesignator) { visit(); }
    public void visit(SingleFactor SingleFactor) { visit(); }
    public void visit(MultipleFactors MultipleFactors) { visit(); }
    public void visit(SingleNegativeTerm SingleNegativeTerm) { visit(); }
    public void visit(SinglePositiveTerm SinglePositiveTerm) { visit(); }
    public void visit(MultipleTerms MultipleTerms) { visit(); }
    public void visit(ErrorInAssignment ErrorInAssignment) { visit(); }
    public void visit(DesignatorAssign DesignatorAssign) { visit(); }
    public void visit(DesignatorStatementDecrement DesignatorStatementDecrement) { visit(); }
    public void visit(DesignatorStatementIncrement DesignatorStatementIncrement) { visit(); }
    public void visit(DesignatorStatementAssignment DesignatorStatementAssignment) { visit(); }
    public void visit(NoOptionalConstant NoOptionalConstant) { visit(); }
    public void visit(HasOptionalConstant HasOptionalConstant) { visit(); }
    public void visit(StatementFindAny StatementFindAny) { visit(); }
    public void visit(StatementPrint StatementPrint) { visit(); }
    public void visit(StatementRead StatementRead) { visit(); }
    public void visit(StatementDesignator StatementDesignator) { visit(); }
    public void visit(NoStatements NoStatements) { visit(); }
    public void visit(Statements Statements) { visit(); }
    public void visit(NoOptonalVariables NoOptonalVariables) { visit(); }
    public void visit(OptionalVariables OptionalVariables) { visit(); }
    public void visit(NoType NoType) { visit(); }
    public void visit(HasType HasType) { visit(); }
    public void visit(MethodName MethodName) { visit(); }
    public void visit(MethodDecl MethodDecl) { visit(); }
    public void visit(LastLocalVarDeclIsArray LastLocalVarDeclIsArray) { visit(); }
    public void visit(LastLocalVarDeclNotArray LastLocalVarDeclNotArray) { visit(); }
    public void visit(BeforeLastLocalVarDeclIsArray BeforeLastLocalVarDeclIsArray) { visit(); }
    public void visit(BeforeLastLocalVarDeclNotArray BeforeLastLocalVarDeclNotArray) { visit(); }
    public void visit(ErrorInLastVarDeclarationStatement ErrorInLastVarDeclarationStatement) { visit(); }
    public void visit(LastVarDeclIsArray LastVarDeclIsArray) { visit(); }
    public void visit(LastVarDeclNotArray LastVarDeclNotArray) { visit(); }
    public void visit(ErrorInBeforeLastVarDeclarationStatement ErrorInBeforeLastVarDeclarationStatement) { visit(); }
    public void visit(BeforeLastVarDeclIsArray BeforeLastVarDeclIsArray) { visit(); }
    public void visit(BeforeLastVarDeclNotArray BeforeLastVarDeclNotArray) { visit(); }
    public void visit(LocalOneVariable LocalOneVariable) { visit(); }
    public void visit(LocalMultipleVariables LocalMultipleVariables) { visit(); }
    public void visit(LocalVarDeclType LocalVarDeclType) { visit(); }
    public void visit(LocalVarDeclarations LocalVarDeclarations) { visit(); }
    public void visit(OneVariable OneVariable) { visit(); }
    public void visit(MulitpleVariables MulitpleVariables) { visit(); }
    public void visit(VarDeclType VarDeclType) { visit(); }
    public void visit(VarDeclarations VarDeclarations) { visit(); }
    public void visit(LastConstBool LastConstBool) { visit(); }
    public void visit(LastConstChar LastConstChar) { visit(); }
    public void visit(LastConstNumber LastConstNumber) { visit(); }
    public void visit(BeforeLastConstBool BeforeLastConstBool) { visit(); }
    public void visit(BeforeLastConstChar BeforeLastConstChar) { visit(); }
    public void visit(BeforeLastConstNumber BeforeLastConstNumber) { visit(); }
    public void visit(OneConstant OneConstant) { visit(); }
    public void visit(MulitpleConstants MulitpleConstants) { visit(); }
    public void visit(ConstDeclType ConstDeclType) { visit(); }
    public void visit(ConstDeclarations ConstDeclarations) { visit(); }
    public void visit(NoDeclarations NoDeclarations) { visit(); }
    public void visit(ProgramVarDeclarations ProgramVarDeclarations) { visit(); }
    public void visit(ProgramConstDeclarations ProgramConstDeclarations) { visit(); }
    public void visit(ProgName ProgName) { visit(); }
    public void visit(Program Program) { visit(); }


    public void visit() { }
}
