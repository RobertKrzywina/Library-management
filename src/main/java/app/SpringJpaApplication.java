package app;

import dao.BookDao;
import dao.ClientDao;
import model.Book;
import model.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.BookRepository;
import repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "dao")
@EntityScan(basePackages = "model")
@EnableJpaRepositories(basePackages = "repository")
public class SpringJpaApplication {

    private static List<Client> clients = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(SpringJpaApplication.class, args);

        BookDao bookDao = ctx.getBean(BookDao.class);
        ClientDao clientDao = ctx.getBean(ClientDao.class);

        books.add(new Book("9781250104625", "Pablo Escobar-My Father", "Juan Pablo Escobar"));
        books.add(new Book("9781402894626", "How to disapear-Simple tutorial", "Michael Scofield"));
        books.add(new Book("2345464634223", "Java for beginners", "Cay S. Horstmann"));
        books.add(new Book("9734345341214", "Zawód-Programista", "Maciej Aniserowicz"));
        books.add(new Book("9567657956934", "Junior Developer", "Mateusz Kupilas"));
        books.add(new Book("2349034589935", "Clean Code", "Robert C. Martin"));
        books.add(new Book("4040404040404", "Solve problems tutorial", "Zombie Developer"));
        books.add(new Book("9999999999999", "C++ Language", "Stephen Prata"));
        books.add(new Book("9343943941213", "Ronnie O'Sulivan biography", "Ronnie O'Sulivan"));
        books.add(new Book("1212121212121", "Core Java-Volume II--Advanced Features", "Cay S. Horstmann"));
        books.add(new Book("9719711917222", "Big C++", "Cay S. Horstmann"));
        books.add(new Book("1212121212124", "Jezyk C. Szkola programowania", "Stephen Prata"));
        books.add(new Book("4323435464521", "How to survive in Prison-tutorial", "Michael Scofield"));

        clients.add(new Client("Pablo", "Escobar", 30, false));
        clients.add(new Client("Michael", "Scofield", 25, true));
        clients.add(new Client("John", "Abruzzi", 32, false));
        clients.add(new Client("Albert", "Einstein", 50, true));
        clients.add(new Client("Joe", "Smith", 20, true));
        clients.add(new Client("Jack", "Garcia", 17, true));
        clients.add(new Client("Martin", "Martin", 20, false));
        clients.add(new Client("Paul", "Rodriguez", 19, true));

        books.forEach(bookDao::save);
        clients.forEach(clientDao::save);

        bookDao.showDetails(books, "All books");
        bookDao.showDetails(clients, "All clients");

        clientDao.borrowBook(1L, 2L);
        clientDao.borrowBook(1L, 3L);
        clientDao.borrowBook(1L, 4L);

        clientDao.borrowBook(2L, 1L);
        clientDao.borrowBook(2L, 1L);
        clientDao.borrowBook(2L, 4L);

        clientDao.borrowBook(3L, 6L);
        clientDao.borrowBook(3L, 9L);
        clientDao.borrowBook(3L, 8L);

        clientDao.borrowBook(4L, 7L);
        clientDao.borrowBook(4L, 5L);
        clientDao.borrowBook(4L, 1L);

        for (Client client : clients) {
            System.out.println(clientDao.get(client.getId()));
        }

        // Spring Data

        BookRepository bookRepo = ctx.getBean(BookRepository.class);

        System.out.println("\nStephen Prata books:");
        bookRepo.findAllOrOrderByAuthor("Stephen Prata").forEach(System.out::println);

        System.out.println("\nCay S. Horstmann books:");
        bookRepo.findAllOrOrderByAuthor("Cay S. Horstmann").forEach(System.out::println);

        System.out.println("\nAvailable books: " + bookRepo.findAllByAvailableTrue());
        System.out.println("\nTitle length >= 30: " + bookRepo.findAllByTitleGreaterThan());
        System.out.println("\nAll books contains (givenValue): " + bookRepo.findAllByTitleContains("C++"));


        ClientRepository clientRepo = ctx.getBean(ClientRepository.class);

        System.out.println("\nPeople id's who are students: " + clientRepo.findByStudentTrue());
        System.out.println("\nNames in order asc by age: " + clientRepo.findLastNameOrderByAge());
        System.out.println("\nPeople age less than (givenParam): " + clientRepo.findLastNameAgeLessEqualThan(20));
        System.out.println("\nShow person where id is equal to (givenParam): " + clientRepo.findFirstById(2L));

        System.out.println("\nAll names starts with letter 'J':");
        clientRepo.findAllByFirstNameLike("J%").forEach(System.out::println);

        System.out.println("\nAll names starts with letter '(givenParam)': " +
                clientRepo.findAllByFirstNameStartingWithLetter('P'));


        ctx.close();
    }
}
