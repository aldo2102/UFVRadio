/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Visao.Consultas;
import controle.conexao;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aldohenrique
 */
public class Propagandas {

    public void mostraPropagandas() {
        String consultaprog;
        try {
            conexao.getConexao();
            consultaprog = ("select * from propaganda");
            Statement st = conexao.getConexao().createStatement();
            ResultSet rs = st.executeQuery(consultaprog);
            int cancela = 0;
            while (rs.next() && cancela != -1 && cancela != 2) {
                String titulo = rs.getString("titulo_prop");
                String texto = rs.getString("texto");
                String requerente = rs.getString("nome_req_prop");


                UIManager.getDefaults().put("OptionPane.background", new Color(217, 232, 238));
                UIManager.put("Panel.background", new Color(217, 232, 238));
                UIManager.put("OptionPane.okButtonText", "Proxima");

                cancela = JOptionPane.showConfirmDialog(null, ("Requerente: " + requerente + "\n"
                        + "Titulo: " + titulo + "\n"
                        + "Texto: " + texto + "\n"
                        ), "Anúncios", JOptionPane.WARNING_MESSAGE);
            }
            conexao.desconect();
        } catch (Exception ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
