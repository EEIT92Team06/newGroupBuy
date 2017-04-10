package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "UploadServlet", urlPatterns = {"/upload.do"})
public class UploadServlet extends HttpServlet {
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp)
           throws ServletException, IOException {
       Part filePart1 = req.getPart("photo");
//       String header = filePart1.getHeader("Content-Disposition");
//       String filename = header.substring(
//               header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
//       
//       InputStream in = filePart1.getInputStream();
//       
//       OutputStream out = new FileOutputStream("c:/workspace/" + filename);
//       byte[] buffer = new byte[1024];
//       int length = -1;
//       while ((length = in.read(buffer)) != -1) {
//           out.write(buffer, 0, length);
//       }
//       in.close();
//       out.close();
   }
}