/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telenorkalkuláció.csomagok;

/**
 *
 * @author Kata
 */
public class Klasszik1 extends Klasszik {

    public Klasszik1(float havidíj, float percdíjLebeszélhető, float percdíj, float smsdíj) {
        super(havidíj, percdíjLebeszélhető, percdíj, smsdíj);
        
        lebeszélhetőBelül = havidíj;
        lebeszélhetőKívül = 0;
    }
    
}
