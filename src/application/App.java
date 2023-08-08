package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class App {

	public static void main(String[] args) {
		
		
		
		Department department = new Department (1, "Sales");
		
		Seller seller = new Seller(
				
				1, "Menina", "boba@do.caramba", new Date(), department);
		
		System.out.println(seller);
		
		

	}

}
