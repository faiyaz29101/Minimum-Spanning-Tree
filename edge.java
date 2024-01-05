public class edge {
    int start, end, weight;

    public edge(int s, int e, int w){
        start = s;
        end = e;
        weight = w;
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }

    public int getWeight() {
        return weight;
    }

    public void swap()
    {
        int t = start;
        start = end;
        end = t;
    }
    public String print(){
        StringBuffer sb = new StringBuffer();
        sb.append(start +" "+ end+ " "+weight +"\n");
        return sb.toString();
    }
}
