package roomescape.model.theme;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import roomescape.model.Reservation;
import roomescape.service.dto.ThemeDto;

import java.util.Objects;
import java.util.Set;

@Entity
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Valid
    @Embedded
    private Name name;
    @NotNull
    @Valid
    @Embedded
    private Description description;
    @NotNull
    @Valid
    @Embedded
    private Thumbnail thumbnail;
    @OneToMany(mappedBy = "theme")
    private Set<Reservation> reservations;

    private Theme(Long id, Name name, Description description, Thumbnail thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public Theme(Long id, String name, String description, String thumbnail) {
        this(id, new Name(name), new Description(description), new Thumbnail(thumbnail));
    }

    public Theme() {
    }

    public static Theme from(ThemeDto themeDto) {
        return new Theme(0L, themeDto.getName(), themeDto.getDescription(), themeDto.getThumbnail());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.getName();
    }

    public String getDescription() {
        return description.getDescription();
    }

    public String getThumbnail() {
        return thumbnail.getThumbnail();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Theme theme = (Theme) o;
        return id.equals(theme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, thumbnail);
    }
}
