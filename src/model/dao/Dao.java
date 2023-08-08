package model.dao;

import java.util.List;

import model.entities.Department;

public interface Dao<T> {
	
	void insert(T obj);
	void update(T obj);
	void deleteById(T obj);
	Department findById(Integer id);
	List<T> findAll();

}
