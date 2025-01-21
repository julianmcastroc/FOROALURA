package com.finalboss.foroalura.controller;

import com.finalboss.foroalura.domain.curso.Curso;
import com.finalboss.foroalura.domain.curso.dto.ActualizarCursoDTO;
import com.finalboss.foroalura.domain.curso.dto.CrearCursoDTO;
import com.finalboss.foroalura.domain.curso.dto.DetalleCursoDTO;
import com.finalboss.foroalura.domain.curso.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name="bearer-key")
@Tag( name= "cursos", description = "Puede pertenecer a muchas de las categorias definidas")
public class CursoController {
    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    @Operation(summary = "Registrar un nuevo curso en la BD.")
    public ResponseEntity<DetalleCursoDTO> crearTopico(@RequestBody @Valid CrearCursoDTO crearCursoDTO, //Para indicar a spring que es un parametro se usa requestBody y @Valid valida que los datos en DatosRegistroMédico todo sea válido, lleguen correctamente
                                                       UriComponentsBuilder uriBuilder){  // Genera la URL URI a retornar donde esta el registro creado
        Curso curso = new Curso(crearCursoDTO); //crearCursoDTO debe estar definido en la clase Curso como constructor con lo parametros a mostrar
        repository.save(curso);
        var uri = uriBuilder.path("/cursos/{i}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalleCursoDTO(curso));

    }
    //Mostrar todos los cursos
    @GetMapping("/all")
    @Operation(summary = "Lee todos los cursos independientemente de su estado")
    public ResponseEntity<Page<DetalleCursoDTO>> listarCursos(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        var pagina = repository.findAll(pageable).map(DetalleCursoDTO::new);
        return ResponseEntity.ok(pagina);
    }

    //Mostrar cursos Activo
    @GetMapping
    @Operation(summary = "Lista de cursos activos")
    public ResponseEntity<Page<DetalleCursoDTO>> listarCursosActivos(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        var pagina = repository.findAll(pageable).map(DetalleCursoDTO::new);
        return ResponseEntity.ok(pagina);
    }

    //Mostrar curso por Id
    @GetMapping("/{id}")
    @Operation(summary = "Lee un solo curso por su ID")
    public ResponseEntity<DetalleCursoDTO> ListarUnCurso(@PathVariable Long id){
        Curso curso = repository.getReferenceById(id);
        var datosDelCurso = new DetalleCursoDTO(
                curso.getName(),
                curso.getId(),
                curso.getCategoria(),
                curso.getActivo()
        );
        return ResponseEntity.ok(datosDelCurso);
    }

    //Actualizar un Curso

    @PutMapping("/{id}")
    @jakarta.transaction.Transactional
    @Operation(summary = "Actualiza el nombre, la categoría o el estado de un curso")
    public ResponseEntity<DetalleCursoDTO> actualizarCurso(@RequestBody @Valid ActualizarCursoDTO actualizarCursoDTO, @PathVariable Long id){

        Curso curso = repository.getReferenceById(id);

        curso.actualizarCurso(actualizarCursoDTO);

        var datosDelCurso = new DetalleCursoDTO(
                curso.getName(),
                curso.getId(),
                curso.getCategoria(),
                curso.getActivo()
        );
        return ResponseEntity.ok(datosDelCurso);
    }


    //Eliminar curso - Cambia el estado de activo true a false.
    @DeleteMapping("/{id}")
    @jakarta.transaction.Transactional
    @Operation(summary = "Elimina un curso")
    public ResponseEntity<?> eliminarCurso(@PathVariable Long id){
        Curso curso = repository.getReferenceById(id);
        curso.eliminarCurso();
        return ResponseEntity.noContent().build();
    }

}
