package lk.ijse.pos_system.dao;

import org.hibernate.Session;

import java.util.List;

public interface CrudDAO <T> extends SuperDAO {
    boolean add(T entity) throws Exception;
    T search(String id) throws Exception;
    boolean update(T entity) throws Exception;
    boolean delete(String id) throws Exception;
    List<T> getAll() throws Exception;

}
