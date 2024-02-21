package org.examples.service.Impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.examples.exception.LibraryException;
import org.examples.model.Book;
import org.examples.model.Library1;
import org.examples.model.Library2;
import org.examples.model.Person;
import org.examples.service.LibraryService2;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class LibraryService2Impl implements LibraryService2 {
    private Library2 library2;
    @Override
    public void issueBook(String bookName) {
        List<Book> listOfBooks = library2.getListOfBooks();
        for (int i = 0; i < listOfBooks.size(); i++) {
            Book book = listOfBooks.get(i);
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                for (Person person : library2.getBorrowers()) {
                    if (book.getNoOfCopies() > 0) {
                        book.setNoOfCopies(book.getNoOfCopies() - 1);
                        List<Book> borrowedBooks = person.getBorrowedBooks();
                        borrowedBooks.add(book);
                        person.setBorrowedBooks(borrowedBooks);
                        System.out.println(bookName + " is issued to " + person.getName());
                    } else {
                        throw new LibraryException("Book has been taken");
                    }
                }
            }

        }
    }
}
