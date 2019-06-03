package common.ag.calculator.v2;

import java.util.ArrayList;
import java.util.List;

/**
 * 三目运算符
 * 
 * @author 87663
 */
public abstract class TernaryOperationalSymbol extends OperationalSymbol {
	
	public final static TernaryOperationalSymbol CONDITIONAL_SIGN = new TernaryOperationalSymbol() {
		
		public final static int OPERAND_NUMBER = 3;
		
		public final static int SYMBOL_LENGTH = 1;
		
		public final static int PARTIAL_SYMBOL_LENGTH = 1;
		
		@Override
		public int getOperandNumber() {
			return OPERAND_NUMBER;
		}
		
		@Override
		public int operationalSymbolLength() {
			return SYMBOL_LENGTH;
		}
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return OperationalSymbolPriority.TERNARY_OPERATION_PRIORITY;
		}
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 2 for conditional sign");
			return nums[0] | nums[1];
		}
		
		/**
		 * 本三目运算符的检查，前面必须是数字或者右括号
		 * 
		 * 对于?和:都是这样
		 */
		@Override
		protected boolean check(ParserShared shared) {
			return shared.numberCheck[shared.now ^ 1]
					|| shared.rightParenthesisCheck[shared.now ^ 1];
		}
		
		/**
		 * 这个匹配略微麻烦，并且在匹配中还有一个问题
		 * 
		 * 那就是三目运算符不能被单独的右括号隔开
		 * 
		 * 形如(1+2+5?1)+1:0 这样的表达式是不合法的
		 * 
		 * 总结为在?和:中间任一时刻，允许出现的增长的右括号数量不应该比左括号多
		 * 
		 * 因为3目运算符如?:，在?和:中间可以夹一个表达式，这个表达式还必须加上括号
		 * 然后将三目运算符放在这个表达式后面，中间需要递归一次
		 */
		
		class ConditionalChecker {
			int numberOfLeftParenthesisBeforeQuestionSign;
			int numberOfRightParenthesisBeforeQuestionSign;
			int from;
			
			public ConditionalChecker(int numberOfLeftParenthesisBeforeQuestionSign,
					int numberOfRightParenthesisBeforeQuestionSign, int from) {
				super();
				this.numberOfLeftParenthesisBeforeQuestionSign = numberOfLeftParenthesisBeforeQuestionSign;
				this.numberOfRightParenthesisBeforeQuestionSign = numberOfRightParenthesisBeforeQuestionSign;
				this.from = from;
			}
			
			void check(ParserShared shared) {
				if (shared.numberOfRightParenthesis
						- numberOfRightParenthesisBeforeQuestionSign <= shared.numberOfLeftParenthesis
								- numberOfLeftParenthesisBeforeQuestionSign)
					throw new IllegalArgumentException(
							"Expression illegal :  illegal right parenthesis show in conditional sign :"
									+ shared.expression.substring(from));
			}
		}
		
		/**
		 * 检查栈，用于检查当前三元状态操作符的中间情况
		 * 并且表示当前嵌套三元状态操作符的数量
		 */
		List<ConditionalChecker> checkers = new ArrayList<>();
		
		@Override
		protected boolean symbol(ParserShared shared) {
			
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c == '?') {
				
				shared.from[shared.now] = shared.from[shared.now ^ 1] + PARTIAL_SYMBOL_LENGTH;
				
				//在每个三元状态操作符中间的表达式加上括号，表示一个整体先算
				shared.elements.add(BindingSymbol.LEFT_PARENTHESIS);
				
				checkers.add(new ConditionalChecker(shared.numberOfLeftParenthesis,
						shared.numberOfRightParenthesis, shared.from[shared.now ^ 1]));
				
				return true;
			} else if (c == ':') {
				if (checkers.size() == 0)
					return false;
				
				shared.from[shared.now] = shared.from[shared.now ^ 1] + PARTIAL_SYMBOL_LENGTH;
				
				//在每个三元状态操作符中间的表达式加上括号，表示一个整体先算
				shared.elements.add(BindingSymbol.RIGHT_PARENTHESIS);
				shared.elements.add(this);
				
				//弹出栈顶的检查器
				checkers.remove(checkers.size() - 1);
				
				return true;
			}
			
			return false;
		}
		
		@Override
		public void insideCkeck(ParserShared shared) {
			for (ConditionalChecker checker : checkers)
				checker.check(shared);
		}
		
		@Override
		public void finalCheck(ParserShared shared) {
			if (checkers.size() != 0)
				throw new IllegalArgumentException(
						"Expression illegal :  incomplete conditional sign:"
								+ shared.expression.substring(checkers.get(0).from));
		}
		
	};
	
}
