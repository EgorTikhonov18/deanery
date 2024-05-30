package egor.deanery.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Запрос с данными пользоветеля для авторизации")
public class ShortUserDto {
    private String username;
    private String password;
}
