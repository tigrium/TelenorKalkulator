/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telenorkalkuláció;

import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import telenorkalkuláció.csomagok.Klasszik;

/**
 *
 * @author Család
 */
public class TelenorKalkuláció {

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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelenorKalkuláció.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TelenorKalkuláció.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TelenorKalkuláció.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TelenorKalkuláció.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Ablak ablak = new Ablak();
        ablak.setVisible(true);
    }
}
