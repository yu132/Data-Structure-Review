package ds.linearList;

import org.junit.Assert;
import org.junit.Test;

import ds.linearList.IntLinearList;
import ds.linearList.UnchekedIntArrayList;


public class UcIIntArrayList {

	@Test
	public void test(){
		IntLinearList uclist=new UnchekedIntArrayList();
		
		uclist.insert(1, uclist.size());
		
		uclist.insert(3, uclist.size());
		
		uclist.insert(-1, uclist.size());
		
		Assert.assertEquals(uclist.get(1),3);
		
		uclist.delete(-1);
		
		Assert.assertEquals(uclist.size(),2);
		
		for(int i=0;i<10000;i++)
			uclist.insert(i, uclist.size()-1);
		
		Assert.assertEquals(uclist.size(),10002);
		
		uclist.deleteIndex(8888);
		
		for(int i=0;i<uclist.size();i++)
			System.out.println(uclist.get(i));
		
		Assert.assertEquals(uclist.indexOf(777),778);
	}
	
}
