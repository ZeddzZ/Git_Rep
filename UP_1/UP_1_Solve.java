package MyPackage.UP_1;

import java.io.*;
import java.util.*;

/**
 * Created by zheny_000 on 10.02.2015.
 */
class Juice {
    private int amountComponents;
    private ArrayList <String> components;
    public Juice (int amountComponents, ArrayList <String> components) {
        this.amountComponents = amountComponents;
        this.components = components;
    }
    public void outData () {
        for (int i = 0; i < amountComponents; i++)
            System.out.print(components.get(i) + " ");
        System.out.println();
    }
    public ArrayList <String> getComponents() {
        return components;
    }
    public int getAmountComponents() {
        return amountComponents;
    }
}
public class UP_1_Solve extends Thread{
    ArrayList <String> componentsWithCase;
    CodeComparator cc = new CodeComparator();
    LengthComparator lc = new LengthComparator();
    public UP_1_Solve()throws  IOException{
        solve();
    }
    public void solve() throws IOException {
        ArrayList <String> juices = data("src\\MyPackage\\UP_1\\juice_2.in");
        ArrayList <Juice> juice = toJuice(juices);
        componentsWithCase = takeComponents("src\\MyPackage\\UP_1\\juice_2.in");
        start();
        ArrayList <String> componentsUnique = takeComponents("src\\MyPackage\\UP_1\\juice_2.in");
        componentsUnique = uniqueComponents(componentsUnique);
        outData(componentsUnique, "src\\MyPackage\\UP_1\\juice1.out");
        outData(componentsWithCase, "src\\MyPackage\\UP_1\\juice2.out");
//        for(Juice j: juice)
//            j.outData();
//        System.out.println();
        Collections.sort(juice, lc);
//        for(Juice j: juice)
//            j.outData();
//        System.out.println();
        PrintWriter pw = new PrintWriter(new FileWriter("src\\MyPackage\\UP_1\\juice3.out"));
        pw.println("You need " + cleanCount(juice) + " cleans.");
        pw.flush();
        pw.close();
    }
    ArrayList <String> data(String way) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(way));
        ArrayList <String> list = new ArrayList <String>();
        String buf;
        while (br.ready()) {
            buf = br.readLine();
            list.add(buf);
        }
        br.close();
        return list;
    }
    ArrayList <String> takeComponents(String way) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(way));
        ArrayList <String> list = new ArrayList <String>();
        String buf;
        StringTokenizer st;
        while (br.ready()) {
            buf = br.readLine();
            st = new StringTokenizer(buf, " ");
            while (st.hasMoreTokens())
                list.add(st.nextToken());
        }
        br.close();
        return list;
    }
    ArrayList <String> uniqueComponents(ArrayList <String> al) {
        String stringToCompare;
        for (int i = 0; i < al.size(); i++) {
            stringToCompare = al.get(i);
            for (int j = i+1; j<al.size(); j++)
                if (stringToCompare.equals(al.get(j)))
                    al.remove(j--);
        }
        return al;
    }
    ArrayList <Juice> toJuice(ArrayList <String> al) {
        StringTokenizer st;
        String S;
        ArrayList <Juice> j = new ArrayList<Juice>();

        for( String s: al) {
            ArrayList <String> components = new ArrayList<String>();
            int i = 0;
            st = new StringTokenizer(s, " ");
            while (st.hasMoreTokens()) {
                S = st.nextToken().toLowerCase();
                components.add(S);
                i++;
            }
            Collections.sort(components);
            Juice juiceTemp = new Juice(i, components);
            j.add(juiceTemp);
        }
        return  j;
    }
    int cleanCount (ArrayList <Juice> juice) {
        int count = 0;
        int originCount = juice.size();
        Map <ArrayList <String>, ArrayList <String>> hm = new HashMap<ArrayList <String>, ArrayList <String>>();
        ArrayList <String> components1;
        ArrayList <String> components2;
        for (int i = 0; i < juice.size(); i++)
            for (int j = i; j < juice.size(); j++) {
                components1 = juice.get(i).getComponents();
                components2 = juice.get(j).getComponents();
                if ( ((components1.containsAll(components2))||(components2.containsAll(components1))) &&(j != i) ) {
                    if (!hm.containsKey(components2)) {
                        count++;
                        if (components1.containsAll(components2)) {
                            hm.put(components1, components2);
                            juice.remove(j);
                            j--;

//                            for(Juice jj: juice)
//                                jj.outData();
//                            System.out.println();

                        }
                        if (components2.containsAll(components1)) {
                            hm.put(components2, components1);
                            juice.remove(i);
                            i--;

//                            for(Juice jj: juice)
//                                jj.outData();
//                            System.out.println();

                            break;
                        }
                    }
                    else {
                        if (hm.get(components2).containsAll(components1)) {
                            count++;
                            juice.remove(j);
                            j--;

//                            for(Juice jj: juice)
//                                jj.outData();
//                            System.out.println();

                        }
                        if (components1.containsAll(hm.get(components2))) {
                            count++;
                            hm.put(components1, components2);
                            juice.remove(j);
                            j--;

//                            for(Juice jj: juice)
//                                jj.outData();
//                            System.out.println();

                        }
                    }
               }
            }
        return originCount - count;
    }
    public void outData (ArrayList <String> al, String way) throws IOException{
        PrintWriter pw = new PrintWriter(new FileWriter(way));
        for (String s : al)
            pw.println(s);
        pw.flush();
        pw.close();
    }

    @Override
    public void run() {
        Collections.sort(componentsWithCase, cc);
    }
    static class CodeComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            String temp1 = new String("_");
            String temp2 = new String("_");
            if (((String)o1).charAt(0) < 93)
                temp1 += o1;
            else
                temp1 = (String)o1;
            if (((String)o2).charAt(0) < 93)
                temp2 += o2;
            else
            //if (((String)o2).charAt(0) > 94)
                temp2 = (String)o2;
            return temp1.compareTo(temp2);
        }
    }
    static class LengthComparator implements Comparator {
        public int compare(Object o1, Object o2) {

            return ((Juice)o1).getAmountComponents() - ((Juice)o2).getAmountComponents();
        }
    }
}
