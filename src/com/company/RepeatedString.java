package com.company;

import java.io.*;
import java.util.Scanner;

public class RepeatedString {

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        long num_a = 0;
        if(n >= s.length()){
            long repeats = n / s.length() + 1;
            long extra_chars = (repeats * (s.length()) - n);
            num_a = extra_count_a(s, extra_chars) + ((initial_count_a(s) * (repeats-1)));
            return num_a;
        }
        for(int i = 0; i < n; i++){
            if (s.charAt(i) == 'a'){
                num_a++;
            }
        }
        return num_a;
    }

    static long initial_count_a(String s){
        long count = 0;
        for(int i = 0; i < s.length(); i++){
            if (s.charAt(i) == 'a'){
                count++;
            }
        }
        return count;
    }

    static long extra_count_a(String s, long e){
        long count = 0;
        for(int i = 0; i < s.length() - e; i++){
            if (s.charAt(i) == 'a') {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./OUTPUT_REPEATEDSTRING.txt"));
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("./INPUT_REPEATEDSTRING.txt")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
