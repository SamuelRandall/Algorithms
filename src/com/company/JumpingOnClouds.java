package com.company;


import java.io.*;
import java.util.Scanner;

public class JumpingOnClouds {

    // Complete the jumpingOnClouds function below.

    // you should be able to use math on each group of 0s to determine how many jumps are to be taken for them
    // calculate how many jumps are needed by using a modulo operator and division on each set of 0s

    static int jumpingOnClouds(int[] c) {
        int jumpCount = 0;
        int zeroCount = 0;
        for(int i = 0; i < c.length; i++){
            if(c[i] == 0){
                zeroCount++;
            } else {
                jumpCount += zeroJumps(zeroCount);
                zeroCount = 0;
                jumpCount++;
            }
            if(i == c.length -1){
                jumpCount += zeroJumps(zeroCount);
            }
        }
        return jumpCount;
    }

    static int zeroJumps(int n){
        if(n % 2 == 0){
            return (n/2);
        } else {
            return ((n-1)/2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./OUTPUT_JUMP.txt"));
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("./INPUT_JUMP.txt")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
