package interfaces;

import entity.LoaiThuoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ILoaiThuoc {

    Boolean create(LoaiThuoc loaiThuoc);

    ArrayList<LoaiThuoc> getAllLoaiThuoc();

    LoaiThuoc getLoaiThuoc(String maLoai);

    boolean updateLoaiThuoc(String maLoai, LoaiThuoc newLoaiThuoc);

    int getSize();

    ArrayList<LoaiThuoc> searchByMaLoai(String maLoai);

    ArrayList<LoaiThuoc> searchByTenLoai(String tenLoai);
}
