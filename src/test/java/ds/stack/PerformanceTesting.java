package ds.stack;

import taskLogging.ShowTimeOfExecution;

public class PerformanceTesting {
	
	public static void one(IntStack stack){
		for(int i=0;i<100;i++)
			stack.push(i);
		
		for(int i=0;i<100;i++){
			stack.top();
			stack.pop();
		}
		
		for(int i=0;i<10;i++)
			stack.push(i);
		
		stack.clear();
	}
	

	public static void main(String[] args) {
		{
			ShowTimeOfExecution s=new ShowTimeOfExecution("A");
	
			IntStack s1=new IntLinkedStack();
			
			for(int i=0;i<1;++i)
				for(int j=0;j<10000000;++j){
					one(s1);
				}
			
			s.showTimeInCmd();
		}
		
		{
			ShowTimeOfExecution s=new ShowTimeOfExecution("B");
	
			IntStack s1=new FastIntLinkedStack();
			
			for(int i=0;i<1;++i)
				for(int j=0;j<10000000;++j){
					one(s1);
				}
			
			s.showTimeInCmd();
		}
		
		{
			ShowTimeOfExecution s=new ShowTimeOfExecution("A");
	
			IntStack s1=new IntLinkedStack();
			
			for(int i=0;i<1;++i)
				for(int j=0;j<10000000;++j){
					one(s1);
				}
			
			s.showTimeInCmd();
		}
		
		{
			ShowTimeOfExecution s=new ShowTimeOfExecution("B");
	
			IntStack s1=new FastIntLinkedStack();
			
			for(int i=0;i<1;++i)
				for(int j=0;j<10000000;++j){
					one(s1);
				}
			
			s.showTimeInCmd();
		}
	}
	
	public static void main2(String[] args) {
		{
			ShowTimeOfExecution s=new ShowTimeOfExecution("A");
	
			IntStack s1=new IntArrayStack();
			
			for(int i=0;i<1;++i)
				for(int j=0;j<10000000;++j){
					one(s1);
				}
			
			s.showTimeInCmd();
		}
		
		{
			ShowTimeOfExecution s=new ShowTimeOfExecution("B");
	
			IntStack s1=new FastIntArrayStack();
			
			for(int i=0;i<1;++i)
				for(int j=0;j<10000000;++j){
					one(s1);
				}
			
			s.showTimeInCmd();
		}
		
		{
			ShowTimeOfExecution s=new ShowTimeOfExecution("A");
	
			IntStack s1=new IntArrayStack();
			
			for(int i=0;i<1;++i)
				for(int j=0;j<10000000;++j){
					one(s1);
				}
			
			s.showTimeInCmd();
		}
		
		{
			ShowTimeOfExecution s=new ShowTimeOfExecution("B");
	
			IntStack s1=new FastIntArrayStack();
			
			for(int i=0;i<1;++i)
				for(int j=0;j<10000000;++j){
					one(s1);
				}
			
			s.showTimeInCmd();
		}
	}
	
}
