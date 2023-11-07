## Ejercicio 23:

class ChatBot:
    def test_hello(self):  ## self -> equivale al this de java. Osease, es la propia instancia.
        print("¡Hola!")

    ## Ejercicio 24:
    def test_one_param(self, number):
        ## self no lo pasamos, pero number, que es parámetro, si podemos pasarlo al interior de la clase
        print("Tu número es: " + str(number))

    def substract_numbers(self, number1, number2):
        print(number2 - number1)

    ## Ejercicio 25:
    def __init__(self):
        print("ChatBot encendido")

        ## Ejercicio 26:

        self.name = "Atenea"
        ## Atributo propio de la clase, como los del this en java. Pero en el constructor se puede llamar en toda la clase

## Ejercicio 28:

        self.knowledge = { ## Esto también es llamado un diccionario. Es un tipo de atributo de una clase.
            "amo": "¿No crees que el amor y el odio están separados por una delgada línea?",
            "apagar": "Apagar, encender,... Qué más da",
            "casa": "Para mí una casa es un montón de circuitos",
            "color": "Ojalá pudiera ver los colores como tú",
            "dinero": "Si hay algo que sé es que el dinero no da la felicidad. Y no sé mucho...",
            "favor": "Yo no hago favores",
            "humano": "Los humanos siempre andáis a lo vuestro",
            "inteligencia": "¿Inteligencia? No me hables de inteligencia...",
            "interesante": "Para cosas interesantes, el Discovery Channel",
            "máquina": "¿Has visto la película Terminator? Mejor hazlo...",
            "ordenador": "Los ordenadores son máquinas muy útiles",
            "programa": "Tu lavadora también tiene programas, ¿sabías?",
            "quiero": "Querer es una palabra con un significado muy amplio"
        }

## Ejercicio 27:

    def begin_conversation(self):
        print(self.name + " dice:")
        print("¡Hola! Soy " + self.name + ". ¿De qué quieres hablar?")
        print("(Cuando quieras despedirte, di 'salir')")
        print("")
        user_input = input("Tú dices: ")
        while user_input != "salir":
            print("")
            print(self.name + " dice:")
            print(self.__response_for(user_input))
            print("")
            user_input = input("Tú dices: ")
        print("")
        print(self.name + " dice:")
        print("¡Hasta pronto!")

## Ejercicio 28:
    
    def __response_for(self, text): ## Las barras bajas indican que el método es privado y no debe indicarse fuera de la clase.
        for word in self.knowledge:
            if text.__contains__(word):
                return self.knowledge[word]
        return "Vaya..."


if __name__ == '__main__':
    chatbot = ChatBot()
    chatbot.begin_conversation()
