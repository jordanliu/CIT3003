class DisjointSet {

    private int[] set;

    public DisjointSet(int numElements) {
        set = new int[numElements];
        for (int i = 0; i < set.length; i++) {
            set[i] = -1;
        }
    }

    public int[] getSet() {
        return set;
    }

    public void union(int root1, int root2) { //merge two sets (union)
        if (set[root2] < set[root1]) { //root2 is deeper
            set[root1] = root2; //make root2 new root
        } else {
            if (set[root1] == set[root2]) {
                set[root1]--; //update height if same
            }
            set[root2] = root1; //make root1 new root
        }
    }

    public int find(int x) {
        if (set[x] < 0) { //if tree is a root, return its index
            return x;
        }
        int next = x;
        while (set[next] > 0) {  //loop until root is found
            next = set[next];
        }
        return next;
    }
}