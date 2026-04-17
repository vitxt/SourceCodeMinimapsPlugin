package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;

public class SendAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor != null) {
            Messages.showWarningDialog("Nenhum editor de texto aberto.", "Aviso");
            return;
        }
        Document document = editor.getDocument();
        if (document.getLineCount() == 0) {
            Messages.showWarningDialog("O documento está vazio.", "Aviso");
            return;
        }
        int startOffset = document.getLineStartOffset(0);
        int endOffset = document.getLineEndOffset(127);

        String capturedText = document.getText(new TextRange(startOffset, endOffset));

        Messages.showInfoMessage("Linhas capturadas com sucesso!", "Sucesso");
    }
}

