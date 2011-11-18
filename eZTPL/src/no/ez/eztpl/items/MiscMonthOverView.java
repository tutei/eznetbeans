package no.ez.eztpl.items;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;

public class MiscMonthOverView implements ActiveEditorDrop {

    public boolean handleTransfer(JTextComponent targetComponent) {

        String body = "input|month_overview( field, "+
                      "today_timestamp, "+
                      "hash( 'current',       current_timestamp,"+
                      "      'day_class',     day_class"+
                      "      'current_class', current_class,"+
                      "      'link',          link,"+
                      "      'month_link',    month_link,"+
                      "      'year_link',     year_link, "+
                      "      'day_link',      day_link,"+
                      "      'next',          hash( 'link', next_link ),"+
                      "      'previous',      hash( 'link', previous_link ) ) )";
        try {
            JavaSourceFilePaletteUtilities.insert(body, targetComponent);
        } catch (BadLocationException ble) {
            return false;
        }

        return true;

    }
}

