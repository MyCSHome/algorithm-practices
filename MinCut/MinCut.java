import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

class Node {
	int id;
	LinkedList<Integer> edges = new LinkedList<>();	
	
	public Node(int nodeID) {
		id = nodeID;
	}
	public void add(int n) {
		edges.add(n);
	}
	public int size() {
		return edges.size();
	}
	public int get(int index) {
		return edges.get(index);
	}
	
}

class Graph {
	LinkedList<Node> nodes = new LinkedList<>();
	
	public Graph() {
		
	}
	public void add(Node node){
		nodes.add(node);
	}
	public int size() {
		return nodes.size();
	}
	public Node get(int index) {
		return nodes.get(index);
	}
}

public class MinCut {
	private static Node[] nodes = new Node[200];
	private static Random random;
	public static Graph readGraph(String filename) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(filename));
		String s;
		Scanner scanner = null;
		int nodeID;
		Graph graph = new Graph();
		for (int i = 0; i < 200; i++) {
			nodes[i] = new Node(i+1);
		}
		while ((s = bf.readLine())!=null) {
			scanner = new Scanner(s);
			nodeID = scanner.nextInt();
			while(scanner.hasNext()) {
				nodes[nodeID-1].add(scanner.nextInt());
			}
			graph.add(nodes[nodeID-1]);
		}
		scanner.close();
		bf.close();
		return graph;
	}
	
	public static int randomContraction(Graph graph) {
		int minCut = 0;
		random = new Random();
		int no1, no2;
		Node node1, node2;
		while(graph.size() > 2) {
			//say size = 5, return a number ~[0, 4]
			no1 = random.nextInt(graph.size());
			//System.out.print(" "+  no1);
			node1 = graph.get(no1);
			no2 = random.nextInt(node1.size());
			node2 = nodes[node1.get(no2)-1];
			//move node2's edges to node1
			for (int node: node2.edges) {
					node1.add(node);
			}
			//remove node2 from other nodes' edge, replace with node1
			for (Node node: graph.nodes) {
				while(node.edges.contains((Integer)node2.id)) {
					node.edges.remove((Integer)node2.id);
					node.edges.add((Integer)node1.id);				
				}
			}
			//remove node2 from graph
			graph.nodes.remove(node2);
		    //remove self-loops
			while(node1.edges.contains((Integer)node1.id))
				node1.edges.remove((Integer)node1.id);
		}
		minCut = graph.nodes.get(0).size();
		return minCut;
	}
	
	public static void main(String[] args) throws IOException {

		int min = 1000;
		int result;
		for (int i = 0;i < 100; i++) {
			Graph graph = readGraph(args[0]);
			if(min > (result = randomContraction(graph)))
				min = result; 
		}
		System.out.println(min);		
	}

}
