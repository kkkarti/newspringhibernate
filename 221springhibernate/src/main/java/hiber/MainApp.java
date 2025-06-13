package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 111));
        User user2 = new User("User2", "Lastname2", "user2@mail.ru", new Car("Lada", 228));
        User user3 = new User("User3", "Lastname3", "user3@mail.ru", new Car("Suzuki", 333));
        User user4 = new User("User4", "Lastname4", "user4@mail.ru", new Car("Honda", 444));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + (user.getCar() != null
                    ? user.getCar().getModel() + " " + user.getCar().getSeries()
                    : "Не найдено :__("));
            System.out.println();
        }
        System.out.println("Поиск пользователя по машине: Lada 228");
        User foundUser = userService.getUserByCar("Lada", 228);
        System.out.println("Найден: " + foundUser.getFirstName() + " " + foundUser.getLastName());

        context.close();
    }
}
