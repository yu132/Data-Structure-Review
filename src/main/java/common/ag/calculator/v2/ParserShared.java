package common.ag.calculator.v2;

import java.util.ArrayList;
import java.util.List;

public class ParserShared {
	
	String expression;
	
	List<Element> elements;
	
	int now;
	
	int numberOfLeftParenthesis;
	int numberOfRightParenthesis;
	
	boolean[] numberCheck;
	boolean[] leftParenthesisCheck;
	boolean[] rightParenthesisCheck;
	boolean[] operationalSymbolCheck;
	
	int[] from;
	
	public ParserShared(String expression) {
		super();
		this.expression = expression;
		this.elements = new ArrayList<>();
		this.now = 1;
		this.numberOfLeftParenthesis = 0;
		this.numberOfRightParenthesis = 0;
		this.numberCheck = new boolean[2];
		this.leftParenthesisCheck = new boolean[2];
		this.rightParenthesisCheck = new boolean[2];
		this.operationalSymbolCheck = new boolean[2];
		this.from = new int[2];
	}
}
