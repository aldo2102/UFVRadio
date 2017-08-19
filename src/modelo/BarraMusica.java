/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Visao.Player;
import javax.swing.JProgressBar;

/**
 *
 * @author aldohenrique
 */
public class BarraMusica implements Runnable{

    @Override
    public void run() {
        
    }
    public void dados(JProgressBar estadoMusica, int tempo){
        
         //determina valor minimo  
        estadoMusica.setMinimum(0);
//determina valor maximo  
        estadoMusica.setMaximum(100);
//Faz aparecer o valor em porcentagem  
        estadoMusica.setStringPainted(true);
        estadoMusica.setValue(tempo);
    }
}
