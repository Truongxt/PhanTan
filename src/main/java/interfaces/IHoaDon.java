package interfaces;

import entity.HoaDon;

import java.util.List;
import java.util.Optional;

public interface IHoaDon {

    Optional<HoaDon> findById(String maHoaDon);

    List<HoaDon> getAll();

    boolean create(HoaDon hoaDon);

    boolean update(HoaDon hoaDon);
}
