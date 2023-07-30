package location_vector_mgmt.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import location_vector_mgmt.model.master.LocationVector;

@Repository("locationVectorReadRepo")
public interface LocationVectorRead_Repo extends JpaRepository<LocationVector, Long> 
{
	@Query(value = "SELECT * FROM PLACE_LOCATION_VECTORS a WHERE a.location_seq_no in :ids order by location_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<LocationVector> getSelectLocationVectorDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value = "SELECT * FROM PLACE_LOCATION_VECTORS a WHERE upper(trim(a.location_id)) in :ids order by location_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<LocationVector> getSelectLocationsByIds(@Param("ids") CopyOnWriteArrayList<String> ids);

	@Query(value = "SELECT coalesce(a.PLACE_VECTOR,' ') FROM PLACE_LOCATION_VECTORS a WHERE a.location_seq_no in :ids  order by location_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<String> getSelectLocationVectors(@Param("ids") CopyOnWriteArrayList<Long> ids);

}