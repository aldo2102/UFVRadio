/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Visao.Player;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author aldohenrique
 */
public class DataHora extends Thread {
private JLabel hora;  
private JLabel data;
    private boolean mostrarData = true;  
    private Music tempo;
  
      

    public DataHora() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DataHora(JLabel data, JLabel hora) {
        this.hora = hora;
        this.data = data;   
    }

  
     
  
    @Override  
    public void run() {  
        try {  
            while (true) {  
                Date d = new Date();  
                StringBuilder data = new StringBuilder();  
                if (mostrarData) {  
                    SimpleDateFormat sdfData = new SimpleDateFormat("EEEE dd/MM/yyyy");  
                    data.append(sdfData.format(d));   
                }  
                SimpleDateFormat horas = new SimpleDateFormat("HH:mm:ss");
                Player teste = new Player();
                this.hora.setText(data.toString());  
                this.data.setText(horas.format(d));
                Thread.sleep(1000);  
                this.hora.revalidate();
                this.data.revalidate();
            }  
        } catch (InterruptedException ex) {  
            System.out.println("Problema na atualização da data/hora");  
        }  
    }
    
}  
