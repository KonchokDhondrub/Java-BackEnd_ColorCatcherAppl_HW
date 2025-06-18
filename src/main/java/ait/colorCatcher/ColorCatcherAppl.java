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
            throw  new IllegalArgumentException("Please type your API KEY");
        }
        if (args.length == 1) {
            String auth = args[0];
            String imgUrl = "https://imagga.com/static/images/tagging/wind-farm-538576_640.jpg";
        }
        if (args.length == 2) {
            String auth = args[0];
            String imgUrl = args[1];
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", auth);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.imagga.com/v2/colors").queryParam("image_url", imgUrl);

        URI url = builder.build().toUri();
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, url);

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
