from django.shortcuts import render
from django.http import HttpResponse


## Ejercicio 30:
def my_first_view(request):
    return HttpResponse("Hola a todo el mundo, usando un HttpResponse")


## Ejercicio 31:

def my_second_view(request):
    return HttpResponse("""
        <html>
    <body>
        <h1>Calentando...</h1>
        <p>En esta página de prueba se muestran tres emperadores romanos</p>
        <ul>
            <li>Augusto</li>
            <li>Claudio</li>
            <li>Nerón</li>
        <ul>
    </body>
</html>      
        """)
