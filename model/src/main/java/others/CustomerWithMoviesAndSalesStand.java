package others;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWithMoviesAndSalesStand {

  private Integer customerId;
  private String customerName;
  private String customerSurname;
  private Integer customerAge;
  private String customerEmail;
  private Integer customerLoyaltyCardId;
  private String movieTitle;
  private String movieGenre;
  private BigDecimal ticketPrice;
  private Integer movieDuration;
  private LocalDate movieReleaseDate;
  private LocalDateTime startDateTime;
}
