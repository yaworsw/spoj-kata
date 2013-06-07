import java.util.List;
import java.util.Scanner;
import java.util.Vector;

class Miserman {

  private static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int sx = scan.nextInt();
    int sy = scan.nextInt();
    Node[][] graph = new Node[sx][sy];

    Node a = new Node(0);
    Node b = new Node(0);

    for (int y = 0; y < sy; y++) {
      for (int x = 0; x < sx; x++) {

        Node current = new Node(scan.nextInt());
        graph[x][y] = current;

        if (y == 0) {
          a.addVectorTo(current);
        } else {
          graph[x][y-1].addVectorTo(current);
          if (x > 0) {
            graph[x-1][y-1].addVectorTo(current);
          }
          if (x < sx - 1) {
            graph[x+1][y-1].addVectorTo(current);
          }
        }

      }
    }

    for (int i = 0; i < sx; i++) {
      graph[i][sy-1].addVectorTo(b);
    }

    System.out.println(b.costTo());

  }

}

class Node {

  private int cost;

  private List<Node> incoming = new Vector<Node>();
  private List<Node> outgoing = new Vector<Node>(2);

  private int costTo = -1;

  public Node(int cost) {
    this.cost = cost;
  }

  public int costTo() {
    if (costTo < 0) {
      int min = Integer.MAX_VALUE;
      for (Node n : incoming) {
        if (min > n.costTo()) {
          min = n.costTo();
        }
      }
      return costTo = cost() + (min == Integer.MAX_VALUE ? 0 : min);
    } else {
      return costTo;
    }
  }

  public int cost() {
    return cost;
  }

  public void addIncoming(Node n) {
    incoming.add(n);
  }

  public void addOutgoing(Node n) {
    outgoing.add(n);
  }

  public void addVectorTo(Node n) {
    addOutgoing(n);
    n.addIncoming(this);
  }

  public boolean isTerminal() {
    return outgoing.isEmpty();
  }

}