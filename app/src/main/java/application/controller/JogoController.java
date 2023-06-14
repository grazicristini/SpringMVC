package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Jogo;
import application.model.JogoRepository;

@Controller
@RequestMapping("/jogo")
public class JogoController {

    @Autowired
    private JogoRepository jogoRepo;
    
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("jogos", jogoRepo.findAll());
        return "/jogo/list";
    }

    @RequestMapping("/insert")
    public String insert() {
        return "/livro/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("titulo") String titulo, @RequestParam("anoDeLancamento") int anoDeLancamento) {
        Jogo jogo = new Jogo();
        jogo.setTitulo(titulo);
        jogo.setAnoDeLancamento(anoDeLancamento);

        jogoRepo.save(jogo);
        return "redirect:/jogo/list";
    }

    @RequestMapping("/update")
    public String update(Model model, @RequestParam("id") int id) {
        Optional<Jogo> jogo = jogoRepo.findById(id);

        if(jogo.isPresent()) {
            model.addAttribute("jogo", jogo.get());
            return "/jogo/update";
        }

        return "redirect:/jogo/list";
    }

    public String update(@RequestParam("id") int id, @RequestParam("titulo") String titulo, @RequestParam("anoDeLancamento") int anoDeLancamento) {
        Optional<Jogo> jogo = jogoRepo.findById(id);
    }
}
