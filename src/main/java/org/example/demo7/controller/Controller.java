package org.example.demo7.controller;

import org.example.demo7.model.Product;
import org.example.demo7.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@org.springframework.stereotype.Controller
@RequestMapping("/Cus")
public class Controller {
    @Autowired
    IService service;
    @RequestMapping
    public ModelAndView ShowAll(@PageableDefault(value = 5) Pageable pageable){
        ModelAndView modelAndView=new ModelAndView("/index");
        modelAndView.addObject("PrList",service.findAll(pageable));
        return modelAndView;
    }
    @GetMapping("/CreatF")
    public ModelAndView CreateF(){
        ModelAndView modelAndView=new ModelAndView("/CreateF");
        modelAndView.addObject("NewPr",new Product());
        return modelAndView;
    }
    @PostMapping("/Create")
    public ModelAndView create(Product product){
        ModelAndView modelAndView=new ModelAndView("redirect:/Cus");
        service.Create(product);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/Cus");
        service.delete(id);
        return modelAndView;
    }
    @GetMapping("/View/{id}")
    public ModelAndView view(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/view");
        Product product = service.FindById(id).get();
        modelAndView.addObject("Product", product);
        return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView update(Product product){
        ModelAndView modelAndView=new ModelAndView("/view");
        service.Update(product.getId(), product);
        modelAndView.addObject("Product", product);
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView search(@PageableDefault(value = 5)Pageable pageable, @RequestParam("keyword") Optional<String> keyword ) {
        Page<Product> products;
        if(keyword.isPresent()){
            products=service.findAllByName(pageable,keyword.get());
        }
        else {
            products=service.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("PrList",products);
        return modelAndView;
    }


}
