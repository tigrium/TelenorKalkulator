/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telenorkalkuláció.csomagok;

/**
 *
 * @author Család
 */
public class Klasszik2 implements Tarifacsomag {
    private static float áfa = 1.27f;
    private static float havidíj = 2590;
    private static float családiCsomag = 1099;
    
    private static float lebeszélhetőBelül = (float)(havidíj * 0.6);
    private static float lebeszélhetőKívül = (float)(havidíj * 0.4);
    
    private static float percdíjLebeszélhető = 36.5f;
    private static float percdíj = 39;
    private static float vezetékesPercdíj = 5.51f;
    private static float smsdíj = 39;
    
    private static float kapcsolásiDíj = 1.96f;
    private static float vezetékesKapcsolásiDíj = 5.51f;

    @Override
    public float számít(int belülPerc, int belülDarab, 
            int kívülPerc, int kívülDarab, int vezetékesPerc, int vezetékesDarab, 
            int smsDarab) {
        
        float fizetni = havidíj + családiCsomag;
        
        float lebeszélhetőPerc, továbbiPerc;
        
        // hálózaton belül
        lebeszélhetőPerc = Math.min(lebeszélhetőBelül / percdíjLebeszélhető, belülPerc);
        továbbiPerc = Math.max(belülPerc - lebeszélhetőPerc, 0);
        fizetni += lebeszélhetőPerc * percdíjLebeszélhető + továbbiPerc * percdíj;
        
        
        // hálózaton kívül
        
        
        return fizetni;
    }
    
}
