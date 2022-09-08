package inc.star.marketproduct.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResMessage {
    private int statusCode;
    private String message;
    private Object data;

    public ResMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
