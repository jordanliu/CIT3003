package kruskal;

import java.util.Arrays;

public class DisjointSet {

    private int[] set;

    DisjointSet(int numElements) {
        set = new int[numElements];
        Arrays.fill(set, -1);
    }

    public int[] getSet() {
        return set;
    }

    void union(int root1, int root2) { //merge two sets
        if (set[root2] < set[root1]) { //root2 is deeper
            set[root1] = root2; //root2 new root
        } else {
            if (set[root1] == set[root2]) {
                set[root1]--; //update height if they're the same
            }
            set[root2] = root1; //root1 new root
        }
    }

    int find(int x) {
        if (set[x] < 0) { //if tree is a root, return index
            return x;
        }
        int next = x;
        while (set[next] > 0) { //loop until root is found
            next = set[next];
        }
        return next;
    }
}