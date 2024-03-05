package kz.iitu.watch.domain.link.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "link")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long createdDate;
    private String name;
    private String webUrl;
    private String imageUrl;


    public Link(Long userId, Long createdDate, String name, String webUrl, String imageUrl) {
        this.userId = userId;
        this.createdDate = createdDate;
        this.name = name;
        this.webUrl = webUrl;
        this.imageUrl = imageUrl;
    }
}
