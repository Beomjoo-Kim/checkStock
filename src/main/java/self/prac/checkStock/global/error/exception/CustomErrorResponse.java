package self.prac.checkStock.global.error.exception;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomErrorResponse {
    private CustomErrorCodes code;
    private String msg;
}
