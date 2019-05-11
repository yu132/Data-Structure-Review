package ag.dm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BUC {

	public interface GroupChecker {
		boolean check(List<Group> list);
	}

	private static class Pair {
		Group	group;
		Tag		tag;

		public Pair(Group group, Tag tag) {
			super();
			this.group = group;
			this.tag = tag;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((group == null) ? 0 : group.hashCode());
			result = prime * result + ((tag == null) ? 0 : tag.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (group == null) {
				if (other.group != null)
					return false;
			} else if (!group.equals(other.group))
				return false;
			if (tag == null) {
				if (other.tag != null)
					return false;
			} else if (!tag.equals(other.tag))
				return false;
			return true;
		}
	}

	/**
	 * 元组，有一组标签，每个标签对应一维上的内容
	 * 
	 * @author 87663
	 */
	public static class Group {
		List<Tag>						list;

		private static Map<Pair, Group>	staticPools	= new HashMap<>();

		private Group(List<Tag> list) {
			super();
			this.list = list;
		}

		public static Group of(Tag... tags) {
			return new Group(new ArrayList<>(Arrays.asList(tags)));
		}

		public static Group of(Group group, Tag tag) {

			Pair pair = new Pair(group, tag);

			Group staticOne = staticPools.get(pair);

			if (staticOne != null)
				return staticOne;

			List<Tag> list = new ArrayList<>();

			list.addAll(group.list);
			list.add(tag);

			Group g = new Group(list);

			staticPools.put(pair, g);

			return g;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((list == null) ? 0 : list.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Group other = (Group) obj;
			if (list == null) {
				if (other.list != null)
					return false;
			} else if (!list.equals(other.list))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Group [list=" + list + "]";
		}

	}

	/**
	 * 标签，对应每一维上的内容
	 * 
	 * @author 87663
	 */
	public static class Tag {
		String name;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Tag other = (Tag) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return name;
		}

		public Tag(String name) {
			super();
			this.name = name;
		}

		public static Tag of(String tag) {
			return new Tag(tag);
		}
	}

	/**
	 * buc 条件是元组的最小支持度不小于某个值
	 * 
	 * @param groups
	 * @param totalDims
	 * @param minCountStar
	 * @return
	 */
	public static Map<Group, Integer> bucMinCountStar(List<Group> groups, int totalDims,
			int minCountStar) {
		Map<Group, List<Group>> ans = buc(groups, (list) -> list.size() >= minCountStar, totalDims);

		Map<Group, Integer> result = new HashMap<>();

		for (Entry<Group, List<Group>> entry : ans.entrySet()) {
			result.put(entry.getKey(), entry.getValue().size());
		}

		return result;
	}

	/**
	 * buc 初始化参数
	 * 
	 * @param groups
	 * @param checker
	 * @param totalDims
	 * @return
	 */
	public static Map<Group, List<Group>> buc(List<Group> groups, GroupChecker checker,
			int totalDims) {

		Map<Group, List<Group>> groupOfEachTypes = new HashMap<>();
		groupOfEachTypes.put(Group.of(), groups);

		Map<Group, List<Group>> ans = new HashMap<>();

		for (int i = 0; i < totalDims; ++i) {
			buc(ans, groupOfEachTypes, i, checker, totalDims, true);
		}

		return ans;
	}

	/**
	 * buc 实现
	 * 
	 * @param groupOfEachTypes 
	 * 
	 * 		上一层每个划分对应的元组的列表
	 * 		例子：{
	 * 				(a1,b2,c1)->{(a1,b2,c1,d1),(a1,b2,c1,d1),(a1,b2,c1,d2),(a1,b2,c1,d3)}
	 * 			  }
	 * 
	 * @param dims	本层的维数  例子：若上一次的某个划分为(a1,b2,c1) 则为3（从0开始）
	 * @param checker	检查器	用于检查元组是否满足条件，不满足的会被剪枝
	 * @param totalDims	总共的维数
	 * 
	 * @param ans 收集每层划分
	 * 		本层每个划分对应元组的列表
	 * 		例子：{
	 * 				(a1,b2,c1,d1)->{(a1,b2,c1,d1),(a1,b2,c1,d1)},
	 * 				(a1,b2,c1,d2)->{(a1,b2,c1,d2)},
	 * 				(a1,b2,c1,d3)->{(a1,b2,c1,d3)}
	 * 			  }
	 */
	private static void buc(Map<Group, List<Group>> ans,
			Map<Group, List<Group>> groupOfEachTypes, int dims,
			GroupChecker checker, int totalDims, boolean base) {

		if (!base)
			ans.putAll(groupOfEachTypes);//把满足条件的划分给收集起来

		if (dims == totalDims)//迭代完毕，返回
			return;

		Map<Group, List<Group>> groupOfEachTypesDeepLevel = new HashMap<>();//新一层的划分和每个划分的组之间的映射

		for (Entry<Group, List<Group>> entry : groupOfEachTypes.entrySet()) {//对于旧的每个划分的列表
			Group groupBefore = entry.getKey();//之前的划分

			for (Group group : entry.getValue()) {//对于每个划分内的每个元组

				Group groupNow = Group.of(groupBefore, group.list.get(dims));//接下来要被分到的划分

				List<Group> list = groupOfEachTypesDeepLevel//获取新的划分的数组
						.getOrDefault(groupNow, new ArrayList<>());

				list.add(group);//将这个元组放到这个元组对应划分的数组中

				groupOfEachTypesDeepLevel.put(groupNow, list);//将数组放回映射中
			}
		}

		for (Iterator<Entry<Group, List<Group>>> iterator = groupOfEachTypesDeepLevel.entrySet()
				.iterator(); iterator.hasNext();) {//对于新的每个划分

			Entry<Group, List<Group>> entry = iterator.next();

			if (!checker.check(entry.getValue())) {//检查是否符合要求
				iterator.remove();//不满足就删掉，即剪枝
			}
		}

		buc(ans, groupOfEachTypesDeepLevel, dims + 1, checker, totalDims, false);//迭代下一层
	}

}
