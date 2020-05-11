package com.example.babellibrary.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.validation.Valid;

import com.example.babellibrary.models.entity.Libro;
import com.example.babellibrary.models.entity.Sala;
import com.example.babellibrary.models.services.interfaces.LibroServiceInterface;
import com.example.babellibrary.models.services.interfaces.UploadFileServiceInterface;
import com.example.babellibrary.util.paginator.PageRender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes(names = { "libro", "salas" })
public class LibroController {

    @Autowired
    private LibroServiceInterface libroService;

    @Autowired
    private UploadFileServiceInterface uploadFileService;

    @GetMapping(value = {"/", "/index"})
    public String index(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Libro> libros = libroService.findAll(pageRequest);

        PageRender<Libro> pageRender = new PageRender<>("/", libros);

        model.addAttribute("titulo", "Índice de libros");
        model.addAttribute("subtitulo", "Libros en la biblioteca");

        model.addAttribute("libros", libros);
        model.addAttribute("page", pageRender);

        return "libros/index";
    }

    @GetMapping(value = "/registrar")
    public String registrarLibro(Model model) {
        Libro libro = new Libro();
        List<Sala> salas = libroService.getSalas();

        model.addAttribute("salas", salas);
        model.addAttribute("libro", libro);
        model.addAttribute("titulo", "Registro de libros");
        model.addAttribute("botonForm", "Registrar");

        return "libros/form";
    }

    @PostMapping(value = "/cargar-libreros", produces = { "application/json" })
    public @ResponseBody List<Object[]> cargarLibreros(@RequestParam("sala") String sala) {
        return libroService.findLibreroBySala(sala);
    }

    @PostMapping(value = "/cargar-estantes", produces = { "application/json" })
    public @ResponseBody List<Object[]> cargarEstantes(@RequestParam("librero") String librero) {
        return libroService.findEstanteByLibrero(librero);
    }

    @PostMapping(value = "/registrar")
    public String guardarLibro(@Valid Libro libro, BindingResult result, Model model, SessionStatus status,
            RedirectAttributes flash, @RequestParam("file") MultipartFile portada) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro de libros");
            String botonForm = (libro.getId() != null) ? "Editar" : "Registrar";
            model.addAttribute("botonForm", botonForm);
            return "libros/form";
        }

        if (!portada.isEmpty()) {
            if (libro.getId() != null && libro.getId() > 0 && libro.getPortada() != null
                    && libro.getPortada().length() > 0) {

                uploadFileService.delete(libro.getPortada());
            }

            String nombrePortada = null;
            try {
                nombrePortada = uploadFileService.copy(portada);
            } catch (IOException e) {
                e.printStackTrace();
            }

            libro.setPortada(nombrePortada);
        }

        if (libro.getLocalizacion() != null) {
            libro.getLocalizacion().setLibro(libro);
        }

        String mensajeFlash = (libro.getId() != null) ? "Libro actualizado correctamente"
                : "Libro registrado exitósamente";

        libroService.save(libro);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);

        return "redirect:/index";
    }

    @GetMapping(value = "/ver/{id}")
    public String verLibro(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        Libro libro = libroService.findOne(id);
        if (libro == null) {
            flash.addFlashAttribute("error", "El libro no existe en la base de datos");
            return "redirect:/index";
        }

        model.addAttribute("libro", libro);
        model.addAttribute("titulo", "Detalle del libro: ".concat(libro.getTitulo()));

        return "libros/detalleLibro";
    }

    @GetMapping(value = "/eliminar/{id}")
    public String eliminarLibro(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            libroService.delete(id);
            flash.addFlashAttribute("success", "Libro eliminado correctamente");
        }
        return "redirect:/index";
    }

    @GetMapping(value = "/editar/{id}")
    public String editarLibro(@PathVariable(value = "id") Long id, RedirectAttributes flash, Model model) {
        if (id < 0) {
            flash.addFlashAttribute("error", "El id debe ser mayor a cero");
            return "redirect:/index";
        }

        Libro libro = libroService.findOne(id);
        if (libro == null) {
            flash.addFlashAttribute("error", "El libro no se encuentra en la base de datos");
            return "redirect:/index";
        }

        model.addAttribute("libro", libro);
        model.addAttribute("titulo", "Editar libro");
        model.addAttribute("botonForm", "Editar");

        return "libros/form";
    }

    @GetMapping(value = "/buscar")
    public String buscarLibro(@RequestParam(value = "term") String term,
            @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Libro> libros = libroService.findByTituloOrAutorOrEditorial(term, pageRequest);
        
        PageRender<Libro> pageRender = new PageRender<>("/buscar", libros);

        model.addAttribute("titulo", "Resultados de la búsqueda");
        model.addAttribute("subtitulo", "Término de búsqueda: '" + term + "'");

        model.addAttribute("libros", libros);
        model.addAttribute("page", pageRender);

        return "libros/index";
    }

    @GetMapping(value = "/upload/{filename:.+}")
    public ResponseEntity<Resource> verPortada(@PathVariable(value = "filename") String filename) {
        Resource resource = null;
        try {
            resource = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attatchment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}