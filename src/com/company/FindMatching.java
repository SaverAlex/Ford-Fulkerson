package com.company;


import static com.company.Main.*;

public class FindMatching {

    public static void addStartEnd(){
        for (int i = 1; i <= amountOfElements1; i++){
            matrix[0][i] = 1;
            matrix[i][0] = 1;
        }
        for (int i = amountOfElements1 + 1; i <= vertex; i++){
            matrix[vertex + 1][i] = 1;
            matrix[i][vertex + 1] = 1;
        }
    }

    public static void modifyMatrix (){
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0 + i; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    matrix[j][i] = -1;
                }
            }
        }
    }
}
