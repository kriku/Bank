package Bank;

import java.util.ArrayList;

/**
 * Created by krikun on 23.07.2015.
 */
public class Bank {
    private ArrayList<Branch> branches = new ArrayList<>();

    public int getCountBranch() {
        return branches.size();
    }

    public Branch getBranch(int index) {
        return branches.get(index);
    }

    public void addBranch(String name) {
        branches.add(new Branch(name));
    }
}
