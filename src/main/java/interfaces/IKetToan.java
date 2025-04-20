package interfaces;

import entity.KetToan;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface IKetToan extends Remote {
    Optional<KetToan> findById(String maKetToan) throws Exception;

    List<KetToan> findAll() throws Exception;

    boolean create(KetToan ketToan) throws Exception;

    boolean update(KetToan ketToan) throws Exception;

    boolean delete(String maKetToan) throws Exception;

    List<KetToan> filterByDateRange(java.util.Date startDate, java.util.Date endDate) throws Exception;

    double calculateTotalRevenue(String maKetToan) throws Exception;
}
