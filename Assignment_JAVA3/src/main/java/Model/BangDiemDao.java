/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Connect_Database;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author Laptop
 */
public class BangDiemDao {

    public boolean insert(BangDiem bd) throws Exception {
        String sql = "INSERT INTO [dbo].[BangDiem]([masv],[tienganh],[tinhoc],[gdtc]) values(?,?,?,?)";
        try (
                 Connection con = Connect_Database.getIntance();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, bd.getMasv());
            pstmt.setFloat(2, bd.getTienganh());
            pstmt.setFloat(3, bd.getTinhoc());
            pstmt.setFloat(4, bd.getGdtc());

            int row = pstmt.executeUpdate();
//            pstmt.close();
//            con.close();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(BangDiem bd) throws Exception {
        String sql = " UPDATE [dbo].[BangDiem]"
                + " SET [tienganh] = ?,[tinhoc]=?,[gdtc]=? "
                + " WHERE [masv] = ?";
        try (
                 Connection con = Connect_Database.getIntance();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(4, bd.getMasv());
            pstmt.setFloat(1, bd.getTienganh());
            pstmt.setFloat(2, bd.getTinhoc());
            pstmt.setFloat(3, bd.getGdtc());

            int row = pstmt.executeUpdate();
//            pstmt.close();
//            con.close();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletebyMasv(String masv) throws Exception {
        String sql = " DELETE FROM  [dbo].[BangDiem]"
                + " WHERE [masv] = ?";
        try (
                 Connection con = Connect_Database.getIntance();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, masv);

            int row = pstmt.executeUpdate();
//            pstmt.close();
//            con.close();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public BangDiem fidnByMasv(String masv) throws Exception {
        String sql = " SELECT * FROM  [dbo].[BangDiem] WHERE [masv] = ?";
        try (
                 Connection con = Connect_Database.getIntance();  
                PreparedStatement pstmt = con.prepareStatement(sql);
                ){
            pstmt.setString(1, masv);

            try ( ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    BangDiem bd = new BangDiem();
                    bd.setMa(rs.getInt("ID"));
                    bd.setMasv(rs.getString("masv"));
                   
                    bd.setTienganh(rs.getFloat("Tienganh"));
                     bd.setTinhoc(rs.getFloat("tinhoc"));
                      bd.setGdtc(rs.getFloat("Gdtc"));
                   
                    return bd;
                }
                
            }
            return null;
        } 
        
    }
    
     public List<BangDiem> fidnAll() throws Exception {
        String sql = " SELETE * FROM  [dbo].[BangDiem] ";
        try (
                 Connection con = Connect_Database.getIntance();  
                PreparedStatement pstmt = con.prepareStatement(sql);
                ){

            try ( ResultSet rs = pstmt.executeQuery();) {
                List<BangDiem> list = new ArrayList<>();
                while (rs.next()) {
                    BangDiem bd = creatBangdiem(rs);
                   
                  list.add(bd);
                }
                return list;
                
            }
           
        } 
        
    }

    private BangDiem creatBangdiem(final ResultSet rs) throws SQLException {
        BangDiem bd = new BangDiem();
        bd.setMa(rs.getInt("ID"));
        bd.setMasv(rs.getString("masv"));
        bd.setTienganh(rs.getFloat("Tienganh"));
        bd.setTinhoc(rs.getFloat("Tinhoc"));
        bd.setGdtc(rs.getFloat("Gdtc"));
        return bd;
    }
     
     public List<BangDiem> fidnTop(int top) throws Exception {
        String sql = " select top " + top +" * , (tienganh + tinhoc + gdtc) / 3 as DTB" +
                     " from BangDiem order by DTB desc";
        try (
                 Connection con = Connect_Database.getIntance();  
                PreparedStatement pstmt = con.prepareStatement(sql);
                ){

            try ( ResultSet rs = pstmt.executeQuery();) {
                List<BangDiem> list = new ArrayList<>();
                while (rs.next()) {
                    BangDiem bd = creatBangdiem(rs);
                   
                  list.add(bd);
                }
                return list;
                
            }
           
        } 
        
    }
}
