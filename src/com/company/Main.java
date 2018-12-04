package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static int amountOfElements1; // Количество элементов первой доли графа
    public static int amountOfElements2; // Количество элементов второй доли графа
    public static int vertex;
    public static ArrayList all = new ArrayList(); // Список смежности
    public static ArrayList all2= new ArrayList(); // Список смежности
    public static int[][] matrix;

    public static void main(String[] args) {
	 Reader(); // Считываем из in.txt
     ProcessIn();
	 MakeMartix(); // По all создаём матрицу смежности
     System.out.println("_____________");
	 FindMatching.addStartEnd(); // Добавляем вершину начала и конца
	 FindMatching.modifyMatrix(); // Модифицируем матрицу, делаем наш двудольный граф ориентированным
	 TestMatrix(); // Вывод матрицы (Для внутренних тестов)
     FordFalkerson.Run();
        try {
            WriteResultModify();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void TestMatrix(){
        for (int i = 0; i < matrix.length; i ++){
            for (int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void TestFlow(){
        for (int i = 0; i < FordFalkerson.flow.length; i ++){
            for (int j = 0; j < FordFalkerson.flow[i].length; j++){
                System.out.print(FordFalkerson.flow[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void ProcessIn(){
        for (int i = 0; i < all.size(); i++){
            ArrayList n = (ArrayList) all.get(i);
            ArrayList res = new ArrayList();
            for (int j = 0; j < n.size() - 1; j++){
                res.add((int) n.get(j)+amountOfElements1);
            }
            all2.add(res);
        }
        all = all2;
    }

    public static void Reader(){
        try{
            FileInputStream fstream = new FileInputStream("in.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String firstLine = br.readLine();
            int indexSpace = firstLine.indexOf(" ");
            amountOfElements1 = Integer.parseInt(firstLine.substring(0,indexSpace));
            amountOfElements2 = Integer.parseInt(firstLine.substring(indexSpace+1));
            vertex = amountOfElements1 + amountOfElements2;
            String strLine =  br.readLine();
            while (strLine != null){ // Идём по строкам
                Parser(strLine);
                strLine = br.readLine();
            }
        }catch (IOException e){
            System.out.println("Ошибка");
        }
    }

    public static void Parser (String str) {
        ArrayList linesArray = new ArrayList();
        while (str != "") {
            int num = str.indexOf(" "); // Ищем пробелы
            if (num == -1){
                break;
            }
            String times = str.substring(0, num); // Берём нужную часть
            if (Integer.parseInt(times) == 0) {
                break;
            }
            str = str.substring(num+1); // Избавляемся от точо что уже записали
            linesArray.add(Integer.parseInt(times)); // Добавляем в массив
        }
        linesArray.add(str);
        all.add(linesArray);

    }

    public static void MakeMartix(){
        matrix = new int [vertex + 2][vertex + 2];
        for (int i = 0; i < all.size(); i++){
            ArrayList arrayVertex = (ArrayList) all.get(i);
            for (int j = 0; j < arrayVertex.size(); j++){
                int x = Integer.parseInt(String.valueOf(arrayVertex.get(j)));
                matrix[i + 1][x] = 1;
            }

        }
    }

    public static void WriteResult() throws IOException {
        File file = new File("out.txt");
        // Создание файла
        file.createNewFile();
        // Создание объекта FileWriter
        FileWriter writer = new FileWriter(file);
        for (int i = 1; i < FordFalkerson.flow.length - 1; i++){
            for (int j = 1; j < FordFalkerson.flow[i].length - 1; j++){
                if (FordFalkerson.flow[i][j] == 1){
                    writer.write("{"+i+","+j+"}" + "\n");
                }
            }
        }
        writer.flush();
        writer.close();
    }
    public static void WriteResultModify() throws IOException {
        File file = new File("out.txt");
        // Создание файла
        file.createNewFile();
        // Создание объекта FileWriter
        FileWriter writer = new FileWriter(file);
        String result = "";
        for (int i = 1; i < amountOfElements1 + 1; i++){
            boolean writeVertex = true;
            for (int j = 1; j < FordFalkerson.flow[i].length - 1; j++){
                if (FordFalkerson.flow[i][j] == 1){
                    int x = j - amountOfElements1;
                    result = result + x + " ";
                    writeVertex = false;
                }
            }
            if (writeVertex) {
                result = result + 0 + " ";
            }
        }
        result = result.substring(0,result.length() - 1);
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
