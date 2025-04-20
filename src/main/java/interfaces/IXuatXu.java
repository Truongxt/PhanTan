package interfaces;

import entity.XuatXu;
import java.util.ArrayList;

public interface IXuatXu {

    boolean create(XuatXu xx);

    ArrayList<XuatXu> getAllXuatXu();

    XuatXu getXuatXuById(String maXuatXu);

    boolean updateXuatXu(String maXuatXu, XuatXu newXuatXu);

    boolean deleteXuatXu(String maXuatXu);

    int getSize();
}
