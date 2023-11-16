from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse


@csrf_exempt
def wishlist(request):
    if request.method == 'GET':
        # Datos de regalos (simulando una respuesta de la base de datos)
        gifts_data = [
            {"mensaje": "Hola, Papá Noel, he sido una persona excelente, trabajadora, estudiosa, amable y buena. Te "
                        "envío mi lista de regalos.",
             "regalos": ["PlayStation 8", "Ordenador i9 con gráfica potente", "Yoyó"]
             },
            {"mensaje": "Mensaje 2", "regalos": ["Regalo4", "Regalo5"]},
        ]

        json_response = {
            "mensaje": gifts_data[0]["mensaje"],
            "regalos": gifts_data[0]["regalos"],
        }

        return JsonResponse(json_response, safe=False, status=200)
