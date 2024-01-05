import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SameParent {
    private int parent = 0;
    private List<edge> l = new ArrayList<edge>();
    public SameParent(int parent)
    {
        this.parent = parent;
    }

    public int getParent() {
        return parent;
    }
    public edge getedge(int i)
    {
        return l.get(i);
    }
    public void delete(edge temp){
        l.remove(temp);
    }
    public void addEdge(edge temp)
    {
        l.add(temp);
    }
    public String print(){
        StringBuffer sb = new StringBuffer();
        sb.append(parent+"\n");
        for(int i =0; i< l.size(); i++)
        {
            sb.append(l.get(i).print());
        }
        return sb.toString();
    }
    public edge findMinWeight(){
        l.sort(new Comparator<edge>() {
            @Override
            public int compare(edge o1, edge o2) {
                return o1.weight - o2.weight;
            }
        });
        return l.get(0);
    }
    public boolean isParent(int p){
        if(p == parent)
        {
            return true;
        }
        else{
            return false;
        }
    }
    public int getSize(){
        return l.size();
    }

    public List<edge> getList() {
        return l;
    }
    public void sort(){
        l.sort(new Comparator<edge>() {
            @Override
            public int compare(edge o1, edge o2) {
                return o1.weight - o2.weight;
            }
        });
    }
}
