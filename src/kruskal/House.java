package kruskal;

public class House implements Comparable<House> {
    private int source;
    private int destination;
    private int distance;

    House(int source, int destination, int distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    int getSource() {
        return source;
    }

    int getDestination() {
        return destination;
    }

    int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(House house) {
        return this.getDistance() - house.getDistance();
    }

    @Override
    public String toString() {
        return "kruskal.House (" + getSource() + " -> " + getDestination() + ") Distance: " + getDistance();
    }
}
