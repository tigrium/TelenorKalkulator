/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telenorkalkuláció.csomagok;

/**
 *
 * @author Család
 */
public interface Tarifacsomag {
    
    public float számít(int belülPerc, int belülDarab, 
            int kívülPerc, int kívülDarab, int vezetékesPerc, int vezetékesDarab, 
            int smsDarab);
    
}
