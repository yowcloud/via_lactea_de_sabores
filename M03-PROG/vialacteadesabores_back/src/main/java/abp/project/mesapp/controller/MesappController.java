package abp.project.mesapp.controller;

import abp.project.mesapp.dao.MesaDao;
import abp.project.mesapp.dao.PlatoDao;
import abp.project.mesapp.mongo.TicketsDao;
import abp.project.mesapp.dao.UsuarioDao;
import abp.project.mesapp.model.*;
import abp.project.mesapp.mongo.Tickets;
import abp.project.mesapp.service.MesappService;
import abp.project.mesapp.util.CheckError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MesappController {

    @Autowired
    private MesappService mesappService;

    @Autowired
    public UsuarioDao usuarioDao;
    @Autowired
    public MesaDao mesaDao;

    public TicketsDao ticketsDao;

    public PlatoDao platoDao;

    //CHECK LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user) {
        System.out.println("ALGUIEN ESTA INTENTANDO ENTRAR!");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Contraseña: " + user.getContrasena());
        try {
            boolean loggedIn = usuarioDao.login(user.getEmail(), user.getContrasena());
            if (loggedIn) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(401).body("CredencialesIncorrectas");
            }
        } catch (CheckError e) {
            throw new RuntimeException(e);
        }
    }

    // REGISTAR USUARIO
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Usuario user) throws CheckError {
        System.out.println("Datos del nuevo cliente");
        System.out.println("Nombre: " + user.getNombre());
        System.out.println("Apellido1: " + user.getApellido1());
        System.out.println("Apellido2: " + user.getApellido2());
        System.out.println("Fecha de nacimiento: " + user.getFecha_nacimiento());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Fecha de registro: " + user.getFecha_registro());
        System.out.println("Telefono: " + user.getTelefono());
        System.out.println("Contraseña: " + user.getContrasena());
        try {
            boolean loggedIn = usuarioDao.register(user.getNombre(), user.getApellido1(), user.getApellido2(), user.getFecha_nacimiento(), user.getEmail(), user.getFecha_registro(), user.getTelefono(), user.getContrasena());
            if (loggedIn)
                return ResponseEntity.ok().build();
            else
                return ResponseEntity.badRequest().body("El correo electrónico ya está registrado.");

        } catch (CheckError e) {
            throw new CheckError(CheckError.ERROR_USER);
        }
    }

    //MOSTRAR MESAS llamndo a masappService
    @GetMapping("/mesapp/{id}")
    public ResponseEntity<?> getMesapp(@PathVariable("id") int option) throws CheckError {
        return mesappService.getMesapp(option);
    }

    //Mostrar info del perfil (no func)
    @GetMapping("/perfil")
    public ResponseEntity<Usuario> getPerfil(@RequestParam String email) {
        return mesappService.getPerfil(email);
    }

    /*
     * TODO:
     *  1. Duplica este trozo de código
     *  2. Reemplaza "XXX" por el objeto java (Mesa, Camarero, Usuario, ...)
     *  3. Reemplaza "Body body" por el objeto (el mismo que el paso 2) Mesa mesa, Camarero camarero, ...
     *  4. Pon tu clase Mesa, Camarero, Usuario en el package de Service para que se pueda importar
     *  5. Importalo
     *  6. Duplica el método "postXXX" dentro de "MesappService" reemplazando "XXX" igual que el paso 2
     *  7. Ya puedes llamar a la url: "http://localhost:8081/mesapp/XXX (donde "XXX" es el mismo que el paso 2
     * @PostMapping("/mesapp/Usuario")
    public ResponseEntity postXXXX(@PathVariable("id") int option,
                                     @RequestBody Body body) {
        return mesappService.postXXXX(option, body);
    }
     */

    //MOSTRAR HISTORIAL DE RESERVAS DE 1 CLIENTE
    @GetMapping("/peril/{id}")
    public ResponseEntity<?> getReservasHistorial(@PathVariable("id") int idCliente) throws CheckError {
        List<Cliente_Mesa> reservas = mesaDao.getReservasHistorial(idCliente);
        if (reservas != null) {
            return ResponseEntity.ok(reservas);
        } else {
            return ResponseEntity.status(401).body("Error al listar las reservas");
        }
    }

    //GET MONGO
//    @GetMapping("/mongo")
//    public ResponseEntity<?> comandaMongo(@RequestBody Tickets newTicket) throws CheckError {
//        System.out.println("Datos de mongoTicket");
//        System.out.println("Order : " + newTicket.getOrder());
//        System.out.println("Table: " + newTicket.getTable());
//        System.out.println("Waiter: " + newTicket.getWaiter());
//        System.out.println("Fecha: " + newTicket.getDate());
//        System.out.println("MaxComensales" + newTicket.getNum_diners());
//        TicketsDao ticketsDao = new TicketsDao();
//        try {
//            //boolean insertTicket = ticketsDao.newTicket(newTicket);
//            if (insertTicket) {
//                return ResponseEntity.ok().build();
//            } else {
//                return ResponseEntity.status(401).body("CredencialesIncorrectas");
//            }
//        } catch (CheckError e) {
//            throw new RuntimeException(e);
//        }
//    }
    public ResponseEntity postXXXX(@PathVariable("id") int option,
                                   @RequestBody Body body) {
        return mesappService.postXXXX(option, body);
    }

    //MAPEO USUARIO
    @PostMapping("/mesapp/Usuario")
    public ResponseEntity postUsuario(@PathVariable("id") int option,
                                      @RequestBody Usuario usuario) {
        return mesappService.postUsuario(option, usuario);
    }

    //MAPEO CHEF
    @PostMapping("/mesapp/Chef")
    public ResponseEntity postChef(@PathVariable("id") int option,
                                   @RequestBody Chef chef) {
        return mesappService.postChef(option, chef);
    }

    //MAPEO CAMARARERO
    @PostMapping("/mesapp/Camarero")
    public ResponseEntity postCamarero(@PathVariable("id") int option,
                                       @RequestBody Camarero camarero) {
        return mesappService.postCamarero(option, camarero);
    }

    //MAPEO MESA
    //@Autowired
    //MesaDao mesaDao;
    @PostMapping("/mesapp/Mesa")
    public ResponseEntity<?> postMesa(@PathVariable("id") int option,
                                      @RequestBody Mesa mesa) {
        return mesappService.postMesa(option, mesa);
    }

    //MAPEO RESERVA
    @PostMapping("/reserva")
    public ResponseEntity<?> nuevaReserva(@RequestBody Cliente_Mesa userReserva, @RequestParam int userId) throws CheckError {
            int reservaId = mesaDao.nuevaReserva(userId, userReserva.getNum_mesa(), userReserva.getFecha_reserva());

            if (reservaId > 0) {
                return ResponseEntity.ok(reservaId);
            } else {
                return ResponseEntity.status(401).body("Error en la reserva");
            }

    }


    //MAPEO CLIENTE
    @PostMapping("/mesapp/Cliente")
    public ResponseEntity postCliente(@PathVariable("id") int option,
                                      @RequestBody Cliente cliente) {
        return mesappService.postCliente(option, cliente);
    }

    //MAPEO COMANDA
    @PostMapping("/mesapp/Comanda")
    public ResponseEntity postComanda(@PathVariable("id") int option,
                                      @RequestBody Comanda comanda) {
        return mesappService.postComanda(option, comanda);
    }

    //MAPEO MENU
    @PostMapping("/mesapp/Menu")
    public ResponseEntity postMenu(@PathVariable("id") int option,
                                   @RequestBody Menu menu) {
        return mesappService.postMenu(option, menu);
    }

    //MAPEO PLATO
    @PostMapping("/mesapp/PlatosAll")
    public ResponseEntity postPlato(@PathVariable("id") int option,
                                    @RequestBody Plato plato) {
        return mesappService.postPlato(option, plato);
    }

    //MAPEO PRODUCTO
    @PostMapping("/mesapp/Producto")
    public ResponseEntity postProducto(@PathVariable("id") int option,
                                       @RequestBody Producto producto) {
        return mesappService.postProducto(option, producto);
    }

    // MOSTRAR PLATOS
    @GetMapping("/mesapp/PlatosAll")
    public ResponseEntity postPlato() {
        try {
            ArrayList<Plato> platos = platoDao.getPlatos();
            if (platos != null) {
                return ResponseEntity.ok(platos);
            } else {
                return ResponseEntity.status(401).body("Error al listar los platos");
            }
        } catch (CheckError e) {
            throw new RuntimeException(e);
        }
    }

    //DELETE PLATOS POR ID
    @DeleteMapping("/mesapp/PlatosAll/{id}")
    public ResponseEntity<?> deletePlato(@PathVariable("id") int id) {
        boolean deleted = platoDao.deletePlato(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(401).body("Error al borrar el plato");
        }
    }


}
