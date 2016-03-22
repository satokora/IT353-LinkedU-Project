

import upload.*;
import dao.DBHelper;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;





@MultipartConfig
public class FileUploadHandler extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection conn = null;
        
        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT353_Fall14_MAGS";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String selectString;
            Statement stmt = DBConn.createStatement();
            
            final Part filePart = request.getPart("file");
         
            PreparedStatement ps = conn.prepareStatement(
                "Update LinkedU.StudentInfo SET (Image, photo_file) Where email='owasp@ilstu.edu'");
            
            System.out.println("Inserting Data...");
            
            // Binary Stream for file to write to database
            InputStream fin = filePart.getInputStream();  
            ps.setBinaryStream(1,fin,fin.available());  

            // Gets row count
            int i = ps.executeUpdate();
            
            System.out.println("Data Upload Complete");

        }
        catch(SQLException e){
        }
     
    }
   

}
