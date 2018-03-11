package myPuzzle;
import java.util.PriorityQueue;
//@Author Nate Wolfrath

public class Frontier{
    private PriorityQueue<Node> frontier;

    Frontier(){
        frontier = new PriorityQueue<Node>(10000, new Node.NodeCompare()); //arbitrary size of 10k
    }

    public Boolean add(Node n){
        return frontier.add(n);
    }

    public Boolean inFrontier(Node n){
        for (Node f : frontier){
            //won't traverse in order but we don't care here
            if (f.isEqual(n)) {
                return true;
            }
        }
        return false;
    }
}
