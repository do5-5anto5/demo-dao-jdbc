package application;

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
		System.out.print("Enter department Id: ");
		int departmentId = sc.nextInt();
		Department department = new Department(departmentId, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n==== TEST 6: seller delete ====");
		System.out.println("Enter id for delete: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed");
		
		sc.close();
	}

}
