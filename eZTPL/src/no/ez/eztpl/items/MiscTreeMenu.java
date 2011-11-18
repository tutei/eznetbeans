package no.ez.eztpl.items;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;

public class MiscTreeMenu implements ActiveEditorDrop {

    public boolean handleTransfer(JTextComponent targetComponent) {

        String body = "treemenu( path "+
          "[, node_id            ]"+
          "[, class_filter       ]"+
          "[, depth_skip         ]"+
          "[, max_level          ]"+
          "[, is_selected_method ]"+
          "[, indentation_level  ]"+
          "[, language           ] )";
        try {
            JavaSourceFilePaletteUtilities.insert(body, targetComponent);
        } catch (BadLocationException ble) {
            return false;
        }

        return true;

    }
}

