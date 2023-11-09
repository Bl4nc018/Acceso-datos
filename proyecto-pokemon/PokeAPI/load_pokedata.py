import sqlite3

# Datos de Pokémon
pokemon_data = [
    ("Pikachu", 43.360, -8.411),
    ("Charmander", 43.355, -8.418),
    ("Bulbasaur", 43.354, -8.413),
    ("Lucario", 43.351, -8.416),
    ("Absol", 43.354, -8.417),
    ("Squirtle", 50.354, -10.417),
    ("Venosaur", 10.354, -1.417),
    ("Ivysaur", 100.354, -30.417),
]

# Conecta con la base de datos
conn = sqlite3.connect("db.sqlite3")
cursor = conn.cursor()

# Inserta los datos en la tabla
for pokemon in pokemon_data:
    cursor.execute("INSERT INTO pokerest04app_pokemon (name, latitud, longitud) VALUES (?, ?, ?)", pokemon)

# Guarda los cambios
conn.commit()

# Cierra la conexión
conn.close()

print("Datos de Pokémon insertados con éxito.")
