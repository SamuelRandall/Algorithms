package com.company;

import java.io.*;
import java.util.*;

public class RoadsAndLibraries {

    public static <K, V> int getKeysByValue(Map<Integer,ArrayList<Integer>> map, int value) {
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(value))
                .map(Map.Entry::getKey)
                .findFirst().get();
    }

    static long solution(int n, int c_lib, int c_road, int[][] cities) {

        int[] all_cities = new int[n+1];
        int num_roads = 0;
        int num_libs = n;
        // Start with edge case of lib in every city
        int min_cost = (num_libs * c_lib);

        Map<Integer, ArrayList<Integer>> Libraries = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            Libraries.put(i, new ArrayList<Integer>());
            Libraries.get(i).add(i);
            all_cities[i] = i;
        }

        for(int i = 0; i < cities.length; i++){
            // if city1 and 2 are in different libraries
            // Move cities (and all connected neighbors) from their respective libraries into one
            int lib_for_city1 = getKeysByValue(Libraries, cities[i][0]);
            int lib_for_city2 = getKeysByValue(Libraries, cities[i][1]);

//             compare the root nodes of each city, if they differ then you want to set the parent of one root node as
//             the other root node.
//            if(root_parent_city1 != root_parent_city2){
//
//            }

            if(lib_for_city1 != lib_for_city2){
                Libraries.get(lib_for_city1).addAll(Libraries.get(lib_for_city2));
                Libraries.remove(lib_for_city2);
            }
        }
        // num_libs equals the amount of keys left in hashmap, num_roads equals the length of
        // each key's list - num_libs
        num_libs = Libraries.size();
        for(int key : Libraries.keySet()){
            for(int i = 0; i < Libraries.get(key).size(); i++){
                num_roads++;
            }
        }
        num_roads = num_roads - num_libs;

        int candidate_cost = (num_libs * c_lib) + (num_roads * c_road);
        if (min_cost > candidate_cost){
            min_cost = candidate_cost;
        }
        return min_cost;
    }
    // Start of final solution

    public static int getParent(final int[] cities, int city){

        while(cities[city] != city){
            city = cities[city];
        }
        return city;
    }

    // Alternative solution using a tree instead of hashmap
    static long solution2(int n, int c_lib, int c_road, int[][] cities) {

        long c_lib_long = c_lib;
        long c_road_long = c_road;

        if(c_lib <= c_road){
            return (n * c_lib_long);
        }

        int[] all_cities = new int[n];
        for (int i = 0; i < all_cities.length; i++) {
            all_cities[i] = i;
        }

        for(int i = 0; i < cities.length; i++){
            int city1 = cities[i][0]-1;
            int city2 = cities[i][1]-1;
            int parent1 = getParent(all_cities, city1);
            int parent2 = getParent(all_cities, city2);

            if(parent1 != parent2){
                all_cities[city1] = parent2;
                all_cities[parent1] = parent2;
            }
        }

        Map<Integer, Long> Libraries = new HashMap<>();
        long num_roads = 0;
        for(int i = 0; i < all_cities.length; i++){
            all_cities[i] = getParent(all_cities, i);
            long old_count = Libraries.getOrDefault(all_cities[i], 0L);
            long count = Libraries.getOrDefault(all_cities[i], 0L) + 1;
            num_roads += count - old_count;
            Libraries.put(all_cities[i], count);
        }

        num_roads = num_roads - (Libraries.size());
        long candidate_cost = (Libraries.size() * c_lib_long) + (num_roads * c_road_long);

        return candidate_cost;
    }

//    private static final Scanner scanner = new Scanner(new BufferedReader(new FileReader("./INPUT.txt")));

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./OUTPUT.txt"));
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("./INPUT.txt")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = solution2(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
