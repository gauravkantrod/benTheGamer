import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BenMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter values -->  ");
		int nLevels = sc.nextInt();
		int mWeapons = sc.nextInt();
		if (mWeapons < 1 || mWeapons > 20) {
			System.out.println(0);
			return;
		}
		if (nLevels < 1 || nLevels > 20) {
			System.out.println(0);
			return;
		}

		Set<String> weaponType = new HashSet<String>();
		for (int i = 0; i < nLevels; i++) {
			System.out.println("Enter weapon " + i + "-->  ");
			String s = sc.next();
			Boolean match = s.matches("[01]*");
			if (match == true && s.length() == mWeapons && weaponType.add(s)) {
				weaponType.add(s);
			} else {
				System.out.println(0);
				return;
			}
		}
		System.out
				.println("Ans --> " + minCoins(nLevels, mWeapons, weaponType));
	}

	public static int minCoins(int nLevels, int mWeapons, Set<String> weaponType) {
		int coins = 0;
		if (nLevels >= 1 && nLevels <= 20 && mWeapons >= 1 && mWeapons <= 20) {
			Set<Integer> result = new HashSet<Integer>();
			List<List<Integer>> set = new ArrayList<List<Integer>>();

			for (String s : weaponType) {
				List<Integer> wepSet = new ArrayList<Integer>();
				char[] c = s.toCharArray();
				for (int i = 0; i < c.length; i++) {
					if (c[i] == '1') {
						wepSet.add(i);
					}
				}
				set.add(wepSet);
			}

			List<List<Integer>> ltr = new ArrayList<List<Integer>>();
			for (List<Integer> s : set) {
				ltr.add(s);
			}
			Collections.sort(ltr, new SizeComparator());
			System.out.println("list--->" + ltr);

			for (List<Integer> s : ltr) {
				coins += symmetricDifference(s, result);
			}
		}
		return coins;
	}

	private static int symmetricDifference(List<Integer> b, Set<Integer> result) {
		int prevResultSize = result.size();
		for (Integer element : b) {
			result.add(element);
		}
		int nextResultSize = result.size();
		int diff = nextResultSize - prevResultSize;
		int square = diff * diff;

		return square;
	}

	public static class SizeComparator implements Comparator<List<?>> {
		@Override
		public int compare(List<?> o1, List<?> o2) {
			return Integer.valueOf(o1.size()).compareTo(o2.size());
		}
	}

}