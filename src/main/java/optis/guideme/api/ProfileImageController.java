package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewProfileImageDto;
import optis.guideme.api.dto.response.ProfileImageUploadDto;
import optis.guideme.api.mapper.CurrentUserProvider;
import optis.guideme.application.S3PresignService;
import optis.guideme.domain.exception.ForbiddenException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/profile-images")
@RequiredArgsConstructor
public class ProfileImageController {

    private final S3PresignService service;
    private final CurrentUserProvider currentUserProvider;

    private static final Map<String, String> EXTENSIONS = Map.of(
            "image/png", ".png",
            "image/jpeg", ".jpg",
            "image/webp", ".webp",
            "image/svg+xml", ".svg"
    );

    @PostMapping("/upload-image")
    public ProfileImageUploadDto getUploadUrl(@RequestBody NewProfileImageDto newProfileDto) {
        String extension = EXTENSIONS.get(newProfileDto.contentType());

        if (extension == null) {
            throw new ForbiddenException("ERROR: Invalid file type: " + newProfileDto.contentType());
        }

        String key = "profile-images/" + currentUserProvider.getUserId() + "/" + UUID.randomUUID() + extension;
        String url = service.generateUploadUrl(key);

        return new ProfileImageUploadDto(url, key);
    }
}