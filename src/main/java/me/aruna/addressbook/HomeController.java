package me.aruna.addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
 @Autowired
 PersonRepository personRepository;

 @RequestMapping("/")
    public String personList(Model model){
     model.addAttribute("persons", personRepository.findAll());
     return "list";
 }
 @GetMapping("/add")
    public String personForm(Model model){
        model.addAttribute("person", new Person());
        return "personform";
 }
 @PostMapping("/process")
    public String processForm(@Valid Person email, BindingResult result)
 {
     System.out.println(result.toString());
     if(result.hasErrors()){
         return "personform";
     }
     personRepository.save(email);
     return "redirect:/";
 }
 @RequestMapping("/detail/{id}")
    public String showPerson(@PathVariable("id") Long id, Model model){
    model.addAttribute("person", personRepository.findOne(id));
    return "show";
 }
 @RequestMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") Long id, Model model){
        model.addAttribute("person",personRepository.findOne(id));
        return "personform";
 }
 @RequestMapping("/delete/{id}")
    public String delPerson(@PathVariable("id") Long id){
        personRepository.delete(id);
        return "redirect:/";
 }
}
