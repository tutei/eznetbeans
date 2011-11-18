package no.ez.eztpl.items;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;

public class MiscMimeTypeIcon implements ActiveEditorDrop {

    public boolean handleTransfer(JTextComponent targetComponent) {

        String body = "input|mimetype_icon( [ size, [, alt_text [, return_type ] ] ] )";
        try {
            JavaSourceFilePaletteUtilities.insert(body, targetComponent);
        } catch (BadLocationException ble) {
            return false;
        }

        return true;

    }
}

