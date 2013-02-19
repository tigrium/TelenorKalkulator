/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telenorkalkuláció.csomagok;

/**
 *
 * @author Kata
 */
public class SmartTarifa implements Tarifacsomag {
    private float családiCsomag = 1099;
    private float havidíj;
    
    float lebeszélhetőBelül;
    float lebeszélhetőKívül;
    
    private float percdíjLebeszélhetőBelül;
    private float percdíjLebeszélhetőKívül;
    private float percdíj;
    private float vezetékesPercdíj = 5.51f * 1.27f;
    private float smsdíj;
    
    private float kapcsolásiDíj = 1.96f * 1.27f;
    private float vezetékesKapcsolásiDíj = 5.51f * 1.57f;

    public SmartTarifa(float havidíj, float percdíjLebeszélhetőBelül, float percdíjLebeszélhetőKívül, 
            float percdíj, float smsdíj) {
        this.havidíj = havidíj;
        this.percdíjLebeszélhetőBelül = percdíjLebeszélhetőBelül;
        this.percdíjLebeszélhetőKívül = percdíjLebeszélhetőKívül;
        this.percdíj = percdíj;
        this.smsdíj = smsdíj;
        
        lebeszélhetőBelül = havidíj * 0.5f;
        lebeszélhetőKívül = havidíj * 0.5f;
    }

    @Override
    public float számít(int belülPerc, int belülDarab, int kívülPerc, int kívülDarab, int vezetékesPerc, int vezetékesDarab, int smsDarab, int családiDarab) {
        float fizetni = havidíj + családiCsomag;
        
        // hálózaton belül
        float belülLebeszélhetőPerc = Math.min(lebeszélhetőBelül / percdíjLebeszélhetőBelül, belülPerc);
        float belülTovábbiPerc = Math.max(belülPerc - belülLebeszélhetőPerc, 0);
        
        fizetni += belülTovábbiPerc * percdíj
                 + belülDarab * kapcsolásiDíj;
        
        // hálózaton kívül
        float kívülLebeszélhetőPerc = Math.min(lebeszélhetőKívül / percdíjLebeszélhetőKívül, kívülPerc);
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
