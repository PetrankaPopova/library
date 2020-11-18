package library.service.imp;

import library.model.entity.Author;
import library.model.entity.Book;
import library.model.entity.Category;
import library.model.entity.Size;
import library.model.service.BookServiceModel;
import library.repository.AuthorRepository;
import library.repository.BookRepository;
import library.repository.CategoryRepository;
import library.repository.SizeRepository;
import library.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;
    private final SizeRepository sizeRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper,
                           AuthorRepository authorRepository, SizeRepository sizeRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
        this.sizeRepository = sizeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public BookServiceModel addNewBook(BookServiceModel bookServiceModel) {

        return null;
    }

    @Override
    //FOR TEST!!!
    public String deleteBookByName(String bookTitle) {
        String deletedBook = null;
        Book foundedBook = this.bookRepository.findBookByTitle(bookTitle).orElse(null);
        if (foundedBook != null) {
            deletedBook = foundedBook.getTitle();
            this.bookRepository.deleteById(foundedBook.getId());
        }
        return deletedBook != null ? "No exist book for remove" : deletedBook;
    }

    @Override
    public List<BookServiceModel> getAllBooksByCategory(String category) {
        List<Book> allBooksByCategory = bookRepository.findAllByCategory(category);
        return bookListToBookServiceModelList(allBooksByCategory);

    }

    @Override
    public List<BookServiceModel> getAllBooksByAuthorName(String authorName) {
        List<Book> allBooksByCategory = bookRepository.findAllByAuthor(authorName);
        return bookListToBookServiceModelList(allBooksByCategory);
    }

    @Override
    public List<BookServiceModel> getAllBooksByPartOfTitle(String strFromTitle) {
        List<Book> foundedBooks = this.bookRepository.searchByPartOfTitle(strFromTitle);
        if (foundedBooks == null) {
            return null;
            //throw new BookWithThisNameIsNotExist("Book with this name is not exist!");
        }
        return bookListToBookServiceModelList(foundedBooks);
    }

    private List<BookServiceModel> bookListToBookServiceModelList(List<Book> allBooksByCategory) {
        return allBooksByCategory
                .stream()
                .map(b -> this.modelMapper.map(b, BookServiceModel.class))
                .collect(Collectors.toList());
    }
}
