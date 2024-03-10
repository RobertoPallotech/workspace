package com.examen.examenpractico.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.examenpractico.exception.ResourceNotFoundException;
import com.examen.examenpractico.model.Paciente;
import com.examen.examenpractico.repository.PacienteRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	//Obtener Todos los Pacientes
	@GetMapping("/")
	public List<Paciente> ListarPacientes(){
		return pacienteRepository.findAll();	
	}
	
	//Modificar un Paciente
	@PostMapping("/")
	public Paciente registrar(@RequestBody Paciente paciente) {
		return pacienteRepository.save(paciente);
		}
	
	//Buscar Paciente por Id
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> buscarById(@PathVariable Long id){
		Paciente paciente = pacienteRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("No existe el Paciente con el id:"+id));
		return ResponseEntity.ok(paciente);
	}
	
	//Eliminar Paciente por id
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarPaciente(@PathVariable Long id){
		Paciente paciente = pacienteRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("No existe el Paciente con el id:"+id));
		pacienteRepository.delete(paciente);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	//Actualizar Paciente
	@PutMapping("/{id}")
	public ResponseEntity<Paciente> actualizarPaciente(@PathVariable long id, @RequestBody Paciente pacienteRequest){
		Paciente paciente = pacienteRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("No existe el Paciente con el id:"+id));
		paciente.setNombre(pacienteRequest.getNombre());
		paciente.setApellido(pacienteRequest.getApellido());
		paciente.setCelular(pacienteRequest.getCelular());
		paciente.setEmail(pacienteRequest.getEmail());
		paciente.setEstado(pacienteRequest.getEstado());
		
		Paciente pacienteActualizado = pacienteRepository.save(paciente);
		return ResponseEntity.ok(pacienteActualizado);
	}
	
}
