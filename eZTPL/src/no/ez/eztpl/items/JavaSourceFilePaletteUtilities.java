/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.ez.eztpl.items;


import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.openide.text.IndentEngine;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;

/**
 *
 * @author gw152771
 */
public class JavaSourceFilePaletteUtilities {
    
    public static void insert(final String s, final JTextComponent target) throws BadLocationException {

        final StyledDocument doc = (StyledDocument) target.getDocument();
        if (doc == null) {
            return;
        }

        class InsertFormatedText implements Runnable {
            public void run() {
                try {
                    insertFormated(s, target, doc);
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        InsertFormatedText insert = new InsertFormatedText();

        //This starts the run() in the Runnable above:
        NbDocument.runAtomicAsUser(doc, insert);
        
    }
    
    private static int insertFormated(String s, JTextComponent target, Document doc) throws BadLocationException {

        int start = -1;
        
        try {
            
            //Find the location in the editor,
            //and if it is a selection, remove it,
            //to be replaced by the dropped item:
            Caret caret = target.getCaret();
            int p0 = Math.min(caret.getDot(), caret.getMark());
            int p1 = Math.max(caret.getDot(), caret.getMark());
            doc.remove(p0, p1 - p0);
        
            start = caret.getDot();
            
            //Insert the string in the document,
            //using the indentation engine
            //to create the correct indentation:
            IndentEngine engine = IndentEngine.find(doc);
            StringWriter textWriter = new StringWriter();
            Writer indentWriter = engine.createWriter(doc, start, textWriter);
            indentWriter.write(s);
            indentWriter.close();

            doc.insertString(start, textWriter.toString(), null);
            
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (BadLocationException ble) {
            Exceptions.printStackTrace(ble);
        }

        return start;
    }
    
}