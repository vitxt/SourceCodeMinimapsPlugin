package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.TextRange;
import dto.ImageDTO;
import org.jetbrains.annotations.NotNull;
import requests.PostImageRequest;
import services.ImageService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class SendAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        if (editor == null) {
            Messages.showWarningDialog("Nenhum editor de texto aberto.", "Aviso");
            return;
        }

        Document document = editor.getDocument();

        try {
            documentController(project.getName(), document);
        } catch (Exception ex) {
            Messages.showWarningDialog("Erro!Tente novamente", "Aviso");
        }
    }

    public void documentController ( String projectName,Document document ) throws Exception {
        int lines  = document.getLineCount();
        if (lines == 0) {
            Messages.showWarningDialog("O documento está vazio.", "Aviso");
            return;
        }
        int  startOffset = document.getLineStartOffset(0);
        int  endOffset = document.getLineEndOffset(lines -  1);
        if (lines > 127) {
            endOffset = document.getLineEndOffset(127);
        }

        String capturedText = document.getText(new TextRange(startOffset, endOffset));
        Messages.showInfoMessage("Linhas capturadas com sucesso!", "Sucesso");
        String image = ImageService.createImage(capturedText);
        PostImageRequest.uploadImage(new ImageDTO(projectName, image));
    }

}

