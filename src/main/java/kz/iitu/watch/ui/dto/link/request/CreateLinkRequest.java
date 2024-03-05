package kz.iitu.watch.ui.dto.link.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLinkRequest {

    private String name;
    private String webUrl;
    private String imageUrl;

}
