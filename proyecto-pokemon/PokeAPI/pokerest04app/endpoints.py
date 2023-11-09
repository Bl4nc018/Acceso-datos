from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from math import radians, sin, cos, sqrt, atan2

from .models import Pokemon

@csrf_exempt
def poke_recibir(request):
    if request.method == 'GET':
        latitud = request.GET.get("latitud", None)
        longitud = request.GET.get("longitud", None)
        radio = request.GET.get("radio", None)

        if latitud is None or longitud is None or radio is None:
            return JsonResponse({"Bad Request": "Los parámetros de consulta son inválidos. ¡Te poke-equivocaste!"}, status=400)

        try:
            latitud = float(latitud)
            longitud = float(longitud)
            radio = int(radio)
        except ValueError:
            return JsonResponse({"Bad Request": "Los parámetros de consulta son inválidos. ¡Te poke-equivocaste!"}, status=400)

        if not (-90 <= latitud <= 90) or not (-180 <= longitud <= 180) or radio <= 0:
            return JsonResponse({"Bad Request": "Los parámetros de consulta son inválidos. ¡Te poke-equivocaste!"}, status=400)

        poke_area = []
        for pokemon in Pokemon.objects.all():
            ## Calculamos la diferencia en latitud y longitud entre el Pokémon y el centro del círculo de búsqueda.
            lat = radians(pokemon.latitud - latitud)
            lon = radians(pokemon.longitud - longitud)

            ## Hallamos los componentes de la fórmula de Haversine
            a = sin(lat / 2) ** 2 + cos(radians(latitud)) * cos(radians(pokemon.latitud)) * sin(lon / 2) ** 2

            ## Calculamos la distancia angular central
            c = 2 * atan2(sqrt(a), sqrt(1 - a))

            ## Finalmente, encontramos la distancia en kilómetros utilizando el radio de la Tierra (6371 km) y
            ## verificamos si la distancia es menor o mayor que el radio proporcionado para ver que pokemons hay en el área.
            poke_distancia = 6371 * c

            if poke_distancia <= radio:
                poke_area.append(pokemon.to_json())

        return JsonResponse(poke_area, safe=False)
    return JsonResponse({"Method not Allowed": "¡Cuidado! Hay hierba alta, con tu poke-método no puedes entrar."}, status=405)


@csrf_exempt
def poke_postear(request, id):
    if request.method == "POST":
        poke_data = f"Has enviado un POST para realizar una acción en el servidor sobre el pokemon con ID {id}"
        return JsonResponse({"mensaje": poke_data}, safe=False)
    else:
        return JsonResponse({"Method not Allowed": "¡Cuidado! Hay hierba alta, con tu poke-método no puedes entrar."}, status=405)
