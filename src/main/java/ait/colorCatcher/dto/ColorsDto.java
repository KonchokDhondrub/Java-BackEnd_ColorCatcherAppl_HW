package ait.colorCatcher.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ColorsDto {
    private List<ColorDetail> background_colors;
    private List<ColorDetail> foreground_colors;
    private List<ColorDetail> image_colors;

    @Getter
    public static class ColorDetail {
        private String closest_palette_color;
        private String closest_palette_color_parent;
        private double percent;

        @Override
        public String toString() {
            return String.format("Color: '%s', Children of: '%s', Coverage: %.2f%%", closest_palette_color, closest_palette_color_parent, percent);
        }
    }
}
