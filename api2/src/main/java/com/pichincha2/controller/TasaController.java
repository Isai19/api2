package com.pichincha2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.session.SessionInformation;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha2.model.Tasa;
import com.pichincha2.model.TasaCambio;
import com.pichincha2.repo.TasaRepo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/tasa")

public class TasaController {

	@Autowired	
	private TasaRepo tasaRepo;

	@Operation(summary = "This method is used to get the clients.")
	@GetMapping("/list")
	public List<Tasa> tasas() {
		return (List<Tasa>) tasaRepo.findAll();
	}

	@PostMapping("/insert")
	public void insertar(@RequestBody Tasa tasa) {
		tasaRepo.save(tasa);
	}
	
	@PutMapping("/update/{id}")
	public void modificar(@PathVariable Integer id, @RequestBody Tasa tasa) throws Exception {
        Tasa tasaupadte = tasaRepo.findById(tasa.getId())
                .orElseThrow(() -> new Exception("Tasa not exist with id: "+id));

        tasaupadte.setMoneda1(tasa.getMoneda1());
        tasaupadte.setMoneda2(tasa.getMoneda2());
        tasaupadte.setTipoCambio(tasa.getTipoCambio());

        tasaRepo.save(tasaupadte);
	}
	
	@DeleteMapping("/delete/{id}")
	public void eliminar(@PathVariable Integer id){
		tasaRepo.deleteById(id);
	}
	
	@GetMapping("/buscarId/{id}")
	public Optional<Tasa> findById(@PathVariable Integer id){
		return tasaRepo.findById(id);
	}	

	@GetMapping("/tasacambio/{monedaO}/{monedaD}/{monto}")
	public TasaCambio TasaChangue(@PathVariable String monedaO, @PathVariable String monedaD, @PathVariable Integer monto){
		
		
		Tasa tasa = tasaRepo.findTasa(monedaO, monedaD);
		double calculado = monto*tasa.getTipoCambio();
		
		TasaCambio tc = new TasaCambio();
		tc.setMonedaO(monedaO);
		tc.setMonedaD(monedaD);
		tc.setConversion(tasa.getTipoCambio());		
		tc.setMonto(monto);
		tc.setCalculado(calculado);
		
		return tc;
	}

	/*
	@GetMapping("/session")
	public ResponseEntity<?> getDetailsSession(){
		
		String sessionId = "";
		User userObject = null;
		
		List<Object> sessions = sessionRegistry.getAllPrincipals();
		
		for(Object session : sessions){
			if(session instanceof User) {
				userObject = (User) session;
			}
			
			List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(session, false);
			
			for(SessionInformation sessionInformation : sessionInformations) {
				sessionId = sessionInformation.getSessionId();
			}
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("response", "helloworld");
		response.put("sessionId", sessionId);
		response.put("sessionUser", userObject);
		
		return ResponseEntity.ok(response);
		
	}*/

}
