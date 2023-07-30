package location_vector_mgmt.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import location_vector_mgmt.model.dto.LocationVector_DTO;
import location_vector_mgmt.service.read.I_LocationVectorRead_Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/locationVectorReadManagement")
public class LocationVectorRead_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(LocationVector_Controller.class);

	@Autowired
	private I_LocationVectorRead_Service locationVectorReadServ;

	@GetMapping(value = "/getAllLocationVectors", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<LocationVector_DTO>> getAllLocationVectors() {
		CompletableFuture<CopyOnWriteArrayList<LocationVector_DTO>> future = locationVectorReadServ
				.getAllLocationVectors();
		CopyOnWriteArrayList<LocationVector_DTO> locationVector_DTOs = null;
		try {
			locationVector_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(locationVector_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectLocationVectorsDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<LocationVector_DTO>> getSelectLocationVectorDetails(
			@RequestBody CopyOnWriteArrayList<Long> locationVectorSeqNos) {
		CompletableFuture<CopyOnWriteArrayList<LocationVector_DTO>> future = locationVectorReadServ
				.getSelectLocationVectorDetails(locationVectorSeqNos);
		CopyOnWriteArrayList<LocationVector_DTO> locationVector_DTOs = null;
		try {
			locationVector_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(locationVector_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectLocationVectors", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<String>> getSelectLocationVectors(
			@RequestBody CopyOnWriteArrayList<Long> locationVectorSeqNos) {
		CompletableFuture<CopyOnWriteArrayList<String>> future = locationVectorReadServ
				.getSelectLocationVectors(locationVectorSeqNos);
		CopyOnWriteArrayList<String> locationVector_DTOs = null;
		try {
			locationVector_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(locationVector_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectLocationVectorsDetailsbyIds", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<LocationVector_DTO>> getSelectLocationVectorDetailsbyIds(
			@RequestBody CopyOnWriteArrayList<String> locationIds) {
		CompletableFuture<CopyOnWriteArrayList<LocationVector_DTO>> future = locationVectorReadServ
				.getSelectLocationsByIds(locationIds);
		CopyOnWriteArrayList<LocationVector_DTO> locationVector_DTOs = null;
		try {
			locationVector_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(locationVector_DTOs, HttpStatus.OK);
	}

}