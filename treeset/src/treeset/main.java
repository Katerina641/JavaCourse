package LibraryManagementSystem;
public class BookClass {
	
	String title;
	String author;
	boolean ISBN;
}
++++++++++++++++++++++++++++++++++++++++
package LibraryManagementSystem;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryClass {

	ArrayList <BookClass> books = new ArrayList<>();
	BookClass bookClass = new BookClass();
	
public static void addBook(BookClass book, ArrayList <BookClass> books) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the title : ");
		book.title = scanner.next();
	
		System.out.println("Enter the autor : ");
		book.author = scanner.next();
		book.ISBN = true;
		books.add(book);
		for(BookClass n: books) {
		System.out.println("The book was added, author is : " + n.author +  " title : " + n.title);
		System.out.println("----------------");
		
		
		try {
		FileOutputStream file = new FileOutputStream("//home//dci-student//Desktop//test.txt");
		byte b[] = n.author.getBytes();
		file.write(b);
		file.close();
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
}


public static void checkoutBook(BookClass book, Person member, ArrayList <BookClass> books) {
	System.out.println("Enter the title : ");
	Scanner scanner = new Scanner(System.in);
	String title = scanner.next();
	for(BookClass n: books) {
	
		
		if(n.title.equalsIgnoreCase(title)) {
			System.out.println("This bok exist in the libary");
			System.out.println("You can take it " + member.name);
			books.remove(n.title);
			books.remove(n.author);
			n.ISBN = false;
	}
	else {
		System.out.println("This bok does not exist in the libary");
	}
	}
}
public static void returnBook(BookClass book, ArrayList <BookClass> books) {
	Scanner scanner = new Scanner(System.in);
	System.out.println("Enter the title : ");
	book.title = scanner.next();
	System.out.println("Enter the author : ");
	book.author = scanner.next();
	book.ISBN = true;
	for(BookClass n: books) {
		System.out.println("The book was return, author is : " + n.author +  " title : " + n.title);
	}
}
public static void displaying( ArrayList <BookClass> books) {
	for(BookClass n: books) {
		System.out.println("The book is : " + n.author +  " title : " + n.title);
	}
	
}
}
++++++++++++++++++++++++++++++++++++++++++++++++
  package LibraryManagementSystem;

public class Person {

	String name;
	String contactInformation;
}
  +++++++++++++++++++++++++++++++++++++++++++++++++
    package LibraryManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Person person = new Person();
		BookClass bookClass = new BookClass();
		LibraryClass libraryClass = new LibraryClass();
		LibraryMember libraryMember = new LibraryMember();
		
		System.out.println("Enter your name: ");
		person.name = scanner.next();
		
		System.out.println("Enter your contact information: ");
		person.contactInformation = scanner.next();
		
		int choice;
		do {
			
			
			System.out.println("Library operations: ");
			System.out.println("Add a book :  - 1");
			System.out.println("Check out a book :  - 2");
			System.out.println("Return a book :  - 3");
			System.out.println("View available books :  - 4");
			System.out.println("Exit :  - 5");
			System.out.println("Enter your choice: ");
			choice = scanner.nextInt();
			
			switch (choice) {
			case 1:
				
				libraryClass.addBook(bookClass, libraryClass.books );
				break;
			case 2:
				
				libraryClass.checkoutBook(bookClass, libraryMember,libraryClass.books);
				
				break;
			case 3:
				libraryClass.returnBook(bookClass, libraryClass.books );
				
				break;
			case 4:
				libraryClass.displaying(libraryClass.books);
				break;
			case 5:
				System.out.println("Exit of the programm");
				break;
			default:
				System.out.println("You was enter wrong number");
			
		}
			
	} while (choice != 5);
	}


}
