package br.edu.senaisp.colegio.model.mapper;

import br.edu.senaisp.colegio.model.Disciplina;
import br.edu.senaisp.colegio.model.dto.DisciplinaClassDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-13T21:33:39-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
public class DisciplinaStructMapperImpl implements DisciplinaStructMapper {

    @Override
    public Disciplina toDisciplina(DisciplinaClassDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Disciplina disciplina = new Disciplina();

        disciplina.setTitulo( dto.getNomeDisciplina() );
        disciplina.setDuracaoH( dto.getHoras() );
        disciplina.setId( dto.getId() );

        return disciplina;
    }

    @Override
    public DisciplinaClassDTO toDTO(Disciplina disciplina) {
        if ( disciplina == null ) {
            return null;
        }

        DisciplinaClassDTO disciplinaClassDTO = new DisciplinaClassDTO();

        disciplinaClassDTO.setId( disciplina.getId() );

        return disciplinaClassDTO;
    }
}
