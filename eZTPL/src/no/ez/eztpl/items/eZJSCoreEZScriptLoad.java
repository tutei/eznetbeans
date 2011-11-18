package no.ez.eztpl.items;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;

public class eZJSCoreEZScriptLoad implements ActiveEditorDrop {

    public boolean handleTransfer(JTextComponent targetComponent) {

        String body = "ezscript_load( [ array|string $scripts"+
                "[, string $type='text/javascript'"+
                " [, string $language='javascript'"+
                "  [, string $charset='utf-8'"+
                "   [, int $pack_level=2]]]]] )";
        try {
            JavaSourceFilePaletteUtilities.insert(body, targetComponent);
        } catch (BadLocationException ble) {
            return false;
        }

        return true;

    }
}

