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
        return "/jogo/insert";
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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") int id, @RequestParam("titulo") String titulo, @RequestParam("anoDeLancamento") int anoDeLancamento) {
        Optional<Jogo> jogo = jogoRepo.findById(id);

        if(jogo.isPresent()) {
            jogo.get().setTitulo(titulo);
            jogo.get().setAnoDeLancamento(anoDeLancamento);
            jogoRepo.save(jogo.get());
        }

        return "redirect:/jogo/list";
    }

    @RequestMapping("/delete")
    public String delete(Model model, @RequestParam("id") int id) {
        Optional<Jogo> jogo = jogoRepo.findById(id);

        if (jogo.isPresent()){
            model.addAttribute("jogo", jogo.get());
            return "jogo/delete";
        }

        return "redirect:/jogo/list";
    }

    @RequestMapping(value =  "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        jogoRepo.deleteById(id);

        return "redirect:/jogo/list";
    }
}
