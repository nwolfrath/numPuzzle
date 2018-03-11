package myPuzzle;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class Node {
    private ArrayList<Integer> myState; //State stored as a row major list of integers
    private Node parent;
    private Integer size;
    private Integer manhattan;


    Node(ArrayList<Integer> state, Node p, Integer nSize){
        this.myState = state;
        this.parent = p;
        this.size = nSize;
        this.manhattan = makeManhattan();
    }
    public boolean isGoal(){
        for (int i = 0; i < size; i++){
            if(myState.get(i) != i){
                return false;
            }
        }
        return true;
    }

    private Integer makeManhattan(){
        Integer ret = 0;
        Integer rows = (int) Math.sqrt(size);
        for (int i = 0; i < size; i++){
            Integer rCorrect = i % rows;
            Integer cCorrect = i / rows; //leverage integer division
            Integer rCurrent = myState.get(i) % rows;
            Integer cCurrent = myState.get(i) / rows; //again, integer division
            ret += (Math.abs(rCorrect-rCurrent) + Math.abs(cCorrect - cCurrent));
        }

        return ret;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getManhattan() {
        return manhattan;
    }

    public void print(){
        Integer add = 0;
        Integer rows = (int) Math.sqrt(size);
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < rows; j++){
                System.out.print(myState.get(j + add));
            }
            System.out.print("\n");
            add += 3;
        }

    }
}


