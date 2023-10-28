package net.ausiasmarch.foxforumserver.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import net.ausiasmarch.foxforumserver.entity.PeliculaEntity;

import net.ausiasmarch.foxforumserver.service.PeliculaService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pelicula")
public class PeliculaApi {
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(peliculaService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody PeliculaEntity pelicula) {
        return ResponseEntity.ok(peliculaService.create(pelicula));
    }

    @PutMapping("")
    public ResponseEntity<PeliculaEntity> update(@RequestBody PeliculaEntity pelicula) {
        return ResponseEntity.ok(peliculaService.update(pelicula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(peliculaService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<PeliculaEntity>> getPage(Pageable pageable) {
        return ResponseEntity.ok(peliculaService.getPage(pageable));
    }

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(peliculaService.populate(amount));
    }
}
