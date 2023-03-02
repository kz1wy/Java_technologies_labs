package servlet;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    private static final String FILE_DIRECTORY = "images";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the requested file name from the query string
        String fileName = request.getParameter("file");
        String speedParam = request.getParameter("speed");

        // If the file name is not specified, output error message
        if (fileName == null || fileName.equals("")) {
            response.setContentType("text/plain");
            response.getWriter().write("File not found, modify your link to download?file='yourfilename.abc'");
            return;
        }

        // Get the file path on the server
        String filePath = getServletContext().getRealPath(FILE_DIRECTORY + File.separator + fileName);

        // If the file doesn't exist, output error message
        File file = new File(filePath);
        if (!file.exists()) {
            response.setContentType("text/plain");
            response.getWriter().write("File not found, modify your link to download?file='yourfilename.abc'");
            return;
        }

        // Set content type and headers for the response
        String mimeType = getServletContext().getMimeType(filePath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
        response.setHeader(headerKey, headerValue);

        // Copy the file's bytes to the response output stream
        FileInputStream fis = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        OutputStream os = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int len = 0;
        int speed = 0;

        if (speedParam != null && !speedParam.isEmpty()) {
            speed = Integer.parseInt(speedParam);
        }

        if (speed > 0) {
            int sleepTime = 1000 / speed;

            while ((len = fis.read(buffer)) != -1) {
                out.write(buffer, 0, len);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            while ((len = fis.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        }

        fis.close();
        out.flush();
        out.close();
    }
}