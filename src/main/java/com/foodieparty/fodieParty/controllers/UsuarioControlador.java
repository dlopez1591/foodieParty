package com.foodieparty.fodieParty.controllers;


import com.foodieparty.fodieParty.dtos.UsuarioDTO;

import com.foodieparty.fodieParty.models.Usuario;
import com.foodieparty.fodieParty.repositories.PedidoRepositorio;
import com.foodieparty.fodieParty.repositories.ReservaRepositorio;
import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;
import com.foodieparty.fodieParty.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private ReservaRepositorio reservaRepositorio;
    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/usuario")
    public List<UsuarioDTO> getUsuario(){
        return usuarioServicio.getUsuario();
    }


    @GetMapping("/usuario/{id}")
    public Optional<UsuarioDTO> getUsuarioPorId(@PathVariable Long id){
        return usuarioServicio.getUsuarioPorId(id);
    }


    @GetMapping("/usuario/autenticado")
    public UsuarioDTO getUsuarioAutenticado(Authentication authentication){
         return new UsuarioDTO(usuarioRepositorio.findByEmail(authentication.getName()));
    }


    @PostMapping("/crear/usuario")
    public ResponseEntity<Object> registrarUsuario(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String email, @RequestParam String password,@RequestParam String telefono) {
            return usuarioServicio.registrarUsuario(nombre,apellido,email,passwordEncoder.encode(password),telefono);

    }


 //   @PostMapping("/borrar/usuario")
   // public ResponseEntity<Object> borrarUsuario(@RequestParam long id){
     //   usuarioServicio.borrarUsuario(id);


    // revisar este metodo aparentemente no esta guardando en la base de datos!


    //metodo para editar un usuario OJO REVISAR Y VERIFICAR SERVICIOS E IMPLEMENTACION:

    @PutMapping("/actualizar/usuario")
    public ResponseEntity<Object> actualizarUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepositorio.findById(usuario.getId());
        if(usuarioExistente.isPresent()) {
            Usuario usuarioActualizado = usuarioExistente.get();
            usuarioActualizado.setNombre(usuario.getNombre());
            usuarioActualizado.setApellido(usuario.getApellido());
            usuarioActualizado.setEmail(usuario.getEmail());
            usuarioActualizado.setTelefono(usuario.getTelefono());
            usuarioRepositorio.save(usuarioActualizado);
            return new ResponseEntity<>("Usuario actualizado", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
    }

   /* @Transactional
    @DeleteMapping("/borrar/usuario")
    public ResponseEntity<Object> borrarUsuario(@RequestParam long id){
        Usuario usuario=usuarioRepositorio.findById(id).orElse(null);
        List<Pedido> pedidos=usuario.getPedidos().stream().collect(toList());
        List<Reserva> reservas=usuario.getReservas().stream().collect(toList());
        if (pedidos!=null){
            for (Pedido pedido:pedidos){
                pedidoRepositorio.deleteById(pedido.getId());
            }
        }
        if (reservas!=null){
            for (Reserva reserva:reservas){
                reservaRepositorio.deleteById(reserva.getId());
            }
        }
        usuarioRepositorio.deleteById(id);

        return new ResponseEntity<>("Usuario borrado", HttpStatus.ACCEPTED);
    }
    */

}
