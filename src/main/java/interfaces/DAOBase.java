package interfaces;

import java.rmi.Remote;
import java.util.ArrayList;

public interface DAOBase<Type> extends Remote {

    public Type getOne(String id) throws Exception;

    public ArrayList<Type> getAll() throws Exception;

    public String generateID() throws Exception;

    public Boolean create(Type object)  throws Exception;

    public Boolean update(String id, Type newObject) throws Exception;

    public Boolean delete(String id) throws Exception;
}
