/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.ez.eztpl.items;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import no.ez.eztpl.items.resources.ItemCustomizer;
import org.openide.text.ActiveEditorDrop;

/**
 *
 * @author gw152771
 */
public class Item implements ActiveEditorDrop {
    
    private String comment = "";
    
    public Item() {
    }
    
    private String createBody() {
        //String comment = getComment();
        StringBuffer buffer = new StringBuffer();
        buffer.append( "/** " );
        buffer.append( comment );
        buffer.append( " */\n" );
        return buffer.toString();
    }
    
    public boolean handleTransfer(JTextComponent targetComponent) {
        
        //ItemCustomizer c = new ItemCustomizer(this, targetComponent);
        //boolean accept = c.showDialog();
        boolean accept=true;
        if (accept) {
            String body = createBody();
            try {
                JavaSourceFilePaletteUtilities.insert(body, targetComponent);
            } catch (BadLocationException ble) {
                accept = false;
            }
        }
        return accept;
        
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
