package Dao;

import Bean.Type;

import java.util.List;

public interface TypeDao {
    List<Type> getType();

    int addType(Type newType);

    int deleteType(int typeId);
}
