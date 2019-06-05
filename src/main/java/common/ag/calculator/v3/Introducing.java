package common.ag.calculator.v3;

public class Introducing {
	
	/**
	 * [运算器介绍]：
	 * 
	 * 这里是一个较为复杂的计算器，其实现的功能其实比较类似编程语言的常数运算
	 * 
	 * [符号介绍和扩展]：
	 * 
	 * 当然也向下兼容普通四则运算，用于常见符号的计算完全没有问题
	 * 
	 * 在四则运算的基础上扩展了乘方，求模，求对数，位运算，逻辑运算，大小比较这些运算符号
	 * 
	 * 当然还可以继续扩展，只要实现{@link common.ag.calculator.v3.parser.AbstractElementParser}这个抽象类
	 * 
	 * 完成一个符号解析器，和{@link common.ag.calculator.v3.element.sign.OperationalSymbol}这个抽象类
	 * 
	 * 完成一个运算符号，就可以实现一个新的运算，当然，不要和之前定义的符号有冲突就好
	 * 
	 * [操作数介绍和扩展]：
	 * 
	 * 当然也可以定义新的操作数，实现{@link common.ag.calculator.v3.element.operand.Operand}和对应的解析器即可
	 * 
	 * 当然定义了新的操作数，想要原有的符号支持是不可能的，所以全部都要重写，重新定义
	 * 
	 * 现有的操作数有浮点，整形和布尔类型三种，三者都可以互相转换，但是浮点到整形需要强制转换，不能自动转换
	 * 
	 * 类型转换详细请见@see {@link common.ag.calculator.v3.element.operand.util.GetValue}
	 * 
	 * 浮点和整形都是无长度限制的，所以可以用来完成高精度的计算，但是对于小数的乘方和对数运算，并不能保证精确
	 * 
	 * [设计缺陷]：
	 * 
	 * 配置Setting是全局的，所以不太能够支持多线程复用，只能支持单线程使用本程序
	 * 
	 * 当然要是多线程都使用本配置，那么是没有问题的
	 * 
	 * 当然修改这个问题其实非常简单，但是不想改了
	 */
	
}
