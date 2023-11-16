from django.db import models


# Ejercicio #3:
class CustomUser(models.Model):
    email = models.CharField(max_length=280, unique=True)
    username = models.CharField(max_length=300)
    encrypted_password = models.CharField(max_length=120)


# Ejercicio #6:
class UserSession(models.Model):
    creator = models.ForeignKey(CustomUser, on_delete=models.CASCADE)
    token = models.CharField(unique=True, max_length=25)


# Ejercicio #9:
class Category(models.Model):
    title = models.CharField(max_length=60, unique=True)

    def __str__(self):
        return self.title

    # Ejercicio #10:
    def to_json(self):  ## Añado esta función para procesar los datos obtenidos del model
        return {
            "id": self.id,
            "name": self.title,
        }


class Idea(models.Model):
    title = models.CharField(max_length=200)
    description = models.CharField(max_length=2000)
    user = models.ForeignKey(CustomUser, on_delete=models.CASCADE)
    category = models.ForeignKey(Category, on_delete=models.CASCADE)

    # Ejercicio #12:
    def to_json(self):
        return{
            "id": self.id,
            "author_id": self.user.id,
            "idea_name": self.title,
            "content": self.description,
        }


class Comment(models.Model):
    content = models.CharField(max_length=1400)
    user = models.ForeignKey(CustomUser, on_delete=models.CASCADE)
    idea = models.ForeignKey(Idea, on_delete=models.CASCADE)

    def to_json(self):
        return{
            "comment_id": self.id,
            "author_id": self.user.id,
            "content": self.content,
        }