/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telenorkalkuláció;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Család
 */
public class TelenorKalkuláció {
    private static Ablak ablak;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Klasszik klasszik2 = new Klasszik(2590, 36.5f, 39, 39);
//        System.out.println(klasszik2.számít(41, 17, 89, 20, 32, 4, 4, 61));
//        
//        if (true) return;
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException 
                | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TelenorKalkuláció.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ablak = new Ablak();
        ablak.setVisible(true);
    }

    public static Ablak getAblak() {
        return ablak;
    }
}
