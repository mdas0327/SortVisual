/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortvisual;

/**
 *
 * @author mohammeddas
 */
public interface IRefresher {
    public void Repaint(DataSet dataset);
    public void RepaintAsyncAndWait(DataSet dataset);
}
