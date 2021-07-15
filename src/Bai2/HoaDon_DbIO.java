package Bai2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class HoaDon_DbIO {
    public void getAll() {
        System.out.println("========================================");
        System.out.println("DANH SÁCH HÓA ĐƠN: ");
        Statement statement = null;
        List<String> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");
            String sql = "select * from HOADON";
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                list.add(rs.getString("MaHD"));
                list.add(rs.getString("MaNV"));
                list.add(rs.getString("LoaiHoaDon"));
                list.add(rs.getString("NgayLap"));
                list.add(rs.getString("NgayGiao"));
                list.add(rs.getString("DienGiai"));
                list.add("\n");
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi"+ex.toString());
        };
        System.out.println(list.toString());
    }
    
    public boolean insert() {
        System.out.println("THÊM HÓA ĐƠN:");
        String mahd, manv, loai, ngayLap, ngayGiao, dienGiai;
        Scanner sc= new Scanner(System.in);
        System.out.println("Nhập mã hóa đơn: ");
        mahd= sc.nextLine();
        System.out.println("Nhập mã nhân viên: ");
        manv= sc.nextLine();
        System.out.println("Nhập loại hóa đơn: ");
        loai= sc.nextLine();
        System.out.println("Nhập ngày lập: ");
        ngayLap= sc.nextLine();
        System.out.println("Nhập ngày giao: ");
        ngayGiao= sc.nextLine();
        System.out.println("Nhập diễn giảt: ");
        dienGiai= sc.nextLine();
        
        Connection conn = null;
        //get data from database
        PreparedStatement statement = null;
        try {
            //get Connect
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");

            String sql = "insert into HOADON values (?,?,?,?,?,?)";
            statement = conn.prepareCall(sql);

            statement.setString(1, mahd);
            statement.setString(2, manv);
            statement.setString(3, loai);
            statement.setString(4, ngayLap);
            statement.setString(5, ngayGiao);
            statement.setString(6, dienGiai);

            statement.execute();
            return true;
        }catch (Exception e){
            System.out.println("Lỗi"+e.toString());
        }
        return false;
    }
    
    public boolean delete() {
        System.out.println("XÓA HÓA ĐƠN: ");
        Scanner sc= new Scanner(System.in);
        String ma;
        System.out.println("Nhap mã HD cần xóa: ");
        ma= sc.nextLine();
        Connection connection = null;
        //get data from database
        PreparedStatement statement = null;
        try {
            //get Connect
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");

            String sql = "DELETE FROM HOADON WHERE MaHD = ?;";
            statement = connection.prepareCall(sql);

            statement.setString(1, ma);
            statement.execute();
            return true;
        }catch (Exception e){
            System.out.println("Lỗi"+e.toString());
        }
        return false;
    }
    
    public boolean update() {
        System.out.println("CẬP NHẬT HÓA ĐƠN: ");
        String mahd, manv, loai, ngayLap, ngayGiao, dienGiai;
        Scanner sc= new Scanner(System.in);
        System.out.println("Nhập mã hóa đơn: ");
        mahd= sc.nextLine();
        System.out.println("Nhập mã nhân viên mới: ");
        manv= sc.nextLine();
        System.out.println("Nhập loại hóa đơn mới: ");
        loai= sc.nextLine();
        System.out.println("Nhập ngày lập mới: ");
        ngayLap= sc.nextLine();
        System.out.println("Nhập ngày giao mới: ");
        ngayGiao= sc.nextLine();
        System.out.println("Nhập diễn giảt mới: ");
        dienGiai= sc.nextLine();
        
        Connection connection = null;
        //get data from database
        PreparedStatement statement = null;
        try {
            //get Connect
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");
            String sql = "UPDATE HOADON SET MaNV = ?, LoaiHoaDon = ?, NgayLap = ?, NgayGiao= ?, DienGiai=? WHERE MaHD = ?;";
            
            statement = connection.prepareCall(sql);
            statement.setString(1, manv);
            statement.setString(2, loai);
            statement.setString(3, ngayLap);
            statement.setString(4, ngayGiao);
            statement.setString(5, dienGiai);
            statement.setString(6, mahd);
            
            statement.execute();
            return true;
        }catch (Exception e){
            System.out.println("Lỗi"+e.toString());
        }
        return false;
    }
}