package ag.dm;

import static ag.dm.BUC.Tag.of;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ag.dm.BUC.Group;

public class BUCTest {

	@Test
	public void test() {
		List<Group> list = Arrays.asList(
				Group.of(of("a1"), of("b1"), of("c1"), of("d1")),
				Group.of(of("a1"), of("b1"), of("c1"), of("d3")),
				Group.of(of("a1"), of("b1"), of("c1"), of("d3")),
				Group.of(of("a1"), of("b1"), of("c1"), of("d3")),
				Group.of(of("a4"), of("b4"), of("c1"), of("d3")),
				Group.of(of("a4"), of("b4"), of("c1"), of("d3")),
				Group.of(of("a4"), of("b2"), of("c2"), of("d3")),
				Group.of(of("a4"), of("b2"), of("c2"), of("d3")),
				Group.of(of("a4"), of("b2"), of("c2"), of("d3")),
				Group.of(of("a4"), of("b4"), of("c1"), of("d1")),
				Group.of(of("a4"), of("b4"), of("c1"), of("d2")),
				Group.of(of("a4"), of("b4"), of("c1"), of("d2")),
				Group.of(of("a1"), of("b4"), of("c1"), of("d2")),
				Group.of(of("a1"), of("b1"), of("c1"), of("d2")),
				Group.of(of("a1"), of("b2"), of("c1"), of("d2")),
				Group.of(of("a1"), of("b2"), of("c1"), of("d2")),
				Group.of(of("a2"), of("b2"), of("c1"), of("d1")),
				Group.of(of("a2"), of("b2"), of("c1"), of("d1")),
				Group.of(of("a2"), of("b2"), of("c1"), of("d1")),
				Group.of(of("a2"), of("b1"), of("c1"), of("d2")),
				Group.of(of("a2"), of("b1"), of("c1"), of("d2")),
				Group.of(of("a1"), of("b1"), of("c1"), of("d2")),
				Group.of(of("a3"), of("b3"), of("c2"), of("d2")),
				Group.of(of("a3"), of("b3"), of("c2"), of("d4")),
				Group.of(of("a3"), of("b3"), of("c2"), of("d4")),
				Group.of(of("a3"), of("b3"), of("c2"), of("d4")),
				Group.of(of("a3"), of("b3"), of("c3"), of("d4")),
				Group.of(of("a3"), of("b3"), of("c3"), of("d4")),
				Group.of(of("a1"), of("b1"), of("c3"), of("d4")),
				Group.of(of("a1"), of("b1"), of("c1"), of("d1")),
				Group.of(of("a1"), of("b1"), of("c1"), of("d1")),
				Group.of(of("a1"), of("b1"), of("c1"), of("d1")));

		System.out.println(BUC.bucMinCountStar(list, 4, 5));

	}

}
