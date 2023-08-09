package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.Dao;
import model.entities.Department;

public class DepartmentDaoJDBC implements Dao<Department>{
	
	private Connection connection;
	
	public DepartmentDaoJDBC (Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
	
	try {
		st = connection.prepareStatement(
				"insert into department "
		        +"(Name) "
                +"value "
                +"(?)",
                Statement.RETURN_GENERATED_KEYS
	            );
		
		st.setString(1, obj.getName());
		
		int affectedRows = st.executeUpdate();
		
		if(affectedRows > 0) {
			ResultSet rs = st.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				obj.setId(id);
			}
			DB.closeResultSet(rs);
		}
		else {
			throw new DbException("Unexpected error! No affected rows.");
		}
	}
	catch (SQLException e) {
		throw new DbException (e.getMessage());
	}
	finally {
		DB.closeStatement(st);
	}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
				"UPDATE department " +
				"SET Name = ? " +
				"WHERE Id = ?");

			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
PreparedStatement st = null;
		
		try {
			st = connection.prepareStatement(
					"DELETE FROM department "
					+"WHERE "
					+"Id = ? "
					);
			
			st.setInt(1, id);
			
			int affectedRows = st.executeUpdate();
			if (affectedRows == 0) {
				throw new DbException("Invalid Id.");
			}
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getLocalizedMessage());
		}
		finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement(
					" SELECT * FROM department WHERE Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement(" SELECT * FROM department");
			
			rs = st.executeQuery();
						
			List<Department> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (rs.next()) {
				
				Department dep = map.get(rs.getInt("Id"));
				
				if (dep == null) {	
					dep = new Department();
					dep.setId(rs.getInt("Id"));
					dep.setName(rs.getString("Name"));
					map.put(rs.getInt("Id"), dep);
				}				
			}
			for (Integer key: map.keySet()) {
				list.add(map.get(key));
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findByDepartment(Department department) {
		return null;
	}
	
}

	