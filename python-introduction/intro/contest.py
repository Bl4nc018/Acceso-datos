## Ejercicio 12:

def make_question():
    question = """
¿Cuál es la universidad más antigua de Europa, fundada en 1088, que sigue en funcionamiento actualmente?

a) Universidad de París
b) Universidad de Bolonia
c) Universidad de Oxford    
"""
    print(question)
    choice = input("Escoge tu respuesta (a, b ó c): ")
    if choice == 'b':
        print("¡ENHORABUENA! ¡HAS ACERTADO! Ganaste 174 puntos")
    else:
        print("¡Fallaste! Pero no pasa nada, ¡sigue intentándolo!")

    ## Ejercicio 13:

    while choice not in ['a', 'b', 'c']:
        choice = input("Por favor, escoge una respuesta válida (a, b ó c): ")


## Ejercicio 14:

def make_question2():
    question2 = """
¿Cuál es el resultado de SUMAR estos dos números?
688
680
"""
    print(question2)
    answer = None
    while answer is None:  # Se prefiere 'is' a '==' para comparar con None
        try:
            answer = int(input("Dime tu respuesta: "))
        except ValueError:
            print("¡No has introducido un número válido!")

    if answer == 1368:
        print("Respuesta correcta")
    else:
        print("Vaya, la respuesta es errónea")


if __name__ == '__main__':
    make_question()
    make_question2()
