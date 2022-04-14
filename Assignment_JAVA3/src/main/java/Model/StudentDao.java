/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Connect_Database;
import java.util.List;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Laptop
 */
public class StudentDao {
public ArrayList<Student> showData() {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = Connect_Database.getIntance();
            String sql = "Select * from students";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            ArrayList<Student> stdList = new ArrayList<>();
            while (rs.next()) {
                Student std = new Student();
                std.setMasv(rs.getString("masv"));
                std.setHoten(rs.getString("hoten"));
                std.setEmail(rs.getString("email"));
                std.setSodt(rs.getString("sdt"));
                std.setGioitinh(rs.getInt("gioitinh"));
                std.setDiachi(rs.getString("diachi"));
                Blob hinh = rs.getBlob("hinh");
                if (hinh != null) {
                    std.setHinh(hinh.getBytes(1, (int) hinh.length()));
                }

                stdList.add(std);
            }
            return stdList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (psmt != null) {

                try {
                    psmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return null;
    }
    public boolean insert(Student stu) throws Exception {
        String sql = "INSERT INTO [dbo].[Sinh Viên]([Masv],[hoten],[email],[sodt],[gioitinh],[diachi],[hinhanh])values(?,?,?,?,?,?,?)";
        try (
                 Connection con = Connect_Database.getIntance();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, stu.getMasv());
            pstmt.setString(2, stu.getHoten());
            pstmt.setString(3, stu.getEmail());
            pstmt.setString(4, stu.getSodt());
            pstmt.setInt(5, stu.getGioitinh());
            pstmt.setString(6, stu.getDiachi());
            if (stu.getHinh() != null) {
                Blob hinh = new SerialBlob(stu.getHinh());
                pstmt.setBlob(7, hinh);
            } else {
                Blob hinh = null;
                pstmt.setBlob(7, hinh);
            }

            int row = pstmt.executeUpdate();
//            pstmt.close();
//            con.close();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Student stu) throws Exception {
        String sql = " update [dbo].[Sinh Viên] set [hoten]=?,[email]=?,[sodt]=?,[gioitinh]=?,[diachi]=?,[hinhanh]=?"
                + " where [Masv] = ? ";
        try (
                 Connection con = Connect_Database.getIntance();  PreparedStatement pstmt = con.prepareStatement(sql);) {

            pstmt.setString(1, stu.getHoten());
            pstmt.setString(2, stu.getEmail());
            pstmt.setString(3, stu.getSodt());
            pstmt.setInt(4, stu.getGioitinh());
            pstmt.setString(5, stu.getDiachi());
            if (stu.getHinh() != null) {
                Blob hinh = new SerialBlob(stu.getHinh());
                pstmt.setBlob(6, hinh);
            } else {
                Blob hinh = null;
                pstmt.setBlob(6, hinh);
            }
            pstmt.setString(7, stu.getMasv());

            int row = pstmt.executeUpdate();
            pstmt.close();
            con.close();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public Student findbyID(String maSV) throws Exception {
//        String sql = "select * from [dbo].[Sinh Viên] where [Masv] = ? ";
//
//        try (
//                 Connection con = Connect_Database.getIntance();  PreparedStatement pstmt = con.prepareStatement(sql);) {
//            pstmt.setString(1, maSV);
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                Student stud = new Student();
//                stud.setMasv(rs.getString("maSV"));
//                stud.setHoten(rs.getString("hoten"));
//                stud.setEmail(rs.getString("email"));
//                stud.setSodt(rs.getString("sodt"));
//                stud.setGioitinh(rs.getInt("gioitinh"));
//                stud.setDiachi(rs.getString("diachi"));
//                Blob blob = rs.getBlob("hinhanh");
//                stud.setHinh(blob.getBytes(0, (int) blob.length()));
//
//                return stud;
//
//            }
//            return null;
//        }
//    }
    public Student findbyID(String maSV) throws Exception {
        String sql = "select * from [dbo].[Sinh Viên] where [Masv] = ?";

        try (
                 Connection con = Connect_Database.getIntance();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, maSV);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Student stud = createSinhvien(rs);

                return stud;

            }
            return null;
        }
    }

    private Student createSinhvien(ResultSet rs) throws SQLException {
        Student stud = new Student();
        stud.setMasv(rs.getString("maSV"));
        stud.setHoten(rs.getString("hoten"));
        stud.setEmail(rs.getString("email"));
        stud.setSodt(rs.getString("sodt"));
        stud.setGioitinh(rs.getInt("gioitinh"));
        stud.setDiachi(rs.getString("diachi"));
        Blob blob = rs.getBlob("hinhanh");
        if(blob != null)
        stud.setHinh(blob.getBytes(1, (int) blob.length()));
        return stud;
    }

    public List<Student> findALL() throws Exception {
        String sql = "select * from [dbo].[Sinh Viên]";

        try (
                 Connection con = Connect_Database.getIntance();  PreparedStatement pstmt = con.prepareStatement(sql);) {

            ResultSet rs = pstmt.executeQuery();
            List<Student> list = new ArrayList<>();
            while (rs.next()) {
                Student stud = createSinhvien(rs);

                list.add(stud);

            }
            return list;
        }
    }

    //đang làm tìm kiếm sinh viên
//    public List<Student> findbyALL() throws Exception {
//        String sql = "select * from sinhvien";
//
//        try (
//                 Connection con = Connect_Database.getIntance();  PreparedStatement pstmt = con.prepareStatement(sql);) {
//            try ( ResultSet rs = pstmt.executeQuery();) {
//                List<Student> list = new ArrayList<>();
//
//                while (rs.next()) {
//                    Student stud = new Student();
//                    stud.setMasv(rs.getString("maSV"));
//                    stud.setHoten(rs.getString("hoten"));
//                    stud.setEmail(rs.getString("email"));
//                    stud.setSodt(rs.getString("sodt"));
//                    stud.setGioitinh(rs.getInt("gioitinh"));
//                    stud.setDiachi(rs.getString("diachi"));
//                    Blob blob = rs.getBlob("hinh");
//                    stud.setHinh(blob.getBytes(0, (int) blob.length()));
//                    list.
//                }
//
//            }
//
//        }
//    }
    public boolean delete(String masv) throws Exception {
        String sql = "delete from [dbo].[Sinh Viên]" + "where [Masv] = ?";
        try (
                 Connection con = Connect_Database.getIntance();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, masv);
            return pstmt.executeUpdate() > 0;
        }
    }

    private ArrayList<Student> list = new ArrayList<>();
    private int currenIndext = -1;

    public void fist() {
        if (currenIndext > 0) {
            currenIndext = 0;
        }
    }

    public void last() {
        currenIndext = list.size() - 1;

    }

    public void next() {
        if (currenIndext < list.size() - 1) {
            currenIndext++;
        }
    }

    public void previous() {
        if (currenIndext > 0) {
            currenIndext--;
        }

    }

    public int getCurenIndextSinhVien() {
        return currenIndext;
    }

    public Student getCurenSinhVien() {
        if (list.size() == 0) {
            return null;
        }
        return list.get(currenIndext);

    }

    public Student findALL(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

  

}
