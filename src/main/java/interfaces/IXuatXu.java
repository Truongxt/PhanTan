package interfaces;

import entity.XuatXu;

import java.util.List;

public interface IXuatXu {
    long countXuatXuByCountry(String country);
    List<XuatXu> listXuatXuByCountry(String country);
    XuatXu findById(String maXuatXu);
    boolean create(XuatXu xuatXu);
    boolean update(XuatXu xuatXu);
    boolean delete(String id);
}
