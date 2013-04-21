import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;

class Node {
	int id;
	int sp = 1000000;
	
	public Node(int no){
		id = no;
	}
	
}

class Edge {
    Node head;
	Node tail;
	int weight;
	
	public Edge(Node h, Node t, int w){
		head = h;
		tail = t;
		weight = w;
	}
}

public class Dijkstra {
	private static boolean[] checked;
	private static Node[] nodes;
	private static ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public static void readGraph(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		Scanner scanner = null;
		Edge edge;
		int head, tail, weight;
		while ((line = br.readLine())!= null){
			scanner = new Scanner(line);
			scanner.useDelimiter(",|\t");
			head = scanner.nextInt();
			while(scanner.hasNext()){
				tail = scanner.nextInt();
				weight = scanner.nextInt();
				edge = new Edge(nodes[head-1], nodes[tail-1], weight);
				edges.add(edge);
			}	
		}
		scanner.close();
		br.close();		
	}
	
	public static void dijkstraLoop() {
		nodes[0].sp = 0;
		checked[0] = true;
		Hashtable<Integer, Edge> bridges = new Hashtable<Integer, Edge>();
		ArrayList<Integer> bridgeLength = new ArrayList<Integer> ();
		int length;
		for (int i=1; i<200; i++){
			for (Edge e : edges) {
				if (checked[e.head.id -1] && !checked[e.tail.id-1]) {
					length = e.head.sp + e.weight;
					bridges.put(length, e);
					bridgeLength.add(length);
				}
			}
			int min = Collections.min(bridgeLength);
			Edge ee = bridges.get(min);
			bridges.clear();
			bridgeLength.clear();
			checked[ee.tail.id-1] = true;
			ee.tail.sp = min;
		}
	}
	
	public static void main(String[] args) throws IOException{
		String filename = args[0];
		int size = Integer.parseInt(args[1]);
		checked = new boolean[size];
		nodes = new Node[size];
		for (int i = 0; i<size; i++){
			Node node = new Node(i+1);
			nodes[i] = node;
		}
		readGraph(filename);
		dijkstraLoop();
		System.out.println(nodes[6].sp + " " + nodes[36].sp + " " + nodes[58].sp + " " + nodes[81].sp+ " " + nodes[98].sp + " " + nodes[114].sp + " " + nodes[132].sp + " " + nodes[164].sp+ " " + nodes[187].sp + " " + nodes[196].sp + " done");
	}
	

}
