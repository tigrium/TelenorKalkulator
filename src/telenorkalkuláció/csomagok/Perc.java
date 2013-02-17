/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telenorkalkuláció.csomagok;

/**
 *
 * @author Család
 */
public class Perc implements  Tarifacsomag {
    private float családiCsomag = 1099;
    private float havidíj;
    
    private float lebeszélhető;
    
    private float percdíj;
    private float vezetékesPercdíj = 5.51f * 1.27f;
    private float smsdíj;
    
    private float kapcsolásiDíj = 1.96f * 1.27f;
    private float vezetékesKapcsolásiDíj = 5.51f * 1.57f;

    public Perc(float havidíj, float lebeszélhető, float percdíj, float smsdíj) {
        this.havidíj = havidíj;
        this.lebeszélhető = lebeszélhető;
        this.percdíj = percdíj;
        this.smsdíj = smsdíj;
    }

    @Override
    public float számít(int belülPerc, int belülDarab, int kívülPerc, int kívülDarab, int vezetékesPerc, int vezetékesDarab, int smsDarab, int családiDarab) {
        float fizetni = havidíj + családiCsomag;
        
        // mobilhívások
        float mobilPerc = Math.max(belülPerc + kívülPerc - lebeszélhető, 0);
        fizetni += mobilPerc * percdíj 
                + (belülDarab + kívülDarab) * kapcsolásiDíj;
        
        // vezetékes hívások
        fizetni += vezetékesPerc * vezetékesPercdíj
                 + vezetékesDarab * vezetékesKapcsolásiDíj;
        
        // családi
        fizetni += családiDarab * kapcsolásiDíj;
        
        // sms
        fizetni += smsDarab * smsdíj;
        
        return fizetni;
    }
    
}
