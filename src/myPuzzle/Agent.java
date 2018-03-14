package myPuzzle;
//@Author Nate Wolfrath

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import static java.lang.System.exit;

public class Agent {
    private Frontier f;
    private ExploredSet exSet;

    Agent(){
        f = new Frontier();
        exSet = new ExploredSet();
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
            ArrayList<Integer> next = new ArrayList<>();
            n.copyState(next);
            Collections.swap(next, loc, loc+1);
            ret.add(new Node(next, n, n.getSize()));
        }
        //if black is a 'middle', you can move left or right
        if((cLoc != 0) && (cLoc != (rows-1))){
            ArrayList<Integer> lNext = new ArrayList<>();
            ArrayList<Integer> rNext = new ArrayList<>();
            n.copyState(lNext);
            n.copyState(rNext);
            Collections.swap(lNext, loc, loc-1);
            Collections.swap(rNext, loc, loc+1);
            ret.add(new Node(lNext, n, n.getSize()));
            ret.add(new Node(rNext, n, n.getSize()));
        }
        //if blank is in the last column, you can go left
        if(cLoc == (rows -1)){
            ArrayList<Integer> next = new ArrayList<>();
            n.copyState(next);
            Collections.swap(next, loc, loc-1);
            ret.add(new Node(next, n, n.getSize()));
        }

        //Now for rows: first row, you can only go down
        if(rLoc == 0){
            ArrayList<Integer> next = new ArrayList<>();
            n.copyState(next);
            Collections.swap(next, loc, loc+rows);
            ret.add(new Node(next, n, n.getSize()));
        }

        //middle row(s) can go up or down
        if((rLoc != 0) && (rLoc != (rows-1))){
            ArrayList<Integer> uNext = new ArrayList<>();
            ArrayList<Integer> dNext = new ArrayList<>();
            n.copyState(uNext);
            n.copyState(dNext);
            Collections.swap(uNext, loc, loc-rows);
            Collections.swap(dNext, loc, loc+rows);
            ret.add(new Node(uNext, n, n.getSize()));
            ret.add(new Node(dNext, n, n.getSize()));
        }

        //final row can only go up
        if(rLoc == (rows-1)){
            ArrayList<Integer> next = new ArrayList<>();
            n.copyState(next);
            Collections.swap(next, loc, loc-rows);
            ret.add(new Node(next, n, n.getSize()));
        }
        return ret;
    }

    public void greedySearch(Node start){

        f.add(start);

        while(true){


            if(f.fEmpty()){
                System.out.print("No Solution :(");
                exit(7);
            }

            Node nextState = f.pop();


            if(nextState.isGoal()){
                nextState.rollback();
                exit(0);
            }

            ArrayList <Node> neighbors = getNewStates(nextState);

            for (Node neighbor : neighbors){



                if (!exSet.inExSet(neighbor) && !f.inFrontier(neighbor)){
                    f.add(neighbor);
                }
            } exSet.add(nextState);

        }
    }
}
