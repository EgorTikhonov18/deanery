package egor.deanery.user.controller;


import egor.deanery.user.dto.UserDto;
import egor.deanery.user.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "Контроллер авторизации и аутентификации пользователей")
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    @Operation(
            summary = "Регистрация пользователя",
            description = "Позволяет зарегистрировать пользователя"
    )

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok(authenticationService.signUp(userDto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    @Operation(
            summary = "Авторизация пользователя",
            description = "Позволяет авторизировать пользователя"
    )
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok(authenticationService.signIn(userDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

}

