package Service;

import Bean.Type;

import java.util.List;

public interface TypeService {
    public List<Type> getType();
    public int addType(Type newType);
    public int deleteType(int typeId);
}
