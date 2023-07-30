package location_vector_mgmt.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import location_vector_mgmt.model.dto.LocationVector_DTO;
import location_vector_mgmt.service.cud.I_LocationVectorCUD_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/locationVectorCUDManagement")
public class LocationVectorCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(LocationVector_Controller.class);

	@Autowired
	private I_LocationVectorCUD_Service locationVectorCUDServ;
	
	@PostMapping("/new")
	public ResponseEntity<LocationVector_DTO> newLocationVector(@RequestBody LocationVector_DTO locationVectorDTO) 
	{
		CompletableFuture<LocationVector_DTO> future = locationVectorCUDServ.newLocationVector(locationVectorDTO);
		LocationVector_DTO locationVectorDTO2=null;
		try {
			locationVectorDTO2 = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(locationVectorDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updLocationVector")
	public void updateLocationVector(@RequestBody LocationVector_DTO LocationVectorDTO) 
	{
		locationVectorCUDServ.updLocationVector(LocationVectorDTO);	
		return;
	}

	@DeleteMapping("/delSelectLocationVector")
	public void deleteSelectLocationVector(@RequestBody CopyOnWriteArrayList<Long> locationVectorSeqNoList) 
	{
		locationVectorCUDServ.delSelectLocationVectorDetails(locationVectorSeqNoList);
		return;
	}
	
	@DeleteMapping("/delSelectLocationVectorsByIds")
	public void deleteSelectLocationVectorsByIds(@RequestBody CopyOnWriteArrayList<String> locationVectorIds) 
	{
		locationVectorCUDServ.delSelectLocationsByIds(locationVectorIds);
		return;
	}
	
	@DeleteMapping("/delAllLocationVector")
	public void deleteAllLocationVectors() {
		locationVectorCUDServ.delAllLocationVectors();
		return;
	}
	}