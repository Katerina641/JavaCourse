package treeset;

public class Person implements Comparable<Person> {
	String name;
	int age;
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return name +" " + age ;
	}
	/*@Override reverse Alphabetical order
	public int compareTo(Person other) {
		return name.compareTo(other.name);
		//return other.name.compareTo(name);
	}*/
	
	
	/*@Override  Alphabetical order
	public int compareTo(Person other) {
		return other.name.compareTo(name);
		//return other.name.compareTo(name);
	}
	*/
	
	/*public int compareTo(Person other) {// sort by age
		return ((Integer)age).compareTo(other.age);
		//return other.name.compareTo(name);
	}
	*/
	public int compareTo(Person other) {// sort by age REVERSE
		return ((Integer)other.age).compareTo(age);
		//return other.name.compareTo(name);
	}
}
