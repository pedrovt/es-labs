/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timer;


import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.Date;


@Startup
@Singleton
public class AutomaticTimerBean {
    
    static int counter=0;
    
    String quote="none";
    
    public void setQuote( String qu)
    {
        quote=qu;
    }
    public String getQuote()
    {
        return quote;
    } 
    public int getCounter()
    {
        return AutomaticTimerBean.counter;
    }
    
    /**
     * This method will be called every 10 second and will fire an @TimerEvent
     */
    @Schedule(hour = "*", minute = "*", second = "*/10", info = "Every 10 second timer")
    public void printDate() {         
        AutomaticTimerBean.counter=AutomaticTimerBean.counter+1;
 
    }

}
