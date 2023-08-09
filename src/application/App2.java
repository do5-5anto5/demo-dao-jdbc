package application;

import java.util.List;
import java.util.Scanner;

import model.dao.Dao;
import model.dao.DaoFactory;
import model.entities.Department;

public class App2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Dao<Department> departmentDao = DaoFactory.createDepartmentDao();

		
		System.out.println("==== Test 1: department findById ====");
		Department department = departmentDao.findById(3);		
		System.out.println(department);
		
		System.out.println("\n==== Test 2: department findAll ====");
		List<Department>list = departmentDao.findAll();
		for (Department obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n==== Test 3: seller insert ====");
		Department newDepartment = new Department(null, "Beach");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted. New id = " + newDepartment.getId());
		
		System.out.println("\n=== TEST 4 : update =======");
		Department dep2 = departmentDao.findById(5);
		dep2.setName("Grocery");
		departmentDao.update(dep2);
		System.out.println("Update completed");
		
		System.out.println("\n==== TEST 6: department delete ====");
		System.out.println("Enter id for delete: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete completed");

		sc.close();
	}

}
