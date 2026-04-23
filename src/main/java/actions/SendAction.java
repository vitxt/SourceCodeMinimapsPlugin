package actions;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import dto.ImageDTO;
import messages.MyMessageBundle;
import org.jetbrains.annotations.NotNull;
import requests.PostImageRequest;
import services.ImageService;

public class SendAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        if (project == null || editor == null) {
            showNotification(project, MyMessageBundle.message("dialog.title.warning"), MyMessageBundle.message("error.no.editor"), NotificationType.WARNING);
            return;
        }

        Document document = editor.getDocument();
        int lines = document.getLineCount();

        if (lines == 0) {
            showNotification(project, MyMessageBundle.message("dialog.title.warning"), MyMessageBundle.message("error.empty.document"), NotificationType.WARNING);
            return;
        }

        int startOffset = document.getLineStartOffset(0);
        int endOffset = document.getLineEndOffset(lines - 1);
        if (lines > 127) {
            endOffset = document.getLineEndOffset(127);
        }

        final String capturedText = document.getText(new TextRange(startOffset, endOffset));

        ProgressManager.getInstance().run(new Task.Backgroundable(project, MyMessageBundle.message("action.send"), true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                try {
                    indicator.setText(MyMessageBundle.message("process.image"));
                    String image = ImageService.createImage(capturedText);

                    indicator.setText(MyMessageBundle.message("image.upload"));
                    PostImageRequest.uploadImage(new ImageDTO(project.getName(), image));

                    showNotification(project, MyMessageBundle.message("dialog.title.success"), MyMessageBundle.message("success.captured"), NotificationType.INFORMATION);

                } catch (Exception ex) {
                    showNotification(project, MyMessageBundle.message("dialog.title.warning"), MyMessageBundle.message("error.generic"), NotificationType.ERROR);
                }
            }
        });
    }

    /**
     * Método auxiliar para não repetir o código do NotificationGroupManager toda hora.
     */
    private void showNotification(Project project, String title, String content, NotificationType type) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("SourceCodeMinimaps.Notifications")
                .createNotification(title, content, type)
                .notify(project);
    }
}