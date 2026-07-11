package studentManagementSystem;

import java.io.IOException;
import java.util.Scanner;

/**
 * Entry point of the Student Management System. Displays the menu, accepts user
 * input, validates data, and invokes the appropriate operations in
 * StudentManager.
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		StudentManager ob = new StudentManager();
		Scanner sc = new Scanner(System.in);
		int ch = 0;
		do {
			System.out.println("enter choice\n" + "1.Add student\n" + "2.view student\n" + "3.search student\n"
					+ "4.delete student\n" + "5.update student\n" + "6.sorting\n" + "7.statistics\n"
					+ "8.search by department\n" + "9.filter students\n" + "10.exit\n");
			System.out.println("Enter the choice:");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("no of students you want to add");
				int n = sc.nextInt();
				for (int i = 1; i <= n; i++) {
					System.out.println("enter details for student " + i);
					System.out.println("id,name,age,dept,marks");
					String s = sc.next();
					String[] arr = s.split(",");
					arr[1] = arr[1].trim();
					if (arr[1].isEmpty()) {
						System.out.println("Name cannot be empty.");
						continue;
					}
					if (!arr[1].matches("[A-Za-z ]+")) {
						System.out.println("Invalid name.");
						continue;
					}
					String dept = arr[3].trim().toUpperCase();

					if (!(dept.equals("CSD") || dept.equals("CSM") || dept.equals("CSE") || dept.equals("ECE")
							|| dept.equals("EEE") || dept.equals("MECH") || dept.equals("CIVIL"))) {

						System.out.println("Invalid department.");
						continue;
					}
					double marks;
					int age;
					int id;
					try {
						marks = Double.parseDouble(arr[4]);
						age = Integer.parseInt(arr[2]);
						id = Integer.parseInt(arr[0]);
					} catch (NumberFormatException e) {
						System.out.println("ID, Age and Marks must be numeric.");
						continue;
					}
					;
					if (marks < 0 || marks > 100) {
						System.out.println("Invalid marks.");
						continue;
					}
					if (age < 16 || age > 100) {
						System.out.println("Invalid age.");
						continue;
					}
					if (id <= 0) {
						System.out.println("Invalid id.");
						continue;
					}
					Student stu = new Student(id, arr[1], age, dept, marks);
					ob.addStudent(stu);
				}
				break;
			case 2:
				ob.display();
				break;
			case 3:
				System.out.println("enter student id");
				ob.searchStudent(sc.nextInt());
				break;
			case 4:
				System.out.println("enter student id");
				ob.deleteStudent(sc.nextInt());
				break;
			case 5:
				System.out.println("1.update entire details\n" + "2.update name\n" + "3.update age\n"
						+ "4.update dept\n" + "5.update marks\n");
				int choice = sc.nextInt();
				System.out.println("enter ID");
				int id = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.println("enter name " + "age " + "dept " + "marks ");
					ob.updateStudent(id, sc.next(), sc.nextInt(), sc.next(), sc.nextInt());
					break;

				case 2:
					System.out.println("enter name");
					ob.updateName(id, sc.next());
					break;
				case 3:
					System.out.println("enter age");
					ob.updateAge(id, sc.nextInt());
					break;
				case 4:
					System.out.print("enter dept");
					ob.updateDept(id, sc.next());
					break;
				case 5:
					System.out.println("enter marks");
					ob.updateMarks(id, sc.nextInt());
					break;

				default:
					System.out.println("invalid input");

				}
				break;
			case 6:
				System.out.println("sort by\n" + "1.ID(low to high)\n" + "2.ID(high to low)\n" + "3.name\n"
						+ "4.marks\n" + "5.age\n" + "6.dept\n");
				int choose = sc.nextInt();
				switch (choose) {
				case 1:
					ob.sortByID();
					break;
				case 2:
					ob.descending();
					break;
				case 3:
					ob.sortByName();
					break;
				case 4:
					ob.sortByMarks();
					break;
				case 5:
					ob.sortByAge();
					break;
				case 6:
					ob.sortByDept();
					break;
				default:
					ob.sortByID();
				}
				break;
			case 7:
				System.out.println(" STUDENT STATISTICS MENU");
				System.out.println("1. Total Students");
				System.out.println("2. Average Marks");
				System.out.println("3. Highest Marks");
				System.out.println("4. Lowest Marks");
				System.out.println("5. Pass Percentage");
				System.out.print("Enter your choice: ");

				int c = sc.nextInt();
				switch (c) {

				case 1:
					ob.totalStudents();
					break;

				case 2:
					ob.averageMarks();
					break;

				case 3:
					ob.highestMarks();
					break;

				case 4:
					ob.lowestMarks();
					break;

				case 5:
					ob.passPercentage();
					break;
				default:
					System.out.println("invalid input");
				}
				break;
			case 8:
				System.out.println("enter the department");
				ob.searchByDepartment(sc.next());
				break;
			case 9:
				System.out.println("filter students based on\n" + "1.marks\n" + "2.Age\n");
				int n1 = sc.nextInt();
				switch (n1) {
				case 1: {
					System.out.println("enter marks to filter");
					ob.filterByMarks(sc.nextDouble());
					break;
				}
				case 2: {
					System.out.println("enter age to filter");
					ob.filterByAge(sc.nextInt());
					break;
				}
				default:
					System.out.println("invalid input");
				}
				break;

			case 10:
				System.out.println("exit\n" + "Thankyou");
				break;
			default:
				System.out.println("invalid choice");
			}

		} while (ch != 10);

	}

}
