package myPuzzle;
//@Author Nate Wolfrath
import java.util.ArrayList;
import java.util.Collections;

public class puzzleSolver {

    public static void main(String[] args){
       /* if (args.length != 1){
            System.out.print("Invalid argument number. Please use [n] to solve an n x n puzzle");
        }
        Integer size = Math.pow((new Integer(args[0]),2);*/
        ArrayList<Integer> nate = makePuz(9);
        Node me = new Node(nate,null, 9);
        me.print();
    }




    private static boolean canSolve(ArrayList<Integer> puzzle) {
        Integer rows = (int) Math.sqrt(puzzle.size());
        Integer size = puzzle.size();
        Integer count = 0;
        Integer tmp;
        Integer thisCnt = 0;
        Integer blankFromBottom = 0;
        Integer i, j, currentValue;
        Boolean gridWidthOdd, inversionsEven, blankOddFromBottom;


        gridWidthOdd = (rows % 2 == 1);

        for (i = 0, j = 0; i < size - 1; i++) {

            currentValue = puzzle.get(j++);
            if (currentValue == 0) {
                blankFromBottom = ((rows - 1) - (i / rows));
                continue;
            }

            tmp = j;
            thisCnt = 0;

            while (tmp <= size - 1) {
                if ((puzzle.get(tmp) != 0) && (puzzle.get(tmp) < currentValue)) {
                    thisCnt++;
                }
                tmp++;
            }

            count += thisCnt;
        }

        inversionsEven = (count % 2 == 0);

        blankOddFromBottom = (blankFromBottom % 2 == 0);

        return (gridWidthOdd && inversionsEven) || (!gridWidthOdd && (blankOddFromBottom == inversionsEven));
    }


    private static ArrayList<Integer> makePuz(Integer size){
        ArrayList<Integer> candidate = new ArrayList<>();
        //populate, mix until solvable
        for (int i = 0; i < size; i++){
            candidate.add(i);
        }

      Collections.shuffle(candidate);

        while(!canSolve(candidate)) {
            Collections.shuffle(candidate);
        }

        return candidate;
    }
}
