package Bai2;

public class Bai2 {

    public static void main(String[] args) {
        NhanVien_DbIO nv = new NhanVien_DbIO();
        nv.getAll();
        if (nv.insert()) {
            nv.getAll();
        } else {
            System.out.println("không thêm được");
        }
        if (nv.update()) {
            nv.getAll();
        } else {
            System.out.println("không cập nhật được");
        }
        if (nv.delete()) {
            nv.getAll();
        } else {
            System.out.println("KHông xóa được");
        }

        SanPham_DbIO sp = new SanPham_DbIO();
        sp.getAll();
        if (sp.insert()) {
            sp.getAll();
        } else {
            System.out.println("không thêm được");
        }
        if (sp.update()) {
            sp.getAll();
        } else {
            System.out.println("không cập nhật được");
        }
        if (sp.delete()) {
            sp.getAll();
        } else {
            System.out.println("KHông xóa được");
        }

        HoaDon_DbIO hd = new HoaDon_DbIO();
        hd.getAll();
        if (hd.insert()) {
            hd.getAll();
        } else {
            System.out.println("không thêm được");
        }
        if (hd.update()) {
            hd.getAll();
        } else {
            System.out.println("không cập nhật được");
        }
        if (hd.delete()) {
            hd.getAll();
        } else {
            System.out.println("KHông xóa được");
        }

        ChiTietHD_DbIO cthd = new ChiTietHD_DbIO();
        cthd.getAll();
        if (cthd.insert()) {
            cthd.getAll();
        } else {
            System.out.println("không thêm được");
        }
        if (cthd.update()) {
            cthd.getAll();
        } else {
            System.out.println("không cập nhật được");
        }
        if (cthd.delete()) {
            cthd.getAll();
        } else {
            System.out.println("KHông xóa được");
        }
    }
}
