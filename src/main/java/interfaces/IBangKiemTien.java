package interfaces;

import entity.BangKiemTien;

import java.util.List;
import java.util.Optional;

public interface IBangKiemTien {

    public interface IBangKiemTien_DAO {

        Optional<BangKiemTien> findById(String id);

        List<BangKiemTien> findAll();

        boolean create(BangKiemTien bangKiemTien);

        boolean update(BangKiemTien bangKiemTien);

        boolean delete(String id);
    }

}
