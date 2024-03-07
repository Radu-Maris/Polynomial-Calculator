import Logic.Controller;
import Logic.Operatii;

import Polinom.Polinom;

import java.util.ConcurrentModificationException;
import java.util.TreeMap;
import Interface.Interfata;

public class Main {
    public static void main(String[] args) {
        Polinom x = new Polinom();
        Polinom y = new Polinom();

        TreeMap<Integer,Double> aux3 = new TreeMap<Integer,Double>();
        Polinom rezultat = new Polinom();

        Interfata interfata = new Interfata();
        Controller controller = new Controller(interfata);
        interfata.setVisible(true);

    }
}