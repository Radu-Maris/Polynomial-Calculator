package Logic;

import Interface.Interfata;
import Polinom.Polinom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private Interfata interfata;

    private String polinom1;
    private String polinom2;
    private String polinomRezultat;
    private Double auxiliar;

    Polinom x = new Polinom();
    Polinom y = new Polinom();
    Polinom rezultat = new Polinom();

    TreeMap<Integer,Double> aux = new TreeMap<Integer,Double>();
    TreeMap<Integer,Double> aux2 = new TreeMap<Integer,Double>();

    Pattern pattern = Pattern.compile("[+-]?[0-9]*(x(\\^[0-9]+)?)?");

    private int power = 0;
    private double coeff = 1.0;
    private int semn = 1;
    private int auxSemn =0;
    private int auxPow =0;
    private int auxPos= 0;
    public Controller(Interfata interfata){
        this.interfata = interfata;
        polinom1 = interfata.getTextField1().getText();
        polinom2 = interfata.getTextField2().getText();

        interfata.addVerificaButtonPlus(new Verifica());
        interfata.addVerificaButtonMinus(new Verifica2());
        interfata.addVerificaButtonMultiply(new Verifica3());
        interfata.addVerificaButtonDeriv(new Verifica4());
        interfata.addVerificaButtonInteg(new Verifica5());
        interfata.addVerificaButtonHelp(new Verifica6());
        interfata.addVerificaButtonDiv(new Verifica7());
    }
    public void afiseazaHelp() {
        JOptionPane.showMessageDialog(interfata, "The input should be in the form of\n\nsign coefficient x ^ power\n\nwhihout any spaces\nthe sign,coefficient and power are optional");
    }

    private void convertire(String polinom1,TreeMap<Integer,Double> aux){
        this.polinom1 = polinom1;
        Matcher matcher = pattern.matcher(polinom1);
        int ok=0;
        while(matcher.find()){
            if(!matcher.group().isEmpty()){
                String[] vector = matcher.group().split("[^0-9]");
                if (matcher.group().charAt(0) == '-') {
                    semn = -1;
                    auxSemn = 1;
                }
                else{
                    semn = 1;
                    auxSemn=0;
                }
                if(matcher.group().charAt(0)=='+'){
                    auxPos = 1;
                }
                else{
                    auxPos=0;
                }
                auxPow=0;
                if(matcher.group().charAt(auxSemn+auxPos) == 'x' || matcher.group().charAt(auxSemn+auxPos)=='X') {
                    auxPow = 1;
                }
                if(matcher.group().isEmpty()){
                    continue;
                }
                else{
                    if(auxPow==1){
                        power = 1;
                        coeff = 1;
                        aux.put(power,coeff * semn);
                    }
                }
                for (String s : vector) {
                    if (!s.isEmpty()) {
                        if(auxPow==1){
                            if(matcher.group().contains("^")){
                                power = Integer.parseInt(s);
                            }
                            coeff = 1.0;
                            auxPow=0;
                            aux.put(power, coeff * semn);
                        }
                        else{
                            if(!matcher.group().contains("^")){
                                if(matcher.group().contains("x")){
                                    power = 1;
                                }
                                else{
                                    power = 0;
                                }
                                coeff = Integer.parseInt(s);
                                aux.put(power, coeff * semn);
                            }
                            else {
                                if(ok==0){
                                    auxiliar = Double.parseDouble(s);
                                    ok=1;
                                }else{
                                    coeff = auxiliar;
                                    power=Integer.parseInt(s);
                                    ok=0;
                                    aux.put(power, coeff * semn);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    class Verifica implements ActionListener{
        public void actionPerformed(ActionEvent e){
            polinom1 = interfata.getTextField1().getText();
            polinom2 = interfata.getTextField2().getText();
            try{
                aux.clear();
                convertire(polinom1,aux);
                x.setMonome(aux);
                aux2.clear();
                convertire(polinom2,aux2);
                y.setMonome(aux2);
                rezultat.setMonome(Operatii.sum(x,y));
                polinomRezultat = "";
                polinomRezultat = rezultat.toString();
                interfata.getTextField3().setText(polinomRezultat);
            }
            catch(Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }
    class Verifica2 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            polinom1 = interfata.getTextField1().getText();
            polinom2 = interfata.getTextField2().getText();
            try{
                aux.clear();
                convertire(polinom1,aux);
                x.setMonome(aux);
                aux2.clear();
                convertire(polinom2,aux2);
                y.setMonome(aux2);
                rezultat.setMonome(Operatii.dif(x,y));
                polinomRezultat = "";
                polinomRezultat = rezultat.toString();
                interfata.getTextField3().setText(polinomRezultat);

            }
            catch(Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }

    class Verifica3 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            polinom1 = interfata.getTextField1().getText();
            polinom2 = interfata.getTextField2().getText();
            try{
                aux.clear();
                convertire(polinom1,aux);
                x.setMonome(aux);
                aux2.clear();
                convertire(polinom2,aux2);
                y.setMonome(aux2);
                rezultat.setMonome(Operatii.mul(x,y));
                polinomRezultat = "";
                polinomRezultat = rezultat.toString();
                interfata.getTextField3().setText(polinomRezultat);

            }
            catch(Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }

    class Verifica4 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            polinom1 = interfata.getTextField1().getText();
            polinom2 = interfata.getTextField2().getText();
            try{
                aux.clear();
                convertire(polinom1,aux);
                x.setMonome(aux);
                rezultat.setMonome(Operatii.deriv(x));
                polinomRezultat = "";
                polinomRezultat = rezultat.toString();
                if(polinomRezultat.isEmpty()){
                    polinomRezultat="0";
                }
                interfata.getTextField3().setText(polinomRezultat);

            }
            catch(Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }

    class Verifica5 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            polinom1 = interfata.getTextField1().getText();
            polinom2 = interfata.getTextField2().getText();
            try{
                aux.clear();
                convertire(polinom1,aux);
                x.setMonome(aux);
                rezultat.setMonome(Operatii.integ(x));
                polinomRezultat = "";
                polinomRezultat = rezultat.toString();
                polinomRezultat = polinomRezultat + " + Constant";
                interfata.getTextField3().setText(polinomRezultat);

            }
            catch(Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }
    class Verifica6 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                afiseazaHelp();
            }
            catch(Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }
    class Verifica7 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            polinom1 = interfata.getTextField1().getText();
            polinom2 = interfata.getTextField2().getText();
            try{
                aux.clear();
                convertire(polinom1,aux);
                x.setMonome(aux);
                aux2.clear();
                convertire(polinom2,aux2);
                y.setMonome(aux2);
                rezultat.setMonome(Operatii.div(x,y).get(0));
                polinomRezultat = "";
                polinomRezultat = rezultat.toString();
                interfata.getTextField3().setText(polinomRezultat);
            }
            catch(Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }
}
