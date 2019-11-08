class House implements Comparable<House> {
    private int source;
    private int destination;
    private int distance;

    public House() {
        this.source = 0;
        this.destination = 0;
        this.distance = 0;
    }

    public House(int source, int destination, int distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(House house) {  //compare based on edge weight (for sorting)

        return this.getDistance() - house.getDistance();
    }

    @Override
    public String toString() {
        return "House (" + getSource() + " -> " + getDestination() + ") Distance: " + getDistance();
    }
}
