/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telenorkalkuláció.csomagok;

/**
 *
 * @author Család
 */
public class Klasszik implements Tarifacsomag {
    private float családiCsomag = 1099;
    private float havidíj;
    
    private float lebeszélhetőBelül;
    private float lebeszélhetőKívül;
    
    private float percdíjLebeszélhető;
    private float percdíj;
    private float vezetékesPercdíj = 5.51f * 1.27f;
    private float smsdíj;
    
    private float kapcsolásiDíj = 1.96f * 1.27f;
    private float vezetékesKapcsolásiDíj = 5.51f * 1.57f;

    public Klasszik(float havidíj, float percdíjLebeszélhető, float percdíj, float smsdíj) {
        this.havidíj = havidíj;
        this.percdíjLebeszélhető = percdíjLebeszélhető;
        this.percdíj = percdíj;
        this.smsdíj = smsdíj;
        
        lebeszélhetőBelül = (float)(havidíj * 0.6);
        lebeszélhetőKívül = (float)(havidíj * 0.4);
    }

    

    @Override
    public float számít(int belülPerc, int belülDarab, 
            int kívülPerc, int kívülDarab, int vezetékesPerc, int vezetékesDarab, 
            int smsDarab, int családiDarab) {
        
        float fizetni = havidíj + családiCsomag;
        
        // hálózaton belül
        float belülLebeszélhetőPerc = Math.min(lebeszélhetőBelül / percdíjLebeszélhető, belülPerc);
        float belülTovábbiPerc = Math.max(belülPerc - belülLebeszélhetőPerc, 0);
        
        fizetni += belülTovábbiPerc * percdíj
                 + belülDarab * kapcsolásiDíj;
        
        // hálózaton kívül
        float kívülLebeszélhetőPerc = Math.min(lebeszélhetőKívül / percdíjLebeszélhető, kívülPerc);
        float kívülTovábbiPerc = Math.max(kívülPerc - kívülLebeszélhetőPerc, 0);
        
        fizetni += kívülTovábbiPerc * percdíj 
                 + kívülDarab * kapcsolásiDíj;
        
        // vezetékes
        fizetni += vezetékesPerc * vezetékesPercdíj
                + vezetékesDarab * vezetékesKapcsolásiDíj;
        
        
        // családi
        fizetni += családiDarab * kapcsolásiDíj;
        
        // sms
        fizetni += smsDarab * smsdíj;
        
        return fizetni;
    }
    
}
