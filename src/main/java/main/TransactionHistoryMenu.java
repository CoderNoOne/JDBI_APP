package main;

import exceptions.AppException;
import lombok.extern.slf4j.Slf4j;
import model.entity.Customer;
import repository.entity_repository.impl.CustomerRepository;
import repository.entity_repository.impl.LoyaltyCardRepository;
import repository.entity_repository.impl.MovieRepository;
import repository.entity_repository.impl.SalesStandRepository;
import repository.others.JoinedEntitiesRepository;
import service.entity_service.CustomerService;
import service.entity_service.LoyaltyCardService;
import service.entity_service.MovieService;
import service.entity_service.SalesStandService;
import service.others.JoinedEntitiesService;
import utils.UserDataUtils;

import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
class TransactionHistoryMenu {

  private final CustomerService customerService = new CustomerService(new CustomerRepository());
  private final MovieService movieService = new MovieService(new MovieRepository());
  private final LoyaltyCardService loyaltyCardService = new LoyaltyCardService(new LoyaltyCardRepository());
  private final SalesStandService salesStandService = new SalesStandService(new SalesStandRepository());
  private final JoinedEntitiesService joinedEntitiesService = new JoinedEntitiesService(new JoinedEntitiesRepository());

  //historia
  void menu() {

    menuOptions();
    while (true) {
      try {
        int option = UserDataUtils.getInt("\nINPUT YOUR OPTION: ");
        switch (option) {
          case 1 -> option1();
          case 2 -> option2();

          default -> throw new AppException("INPUT OPTION IS NOT DEFINED");
        }
      } catch (AppException e) {
        log.info(e.getExceptionMessage());
        log.error(Arrays.toString(e.getStackTrace()));
      }
    }
  }

  private void option1() {
    joinedEntitiesService.allMoviesBoughtSortedAlphabetically().forEach(System.out::println);
  }

  private void option2() {
    var allCustomers = customerService.showAllCustomers();
    int customerId;

    if (allCustomers.isEmpty()) {
      System.out.println("There are no customers in database yet");
      return;
    }

    do {
      customerId = UserDataUtils.getInt("Choose proper customer id from above list");
    } while (customerService.findCustomerById(customerId).isEmpty());

    var distinctMovies = joinedEntitiesService.allDistinctMoviesBoughtBySpecifiedCustomerSortedAlphabetically(customerId);

    if (distinctMovies.size() == 0) {
      System.out.println("Selected customer didn't bought any ticket yet");
    } else {
      System.out.println("Selected customer bought " + distinctMovies.size() + " tickets for different movies\n");
      AtomicInteger counter = new AtomicInteger(1);
      distinctMovies.forEach(movie -> System.out.println("No. " + counter.getAndIncrement() + ". " + movie));
    }
  }


  public void menuOptions() {
    System.out.println(MessageFormat.format(
            "\nOption no. 1 - {0}\n" +
                    "Option no. 2 - {1}\n" +
                    "Option no. 3 - {2}\n" +
                    "Option no. 4 - {3}\n" +
                    "Option no. 5 - {4}",

            "Movies ticket bought",
            "Movies bought by specified customer",
            "",
            "",
            ""
    ));

  }

}
