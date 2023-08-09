package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.Dao;
import model.dao.DaoFactory;
import model.entities.Department;
import model.entities.Seller;

public class App {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		Dao<Seller> sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("==== Test 1: seller findById ====");
		Seller seller = sellerDao.findById(3);		
		System.out.println(seller);	
		
		System.out.println("\n==== Test 2: seller findByDepartment ====");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n==== Test 3: seller findAll ====");
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n==== Test 4: seller insert ====");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted. New id = " + newSeller.getId());

		System.out.println("\n==== TEST 6: seller delete ====");
		System.out.println("Enter id for delete: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed");
		
		sc.close();
	}

}
