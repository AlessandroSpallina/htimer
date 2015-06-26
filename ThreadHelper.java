/****************************************************************************
* Copyright © 2015 Alessandro Spallina
* email: alessandrospallina1@gmail.com
* website: aleksnote.altervista.org
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
****************************************************************************/ 

package timerspegnimento;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author aleks
 */
public class ThreadHelper extends Thread {
    private JTextArea tarea; 
    private Runtime rt;
    private int minutes;
    
    
    public ThreadHelper(Runtime rt, int minutes, JTextArea tarea) {
        this.rt=rt;
        this.minutes=minutes;
        this.tarea=tarea;
    }
    
    
    @Override 
    public void run() {
        tarea.append("@ Spegnimento tra "+Integer.toString(minutes)+" minuti.\n");
        try {
            Thread.sleep(minutes*60*1000);
            String command[]={"/sbin/shutdown","-h","now"};
            System.out.println("spento");
            rt.exec(command);
        } catch (InterruptedException ex) {
            tarea.append("@ Schedulazione interrotta con successo\n");
        } catch (IOException ex) {
            tarea.append("@ ERRORE: shutdown non trovato\n");
        }
    }
}
