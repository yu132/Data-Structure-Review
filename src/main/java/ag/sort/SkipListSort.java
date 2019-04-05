package ag.sort;

import ds.IntIterator;
import ds.linearList.sortedList.IntRepeatableSkipList;

public class SkipListSort implements IntArraySort {

	public static void sortIntArray(int[] array) {
		IntRepeatableSkipList sl = new IntRepeatableSkipList(array.length);

		for (int i = 0; i < array.length; ++i)
			sl.add(array[i]);

		{
			IntIterator it = sl.iterator();
			int i = 0;
			for (; it.hasNext(); ++i)
				array[i] = it.next();
		}
	}

	@Override
	public void sort(int[] array) {
		sortIntArray(array);
	}

}
