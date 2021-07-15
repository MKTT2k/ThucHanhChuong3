package Bai2;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class NhanVien_DbIO {

    public NhanVien_DbIO() {
    }
    
    public void getAll() {
        System.out.println("========================================");
        System.out.println("DANH SÁCH NHÂN VIÊN: ");
        Statement statement = null;
        List<String> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");
            String sql = "select * from NHANVIEN";
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                list.add(rs.getString("MaNV"));
                list.add(rs.getString("HoTen"));
                list.add(rs.getString("NgaySinh"));
                list.add(rs.getString("DiaChi"));
                list.add(rs.getString("GioiTinh"));
                list.add("\n");
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi"+ex.toString());
        };
        System.out.println(list.toString());
    }
    
    public boolean insert() {
        System.out.println("THÊM NHÂN VIÊN: ");
        String manv, ten, dc, ns, gt;
        Scanner sc= new Scanner(System.in);
        System.out.println("Nhập mã NV: ");
        manv= sc.nextLine();
        System.out.println("Nhập tên NV: ");
        ten= sc.nextLine();
        System.out.println("Nhập ngày sinh");
        ns= sc.nextLine();
        System.out.println("Nhập địa chỉ NV: ");
        dc= sc.nextLine();
        System.out.println("Nhập giới tính NV: ");
        gt= sc.nextLine();
        
        Connection conn = null;
        //get data from database
        PreparedStatement statement = null;
        try {
            //get Connect
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");

            String sql = "insert into NHANVIEN values (?,?,?,?,?)";
            statement = conn.prepareCall(sql);

            statement.setString(1, manv);
            statement.setString(2, ten);
            statement.setString(3, ns);
            statement.setString(4, dc);
            statement.setString(5, gt);

            statement.execute();
            return true;
        }catch (Exception e){
            System.out.println("Lỗi"+e.toString());
        }
        return false;
    }
    
    public boolean delete() {
        System.out.println("XÓA NHÂN VIÊN: ");
        Scanner sc= new Scanner(System.in);
        String manv;
        System.out.println("Nhap mã NV cần xóa: ");
        manv= sc.nextLine();
        Connection connection = null;
        //get data from database
        PreparedStatement statement = null;
        try {
            //get Connect
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");

            String sql = "DELETE FROM NHANVIEN WHERE MaNV = ?;";
            statement = connection.prepareCall(sql);

            statement.setString(1, manv);

            statement.execute();
            return true;
        }catch (Exception e){
            System.out.println("Lỗi"+e.toString());
        }
        return false;
    }
    
    public boolean update() {
        System.out.println("CẬP NHẬT NHÂN VIÊN: ");
        String manv, ten, dc, ns, gt;
        Scanner sc= new Scanner(System.in);
        System.out.println("Nhập mã NV: ");
        manv= sc.nextLine();
        System.out.println("Nhập tên NV mới:");
        ten= sc.nextLine();
        System.out.println("Nhập ngày sinh mới: ");
        ns= sc.nextLine();
        System.out.println("Nhập địa chỉ NV mới: ");
        dc= sc.nextLine();
        System.out.println("Nhập giớitinhs NV mới: ");
        gt= sc.nextLine();
        Connection connection = null;
        //get data from database
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");
            String sql = "UPDATE NHANVIEN SET HoTen = ?, NgaySinh = ?, DiaChi = ?, GioiTinh = ? WHERE Manv = ?;";
            statement = connection.prepareCall(sql);
            statement.setString(1, ten);
            statement.setString(2, ns);
            statement.setString(3, dc);
            statement.setString(4, gt);
            statement.setString(5, manv);
            statement.execute();
            return true;
        }catch (Exception e){
            System.out.println("Lỗi"+e.toString());
        }
        return false;
    }
}
