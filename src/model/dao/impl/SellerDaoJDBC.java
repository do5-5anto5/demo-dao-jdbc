package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.Dao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements Dao<Seller> {
	
	private Connection connection;
	
	public SellerDaoJDBC (Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement st = null;
		
		try {
			st = connection.prepareStatement(
					"DELETE FROM seller "
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
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement(
					" SELECT seller.*,department.Name as DepName " 
					+" FROM seller INNER JOIN department " 
					+" ON seller.DepartmentId = department.Id " 
					+" WHERE seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department department = instantiateDepartment(rs);
				Seller obj = instantiateSeller(rs, department);				
				
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

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(department);
		
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepName"));
		
		return department;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
