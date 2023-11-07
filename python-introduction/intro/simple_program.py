## Ejercicio 5:

# import functions <- Si importas así el módulo, deberás poner modulo.funcion cada vez que quieras sacar una funcion específica.
# from functions import do_nothing <- O, puedes importar directamente la función deseada.
from functions import get_color

# functions.do_something()
# ()  # ¡Ya no lleva prefijo!

color = get_color()
print("Ahora, vemos el color: ")
print(color)
