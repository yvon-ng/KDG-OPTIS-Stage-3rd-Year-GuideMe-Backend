package optis.guideme.api.mapper;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewUserDto;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.application.S3PresignService;
import optis.guideme.domain.user.Consultant;
import optis.guideme.domain.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class UserMapper {
    public static final String S3_BASE_URL = "https://stg-guide-me.s3.eu-west-1.amazonaws.com/";
    private final S3PresignService s3PresignService;


    public UserDto toResponseDto(User user) {
        String clientName  = null; // null when not a consultant
        LocalDateTime startDateAtClient = null; // null when not a consultant

        if (user instanceof Consultant consultant) {
            clientName  = consultant.getClientName();
            startDateAtClient = consultant.getStartDateAtClient();
        }

        return new UserDto(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                clientName,
                startDateAtClient,
                s3PresignService.generateGetUrl(user.getAvatar()),
                user.getRole()
        );
    }

    private String mapToS3Url(String avatarDbValue){
       return String.format("%s%s", S3_BASE_URL, avatarDbValue);
    }
}
