import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class kruskal {
    private int vertices = 0;
    private List<edge> edgelist = new ArrayList<edge>();
    private List<edge> resultedge = new ArrayList<edge>();
    private int  weight = 0;

    private String st = new String();
    public kruskal(int v, List<edge> l){
        vertices = v;
        edgelist = l;
        subset s[] = new subset[vertices];
        for(int i=0; i<vertices; i++)
        {
            s[i] = new subset(i, -1);
        }
        edgelist.sort(new Comparator<edge>() {
            @Override
            public int compare(edge o1, edge o2) {
                return o1.weight - o2.weight;
            }
        });
        for(int i=0; i<edgelist.size(); i++)
        {
            if(edgelist.get(i).getStart() > edgelist.get(i).getEnd())
            {
                edgelist.get(i).swap();
            }
        }
        int x = 0, j = 0;
        while(x < vertices-1){
            edge temp = edgelist.get(j);
            int m = findparent(s, temp.getStart());
            int n = findparent(s, temp.getEnd());


            if(m != n){
                resultedge.add(temp);
                union(s, m, n);
                weight+=temp.getWeight();
                x++;
            }
            j++;

        }
        StringBuffer sb = new StringBuffer();
        sb.append("Kruskal’s Algorithm:\n");
        sb.append("Total weight = " + weight + "\n");
        for(int i=0; i< resultedge.size(); i++) {
            sb.append(resultedge.get(i).getStart() + " " + resultedge.get(i).getEnd() + "\n");
        }
        st = sb.toString();
    }

    public String getSt() {
        return st;
    }

    private int findparent(subset[] subset, int x)
    {
        if(subset[x].parent == x){
            return subset[x].parent;
        }
        else {
            subset[x].parent = findparent(subset, subset[x].parent);
                    return subset[x].parent;
        }
    }

    private void union(subset[] subset, int i, int j)
    {
        int x = findparent(subset, i);
        int y = findparent(subset, j);
        if(subset[y].rank < subset[x].rank)
        {
            subset[y].parent = x;
        }
        else if(subset[x].rank < subset[y].rank)
        {
            subset[x].parent = y;
        }
        else{
            subset[y].parent = x;
            subset[x].rank++;
        }
    }
}
