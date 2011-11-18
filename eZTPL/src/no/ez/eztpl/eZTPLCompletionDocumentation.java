/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.ez.eztpl;

import java.net.URL;
import org.netbeans.spi.editor.completion.CompletionDocumentation;

/**
 *
 * @author thiago
 */
public class eZTPLCompletionDocumentation implements CompletionDocumentation {

    private eZTPLCompletionItem item;

    public eZTPLCompletionDocumentation(eZTPLCompletionItem item) {
        this.item = item;
    }

    @Override
    public String getText() {
        return "Information about " + item.getText();
    }

    @Override
    public URL getURL() {
        return null;
    }

    public CompletionDocumentation resolveLink(String string) {
        return null;
    }

    @Override
    public javax.swing.Action getGotoSourceAction() {
        return null;
    }





}
