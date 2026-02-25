package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      // чтобы не плодить дубли при каждом запуске
      if (userService.listUsers().isEmpty()) {
         User u1 = new User("User1", "Lastname1", "user1@mail.ru");
         u1.setCar(new Car("BMW", 5));
         userService.add(u1);

         User u2 = new User("User2", "Lastname2", "user2@mail.ru");
         u2.setCar(new Car("Audi", 7));
         userService.add(u2);

         User u3 = new User("User3", "Lastname3", "user3@mail.ru");
         u3.setCar(new Car("Toyota", 3));
         userService.add(u3);

         User u4 = new User("User4", "Lastname4", "user4@mail.ru");
         u4.setCar(new Car("BMW", 1));
         userService.add(u4);
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      User owner = userService.findUserByCar("BMW", 5);
      System.out.println("Owner of BMW 5: " + owner);

      context.close();
   }
}