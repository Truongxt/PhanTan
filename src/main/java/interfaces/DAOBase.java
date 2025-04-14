package interfaces;

import java.util.ArrayList;

public interface DAOBase<Type> {

    public Type getOne(String id);

    public ArrayList<Type> getAll();

    public String generateID();

    public Boolean create(Type object);

    public Boolean update(String id, Type newObject);

    public Boolean delete(String id);
}
