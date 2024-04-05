/**
 * Name:
 * SBU ID:
 * Do not use any external packages other than the ones provided below!
*/
import java.io.*;

public class HW1 {
	private Student[] rawData;
	/* 
	 * You may add any variables that you find necessary, but add only what you need. 
	 */
	public HW1(String filePath) {
		rawData = new Student[20];
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			while((line = br.readLine()) != null) {
				String[] tokens = line.split("\\s");
				// 'tokens' is now an array of Strings.
				// TODO: use the values in 'tokens' to populate rawData
			}
			br.close();
		} catch(Exception e) {
			System.err.println("File error: " + e.getMessage());
			System.exit(-1);
		}
	}

	// TODO: Implement the following empty methods.
	/**
	 * Insert a student from the record. 
	 * If the array is already full, nothing happens, 
	 * @param s Student instance
	 * @return true if addition is successful, false otherwise
	 */
	public boolean addStudent(Student s) {
		return false;
	}
	
	/**
	 * Remove a student from the record. 
	 * @param s Student instance
	 * @return true if deletion is successful, false otherwise
	 */	
	public boolean removeStudent(Student s) {
		return false;
	}
	
	/**
	 * For a match, all fields must be equal. i.e., same name, same id, same major.
	 * @param s Student class
	 * @return A student in rawData that matches s or a null otherwise.
	 */
	public Student find(Student s) {
		return null;
	}

	/**
	 * Return a Student instance that has the first matching ID.
	 * @param s Student class
	 * @return A student in rawData that matches id or a null otherwise.
	 */
	public Student findByID(int id) {
		return null;
	}
	
	/**
	 * Return a Student instance that has the first matching major.
	 * @param s Student class
	 * @return A student in rawData that matches major or a null otherwise.
	 */
	public Student findByMajor(String major) {
		return null;
	}
	
	public static void main(String[] args) {
		HW1 hw = new HW1(args[1]);
		HW1Test test = new HW1Test(hw);
		// This is where you can add codes to test your implementation.
		// e.g., try 'hw.findByID(...)' or build your own Student instance to try 'hw.find(...)'.
	}
}

class Student {
	private String name;
	private String major;
	private int id;
	
	// TODO: Add a non-default constructor and all necessary methods  
		
}