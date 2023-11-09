from django.db import models


class Pokemon(models.Model):
    name = models.CharField(max_length=255)
    latitud = models.FloatField()
    longitud = models.FloatField()

    def to_json(self):
        return {
            "id": self.id,
            "name": self.name,
            "latitud": self.latitud,
            "longitud": self.longitud,
        }