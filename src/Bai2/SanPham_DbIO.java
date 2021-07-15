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


public class SanPham_DbIO {
    public void getAll() {
        System.out.println("========================================");
        System.out.println("DANH SÁCH SẢN PHẨM: ");
        Statement statement = null;
        List<String> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");
            String sql = "select * from SANPHAM";
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                list.add(rs.getString("MaSP"));
                list.add(rs.getString("TenSP"));
                list.add(rs.getString("DonViTinh"));
                list.add(rs.getString("Gia"));
                list.add("\n");
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi"+ex.toString());
        };
        System.out.println(list.toString());
    }
    
    public boolean insert() {
        System.out.println("THÊM SẢN PHẨM");
        String ma, ten, dv;
        int gia;
        Scanner sc= new Scanner(System.in);
        System.out.println("Nhập mã SP: ");
        ma= sc.nextLine();
        System.out.println("Nhập tên SP: ");
        ten= sc.nextLine();
        System.out.println("Nhập đơn vị tính");
        dv= sc.nextLine();
        System.out.println("Nhập Giá: ");
        gia= sc.nextInt();
        
        Connection conn = null;
        //get data from database
        PreparedStatement statement = null;
        try {
            //get Connect
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");

            String sql = "insert into SANPHAM values (?,?,?,?)";
            statement = conn.prepareCall(sql);

            statement.setString(1, ma);
            statement.setString(2, ten);
            statement.setString(3, dv);
            statement.setInt(4, gia);

            statement.execute();
            return true;
        }catch (Exception e){
            System.out.println("Lỗi"+e.toString());
        }
        return false;
    }
    
    public boolean delete() {
        System.out.println("XÓA SẢN PHẨM: ");
        Scanner sc= new Scanner(System.in);
        String ma;
        System.out.println("Nhap mã SP cần xóa: ");
        ma= sc.nextLine();
        Connection connection = null;
        //get data from database
        PreparedStatement statement = null;
        try {
            //get Connect
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");

            String sql = "DELETE FROM SANPHAM WHERE MaSP = ?;";
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
        System.out.println("CẬP NHẬT SẢN PHẨM: ");
        String ma, ten,dv;
        int gia;
        Scanner sc= new Scanner(System.in);
        System.out.println("Nhập mã SP: ");
        ma= sc.nextLine();
        System.out.println("Nhập tên SP mới:");
        ten= sc.nextLine();
        System.out.println("Nhập đơn vị mới: ");
        dv= sc.nextLine();
        System.out.println("Nhập giá mới: ");
        gia= sc.nextInt();
        Connection connection = null;
        //get data from database
        PreparedStatement statement = null;
        try {
            //get Connect
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLNV", "root", "123456");

            String sql = "UPDATE SANPHAM SET TenSP = ?, DonViTinh = ?, Gia = ? WHERE MaSP = ?;";
            statement = connection.prepareCall(sql);
            statement.setString(1, ten);
            statement.setString(2, dv);
            statement.setInt(3, gia);
            statement.setString(4, ma);
            statement.execute();
            return true;
        }catch (Exception e){
            System.out.println("Lỗi"+e.toString());
        }
        return false;
    }
}