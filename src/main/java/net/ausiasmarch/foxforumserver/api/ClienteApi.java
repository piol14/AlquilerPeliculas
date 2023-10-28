package net.ausiasmarch.foxforumserver.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.ausiasmarch.foxforumserver.entity.ClienteEntity;
import net.ausiasmarch.foxforumserver.service.ClienteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cliente")
public class ClienteApi {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clienteService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody ClienteEntity cliente) {
        return ResponseEntity.ok(clienteService.create(cliente));
    }

    @PutMapping("")
    public ResponseEntity<ClienteEntity> update(@RequestBody ClienteEntity cliente) {
        return ResponseEntity.ok(clienteService.update(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clienteService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<ClienteEntity>> getPage(Pageable pageable) {
        return ResponseEntity.ok(clienteService.getPage(pageable));
    }

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(clienteService.populate(amount));
    }
}
