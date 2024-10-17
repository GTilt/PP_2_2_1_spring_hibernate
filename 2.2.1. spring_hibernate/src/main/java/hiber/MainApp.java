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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User userWithCar1 = new User("UserCar1", "LastnameCar1", "user1Car@mail.ru");
      User userWithCar2 = new User("UserCar2", "LastnameCar2", "user2Car@mail.ru");
      User userWithCar3 = new User("UserCar3", "LastnameCar3", "user3Car@mail.ru");
      Car car1 = new Car(userWithCar1, "Ferrari", 20);
      Car car2 = new Car(userWithCar2, "Mers", 30);
      Car car3 = new Car(userWithCar3, "BMW", 40);
      userWithCar1.setCar(car1);
      userWithCar2.setCar(car2);
      userWithCar3.setCar(car3);
      userService.add(userWithCar1);
      userService.add(userWithCar2);
      userService.add(userWithCar3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      List<User> user1 = userService.getUserByCar("Mers", 30);
      for (User user : user1) {
         System.out.println("Id = "+ user.getId());
         System.out.println("First Name = "+ user.getFirstName());
         System.out.println("Last Name = "+ user.getLastName());
         System.out.println("Email = "+ user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
