package location_vector_mgmt.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import location_vector_mgmt.model.dto.LocationVector_DTO;

public interface I_LocationVectorRead_Service
{
	public abstract CompletableFuture<CopyOnWriteArrayList<LocationVector_DTO>> getAllLocationVectors();    
	public abstract CompletableFuture<CopyOnWriteArrayList<LocationVector_DTO>> getSelectLocationVectorDetails(CopyOnWriteArrayList<Long> ids);
	public abstract CompletableFuture<CopyOnWriteArrayList<LocationVector_DTO>> getSelectLocationsByIds(CopyOnWriteArrayList<String> ids);
	public abstract CompletableFuture<CopyOnWriteArrayList<String>> getSelectLocationVectors(CopyOnWriteArrayList<Long> ids);
}