package org.examples.service.Impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.examples.exception.LibraryException;
import org.examples.model.Book;
import org.examples.model.Library1;
import org.examples.model.Person;
import org.examples.service.LibraryService1;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class LibraryService1Impl implements LibraryService1 {
    private Library1 library1;

    @Override
    public void issueBook(String bookName) {
        for (Book book : library1.getListOfBooks()) {
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                    for (Person person : library1.getBorrowers()) {
                        if (book.getNoOfCopies() > 0) {
                            book.setNoOfCopies(book.getNoOfCopies() - 1);
                            List<Book> borrowedBooks = person.getBorrowedBooks();
                            borrowedBooks.add(book);
                            person.setBorrowedBooks(borrowedBooks);
                            System.out.println(bookName+" is issued to " + person.getName());
                    }else {
                        throw new LibraryException("Book is taken");
                    }
                }
            }
        }
    }
}
