package location_vector_mgmt.model.repo.cud;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import location_vector_mgmt.model.master.LocationVector;

@Repository("locationVectorCUDRepo")
public interface LocationVectorCUD_Repo extends JpaRepository<LocationVector, Long> 
{
	@Modifying
	@Query(value = "DELETE FROM PLACE_LOCATION_VECTORS a WHERE a.location_seq_no in :ids", nativeQuery = true)
	void delSelectLocationVectorDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Modifying
	@Query(value = "DELETE FROM PLACE_LOCATION_VECTORS a WHERE upper(trim(a.location_id)) in :ids order by location_seq_no", nativeQuery = true)
	void delSelectLocationsByIds(@Param("ids") CopyOnWriteArrayList<String> ids);
	
}