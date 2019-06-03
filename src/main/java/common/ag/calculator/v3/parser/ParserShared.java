package common.ag.calculator.v3.parser;

import java.util.ArrayList;
import java.util.List;

import common.ag.calculator.v3.element.Element;

public class ParserShared {
	
	public String expression;
	
	public List<Element> elements;
	
	public int now;
	
	public int numberOfLeftParenthesis;
	public int numberOfRightParenthesis;
	
	public boolean[] numberCheck;
	public boolean[] leftParenthesisCheck;
	public boolean[] rightParenthesisCheck;
	public boolean[] operationalSymbolCheck;
	
	public int[] from;
	
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
