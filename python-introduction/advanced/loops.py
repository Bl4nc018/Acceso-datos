## Ejercicio 15:

def simple_for():
    for i in range(61):
        print(i)


## Ejercicio 16:

def simple_for_start():
    for i in range(90, 123):
        print(i)


## Ejercicio 17:

def simple_for_increment():
    for i in range(90, 123, 3):
        print(i)


## Ejercicio 18:

def multiples():
    for i in range(31, 57):
        if i % 4 == 0:
            print(i)


## Ejercicio 19:

def multiplication_table(num):
    for i in range(1, 11):
        print(num * i)


## Ejercicio 20:

def search_for_dino(dino):
    dinos = ["Triceratops", "Tiranosaurio", "Diplodocus", "Pterodáctilo", "Velocirraptor"]

    for i in dinos:
        if dino == i:
            return True
        elif i is None:
            return False


## Ejercicio 21:

def find_dino2(dinosaur):
    return dinosaur in ["Triceratops", "Diplodocus", "Pterodáctilo"]

def example_length():
    list1 = ["Alice", "Bob"]
    size1 = len(list1)
    print("El tamaño de la primera lista es: " + str(size1))
    list2 = [None, None, None, None]
    size2 = len(list2)
    print("El tamaño de la segunda lista es: " + str(size2))
    list3 = [[1, 2], [3, 4]]
    size3 = len(list3)
    print("El tamaño de la tercera lista es: " + str(size3))

def retrieve_value(param):
    list = [4, 8, -35, "Pepe Depura", 45]
    sizeL = len(list)

    if 0 <= param < sizeL: ## Si el parámetro es menor que 0 o si es mayor que la longitud de la lista, no se devuelve nada.
        return list[param]
    else:
        return None



if __name__ == '__main__':

## Ejercicio 21:
    print(retrieve_value(2))
