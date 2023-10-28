package net.ausiasmarch.foxforumserver.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.ausiasmarch.foxforumserver.entity.AlquilerEntity;
import net.ausiasmarch.foxforumserver.service.AlquilerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/alquiler")
public class AlquilerApi {
    @Autowired
    private AlquilerService alquilerService;

    @GetMapping("/{id}")
    public ResponseEntity<AlquilerEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(alquilerService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody AlquilerEntity alquiler) {
        return ResponseEntity.ok(alquilerService.create(alquiler));
    }

    @PutMapping("")
    public ResponseEntity<AlquilerEntity> update(@RequestBody AlquilerEntity alquiler) {
        return ResponseEntity.ok(alquilerService.update(alquiler));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(alquilerService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<AlquilerEntity>> getPage(Pageable pageable) {
        return ResponseEntity.ok(alquilerService.getPage(pageable));
    }

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(alquilerService.populate(amount));
    }
}
