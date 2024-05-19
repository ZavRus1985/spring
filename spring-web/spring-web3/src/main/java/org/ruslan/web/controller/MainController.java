package org.ruslan.web.controller;


import org.ruslan.web.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MainController {

    private LinkedList<Person> persons = new LinkedList<>();

    public LinkedList<Person> getPersons() {
        return persons;
    }

    //С помощью значений из параметров запроса.
    // http://localhost:8880/index?name=Anna&sex=female&age=22
    @GetMapping("/index")
    public void getPerson(@RequestParam String name,
                          @RequestParam String sex,
                          @RequestParam Integer age,
                          @ModelAttribute Person person) {
        System.out.println(person);
    }

    @GetMapping("/form")
    public String getForm() {

        return "form";
    }

    // С помощью значений из формы, отправленной GET-запросом.
    @GetMapping("/form-handler")
    public String handleForm(@ModelAttribute Person person) {

        System.out.println(person);
        return "getUserForm";
    }

    //С помощью значений из формы, отправленной POST-запросом.
    @GetMapping("/form-post")
    public String getFormPost(Model model) {

        model.addAttribute("person", new Person());
        return "form";
    }
//------------------------------------------------------------------------------------
    //4.	Для формы POST реализовать паттерн PRG.

    @PostMapping("/form-handler-post")
    public String handleFormPost(@ModelAttribute Person person) {

        this.persons.add(person);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String getResult(Model model) {

        model.addAttribute("person", this.persons.getLast());
        System.out.println("Invoked/result");
        return "getUserForm";
    }

    //------------------------------------------------------------------------------------
    //Каждый Person, полученный из формы POST, должен сохраняться в список.
    // Данный список необходимо отображать на HTML-странице в виде таблицы.
    @GetMapping("/table")
    public String getTable(Model model) {

        model.addAttribute("persons", this.persons);
        return "table";
    }

    //-----------------------------------------------------------------------------------------
    /*
    Реализовать отдельный метод, позволяющий выводить таблицу в нужном виде:
    с помощью параметров запроса должна быть возможность настроить количество выводимых объектов,
    порядок сортировки, поле для сортировки. Пример: нужно вывести 5 объектов,
    отсортированных по возрастанию по возрасту.
    */
    @GetMapping("/array") //localhost:8080/array/0
    public String getIndex(@RequestParam Integer size, @RequestParam String field,
                           @RequestParam String sort, Model model) {

        sort(size, field, sort);
        model.addAttribute("persons", persons);
        return "table";
    }

    private void sort(Integer size, String field, String sort) {
        Comparator<Person> comparator;
        switch (field) {
            case "name" -> comparator = Comparator.comparing(Person::getName);
            case "sex" -> comparator = Comparator.comparing(Person::getSex);
            case "age" -> comparator = Comparator.comparing(Person::getAge);
            default -> throw new IllegalArgumentException("Illegal field name for Person");
        }
        if (Objects.equals(sort, "desc")) {
            comparator = comparator.reversed();
        }
        persons.sort(comparator);
    }

    //--------------------------------------------------------------------------------------------------
    /*
     *Описать отдельную форму с двумя полями: первое – название поля для фильтрации, второе – значение поля.
     * По данным формы необходимо удалить совпадающие объекты из списка Person.
     * После удаления вывести таблицу со всеми Person.
     * Пример: введены «пол» и «мужчина», необходимо удалить всех мужчин из списка.
     * */
    @PostMapping("/form-delete")
    public String deletePerson(@ModelAttribute Person person) {


        return "table";
    }
    //---------------------------------------------------------------------------------------------------
    /*
    *Реализовать стандартное меню, открывающееся сразу при запуске приложения (localhost:8080).
    В меню должен быть выбор (кнопки):
    Перейти на страницу заполнения GET-формы.
    Перейти на страницу заполнения POST-формы.
    Перейти на страницу с формой для удаления объектов.
    Просмотреть список Person.
    */
    @GetMapping("/")
    public String getMenu() {

        return "menu";
    }
}
