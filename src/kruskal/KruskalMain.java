package kruskal;

import java.util.*;
import java.io.*;

public class KruskalMain {
    public static void main(String[] args) {
        ArrayList<House> houses = new ArrayList<>();

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

        int nodeCount = 8; //[NEEDS TO BE CHANGED IF DATA SET IS CHANGED]

        KruskalMain graph = new KruskalMain();
        graph.kruskal(houses, nodeCount);
    }

    private void kruskal(ArrayList<House> graphHouses, int nodeCount) {
        String outputMessage = "";
        Collections.sort(graphHouses); //sorts in ascending order

        ArrayList<House> houses = new ArrayList<>(); //lists edges

        DisjointSet nodeSet = new DisjointSet(nodeCount + 1); //initializing singleton sets for each node in graph

        for (int i = 0; i < graphHouses.size() && houses.size() < (nodeCount - 1); i++) { //loops over the all edges
            House currentHouse = graphHouses.get(i);
            int root1 = nodeSet.find(currentHouse.getSource());   //finds the root
            int root2 = nodeSet.find(currentHouse.getDestination());

            if (root1 != root2) {  //if roots are in different sets then a cycle is not created
                houses.add(currentHouse);  //add edge to the graph
                nodeSet.union(root1, root2);  //union the sets
            }
        }

        outputMessage += "\nMost Effective Route (" + houses.size() + " edges)\n";
        int totalEdgeWeight = 0;
        for (House house : houses) {
            outputMessage += house + "\n";
            totalEdgeWeight += house.getDistance();
        }
        outputMessage += "\nTotal distance of all houses (in foot) = " + totalEdgeWeight + "ft";

        outputMessage += "\nExpected cost for fibre optics ($4.0/ft) = $" + cableCost(totalEdgeWeight);
        outputMessage += "\nExpected cost for electrical cables ($1.65/ft) = $" + electricalCost(totalEdgeWeight);
        System.out.println(outputMessage); //displays outputMessage to the console
        export(outputMessage); //calls Export() function which creates a text file with outputMessage


    }

    private float cableCost(int distance) {
        return distance * 4.00f;
    }

    private float electricalCost(int distance) {
        return distance * 1.65f;
    }

    private void export(String outputMessage) {
        try (PrintWriter outputFile = new PrintWriter(new File("output.txt"))) {
            outputFile.println(outputMessage);
            System.out.println("\n\"output.txt\" has been created");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error creating file.");
        }
    }
}
