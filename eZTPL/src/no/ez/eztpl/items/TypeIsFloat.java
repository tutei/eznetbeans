package no.ez.eztpl.items;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;

public class TypeIsFloat implements ActiveEditorDrop {

    public boolean handleTransfer(JTextComponent targetComponent) {

        String body = "input|is_float( target )";
        try {
            JavaSourceFilePaletteUtilities.insert(body, targetComponent);
        } catch (BadLocationException ble) {
            return false;
        }

        return true;

    }
}

