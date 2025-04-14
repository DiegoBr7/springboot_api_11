package br.edu.senaisp.colegio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.senaisp.colegio.exception.RecursoNotFound;
import br.edu.senaisp.colegio.model.Usuario;
import br.edu.senaisp.colegio.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usRepo;

	public Usuario gravarUsuario(Usuario u) {
		try {
			return usRepo.save(u);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel inserir o usuario");
		}

	};

	public List<Usuario> listarUsuario() {
		return usRepo.findAll();
	}

	public Usuario usuarioPorId(Long id) {
		Optional<Usuario> userId = usRepo.findById(id);

		return userId.orElseThrow(() -> new RecursoNotFound("Não encontrado"));
	}

	public Usuario alterarPorId(Usuario u , Long id) {
		Optional<Usuario> userId = usRepo.findById(id);
		if (userId.isPresent()) {
			u.setId(id);
			return usRepo.save(u);
		} else {
			throw new RecursoNotFound("Identificador nao encontrado");
		}
	}

	public Usuario deletarPorId(Long id) {

		try {
			Usuario a = usuarioPorId(id);
			if(a != null){
				usRepo.deleteById(id);
				a = usuarioPorId(id);
				if(a == null)
					return  a ;
				throw new RuntimeException("Nao foi possivel excluir");
			}
		}catch (Exception e){
			throw  new RuntimeException("Error: " + e.getMessage());
		}
		return  null;
	}
}
