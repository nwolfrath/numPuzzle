package myPuzzle;
//@Author Nate Wolfrath

import java.util.ArrayList;

public class ExploredSet {
    private ArrayList<Node> exSet;

    ExploredSet() {
        exSet = new ArrayList<Node>();
    }

    public Boolean add(Node n){
        return exSet.add(n);
    }


    public Boolean inExSet(Node n){
        for (Node e : exSet){
            //won't traverse in order but we don't care here
            if (e.isEqual(n)) {
                return true;
            }
        }
        return false;
    }

}
