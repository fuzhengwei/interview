/*
 * SingleThread.java
 *
 * Created on 2006��8��22��, ����1:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.itstack.interview;

/**
 * this is Demo C, single thread
 * @author Li Ling
 */
public class SingleThread {
    
    /** Creates a new instance of SingleThread */
    public SingleThread() {
    }
    
    public static void someBusiness()
    {
        try{
        Thread.sleep(1000);
        
        //probe user info here
        IAfterAdvice probeC=new AfterAdvice(Storage.getC(),Probe.getC());
        probeC.outCallProbe();
        
        }
        catch(Exception e)
        {}
    }
    
}
