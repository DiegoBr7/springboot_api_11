package br.edu.senaisp.colegio.controller;

import java.util.List;

import br.edu.senaisp.colegio.exception.RecursoNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.edu.senaisp.colegio.model.Usuario;
import br.edu.senaisp.colegio.service.UsuarioService;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usService ;
	
	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody Usuario u ){
		return ResponseEntity.ok(usService.gravarUsuario(u));
	}

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable Long id,@RequestBody Usuario u ){
        u = usService.alterarPorId(u , id);
		return ResponseEntity.status(HttpStatus.OK).body(u);
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id ){
		try{

			if (usService.deletarPorId(id))
				return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso !");
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi excluido");
			}
		} catch (Exception e){
			return ResponseEntity.badRequest().body("erro" + e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarioEntity(){
		return ResponseEntity.ok(usService.listarUsuario());
		
	}
	
	@GetMapping({"/{id}"})
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
	try {
		Usuario u = usService.usuarioPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(u);
	}catch (RecursoNotFound e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}
	}
}
