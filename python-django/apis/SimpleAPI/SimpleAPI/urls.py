"""
URL configuration for SimpleAPI project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path

from apis.SimpleAPI.simplerest04app import endpoints

urlpatterns = [
    path('admin/', admin.site.urls),
    path('health', endpoints.health_check),

    ## Ejercicio #3:
    path('multiplication_table_six', endpoints.table_of_six),

    ## Ejercicio #4:
    path('multiplication/<number>', endpoints.multiplication_table),

    ## Ejercicio #5:
    path('v2/multiplication/<int:number>', endpoints.multiply_number_improved),

    ## Ejercicio #6:
    path('ejemplo', endpoints.mi_funcion),
    path('v3/multiplication', endpoints.multiplication_table_query_param),

    ## Ejercicio #7:
    path('prime', endpoints.is_prime_number),

    ## Ejercicio #8:
    path('years_since/<int:year>', endpoints.years_since),

    ## Ejercicio #9:
    path('resource/<int:number>', endpoints.resource_example),

    ## Ejercicio #12:
    path('preferred_animal', endpoints.preferred_animals),

    ## Ejercicio #13:
    path('http_methods', endpoints.http_methods_example),
]
