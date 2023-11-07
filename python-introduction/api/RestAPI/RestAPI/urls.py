"""RestAPI URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.0/topics/http/urls/
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

from django.contrib import admin
from django.urls import path
from webservice04app import views, endpoints

## Ejercicio 30:

urlpatterns = [
    path('admin/', admin.site.urls),
    path('example', views.my_first_view),
    path('emperors', views.my_second_view),

## Ejercicio 32:

    path('api/v1/example', endpoints.my_first_endpoint),

## Ejercicio 33:

    path('api/v1/not_found_example', endpoints.my_not_found_endpoint),

## Ejercicio 34:

    path('api/v1/animals', endpoints.my_list_endpoint),

]
