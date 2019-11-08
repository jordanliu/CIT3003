import java.util.*;
import java.io.*;

// TODO: 10/31/2019 IMPLEMENT PARALLEL ALGORITHMS
public class KruskalMain {
    public static void main(String[] args) {
        ArrayList<House> graphHouses = new ArrayList<House>();        //Edge List
        //graphHouses.add(new House(3, 5, 2));

        String filepath = "input.txt";
        try {
            Scanner x;
            x = new Scanner(new File(filepath));

            while (x.hasNext()) {
                try {
                    int source = 0, destination = 0, distance = 0;

                    source = x.nextInt();
                    destination = x.nextInt();
                    distance = x.nextInt();

                    graphHouses.add(new House(source, destination, distance));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("File error, please enter text in \"input.txt\" - format [0 0 0 - source destination distance]");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found, please enter text in \"input.txt\" - format [0 0 0 - source destination distance]");
        }

        int nodeCount = 8;        // TODO: 10/30/2019 Add error exception and check nodeCount (currently manual)

        KruskalMain graph = new KruskalMain();
        graph.kruskal(graphHouses, nodeCount);
    }

    public void kruskal(ArrayList<House> graphHouses, int nodeCount) {
        String outputMessage = "";
        Collections.sort(graphHouses); //Sorts in ascending order

        ArrayList<House> houses = new ArrayList<House>(); //list of edges included in the mst, initially empty

        DisjointSet nodeSet = new DisjointSet(nodeCount + 1); //initializing singleton sets for each node in graph.  (nodeCount +1) to account for arrays indexing from 0

        for (int i = 0; i < graphHouses.size() && houses.size() < (nodeCount - 1); i++) { //loop over all edges
            House currentHouse = graphHouses.get(i);
            int root1 = nodeSet.find(currentHouse.getSource());        //Find root
            int root2 = nodeSet.find(currentHouse.getDestination());

            if (root1 != root2) {            //if roots are in different sets then a cycle is not created
                houses.add(currentHouse);        //add the edge to the graph
                nodeSet.union(root1, root2);    //union the sets
            }
        }

        outputMessage += "\nFinal Minimum Spanning Tree (" + houses.size() + " edges)\n";
        int totalEdgeWeight = 0;
        for (House house : houses) {
            outputMessage += house + "\n";        //prints each edge
            totalEdgeWeight += house.getDistance(); //sums each edge weight (distance)
        }
        outputMessage += "\nTotal distance of all houses (in foot) = " + totalEdgeWeight + "ft";


        System.out.println(outputMessage); //displays outputMessage to the console
        expectedCosts(totalEdgeWeight); //calls expectedCosts() which calculates expected costs of wiring
        export(outputMessage); //calls Export() function which creates a text file with outputMessage


    }

    public void expectedCosts(int distance) {
        float cableCost = 4.0f;
        float electricalCost = 1.65f;

        System.out.println("Expected cost for fibre optics ($" + cableCost + "/ft) = $" + (distance * cableCost));
        System.out.println("Expected cost for electrical cables ($" + electricalCost + "/ft) = $" + (distance * electricalCost));
    }

    public void export(String outputMessage) {
        try (PrintWriter outputFile = new PrintWriter(new File("output.txt"))) {
            outputFile.println(outputMessage);
            System.out.println("\n\"output.txt\" has been created");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error creating file.");
        }
    }
}
