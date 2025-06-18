package ait.colorCatcher;

import ait.colorCatcher.dto.TagsResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class ColorCatcherAppl {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Please provide Basic Authentication Credentials");
        }

        String url = "https://api.imagga.com/v2/colors";
        String testImg = "https://imagga.com/static/images/tagging/wind-farm-538576_640.jpg";
        String auth = args[0].startsWith("Basic ") ? args[0] : "Basic " + args[0].trim();
        String imgUrl = args.length != 2 ? testImg : args[1];

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", auth);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("image_url", imgUrl);

        URI uri = builder.build().toUri();
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, uri);

        ResponseEntity<TagsResponseDto> response = restTemplate.exchange(request, TagsResponseDto.class);

        assert response.getBody() != null;
        System.out.println("Background colors:");
        response.getBody().getResult().getColors().getBackground_colors().forEach(System.out::println);

        System.out.println("\nForeground colors:");
        response.getBody().getResult().getColors().getForeground_colors().forEach(System.out::println);

        System.out.println("\nImage colors:");
        response.getBody().getResult().getColors().getImage_colors().forEach(System.out::println);
    }
}
