package location_vector_mgmt.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import location_vector_mgmt.model.dto.LocationVector_DTO;
import location_vector_mgmt.model.master.LocationVector;
import location_vector_mgmt.model.repo.read.LocationVectorRead_Repo;

@Service("locationVectorReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class LocationVectorRead_Service implements I_LocationVectorRead_Service {

	@Autowired
	private LocationVectorRead_Repo locationVectorReadRepo;

	@Autowired
	private Executor asyncExecutor;

	@Override
	public CompletableFuture<CopyOnWriteArrayList<LocationVector_DTO>> getAllLocationVectors() 
	{
		CompletableFuture<CopyOnWriteArrayList<LocationVector_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<LocationVector> placeList = (CopyOnWriteArrayList<LocationVector>) locationVectorReadRepo
					.findAll();
			CopyOnWriteArrayList<LocationVector_DTO> lMasters = new CopyOnWriteArrayList<LocationVector_DTO>();
			lMasters = placeList != null ? this.getLocationVectorDTOs(placeList) : null;
			return lMasters;
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<LocationVector_DTO>> getSelectLocationVectorDetails(
			CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<LocationVector_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<LocationVector> lMasters = locationVectorReadRepo.getSelectLocationVectorDetails(ids);
			CopyOnWriteArrayList<LocationVector_DTO> locationVector_DTOs = new CopyOnWriteArrayList<LocationVector_DTO>();
			locationVector_DTOs = lMasters != null ? this.getLocationVectorDTOs(lMasters) : null;
			return locationVector_DTOs;
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<LocationVector_DTO>> getSelectLocationsByIds(
			CopyOnWriteArrayList<String> ids) {
		CompletableFuture<CopyOnWriteArrayList<LocationVector_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<LocationVector> lMasters = locationVectorReadRepo.getSelectLocationsByIds(ids);
			CopyOnWriteArrayList<LocationVector_DTO> locationVector_DTOs = new CopyOnWriteArrayList<LocationVector_DTO>();
			locationVector_DTOs = lMasters != null ? this.getLocationVectorDTOs(lMasters) : null;
			return locationVector_DTOs;
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<String>> getSelectLocationVectors(CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<String>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<String> lVectors = locationVectorReadRepo.getSelectLocationVectors(ids);
			return lVectors;
		}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<LocationVector_DTO> getLocationVectorDTOs(
			CopyOnWriteArrayList<LocationVector> lMasters) {
		LocationVector_DTO lDTO = null;
		CopyOnWriteArrayList<LocationVector_DTO> lMasterDTOs = new CopyOnWriteArrayList<LocationVector_DTO>();
		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getLocationVectorDTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized LocationVector_DTO getLocationVectorDTO(LocationVector lMaster) {
		LocationVector_DTO lDTO = new LocationVector_DTO();
		lDTO.setLocationSeqNo(lMaster.getLocationSeqNo());
		lDTO.setPlaceVector(lMaster.getPlaceVector());
		lDTO.setMapidSeqNo(lMaster.getMapidSeqNo());
		lDTO.setSpecificationSeqNo(lMaster.getSpecificationSeqNo());
		lDTO.setLocationId(lMaster.getLocationId());
		return lDTO;
	}

}