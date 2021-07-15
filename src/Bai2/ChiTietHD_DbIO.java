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


public class ChiTietHD_DbIO {
    public void getAll() {
        System.out.println("========================================");
        System.out.println("DANH SÁCH CHI TIẾT HÓA ĐƠN: ");
        Statement statement = null;
        List<String> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");
            String sql = "select * from CHITIETHOADON";
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                list.add(rs.getString("MaSP"));
                list.add(rs.getString("MaHD"));
                list.add(rs.getString("LoaiHoaDon"));
                list.add(rs.getString("SoLuong"));
                list.add(rs.getString("DonGiaBan"));
                list.add("\n");
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi"+ex.toString());
        };
        System.out.println(list.toString());
    }
    
    public boolean insert() {
        System.out.println("THÊM CHI TIẾT HÓA ĐƠN:");
        String mahd, masp, loai;
        int sl,dg;
        Scanner sc= new Scanner(System.in);
        System.out.println("Nhập mã hóa đơn: ");
        mahd= sc.nextLine();
        System.out.println("Nhập mã sản phẩm: ");
        masp= sc.nextLine();
        System.out.println("Nhập loại hóa đơn: ");
        loai= sc.nextLine();
        System.out.println("Nhập số lượng: ");
        sl= sc.nextInt();
        System.out.println("Nhập đơn giá: ");
        dg= sc.nextInt();
        
        Connection conn = null;
        //get data from database
        PreparedStatement statement = null;
        try {
            //get Connect
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");

            String sql = "insert into CHITIETHOADON values (?,?,?,?,?)";
            statement = conn.prepareCall(sql);

            statement.setString(1, mahd);
            statement.setString(2, masp);
            statement.setString(3, loai);
            statement.setInt(4, sl);
            statement.setInt(5, dg);

            statement.execute();
            return true;
        }catch (Exception e){
            System.out.println("Lỗi"+e.toString());
        }
        return false;
    }
    
    public boolean delete() {
        System.out.println("XÓA CHI TIẾT HÓA ĐƠN: ");
        Scanner sc= new Scanner(System.in);
        String mahd, masp;
        System.out.println("Nhap mã CTHD cần xóa: ");
        mahd= sc.nextLine();
        System.out.println("Nhap mã sản phẩm cần xóa: ");
        masp= sc.nextLine();
        Connection connection = null;
        //get data from database
        PreparedStatement statement = null;
        try {
            //get Connect
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");

            String sql = "DELETE FROM CHITIETHOADON WHERE MaHD = ? AND MaSP = ? ;";
            statement = connection.prepareCall(sql);

            statement.setString(1, mahd);
            statement.setString(2, masp);
            statement.execute();
            return true;
        }catch (Exception e){
            System.out.println("Lỗi"+e.toString());
        }
        return false;
    }
    
    public boolean update() {
        System.out.println("CẬP NHẬT CHI TIẾT HÓA ĐƠN: ");
        String mahd, masp, loai;
        int sl, dg;
        Scanner sc= new Scanner(System.in);
        System.out.println("Nhập mã hóa đơn: ");
        mahd= sc.nextLine();
        System.out.println("Nhập mã sản phẩm: ");
        masp= sc.nextLine();
        System.out.println("Nhập loại hóa đơn mới: ");
        loai= sc.nextLine();
        System.out.println("Nhập số lượng mới: ");
        sl= sc.nextInt();
        System.out.println("Nhập đơn giá mới: ");
        dg= sc.nextInt();
        Connection connection = null;
        //get data from database
        PreparedStatement statement = null;
        try {
            //get Connect
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");
            String sql = "UPDATE CHITIETHOADON LoaiHoaDon = ?, SoLuong = ?, DonGiaBan= ? WHERE MaHD = ? AND MaSP = ?;";
            statement = connection.prepareCall(sql);
            statement.setString(1, loai);
            statement.setInt(2, sl);
            statement.setInt(3, dg);
            statement.setString(4, mahd);
            statement.setString(5, masp);
            statement.execute();
            return true;
        }catch (Exception e){
            System.out.println("Lỗi"+e.toString());
        }
        return false;
    }
}