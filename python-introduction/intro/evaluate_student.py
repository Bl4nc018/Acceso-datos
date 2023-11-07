## Ejercicio 7:

import maths

if __name__ == "__main__":
    nota_practica = 4
    nota_teoria = 4

    print('La nota de prácticas es: ', nota_practica)
    print('La nota de teoría es: ', nota_teoria)

    nota_final = maths.sum_two_variables(nota_practica, nota_teoria)

    print('Suma de ambas notas: ', nota_final)
