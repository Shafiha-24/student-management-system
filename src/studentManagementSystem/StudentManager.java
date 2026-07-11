package studentManagementSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Manages all student-related operations. Provides functionalities such as
 * adding, viewing, searching, updating, deleting, sorting, filtering,
 * statistics generation, and file handling.
 *
 */
public class StudentManager {
	private static final String FILE_NAME = "students.txt";
	private static final String TEMP_FILE = "temporary.txt";
	private ArrayList<Student> al = new ArrayList<>();

//CRUD OPERATIONS
	/**
	 * Adds a new student to the file after validating the student ID for
	 * duplicates.
	 *
	 * @param s Student object to be added
	 */
	public void addStudent(Student s) {
		try (FileReader fr = new FileReader(FILE_NAME);
				BufferedReader br = new BufferedReader(fr);
				FileWriter fw = new FileWriter(FILE_NAME, true);
				BufferedWriter bw = new BufferedWriter(fw);) {
			String str = br.readLine();
			while (str != null) {
				String[] ar = str.split(",");
				if (Integer.parseInt(ar[0].trim()) == s.getId()) {
					System.out.println("student already exist with this id");
					return;
				}
				str = br.readLine();
			}
			bw.write(s.toString());
			bw.newLine();
			bw.close();
			System.out.println("Student is successfully added");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Displays all students stored in the file.
	 */
	public void display() {
		try (FileReader fr = new FileReader(FILE_NAME); BufferedReader br = new BufferedReader(fr)) {
			String s = br.readLine();
			while (s != null) {
				System.out.println(s);
				s = br.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Updates the details of an existing student.
	 *
	 * @param id Student ID to be updated
	 */
	public void updateStudent(int id, String name, int age, String dept, int marks) throws IOException {
		boolean found = false;
		FileWriter FW = new FileWriter(TEMP_FILE);
		BufferedWriter BW = new BufferedWriter(FW);
		FileReader fr = new FileReader(FILE_NAME);
		BufferedReader br = new BufferedReader(fr);
		String s = br.readLine();
		while (s != null) {
			String[] ar = s.split(",");
			if (Integer.parseInt(ar[0]) == id) {
				Student ob = new Student(id, name, age, dept, marks);
				BW.write(ob.toString());
				BW.newLine();
				found = true;
			} else {
				BW.write(s);
				BW.newLine();
			}
			s = br.readLine();
		}

		File og = new File(FILE_NAME);
		File temp = new File(TEMP_FILE);
		if (found) {
			if (og.delete()) {
				if (temp.renameTo(og)) {
					System.out.println("Student updated successfully.");
				} else {
					System.out.println("Could not rename temporary file.");
				}

			} else {
				System.out.println("file not updated");
				return;
			}
		} else {
			System.out.println("student not found");
		}

	}

	/**
	 * Updates the name of an existing student.
	 *
	 * @param id   Student ID
	 * @param name New student name
	 */
	public void updateName(int id, String name) throws IOException {
		boolean found = false;
		FileWriter FW = new FileWriter(TEMP_FILE);
		BufferedWriter BW = new BufferedWriter(FW);
		FileReader fr = new FileReader(FILE_NAME);
		BufferedReader br = new BufferedReader(fr);
		String s = br.readLine();
		while (s != null) {
			String[] ar = s.split(",");
			if (Integer.parseInt(ar[0]) == id) {
				Student ob = new Student(id, name, Integer.parseInt(ar[2].trim()), ar[3],
						Double.parseDouble(ar[4].trim()));
				BW.write(ob.toString());
				BW.newLine();
				found = true;
			} else {
				BW.write(s);
				BW.newLine();
			}
			s = br.readLine();
		}
		br.close();
		fr.close();
		BW.close();
		File og = new File(FILE_NAME);
		File temp = new File(TEMP_FILE);
		if (found) {
			if (og.delete()) {
				if (temp.renameTo(og)) {
					System.out.println("Student updated successfully.");
				} else {
					System.out.println("Could not rename temporary file.");
				}

			} else {
				System.out.println("file not updated");
				return;
			}
		} else {
			System.out.println("student not found");
		}

	}

	/**
	 * Updates the age of an existing student.
	 *
	 * @param id  Student ID
	 * @param age New student age
	 */
	public void updateAge(int id, int age) throws IOException {
		boolean found = false;
		FileWriter FW = new FileWriter("temporary.txt");
		BufferedWriter BW = new BufferedWriter(FW);
		FileReader fr = new FileReader(FILE_NAME);
		BufferedReader br = new BufferedReader(fr);
		String s = br.readLine();
		while (s != null) {
			String[] ar = s.split(",");
			if (Integer.parseInt(ar[0]) == id) {
				Student ob = new Student(id, ar[1], age, ar[3], Double.parseDouble(ar[4].trim()));
				BW.write(ob.toString());
				BW.newLine();
				found = true;
			} else {
				BW.write(s);
				BW.newLine();
			}
			s = br.readLine();
		}
		br.close();
		fr.close();
		BW.close();
		File og = new File(FILE_NAME);
		File temp = new File(TEMP_FILE);
		if (found) {
			if (og.delete()) {
				if (temp.renameTo(og)) {
					System.out.println("Student updated successfully.");
				} else {
					System.out.println("Could not rename temporary file.");
				}

			} else {
				System.out.println("file not updated");
				return;
			}
		} else {
			System.out.println("student not found");
		}

	}

	/**
	 * Updates the department of an existing student.
	 *
	 * @param id   Student ID
	 * @param dept New department
	 */
	public void updateDept(int id, String dept) throws IOException {
		boolean found = false;
		FileWriter FW = new FileWriter(TEMP_FILE);
		BufferedWriter BW = new BufferedWriter(FW);
		FileReader fr = new FileReader(FILE_NAME);
		BufferedReader br = new BufferedReader(fr);
		String s = br.readLine();
		while (s != null) {
			String[] ar = s.split(",");
			if (Integer.parseInt(ar[0]) == id) {
				Student ob = new Student(id, ar[1], Integer.parseInt(ar[2].trim()), dept,
						Double.parseDouble(ar[4].trim()));
				BW.write(ob.toString());
				BW.newLine();
				found = true;
			} else {
				BW.write(s);
				BW.newLine();
			}
			s = br.readLine();
		}
		br.close();
		fr.close();
		BW.close();
		File og = new File(FILE_NAME);
		File temp = new File(TEMP_FILE);
		if (found) {
			if (og.delete()) {
				if (temp.renameTo(og)) {
					System.out.println("Student updated successfully.");
				} else {
					System.out.println("Could not rename temporary file.");
				}

			} else {
				System.out.println("file not updated");
				return;
			}
		} else {
			System.out.println("student not found");
		}

	}

	/**
	 * Updates the marks of an existing student.
	 *
	 * @param id    Student ID
	 * @param marks New marks
	 */
	public void updateMarks(int id, int marks) throws IOException {
		boolean found = false;
		FileWriter FW = new FileWriter(TEMP_FILE);
		BufferedWriter BW = new BufferedWriter(FW);
		FileReader fr = new FileReader(FILE_NAME);
		BufferedReader br = new BufferedReader(fr);
		String s = br.readLine();
		while (s != null) {
			String[] ar = s.split(",");
			if (Integer.parseInt(ar[0]) == id) {
				Student ob = new Student(id, ar[1], Integer.parseInt(ar[2].trim()), ar[3], marks);
				BW.write(ob.toString());
				BW.newLine();
				found = true;
			} else {
				BW.write(s);
				BW.newLine();
			}
			s = br.readLine();
		}
		br.close();
		fr.close();
		BW.close();
		File og = new File(FILE_NAME);
		File temp = new File(TEMP_FILE);
		if (found) {
			if (og.delete()) {
				if (temp.renameTo(og)) {
					System.out.println("Student updated successfully.");
				} else {
					System.out.println("Could not rename temporary file.");
				}

			} else {
				System.out.println("file not updated");
				return;
			}
		} else {
			System.out.println("student not found");
		}

	}

	/**
	 * Deletes a student from the file using the specified student ID.
	 *
	 * @param id Student ID
	 */
	public void deleteStudent(int id) {
		boolean found = false;
		try (FileWriter FW = new FileWriter(TEMP_FILE);
				BufferedWriter BW = new BufferedWriter(FW);

				FileReader fr = new FileReader(FILE_NAME);
				BufferedReader br = new BufferedReader(fr)) {
			String s = br.readLine();
			while (s != null) {
				String[] ar = s.split(",");
				if (Integer.parseInt(ar[0]) == id) {
					found = true;

				} else {
					BW.write(s);
					BW.newLine();
				}
				s = br.readLine();
			}
			br.close();
			fr.close();
			BW.close();
			File og = new File(FILE_NAME);
			File temp = new File(TEMP_FILE);
			if (found) {
				if (og.delete()) {
					if (temp.renameTo(og)) {
						System.out.println("Student deleted successfully.");
					} else {
						System.out.println("Could not rename temporary file.");
					}

				} else {
					System.out.println("file not deleted");
					return;
				}
			} else {
				System.out.println("student not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Searching
	/**
	 * Searches for a student using the student ID.
	 *
	 * @param id Student ID
	 */
	public void searchStudent(int id) {
		try (FileReader fr = new FileReader(FILE_NAME); BufferedReader br = new BufferedReader(fr)) {
			String s = br.readLine();
			while (s != null) {
				String[] ar = s.split(",");
				if (Integer.parseInt(ar[0]) == id) {
					System.out.println(s);

					return;
				}
				s = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("student not found");
	}

	/**
	 * Displays all students belonging to the specified department.
	 */
	public void searchByDepartment(String dept) {
		boolean found = false;
		ArrayList<Student> al = getAllStudents();
		for (Student x : al) {
			if (x.getDepartment().equalsIgnoreCase(dept)) {
				System.out.println(x);
				found = true;
			}
		}
		if (!found) {
			System.out.println("Department not found");
		}

	}

	/**
	 * Compares two Student objects based on their names in alphabetical order.
	 *
	 */
	class NameComparator implements Comparator<Student> {
		@Override
		public int compare(Student s1, Student s2) {
			return String.CASE_INSENSITIVE_ORDER.compare(s1.getName(), s2.getName());
		}
	}

	/**
	 * Compares two Student objects based on their marks in ascending order.
	 *
	 */
	class MarksComparator implements Comparator<Student> {

		@Override
		public int compare(Student s1, Student s2) {

			return -1 * Double.compare(s1.getMarks(), s2.getMarks());
		}
	}

	/**
	 * Compares two Student objects based on their student ID in ascending order.
	 *
	 */
	class IdComparator implements Comparator<Student> {
		@Override
		public int compare(Student s1, Student s2) {
			return Double.compare(s1.getId(), s2.getId());
		}
	}

	/**
	 * Compares two Student objects based on their student ID in descending order.
	 *
	 */
	class DescendingIdComparator implements Comparator<Student> {

		@Override
		public int compare(Student s1, Student s2) {

			return -1 * Double.compare(s1.getId(), s2.getId());
		}
		/**
		 * Compares two Student objects based on their age in ascending order.
		 *
		 */
	}

	class AgeComparator implements Comparator<Student> {

		@Override
		public int compare(Student s1, Student s2) {
			return Double.compare(s1.getAge(), s2.getAge());
		}
	}

	/**
	 * Compares two Student objects based on their department in alphabetical order.
	 *
	 */
	class DepartmentComparator implements Comparator<Student> {

		@Override
		public int compare(Student s1, Student s2) {
			return String.CASE_INSENSITIVE_ORDER.compare(s1.getDepartment(), s2.getDepartment());
		}
	}

//SORTING OPERATIONS
	/**
	 * Sorts all students by name in alphabetical order.
	 */
	public void sortByName() {
		ArrayList<Student> al = getAllStudents();
		Collections.sort(al, new NameComparator());

		try (FileWriter FW = new FileWriter(TEMP_FILE); BufferedWriter BW = new BufferedWriter(FW)) {
			for (Student x : al) {
				BW.write(x.toString());
				BW.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		File temp = new File(TEMP_FILE);
		File og = new File(FILE_NAME);
		if (og.delete()) {
			if (temp.renameTo(og)) {
				System.out.println("Sorted successfully.");
			} else {
				System.out.println("Could not rename temporary file.");
			}

		} else {
			System.out.println("file not updated");
			return;
		}
	}

	/**
	 * Sorts all students by marks in descending order.
	 */
	public void sortByMarks() {
		ArrayList<Student> al = getAllStudents();
		Collections.sort(al, new MarksComparator());
		try (FileWriter FW = new FileWriter(TEMP_FILE); BufferedWriter BW = new BufferedWriter(FW)) {
			for (Student x : al) {
				BW.write(x.toString());
				BW.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		File temp = new File(TEMP_FILE);
		File og = new File(FILE_NAME);
		if (og.delete()) {
			if (temp.renameTo(og)) {
				System.out.println("Sorted successfully.");
			} else {
				System.out.println("Could not rename temporary file.");
			}

		} else {
			System.out.println("file not updated");
			return;
		}
	}

	/**
	 * Sorts all students by student ID in ascending order.
	 */
	public void sortByID() {
		ArrayList<Student> al = getAllStudents();
		Collections.sort(al, new IdComparator());
		try (FileWriter FW = new FileWriter(TEMP_FILE); BufferedWriter BW = new BufferedWriter(FW)) {
			for (Student x : al) {
				BW.write(x.toString());
				BW.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		File temp = new File(TEMP_FILE);
		File og = new File(FILE_NAME);
		if (og.delete()) {
			if (temp.renameTo(og)) {
				System.out.println("Sorted successfully.");
			} else {
				System.out.println("Could not rename temporary file.");
			}

		} else {
			System.out.println("file not updated");
			return;
		}

	}

	/**
	 * Sorts all students by student ID in descending order.
	 */
	public void descending() {
		ArrayList<Student> al = getAllStudents();
		Collections.sort(al, new DescendingIdComparator());
		try (FileWriter FW = new FileWriter(TEMP_FILE); BufferedWriter BW = new BufferedWriter(FW)) {
			for (Student x : al) {
				BW.write(x.toString());
				BW.newLine();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		File temp = new File(TEMP_FILE);
		File og = new File(FILE_NAME);

		if (og.delete()) {
			if (temp.renameTo(og)) {
				System.out.println("Sorted successfully.");
			} else {
				System.out.println("Could not rename temporary file.");
			}

		} else {
			System.out.println("file not sorted");
			return;
		}

	}

	/**
	 * Sorts all students by age in ascending order.
	 */
	public void sortByAge() {
		ArrayList<Student> al = getAllStudents();
		Collections.sort(al, new AgeComparator());
		try (FileWriter FW = new FileWriter(TEMP_FILE); BufferedWriter BW = new BufferedWriter(FW)) {
			for (Student x : al) {
				BW.write(x.toString());
				BW.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		File temp = new File(TEMP_FILE);
		File og = new File(FILE_NAME);
		if (og.delete()) {
			if (temp.renameTo(og)) {
				System.out.println("Sorted successfully.");
			} else {
				System.out.println("Could not rename temporary file.");
			}

		} else {
			System.out.println("file not updated");
			return;
		}
	}

	/**
	 * Sorts all students by department in alphabetical order.
	 */
	public void sortByDept() {
		ArrayList<Student> al = getAllStudents();
		Collections.sort(al, new DepartmentComparator());
		try (FileWriter FW = new FileWriter(TEMP_FILE); BufferedWriter BW = new BufferedWriter(FW)) {
			for (Student x : al) {
				BW.write(x.toString());
				BW.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		File temp = new File(TEMP_FILE);
		File og = new File(FILE_NAME);
		if (og.delete()) {
			if (temp.renameTo(og)) {
				System.out.println("Sorted successfully.");
			} else {
				System.out.println("Could not rename temporary file.");
			}

		} else {
			System.out.println("file not updated");
			return;
		}
	}

	// STATISTICS
	/**
	 * Displays the total number of students.
	 */
	public void totalStudents() {
		ArrayList<Student> al = getAllStudents();
		System.out.println("total students:" + al.size());
	}

	/**
	 * Calculates and displays the average marks of all students.
	 */
	public void averageMarks() {
		ArrayList<Student> al = getAllStudents();
		if (al.isEmpty()) {
			System.out.println("no students found");
			return;
		}
		double sum = 0;
		for (Student x : al) {
			sum += x.getMarks();
		}
		double avg = sum / al.size();
		System.out.println("avg marks:" + avg);
	}

	/**
	 * Finds and displays the highest marks among all students.
	 */
	public void highestMarks() {
		ArrayList<Student> al = getAllStudents();
		if (al.isEmpty()) {
			System.out.println("no students found");
			return;
		}
		double highest = al.get(0).getMarks();
		for (Student x : al) {
			if (x.getMarks() > highest) {
				highest = x.getMarks();
			}
		}
		System.out.println("highest marks:" + highest);
	}

	/**
	 * Finds and displays the lowest marks among all students.
	 */
	public void lowestMarks() {
		ArrayList<Student> al = getAllStudents();
		if (al.isEmpty()) {
			System.out.println("no students found");
			return;
		}
		double lowest = al.get(0).getMarks();
		for (Student x : al) {
			if (x.getMarks() < lowest) {
				lowest = x.getMarks();
			}
		}
		System.out.println("Lowest marks:" + lowest);
	}

	/**
	 * Calculates and displays the pass percentage of students based on the passing
	 * marks criteria.
	 */
	public void passPercentage() {
		ArrayList<Student> al = getAllStudents();
		if (al.isEmpty()) {
			System.out.println("no students found");
			return;
		}
		int passcount = 0;
		for (Student x : al) {
			if (x.getMarks() >= 35) {
				passcount++;
			}
		}
		double percentage = (passcount * 100.0) / al.size();
		System.out.println("pass percentage:" + percentage);

	}

	// Filtering
	/**
	 * Displays students whose marks are greater than or equal to the specified
	 * value.
	 */
	public void filterByMarks(double marks) {
		ArrayList<Student> al = getAllStudents();
		boolean found = false;
		if (al.isEmpty()) {
			System.out.println("no students found");
			return;
		}
		for (Student x : al) {
			if (x.getMarks() >= marks) {
				System.out.println(x);
				found = true;
			}
		}
		if (!found) {
			System.out.println("no students with these marks :" + marks);
		}
	}

	/**
	 * Displays students whose age is greater than or equal to the specified value.
	 *
	 * @param age Minimum age to filter
	 */
	public void filterByAge(int age) {
		ArrayList<Student> al = getAllStudents();
		boolean found = false;
		if (al.isEmpty()) {
			System.out.println("no students found");
			return;
		}
		for (Student x : al) {
			if (x.getAge() >= age) {
				System.out.println(x);
				found = true;
			}
		}
		if (!found) {
			System.out.println("no students with these age " + age);
		}
	}

	/**
	 * Reads all student records from the file and returns them as an ArrayList.
	 *
	 * @return List of students
	 */
	public ArrayList<Student> getAllStudents() {
		try (FileReader fr = new FileReader(FILE_NAME); BufferedReader br = new BufferedReader(fr)) {
			String s = br.readLine();
			while (s != null) {
				String[] ar = s.split(",");
				Student ob = new Student(Integer.parseInt(ar[0].trim()), ar[1], Integer.parseInt(ar[2].trim()), ar[3],
						Double.parseDouble(ar[4].trim()));
				al.add(ob);
				s = br.readLine();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}

}
