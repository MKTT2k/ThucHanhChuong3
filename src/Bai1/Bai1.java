package Bai1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bai1 {

    public static void nhapLSP(){
        
        Scanner sc= new Scanner(System.in);
        String maLoai, tenLoai;
        System.out.println("Nhap ma loai");
        maLoai= sc.nextLine();
        System.out.println("Nhap ten loai");
        tenLoai= sc.nextLine();
        
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QUANLYSANPHAM", "root", "123456");
            String sql = "insert into LoaiSanPham (MaLoaiSP, TenLoaiSP) values(?,?)";
            
            statement = conn.prepareCall(sql);
            statement.setString(1, maLoai);
            statement.setString(2, tenLoai);
           
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Lỗi "+e.toString());
        }
    }
    public static void main(String args[]) {
        Connection conn = null;
        Statement statement = null;
        List<String> list = new ArrayList<>();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QUANLYSANPHAM", "root", "123456");
            String sql = "select MaSP, TenSP, TenLoaiSP from SanPham inner join LoaiSanPham ON SanPham.MaLoaiSP = LoaiSanPham.MaLoaiSP";

            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getInt("MaSP")+"");
                list.add(rs.getString("TenSP"));
                list.add(rs.getString("TenLoaiSP"));
                list.add("\n");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi");
        }
        System.out.println(list.toString());
        
        nhapLSP();
    }
    
}