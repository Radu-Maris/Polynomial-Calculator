package Logic;
import Polinom.Polinom;
import com.sun.source.tree.Tree;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import java.util.Map;

public class Operatii{
    public static void test(Polinom x){
        for(int i : x.getMonome().keySet()){
            System.out.println(i + " " + x.getMonome().get(i));
        }
    }
    public static TreeMap<Integer,Double> sum (Polinom x, Polinom y){
        TreeMap<Integer,Double> suma = new TreeMap<Integer,Double>();
        suma.putAll(x.getMonome());
        for(int i: y.getMonome().keySet()){
            if(x.getMonome().containsKey(i)){
                double aux = x.getMonome().get(i) + y.getMonome().get(i);
                suma.put(i,aux);
            }
            else{
                double aux = y.getMonome().get(i);
                suma.put(i,aux);
            }
        }
        return suma;
    }

    public static TreeMap<Integer,Double> dif (Polinom x, Polinom y){
        TreeMap<Integer,Double> sub = new TreeMap<Integer, Double>();
        sub.putAll(x.getMonome());
        for(int i: y.getMonome().keySet()){
            if(x.getMonome().containsKey(i)){
                double aux = x.getMonome().get(i) - y.getMonome().get(i);
                sub.put(i,aux);
            }
            else{
                double aux = -1 * y.getMonome().get(i);
                sub.put(i,aux);
            }
        }
        return sub;
    }

    public static TreeMap<Integer,Double> deriv (Polinom x){
        TreeMap<Integer,Double> derivative = new TreeMap<Integer, Double>();
        int j=0;
        for(int i: x.getMonome().keySet()){
            if(i == 0){
                continue;
            }
            else {
                j = i - 1;
                double aux = x.getMonome().get(i) * i;
                derivative.put(j, aux);
            }
        }
        derivative.put(j+1,0.0);
        return derivative;
    }
    public static TreeMap<Integer,Double> integ (Polinom x){
        TreeMap<Integer,Double> integrate = new TreeMap<Integer, Double>();
        int j=0;
        for(int i: x.getMonome().keySet()){
            double aux = x.getMonome().get(i) * 1/(i+1);
            DecimalFormat variabila = new DecimalFormat("0.###");
            aux = Double.parseDouble(variabila.format(aux));
            integrate.put(i+1, aux);
        }
        return integrate;
    }
    public static TreeMap<Integer,Double> mul (Polinom x, Polinom y){
        TreeMap<Integer,Double> multiply = new TreeMap<Integer, Double>();
        for(int i=0;i<y.getMonome().size()+x.getMonome().size();i++){
            multiply.put(i,0.0);
        }
        multiply.put(0,0.0);
        for(int i: y.getMonome().keySet()){
            for(int j: x.getMonome().keySet()) {
                double aux = x.getMonome().get(j) * y.getMonome().get(i);
                int poz = i+j;
                if(multiply.containsKey(poz)){
                    multiply.put(poz, multiply.get(poz)+aux);
                }
                else{
                    multiply.put(poz,aux);
                }
            }
        }
        return multiply;
    }
    public static ArrayList<TreeMap<Integer,Double>> div(Polinom x, Polinom y){
        int maxPowerX=0;
        int maxPowerY=0;
        for (int i:x.getMonome().keySet()) {
            maxPowerX=i;
        }
        for (int i:y.getMonome().keySet()) {
            maxPowerY=i;
        }
        ArrayList<TreeMap<Integer,Double>> rezultat = new ArrayList<>();
        Polinom x2 = new Polinom();
        TreeMap<Integer, Double> x1 = new TreeMap<Integer, Double>();
        TreeMap<Integer,Double> result = new TreeMap<Integer,Double>();
        Double coeff = 0.0;
        DecimalFormat coeffZecimal = new DecimalFormat("0.###");
        if(maxPowerY==0){
            for (int i:x.getMonome().keySet()) {
                coeff = x.getMonome().get(i)/y.getMonome().get(0);
                coeff = Double.parseDouble(coeffZecimal.format(coeff));
                x1.put(i,coeff);
            }
            rezultat.add(x1);
            return rezultat;
        }
        while(maxPowerX>=maxPowerY){
            coeff =x.getMonome().get(maxPowerX)/y.getMonome().get(maxPowerY);
            coeff = Double.parseDouble(coeffZecimal.format(coeff));

            Integer grad = maxPowerX-maxPowerY;
            result.put(grad,coeff);

            TreeMap<Integer, Double> t = new TreeMap<Integer, Double>();

            t.put(grad, coeff);
            Polinom t1 = new Polinom();
            t1.setMonome(t);

            t = Operatii.mul(t1,y);
            t1.setMonome(t);

            x1 = Operatii.dif(x,t1);
            x1.remove(maxPowerX);
            x.setMonome(x1);

            for (int i:x.getMonome().keySet()) {
                maxPowerX=i;
            }
            for (int i:y.getMonome().keySet()) {
                maxPowerY = i;
            }
        }
        rezultat.add(result);
        rezultat.add(x1);
        return rezultat;
    }
}
