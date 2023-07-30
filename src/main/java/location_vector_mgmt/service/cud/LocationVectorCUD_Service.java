package location_vector_mgmt.service.cud;

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
import location_vector_mgmt.model.repo.cud.LocationVectorCUD_Repo;

@Service("locationVectorCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class LocationVectorCUD_Service implements I_LocationVectorCUD_Service {

	@Autowired
	private LocationVectorCUD_Repo locationVectorCUDRepo;

	@Autowired
	private Executor asyncExecutor;

	@Override
	public CompletableFuture<LocationVector_DTO> newLocationVector(LocationVector_DTO lMaster) {
		CompletableFuture<LocationVector_DTO> future = CompletableFuture.supplyAsync(() -> {
			LocationVector_DTO locationVectorDTO = null;
			if (!locationVectorCUDRepo.existsById(lMaster.getLocationSeqNo())) {
				locationVectorDTO = this
						.getLocationVectorDTO(locationVectorCUDRepo.save(this.setLocationVector(lMaster)));
			}
			return locationVectorDTO;
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<Void> updLocationVector(LocationVector_DTO lMaster) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (locationVectorCUDRepo.existsById(lMaster.getLocationSeqNo())) {
				LocationVector locationVector = this.setLocationVector(lMaster);
				locationVector.setLocationSeqNo(lMaster.getLocationSeqNo());
				locationVectorCUDRepo.save(locationVector);
			}
			return;
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<Void> delSelectLocationVectorDetails(CopyOnWriteArrayList<Long> placeSeqNos) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (placeSeqNos != null) {
				locationVectorCUDRepo.delSelectLocationVectorDetails(placeSeqNos);
			}
			return;
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<Void> delAllLocationVectors() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			locationVectorCUDRepo.deleteAll();
			;
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectLocationsByIds(CopyOnWriteArrayList<String> ids) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (ids != null) {
				locationVectorCUDRepo.delSelectLocationsByIds(ids);
			}
			return;
		}, asyncExecutor);
		return future;
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

	private synchronized LocationVector setLocationVector(LocationVector_DTO lDTO) {
		LocationVector lMaster = new LocationVector();
		lMaster.setPlaceVector(lDTO.getPlaceVector());
		lMaster.setMapidSeqNo(lDTO.getMapidSeqNo());
		lMaster.setSpecificationSeqNo(lDTO.getSpecificationSeqNo());
		lMaster.setLocationId(lDTO.getLocationId());
		return lMaster;
	}
}