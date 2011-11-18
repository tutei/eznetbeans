package no.ez.eztpl.items;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;

public class eZJSCoreEZCSS implements ActiveEditorDrop {

    public boolean handleTransfer(JTextComponent targetComponent) {

        String body = "ezcss( array|string $css_files"+
      "[, string $media='all'"+
      " [, string $type='text/css'"+
      "  [, string $rel='stylesheet'"+
      "   [, string $charset='utf-8'"+
      "    [, int $pack_level=3]]]]] )";
        try {
            JavaSourceFilePaletteUtilities.insert(body, targetComponent);
        } catch (BadLocationException ble) {
            return false;
        }

        return true;

    }
}

