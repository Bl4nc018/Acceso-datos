from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from .models import CustomUser, UserSession, Category, Idea, Comment
import json
import bcrypt
import secrets


# Ejercicio #2:
def health_check(request):
    http_response = {"is_running": True}
    return JsonResponse(http_response)


# Ejercicio #4:
@csrf_exempt
def users(request):
    if request.method != 'POST':  ## ¿Qué pasa si el método es POST?
        return JsonResponse({'error': 'HTTP method not supported'}, status=405)  ## Error!

    # Ejercicio #5:
    try:
        body_json = json.loads(request.body)  ## Cargamos el cuerpo del modelo que importamos.
        json_username = body_json['username']
        json_email = body_json['email']
        json_password = body_json['password']
    except KeyError:
        return JsonResponse({"error": "Missing parameter in body request"}, status=400)

    # Ejercicio #5:
    if "@" not in json_email or len(json_email) < 7:  ## Si no contiene arroba o su long es menor que 7, error!
        return JsonResponse({"error": "Not valid email"}, status=400)

    salted_and_hashed_pass = bcrypt.hashpw(json_password.encode('utf8'), bcrypt.gensalt()).decode('utf8')
    ## Este es el proceso que empleamos para codificar la contraseña añadiendo, además una codificación hash a la par
    ## que se le añade una línea denominada salt, para complicar el intento de un hacker de pasar nuestra seguridad.

    # Ejercicio #5:
    existing_user = CustomUser.objects.filter(email=json_email).exists()
    if existing_user:
        return JsonResponse({"error": "User already registered"}, status=409)

    user_object = CustomUser(email=json_email, username=json_username, encrypted_password=salted_and_hashed_pass)
    ## Completamos los valores del modelo en una variable y la guardamos.
    user_object.save()

    return JsonResponse({"is_registered": True}, status=201)


# Ejercicio #7:
@csrf_exempt
def sessions(request):
    if request.method != 'POST':
        return JsonResponse({'error': 'HTTP method unsupported'}, status=405)
    # Ejercicio #8:
    try:
        body_json = json.loads(request.body)
        json_email = body_json['login_email']
        json_password = body_json['password']
    except KeyError:
        return JsonResponse({"error": "Missing parameter"}, status=400)

    try:  ## Procedemos a recuperar al usuario con email json_email de la base de datos:
        db_user = CustomUser.objects.get(email=json_email)
    # Ejercicio #8:
    except CustomUser.DoesNotExist:
        return JsonResponse({"error": "User not in our system"}, status=404)

    ## Verificamos si la contraseña proporcionada coincide con la contraseña almacenada en la base de datos
    ## para el usuario específico.
    if bcrypt.checkpw(json_password.encode('utf8'), db_user.encrypted_password.encode('utf8')):
        random_token = secrets.token_hex(10)  ## Generamos un token aleatorio utilizando el módulo secrets de Python.
        session = UserSession(creator=db_user, token=random_token)  ## Generamos y guardamos la session.
        session.save()
        return JsonResponse({"token": random_token}, status=201)
    # Ejercicio #8:
    else:
        return JsonResponse({"error": "Invalid password"}, status=401)


# Ejercicio #10:
@csrf_exempt
def categories(request):
    category = Category.objects.all()  ## Recogemos los datos del model

    json_response = []  ## Instanciamos la lista.

    for i in category:  ## Creamos un for que vaya rellenando la lista con lo que obtiene del model y, además,
        ## con el formato que definimos en la función to_json.
        json_response.append(i.to_json())
    return JsonResponse(json_response, safe=False)


# Ejercicio #11:
@csrf_exempt
def ideas(request, category_id):
    if request.method == 'POST':
        # El usuario va a añadir una idea a la base de datos
        try:  ## Comprobamos que la categoría existe.
            category = Category.objects.get(id=category_id)
        except Category.DoesNotExist:
            return JsonResponse({"error": "Category was not found"}, status=404)

        body_json = json.loads(request.body)  ## Cargamos el cuerpo del json.

        try:  ## Confirmamos que en el cuerpo de la petición no falta ningún parámetro.
            json_title = body_json['new_idea_title']
            json_description = body_json['content']
        except KeyError:
            return JsonResponse({"error": "Missing parameter in body"}, status=400)

        idea = Idea()  ## Instanciamos una variable con lo que viene a ser el modelo a utilizar.

        authenticated_user = __get_request_user(request)  ## Llamamos a la función que verifica si el user es válido.
        if authenticated_user is None:  ## Si no, error!
            return JsonResponse({"error": "Not valid token or missing header"}, status=401)

        ## Asignamos los valores correspondientes a cada parte correspondiente del modelo:

        idea.user = authenticated_user
        idea.title = json_title
        idea.description = json_description
        idea.category = category

        idea.save()  ## Guardamos los datos una vez recogidos.

        return JsonResponse({"success": True}, status=201)

    # Ejercicio #12:
    elif request.method == 'GET':

        try:  ## Comprobamos que existe la categoría que le pasamos.
            ## Obtenemos todas las categorías cuya pk coincida con la que le pasamos en category_id.
            ## Lo guardamos en una variable.
            categoria = Category.objects.get(pk=category_id)
        except Category.DoesNotExist:
            return JsonResponse({'error': 'Category was not found'}, status=404)

        ## Filtramos los resultados basandonos en lo obtenido en la variable categoria.
        idea = Idea.objects.filter(category=categoria)

        json_response = []
        for i in idea:
            json_response.append(i.to_json())
        return JsonResponse(json_response, safe=False)

    else:
        return JsonResponse({'error': 'HTTP method unsupported'}, status=405)


def __get_request_user(request):
    ## Esta función recoge el usuario deseado teniendo en cuenta que la cabecera de la petición API es personalizable.

    header_token = request.headers.get('Api-Session-Token', None)
    if header_token is None:
        return None
    try:
        db_session = UserSession.objects.get(token=header_token)
        return db_session.creator
    except UserSession.DoesNotExist:
        return None


# Ejercicio #13:
@csrf_exempt
def comments(request, idea_id):
    # Ejercicio #13:
    if request.method == 'POST':
        try:
            idea = Idea.objects.get(id=idea_id)
        except Idea.DoesNotExist:
            return JsonResponse({"error": "Category was not found"}, status=404)

        body_json = json.loads(request.body)

        try:
            json_content = body_json['content']
        except KeyError:
            return JsonResponse({"error": "Missing parameter in body"}, status=400)

        comment = Comment()

        authenticated_user = __get_request_user(request)
        if authenticated_user is None:
            return JsonResponse({"error": "Not valid token or missing header"}, status=401)

        comment.user = authenticated_user
        comment.idea = idea
        comment.content = json_content

        comment.save()

        return JsonResponse({"success": True}, status=201)

    # Ejercicio #14:
    elif request.method == 'GET':
        try:
            idea = Idea.objects.get(pk=idea_id)
        except Idea.DoesNotExist:
            return JsonResponse({'error': 'Category was not found'}, status=404)

        comment = Comment.objects.filter(idea=idea)

        json_response = []
        for i in comment:
            json_response.append(i.to_json())
        return JsonResponse(json_response, safe=False)

    else:
        return JsonResponse({'error': 'HTTP method unsupported'}, status=405)
