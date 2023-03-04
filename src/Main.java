import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

    boolean existEdge(Edge case1, Edge case2, ArrayList<Edge> edges){

        for(int i = 0; i < edges.size(); i++){
            if(edges.get(i).getValue1() == case1.getValue1() &&
                    edges.get(i).getValue2() == case1.getValue2()){
                return true;
            }
            if(edges.get(i).getValue1() == case2.getValue1() &&
                    edges.get(i).getValue2() == case2.getValue2()){
                return true;
            }

        }
        return false;
    }

    boolean findClique(ArrayList<Edge> edgeArrayList, ArrayList<Integer> nodes){


        for(Integer nod1 : nodes){
            for(Integer nod2 : nodes){
                if(!nod1.equals(nod2)){
                    Edge case1 = new Edge((int)nod1, (int)nod2);
                    Edge case2 = new Edge((int)nod2, (int)nod1);

                    if(!existEdge(case1, case2, edgeArrayList))
                        return false;
                }
            }
        }
        return true;
    }

    boolean verifyCliqueInGraph(ArrayList<Edge> edgeArrayList, Set<Integer> nodes, int k, ArrayList<Integer> clique){
        //boolean result = false;
        if(clique.size() == k)
            return findClique(edgeArrayList, clique);

        for(Integer i : nodes){
            if(clique.contains(i)){
                continue;
            }
            clique.add(i);
            if(verifyCliqueInGraph(edgeArrayList, nodes, k, clique)){
                return true;
            }
            clique.remove(i);

        }

        return false;
    }

    public static void main(String[] args) {
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        Set<Integer> nodesList = new HashSet<>();
        int k = -1;
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            k = Integer.parseInt(br.readLine());
            int nodes = Integer.parseInt(br.readLine());
            int edges = Integer.parseInt(br.readLine());
            while((line = br.readLine()) != null){
                String[] legaturi = line.split(" ");
                int value1 = Integer.parseInt(legaturi[0]);
                int value2 = Integer.parseInt(legaturi[1]);
                Edge edge = new Edge(value1, value2);
                edgeArrayList.add(edge);
                nodesList.add(value1);
                nodesList.add(value2);
            }
//            for(Integer i : nodesList){
//                System.out.println(i);
//            }
//            for(Edge e : edgeArrayList){
//                System.out.print(e.getValue1());
//                System.out.println(e.getValue2());
//            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main main = new Main();
        ArrayList<Integer> clique = new ArrayList<>();
        boolean res = main.verifyCliqueInGraph(edgeArrayList, nodesList, k, clique);
        if (res) {
            System.out.println("True");
            return;
        }
        System.out.println("False");
    }
}
