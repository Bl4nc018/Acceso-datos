import json

from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt


def health_check(request):
    http_response = {"running": True}
    return JsonResponse(http_response)


## Ejercicio #3:
def table_of_six(request):
    http_response = []
    for i in range(6, 61, 6):
        http_response.append(i)
    return JsonResponse(http_response, safe=False)


## Ejercicio #4: Creamos una función mediante la que, según el parámetro que se pase (que será un número entre 1 y 10), se mostrará
## como respuesta las tablas de multiplicar de ese número.
def multiplication_table(request, number):
    print("El usuario ha visitado la tabla del " + number)

    if number == "one":
        return JsonResponse([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], safe=False)
    elif number == "two":
        return JsonResponse([2, 4, 6, 8, 10, 12, 14, 16, 18, 20], safe=False)
    elif number == "three":
        return JsonResponse([3, 6, 9, 12, 15, 18, 21, 24, 27, 30], safe=False)
    elif number == "four":
        return JsonResponse([4, 8, 12, 16, 20, 24, 28, 32, 36, 40], safe=False)
    elif number == "five":
        return JsonResponse([5, 10, 15, 20, 25, 30, 35, 40, 45, 50], safe=False)
    elif number == "six":
        return JsonResponse([6, 12, 18, 24, 30, 36, 42, 48, 54, 60], safe=False)
    elif number == "seven":
        return JsonResponse([7, 14, 21, 28, 35, 42, 49, 56, 63, 70], safe=False)
    elif number == "eight":
        return JsonResponse([8, 16, 24, 32, 40, 48, 56, 64, 72, 80], safe=False)
    elif number == "nine":
        return JsonResponse([9, 18, 27, 36, 45, 54, 63, 72, 81, 90], safe=False)
    elif number == "ten":
        return JsonResponse([10, 20, 30, 40, 50, 60, 70, 80, 90, 100], safe=False)
    else:
        var = {'error': 'Number invalid. Only one to ten are supported'}
        return JsonResponse(var, status=404)


## Ejercicio #5:
def multiply_number_improved(request, number):
    result = []
    for i in range(1, 11):
        result.append(number * i)
    return JsonResponse(result, safe=False)


## Ejercicio #6:
def mi_funcion(request):
    # Siguiendo el ejemplo de arriba,
    # Esto imprimirá 'cualquiera'
    print(request.GET["una_cosa", "ninguna"])


def multiplication_table_query_param(request):
    number = request.GET.get("d", None)
    if number is not None:
        try:
            number = int(number)
        except ValueError:
            return JsonResponse({"error": "Parameter 'd' must be a number"}, status=400)
        result = [number * i for i in range(1, 11)]
        return JsonResponse(result, safe=False)
    else:
        return JsonResponse({"error": "Missing 'd' parameter"}, status=400)


## Ejercicio #7:
def is_prime_number(request):
    prime = request.GET.get("i", None)
    if prime is not None:
        try:
            prime = int(prime)
            if prime <= 0:
                return JsonResponse({"error": "Parameter must be a number greater than zero"}, status=400)
        except ValueError:
            return JsonResponse({"error": "Parameter must be a number greater than zero"}, status=400)
        for i in range(2, prime):
            if prime % i == 0:
                return JsonResponse({"is_prime_number": False}, safe=False)
        return JsonResponse({"is_prime_number": True}, safe=False)
    else:
        return JsonResponse({"error": "Missing required 'i' parameter"}, status=400)


## Ejercicio #8:
def years_since(request, year):
    if year is not None:
        year = int(year)
        if year > 2023:
            return JsonResponse({"error": "Years in the future are not supported"}, status=400)
        else:
            N = 2023 - year
            return JsonResponse({"years_passed": N}, safe=False)


## Ejercicio #9:
@csrf_exempt
def resource_example(request, number):
    ## Ejercicio #10:
    if request.method != 'POST':
        return JsonResponse({"error": "HTTP method not supported"}, status=405)
    ## Ejercicio #11:
    if len(request.body) == 0:
        return JsonResponse({"message": "You have sent a POST to the resource " + str(number)})
    http_body = json.loads(request.body)
    client_mood = http_body.get("mood", "No mood")
    return JsonResponse({"message": "You have sent a POST to the resource " + str(
        number) + " and you're in the following mood: " + client_mood})


## Ejercicio #12:
@csrf_exempt
def preferred_animals(request):  ## Empezamos definiendo la funcion a emplear
    if request.method == 'POST':  ## Si el método de la petición no se coincide con 'POST', da un error 405 (Method Not Allowed).
        try:
            ## En este punto, el programa toma el cuerpo de la solicitud, y lo descodifica del formato JSON en una
            ## cadena de texto. Tras ello la analiza (parsea) en una estructura de datos de Python, que permite al
            ## servidor acceder a los datos enviados por el cliente y trabajar con ellos en el código del servidor.
            data = json.loads(request.body.decode('utf-8'))
        except ValueError:
            return JsonResponse({"error": "Bad Request"}, status=400)  ## Error 400 (Bad Request).

        if 'name' in data:
            name = data['name']

            if name == 'Cat':
                return JsonResponse({"message": "Great! Seven lives will be enough"}, safe=False)
            else:
                return JsonResponse({"message": "OK! Have a nice day"}, safe=False)
        else:
            return JsonResponse({"error": "Name no está en data"}, status=400)  ## Bad Request.
    return JsonResponse({"error": "Error"}, status=405)  ## Error 405 (Method Not Allowed).


## Ejercicio #13:

@csrf_exempt
def http_methods_example(request):
    if request.method == "GET":
        message = "Reading some data, huh?"
    elif request.method == "POST":
        message = "This should create a new thing"
    elif request.method == "PUT":
        message = "You can update any element with this"
    elif request.method == "DELETE":
        message = "This will remove one or many elements"
    else:
        # Esta línea nunca se ejecutará si el verbo HTTP no es GET, POST, PUT o DELETE
        return JsonResponse({"error": "HTTP method not allowed"}, status=405)
    # Flujo normal
    return JsonResponse({"message": message})
