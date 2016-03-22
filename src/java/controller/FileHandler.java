package controller;

import dao.DBHelper;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;





@MultipartConfig
public class FileHandler extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection conn = null;
        
        try {
            DBHelper.loadDriver();
            conn = DBHelper.connect2DB();
            
            final Part filePart = request.getPart("file");
         
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO ImageTest (Image) VALUES (?)");
            
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
