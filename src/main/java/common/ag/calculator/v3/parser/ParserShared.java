package common.ag.calculator.v3.parser;

import java.util.ArrayList;
import java.util.List;

import common.ag.calculator.v3.element.Element;
import common.ag.calculator.v3.parser.sign.BindingSymbolParser.ExperssionInteriorParenthsisCheck;

public class ParserShared {
	
	public String expression;
	
	public List<Element> elements = new ArrayList<>();
	
	public int now = 1;
	
	public int numberOfLeftParenthesis = 0;
	public int numberOfRightParenthesis = 0;
	
	public boolean[] numberCheck = new boolean[2];
	public boolean[] leftParenthesisCheck = new boolean[2];
	public boolean[] rightParenthesisCheck = new boolean[2];
	public boolean[] operationalSymbolCheck = new boolean[2];
	
	public int[] from = new int[2];
	
	public int condidtionalSignCount = 0;
	
	public List<ExperssionInteriorParenthsisCheck> parenthsisCheckers = new ArrayList<>();
	
	public ParserShared(String expression) {
		super();
		this.expression = expression;
	}
}
