package ds.hash;

import org.junit.Assert;
import org.junit.Test;

import ds.dict.hashMap.IntArrayHashMap;
import ds.dict.hashMap.IntHashMap;
import ds.dict.hashMap.IntValueNotExistException;

public class IntHashMapTest {

	public static void one(IntHashMap map) {
		try {
			map.put(100, 50);
		} catch (IntValueNotExistException e) {}
		
		try {
			map.put(101, 51);
		} catch (IntValueNotExistException e) {}
		
		try {
			map.put(102, 52);
		} catch (IntValueNotExistException e) {}
		
		try {
			map.put(103, 53);
		} catch (IntValueNotExistException e) {}
		
		
		Assert.assertEquals(4, map.size());
		
		try {
			Assert.assertEquals(50, map.get(100));
		} catch (IntValueNotExistException e1) {}
		
		
		try {
			map.put(100, 60);
		} catch (IntValueNotExistException e) {}
		
		try {
			Assert.assertEquals(60, map.get(100));
		} catch (IntValueNotExistException e1) {}
		
		map.clear();
		
		Assert.assertEquals(0, map.size());
		
		Assert.assertTrue(map.isEmpty());
	}
	
	@Test
	public void test1(){
		one(new IntArrayHashMap());
	}
	
}
