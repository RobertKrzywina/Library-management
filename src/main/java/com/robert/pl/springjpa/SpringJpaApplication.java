package com.robert.pl.springjpa;

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
        ClientDao clientDao = ctx.getBean(ClientDao.class);

        clients.add(new Client("Pablo", "Escobar", 30, false));
        clients.add(new Client("Michael", "Scofield", 25, true));
        clients.add(new Client("John", "Abruzzi", 32, false));
        clients.add(new Client("Albert", "Einstein", 50, true));

        clients.forEach(clientDao::save);

        for(long i=1; i<clients.size()+1; i++) {
            System.out.println(clientDao.get(i));
        }

        ////////////////////////////////////////////////////////////////////////////////////////////

        BookDao bookDao = ctx.getBean(BookDao.class);

        books.add(new Book("9783598215018", "How to do COCAIN", "Pablo Escobar"));
        books.add(new Book("9781402894626", "Escape from prison", "Michael Scofield"));
        books.add(new Book("9782402823423", "Java for beginners", "Cay S. Hostman"));
        books.add(new Book("9734345341214", "Zawód: Programista", "Maciej Aniserowicz"));

        books.forEach(bookDao::save);

        for(long i=1; i<books.size()+1; i++) {
            System.out.println(bookDao.get(i));
        }


        ctx.close();
    }
}
