package org.example;
import Logic.Operatii;
import Polinom.Polinom;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class OperatiiTest {
    Polinom x = new Polinom();
    Polinom y = new Polinom();
    Polinom z = new Polinom();
    Polinom rezultat = new Polinom();
    Polinom expected = new Polinom();
    Polinom expected2 = new Polinom();
    Polinom expected3 = new Polinom();

    TreeMap<Integer,Double> aux = new TreeMap<Integer,Double>();
    TreeMap<Integer,Double> aux2 = new TreeMap<Integer,Double>();
    TreeMap<Integer,Double> aux3 = new TreeMap<Integer,Double>();
    TreeMap<Integer,Double> aux4 = new TreeMap<Integer,Double>();
    TreeMap<Integer,Double> aux5 = new TreeMap<Integer,Double>();
    TreeMap<Integer,Double> aux6 = new TreeMap<Integer,Double>();

    public void setValues(){
        aux.put(1,1.0);
        aux.put(2,1.0);
        x.setMonome(aux); //x = x + x^2

        aux2.put(1,3.0);
        aux2.put(3,1.0);
        y.setMonome(aux2); //y =3x + x^3

        aux3.put(0,4.0);
        aux3.put(2,10.0);
        z.setMonome(aux3); //z = 10x^2+4
    }

    @Test
        public void addTest(){
            setValues();

            aux4.put(1,4.0);
            aux4.put(2,1.0);
            aux4.put(3,1.0);
            expected.setMonome(aux4);

            aux5.put(0,4.0);
            aux5.put(1,1.0);
            aux5.put(2,11.0);
            expected2.setMonome(aux5);

            aux6.put(0,4.0);
            aux6.put(1,3.0);
            aux6.put(2,10.0);
            aux6.put(3,1.0);
            expected3.setMonome(aux6);

            rezultat.setMonome(Operatii.sum(x,y));
            assertEquals(expected, rezultat);

            rezultat.setMonome(Operatii.sum(x,z));
            assertEquals(expected2, rezultat);

            rezultat.setMonome(Operatii.sum(y,z));
            assertEquals(expected3, rezultat);
    }

    @Test
    public void difTest(){
        setValues();

        aux4.put(1,-2.0);
        aux4.put(2,1.0);
        aux4.put(3,-1.0);
        expected.setMonome(aux4);

        aux5.put(0,-4.0);
        aux5.put(1,3.0);
        aux5.put(2,-10.0);
        aux5.put(3,1.0);
        expected2.setMonome(aux5);

        aux6.put(0,4.0);
        aux6.put(1,-1.0);
        aux6.put(2,9.0);
        expected3.setMonome(aux6);

        rezultat.setMonome(Operatii.dif(x,y));
        assertEquals(expected, rezultat);

        rezultat.setMonome(Operatii.dif(y,z));
        assertEquals(expected2, rezultat);

        rezultat.setMonome(Operatii.dif(z,x));
        assertEquals(expected3, rezultat);
    }

    @Test
    public void mulTest(){
        setValues();

        aux4.put(2,3.0);
        aux4.put(3,3.0);
        aux4.put(4,1.0);
        aux4.put(5,1.0);
        expected.setMonome(aux4);

        aux5.put(1,4.0);
        aux5.put(2,4.0);
        aux5.put(3,10.0);
        aux5.put(4,10.0);
        expected2.setMonome(aux5);

        aux6.put(1,12.0);
        aux6.put(3,34.0);
        aux6.put(5,10.0);
        expected3.setMonome(aux6);

        rezultat.setMonome(Operatii.mul(x,y));
        assertEquals(expected.toString(), rezultat.toString());

        rezultat.setMonome(Operatii.mul(x,z));
        assertEquals(expected2.toString(), rezultat.toString());

        rezultat.setMonome(Operatii.mul(y,z));
        assertEquals(expected3.toString(), rezultat.toString());
    }

    @Test
    public void derivativeTest(){
        setValues();

        aux4.put(0,1.0);
        aux4.put(1,2.0);
        expected.setMonome(aux4);

        aux5.put(0,3.0);
        aux5.put(2,3.0);
        expected2.setMonome(aux5);

        aux6.put(1,20.0);
        expected3.setMonome(aux6);

        rezultat.setMonome(Operatii.deriv(x));
        assertEquals(expected.toString(), rezultat.toString());

        rezultat.setMonome(Operatii.deriv(y));
        assertEquals(expected2.toString(), rezultat.toString());

        rezultat.setMonome(Operatii.deriv(z));
        assertEquals(expected3.toString(), rezultat.toString());
    }
    @Test
    public void integrateTest(){
        setValues();

        aux4.put(2,0.5);
        aux4.put(3,0.333);  //(1/2)*x^2 + (1/3)*x^3 +C
        expected.setMonome(aux4);

        aux5.put(2,1.5);
        aux5.put(4,0.25);
        expected2.setMonome(aux5);

        aux6.put(1,4.0);
        aux6.put(3,3.333);
        expected3.setMonome(aux6);

        rezultat.setMonome(Operatii.integ(x));
        assertEquals(expected.toString(), rezultat.toString());

        rezultat.setMonome(Operatii.integ(y));
        assertEquals(expected2.toString(), rezultat.toString());

        rezultat.setMonome(Operatii.integ(z));
        assertEquals(expected3.toString(), rezultat.toString());
    }
}
