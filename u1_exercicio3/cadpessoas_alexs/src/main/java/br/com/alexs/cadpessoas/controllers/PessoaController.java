package br.com.alexs.cadpessoas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alexs.cadpessoas.model.Pessoa;
import br.com.alexs.cadpessoas.repositories.PessoaRepository;

@Controller
@RequestMapping("/")
public class PessoaController {

@Autowired
PessoaRepository pessoaRepo;

@GetMapping
public String index() {
return "index.html";
}
@GetMapping ("/listarPessoas")
public ModelAndView listarPessoas() {
	
	List<Pessoa> lista = pessoaRepo.findAll();
	ModelAndView mav = new ModelAndView("listarPessoas");
	mav.addObject("pessoas", lista);
	return mav;
	
}

@GetMapping ("/editar/{id}")
public ModelAndView formeditarPessoa (@PathVariable("id")long id) {

Pessoa pessoa = pessoaRepo.findById(id)
.orElseThrow(()  -> new IllegalArgumentException ("ID Inválido:" + id));
ModelAndView modelAndView = new ModelAndView ("editarPessoa");
modelAndView.addObject(pessoa);
return modelAndView;

}



@PostMapping ("/editar/{id}")
public ModelAndView editarPessoa(@PathVariable("id") long id, Pessoa pessoa) {
	pessoaRepo.save(pessoa);
	return new ModelAndView ("redirect:/listarPessoas");
}

@GetMapping ("/remover/{id}")
public ModelAndView removerPessoa (@PathVariable("id")long id) {
 
Pessoa aRemover = pessoaRepo.findById(id)
.orElseThrow(() -> new IllegalArgumentException ("ID Inválido:" + id));

pessoaRepo.delete(aRemover);
return new ModelAndView ("redirect:/listarPessoas ");

}



}

