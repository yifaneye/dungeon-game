package unsw.dungeon.test;

import unsw.dungeon.*;

public class CompositeGoalTest {

	public static void main(String[] args) {
		Leaf exit = new Leaf("exit");
		Leaf enemies = new Leaf("enemies");
		Leaf boulders = new Leaf("boulders");
		Leaf treasure = new Leaf("treasure");

		Composite goal = new Composite("AND");

		Composite subgoal1 = new Composite("OR");
		subgoal1.add(exit);
		subgoal1.add(boulders);
		goal.add(subgoal1);
		
		Composite subgoal2 = new Composite("AND");
		subgoal2.add(enemies);
		subgoal2.add(treasure);
		goal.add(subgoal2);
		
		System.out.println(goal.nameString());
	}

}
