import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class KruskalMain {
    public static void main(String[] args) {
        ArrayList<House> houses = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter # of houses (Sample count is 8): ");
        int nodeCount = scanner.nextInt(); //takes in nodeCount input from the user

        //int nodeCount = 8; {nodeCount for input.txt)

        String filepath = "input.txt";
        try {
            Scanner x;
            x = new Scanner(new File(filepath));

            while (x.hasNext()) {
                try {
                    int source, destination, distance;

                    source = x.nextInt();
                    destination = x.nextInt();
                    distance = x.nextInt();
                    houses.add(new House(source, destination, distance));

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("File error, please enter text in \"input.txt\" - format [0 0 0 - source destination distance]");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found, please enter text in \"input.txt\" - format [0 0 0 - source destination distance]");
        }

        KruskalMain graph = new KruskalMain();
        graph.kruskal(houses, nodeCount);

    }

    private void kruskal(ArrayList<House> graphHouses, int nodeCount) {
        String output;
        Collections.sort(graphHouses); //sorts in ascending order

        ArrayList<House> houses = new ArrayList<>(); //edge list

        DisjointSet nodeSet = new DisjointSet(nodeCount + 1); //creating sets for each node in the graph

        for (int i = 0; i < graphHouses.size() && houses.size() < (nodeCount - 1); i++) { //loops over the all edges
            House currentHouse = graphHouses.get(i);
            int root1 = nodeSet.find(currentHouse.getSource());   //finds the root
            int root2 = nodeSet.find(currentHouse.getDestination());

            if (root1 != root2) {  //if roots are in different sets then a cycle is not created
                houses.add(currentHouse);  //adds edge to the graph
                nodeSet.union(root1, root2);  //union the sets
            }
        }

        output = "\nMost Effective Route (" + houses.size() + " edges)\n";
        int totalEdgeWeight = 0;
        for (House house : houses) {
            output += house + "\n"; //gets toString() from house class and appends
            totalEdgeWeight += house.getDistance(); //sums total edge weight for all houses
        }

        output += "\nTotal distance of all houses (in feet) = " + totalEdgeWeight + "ft";
        output += "\nExpected cost for fibre optics ($4.0/ft) = $" + cableCost(totalEdgeWeight);
        output += "\nExpected cost for electrical cables ($1.65/ft) = $" + electricalCost(totalEdgeWeight);
        System.out.println(output); //displays output to the console

    }

    private float cableCost(int distance) {
        return distance * 4.00f;
    } //sample cost for cables

    private float electricalCost(int distance) {
        return distance * 1.65f;
    } //sample cost for cables

}
