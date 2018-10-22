package app;

import dao.BookDao;
import dao.ClientDao;
import model.Book;
import model.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "dao")
@EntityScan(basePackages = "model")
public class SpringJpaApplication {

    private static List<Client> clients = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(SpringJpaApplication.class, args);
        BookDao bookDao = ctx.getBean(BookDao.class);
        ClientDao clientDao = ctx.getBean(ClientDao.class);

        books.add(new Book("9781250104625", "Pablo Escobar: My Father", "Juan Pablo Escobar"));
        books.add(new Book("9781402894626", "How to disapear? Simple tutorial", "Michael Scofield"));
        books.add(new Book("2345464634223", "Java for beginners", "Cay S. Horstmann"));
        books.add(new Book("9734345341214", "Zawód: Programista", "Maciej Aniserowicz"));
        books.add(new Book("9567657956934", "Junior Developer", "Mateusz Kupilas"));
        books.add(new Book("2349034589935", "Clean Code", "Robert C. Martin"));
        books.add(new Book("4040404040404", "Solve problems tutorial", "Zombie Developer"));
        books.add(new Book("9999999999999", "C++ Language", "Stephen Prata"));
        books.add(new Book("9343943941213", "Ronnie O'Sulivan biography", "Ronnie O'Sulivan"));

        clients.add(new Client("Pablo", "Escobar", 30, false));
        clients.add(new Client("Michael", "Scofield", 25, true));
        clients.add(new Client("John", "Abruzzi", 32, false));
        clients.add(new Client("Albert", "Einstein", 50, true));

        bookDao.showDetails(books, "All books");
        bookDao.showDetails(clients, "All clients");

        ////////////////////////////////////////////////////////////////////////////////////////////

        books.forEach(bookDao::save);
        clients.forEach(clientDao::save);

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

        ctx.close();
    }
}
