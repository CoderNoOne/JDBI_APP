package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesStand {

  private Integer id;
  private Integer customerId;
  private Integer movieId;
  private LocalDate startDateTime; // LocalDateTime

}
