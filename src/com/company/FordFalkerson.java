package com.company;

import java.util.ArrayList;

import static com.company.Main.*;

public class FordFalkerson {

    public static int[][] flow;

    public static void Run (){
        flow = new int[vertex + 2][vertex + 2];
        boolean stoper = true;
        while (stoper){
            ArrayList used = new ArrayList();
            ArrayList badUsed = new ArrayList();
            int presentVertex = 0;
            while (presentVertex != (vertex + 1)){
                boolean checIn = true;
                for (int i = 0; i < matrix[presentVertex].length; i++){
                    if (VertexCheck(i,presentVertex,used,badUsed))
                    {
                        used.add(presentVertex);
                        presentVertex = i;
                        checIn = false;
                        break;
                    }
                }
                if (checIn) {
                    if (used.isEmpty()){
                        stoper = false;
                        break;
                    }
                    else {
                        if (!badUsed.contains(presentVertex)) {
                            System.out.println(presentVertex+"<=");
                            badUsed.add(presentVertex);
                        }
                        presentVertex = (int) used.get(used.size() - 1);
                        used.remove(used.size() - 1);

                    }
                }
            }
            used.add(presentVertex);
            for (int i = 0; i < used.size(); i++){
               System.out.println(used.get(i));
            }
            ProcessMatrix(used);
            Main.TestFlow();
        }
    }

    private static boolean VertexCheck(int i, int presentVertex, ArrayList used, ArrayList badUsed) {
        System.out.println("________");
        System.out.println(presentVertex + " " + i);
        System.out.println(matrix[presentVertex][i] +" "+!used.contains(presentVertex) +" "+flow[presentVertex][i] + "_"+ flow[i][presentVertex] + " " + !badUsed.contains(presentVertex));
        if ( ((matrix[presentVertex][i] == 1)
                && (!used.contains(i))
                && (matrix[presentVertex][i] > flow[presentVertex][i])
                && (!badUsed.contains(i)))
                || ( (matrix[presentVertex][i] == -1)
                && (!used.contains(i))
                && (flow[i][presentVertex] > 0)
                && (!badUsed.contains(i))) ){
            System.out.println("True");
            System.out.println("________");
            return true;
        }
        else {
            System.out.println("False");
            System.out.println("________");
            return false;
        }
    }

    public static void ProcessMatrix(ArrayList used){
        for (int i = 0; i < used.size() - 1; i++){
            int from = (int) used.get(i);
            int in = (int) used.get(i+1);
            if (matrix[from][in] == 1){
                flow[from][in] = 1;
            }
            if (matrix[from][in] == -1){
                Main.TestFlow();
                flow[in][from] = 0;
                Main.TestFlow();
            }
        }
    }
}
