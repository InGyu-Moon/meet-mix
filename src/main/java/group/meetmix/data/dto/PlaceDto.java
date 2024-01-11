package group.meetmix.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceDto {
    private Long id;
    private int type;
    private String location;
    private String name;
    private String address;
    private String roadAddress;
    private String phone;
    private String url;
    private double x;
    private double y;
    private String imgUrl;
}
