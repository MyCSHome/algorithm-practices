import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;


public class HashTable2Sum {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("HashInt.txt");
		Scanner s = new Scanner(file);
		HashSet<Integer> hs = new HashSet<>();
		int element;
		while(s.hasNext()) {
			element = s.nextInt();
			hs.add(element);
		}
		int count =0;
		for (int t = 2500; t <= 4000; t++) {
			for(int i : hs) {
				if (hs.contains(t - i) && i != (t-i)) {
					count++;
					break;
				}
			}
		}
		s.close();
		System.out.println("count is " + count);
	}
}
