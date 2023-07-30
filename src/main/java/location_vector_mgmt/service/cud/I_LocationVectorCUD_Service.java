package location_vector_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import location_vector_mgmt.model.dto.LocationVector_DTO;

public interface I_LocationVectorCUD_Service
{
    public abstract CompletableFuture<LocationVector_DTO> newLocationVector(LocationVector_DTO locationVector_DTO);
    public abstract CompletableFuture<Void> updLocationVector(LocationVector_DTO LocationVector_DTO);
    public abstract CompletableFuture<Void> delAllLocationVectors();    
    public CompletableFuture<Void> delSelectLocationVectorDetails(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<Void> delSelectLocationsByIds(CopyOnWriteArrayList<String> ids);    
}