package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
@MultipartConfig
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";
    private static final List<String> EXTENSIONS = Arrays.asList("txt", "doc", "docx", "png", "pdf", "rar", "zip", "rar", "jpg");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String fileName = request.getParameter("fileName");
        Part filePart = request.getPart("file");
        String fileExtension = getFileExtension(filePart);

        if (!isValidExtension(fileExtension)) {
            response.getWriter().println("Không hỗ trợ loại file này!");
            return;
        }

        File uploadDir = new File(getServletContext().getRealPath("") + File.separator + UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        File existingFile = new File(uploadDir.getAbsolutePath() + File.separator + fileName);
        boolean fileExists = existingFile.exists();
        boolean overrideIfExists = request.getParameter("overrideIfExists") != null;

        if (fileExists && !overrideIfExists) {
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, existingFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            String fileURL = request.getContextPath() + "/" + UPLOAD_DIR + "/" + existingFile.getName();
            response.setContentType("text/html");
            response.getWriter().println("<html><body>");
            response.getWriter().println("File uploaded. Click <a href=\"" + fileURL + "\">here</a> to view.");
            response.getWriter().println("</body></html>");
            return;
        }

        String filePath = existingFile.getAbsolutePath();
        filePart.write(filePath);

        if (fileExists && overrideIfExists) {
            response.getWriter().println("Ghi đè thành công");
        } else {
            response.getWriter().println("File bị trùng");
        }
    }

    private String getFileExtension(Part part) {
        String[] parts = part.getSubmittedFileName().split("\\.");
        return parts[parts.length - 1];
    }

    private boolean isValidExtension(String extension) {
        return EXTENSIONS.contains(extension);
    }

}
