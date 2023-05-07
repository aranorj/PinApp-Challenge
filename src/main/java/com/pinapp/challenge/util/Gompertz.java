package com.pinapp.challenge.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Gompertz {

    public static Double getTotalLifeExpectancyByAge(Integer edad) {

        double A; // tasa de mortalidad constante en la población
        double B;// tasa de mortalidad que varía en función de la edad
        double C; // tasa de aceleración de la mortalidad a medida que la edad aumenta

        //Se separa en 3 grupos etarios para poder manipular a la población con mas detalle

        if (edad <= 30) { //mortandad baja en la juventud
            A = 3.0;
            B = 0.0005;
            C = 0.0001;
        } else if (edad <= 70) { //media en la adultez
            A = 4.0;
            B = 0.0006;
            C = 0.0002;
        } else { // elevada en la ancianidad
            A = 6.0;
            B = 0.001;
            C = 0.001;
        }

        // Calcular la probabilidad de supervivencia para la edad actual segun la Ley de Gompertz-Makeham
        double q = Math.exp(-(A + B * edad + C * Math.pow(edad, 2)));

        // Calcular la expectativa de vida restante con base en las probababilidades año a año
        double lifeExpectancy = 0.0;
        int maxAge = 100;
        for (int i = edad; i < maxAge; i++) {
            double qi = Math.exp(-(A + B * i + C * Math.pow(i, 2)));
            lifeExpectancy += qi;
        }
        lifeExpectancy /= q;

        log.info(edad + " " +lifeExpectancy);

        return lifeExpectancy + edad;
    }
}
