package cse214;
/*

 * Name: Michael Lee

 * SBU ID: 112424954
 * Do not use any external packages other than the ones provided below!
*/
import java.io.*;

public class HW1 {
	private Student[] rawData;
	//Added a static variable counter to keep measure of Students added to rawData array.
	private static int counter = 0;
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
				//Added new students to rawData array
				Student temp = new Student(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
				addStudent(temp);
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
	
	//Only adds student if the rawData isn't full. Returns true if student addition is success, false otherwise.
	//counter goes up for each student added.
	public boolean addStudent(Student s) {
		if(rawData[19] == null) {
			rawData[counter++] = s;
			return true;
		}
		return false;
	}
	
	/**
	 * Remove a student from the record. 
	 * @param s Student instance
	 * @return true if deletion is successful, false otherwise
	 */	
	
	//Removes a student if student is found using studentIndex(s). 
	//If successful, shifts all subsequent students back 1 spot 
	//and counter goes down by one, then returns true. Else returns
	//false.
	public boolean removeStudent(Student s) {
		try {
			if(studentIndex(s) >= 0) {
				int x = studentIndex(s);
				rawData[x] = null;
				for(int i = x; i < counter; i++) {
					rawData[i] = rawData[i+1];
				}
				rawData[counter--] = null;
				return true;
			}
			return false;
		} catch(NullPointerException e) {
			System.err.println(e.getMessage());
			return false;
		}
		
	}
	
	/**
	 * For a match, all fields must be equal. i.e., same name, same id, same major.
	 * @param s Student class
	 * @return A student in rawData that matches s or a null otherwise.
	 */
	
	//Finds student using equalsTo method. If found, returns student
	//else returns null.
	public Student find(Student s) {
		for(Student s1:rawData) {
			if(s1.equalsTo(s))
				return s1;
		}
		return null;
	}

	/**
	 * Return a Student instance that has the first matching ID.
	 * @param s Student class
	 * @return A student in rawData that matches id or a null otherwise.
	 */
	
	//Finds student by equality of id. If found, returns student.
	//else, returns null.
	public Student findByID(int id) {
		for(Student s:rawData) {
			if(s.getId() == id)
				return s;
		}
		return null;
	}
	
	/**
	 * Return a Student instance that has the first matching major.
	 * @param s Student class
	 * @return A student in rawData that matches major or a null otherwise.
	 */
	
	//Finds student by equality of Strings. If found, returns student.
	//else, returns null.
	public Student findByMajor(String major) {
		for(Student s:rawData) {
			if(s.getMajor().contentEquals(major))
				return s;
		}
		return null;
	}
	
	//Finds the index of a student from rawData array. If not found,
	//returns -1.
	public int studentIndex(Student s) {
		for(int i = 0; i < rawData.length; i++) {
			if(rawData[i] != null) {
				if(rawData[i].toString().contentEquals(s.toString()))
					return i;
			}		
		}
		return -1;
	}
	
	//Prints students available in rawData
	public void printStudents() {
		for(int i = 0; i < counter; i++)
			System.out.print(rawData[i].toString());
	}
	
	public static void main(String[] args) {
		HW1 hw = new HW1(args[0]);
		//HW1Test test = new HW1Test(hw);
		// This is where you can add codes to test your implementation.
		// e.g., try 'hw.findByID(...)' or build your own Student instance to try 'hw.find(...)'.
	}
}

class Student {
	private String name;
	private String major;
	private int id;
	
	// TODO: Add a non-default constructor and all necessary methods  
	
	//non-default constructor
	public Student(String name, String major, int id) {
		this.name = name;
		this.major = major;
		this.id = id;
	}
	//checks equality between students by checking equality between name, major, and id.
	public boolean equalsTo(Student s2) {
		return (this.name.contentEquals(s2.name) && this.major.contentEquals(s2.major) && this.id == s2.id);
	}
	public int getId() {
		return this.id;
	}
	public String getMajor() {
		return this.major;
	}
	public String getName() {
		return this.name;
	}
	//returns a string with name, major, and id.
	public String toString() {
		return this.name + " " + this.major + " " + this.id + "\n";
	}
		
}