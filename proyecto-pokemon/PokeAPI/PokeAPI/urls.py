
from django.contrib import admin
from django.urls import path
from pokerest04app import endpoints


urlpatterns = [
    path('admin/', admin.site.urls),
    path('pokemon', endpoints.poke_recibir),
    path('pokemon/<int:id>', endpoints.poke_postear),
]
