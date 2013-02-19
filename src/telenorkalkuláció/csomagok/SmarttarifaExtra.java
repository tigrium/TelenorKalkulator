/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telenorkalkuláció.csomagok;

/**
 *
 * @author Kata
 */
public class SmarttarifaExtra implements Tarifacsomag {
    private float családiCsomag = 1099;
    private float havidíj;
    
    float lebeszélhetőPercBelül;
    float lebeszélhetőPercKívül;
    
    private float percdíj;
    private float vezetékesPercdíj = 5.51f * 1.27f;
    private float smsdíj;
    
    private float kapcsolásiDíj = 1.96f * 1.27f;
    private float vezetékesKapcsolásiDíj = 5.51f * 1.57f;

    public SmarttarifaExtra(float havidíj, float lebeszélhetőPercBelül, float lebeszélhetőPercKívül, 
            float percdíj, float smsdíj) {
        this.havidíj = havidíj;
        this.lebeszélhetőPercBelül = lebeszélhetőPercBelül;
        this.lebeszélhetőPercKívül = lebeszélhetőPercKívül;
        this.percdíj = percdíj;
        this.smsdíj = smsdíj;
    }
    
    

    @Override
    public float számít(int belülPerc, int belülDarab, int kívülPerc, int kívülDarab, 
            int vezetékesPerc, int vezetékesDarab, int smsDarab, int családiDarab) {
        float fizetni = havidíj + családiCsomag;
        
        // hálózaton belül
        float belülTovábbiPerc = Math.max(belülPerc - lebeszélhetőPercBelül, 0);
        
        fizetni += belülTovábbiPerc * percdíj
                 + belülDarab * kapcsolásiDíj;
        
        // hálózaton kívül
        float kívülTovábbiPerc = Math.max(kívülPerc - lebeszélhetőPercKívül, 0);
        
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
