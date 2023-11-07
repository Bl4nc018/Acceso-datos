import json

from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt

from dashboard04app.models import Dashboard, Question, Answer



## Ejercicio #25:

@csrf_exempt
def all_dashboards(request):
    if request.method == 'GET':
        dashboards = Dashboard.objects.all()  ## Recibimos todos los datos del Dashboard.

        json_response = []  ## Empleamos una lista para almacenarlos.
        for i in dashboards:
            ## Para cada dato que vamos leyendo en lo que recibimos (dashboards) lo almacenamos en un diccionario.
            dashboard_dict = {
                'id': i.id,
                'title': i.title,
                'content': i.content
            }
            json_response.append(dashboard_dict)

        # Retorna la lista de dashboards en formato JSON
        return JsonResponse(json_response, safe=False)


## Ejercicio #26:
@csrf_exempt
def dashboard_questions(request, dashboardId):
    if request.method == 'GET':
        d = Dashboard.objects.get(pk=dashboardId)
        ## questions = Question.objects.filter(dashboard=d).order_by("-publication_date")

        older_than = request.GET.get("older_than", None)
        page_size = request.GET.get("page_size", None)

        if page_size is not None:
            try:
                page_size = int(page_size)
            except ValueError:
                return JsonResponse({"error": "Wrong size parameter"}, status=400)

            if older_than is None:
                questions = Question.objects.filter(dashboard=d).order_by("-publication_date")[:page_size]
            else:
                questions = Question.objects.filter(dashboard=d).filter(publication_date__lt=older_than).order_by(
                    "-publication_date")[:page_size]
        else:
            if older_than is None:
                questions = Question.objects.filter(dashboard=d).order_by("-publication_date")
            else:
                questions = Question.objects.filter(dashboard=d).filter(publication_date__lt=older_than).order_by(
                    "-publication_date")

        json_response = []
        for i in questions:
            json_response.append(i.to_json())
        return JsonResponse(json_response, safe=False)

    ## Ejercicio #27:
    elif request.method == 'POST':
        d = Dashboard.objects.get(pk=dashboardId) ## Importante volver a traer el .get() ya que antes se instanció dentro del if del 'GET'

        try: ## Try por si nos falla el json.
            client_json = json.loads(request.body) ## Cargamos el json.
            question_title = client_json.get("title", None) ## Recogemos tanto el titulo como el contenido de la pregunta y lo guardamos en variables.
            question_content = client_json.get("content", None)

            if question_title is None or question_content is None: ## Si no tienen contenido dan error.
                return JsonResponse({"error": "Missing title or content in the request body"}, status=400)

            new_entry = Question(title=question_title, content=question_content, dashboard=d) ## Sino guardamos todo en una variable.
            new_entry.save()
            return JsonResponse({"Success": True}, status=201)

        except ValueError:
            return JsonResponse({"error": "Invalid JSON in the request body"}, status=400)

    else:
        return JsonResponse({"error": "Method not allowed"}, status=405)


## Ejercicio #28:
@csrf_exempt
def questions_answers(request, questionId):
    if request.method == 'GET':
        q = Question.objects.get(pk=questionId)

        older_than = request.GET.get("older_than", None)
        if older_than is None:
            questions = Answer.objects.filter(question=q).order_by("-publication_date")
        else:
            questions = Answer.objects.filter(question=q).filter(publication_date__lt=older_than).order_by(
                "-publication_date")

        json_response = []
        for i in questions:
            json_response.append(i.to_json())
        return JsonResponse(json_response, safe=False)

    elif request.method == 'POST':
        q = Question.objects.get(pk=questionId)

        try:
            client_json = json.loads(request.body)
            answer_content = client_json.get("content", None)

            if answer_content is None:
                return JsonResponse({"error": "Missing title or content in the request body"}, status=400)

            new_answer = Answer(content=answer_content, question=q)
            new_answer.save()
            return JsonResponse({"Success": True}, status=201)

        except ValueError:
            return JsonResponse({"error": "Invalid JSON in the request body"}, status=400)

    else:
        return JsonResponse({"error": "Method not allowed"}, status=405)