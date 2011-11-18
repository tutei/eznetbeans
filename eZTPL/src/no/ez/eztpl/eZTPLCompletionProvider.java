/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.ez.eztpl;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;

import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;

public class eZTPLCompletionProvider implements CompletionProvider {

    public eZTPLCompletionProvider() {
    }

    @Override
    public CompletionTask createTask(int i, JTextComponent jTextComponent) {

        if (i != CompletionProvider.COMPLETION_QUERY_TYPE) {
            return null;
        }

        return new AsyncCompletionTask(new AsyncCompletionQuery() {

            @Override
            protected void query(CompletionResultSet completionResultSet, Document document, int caretOffset) {
                String filter = null;
                int startOffset = caretOffset - 1;



                try {
                    final StyledDocument bDoc = (StyledDocument) document;
                    final int lineStartOffset = getRowFirstNonWhite(bDoc, caretOffset);
                    final char[] line = bDoc.getText(lineStartOffset, caretOffset - lineStartOffset).toCharArray();
                    final int whiteOffset = indexOfWhite(line);
                    filter = new String(line, whiteOffset + 1, line.length - whiteOffset - 1);
                    if (whiteOffset > 0) {
                        startOffset = lineStartOffset + whiteOffset + 1;
                    } else {
                        startOffset = lineStartOffset;
                    }
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }

                //Iterate through the available locales
                //and assign each country display name
                //to a CompletionResultSet:
                FileObject[] tags = FileUtil.getConfigFile("eZTags").getChildren();

                System.out.println(tags.toString());
                for (int i=0;i<tags.length;i++) {
                    String tag=tags[i].getName();
                    //Here we test whether the country starts with the filter defined above:
                    if (!tag.equals("") && tag.startsWith(filter)) {
                        //Here we include the start offset, so that we'll be able to figure out
                        //the number of characters that we'll need to remove:
                        completionResultSet.addItem(new eZTPLCompletionItem(tag, startOffset, caretOffset));
                    }

                }

                completionResultSet.finish();
            }
        }, jTextComponent);

    }

    static int getRowFirstNonWhite(StyledDocument doc, int offset)
            throws BadLocationException {
        Element lineElement = doc.getParagraphElement(offset);
        int start = lineElement.getStartOffset();
        while (start + 1 < lineElement.getEndOffset()) {
            try {
                if (doc.getText(start, 1).charAt(0) != ' ' && doc.getText(start, 1).charAt(0) != '{' && doc.getText(start, 1).charAt(0) != '.') {
                    break;
                }
            } catch (BadLocationException ex) {
                throw (BadLocationException) new BadLocationException(
                        "calling getText(" + start + ", " + (start + 1)
                        + ") on doc of length: " + doc.getLength(), start).initCause(ex);
            }
            start++;
        }
        return start;
    }

    static int indexOfWhite(char[] line){
    int i = line.length;
    while(--i > -1){
        final char c = line[i];
        if(Character.isWhitespace(c) || c=='{' || c=='.'){
            return i;
        }
    }
    return -1;
}


    @Override
    public int getAutoQueryTypes(JTextComponent jTextComponent, String string) {
        return 0;
    }
}
