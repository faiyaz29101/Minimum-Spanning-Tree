import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Prim {
    private int vertices = 0;
    private List<edge> edgelist = new ArrayList<edge>();
    private List<edge> resultedge = new ArrayList<edge>();
    private int  weight = 0;
    private int st = 0;
    private StringBuffer sb = new StringBuffer();
    public Prim(int v,int root, List<edge> l){
        vertices = v;
        edgelist = l;
        edgelist.sort(new Comparator<edge>() {
            @Override
            public int compare(edge o1, edge o2) {
                return o1.weight - o2.weight;
            }
        });
        st = root;
        edge temp = null;
        List<Integer> reslist = new ArrayList<Integer>();
        reslist.add(st);
        int k = 0;
        subset s[] = new subset[vertices];
        for(int i=0; i<vertices; i++)
        {
            s[i] = new subset(i, -1);
        }

        while(k < vertices-1) {
            List<edge> tot = new ArrayList<edge>();
            for (int i = 0; i < edgelist.size(); i++) {
                for (int j = 0; j < reslist.size(); j++) {
                    if (edgelist.get(i).getStart() == reslist.get(j) ||edgelist.get(i).getEnd() == reslist.get(j)) {
                        tot.add(edgelist.get(i));
                    }
                }
            }
            tot.sort(new Comparator<edge>() {
                @Override
                public int compare(edge o1, edge o2) {
                    return o1.weight - o2.weight;
                }
            });
            for(int i =0; i<tot.size(); i++){
                temp = tot.get(i);
                int m = findparent(s, temp.getStart());
                int n = findparent(s, temp.getEnd());
                if(m!=n){
                    resultedge.add(temp);
                    weight += temp.getWeight();
                    edgelist.remove(temp);
                    if(!reslist.contains(temp.getStart())){
                        reslist.add(temp.getStart());
                    }
                    if(!reslist.contains(temp.getEnd())){
                        reslist.add(temp.getEnd());
                    }
                    union(s,m,n);
                    k++;
                    break;
                }
            }

//            for (int i = 0; i < tot.size(); i++) {
//                System.out.println(tot.get(i).print());
//            }


        }
        sb.append("Prim's Algorithm: \nTotal weight = "+weight+"\nRoot Node = "+st+"\n");

        for(int i =0; i<resultedge.size(); i++)
        {
            sb.append(resultedge.get(i).getStart()+" "+resultedge.get(i).getEnd()+"\n");
        }
        //System.out.println(sb);
    }
    public String getPrim(){
        return sb.toString();
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
