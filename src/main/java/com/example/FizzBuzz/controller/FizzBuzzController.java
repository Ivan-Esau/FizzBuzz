package com.example.FizzBuzz.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 * Ein REST-Controller, der die Fizz-Buzz-Logik bereitstellt.
 */

@RestController
public class FizzBuzzController
{

    /**
     * Die Methode verarbeitet GET-Anfragen auf dem Pfad "/fizzbuzz".
     * Sie akzeptiert eine Zahl als Parameter und gibt eine Liste von FizzBuzz-Ergebnissen zurück.
     *
     * @param number Die Zahl, bis zu der die FizzBuzz-ausgabe durchgelaufen werden soll.
     * @return Eine Liste von Strings, die die FizzBuzz Werte enthält.
     * @throws IllegalArgumentException wenn die übergebene Zahl negativ oder null ist.
     */

    @GetMapping("/fizzbuzz")
    public List<String> fizzBuzz(@RequestParam int number)
    {
        //Exception wird geworfen falls die Zahl negativ sein sollte
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }

        //Schleife zum durchlaufen der Zahlen bis zum vorgegeben Wert in der URL
        //z.b. http://localhost:8080/fizzbuzz?number=10
        //Ausgabe: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz"]
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= number; i++)
        {
            //Überprüfung Zahl durch 3 UND 5 Teilbar?
            if (i % 3 == 0 && i % 5 == 0)
            {
                result.add("FizzBuzz");
            }
            //Überprüfung Zahl durch 3 Teilbar?
            else if (i % 3 == 0)
            {
                result.add("Fizz");
            }
            //Überprüfung Zahl durch 5 Teilbar?
            else if (i % 5 == 0)
            {
                result.add("Buzz");
            }
            //Ausgabe der Zahl falls sonst nicht zu trifft
            else
            {
                result.add(String.valueOf(i));
            }
        }
        //Rückgabe der Liste mit den FizzBuzz zahlen
        return result;
    }

    /**
     * IllegalArgumentException.
     *
     * @param ex Die ausgelöste IllegalArgumentException.
     * @return Eine HTTP-Fehlerantwort mit dem Fehlercode "BAD_REQUEST" und der Fehlermeldung.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

