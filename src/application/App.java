package application;

import java.util.Scanner;

import model.dao.Dao;
import model.dao.DaoFactory;
import model.entities.Seller;

public class App {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		Dao<Seller> sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(3);
		
		System.out.println(seller);	
		

		System.out.println("\n=== TEST 6: seller delete ====");
		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed");
	}

}
