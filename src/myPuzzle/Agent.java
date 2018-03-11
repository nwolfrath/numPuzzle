package myPuzzle;
//@Author Nate Wolfrath
import java.util.ArrayList;
import java.util.Collections;

public class Agent {
    private Frontier f;
    private ExploredSet exSet;

    Agent(Node n){
        f.add(n);
    }

    private ArrayList<Node> getNewStates(Node n){
        Integer rows = (int)Math.sqrt(n.getSize());
        ArrayList<Node> ret = new ArrayList<Node>();
        ArrayList<Integer> state = n.getMyState();
        Integer loc = 0;
        //Need to find where the blank is
        for(int i = 0; i<n.getSize(); i++){
            if (state.get(i) == 0){
                loc = i;
                break;
            }
        }
        Integer cLoc = loc % rows;
        Integer rLoc = loc / rows;

        //Make new states

        //if blank is in the first column, you can move right
        if(cLoc == 0){
            ArrayList<Integer> next = state;
            Collections.swap(next, loc, loc+1);
            ret.add(new Node(next, n, n.getSize()));
        }
        //if black is a 'middle', you can move left or right
        if((cLoc != 0) && (cLoc != (rows-1))){
            ArrayList<Integer> lNext,rNext;
            lNext = rNext = state;
            Collections.swap(lNext, loc, loc-1);
            Collections.swap(rNext, loc, loc+1);
            ret.add(new Node(lNext, n, n.getSize()));
            ret.add(new Node(rNext, n, n.getSize()));
        }
        //if blank is in the last column, you can go left
        if(cLoc == (rows -1)){
            ArrayList<Integer> next = state;
            Collections.swap(next, loc, loc-1);
            ret.add(new Node(next, n, n.getSize()));
        }

        //Now for rows: first row, you can only go down
        if(rLoc == 0){
            ArrayList<Integer> next = state;
            Collections.swap(next, loc, loc+3);
            ret.add(new Node(next, n, n.getSize()));
        }

        //middle row(s) can go up or down
        if((rLoc != 0) && (rLoc != (rows-1))){
            ArrayList<Integer> uNext,dNext;
            uNext = dNext = state;
            Collections.swap(uNext, loc, loc-3);
            Collections.swap(dNext, loc, loc+3);
            ret.add(new Node(uNext, n, n.getSize()));
            ret.add(new Node(dNext, n, n.getSize()));
        }

        //final row can only go up
        if(rLoc == (rows-1)){
            ArrayList next = state;
            Collections.swap(next, loc, loc-3);
            ret.add(new Node(next, n, n.getSize()));
        }
        return ret;
    }
}
