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
    private float áfa = 1.27f;
    private float családiCsomag = 1099;
    
    private float havidíj;
    
    private float lebeszélhetőBelül = (float)(havidíj * 0.6);
    private float lebeszélhetőKívül = (float)(havidíj * 0.4);
    
    private float percdíjLebeszélhető;
    private float percdíj;
    private float vezetékesPercdíj = 5.51f;
    private float smsdíj;
    
    private float kapcsolásiDíj = 1.96f;
    private float vezetékesKapcsolásiDíj = 5.51f;

    public Klasszik(float havidíj, float percdíjLebeszélhető, float percdíj, float smsdíj) {
        this.havidíj = havidíj;
        this.percdíjLebeszélhető = percdíjLebeszélhető;
        this.percdíj = percdíj;
        this.smsdíj = smsdíj;
    }

    

    @Override
    public float számít(int belülPerc, int belülDarab, 
            int kívülPerc, int kívülDarab, int vezetékesPerc, int vezetékesDarab, 
            int smsDarab, int családiDarab) {
        
        float fizetni = havidíj + családiCsomag;
        
        float lebeszélhetőPerc, továbbiPerc;
        
        // hálózaton belül
        lebeszélhetőPerc = Math.min(lebeszélhetőBelül / percdíjLebeszélhető, belülPerc);
        továbbiPerc = Math.max(belülPerc - lebeszélhetőPerc, 0);
        fizetni += továbbiPerc * percdíj * áfa + belülDarab * kapcsolásiDíj * áfa;
        
        // hálózaton kívül
        lebeszélhetőPerc = Math.min(lebeszélhetőKívül / percdíjLebeszélhető, kívülPerc);
        továbbiPerc = Math.max(kívülPerc - lebeszélhetőPerc, 0);
        fizetni += továbbiPerc * percdíj * áfa + kívülDarab * kapcsolásiDíj * áfa;
        
        // vezetékes
        fizetni += vezetékesPerc * vezetékesPercdíj * áfa 
                + vezetékesDarab * vezetékesKapcsolásiDíj * áfa;
        
        // családi
        fizetni += családiDarab * kapcsolásiDíj * áfa;
        
        // sms
        fizetni += smsDarab * smsdíj * áfa;
        
        return fizetni;
    }
    
}
