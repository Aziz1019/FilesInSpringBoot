package inc.star.marketproduct.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {

  private long id;
  private String productName;
  private double count;
  private long categoryId;
  private Date createdDate;
  private boolean isActive;


}
