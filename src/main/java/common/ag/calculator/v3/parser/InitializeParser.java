package common.ag.calculator.v3.parser;

import static common.ag.calculator.v3.parser.BaseStringType.ADD_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.BITWISE_AND_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.BITWISE_LEFT_SHIFT_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.BITWISE_NOT_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.BITWISE_OR_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.BITWISE_RIGHT_SHIFT_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.BITWISE_XOR_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.BOOLEAN_NUMBER;
import static common.ag.calculator.v3.parser.BaseStringType.CONDITIONAL_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.DIVIDE_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.EQUAL_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.FLOOR_DIVIDE_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.GREATER_EQUAL_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.GREATER_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.LEFT_PARENTHESIS;
import static common.ag.calculator.v3.parser.BaseStringType.LITTLE_EQUAL_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.LITTLE_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.LOGARITHM_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.LOGICAL_AND_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.LOGICAL_NOT_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.LOGICAL_OR_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.MODULO_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.MULTIPLY_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.NEGATIVE_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.NOT_EQUAL_SIGN_1;
import static common.ag.calculator.v3.parser.BaseStringType.NOT_EQUAL_SIGN_2;
import static common.ag.calculator.v3.parser.BaseStringType.NUMBER;
import static common.ag.calculator.v3.parser.BaseStringType.POSITIVE_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.POWER_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.RIGHT_PARENTHESIS;
import static common.ag.calculator.v3.parser.BaseStringType.SUBTRACT_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.TO_BOOLEAN_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.TO_DOUBLE_SIGN;
import static common.ag.calculator.v3.parser.BaseStringType.TO_INT_SIGN;
import static common.ag.calculator.v3.parser.operand.BooleanNumberParser.BOOLEAN_NUMBER_PARSER;
import static common.ag.calculator.v3.parser.operand.NumberParser.NUMBER_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.ADD_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.BITWISE_AND_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.BITWISE_LEFT_SHIFT_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.BITWISE_OR_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.BITWISE_RIGHT_SHIFT_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.BITWISE_XOR_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.DIVIDE_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.EQUAL_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.FLOOR_DIVIDE_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.GREATER_EQUAL_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.GREATER_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.LITTLE_EQUAL_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.LITTLE_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.LOGARITHM_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.LOGICAL_AND_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.LOGICAL_OR_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.MODULO_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.MULTIPLY_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.NOT_EQUAL_SIGN_PARSER_1;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.POWER_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BinaryOperationalSymbolParser.SUBTRACT_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.BindingSymbolParser.LEFT_PARENTHESIS_PARSER;
import static common.ag.calculator.v3.parser.sign.BindingSymbolParser.RIGHT_PARENTHESIS_PARSER;
import static common.ag.calculator.v3.parser.sign.TernaryOperationalSymbolParser.CONDITIONAL_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.UnaryOperationalSymbolParser.BITWISE_NOT_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.UnaryOperationalSymbolParser.LOGICAL_NOT_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.UnaryOperationalSymbolParser.NEGATIVE_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.UnaryOperationalSymbolParser.POSITIVE_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.UnaryOperationalSymbolParser.TO_BOOLEAN_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.UnaryOperationalSymbolParser.TO_DOUBLE_SIGN_PARSER;
import static common.ag.calculator.v3.parser.sign.UnaryOperationalSymbolParser.TO_INT_SIGN_PARSER;

import common.ag.calculator.v3.setting.Setting;

public final class InitializeParser {
	
	/**
	 * 本实现中总是将长符号的解析器放在前面，因为你可能能够解析出一个<小于号；但实际上是一个左移符号<<
	 * 
	 * 那么接下来解析就会发送错误，但是顺序反过来就不会出现这个问题，因此总是先解析长的，就不会有任何问题
	 * 
	 * 解析短的，可能会把长的解析了一部分，但是是错误的
	 */
	public static void initializeParser() {
		
		//长度大于3
		
		Setting.parserMaping.put(NUMBER, NUMBER_PARSER);//max
		
		Setting.parserMaping.put(TO_BOOLEAN_SIGN, TO_BOOLEAN_SIGN_PARSER);//9
		
		Setting.parserMaping.put(TO_DOUBLE_SIGN, TO_DOUBLE_SIGN_PARSER);//8
		
		Setting.parserMaping.put(BOOLEAN_NUMBER, BOOLEAN_NUMBER_PARSER);//5
		
		Setting.parserMaping.put(TO_INT_SIGN, TO_INT_SIGN_PARSER);//5
		
		Setting.parserMaping.put(LOGARITHM_SIGN, LOGARITHM_SIGN_PARSER);//3
		
		//长度等于2
		
		Setting.parserMaping.put(GREATER_EQUAL_SIGN, GREATER_EQUAL_SIGN_PARSER);//2
		
		Setting.parserMaping.put(LITTLE_EQUAL_SIGN, LITTLE_EQUAL_SIGN_PARSER);//2
		
		Setting.parserMaping.put(EQUAL_SIGN, EQUAL_SIGN_PARSER);//2
		
		Setting.parserMaping.put(NOT_EQUAL_SIGN_1, NOT_EQUAL_SIGN_PARSER_1);//2
		
		Setting.parserMaping.put(NOT_EQUAL_SIGN_2, NOT_EQUAL_SIGN_PARSER_1);//2
		
		Setting.parserMaping.put(BITWISE_LEFT_SHIFT_SIGN, BITWISE_LEFT_SHIFT_SIGN_PARSER);//2
		
		Setting.parserMaping.put(BITWISE_RIGHT_SHIFT_SIGN, BITWISE_RIGHT_SHIFT_SIGN_PARSER);//2
		
		Setting.parserMaping.put(LOGICAL_OR_SIGN, LOGICAL_OR_SIGN_PARSER);//2
		
		Setting.parserMaping.put(LOGICAL_AND_SIGN, LOGICAL_AND_SIGN_PARSER);//2
		
		Setting.parserMaping.put(FLOOR_DIVIDE_SIGN, FLOOR_DIVIDE_SIGN_PARSER);//2
		
		Setting.parserMaping.put(POWER_SIGN, POWER_SIGN_PARSER);//2
		
		//长度等于1
		
		Setting.parserMaping.put(NEGATIVE_SIGN, NEGATIVE_SIGN_PARSER);//1
		
		Setting.parserMaping.put(POSITIVE_SIGN, POSITIVE_SIGN_PARSER);//1
		
		Setting.parserMaping.put(BITWISE_NOT_SIGN, BITWISE_NOT_SIGN_PARSER);//1
		
		Setting.parserMaping.put(LOGICAL_NOT_SIGN, LOGICAL_NOT_SIGN_PARSER);//1
		
		Setting.parserMaping.put(GREATER_SIGN, GREATER_SIGN_PARSER);//1
		
		Setting.parserMaping.put(LITTLE_SIGN, LITTLE_SIGN_PARSER);//1
		
		Setting.parserMaping.put(BITWISE_OR_SIGN, BITWISE_OR_SIGN_PARSER);//1
		
		Setting.parserMaping.put(BITWISE_AND_SIGN, BITWISE_AND_SIGN_PARSER);//1
		
		Setting.parserMaping.put(BITWISE_XOR_SIGN, BITWISE_XOR_SIGN_PARSER);//1
		
		Setting.parserMaping.put(ADD_SIGN, ADD_SIGN_PARSER);//1
		
		Setting.parserMaping.put(SUBTRACT_SIGN, SUBTRACT_SIGN_PARSER);//1
		
		Setting.parserMaping.put(MULTIPLY_SIGN, MULTIPLY_SIGN_PARSER);//1
		
		Setting.parserMaping.put(DIVIDE_SIGN, DIVIDE_SIGN_PARSER);//1
		
		Setting.parserMaping.put(MODULO_SIGN, MODULO_SIGN_PARSER);//1
		
		Setting.parserMaping.put(CONDITIONAL_SIGN, CONDITIONAL_SIGN_PARSER);//1
		
		Setting.parserMaping.put(LEFT_PARENTHESIS, LEFT_PARENTHESIS_PARSER);//1
		
		Setting.parserMaping.put(RIGHT_PARENTHESIS, RIGHT_PARENTHESIS_PARSER);//1
	}
	
}
