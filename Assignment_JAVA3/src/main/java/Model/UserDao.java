/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Connect_Database;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Laptop
 */
public class UserDao {
      public User checkLogin(String username, String password){
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs =null;
        try {
            conn =Connect_Database.getIntance();
            String sql ="select * from USERS"+" where tendangnhap =? and matkhau =?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, username);
            psmt.setString(2, password);
            rs = psmt.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setUsename(rs.getString("tendangnhap"));
                user.setPassword(rs.getString("matkhau"));
                user.setRole(rs.getString("vaitro"));
                Share.User = user;
                return user;
            }
        } catch (Exception e) {
        }
        finally {
            if (rs != null) {
                
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            if (psmt != null) {
                
                try {
                    psmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return null;
    }
    
    public User ForgottenPassword(String username, String email){
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs =null;
        try {
            conn =Connect_Database.getIntance();
            String sql ="select matkhau from USERS where  tendangnhap=? and email=?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, username);
            psmt.setString(2, email);
            rs = psmt.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setPassword(rs.getString("matkhau"));
                return user;
            }
        } catch (Exception e) {
        }
        finally {
            if (rs != null) {
                
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            if (psmt != null) {
                
                try {
                    psmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return null;
    }
    
    public boolean updatePassword(String userName, String passWord){
        Connection conn = null;
        PreparedStatement psmt =null;
        try {
            conn =Connect_Database.getIntance();
            String sql ="update [dbo].[USERS] set matkhau=? where tendangnhap =?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, userName);
            psmt.setString(2, passWord);
            psmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }finally{
            if(psmt!=null){
                try {
                    psmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
