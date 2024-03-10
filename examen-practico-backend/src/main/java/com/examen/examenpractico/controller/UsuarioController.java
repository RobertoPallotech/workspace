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
import com.examen.examenpractico.model.Usuario;
import com.examen.examenpractico.repository.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//Obtener Todos los Usuarios
	@GetMapping("/")
	public List<Usuario> ListarUsuarios(){
		return usuarioRepository.findAll();	
	}
	
	//Modificar un usuario
	@PostMapping("/")
	public Usuario registrar(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
		}
	
	//Buscar Usuario por Id
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarById(@PathVariable Long id){
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("No existe el usuario con el id:"+id));
		return ResponseEntity.ok(usuario);
	}
	
	//Eliminar Usuario por id
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable Long id){
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("No existe el usuario con el id:"+id));
		usuarioRepository.delete(usuario);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	//Actualizar Usuario
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable long id, @RequestBody Usuario usuarioRequest){
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("No existe el usuario con el id:"+id));
		usuario.setNombre(usuarioRequest.getNombre());
		usuario.setApellido(usuarioRequest.getApellido());
		usuario.setPasswd(usuarioRequest.getPasswd());
		usuario.setEmail(usuarioRequest.getEmail());
		usuario.setEstado(usuarioRequest.getEstado());
		
		Usuario usuarioActualizado = usuarioRepository.save(usuario);
		return ResponseEntity.ok(usuarioActualizado);
	}
	
}