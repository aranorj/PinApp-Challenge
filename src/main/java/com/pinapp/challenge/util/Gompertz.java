package com.pinapp.challenge.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Gompertz {

    //Parametros para una población hipotética con alta expectativa de vida
    final static double A = 4.0; // tasa de mortalidad constante en la población
    final static double B = 0.0005; // tasa de mortalidad que varía en función de la edad
    final static double C = 0.0001; // tasa de aceleración de la mortalidad a medida que la edad aumenta

    public static Double getTotalLifeExpectancyByAge(Integer edad) {

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
