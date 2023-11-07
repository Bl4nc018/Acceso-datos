from django.db import models


## Ejercicio #25:
class Dashboard(models.Model):
    title = models.CharField(max_length=160)
    content = models.CharField(max_length=5500)

    def to_json(self):
        return {
            "id": self.id,
            "title": self.title,
            "content": self.content,
        }


class Question(models.Model):
    dashboard = models.ForeignKey(Dashboard, on_delete=models.CASCADE)
    title = models.CharField(max_length=160)
    content = models.CharField(max_length=5500)
    publication_date = models.DateTimeField(auto_now=True)

    def to_json(self):
        return {
            "id": self.id,
            "title": self.title,
            "content": self.content,
            "publication_date": self.publication_date,
        }


class Answer(models.Model):
    question = models.ForeignKey(Question, on_delete=models.CASCADE)
    content = models.CharField(max_length=5500)
    publication_date = models.DateTimeField(auto_now=True)

    def to_json(self):
        return {
            "content": self.content,
            "publication_date": self.publication_date,
        }
