/**
 * @author Rian Lima
 * @date   7.15.22
 */

package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enuns.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/yyyy");
		
		System.out.print("Enter department's name: ");
		String department = sc.nextLine();
		System.out.println("Enter worker data: ");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Level: ");
		String level = sc.nextLine();
		System.out.print("Base salary: ");
		Double salary = sc.nextDouble();
		sc.nextLine();
		
		Worker worker = new Worker (
				name, 
				WorkerLevel.valueOf(level), 
				salary, 
				new Department(department)
		);
		
		System.out.print("How many contracts to this worker? ");
		int n = sc.nextInt();
		sc.nextLine();
		
		for(int i = 1; i <= n; i++) {
			System.out.printf("Enter contract #%d data: \n", i);
			System.out.print("Date (DD/MM/YYYY): ");
			Date date = sdf.parse(sc.nextLine());
			System.out.print("Value per hour: ");
			Double valuePerHour = sc.nextDouble();
			sc.nextLine();
			System.out.print("Duration (hours): ");
			Integer hours = sc.nextInt();
			sc.nextLine();
			worker.addContract(new HourContract(date, valuePerHour, hours));
		}
		
		System.out.println();
		
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		Date date2 = sdf2.parse(sc.nextLine());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date2);
		
		int year = calendar.get(Calendar.YEAR);
		int month = 1 + calendar.get(Calendar.MONTH);
		
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for " 
										+ sdf2.format(date2) + ": " 
										+ String.format("%.2f", worker.income(year, month)));
		
		sc.close();
		System.exit(0);
	}

}
