package application;

import java.util.Date;

import model.dao.Dao;
import model.dao.DaoFactory;
import model.entities.Department;
import model.entities.Seller;

public class App {

	public static void main(String[] args) {
		
		
		
		Department department = new Department (1, "Sales");
		
		Seller seller = new Seller(				
				1, "Menina", "boba@do.caramba", new Date(), department);
		
		Dao<Seller> sellerDao = DaoFactory.createSellerDao();
		
		System.out.println(seller);	
		

	}

}
