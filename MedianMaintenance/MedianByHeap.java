import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;


public class MedianByHeap {
	
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("Median.txt");
		Scanner s = new Scanner(f);
		PriorityQueue<Integer> low = new PriorityQueue<>(11, Collections.reverseOrder());
		PriorityQueue<Integer> high = new PriorityQueue<>();
		int templ, temph, temp;
		ArrayList<Integer> medians = new ArrayList<>();
		low.offer(0);
		high.offer(10001);
		while(s.hasNext()) {
			templ = low.peek();
			temph = high.peek();
			temp = s.nextInt();
			if (temp <= templ) 
				low.offer(temp);
			else if (temp >= temph)
				high.offer(temp);
			else
				low.offer(temp);
			
			if(low.size() - high.size() >= 2) {
				high.offer(low.poll());
			}
			else if(high.size() - low.size() >= 2) {
				low.offer(high.poll());
			}
			
			
			if (high.size() > low.size() ) {
				medians.add(high.peek());
			}
			else if(low.size() > high.size()) {
				medians.add(low.peek());
			}
			else
				medians.add(low.peek());			
		}
		s.close();
		int sum = 0, modd;
		for (int i: medians) {
			sum = sum + i;
		}
		modd = sum % medians.size();
		System.out.println("mod is " + modd);
	}
}
