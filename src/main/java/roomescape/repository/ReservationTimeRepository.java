package roomescape.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import roomescape.model.ReservationTime;

import java.time.LocalTime;

public interface ReservationTimeRepository extends JpaRepository<ReservationTime, Long> {

    boolean existsByStartAt(LocalTime startAt);
}
