package inc.star.marketproduct.Model;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor

public class Category {

  private long id;
  private Date createdDate;
  private boolean isActive;
  private String name;

}
