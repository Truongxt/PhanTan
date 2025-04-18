package interfaces;

import entity.XuatXu;

import java.util.List;

public interface IXuatXu {
    long countXuatXuByCountry(String country) throws Exception;
    List<XuatXu> listXuatXuByCountry(String country) throws Exception;
    XuatXu findById(String maXuatXu) throws Exception;
    boolean create(XuatXu xuatXu) throws Exception;
    boolean update(XuatXu xuatXu) throws Exception;
    boolean delete(String id) throws Exception;
}
