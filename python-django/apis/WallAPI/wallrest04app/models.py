from django.db import models


## Ejercicio #15:
class Entry(models.Model):
    title = models.CharField(max_length=160)
    content = models.CharField(max_length=5500)
    publication_date = models.DateTimeField(auto_now=True)

    def to_json(self):
        return {
            "title": self.title,
            "content": self.content,
            "created": self.publication_date,
        }


## Ejercicio #16:
class Comment(models.Model):  ## Cada Comment pertenece a un Entry . Cada Entry puede tener varios Comment.
    content = models.CharField(max_length=1300)
    entry = models.ForeignKey(Entry, on_delete=models.CASCADE)  # Clave foránea a Entry

    ## Ejercicio #20:
    def to_json(self):
        return {
            "comment": self.content,
        }
