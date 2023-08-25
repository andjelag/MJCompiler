package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.HashTableDataStructure;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class SemanticAnalyzer extends VisitorAdaptor {

	boolean errorDetected = false;
	int printCallCount = 0;
	boolean returnFound = false;
	int nVars = 0;

	SpecifiedDumpSymbolTableVisitor stv = new SpecifiedDumpSymbolTableVisitor();

	Struct currentType = null;
	Obj currentMethod = null;

	Struct arrayTypeInt = null, arrayTypeBool = null, arrayTypeChar = null;

	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	// ****************************** Program *********************************

	@Override
	public void visit(Program Program) {
		SpecifiedTab.chainLocalSymbols(Program.getProgName().obj);
		nVars = SpecifiedTab.currentScope.getnVars();
		SpecifiedTab.closeScope();
	}

	@Override
	public void visit(ProgName ProgName) {
		ProgName.obj = SpecifiedTab.insert(Obj.Prog, ProgName.getProgName(), SpecifiedTab.noType);
		SpecifiedTab.openScope();
	}

	// ****************************** Type *********************************

	@Override
	public void visit(Type Type) {
		Obj foundType = SpecifiedTab.find(Type.getTypeName());
		if (foundType == SpecifiedTab.noObj) {
			report_error("Ne postoji tip " + Type.getTypeName() + " u tabeli simbola!", null);
			errorDetected = true;
			Type.struct = SpecifiedTab.noType;
		} else if (Obj.Type == foundType.getKind()) {
			currentType = Type.struct = foundType.getType();
		} else {
			report_error("Simbol " + Type.getTypeName() + " nije tip podatka!", Type);
			errorDetected = true;
			Type.struct = SpecifiedTab.noType;
		}

	}

	// ****************************** Variables *********************************

	@Override
	public void visit(VarDeclType VarDeclType) {
		if (currentType == null) {
			report_error("Invalidno mesto za definisanje tipa!", VarDeclType);
			errorDetected = true;
			return;
		}
		currentType = VarDeclType.getType().struct;
	}

	@Override
	public void visit(BeforeLastVarDeclNotArray BeforeLastVarDeclNotArray) {
		String variableName = BeforeLastVarDeclNotArray.getVarName();
		Obj variable = SpecifiedTab.find(variableName);
		if (variable != SpecifiedTab.noObj) {
			report_error("Greska na " + BeforeLastVarDeclNotArray.getLine() + ": " + variableName + " vec deklarisano",
					null);
			errorDetected = true;
		} else {
			variable = SpecifiedTab.insert(Obj.Var, variableName, currentType);
			if (variable != SpecifiedTab.noObj) {
				report_info("Deklarisana promenljiva " + variableName, BeforeLastVarDeclNotArray);
			}
		}
	}

	@Override
	public void visit(BeforeLastVarDeclIsArray BeforeLastVarDeclIsArray) {
		String variableName = BeforeLastVarDeclIsArray.getVarName();
		Obj variable = SpecifiedTab.find(variableName);
		if (variable != SpecifiedTab.noObj) {
			report_error("Greska na " + BeforeLastVarDeclIsArray.getLine() + ": " + variableName + " vec deklarisano",
					null);
			errorDetected = true;
		} else {
			int structKind = currentType.getKind();
			Struct arrayType = getArrayStruct(structKind);
			if (arrayType == null) {
				arrayType = createStructOfArrayType(structKind);
			}
			variable = SpecifiedTab.insert(Obj.Var, variableName, arrayType);
			if (variable != SpecifiedTab.noObj) {
				report_info("Deklarisana promenljiva " + variableName, BeforeLastVarDeclIsArray);
			}
		}
	}

	public Struct getArrayStruct(int kind) {
		switch (kind) {
		case 1:
			return arrayTypeInt;
		case 2:
			return arrayTypeChar;
		case 5:
			return arrayTypeBool;
		default:
			return null;
		}
	}

	public Struct createStructOfArrayType(int kind) {
		switch (kind) {
		case 1: {
			arrayTypeInt = new Struct(Struct.Array, SpecifiedTab.intType);
			return arrayTypeInt;
		}
		case 2: {
			arrayTypeChar = new Struct(Struct.Array, SpecifiedTab.charType);
			return arrayTypeChar;
		}
		case 5: {
			arrayTypeBool = new Struct(Struct.Array, SpecifiedTab.boolType);
			return arrayTypeBool;
		}
		default:
			return null;
		}
	}

	@Override
	public void visit(LastVarDeclNotArray LastVarDeclNotArray) {
		String variableName = LastVarDeclNotArray.getVarName();
		Obj variable = SpecifiedTab.find(variableName);
		if (variable != SpecifiedTab.noObj) {
			report_error("Greska na " + LastVarDeclNotArray.getLine() + ": " + variableName + " vec deklarisano", null);
			errorDetected = true;
		} else {
			variable = SpecifiedTab.insert(Obj.Var, variableName, currentType);
			if (variable != SpecifiedTab.noObj) {
				report_info("Deklarisana promenljiva " + variableName, LastVarDeclNotArray);
			}
		}
		currentType = null;
	}

	@Override
	public void visit(LastVarDeclIsArray LastVarDeclIsArray) {
		String variableName = LastVarDeclIsArray.getVarName();
		Obj variable = SpecifiedTab.find(variableName);
		if (variable != SpecifiedTab.noObj) {
			report_error("Greska na " + LastVarDeclIsArray.getLine() + ": " + variableName + " vec deklarisano", null);
			errorDetected = true;
		} else {
			int structKind = currentType.getKind();
			Struct arrayType = getArrayStruct(structKind);
			if (arrayType == null) {
				arrayType = createStructOfArrayType(structKind);
			}
			variable = SpecifiedTab.insert(Obj.Var, variableName, arrayType);
			if (variable != SpecifiedTab.noObj) {
				report_info("Deklarisana promenljiva " + variableName, LastVarDeclIsArray);
			}
		}
		currentType = null;
	}

	// ****************************** Constants *********************************

	@Override
	public void visit(ConstDeclType ConstDeclType) {
		if (currentType == null) {
			report_error("Invalidno mesto za definisanje tipa!", ConstDeclType);
			errorDetected = true;
			return;
		}
		currentType = ConstDeclType.getType().struct;
	}

	@Override
	public void visit(BeforeLastConstBool BeforeLastConstBool) {
		String constName = BeforeLastConstBool.getConstName();
		Obj constant = SpecifiedTab.find(constName);
		if (constant != SpecifiedTab.noObj) {
			report_error("Greska na " + BeforeLastConstBool.getLine() + ": " + constName + " vec deklarisano", null);
			errorDetected = true;
		} else if (currentType != SpecifiedTab.boolType) {
			report_error("Greska na " + BeforeLastConstBool.getLine()
					+ ": Ne moze se dodeliti vrednost tipa bool konstanti tipa " + kindToString(currentType.getKind()),
					null);
			errorDetected = true;
		} else {
			constant = SpecifiedTab.insert(Obj.Con, constName, currentType);
			if (constant != SpecifiedTab.noObj) {
				constant.setAdr((BeforeLastConstBool.getValue()) ? 1 : 0);
				report_info("Deklarisana konstanta " + constName, BeforeLastConstBool);
			}
		}
	}

	@Override
	public void visit(LastConstBool LastConstBool) {
		String constName = LastConstBool.getConstName();
		Obj constant = SpecifiedTab.find(constName);
		if (constant != SpecifiedTab.noObj) {
			report_error("Greska na " + LastConstBool.getLine() + ": " + constName + " vec deklarisano", null);
			errorDetected = true;
		} else if (currentType != SpecifiedTab.boolType) {
			report_error("Greska na " + LastConstBool.getLine()
					+ ": Ne moze se dodeliti vrednost tipa bool konstanti tipa " + kindToString(currentType.getKind()),
					null);
			errorDetected = true;
		} else {
			constant = SpecifiedTab.insert(Obj.Con, constName, currentType);
			if (constant != SpecifiedTab.noObj) {
				constant.setAdr((LastConstBool.getValue()) ? 1 : 0);
				report_info("Deklarisana konstanta " + constName, LastConstBool);
			}
		}
	}

	public String kindToString(int kind) {
		switch (kind) {
		case 1:
			return "int";
		case 2:
			return "char";
		case 5:
			return "bool";
		default:
			return null;
		}
	}

	@Override
	public void visit(BeforeLastConstChar BeforeLastConstChar) {
		String constName = BeforeLastConstChar.getConstName();
		Obj constant = SpecifiedTab.find(constName);
		if (constant != SpecifiedTab.noObj) {
			report_error("Greska na " + BeforeLastConstChar.getLine() + ": " + constName + " vec deklarisano", null);
			errorDetected = true;
		} else if (currentType != SpecifiedTab.charType) {
			report_error("Greska na " + BeforeLastConstChar.getLine()
					+ ": Ne moze se dodeliti vrednost tipa char konstanti tipa " + kindToString(currentType.getKind()),
					null);
			errorDetected = true;
		} else {
			constant = SpecifiedTab.insert(Obj.Con, constName, currentType);
			if (constant != SpecifiedTab.noObj) {
				constant.setAdr(BeforeLastConstChar.getValue());
				report_info("Deklarisana konstanta " + constName, BeforeLastConstChar);
			}
		}
	}

	@Override
	public void visit(LastConstChar LastConstChar) {
		String constName = LastConstChar.getConstName();
		Obj constant = SpecifiedTab.find(constName);
		if (constant != SpecifiedTab.noObj) {
			report_error("Greska na " + LastConstChar.getLine() + ": " + constName + " vec deklarisano", null);
			errorDetected = true;
		} else if (currentType != SpecifiedTab.charType) {
			report_error("Greska na " + LastConstChar.getLine()
					+ ": Ne moze se dodeliti vrednost tipa char konstanti tipa " + kindToString(currentType.getKind()),
					null);
			errorDetected = true;
		} else {
			constant = SpecifiedTab.insert(Obj.Con, constName, currentType);
			if (constant != SpecifiedTab.noObj) {
				constant.setAdr(LastConstChar.getValue());
				report_info("Deklarisana konstanta " + constName, LastConstChar);
			}
		}
	}

	@Override
	public void visit(BeforeLastConstNumber BeforeLastConstNumber) {
		String constName = BeforeLastConstNumber.getConstName();
		Obj constant = SpecifiedTab.find(constName);
		if (constant != SpecifiedTab.noObj) {
			report_error("Greska na " + BeforeLastConstNumber.getLine() + ": " + constName + " vec deklarisano", null);
			errorDetected = true;
		} else if (currentType != SpecifiedTab.intType) {
			report_error("Greska na " + BeforeLastConstNumber.getLine()
					+ ": Ne moze se dodeliti vrednost tipa int konstanti tipa " + kindToString(currentType.getKind()),
					null);
			errorDetected = true;
		} else {
			constant = SpecifiedTab.insert(Obj.Con, constName, currentType);
			if (constant != SpecifiedTab.noObj) {
				constant.setAdr(BeforeLastConstNumber.getValue());
				report_info("Deklarisana konstanta " + constName, BeforeLastConstNumber);
			}
		}
	}

	@Override
	public void visit(LastConstNumber LastConstNumber) {
		String constName = LastConstNumber.getConstName();
		Obj constant = SpecifiedTab.find(constName);
		if (constant != SpecifiedTab.noObj) {
			report_error("Greska na " + LastConstNumber.getLine() + ": " + constName + " vec deklarisano", null);
			errorDetected = true;
		} else if (currentType != SpecifiedTab.intType) {
			report_error("Greska na " + LastConstNumber.getLine()
					+ ": Ne moze se dodeliti vrednost tipa int konstanti tipa " + kindToString(currentType.getKind()),
					null);
			errorDetected = true;
		} else {
			constant = SpecifiedTab.insert(Obj.Con, constName, currentType);
			if (constant != SpecifiedTab.noObj) {
				constant.setAdr(LastConstNumber.getValue());
				report_info("Deklarisana konstanta " + constName, LastConstNumber);
			}
		}
	}

	// ****************************** Methods *********************************

	@Override
	public void visit(MethodDecl MethodDecl) {
		SpecifiedTab.chainLocalSymbols(currentMethod);
		currentMethod.setLevel(SpecifiedTab.currentScope.getnVars());
		SpecifiedTab.closeScope();
		currentMethod = null;
	}

	@Override
	public void visit(HasType HasType) {
		report_error("Greska na " + HasType.getLine() + ": podrzana je samo metoda void main()", null);
		errorDetected = true;
	}

	@Override
	public void visit(MethodName MethodName) {
		if (!(MethodName.getMethodName().equals("main"))) {
			report_error("Greska na " + MethodName.getLine() + ": podrzana je samo metoda void main()", null);
			errorDetected = true;
		} else {
			Obj main = SpecifiedTab.find(MethodName.getMethodName());
			if (main != SpecifiedTab.noObj) {
				report_error("Greska na " + MethodName.getLine() + ": main metoda vec deklarisana", null);
				errorDetected = true;
			} else {
				main = SpecifiedTab.insert(Obj.Meth, "main", SpecifiedTab.noType);
				if (main != SpecifiedTab.noObj) {
					report_info("Deklarisana metoda main", MethodName);
					currentMethod = main;
					MethodName.obj = currentMethod;
					SpecifiedTab.openScope();
				}
			}
		}
	}

	// ************************** Methods Local Variables **************************

	@Override
	public void visit(LocalVarDeclType LocalVarDeclType) {
		if (currentType == null) {
			report_error("Invalidno mesto za definisanje tipa!", LocalVarDeclType);
			errorDetected = true;
			return;
		}
		currentType = LocalVarDeclType.getType().struct;
	}

	@Override
	public void visit(BeforeLastLocalVarDeclNotArray BeforeLastLocalVarDeclNotArray) {
		String variableName = BeforeLastLocalVarDeclNotArray.getVarName();
		Obj variable = SpecifiedTab.currentScope.findSymbol(variableName);
		if (variable != null) {
			report_error(
					"Greska na " + BeforeLastLocalVarDeclNotArray.getLine() + ": " + variableName + " vec deklarisano",
					null);
			errorDetected = true;
		} else {
			variable = SpecifiedTab.insert(Obj.Var, variableName, currentType);
			if (variable != SpecifiedTab.noObj) {
				report_info("Deklarisana promenljiva " + variableName, BeforeLastLocalVarDeclNotArray);
			}
		}
	}

	@Override
	public void visit(BeforeLastLocalVarDeclIsArray BeforeLastLocalVarDeclIsArray) {
		String variableName = BeforeLastLocalVarDeclIsArray.getVarName();
		Obj variable = SpecifiedTab.currentScope.findSymbol(variableName);
		if (variable != null) {
			report_error(
					"Greska na " + BeforeLastLocalVarDeclIsArray.getLine() + ": " + variableName + " vec deklarisano",
					null);
			errorDetected = true;
		} else {
			int structKind = currentType.getKind();
			Struct arrayType = getArrayStruct(structKind);
			if (arrayType == null) {
				arrayType = createStructOfArrayType(structKind);
			}
			variable = SpecifiedTab.insert(Obj.Var, variableName, arrayType);
			if (variable != SpecifiedTab.noObj) {
				report_info("Deklarisana promenljiva " + variableName, BeforeLastLocalVarDeclIsArray);
			}
		}
	}

	@Override
	public void visit(LastLocalVarDeclNotArray LastLocalVarDeclNotArray) {
		String variableName = LastLocalVarDeclNotArray.getVarName();
		Obj variable = SpecifiedTab.currentScope.findSymbol(variableName);
		if (variable != null) {
			report_error("Greska na " + LastLocalVarDeclNotArray.getLine() + ": " + variableName + " vec deklarisano",
					null);
			errorDetected = true;
		} else {
			variable = SpecifiedTab.insert(Obj.Var, variableName, currentType);
			if (variable != SpecifiedTab.noObj) {
				report_info("Deklarisana promenljiva " + variableName, LastLocalVarDeclNotArray);
			}
		}
		currentType = null;
	}

	@Override
	public void visit(LastLocalVarDeclIsArray LastLocalVarDeclIsArray) {
		String variableName = LastLocalVarDeclIsArray.getVarName();
		Obj variable = SpecifiedTab.currentScope.findSymbol(variableName);
		if (variable != null) {
			report_error("Greska na " + LastLocalVarDeclIsArray.getLine() + ": " + variableName + " vec deklarisano",
					null);
			errorDetected = true;
		} else {
			int structKind = currentType.getKind();
			Struct arrayType = getArrayStruct(structKind);
			if (arrayType == null) {
				arrayType = createStructOfArrayType(structKind);
			}
			variable = SpecifiedTab.insert(Obj.Var, variableName, arrayType);
			if (variable != SpecifiedTab.noObj) {
				report_info("Deklarisana promenljiva " + variableName, LastLocalVarDeclIsArray);
			}
		}
		currentType = null;
	}

	// ************************** Designator Semantics **************************

	@Override
	public void visit(ArrayElementDesignator ArrayElementDesignator) {
		Obj designatorObj = ArrayElementDesignator.getIdentForArrayWhenUsingElement().obj;
		if (designatorObj != null) {
			Expr expr = ArrayElementDesignator.getExpr();
			if (expr.struct != SpecifiedTab.intType) {
				report_error("Greska na " + ArrayElementDesignator.getLine() + "(" + designatorObj.getName()
						+ ") nije moguce indeksirati niz sa tipom koji nije int", null);
				errorDetected = true;
			}
		}
		
		ArrayElementDesignator.obj = designatorObj;
	}

	@Override
	public void visit(IdentForArrayWhenUsingElement IdentForArrayWhenUsingElement) {
		String designatorIdent = IdentForArrayWhenUsingElement.getIdent();
		Obj designatorObj = SpecifiedTab.find(designatorIdent);
		if (designatorObj == SpecifiedTab.noObj) {
			report_error(
					"Greska na " + IdentForArrayWhenUsingElement.getLine() + "(" + designatorIdent + ") nije nadjeno",
					null);
			errorDetected = true;
		} else if (designatorObj.getType().getKind() != Struct.Array) {
			report_error("Greska na " + IdentForArrayWhenUsingElement.getLine() + "(" + designatorIdent
					+ ") nije moguce pristupiti ne nizovskom tipu sa []", null);
			errorDetected = true;
		} else {
			stv = new SpecifiedDumpSymbolTableVisitor();
			stv.visitObjNode(designatorObj);
			report_info("Pretraga na " + IdentForArrayWhenUsingElement.getLine() + "(" + designatorIdent + "), nadjeno "
					+ stv.getOutput(), null);

			IdentForArrayWhenUsingElement.obj = designatorObj;
		}
	}

	@Override
	public void visit(NotArrayElementDesignator NotArrayElementDesignator) {
		String designatorIdent = NotArrayElementDesignator.getIdent();
		Obj designatorObj = SpecifiedTab.find(designatorIdent);
		if (designatorObj == SpecifiedTab.noObj) {
			report_error("Greska na " + NotArrayElementDesignator.getLine() + "(" + designatorIdent + ") nije nadjeno",
					null);
			errorDetected = true;
		} else {
			stv = new SpecifiedDumpSymbolTableVisitor();
			stv.visitObjNode(designatorObj);
			report_info("Pretraga na " + NotArrayElementDesignator.getLine() + "(" + designatorIdent + "), nadjeno "
					+ stv.getOutput(), null);
			NotArrayElementDesignator.obj = designatorObj;
		}
	}

	// ********************* Designator Statements Semantics *********************

	@Override
	public void visit(DesignatorStatementIncrement DesignatorStatementIncrement) {
		Obj designatorObj = DesignatorStatementIncrement.getDesignator().obj;
		if (designatorObj != null && designatorObj != SpecifiedTab.noObj) {
			if (DesignatorStatementIncrement.getDesignator() instanceof NotArrayElementDesignator
					&& designatorObj.getType().getKind() != Struct.Int) {
				report_error("Nemoguce je inkrementirati promenljivu koja nije tipa int", DesignatorStatementIncrement);
				errorDetected = true;
			} else if (designatorObj.getType().getKind() != Struct.Int
					&& !(designatorObj.getType().getKind() == Struct.Array
							&& designatorObj.getType().getElemType().getKind() == Struct.Int)) {
				report_error("Nemoguce je inkrementirati promenljivu koja nije tipa int", DesignatorStatementIncrement);
				errorDetected = true;
			}
		}
	}

	@Override
	public void visit(DesignatorStatementDecrement DesignatorStatementDecrement) {
		Obj designatorObj = DesignatorStatementDecrement.getDesignator().obj;
		if (designatorObj != null && designatorObj != SpecifiedTab.noObj) {
			if (DesignatorStatementDecrement.getDesignator() instanceof NotArrayElementDesignator
					&& designatorObj.getType().getKind() != Struct.Int) {
				report_error("Nemoguce je inkrementirati promenljivu koja nije tipa int", DesignatorStatementDecrement);
				errorDetected = true;
			} else if (designatorObj.getType().getKind() != Struct.Int
					&& !(designatorObj.getType().getKind() == Struct.Array
							&& designatorObj.getType().getElemType().getKind() == Struct.Int)) {
				report_error("Nemoguce je inkrementirati promenljivu koja nije tipa int", DesignatorStatementDecrement);
				errorDetected = true;
			}
		}
	}

	@Override
	public void visit(DesignatorAssign DesignatorAssign) {
		Designator designator = DesignatorAssign.getDesignator();
		Expr expr = DesignatorAssign.getExpr();

		if (designator.obj != null && expr.struct != null) {
			if (designator instanceof ArrayElementDesignator) {
				if (designator.obj.getType().getElemType() != expr.struct) {
					report_error("Greska: dodela vrednosti nije moguca jer tipovi nisu kompatabilni ", DesignatorAssign);
					errorDetected = true;
				}
			} else if (designator.obj.getType().getKind() == Struct.Array) {
				if (!(expr.struct.getKind() == Struct.Array
						&& designator.obj.getType().getElemType() == expr.struct.getElemType())) {
					report_error("Greska: dodela vrednosti nije moguca jer tipovi nisu kompatabilni", DesignatorAssign);
					errorDetected = true;
				}
			} else if (designator.obj.getType() != expr.struct) {
				report_error("Greska: dodela vrednosti nije moguca jer tipovi nisu kompatabilni", DesignatorAssign);
				errorDetected = true;
			}
		}
	}

	// **************************** Statements Semantics
	// ****************************

	@Override
	public void visit(StatementRead StatementRead) {
		Struct type = null;
		Designator designator = StatementRead.getDesignator();
		if (designator instanceof ArrayElementDesignator) {
			if (designator.obj != null) {
				type = designator.obj.getType().getElemType();
			}
		} else {
			type = designator.obj.getType();
		}
		if (!(type == SpecifiedTab.boolType || type == SpecifiedTab.charType || type == SpecifiedTab.intType)) {
			report_error("Greska: argument read funkcije mora da bude osnovnog tipa", StatementRead);
			errorDetected = true;
		}
	}

	@Override
	public void visit(StatementPrint StatementPrint) {
		Expr expr = StatementPrint.getExpr();
		if (expr.struct != null) {
			if (!(expr.struct == SpecifiedTab.boolType || expr.struct == SpecifiedTab.charType
					|| expr.struct == SpecifiedTab.intType)) {
				report_error("Greska: argument print funkcije mora da bude osnovnog tipa", StatementPrint);
				errorDetected = true;
			}
		}
	}

	@Override
	public void visit(StatementFindAny StatementFindAny) {
		Designator leftSideDesignator = StatementFindAny.getDesignator();
		Designator rightSideDesignator = StatementFindAny.getDesignator1();
		Expr expr = StatementFindAny.getExpr();

		if (leftSideDesignator.obj != null && rightSideDesignator.obj != null && expr.struct != null) {
			if (leftSideDesignator instanceof ArrayElementDesignator) {
				if (leftSideDesignator.obj.getType().getElemType() != SpecifiedTab.boolType) {
					report_error("Greska: Vrednost sa leve strane jednakosti mora da bude bool tipa", StatementFindAny);
					errorDetected = true;
				}
			} else if (leftSideDesignator.obj.getType() != SpecifiedTab.boolType) {
				report_error("Greska: Vrednost sa leve strane jednakosti mora da bude bool tipa", StatementFindAny);
				errorDetected = true;
			}

			if (rightSideDesignator instanceof ArrayElementDesignator) {
				report_error("Greska: promenljiva za koju se poziva findAny funkcija mora da bude niz",
						StatementFindAny);
				errorDetected = true;
			} else if (rightSideDesignator.obj.getType().getKind() != Struct.Array) {
				report_error("Greska: promenljiva za koju se poziva findAny funkcija mora da bude niz",
						StatementFindAny);
				errorDetected = true;
			} else {
				if (rightSideDesignator.obj.getType().getElemType() != expr.struct) {
					report_error("Greska: argument findAny funkcije mora da bude isti kao tip elemenata niza",
							StatementFindAny);
					errorDetected = true;
				}
			}

		}
	}

	// ****************************** Factor Semantics ****************************

	@Override
	public void visit(FactorBool FactorBool) {
		FactorBool.struct = SpecifiedTab.boolType;
	}

	@Override
	public void visit(FactorChar FactorChar) {
		FactorChar.struct = SpecifiedTab.charType;
	}

	@Override
	public void visit(FactorNumber FactorNumber) {
		FactorNumber.struct = SpecifiedTab.intType;
	}

	@Override
	public void visit(FactorDesignator FactorDesignator) {
		if (FactorDesignator.getDesignator().obj != null) {
			if (FactorDesignator.getDesignator() instanceof ArrayElementDesignator) {
				FactorDesignator.struct = FactorDesignator.getDesignator().obj.getType().getElemType();
			} else {
				FactorDesignator.struct = FactorDesignator.getDesignator().obj.getType();
			}
		}
	}

	@Override
	public void visit(FactorInParanthesis FactorInParanthesis) {
		FactorInParanthesis.struct = FactorInParanthesis.getExpr().struct;
	}

	@Override
	public void visit(FactorNewConstructor FactorNewConstructor) {
		if (FactorNewConstructor.getExpr().struct == null) {
			report_error("Greska: broj elemenata niza mora biti celobrojna vrednost ", FactorNewConstructor);
			errorDetected = true;
		} else if (FactorNewConstructor.getExpr().struct.getKind() != Struct.Int) { // ovde ce da mi puca ako bude null
			report_error("Greska: broj elemenata niza mora biti celobrojna vrednost ", FactorNewConstructor);
			errorDetected = true;
		}

		if (FactorNewConstructor.getType().getTypeName().equals("int"))
			FactorNewConstructor.struct = arrayTypeInt;
		if (FactorNewConstructor.getType().getTypeName().equals("char"))
			FactorNewConstructor.struct = arrayTypeChar;
		if (FactorNewConstructor.getType().getTypeName().equals("bool"))
			FactorNewConstructor.struct = arrayTypeBool;
	}

	// ****************************** Term Semantics ****************************

	@Override
	public void visit(SingleFactor SingleFactor) {
		SingleFactor.struct = SingleFactor.getFactor().struct;
	}

	@Override
	public void visit(MultipleFactors MultipleFactors) {
		Factor factor = MultipleFactors.getFactor();
		Term term = MultipleFactors.getTerm();

		if (factor.struct != null && term.struct != null) {
			if (factor.struct.getKind() == Struct.Array || term.struct.getKind() == Struct.Array) {
				report_error("Greska: nije moguce primeniti operaciju mnozenja na promenljivu tipa niz",
						MultipleFactors);
				errorDetected = true;
			} else {
				if (factor.struct.getKind() != Struct.Int || term.struct.getKind() != Struct.Int) {
					report_error("Greska: nije moguce primeniti operaciju mnozenja na promenljivu ciji tip nije int",
							MultipleFactors);
					errorDetected = true;
				}
			}
		}

		MultipleFactors.struct = SpecifiedTab.intType;
	}

	// ************************** Expr Semantics *************************

	@Override
	public void visit(SingleNegativeTerm SingleNegativeTerm) {
		if (SingleNegativeTerm.getTerm().struct != null) {
			if (SingleNegativeTerm.getTerm().struct.getKind() != Struct.Int) {
				report_error("Greska: moguce je minus znak koristiti samo ispred int tipa", SingleNegativeTerm);
				errorDetected = true;
			}
		}

		SingleNegativeTerm.struct = SpecifiedTab.intType;
	}

	@Override
	public void visit(SinglePositiveTerm SinglePositiveTerm) {
		SinglePositiveTerm.struct = SinglePositiveTerm.getTerm().struct;
	}

	@Override
	public void visit(MultipleTerms MultipleTerms) {
		Term term = MultipleTerms.getTerm();
		Expr expr = MultipleTerms.getExpr();

		if (expr.struct != null && term.struct != null) {
			if (expr.struct.getKind() == Struct.Array || term.struct.getKind() == Struct.Array) {
				report_error(
						"Greska: nije moguce primeniti operaciju sabiranja ili oduzimanja na promenljivu ciji tip nije int",
						MultipleTerms);
				errorDetected = true;
			} else {
				if (expr.struct.getKind() != Struct.Int || term.struct.getKind() != Struct.Int) {
					report_error(
							"Greska: nije moguce primeniti operaciju sabiranja ili oduzimanja na promenljivu ciji tip nije int",
							MultipleTerms);
					errorDetected = true;
				}
			}
		}

		MultipleTerms.struct = SpecifiedTab.intType;
	}

}
