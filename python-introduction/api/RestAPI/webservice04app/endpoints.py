## Ejercicio 32:

from django.http import JsonResponse


def my_first_endpoint(request):
    json_dictionary = {
        "has_been_successful": True,
        "message": "Ha ido bien"
    }
    return JsonResponse(json_dictionary)


## Ejercicio 33:

def my_not_found_endpoint(request):
    json_dictionary = {
        "message": "No encontrado"
    }
    return JsonResponse(json_dictionary, status=404)


## Ejercicio 34:

def my_list_endpoint(request):
    animals = [
        "Focha común (Fulica atra)",
        "Lagartija carpetana (Iberolacerta cyreni)",
        "Avispón europeo (Vespa crabro)"
    ]
    return JsonResponse(animals, safe=False)
    ## Parámetro requerido cuando enviamos cosas que no formen parte de un dicc. de Python.


