import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {

        int num_roads = 0;
        int num_libs = n;
        // Start with lib in every city
        int min_cost = (num_libs * c_lib);

        HashMap<Integer, Boolean> hasEdge = new HashMap<>();
        for(int i = 1; i <= n; i++){
            hasEdge.put(i,false);
        }
        for(int i = 0; i < cities.length; i++){
            if(i == 0) {
                hasEdge.put(cities[i][0],true);
                hasEdge.put(cities[i][1],true);
                num_libs--;
                num_roads++;
                System.out.println("First item"+ " " + num_libs + " " + num_roads);
            } else if(!hasEdge.get(cities[i][0]) && !hasEdge.get(cities[i][1])){
                hasEdge.put(cities[i][0],true);
                hasEdge.put(cities[i][1],true);
                num_libs--;
                num_roads++;
                System.out.println("Have not seen either city"+ " " + num_libs + " " + num_roads);
            } else if(!hasEdge.get(cities[i][0])) {
                hasEdge.put(cities[i][0],true);
                num_libs--;
                num_roads++;
                System.out.println("Have not seen the first city"+ " " + num_libs + " " + num_roads);
            } else if(!hasEdge.get(cities[i][1])) {
                hasEdge.put(cities[i][1],true);
                num_libs--;
                num_roads++;
                System.out.println("Have not seen the second city"+ " " + num_libs + " " + num_roads);
            } else if(both cities are accounted for but were previously separate){
                num_libs--;
                num_roads++;
            }
        }
        System.out.println(hasEdge + " " + num_libs + " " + num_roads);
        int candidate_cost = (num_libs * c_lib) + (num_roads * c_road);
        if (min_cost > candidate_cost){
            min_cost = candidate_cost;
        }
        return min_cost;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

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

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
// New Solution
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    public static <K, V> int getKeysByValue(Map<Integer,ArrayList<Integer>> map, int value) {
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(value))
                .map(Map.Entry::getKey)
                .findFirst().get();
    }

    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {

        int num_roads = 0;
        int num_libs = n;
        // Start with lib in every city
        int min_cost = (num_libs * c_lib);

        Map<Integer, ArrayList<Integer>> Libraries = new HashMap<>();
        for(int i = 1; i <= n; i++){
            Libraries.put(i, new ArrayList<Integer>());
            Libraries.get(i).add(i);
        }
        System.out.println(Libraries);
        for(int i = 0; i < cities.length; i++){
            // if city1 and 2 are in different libraries
            // Move cities (and all connected neighbors) from their respective libraries into one
            int lib_for_city1 = getKeysByValue(Libraries, cities[i][0]);
            int lib_for_city2 = getKeysByValue(Libraries, cities[i][1]);
            if(lib_for_city1 != lib_for_city2){
                System.out.println("Adding " + Libraries.get(lib_for_city2) + " to " + Libraries.get(lib_for_city1));
                Libraries.get(lib_for_city1).addAll(Libraries.get(lib_for_city2));
                Libraries.remove(lib_for_city2);
                System.out.println(Libraries);
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
        // System.out.println(Libraries);
        return min_cost;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

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

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
