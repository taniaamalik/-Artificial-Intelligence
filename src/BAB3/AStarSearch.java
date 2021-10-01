package BAB3;

import java.util.ArrayList;

public class AStarSearch {

    public static ArrayList<City> expandable;
    public static City a, b, c, d, e, f, g;
    public static City[] all;
    public static int iterasi = 0;

    public static void main(String[] args) {
        
        a = new City("a", 50, -1, 22, -1, -1, -1, 24, 19);
        b = new City("b", 47, 22, -1, 30, 48, -1, -1, 15);
        c = new City("c", 10, -1, 30, -1, 16, -1, -1, -1);
        d = new City("d", 0, -1, 48, 16, -1, 17, 50, -1);
        e = new City("e", 15, -1, -1, -1, 17, -1, 36, -1);
        f = new City("f", 45, 24, -1, -1, 50, 36, -1, 14);
        g = new City("g", 31, 19, 15, -1, -1, -1, 14, -1);
        all = new City[7];
        expandable = new ArrayList<City>();
        all[0] = a;
        all[1] = b;
        all[2] = c;
        all[3] = d;
        all[4] = e;
        all[5] = f;
        all[6] = g;

        City from = a;
        City target = d;

        City jawaban = AStar(from, target);
        System.out.println("The Best Path from " + from.name + " to " + target.name + " is : ");
        System.out.println("PATH : ");
        for (int i = 0; i < jawaban.path.size(); i++) {
            System.out.print(jawaban.path.get(i).name + " ==> ");
        }
        System.out.println(jawaban.name);
        System.out.println("With Path Cost= " + jawaban.pathCost);

        System.out.println("");
        System.out.println("And leaves node(City) are : ");
        for (int i = 0; i < expandable.size(); i++) {
            System.out.print("City : " + expandable.get(i).name + " With path= ");
            for (int j = 0; j < expandable.get(i).path.size(); j++) {
                System.out.print(expandable.get(i).path.get(j).name + "==>");
            }
            System.out.println(expandable.get(i).name);
        }
    }

    public static City AStar(City state, City target) {
        System.out.println("Iteration number : " + iterasi);
        iterasi++;
        System.out.println("Now On  = " + state.name);
        System.out.println("Goal = " + target.name);

        for (int i = 0; i < state.range.length; i++) {
            if (state.range[i] != -1) {
                City n = new City(all[i]);
                n.pathCost = state.pathCost + state.range[i];
                n.path = new ArrayList<City>();
                for (int j = 0; j < state.path.size(); j++) {
                    n.path.add(state.path.get(j));
                }
                n.path.add(state);
                expandable.add(n);

            }
        }
        System.out.println("Leaves node(City) for now is  :");
        for (int i = 0; i < expandable.size(); i++) {
            int t = expandable.get(i).pathCost + expandable.get(i).h;
            System.out.println(expandable.get(i).name + " with  f(n) = " + t);
        }

        City next = null;
        for (int i = 0; i < expandable.size(); i++) {
            City check = expandable.get(i);
            int f = check.pathCost + check.h;
            if (next == null) {
                next = check;
            } else {
                int fnext = next.pathCost + next.h;
                if (f < fnext) {
                    next = check;
                }
            }
        }

        if (next.name == target.name) {
            System.out.println("Best Path Found ! A* Stopped");
            return next;
        } else {
            System.out.println("City " + next.name + " is choosen for next iteration! ");
            System.out.println("===============================================");
            System.out.println("");
            expandable.remove(next);
            City answer = AStar(next, target);
            return answer;
        }

    }

}

class City {

    public String name;
    public int range[];
    public int h;
    public int pathCost;
    public ArrayList<City> path;

    public City(String nama, int h, int r1, int r2, int r3, int r4, int r5, int r6, int r7) {

        range = new int[7];
        this.h = h;
        this.name = nama;
        this.range[0] = r1;
        this.range[1] = r2;
        this.range[2] = r3;
        this.range[3] = r4;
        this.range[4] = r5;
        this.range[5] = r6;
        this.range[6] = r7;
        pathCost = 0;
        path = new ArrayList<City>();
    }

    public City(City city) {
        this.range = city.range;
        this.name = city.name;
        this.h = city.h;
        this.pathCost = city.pathCost;
        this.path = city.path;
    }
}