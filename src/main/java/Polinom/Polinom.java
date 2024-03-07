package Polinom;

import java.util.TreeMap;

public class Polinom{
    private TreeMap<Integer,Double> monome;

    public Polinom() {
        this.monome = new  TreeMap<Integer,Double>();
    }

    public TreeMap<Integer, Double> getMonome() {
        return monome;
    }

    public void setMonome(TreeMap<Integer, Double> monome) {
        this.monome = monome;
    }


    @Override
    public String toString() {
        String result = new String();
        for(int i: monome.keySet()){
            if(monome.containsKey(i)) {
                if (monome.get(i) != 0) {
                    if (i == 1) {
                        result = result + monome.get(i) + "*x";
                    } else if (i == 0) {
                        result = result + monome.get(i);
                    } else {
                        result = result + monome.get(i) + "*x^" + i;
                    }
                    int ok = 0;
                    for (int j : monome.keySet()) {
                        if (j > i && ok == 0 && monome.get(j)!=0) {
                            result = result + " + ";
                            ok = 1;
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return this.monome.equals(((Polinom)obj).getMonome());
    }
}
