package rs.ac.bg.etf.pp1;

import java.util.Collection;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPc;

	public int getMainPc() {
		return mainPc;
	}

	private Obj mainMethod;

	// **************************** Statements ****************************

	@Override
	public void visit(StatementPrint StatementPrint) {
		if (StatementPrint.getExpr().struct.getKind() == Struct.Char) {
			if (StatementPrint.getOptionalConstant() instanceof HasOptionalConstant) {
				HasOptionalConstant hasConstant = (HasOptionalConstant) StatementPrint.getOptionalConstant();
				Code.loadConst(hasConstant.getN1());
			} else {
				Code.loadConst(5);
			}
			Code.put(Code.bprint);
		} else {
			if (StatementPrint.getOptionalConstant() instanceof HasOptionalConstant) {
				HasOptionalConstant hasConstant = (HasOptionalConstant) StatementPrint.getOptionalConstant();
				Code.loadConst(hasConstant.getN1());
			} else {
				Code.loadConst(5);
			}
			Code.put(Code.print);
		}
	}

	@Override
	public void visit(StatementRead StatementRead) {
		if (StatementRead.getDesignator().obj.getType() == SpecifiedTab.charType) {
			Code.put(Code.bread);
		} else {
			Code.put(Code.read);
		}

		if (StatementRead.getDesignator().obj.getFpPos() == 1) {
			Code.put(Code.astore);
		} else {
			Code.store(StatementRead.getDesignator().obj);
		}
	}

	@Override
	public void visit(StatementFindAny StatementFindAny) {
		Collection<Obj> mainLocals = mainMethod.getLocalSymbols();

		if (StatementFindAny.getDesignator1().obj.getFpPos() == 1) {
			Code.put(Code.aload);
		} else {
			Code.load(StatementFindAny.getDesignator1().obj);
		}
		Code.put(Code.arraylength); // uzima duzinu niza

		Code.loadConst(1);
		
		Code.put(Code.sub); // krece od kraja niza da ne cuvam dve promenljive

	
		Obj index = SpecifiedTab.insert(Obj.Var, ".", SpecifiedTab.intType);
		index.setAdr(mainLocals.size());
		Code.store(index);

		int checkElement = Code.pc;
//checkElement:		
		Code.put(Code.dup);
		if (StatementFindAny.getDesignator1().obj.getFpPos() == 1) {
			Code.put(Code.aload);
		} else {
			Code.load(StatementFindAny.getDesignator1().obj);
		}
		Code.load(index);
		Code.put(Code.aload);

		int patchAddressForJumpIfExprInArray = Code.pc;
		Code.putFalseJump(Code.ne, Code.pc - 1); // expr is in array

		// ako se expr ne podudara sa trenutnim elementom:
		Code.load(index);
		Code.loadConst(1);
		Code.put(Code.sub);

		Code.put(Code.dup); // dupliramo da posle vrednost ostane i za cuvanje u promenljivu index

		Code.loadConst(0);
		int patchAddressForJumpIfAllElementsChecked = Code.pc;
		Code.putFalseJump(Code.ge, Code.pc - 1); // expr not in array <- kraj prolaska kroz elemente

		Code.store(index); // zbog ovoga smo dekrementiranu vrednost duplirali
		Code.putJump(checkElement);

// expr in array : 
		Code.fixup(patchAddressForJumpIfExprInArray + 1); // + 1 zbog jednog bajta koda instrukcije za skok
		Code.put(Code.pop);
		Code.loadConst(1); 
		//preskace sve dalje i ide na upis vrednosti 
		int patchAddressToSkipToFunctionExit = Code.pc;
		Code.put(Code.jmp);
		Code.put2(0);
// expr not in array: 
		Code.fixup(patchAddressForJumpIfAllElementsChecked + 1);
		Code.put(Code.pop); // indeks
		Code.put(Code.pop); // expr
		Code.loadConst(0);

		Code.fixup(patchAddressToSkipToFunctionExit + 1);

		if (StatementFindAny.getDesignator().obj.getFpPos() == 1) {
			Code.put(Code.astore);
		} else {
			Code.store(StatementFindAny.getDesignator().obj);
		}

	}

	// **************************** Methods ****************************

	@Override
	public void visit(MethodName MethodName) {
		MethodName.obj.setAdr(Code.pc);
		mainPc = Code.pc;

		Code.put(Code.enter);
		Code.put(0);
		Code.put(MethodName.obj.getLocalSymbols().size());
		//ako indeks pravi problem zameniti : 
//		Code.put(MethodName.obj.getLocalSymbols().size() + 1);
		mainMethod = MethodName.obj;
	}

	@Override
	public void visit(MethodDecl MethodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	// ************************** Designator Statements **************************

	@Override
	public void visit(DesignatorAssign DesignatorAssign) {
		if (DesignatorAssign.getDesignator().obj.getFpPos() == 1) {
			Code.put(Code.astore);
		} else {
			Code.store(DesignatorAssign.getDesignator().obj);
		}
	}

	@Override
	public void visit(DesignatorStatementIncrement DesignatorStatementIncrement) {
		if (DesignatorStatementIncrement.getDesignator().obj.getKind() == Obj.Elem) { // element niza se vec nalazi na
																						// steku
			Code.put(Code.dup2);
		}
		// ako je element niz vec se nalazi sve na steku pa ovo samo ispisuje aload, a
		// ako nije onda taman ucitava jer nije bilo na steku
		if (DesignatorStatementIncrement.getDesignator().obj.getFpPos() == 1) {
			Code.put(Code.aload);
		} else {
			Code.load(DesignatorStatementIncrement.getDesignator().obj);
		}

		Code.loadConst(1);
		Code.put(Code.add);

		if (DesignatorStatementIncrement.getDesignator().obj.getFpPos() == 1) {
			Code.put(Code.astore);
		} else {
			Code.store(DesignatorStatementIncrement.getDesignator().obj);
		}
	}

	@Override
	public void visit(DesignatorStatementDecrement DesignatorStatementDecrement) {
		if (DesignatorStatementDecrement.getDesignator().obj.getKind() == Obj.Elem) { // element niza se vec nalazi na
																						// steku
			Code.put(Code.dup2);
		}
		// ako je element niz vec se nalazi sve na steku pa ovo samo ispisuje aload, a
		// ako nije onda taman ucitava jer nije bilo na steku
		
		if (DesignatorStatementDecrement.getDesignator().obj.getFpPos() == 1) {
			Code.put(Code.aload);
		} else {
			Code.load(DesignatorStatementDecrement.getDesignator().obj);
		}

		Code.loadConst(1);
		Code.put(Code.sub);

		if (DesignatorStatementDecrement.getDesignator().obj.getFpPos() == 1) {
			Code.put(Code.astore);
		} else {
			Code.store(DesignatorStatementDecrement.getDesignator().obj);
		}
	}

	// **************************** Expr ****************************

	@Override
	public void visit(MultipleTerms MultipleTerms) {
		if (MultipleTerms.getAddop() instanceof OpPlus) {
			Code.put(Code.add);
		} else { // oduzimanje
			Code.put(Code.sub);
		}
	}

	@Override
	public void visit(SingleNegativeTerm SingleNegativeTerm) {
		Code.put(Code.neg);
	}

	// **************************** Term ****************************

	@Override
	public void visit(MultipleFactors MultipleFactors) {
		if (MultipleFactors.getMulop() instanceof OpTimes) {
			Code.put(Code.mul);
		} else if (MultipleFactors.getMulop() instanceof OpSolidus) {
			Code.put(Code.div);
		} else { // mod tj reminder
			Code.put(Code.rem);
		}
	}

	// **************************** Factors ****************************

	@Override
	public void visit(FactorNumber FactorNumber) {
		Obj con = SpecifiedTab.insert(Obj.Con, "$", FactorNumber.struct);
		con.setLevel(0);
		con.setAdr(FactorNumber.getN1());

		Code.load(con);
	}

	@Override
	public void visit(FactorBool FactorBool) {
		Obj con = SpecifiedTab.insert(Obj.Con, "$", FactorBool.struct);
		con.setLevel(0);
		con.setAdr((FactorBool.getB1()) ? 1 : 0);

		Code.load(con);
	}

	@Override
	public void visit(FactorChar FactorChar) {
		Obj con = SpecifiedTab.insert(Obj.Con, "$", FactorChar.struct);
		con.setLevel(0);
		con.setAdr(FactorChar.getC1());

		Code.load(con);
	}

	@Override
	public void visit(FactorDesignator FactorDesignator) {
		// ovo odgovara za print
		if (FactorDesignator.getDesignator().obj.getFpPos() == 1) {
			Code.put(Code.aload);
		} else {
			Code.load(FactorDesignator.getDesignator().obj);
		}
	}

	// FactorInParanthesis -> Trebalo bi da je expr vec na steku

	@Override
	public void visit(FactorNewConstructor FactorNewConstructor) {
		Code.put(Code.newarray);
		if (FactorNewConstructor.getType().struct == SpecifiedTab.charType) {
			// niz bajtova
			Code.put(0);
		} else {
			// niz reci
			Code.put(1);
		}
	}

	// **************************** Designator ****************************

	@Override
	public void visit(IdentForArrayWhenUsingElement IdentForArrayWhenUsingElement) {
		Code.load(IdentForArrayWhenUsingElement.obj);
	}

	@Override
	public void visit(ArrayElementDesignator ArrayElementDesignator) {
		ArrayElementDesignator.obj.setFpPos(1);
	}

	@Override
	public void visit(NotArrayElementDesignator NotArrayElementDesignator) {
		NotArrayElementDesignator.obj.setFpPos(0);
	}
}