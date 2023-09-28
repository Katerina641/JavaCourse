package treeset;

import java.util.*;// all libary

public class main {

	public static void main(String[] args) {
		
		//Treeset with strings
		
		TreeSet<String> colors = new TreeSet<>(); //natural order
		colors.add("Black");
		colors.add("Grey");
		colors.add("Brown");
		colors.add("Yellow");
		colors.add("Brown");
		System.out.println(colors.contains("grey"));
		
		int count = 1;
		for(String color : colors) {
			System.out.println(count + ": " + color);
			count++; 
		}
		
		//Treeset with Intigers
		TreeSet<Integer> values = new TreeSet<>();
		values.add(12);
		values.add(13);
		values.add(14);
		values.add(15);
		for(Integer value : values) {
			System.out.println(count + ": " + value);
			count++; 
		}
		
		//Treeset with custom object
		TreeSet<Person> people = new TreeSet<>();
		
		people.add(new Person("Tom", 43));
		people.add(new Person("Tomara", 20));
		people.add(new Person("Bob", 25));
		people.add(new Person("Stella", 11));
		
		for(Person person: people) {
			System.out.println(person);
		}
	}

}
