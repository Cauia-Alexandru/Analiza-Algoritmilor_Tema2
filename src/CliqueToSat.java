import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CliqueToSat{

    void generateFirstClauses(int nodes, int k){
        //prima etapa este formata din genrarea a k clauze, unde fiecare clauza va contine
        //nodeList.size() elemente. Indicile i reprezinta nr clauzei, iar j->nr elementului din clauza

        String s = "";
        int j = 0;
        for(int i = 1; i <= k; i++){
            System.out.print("(");
            for(j = 1; j < nodes; j++){
                s = "x" + i + j + " V ";
                System.out.print(s);
            }
            s = "x" + i + j;
            System.out.print(s);
            if(i < k)
                System.out.print(") ^ ");
            else                            //daca e ultima clauza
                System.out.print(")");      //scap de ultimul ^
        }
    }

    void generateSecondTypeClause(int nodes, int k){
        //grupez in clauze cate 2 elemente de pe aceeasi pozitie din clauze diferite
        //pentru a ma asigura ca doar un nod in clauza este true
        //indicii elementelor avanseaza in 3 moduri diferite, deci am facut 3 for-uri

        for(int c = 1; c <= nodes; c++){
            for(int b = 1; b <= k; b++){
                for(int a = b + 1; a <= k; a++){
                    if(a!=b)
                        System.out.print(" ^ (~x" + b + c + " V ~x" + a + c + ")");
                }
            }
        }
    }

    void generateThirdTypeClause(int nodes, int k, ArrayList<Edge> edges){
        for(int x = 1; x <= nodes; x++){
            for(int y = x + 1; y <= nodes; y++){
                Edge e = new Edge(x, y);
                //selectez nodurile care nu au muchie intre ele
                if(!edges.contains(e)) {
                    for(int z = 1; z <=k; z++){
                        for(int t = 1; t <=k; t++){
                            System.out.print(" ^ (~x" + z + x + " V ~x" + t + y + ")");
                        }
                    }
                }
            }
        }
    }


    void generateString(Set<Integer> nodeList, int k, ArrayList<Edge> edges){

        //int j, i;
        int nodes = nodeList.size();


        generateFirstClauses(nodes, k);

        generateSecondTypeClause(nodes, k);

        generateThirdTypeClause(nodes, k, edges);

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

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CliqueToSat res = new CliqueToSat();
        res.generateString(nodesList, k, edgeArrayList);
    }


}
